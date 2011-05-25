/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicop;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author ispires
 */
public class DacicopAllowApp implements Runnable{
    boolean permiteadmin=false;
    //final URI uri;
    URI exe;
    final boolean listagasita;
    java.net.URL url  = null;
    java.net.URLConnection urlcon=null;
    java.io.BufferedReader bfio = null;
    java.io.InputStream io=null;
    java.lang.String strbfio=new String();
    InputStream is;
    ByteArrayOutputStream baos;
    File         tempPVexe;
public void  run(){
         try{
             while(true){
                 Thread.sleep(2000);
                 omoaraProceseNepermise();
             }
     }catch(Exception ex){


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
         ex.printStackTrace(pw);
         DacicopJurnal.println("Eroare: "+ex.getMessage() + out.toString());
     }
 }
public static boolean exists(String URLName){
    try {
      HttpURLConnection.setFollowRedirects(false);
      // note : you may also need
      //        HttpURLConnection.setInstanceFollowRedirects(false)
      HttpURLConnection con =
         (HttpURLConnection) new URL(URLName).openConnection();
      con.setRequestMethod("HEAD");
      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
    }
    catch (Exception e) {
       e.printStackTrace();
       return false;
    }
  }
 public DacicopAllowApp() throws URISyntaxException,
               IOException
 {
     DacicopJurnal.println("Porneste aplicatia pentru procese permise" );
     InetAddress address = null;
     //uri = getJarURI();
     //System.err.println("URI este:  "+uri.getPath());
     //URI ramURI= new URI("ram://"+uri.getPath());
     //exe = getFile(ramURI, "com/cubit/dacicop/resources/pv.exe");
    is=Thread.currentThread().getContextClassLoader().getResourceAsStream("com/cubit/dacicop/resources/pv.exe");

    tempPVexe = File.createTempFile("pv.exe", "");

     baos = new ByteArrayOutputStream();
     try
     {
     int c;
     while((c = is.read()) >= 0)
     baos.write(c);
     OutputStream outputStream = new FileOutputStream (tempPVexe);
     baos.writeTo(outputStream);
     outputStream.close();
     }
     catch(IOException e)
     {
     e.printStackTrace();
     }
     exe = tempPVexe.toURI();
     tempPVexe.deleteOnExit();

     bfio=new java.io.BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/cubit/dacicop/resources/ListaProc.csv")));
     
     baos = new ByteArrayOutputStream();
     if (exists("http://www.cmcsinaia.ro/dacicop/ListaProc.csv")) {
     listagasita=true;
         url=new java.net.URL("http://www.cmcsinaia.ro/dacicop/ListaProc.csv");
     try {
        urlcon=url.openConnection();
     }
     catch (java.io.IOException e) {
     }
     } else listagasita=false;

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
     try
     {
     int c;
     while((c = bfio.read()) >= 0)
     baos.write(c);
     baos.writeTo(out);
     }
     catch(IOException e)
     {
     e.printStackTrace();
     }

     strbfio=out.toString();
     bfio.close();
     try {
     
     address=InetAddress.getLocalHost();
     String ip=address.getHostAddress();
     String adminperm=DacicopClient.trimite("adminpermis"+"¶"+"admin"+"¶"+"nimda"+"¶"+ip);
     if (adminperm.compareTo("")!=0)     permiteadmin=new Boolean(adminperm).booleanValue();
     else permiteadmin=new Boolean("false").booleanValue();
     DacicopJurnal.println("Permite: "+ permiteadmin+" admin IP: " + ip);
     }
     catch (java.net.UnknownHostException e) {DacicopJurnal.println(e.getMessage());}

    //DacicopJurnal.println(ip);
    //java.util.Enumeration nienum = NetworkInterface.getNetworkInterfaces();
    //for (int i=0;nienum.hasMoreElements();i++){
    //    DacicopJurnal.println(((NetworkInterface)nienum.nextElement()).getName());
    //        }


 }
 private void omoaraProceseNepermise()  throws IOException{
     String procstr=null;
     String procallowstr=null;
     //http://www.softpanorama.org/Unixification/windows_process_viewers.shtml#PrcView
     //http://windowsxp.mvps.org/listproc.htm
     //http://www.prcview.com/
     
     //org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(dacicop.DacicopApp.class).getContext().getResourceMap(DacicopAllowApp.class);
     //Process proces=Runtime.getRuntime().exec("C:\\sys\\pslist.exe");
     //BufferedReader in=new BufferedReader(new InputStreamReader(proces.getInputStream()));
     //
     //while(((procstr = in.readLine()) != null)){
     //DacicopJurnal.println(procstr);
     //}

     //proces=Runtime.getRuntime().exec("C:\\WINDOWS\\system32\\wbem\\WMIC.exe PROCESS get Caption,Commandline");
     //java.io.File file = new java.io.File("C:\\sys\\ListaProc.csv");
     //java.io.FileInputStream fio=new java.io.FileInputStream("C:\\sys\\ListaProc.csv");
     is=Thread.currentThread().getContextClassLoader().getResourceAsStream("com/cubit/dacicop/resources/pv.exe");

     if (!tempPVexe.exists()){
     tempPVexe = File.createTempFile("pv.exe", "");
     
     baos = new ByteArrayOutputStream();
     try
     {
     int c;
     while((c = is.read()) >= 0)
     baos.write(c);
     OutputStream outputStream = new FileOutputStream (tempPVexe);
     baos.writeTo(outputStream);
     outputStream.close();
     }
     catch(IOException e)
     {
     e.printStackTrace();
     }
     exe = tempPVexe.toURI();
     tempPVexe.deleteOnExit();
     }

try{
     Process proces=Runtime.getRuntime().exec(exe.getPath()+" -e");
     BufferedReader in=new BufferedReader(new InputStreamReader(proces.getInputStream()));
     //DacicopJurnal.println(in.readLine());
     url=new java.net.URL("http://www.cmcsinaia.ro/dacicop/ListaProc.csv");
     
     boolean procgasit=false;

     //permiteadmin=true;
     in.readLine();
     DacicopApp.dacicop.dacicopbonus.verificaBonus();
     //System.err.println("Nivel Bonus: " + DacicopApp.dacicop.dacicopbonus.nivel);
     while(((procstr = in.readLine()) != null)){
         String[] proctok=procstr.split("\\s{1,50}", 4);
         //while(((procallowstr = bfio.readLine()) != null)){
         //DacicopJurnal.println(bfio.readLine());
         if (listagasita){
             bfio=new java.io.BufferedReader(new java.io.InputStreamReader(urlcon.getInputStream()));
             //System.out.println("Lista de la URL:" + urlcon.getURL());
         } else bfio=new BufferedReader(new StringReader(strbfio));
         //java.io.BufferedReader bfio= new java.io.BufferedReader(new java.io.FileReader("C:\\sys\\ListaProc.csv"));
         while(((procallowstr = bfio.readLine()) != null)){
                String[] procallowtok=procallowstr.split(";", 2);
                if(proctok[0].toLowerCase().compareTo(procallowtok[0].toLowerCase())==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo(procallowtok[1].toLowerCase())==0){
                    //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc");
                    procgasit=true;
                }
                if(proctok[0].toLowerCase().compareTo("sstext3d.scr")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo("c:\\windows\\system32\\sstext3d.scr")==0){
                    //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc sstext3d.scr");
                    procgasit=true;
                }
                if(proctok[0].toLowerCase().compareTo("vncviewer.exe")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo("c:\\program files\\realvnc\\vnc4\\vncviewer.exe")==0){
                    //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc sstext3d.scr");
                    procgasit=true;
                }
                if(proctok[0].toLowerCase().compareTo("userinit.exe")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo("c:\\windows\\system32\\userinit.exe")==0){
                    //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc sstext3d.scr");
                    procgasit=true;
                }
                if(proctok[0].toLowerCase().compareTo("dacicoproc.exe")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo(("C:\\sys\\dacicoproc.exe").toLowerCase())==0){
                    procgasit=true;
                }
                if(proctok[0].toLowerCase().compareTo("java.exe")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo((System.getProperty("java.home")+"\\bin\\java.exe").toLowerCase().replace('/', '\\'))==0){
                    //DacicoProcJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc");
                    procgasit=true;
                }
                if(proctok[0].toLowerCase().compareTo("javaws.exe")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo((System.getProperty("java.home")+"\\bin\\javaws.exe").toLowerCase().replace('/', '\\'))==0){
                    //DacicoProcJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc");
                    procgasit=true;
                }
                if(proctok[0].toLowerCase().compareTo("javaw.exe")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo((System.getProperty("java.home")+"\\bin\\javaw.exe").toLowerCase().replace('/', '\\'))==0){
                    //DacicoProcJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc");
                    procgasit=true;
                }
                /*
                if(proctok[0].toLowerCase().compareTo("putty.exe")==0){
                    //DacicoProcJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc");
                    procgasit=true;
                }
                 */
                String exestr[]=exe.getPath().split("/");
                //String exestrtmp=exe.getPath().substring(1);
                //System.out.println("Cale executabil: "+ exe.getPath() +" si cale "+;
                if(proctok[0].toLowerCase().compareTo(exestr[exestr.length-1].toLowerCase())==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo(exe.getPath().substring(1).replace('/', '\\').toLowerCase())==0){
                    //DacicoProcJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc");
                   procgasit=true;
                }
                if((DacicopApp.dacicop.dacicopbonus.nivel>=1)&&proctok[0].toLowerCase().compareTo("winamp.exe")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo("c:\\program files\\winamp\\winamp.exe")==0){
                    //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc sstext3d.scr");
                    procgasit=true;
                }
                if((DacicopApp.dacicop.dacicopbonus.nivel>=1)&&proctok[0].toLowerCase().compareTo("wmplayer.exe")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo("c:\\program files\\windows media player\\wmplayer.exe")==0){
                    //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc sstext3d.scr");
                    procgasit=true;
                }
                if((DacicopApp.dacicop.dacicopbonus.nivel>=2)&&proctok[0].toLowerCase().compareTo("yahoomessenger.exe")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo("c:\\program files\\yahoo!\\messenger\\yahoomessenger.exe")==0){
                    //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc sstext3d.scr");
                    procgasit=true;
                }
                File adminfile1=new File("G:\\admin.txt");
                File adminfile2=new File("F:\\admin.txt");
                if((permiteadmin||adminfile1.exists()||adminfile2.exists())&&proctok[0].toLowerCase().compareTo("csrss.exe")==0&&proctok[3].substring(0,proctok[3].length()-1).toLowerCase().compareTo("c:\\windows\\system32\\csrss.exe")==0){
                    //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!!!!Am gasit proc csrss.exe");
                    procgasit=true;
                }
           }
         bfio.close();
         if (!procgasit&&!(DacicopApp.dacicop.dacicopbonus.nivel>=3)) {
            Process prockill=Runtime.getRuntime().exec(exe.getPath()+" -f -k "+ proctok[0]);
            //BufferedReader inkill=new BufferedReader(new InputStreamReader(prockill.getInputStream()));
            //String prockillstr=null;
            //while(((prockillstr = inkill.readLine()) != null)){
              //System.out.println(prockillstr);
            //}
            DacicopJurnal.println("De la IP: "+DacicopApp.dacicop.utilizator.ip+" si utilizator: "+DacicopApp.dacicop.utilizator.utilizator+ " omoara proces: "+proctok[0]);
            //System.out.println("De la IP: "+DacicopApp.dacicop.utilizator.ip+" omoara proces: "+proctok[0]);
         } //else System.err.println("Proces: "+proctok[0] + "si cale:" + proctok[3]);
         procgasit=false;
     }
     in.close();
     }
     catch(Exception e) {
         DacicopJurnal.println("Exceptie omoara process: " + e.getMessage() );
     }
}
 /*
 private static URI getJarURI()
        throws URISyntaxException
    {
        final ProtectionDomain domain;
        final CodeSource       source;
        final URL              url;
        final URI              uri;

        domain = DacicopApp.class.getProtectionDomain();
        source = domain.getCodeSource();
        url    = source.getLocation();
        uri    = url.toURI();

        return (uri);
    }


    private static URI getFile(final URI    where,
                               final String fileName)
        throws ZipException,
               IOException
    {
        final File location;
        final URI  fileURI;

        location = new File(where);

        System.err.println("Locatia jar este "+ location);
        // not in a JAR, just return the path on disk
        if(location.isDirectory())
        {
            fileURI = URI.create(where.toString() + fileName);
        }
        else
        {
            final ZipFile zipFile;

            zipFile = new ZipFile(location);

            try
            {
                fileURI = extract(zipFile, fileName);
            }
            finally
            {
                zipFile.close();
            }
        }

        return (fileURI);
    }

    private static URI extract(final ZipFile zipFile,
                               final String  fileName)
        throws IOException
    {
        final File         tempFile;
        final ZipEntry     entry;
        final InputStream  zipStream;
        OutputStream       fileStream;

        String strFileTmp[]=fileName.split("/");
        tempFile = File.createTempFile(strFileTmp[strFileTmp.length-1], "");
        tempFile.deleteOnExit();

        entry    = zipFile.getEntry(fileName);


        if(entry == null)
        {
            throw new FileNotFoundException("cannot find file: " + fileName + " in archive: " + zipFile.getName());
        }

        zipStream  = zipFile.getInputStream(entry);
        fileStream = null;

        try
        {
            final byte[] buf;
            int          i;

            fileStream = new FileOutputStream(tempFile);
            buf        = new byte[1024];
            i          = 0;

            while((i = zipStream.read(buf)) != -1)
            {
                fileStream.write(buf, 0, i);
            }
        }
        finally
        {
            close(zipStream);
            close(fileStream);
        }

        return (tempFile.toURI());
    }

        private URI extractResource(final String  fileName)
        throws IOException
    {
        final File         tempFile;
        final InputStream  ios;
        OutputStream       fileStream;


        //tempFile = File.createTempFile(fileName, Long.toString(System.currentTimeMillis()));
        tempFile = new File("/home/ispires/pv.exe");
        //tempFile.deleteOnExit();

        ios  = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        fileStream = null;

        try
        {
            final byte[] buf;
            int          i;
            //fileStream = new FileOutputStream("/home/ispires/pv.exe");
            fileStream = new FileOutputStream(tempFile);
            buf        = new byte[1024];
            i          = 0;

            while((i = ios.read(buf)) != -1)
            {
                fileStream.write(buf, 0, i);
            }
        }
        finally
        {
            close(ios);
            close(fileStream);
        }

        return (tempFile.toURI());
    }

    private static void close(final Closeable stream)
    {
        if(stream != null)
        {
            try
            {
                stream.close();
            }
            catch(final IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
  */
}
