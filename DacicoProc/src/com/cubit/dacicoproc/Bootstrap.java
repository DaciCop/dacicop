/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.cubit.dacicoproc;


import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

//import org.apache.dacicoproc.security.SecurityClassLoad;
//import org.apache.juli.logging.Log;
//import org.apache.juli.logging.LogFactory;


/**
 * Boostrap loader for DacicoProc.  This application constructs a class loader
 * for use in loading the DacicoProc internal classes (by accumulating all of the
 * JAR files found in the "server" directory under "dacicoproc.home"), and
 * starts the regular execution of the container.  The purpose of this
 * roundabout approach is to keep the DacicoProc internal classes (and any
 * other classes they depend on, such as an XML parser) out of the system
 * class path and therefore not visible to application level classes.
 *
 * @author Craig R. McClanahan
 * @author Remy Maucherat
 * @version $Revision: 467222 $ $Date: 2006-10-24 05:17:11 +0200 (Tue, 24 Oct 2006) $
 */

public final class Bootstrap {

    //private static Log log = LogFactory.getLog(Bootstrap.class);
    
    // -------------------------------------------------------------- Constants


    // ------------------------------------------------------- Static Variables


    /**
     * Daemon object used by main.
     */
    private static Bootstrap daemon = null;


    // -------------------------------------------------------------- Variables


    /**
     * Daemon reference.
     */
    private Object dacicoprocDaemon = null;


    protected ClassLoader commonLoader = null;
    protected ClassLoader dacicoprocLoader = null;
    protected ClassLoader sharedLoader = null;


    // -------------------------------------------------------- Private Methods


    private void initClassLoaders() {
        try {
            commonLoader = createClassLoader("common", null);
            if( commonLoader == null ) {
                // no config file, default to this loader - we might be in a 'single' env.
                commonLoader=this.getClass().getClassLoader();
            }
            dacicoprocLoader = createClassLoader("server", commonLoader);
            sharedLoader = createClassLoader("shared", commonLoader);
        } catch (Throwable t) {
            //log.error("Class loader creation threw exception", t);
            System.exit(1);
        }
    }


    private ClassLoader createClassLoader(String name, ClassLoader parent)
        throws Exception {

        String value = "C:\\sys\\lib\\tools.jar";
        if ((value == null) || (value.equals("")))
            return parent;

        ArrayList repositoryLocations = new ArrayList();
        ArrayList repositoryTypes = new ArrayList();
        int i;
 
        StringTokenizer tokenizer = new StringTokenizer(value, ",");
        while (tokenizer.hasMoreElements()) {
            String repository = tokenizer.nextToken();

            // Local repository
                    
            //if (replace && log.isDebugEnabled())
               // log.debug("Expanded " + before + " to " + replace);

            // Check for a JAR URL repository
            try {
                URL url=new URL(repository);
                repositoryLocations.add(repository);
                repositoryTypes.add(ClassLoaderFactory.IS_URL);
                continue;
            } catch (MalformedURLException e) {
                // Ignore
            }

            if (repository.endsWith("*.jar")) {
                repository = repository.substring
                    (0, repository.length() - "*.jar".length());
                repositoryLocations.add(repository);
                repositoryTypes.add(ClassLoaderFactory.IS_GLOB);
            } else if (repository.endsWith(".jar")) {
                repositoryLocations.add(repository);
                repositoryTypes.add(ClassLoaderFactory.IS_JAR);
            } else {
                repositoryLocations.add(repository);
                repositoryTypes.add(ClassLoaderFactory.IS_DIR);
            }
        }

        String[] locations = (String[]) repositoryLocations.toArray(new String[0]);
        Integer[] types = (Integer[]) repositoryTypes.toArray(new Integer[0]);
 
        ClassLoader classLoader = ClassLoaderFactory.createClassLoader
            (locations, types, parent);

        // Retrieving MBean server
        MBeanServer mBeanServer = null;
        if (MBeanServerFactory.findMBeanServer(null).size() > 0) {
            mBeanServer =
                (MBeanServer) MBeanServerFactory.findMBeanServer(null).get(0);
        } else {
            mBeanServer = MBeanServerFactory.createMBeanServer();
        }

        // Register the server classloader
        ObjectName objectName =
            new ObjectName("Main:type=URLClassLoader,name=" + name);
        mBeanServer.registerMBean(classLoader, objectName);

        return classLoader;

    }


    /**
     * Initialize daemon.
     */
    public void init()
        throws Exception
    {

        // Set DacicoProc path
        setDacicoProcHome();
        setDacicoProcBase();

        initClassLoaders();

        Thread.currentThread().setContextClassLoader(dacicoprocLoader);

        //SecurityClassLoad.securityClassLoad(dacicoprocLoader);

        // Load our startup class and call its process() method
        //if (log.isDebugEnabled())
        //    log.debug("Loading startup class");
        Class startupClass =
            dacicoprocLoader.loadClass
            ("com.cubit.dacicoproc.Test");

        Class[] arglist = new Class[1];
        arglist[0] = String[].class;
        java.lang.reflect.Method pornire = startupClass.getDeclaredMethod("main", arglist);
        Object startupInstance = pornire.invoke(null, new Object[] {new String("start")});


        // Set the shared extensions class loader
        //if (log.isDebugEnabled())
        //    log.debug("Setting startup class properties");
        String methodName = "setParentClassLoader";
        Class paramTypes[] = new Class[1];
        paramTypes[0] = Class.forName("java.lang.ClassLoader");
        Object paramValues[] = new Object[1];
        paramValues[0] = sharedLoader;
        Method method =
            startupInstance.getClass().getMethod(methodName, paramTypes);
        method.invoke(startupInstance, paramValues);

        dacicoprocDaemon = startupInstance;

    }


    /**
     * Load daemon.
     */
    private void load(String[] arguments)
        throws Exception {

        // Call the load() method
        String methodName = "load";
        Object param[];
        Class paramTypes[];
        if (arguments==null || arguments.length==0) {
            paramTypes = null;
            param = null;
        } else {
            paramTypes = new Class[1];
            paramTypes[0] = arguments.getClass();
            param = new Object[1];
            param[0] = arguments;
        }
        Method method = 
            dacicoprocDaemon.getClass().getMethod(methodName, paramTypes);
        //if (log.isDebugEnabled())
        //    log.debug("Calling startup class " + method);
        method.invoke(dacicoprocDaemon, param);

    }


    // ----------------------------------------------------------- Main Program


    /**
     * Load the DacicoProc daemon.
     */
    public void init(String[] arguments)
        throws Exception {

        init();
        load(arguments);

    }


    /**
     * Start the DacicoProc daemon.
     */
    public void start()
        throws Exception {
        if( dacicoprocDaemon==null ) init();

        Method method = dacicoprocDaemon.getClass().getMethod("start", (Class [] )null);
        method.invoke(dacicoprocDaemon, (Object [])null);

    }


    /**
     * Stop the DacicoProc Daemon.
     */
    public void stop()
        throws Exception {

        Method method = dacicoprocDaemon.getClass().getMethod("stop", (Class [] ) null);
        method.invoke(dacicoprocDaemon, (Object [] ) null);

    }


    /**
     * Stop the standlone server.
     */
    public void stopServer()
        throws Exception {

        Method method = 
            dacicoprocDaemon.getClass().getMethod("stopServer", (Class []) null);
        method.invoke(dacicoprocDaemon, (Object []) null);

    }


   /**
     * Stop the standlone server.
     */
    public void stopServer(String[] arguments)
        throws Exception {

        Object param[];
        Class paramTypes[];
        if (arguments==null || arguments.length==0) {
            paramTypes = null;
            param = null;
        } else {
            paramTypes = new Class[1];
            paramTypes[0] = arguments.getClass();
            param = new Object[1];
            param[0] = arguments;
        }
        Method method = 
            dacicoprocDaemon.getClass().getMethod("stopServer", paramTypes);
        method.invoke(dacicoprocDaemon, param);

    }


    /**
     * Set flag.
     */
    public void setAwait(boolean await)
        throws Exception {

        Class paramTypes[] = new Class[1];
        paramTypes[0] = Boolean.TYPE;
        Object paramValues[] = new Object[1];
        paramValues[0] = new Boolean(await);
        Method method = 
            dacicoprocDaemon.getClass().getMethod("setAwait", paramTypes);
        method.invoke(dacicoprocDaemon, paramValues);

    }

    public boolean getAwait()
        throws Exception
    {
        Class paramTypes[] = new Class[0];
        Object paramValues[] = new Object[0];
        Method method =
            dacicoprocDaemon.getClass().getMethod("getAwait", paramTypes);
        Boolean b=(Boolean)method.invoke(dacicoprocDaemon, paramValues);
        return b.booleanValue();
    }


    /**
     * Destroy the DacicoProc Daemon.
     */
    public void destroy() {

        // FIXME

    }


    /**
     * Main method, used for testing only.
     *
     * @param args Command line arguments to be processed
     */
    public static void main(String args[]) {

        if (daemon == null) {
            daemon = new Bootstrap();
            try {
                daemon.init();
            } catch (Throwable t) {
                t.printStackTrace();
                return;
            }
        }

        try {
            String command = "start";
            if (args.length > 0) {
                command = args[args.length - 1];
            }

            if (command.equals("startd")) {
                args[0] = "start";
                daemon.load(args);
                daemon.start();
            } else if (command.equals("stopd")) {
                args[0] = "stop";
                daemon.stop();
            } else if (command.equals("start")) {
                daemon.setAwait(true);
                daemon.load(args);
                daemon.start();
            } else if (command.equals("stop")) {
                daemon.stopServer(args);
            } else {
                //log.warn("Bootstrap: command \"" + command + "\" does not exist.");
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    public void setDacicoProcHome(String s) {
        System.setProperty( "dacicoproc.home", s );
    }

    public void setDacicoProcBase(String s) {
        System.setProperty( "dacicoproc.base", s );
    }


    /**
     * Set the <code>dacicoproc.base</code> System property to the current
     * working directory if it has not been set.
     */
    private void setDacicoProcBase() {

        if (System.getProperty("dacicoproc.base") != null)
            return;
        if (System.getProperty("dacicoproc.home") != null)
            System.setProperty("dacicoproc.base",
                               System.getProperty("dacicoproc.home"));
        else
            System.setProperty("dacicoproc.base",
                               System.getProperty("user.dir"));

    }


    /**
     * Set the <code>dacicoproc.home</code> System property to the current
     * working directory if it has not been set.
     */
    private void setDacicoProcHome() {

        if (System.getProperty("dacicoproc.home") != null)
            return;
        File bootstrapJar = 
            new File(System.getProperty("user.dir"), "bootstrap.jar");
        if (bootstrapJar.exists()) {
            try {
                System.setProperty
                    ("dacicoproc.home",
                     (new File(System.getProperty("user.dir"), ".."))
                     .getCanonicalPath());
            } catch (Exception e) {
                // Ignore
                System.setProperty("dacicoproc.home",
                                   System.getProperty("user.dir"));
            }
        } else {
            System.setProperty("dacicoproc.home",
                               System.getProperty("user.dir"));
        }

    }


    /**
     * Get the value of the dacicoproc.home environment variable.
     */
    public static String getDacicoProcHome() {
        return System.getProperty("dacicoproc.home",
                                  System.getProperty("user.dir"));
    }


    /**
     * Get the value of the dacicoproc.base environment variable.
     */
    public static String getDacicoProcBase() {
        return System.getProperty("dacicoproc.base", getDacicoProcHome());
    }


}
