/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicoproc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.*;
import sun.jvmstat.monitor.*;

/**
 *
 * @author ispires
 */
public class Main implements Runnable {
    boolean dacicopgasit=false;
    static String idDacicoProc = new String();
    static DacicoProcAllowApp s;
    Thread serv;
    String procstrexp=null;
    boolean explorergasit=false;
    public void run() {
        while(true){
        try{
        Thread.sleep(1000);
        //String javahome=System.getProperty("java.home");
        //System.out.println("Java home: " + javahome);
        MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
        //boolean javawseste=false;
        //boolean javaweste=false;
        /* Take all active VM’s on Host, LocalHost here */
        Set vmlist = new HashSet(local.activeVms());
        dacicopgasit=false;
        //s.stop=true;
        for (Object id : vmlist) {
            /* 1234 - Specifies the Java Virtual Machine identified by lvmid 1234 on an unnamed host.
            This string is transformed into the absolute form //1234, which must be resolved against
            a HostIdentifier. */
            MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + id));
            /* take care of class file and jar file both */
            String processname = MonitoredVmUtil.mainClass(vm, true);
            String argumente = MonitoredVmUtil.mainArgs(vm);
            idDacicoProc=id.toString();
            //System.out.println(id + " --> " + processname + " argumente: " + argumente);
            //if (argumente!=null) System.out.println(id + " |" + processname.toLowerCase().startsWith("com.sun.javaws.Main".toLowerCase()) + "| argumente: |" + argumente.toLowerCase().startsWith("http://www.cmcsinaia.ro/dacicop/dacicop.jnlp".toLowerCase())+"|");
            if (argumente!=null)
            if (processname.toLowerCase().startsWith("com.sun.javaws.Main".toLowerCase())&&argumente.toLowerCase().startsWith("http://www.cmcsinaia.ro/dacicop/dacicop.jnlp".toLowerCase())) {
                //System.out.println("Gasit dacicop: ");
                dacicopgasit=true;
            } //else s.stop=false;
        }
        }catch (java.lang.InterruptedException e){}
        catch (sun.jvmstat.monitor.MonitorException e){}
        catch (java.net.URISyntaxException e){}
        try{
        Process procesexp=Runtime.getRuntime().exec(s.exe.getPath()+" -e");
        BufferedReader inexp=new BufferedReader(new InputStreamReader(procesexp.getInputStream()));
        while(((procstrexp = inexp.readLine()) != null)){
            //System.err.println(procstrexp.toLowerCase() + "contine explorer.exe: " + procstrexp.toLowerCase().contains("explorer.exe".subSequence(0, 12)));
            if (procstrexp.toLowerCase().contains("explorer.exe".subSequence(0, 12))) {explorergasit=true;
            //break;
            }
        }
        }
        catch (IOException e) {}

        try {
        String verifpornit=DacicoProcClient.trimite("verificapornit");
        
        s.stop=java.lang.Boolean.parseBoolean(verifpornit);
        if (!dacicopgasit&&explorergasit&&!s.stop) {Runtime.getRuntime().exec(System.getProperty("java.home")+"\\bin\\javaws.exe http://www.cmcsinaia.ro/dacicop/dacicop.jnlp");
                        Thread.sleep(5000);
                        //s.stop=true;
                        //Thread.sleep(1000);
                        //System.out.println("Proc allow thread stop ? "+s.stop);
                       // dacicopgasit=true;
        } else {
            //System.out.println("Serv este viu?" + serv.isAlive());
            
            //System.out.println("Verifica pornit dacicop este: " + s.stop);
            if (!serv.isAlive()&&s.stop==false) {
            explorergasit=false;
            serv=new Thread(s);
            serv.setDaemon(true);
            serv.start();
            //System.out.println("Start proc allow thread : "+!s.stop);
        }
        }
        }catch (java.io.IOException e){}
        catch (java.lang.InterruptedException e){}
        }
    }
    Main(){
    try {
    s= new DacicoProcAllowApp();
    serv=new Thread(s);
    serv.setDaemon(true);
    serv.start();
    }
    catch (IOException e) {
        java.io.OutputStream out=new java.io.OutputStream()
            {
                private StringBuilder string = new StringBuilder();
                @Override
                public void write(int b) throws IOException {
                    this.string.append((char) b );
                }

                //Netbeans IDE automatically overrides this toString()
                public String toString(){
                    return this.string.toString();
                }
            };
         java.io.PrintStream pw=new java.io.PrintStream(out);
         e.printStackTrace(pw);

        System.err.println("Eroare IOException " + out.toString());}
    }

    public static void main(String[] args) {
    // TODO code application logic here
    //if (args[0].startsWith("start")){
    Runtime.getRuntime().addShutdownHook(new Thread(){
    //private DacicopView dacicop=null;
    public void  run(){
             try{
                 InetAddress address = null;
                 address=InetAddress.getLocalHost();
                 String ip=address.getHostAddress();
                 DacicoProcClient.trimite("jurnalnou"+"¶"+"jurnal"+"¶"+"jurnal"+"¶"+"Inchidere DacicoProc la Exit de la IP:" + ip + " - ID dacicop: " + idDacicoProc);
                 DacicoProcClient.trimite("exitdeconectare"+"¶"+ip);
                 //System.out.println("Inchidere DacicoProc la Exit de la IP:" + ip );
                 Runtime.getRuntime().exec(s.exe.getPath()+" -xa -k -id "+ idDacicoProc);
             }
                  catch (java.net.UnknownHostException e) {System.out.println(e.getMessage());}         catch(Exception ex){
             System.out.println("Eroare: "+ex.getMessage());
         }

     }

    });
        Main server=new Main();
    Thread thread=new Thread(server);
    System.out.println("Porneste DacicoProc");
    thread.start();
    /*} else {
        try{
                 InetAddress address = null;
                 address=InetAddress.getLocalHost();
                 String ip=address.getHostAddress();
                 DacicoProcClient.trimite("jurnalnou"+"¶"+"jurnal"+"¶"+"jurnal"+"¶"+"Inchidere DacicoProc la Exit de la IP:" + ip + " - ID dacicop: " + idDacicoProc);
                 DacicoProcClient.trimite("exitdeconectare"+"¶"+ip);
                 //System.out.println("Inchidere DacicoProc la Exit de la IP:" + ip );
                 Runtime.getRuntime().exec(s.exe.getPath()+" -xa -k -id "+ idDacicoProc);
             }
                  catch (java.net.UnknownHostException e) {System.out.println(e.getMessage());}         catch(Exception ex){
             System.out.println("Eroare: "+ex.getMessage());
                  }
        System.exit(0);
    }
     */
    }
}
