/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicopserv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ispires
 */
/*
public class Main implements Runnable {
    final static int serverPort   = 9999;                      // server port number
    final static int nrInstante=50;
    DacicopJurnal jurnal=new DacicopJurnal();
    public void run() {
         Server  ser=new Server(serverPort,nrInstante);
         Thread t=new Thread(ser);
         t.start();
         //t.setDaemon(true);
         DezactiveazaBonus bonus= new DezactiveazaBonus();
         Thread b=new Thread(bonus);
         b.start();
         //b.setDaemon(true);
    }

    public static void main(String[] args) {
    // TODO code application logic here
    Main server=new Main();
    Thread thread=new Thread(server);   
    System.out.println("Porneste serverul");
    thread.start();
    }
}
*/
public class Main extends Thread {
  protected Socket socket;
  static java.util.Vector sesiuniVector;
  static String sesUtilDeDezactivat;

  static Main head = null;

  protected Main next = null;
  private static String ascii = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz*&");
  protected     PrintStream out;
  protected   BufferedReader in;

  DacicopJurnal jurnal=new DacicopJurnal();
  AccesDB dbcon = new AccesDB();

  AccesIptables  iptables=new AccesIptables();

  static protected boolean init(Class clobj, int threads) {
    try {
      for (int i = 0; i < threads; i++) {
        Main thread = (Main) clobj.newInstance();
        thread.next = head;
        head = thread;
        thread.start();
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  static synchronized protected void addToList(Main me) {
    me.next = head;
    head = me;
  }

  synchronized protected void waitForSignal() {
    try {
      wait();
    } catch (InterruptedException e) {
    }
  }

  public void run() {
    //b.setDaemon(true);
    while (true) {
      waitForSignal();
      doClientProcessing();
      addToList(this);
    }
  }

  synchronized protected void handleClient(Socket s) {
    socket = s;
    notify();
  }

  protected void doClientProcessing() {
    try {

        getIOStream();
        //socket.setSoTimeout(1);
        //this.wait(500);
         sleep(200);
         proccessConnection();
         closeConnection();
    }
    catch (Exception e) {
    jurnal.println(e.getMessage());
    }
  }

  static synchronized protected boolean listEmpty() {
    return head == null;
  }

  static protected void assignThread(Socket sock) {
    Main t = head;
    head = head.next;
    t.socket = sock;
    synchronized (t) {
      t.notify();
    }
  }

  static public void startServer(int port) {
    ServerSocket ssock;
    //Socket sock;
    DezactiveazaBonus bonus= new DezactiveazaBonus();
    Thread b=new Thread(bonus);
    b.start();
    try {
      ssock = new ServerSocket(port);
      while (true) {
        Socket esock = null;
        try {
          esock = ssock.accept();
          while (listEmpty())
            yield();
          //System.out.println("A intrat in bucla");
          assignThread(esock);
        } catch (Exception e) {
          try {
            esock.close();
          } catch (Exception ec) {
          }
        }
        //System.out.println("A iesit din bucla");
      }
    } catch (IOException e) {
    }
  }

  static public void main(String args[]) {
    init(Main.class, 50);
    System.out.println("Starting server on port 9999");
    startServer(9999);
  }

  public void getIOStream(){
         try{
         out=new PrintStream(socket.getOutputStream());
         in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
         }catch(Exception ex){
            jurnal.println("Eroare flux IO "+ex.getMessage());
         }
     }

     public void proccessConnection(){
             try{
              if (in.ready()){
              
              sendMessages(gen(trateazaMesaj(ungen(in.readLine()))));
              if (sesUtilDeDezactivat!=null) if (sesUtilDeDezactivat.compareTo("")!=0) {interziceListaCereri(sesUtilDeDezactivat);
              String idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+sesUtilDeDezactivat+"'", "id");
              String ip = dbcon.getString("SELECT ip from sesiuni where idutil='"+idutil+"' and datapornire=dataoprire order by datapornire desc", "ip");
              dbcon.update("UPDATE sesiuni set dataoprire=NOW() where ip='"+ip+"' and datapornire=dataoprire");
              }
              out.flush();
              out.close();
              }
             }
             catch(Exception ex){
                  jurnal.println("Eroare la procesarea conexiunii: "+ex.getMessage());
                  ex.printStackTrace();
             }
        }

     public void closeConnection(){
             try{
                 socket.close();
                 out.close();
                 in.close();
                 }catch(Exception ex){
                    jurnal.println("Eroare la inchiderea conexiunii: "+ex.getMessage()+"')");
                 }
         }

        public void sendMessages(String s){
         try{
             out.println(s);
         }catch(Exception ex){
             jurnal.println("Eroare la trimiterea mesajului: "+ex.getMessage());
         }
     }

    public String getGrupDinOra(String prof){
    return "9A";
    }

     public  String trateazaMesaj(String mesaj){
     String result=new String("Stop"+"¶"+"Stop"+"¶"+"Stop"+"¶"+"Stop"+"¶"+"Stop");
     String idutil=new String("0");
     //jurnal.println("Mesaj cerere brut: " + mesaj);
     String[] mesaje = mesaj.split("\\¶{1}");
     //jurnal.println("Mesaj cerere: " + mesaje[0] + mesaje[1]);
     
     if (mesaje[0].compareTo("verificapornit")==0) {
     int nrelem=0;
     boolean gasitelement=false;
     if (Main.sesiuniVector==null) Main.sesiuniVector=new java.util.Vector();
     if (!Main.sesiuniVector.isEmpty())
     for (java.util.Iterator iter=sesiuniVector.iterator();iter.hasNext();nrelem++){
                 Object[] element=(Object[])iter.next();
                 if (element[0].toString().compareTo(socket.getInetAddress().getHostAddress())==0) {
                 gasitelement=true;
                 }
                 
     }
     //System.out.println("Verificat pornit de la IP "+socket.getInetAddress().getHostAddress()+" este:" + gasitelement);
     result = java.lang.Boolean.toString(gasitelement);
     }
     
     if (mesaje[0].compareTo("exitdeconectare")==0) {
             idutil=dbcon.getString("SELECT idutil from sesiuni where ip='"+mesaje[1]+"' and datapornire=dataoprire order by datapornire desc", "idutil");
             String utilizator=dbcon.getString("SELECT utilizator from utilizatori where id='"+idutil+"'", "utilizator");
             int sesiune=dbcon.getInteger("SELECT id from sesiuni where ip='"+mesaje[1]+"' and datapornire=dataoprire order by datapornire desc","id");
             jurnal.println("ID-ul sesiunii este: " + sesiune );
             if(!utilizator.isEmpty()&&sesiune!=0){
             interziceListaCereri(utilizator);
             dbcon.update("UPDATE sesiuni set dataoprire=NOW() where id='"+sesiune+"' and idutil='"+idutil+"'");
             }
     }

     if (mesaje[0].compareTo("conectare")==0) {
         //result=;
         String parolaDB5=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"' and activ=true ", "parola");
         String idgrup=dbcon.getString("SELECT grup from utilizatori where utilizator='"+mesaje[1]+"'", "grup");
         String grup=dbcon.getString("SELECT grup from grupuri where id='"+idgrup+"'", "grup");
         String idipacces=dbcon.getString("SELECT ipacces from utilizatori where utilizator='"+mesaje[1]+"'", "ipacces");
         String ipacces=dbcon.getString("SELECT ip from ip where id='"+idipacces+"'", "ip");
         String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");
         //jurnal.println("Ip acces:" + ipacces + "si ip conectare: " +  mesaje[4] );

         if (parolaDB5.compareTo(mesaje[2])==0&&((ipacces.compareTo(mesaje[4])==0&&rol.startsWith("3"))||rol.startsWith("1")||rol.startsWith("2"))) {
             result=new String(dbcon.getString("SELECT id from utilizatori where utilizator='"+mesaje[1]+"' and activ=true ", "id")+"¶"+parolaDB5+"¶"+dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol")+"¶"+grup+"¶"+idgrup);
             idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+mesaje[1]+"'", "id");
             dbcon.update("INSERT into sesiuni(idutil,mac,ip,datapornire,dataoprire) VALUES("+idutil+",'"+mesaje[3]+"','"+mesaje[4]+"',NOW(),NOW())");
             // Activeaza bonus
             String nivelbonus=dbcon.getString("SELECT nivel from bonus where activ=true and expirat=false and (beneficiari LIKE 'toti=' OR beneficiari LIKE 'grup=%"+idgrup+"-"+grup+" %' OR beneficiari LIKE 'lista=%"+idutil+"-"+mesaje[1]+"%') order by nivel desc", "nivel");
             if (nivelbonus.compareTo("")!=0) { executaBonus(nivelbonus,"lista="+idutil+"-"+mesaje[1]);}
             // Activeaza link-uri
             permiteListaCereri(mesaje[1]);
             int nrelem=0;
             for (java.util.Iterator iter=sesiuniVector.iterator();iter.hasNext();nrelem++){
                 Object[] element=(Object[])iter.next();
                 if (element[0].toString().compareTo(mesaje[4])==0) {
                     sesiuniVector.setElementAt(new Object[]{element[0],mesaje[1],true,dbcon.getString("SELECT NOW() as acum","acum")},nrelem);
                 }
             }
         }
     }
     if (mesaje[0].compareTo("deconectare")==0) {
         result=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
         if (result.compareTo(mesaje[2])==0){
             idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+mesaje[1]+"'", "id");
             int sesiune=dbcon.getInteger("SELECT id from sesiuni where idutil='"+idutil+"' and mac='"+mesaje[3]+"' and ip='"+mesaje[4]+"' and datapornire=dataoprire order by datapornire desc","id");
             jurnal.println("ID-ul sesiunii este: " + sesiune );
             interziceListaCereri(mesaje[1]);
             dbcon.update("UPDATE sesiuni set dataoprire=NOW() where id='"+sesiune+"' and idutil='"+idutil+"'");
             int nrelem=0;
             if (Main.sesiuniVector==null) Main.sesiuniVector=new java.util.Vector();
             if (!Main.sesiuniVector.isEmpty())
             for (java.util.Iterator iter=sesiuniVector.iterator();iter.hasNext();nrelem++){
                 Object[] element=(Object[])iter.next();
                 if (element[0].toString().compareTo(mesaje[4])==0) {
                     sesiuniVector.setElementAt(new Object[]{element[0],"neconectat",true,dbcon.getString("SELECT NOW() as acum","acum")},nrelem);
                 }
             }
        }
     }
     if (mesaje[0].compareTo("verifrol")==0) {
         result=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");
     }
     if (mesaje[0].compareTo("stergecerere")==0) {
         String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
         String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");
         idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+mesaje[1]+"'", "id");
         if (parola.compareTo(mesaje[2])==0)
         { if (rol.compareTo("1")==0)
              dbcon.update("UPDATE cereri set valabil=false where id='"+mesaje[3]+"'");
                else dbcon.update("UPDATE cereri set valabil=false where id='"+mesaje[3]+"' and idutil='"+idutil+"'");
         String url=dbcon.getString("SELECT url from cereri where id='"+mesaje[3]+"'", "url");
         String beneficiari=dbcon.getString("SELECT beneficiari from cereri where id='"+mesaje[3]+"'", "beneficiari");
         interziceCerere(url,beneficiari);
         }
     }
     if (mesaje[0].compareTo("aprobacerere")==0) {
         String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
         String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");
         //jurnal.println("!!!! -- Parola: "+ parola.compareTo(mesaje[2]) + " si rol: "+ rol.compareTo("1"));
         if (parola.compareTo(mesaje[2])==0&&(rol.compareTo("1")==0||rol.compareTo("2")==0))
         {
             //jurnal.println("!!!!!! Activare link cu id:" + mesaje[3]);
             dbcon.update("UPDATE cereri set activ='true' where id='"+mesaje[3]+"'");

             String idut=dbcon.getString("SELECT idutil from cereri where id='"+mesaje[3]+"'", "idutil");
             String ipsesiune=dbcon.getString("SELECT ip from sesiuni where idutil='"+idut+"' and datapornire=dataoprire", "ip");
             String url=dbcon.getString("SELECT url from cereri where id='"+mesaje[3]+"'", "url");
             String beneficiari=dbcon.getString("SELECT beneficiari from cereri where id='"+mesaje[3]+"'", "beneficiari");
             permiteCerere(url,beneficiari);
         }
     }
     if (mesaje[0].compareTo("listacereri")==0) {
         String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
         if (parola.compareTo(mesaje[2])==0){
         idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+mesaje[1]+"'", "id");
         String idgrup=dbcon.getString("SELECT grup from utilizatori where utilizator='"+mesaje[1]+"'", "grup");
         String gruputil=dbcon.getString("SELECT grup from grupuri where id='"+idgrup+"'", "grup");
         String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");

         String query=new String("SELECT url,inceputinterv,sfarsitinterv,beneficiari,activ,inceput,sfarsit,comentariu,id from cereri where valabil=true "+(rol.startsWith("1")||rol.startsWith("2")?"":" AND ( idutil='"+idutil+"' OR beneficiari LIKE 'toti=' OR beneficiari LIKE 'grup=%"+idgrup+"-"+gruputil+" %' OR beneficiari LIKE 'lista=%"+idutil+"-"+mesaje[1]+"%' OR beneficiari='eu="+idutil+"-"+mesaje[1]+"')")+" order by datacerere desc, url asc ");
         //jurnal.println("Teeeeessssssttttttt query: " + query);
         java.util.Vector raspunsvect=dbcon.getVector(query);
         StringBuffer raspunsbufwr=new StringBuffer();

         int nrvect=1;
         //try{
         for (java.util.Iterator iter=raspunsvect.iterator();iter.hasNext();nrvect++){
         String next=iter.next().toString();
            //jurnal.println(next);
            raspunsbufwr.append(next+"¹");
            //jurnal.println("  --------- nrvect%9: " + nrvect%9);
            if (nrvect%9==0) {
            String idutilcerere=dbcon.getString("SELECT idutil from cereri where id='"+next+"'", "idutil");
            String nume=dbcon.getString("SELECT nume from utilizatori where id='"+idutilcerere+"'", "nume");
            String prenume=dbcon.getString("SELECT prenume from utilizatori where id='"+idutilcerere+"'", "prenume");
            String grup=dbcon.getString("SELECT grup from utilizatori where id='"+idutilcerere+"'", "grup");

            raspunsbufwr.append(nume+" "+prenume+"¹");
            raspunsbufwr.append(grup+"-"+dbcon.getString("SELECT grup from grupuri where id='"+grup+"'", "grup"));
            //raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
            raspunsbufwr.append("\n");
            }
         }
         //raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
         //}
         //}catch (java.io.IOException e) {jurnal.println(e.getMessage());}
         //jurnal.println(raspunsbufwr);

         result=raspunsbufwr.toString();
         }
     }
     if (mesaje[0].compareTo("listabonus")==0) {
         String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
         String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");
         if (parola.compareTo(mesaje[2])==0&&(rol.startsWith("1")||rol.startsWith("2"))){

         String query=new String("SELECT id,nivel,beneficiari,data-NOW() from bonus where activ=true and expirat=false order by data-NOW() desc");

         java.util.Vector raspunsvect=dbcon.getVector(query);
         StringBuffer raspunsbufwr=new StringBuffer();

         int nrvect=1;
         //try{
         for (java.util.Iterator iter=raspunsvect.iterator();iter.hasNext();nrvect++){
         String next=iter.next().toString();
            //jurnal.println(next);
            raspunsbufwr.append(next+"¹");


            if (nrvect%4==0){
            raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
            raspunsbufwr.append("\n");
            }
         }
         result=raspunsbufwr.toString();
         }
     }
     if (mesaje[0].compareTo("bonusnou")==0) {
     String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
     String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");
     if (parola.compareTo(mesaje[2])==0&&(rol.startsWith("1")||rol.startsWith("2"))){
             String query=new String("INSERT into bonus(nivel,beneficiari,data) VALUES('"+mesaje[3]+"','"+mesaje[4]+"',NOW()+ interval '15 minute')");
             dbcon.update(query);
             executaBonus(mesaje[3],mesaje[4]);
         }
     }
     if (mesaje[0].compareTo("jurnalnou")==0) {
     //String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
     //String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");
     if ("jurnal".compareTo(mesaje[2])==0){
             //String query=new String("INSERT into jurnal(jurnal) VALUES('"+mesaje[3]+"')");
             dbcon.update("INSERT into jurnal(jurnal) VALUES('"+mesaje[3]+"')");
         }
     }

     if (mesaje[0].compareTo("verificabonus")==0) {
     String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
     boolean pass=false;
     if (parola.compareTo(mesaje[2])==0){
         idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+mesaje[1]+"'", "id");
         String idgrup=dbcon.getString("SELECT grup from utilizatori where utilizator='"+mesaje[1]+"'", "grup");
         String gruputil=dbcon.getString("SELECT grup from grupuri where id='"+idgrup+"'", "grup");
           result=dbcon.getString("SELECT nivel from bonus where activ=true and expirat=false and (beneficiari LIKE 'toti=' OR beneficiari LIKE 'grup=%"+idgrup+"-"+gruputil+" %' OR beneficiari LIKE 'lista=%"+idutil+"-"+mesaje[1]+"%') order by nivel desc", "nivel");
           pass=true;
     }
     if (result.compareTo("")==0||!pass) { result="0";}
       //jurnal.println("Nivel verif bonus:  "+ result );
     int nrelem=0;
     boolean gasitelement=false;
     if (sesiuniVector==null) sesiuniVector=new java.util.Vector();
     if (!sesiuniVector.isEmpty())
         for (java.util.Iterator iter=sesiuniVector.iterator();iter.hasNext();nrelem++){

             Object[] element=(Object[])iter.next();
             //System.out.println("Sesiuni verifbonus inainte: " + element[0] + " - " + element[1] +" - "+ element[2] + " - " + element[3]);
             if (element[0].toString().compareTo(socket.getInetAddress().getHostAddress())==0) {
                 gasitelement=true;
                 element[3]=dbcon.getString("SELECT NOW() as acum","acum");
                 sesiuniVector.setElementAt(element,nrelem);
                 }
             //System.out.println("Sesiuni verifbonus dupa: " + element[0] + " - " + element[1] +" - "+ element[2] + " - " + element[3]);
     }
     if (!gasitelement) sesiuniVector.add(new Object[]{socket.getInetAddress().getHostAddress(),mesaje[1],true,dbcon.getString("SELECT NOW() as acum","acum")});
     }

     if (mesaje[0].compareTo("adminpermis")==0) {

     if ("nimda".compareTo(mesaje[2])==0&&("admin".compareTo(mesaje[1])==0)){

         String id=dbcon.getString("SELECT id from admin where ip='"+mesaje[3]+"' and activ=true", "id" );
         //System.out.println("ID admin este: "+id);
         if (id.compareTo("")!=0) result="true";
             else result="false";
     }
     }

     if (mesaje[0].compareTo("permiteadmin")==0) {
     if ("nimda".compareTo(mesaje[2])==0&&("admin".compareTo(mesaje[1])==0)){
         String ip=dbcon.getString("SELECT ip from ip where id='"+mesaje[3]+"'","ip");
         dbcon.update("INSERT into admin(ip) values('"+ip+"')");
       }
     }

     if (mesaje[0].compareTo("interziceadmin")==0) {
     if ("nimda".compareTo(mesaje[2])==0&&("admin".compareTo(mesaje[1])==0)){
         String ip=dbcon.getString("SELECT ip from ip where id='"+mesaje[3]+"'","ip");
         dbcon.update("UPDATE admin set activ=false where ip='"+ip+"' and activ=true");
     }
     }


     if (mesaje[0].compareTo("mesajnou")==0) {
     String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
         if (parola.compareTo(mesaje[2])==0){
             String query=new String("INSERT into mesaje(subiect,dela,catre,mesaj,data,activ) VALUES('"+mesaje[3]+"','"+mesaje[4]+"','"+mesaje[5]+"','"+mesaje[6]+"','NOW()',true)");
             dbcon.update(query);
         }
     }
     if (mesaje[0].compareTo("stergemesaj")==0) {
     String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
     String dela=dbcon.getString("SELECT dela from mesaje where id='"+mesaje[3]+"'", "dela");
     jurnal.println("Dela :" + dela.split("-")[1].toString() + " mesaje[1]:" + mesaje[1]);
     if (parola.compareTo(mesaje[2])==0&&mesaje[1].compareTo(dela.split("-")[1].toString())==0){
             String query=new String("UPDATE mesaje set activ=false where id='"+mesaje[3]+"'");
             dbcon.update(query);
         }
     }
     if (mesaje[0].compareTo("continutmesaj")==0) {
     String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
     if (parola.compareTo(mesaje[2])==0){
           result=dbcon.getString("Select mesaj from mesaje where id='"+mesaje[3]+"'","mesaj");
         }
     }
     if (mesaje[0].compareTo("listamesaje")==0) {
         String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
         if (parola.compareTo(mesaje[2])==0){
         idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+mesaje[1]+"'", "id");
         String idgrup=dbcon.getString("SELECT grup from utilizatori where utilizator='"+mesaje[1]+"'", "grup");
         String gruputil=dbcon.getString("SELECT grup from grupuri where id='"+idgrup+"'", "grup");
         String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");

         String strnivelclasa=new String("");

         if (java.lang.Character.isDigit(gruputil.charAt(0))) strnivelclasa= strnivelclasa+gruputil.charAt(0);
         if (java.lang.Character.isDigit(gruputil.charAt(1))) strnivelclasa= strnivelclasa+gruputil.charAt(1);


         String query=new String("");
               if (rol.startsWith("1")) query=new String("SELECT id,subiect,dela,catre,data from mesaje where activ=true order by data desc");
               else if (rol.startsWith("2"))  query=new String("SELECT id,subiect,dela,catre,data from mesaje where activ=true AND ( catre LIKE '%"+mesaje[1]+"%' OR catre LIKE 'toti' OR catre LIKE 'prof') order by data desc");
               else query=new String("SELECT id,subiect,dela,catre,data from mesaje where activ=true AND (catre LIKE '%"+mesaje[1]+"%' OR catre LIKE 'toti' OR catre LIKE 'clase %" + strnivelclasa+"%' OR catre LIKE '" +gruputil+"') order by data desc");
         //jurnal.println("Teeeeessssssttttttt query: " + query);
         java.util.Vector raspunsvect=dbcon.getVector(query);
         StringBuffer raspunsbufwr=new StringBuffer();

         int nrvect=1;
         //try{
         for (java.util.Iterator iter=raspunsvect.iterator();iter.hasNext();nrvect++){
         String next=iter.next().toString();
            //jurnal.println(next);
            raspunsbufwr.append(next+"¹");
            //jurnal.println("  --------- nrvect%9: " + nrvect%9);
            if (nrvect%5==0) {
            //String idutilcerere=dbcon.getString("SELECT idutil from cereri where id='"+next+"'", "idutil");
            //String nume=dbcon.getString("SELECT nume from utilizatori where id='"+idutilcerere+"'", "nume");
            //String prenume=dbcon.getString("SELECT prenume from utilizatori where id='"+idutilcerere+"'", "prenume");
            //String grup=dbcon.getString("SELECT grup from utilizatori where id='"+idutilcerere+"'", "grup");

            //raspunsbufwr.append(nume+" "+prenume+"¹");
            //raspunsbufwr.append(grup+"-"+dbcon.getString("SELECT grup from grupuri where id='"+grup+"'", "grup"));
            raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
            raspunsbufwr.append("\n");
            }
         }
         //raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
         //}
         //}catch (java.io.IOException e) {jurnal.println(e.getMessage());}
         //jurnal.println(raspunsbufwr);

         result=raspunsbufwr.toString();
         }
     }

     if (mesaje[0].compareTo("listautil")==0) {
         String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"'", "parola");
         if (parola.compareTo(mesaje[2])==0){
         idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+mesaje[1]+"'", "id");
         String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");
         String grupprof=getGrupDinOra("prof");
         String query=new String("SELECT utilizator,nume,prenume,grup,rol,ipacces,activ,id from utilizatori "+((rol.startsWith("1")||rol.startsWith("2"))?"":" where grup='"+ grupprof +"'"));
         //jurnal.println("Teeeeessssssttttttt query: " + query);
         java.util.Vector raspunsvect=dbcon.getVector(query);
         StringBuffer raspunsbufwr=new StringBuffer();

         int nrvect=1;
         //try{
         for (java.util.Iterator iter=raspunsvect.iterator();iter.hasNext();nrvect++){
         String next=iter.next().toString();
         int idut=0;
         //if (nrvect%6==0) {}
            raspunsbufwr.append(next+"¹");
            //jurnal.println("  --------- next:"+next+" la nrvect:" + nrvect);
            if (nrvect==4) {
                raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
                raspunsbufwr.append("-"+dbcon.getString("SELECT grup from grupuri where id='"+next+"'","grup") + "¹");
            }
            if (nrvect==5) {
                raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
                raspunsbufwr.append("-"+dbcon.getString("SELECT rol from roluri where id='"+next+"'","rol") + "¹");
            }
            if (nrvect==8) {
            idut=new Integer(next).intValue();
            //jurnal.println("Id utilizator este : "+idut);
            String ipsesiune=dbcon.getString("SELECT ip from sesiuni where idutil='"+idut+"' and datapornire=dataoprire order by datapornire desc limit 1", "ip");
            if (ipsesiune.compareTo("")==0) ipsesiune=new String("neconectat");
            //jurnal.println("IP sesiune este: " + ipsesiune);
            //String nume=dbcon.getString("SELECT nume from utilizatori where id='"+idutilcerere+"'", "nume");
            //String prenume=dbcon.getString("SELECT prenume from utilizatori where id='"+idutilcerere+"'", "prenume");
            //String grup=dbcon.getString("SELECT grup from utilizatori where id='"+idutilcerere+"'", "grup");

            raspunsbufwr.append(ipsesiune);
            //raspunsbufwr.append(grup);
            //raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
            raspunsbufwr.append("\n");
            nrvect=0;
            }
         }
         //raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
         //}
         //}catch (java.io.IOException e) {jurnal.println(e.getMessage());}
         //jurnal.println(raspunsbufwr);

         result=raspunsbufwr.toString();
         }
     }

     if (mesaje[0].compareTo("listagrup")==0) {
            java.util.Vector grupuri=dbcon.getVector("SELECT grup from grupuri " + ((mesaje.length==4)?"where id="+mesaje[3]:""));
            StringBuffer raspunsbufwr=new StringBuffer();
         for (java.util.Iterator iter=grupuri.iterator();iter.hasNext();){
         String next=iter.next().toString();
         raspunsbufwr.append(next+"¹");
         }
         raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
         raspunsbufwr.append("\n");
         result=raspunsbufwr.toString();
     }

     if (mesaje[0].compareTo("listaip")==0) {
            java.util.Vector grupuri=dbcon.getVector("SELECT ip from ip "+((mesaje.length==4)?" where id="+mesaje[3]:"")+" order by ip asc");
            StringBuffer raspunsbufwr=new StringBuffer();
         int nrvect=1;
         for (java.util.Iterator iter=grupuri.iterator();iter.hasNext();nrvect++){
         String next=iter.next().toString();
         raspunsbufwr.append(next+"¹");
        }
         raspunsbufwr.deleteCharAt(raspunsbufwr.length()-1);
         raspunsbufwr.append("\n");
         result=raspunsbufwr.toString();
     }

     if (mesaje[0].compareTo("creareutil")==0) {
        idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+mesaje[1]+"'", "id");
        String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");
        byte[] hash=null;
        String parolaDB5=null;
        java.security.MessageDigest digest = null;
        try{
            digest=java.security.MessageDigest.getInstance("MD5");
        }
        catch(java.security.NoSuchAlgorithmException e){jurnal.println(e.getMessage());}
        if (mesaje.length==12&&mesaje[4].compareTo("")!=0) {
            jurnal.println("12 - Parola este: " + mesaje[4]);
            digest.update(mesaje[4].getBytes());
        }
        else if (mesaje[4].compareTo("")==0&&mesaje.length!=12) {
            jurnal.println("11 - Parola este: " + mesaje[3]);
            digest.update(mesaje[3].getBytes());
        } else if (mesaje[4].compareTo("")!=0&&mesaje.length!=12) {digest.update(mesaje[4].getBytes());}
        hash = digest.digest();
        java.lang.StringBuffer hashstrbuff = new java.lang.StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            hashstrbuff.append(String.format("%02X%s", hash[i], (i < hash.length - 1) ? "" : ""));
        }
        parolaDB5=hashstrbuff.toString();
        String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"' and activ=true ", "parola");
        if ((rol.compareTo("1")==0||rol.compareTo("2")==0)&&parola.compareTo(mesaje[2])==0) {
            if (mesaje.length==12) {
                String query=new String("UPDATE utilizatori set utilizator='"+mesaje[3]+"', parola='"+parolaDB5+"', nume='"+mesaje[5]+"', prenume='"+mesaje[6]+"', grup='"+mesaje[7]+"', rol='"+mesaje[8]+"', ipacces='"+mesaje[9]+"', activ='"+mesaje[10]+"' where id='"+mesaje[11]+"'");
                if (mesaje[4].compareTo("")==0) 
                {
                    query=new String("UPDATE utilizatori set utilizator='"+mesaje[3]+"', nume='"+mesaje[5]+"', prenume='"+mesaje[6]+"', grup='"+mesaje[7]+"', rol='"+mesaje[8]+"', ipacces='"+mesaje[9]+"', activ='"+mesaje[10]+"' where id='"+mesaje[11]+"'");
                }
                dbcon.update(query);
            }
            else {
                String query=new String("INSERT into utilizatori(utilizator,parola,nume,prenume,grup,rol,ipacces,activ)  VALUES('"+mesaje[3]+"','"+parolaDB5+"','"+mesaje[5]+"','"+mesaje[6]+"','"+mesaje[7]+"','"+mesaje[8]+"','"+mesaje[9]+"','"+mesaje[10]+"')");
                dbcon.update(query);
            }
        }
        result=new String ("Utilizator actualizat");
     }

     if (mesaje[0].compareTo("cerere")==0) {
         String parola=dbcon.getString("SELECT parola from utilizatori where utilizator='"+mesaje[1]+"' and activ=true ", "parola");
         if (parola.compareTo(mesaje[2])==0)
         {
             //jurnal.println("!!!!!! Mesajul este " + mesaj);
             String  inceputinterv=new String("1970-01-01 00:00:00");
             String  sfarsitinterv=new String("1970-01-01 00:00:00");
             //String  inceputinterv=new String("permanent");
             //String  sfarsitinterv=new String("permanent");
             String  inceput=new String("1971¶01¶01¶00¶00");
             String  sfarsit=new String("1971¶01¶01¶00¶00");
             java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
             java.lang.Integer anincepinterv=new java.lang.Integer("1970");
             java.lang.Integer ansfarinterv=new java.lang.Integer("1970");
             inceput=mesaje[4]+"¶"+mesaje[5]+"¶"+mesaje[6]+"¶"+mesaje[7]+"¶"+mesaje[8];
             sfarsit=mesaje[9]+"¶"+mesaje[10]+"¶"+mesaje[11]+"¶"+mesaje[12]+"¶"+mesaje[13];
             if (mesaje[4].compareTo("0")!=0&&mesaje[5].compareTo("0")!=0&&mesaje[6].compareTo("0")!=0) {
                 anincepinterv=new java.lang.Integer(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)+new java.lang.Integer(mesaje[4]).intValue()-1);
                 inceputinterv=new String(anincepinterv+"-"+String.format("%02d",new java.lang.Integer(mesaje[5]).intValue())+"-"+String.format("%02d",new java.lang.Integer(mesaje[6]).intValue())+" "+String.format("%02d",new java.lang.Integer(mesaje[7]).intValue())+":"+String.format("%02d",new java.lang.Integer(mesaje[8]).intValue())+":00");
             }
             if (mesaje[9].compareTo("0")!=0&&mesaje[10].compareTo("0")!=0&&mesaje[11].compareTo("0")!=0) {
                 ansfarinterv=new java.lang.Integer(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)+new java.lang.Integer(mesaje[9]).intValue()-1);
                 sfarsitinterv=new String(ansfarinterv+"-"+String.format("%02d",new java.lang.Integer(mesaje[10]).intValue())+"-"+String.format("%02d",new java.lang.Integer(mesaje[11]).intValue())+" "+String.format("%02d",new java.lang.Integer(mesaje[12]).intValue())+":"+String.format("%02d",new java.lang.Integer(mesaje[13]).intValue())+":00");
             }

             //jurnal.println("Inceput interval: " + inceputinterv);
             //jurnal.println("Sfarsit interval: " + sfarsitinterv);
             idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+mesaje[1]+"'", "id");
             //jurnal.println("ID utilizator este " + idutil);
             String rol=dbcon.getString("SELECT rol from utilizatori where utilizator='"+mesaje[1]+"'", "rol");
             if (rol.compareTo("3")==0&&mesaje[16].compareTo("true")==0) mesaje[16]="false";
             //jurnal.println("Lungime mesaje "+mesaje.length);

             //jurnal.println(query);
             if (mesaje.length==18) {
                String idut=dbcon.getString("SELECT idutil from cereri where id='"+mesaje[17]+"'", "idutil");
                String ipsesiune=dbcon.getString("SELECT ip from sesiuni where idutil='"+idut+"' and datapornire=dataoprire", "ip");
                 if (idutil.compareTo(idut)==0||rol.compareTo("1")==0||rol.compareTo("2")==0){
                   String url=dbcon.getString("SELECT url from cereri where id='"+mesaje[17]+"'", "url");
                    String beneficiari=dbcon.getString("SELECT beneficiari from cereri where id='"+mesaje[17]+"'", "beneficiari");
                    interziceCerere(url,beneficiari);

                    //String query=new String("UPDATE cereri set url='"+mesaje[3]+"',inceputinterv='"+inceputinterv+"',sfarsitinterv='"+sfarsitinterv+"',beneficiari='"+mesaje[14]+"',activ='"+mesaje[16]+"',idutil='"+idutil+"',inceput='"+inceput+"',sfarsit='"+sfarsit+"',comentariu='"+mesaje[15]+"' where id='"+mesaje[17]+"'");
                    //String query=new String("UPDATE cereri set valabil=false where id='"+mesaje[17]+"'");
                    //System.out.println("Update modeificare este: " + query);
                    dbcon.update("UPDATE cereri set valabil=false where id='"+mesaje[17]+"'");
                    dbcon.update("INSERT into cereri(url,inceputinterv,sfarsitinterv,beneficiari,activ,idutil,inceput,sfarsit,comentariu) values('"+mesaje[3]+"','"+inceputinterv+"','"+sfarsitinterv+"','"+mesaje[14]+"','"+mesaje[16]+"',"+idutil+",'"+inceput+"','"+sfarsit+"','"+mesaje[15]+"')");
                    if (new Boolean(mesaje[16]).booleanValue()) {
                        permiteCerere(mesaje[3],mesaje[14]);
                        //jurnal.println("Modif cerere executie permiteCerere");
                        //System.out.println("Modif cerere executie permiteCerere");
                    }
                    //else interziceCerere(mesaje[3],beneficiari);
                 }
                 }
             else {dbcon.update("INSERT into cereri(url,inceputinterv,sfarsitinterv,beneficiari,activ,idutil,inceput,sfarsit,comentariu) values('"+mesaje[3]+"','"+inceputinterv+"','"+sfarsitinterv+"','"+mesaje[14]+"','"+mesaje[16]+"',"+idutil+",'"+inceput+"','"+sfarsit+"','"+mesaje[15]+"')");
             //String id=dbcon.getString("SELECT id from cereri order by datacerere desc limit 1", "url");
             if (new Boolean(mesaje[16]).booleanValue()) {
                permiteCerere(mesaje[3],mesaje[14]);
             }
             }
         }
         result=new String("Cerere receptionata");
     }
     //jurnal.println(" ----- rezultat trimitere = "+ result);
     return result;
     }


     public static String gen(String degen)
	{
		String tichet=new String("INCEP");
		String nr64str=new String();
		for (int i=0; i<degen.length();i++)
		{
			int nr64 =degen.charAt(i)/64;
			String stri=Integer.toString(i);

			if (nr64!=1) nr64str=nr64str+nr64+"0000000000".substring(0,10-stri.length())+i;
			//tichet = tichet+ascii.charAt(degen.charAt(i)%64);
            tichet = tichet+ascii.charAt(degen.charAt(i)%64);
		}
//		jurnal.println(nr64str);
		return nr64str+tichet;
	}
//	ascii codes  48-57 numere 0123456789
//				65-90 litere mari ABCDEFGHIJKLMNOPQRSTUVWXYZ
//				97-122 litere mici 	abcdefghijklmnopqrstuvwxyz
/*
		 // Encode a String into bytes
		 String inputString = "blahblahblah??";
		 byte[] input = inputString.getBytes("UTF-8");

		 // Compress the bytes
		 byte[] output = new byte[100];
		 Deflater compresser = new Deflater();
		 compresser.setInput(input);
		 compresser.finish();
		 int compressedDataLength = compresser.deflate(output);

		 // Decompress the bytes
		 Inflater decompresser = new Inflater();
		 decompresser.setInput(output, 0, compressedDataLength);
		 byte[] result = new byte[100];
		 int resultLength = decompresser.inflate(result);
		 decompresser.end();

		 // Decode the bytes into a String
		 String outputString = new String(result, 0, resultLength, "UTF-8");
*/
	public static String ungen(String tichet)
	{
		String tic= new String();
		String cap= new String();
			if (tichet.indexOf("INCEP")!=0) cap=tichet.substring(0, tichet.indexOf("INCEP"));
		String coada=new String(tichet.substring(tichet.indexOf("INCEP")+5));
		//jurnal.println(coada);
		int j=0;
		for(int a=0;a<coada.length();a++)
		{
			int inmul=1,poz=-1;
			if (cap.length()!=0&&(j*11)!=cap.length()) {
				poz=Integer.parseInt(cap.substring(j*11+1, (j+1)*11));
				}
			//jurnal.println(poz);
			if (poz==a) {
				inmul=Integer.parseInt(String.valueOf(cap.charAt(j*11)));
				if ((j*11)!=cap.length()) j++;
			}
			tic=tic + (char)(64*inmul+ascii.indexOf(coada.charAt(a)));
			//jurnal.println(tic);
		}
//		jurnal.println(tic);
		return tic;
	}

    public void permiteCerere(String adresa,String beneficiari){
        if (beneficiari.startsWith("toti=")) {
                        java.util.Vector totivect=dbcon.getVector("SELECT ip from sesiuni where (datapornire<=NOW() AND datapornire=dataoprire) order by datapornire desc");
                        for (java.util.Iterator iterbenef=totivect.iterator();iterbenef.hasNext();){
                        String ipses=iterbenef.next().toString();
                            iptables.accesIptables(adresa, ipses+"-"+ipses, true);
                        }
                    }
                    if (beneficiari.startsWith("lista=")) {
                        String beneficiaristr=new String("");
                        if (!beneficiari.endsWith("="))
                        {
                            beneficiaristr=beneficiari.split("=")[1];
                            String benefstr[]=beneficiaristr.split(",");
                            for (int i=0;i<benefstr.length;i++){
                                String id=benefstr[i].split("-")[0];
                                String listavect =dbcon.getString("SELECT ip from sesiuni where idutil='"+id+"' and (datapornire<=NOW() AND datapornire=dataoprire) order by datapornire desc", "ip");
                                //for (java.util.Iterator iterbenef=listavect.iterator();iterbenef.hasNext();){
                                     String ipses=listavect;
                                    iptables.accesIptables(adresa, ipses+"-"+ipses, true);
                                //}
                        }
                      }
                    }
                    if (beneficiari.startsWith("grup=")) {
                        String beneficiaristr=beneficiari.split("=")[1];
                        String idgrup=beneficiaristr.split("-")[0];
                        java.util.Vector listagrupvect=dbcon.getVector("SELECT sesiuni.ip from sesiuni JOIN utilizatori on utilizatori.id=sesiuni.idutil where utilizatori.grup='"+idgrup+"' and (sesiuni.datapornire<=NOW() AND sesiuni.datapornire=sesiuni.dataoprire) order by datapornire desc");
                        for (java.util.Iterator iterbenef=listagrupvect.iterator();iterbenef.hasNext();){
                                String ipses=iterbenef.next().toString();
                                iptables.accesIptables(adresa, ipses+"-"+ipses, true);
                            }
                    }
    }
    public void interziceCerere(String adresa,String beneficiari){
        if (beneficiari.startsWith("toti=")) {
                        java.util.Vector totivect=dbcon.getVector("SELECT ip from sesiuni where (datapornire<=NOW() AND datapornire=dataoprire) order by datapornire desc");
                        for (java.util.Iterator iterbenef=totivect.iterator();iterbenef.hasNext();){
                        String ipses=iterbenef.next().toString();
                            iptables.accesIptables(adresa, ipses+"-"+ipses, false);
                        }
                    }
                    if (beneficiari.startsWith("lista=")) {
                        String beneficiaristr=new String("");
                        if (!beneficiari.endsWith("="))
                        {
                        beneficiaristr=beneficiari.split("=")[1];
                        String benefstr[]=beneficiaristr.split(",");
                        for (int i=0;i<benefstr.length;i++){
                            String id=benefstr[i].split("-")[0];
                            String listavect =dbcon.getString("SELECT ip from sesiuni where idutil='"+id+"' and (datapornire<=NOW() AND datapornire=dataoprire) order by datapornire desc", "ip");
                            //for (java.util.Iterator iterbenef=listavect.iterator();iterbenef.hasNext();){
                                 String ipses=listavect;
                                iptables.accesIptables(adresa, ipses+"-"+ipses, false);
                            //}
                        }
                        }
                    }
                    if (beneficiari.startsWith("grup=")) {
                        String beneficiaristr=beneficiari.split("=")[1];
                        String idgrup=beneficiaristr.split("-")[0];
                        java.util.Vector listagrupvect=dbcon.getVector("SELECT sesiuni.ip from sesiuni JOIN utilizatori on utilizatori.id=sesiuni.idutil where utilizatori.grup='"+idgrup+"' and (sesiuni.datapornire<=NOW() AND sesiuni.datapornire=sesiuni.dataoprire) order by datapornire desc");
                        for (java.util.Iterator iterbenef=listagrupvect.iterator();iterbenef.hasNext();){
                                String ipses=iterbenef.next().toString();
                                iptables.accesIptables(adresa, ipses+"-"+ipses, false);
                            }
                    }
    }
    void permiteListaCereri(String utilizator){

         String idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+utilizator+"'", "id");
         String idgrup=dbcon.getString("SELECT grup from utilizatori where utilizator='"+utilizator+"'", "grup");
         String gruputil=dbcon.getString("SELECT grup from grupuri where id='"+idgrup+"'", "grup");

        String query=new String("SELECT url from cereri where valabil=true AND activ=true AND ( idutil='"+idutil+"' OR beneficiari LIKE 'toti=' OR beneficiari LIKE 'grup=%"+idgrup+"-"+gruputil+" %' OR beneficiari LIKE 'lista=%"+idutil+"-"+utilizator+"%' OR beneficiari='eu="+idutil+"-"+utilizator+"') AND ((inceputinterv<=NOW() AND sfarsitinterv>=NOW()) OR (inceputinterv=sfarsitinterv AND inceputinterv='1970-01-01 00:00:00')) order by datacerere desc, url asc ");
        //System.out.println("SELECT url from cereri where valabil=true AND ( idutil='"+idutil+"' OR beneficiari LIKE 'toti=' OR beneficiari LIKE 'grup=%"+idgrup+"-"+gruputil+" %' OR beneficiari LIKE 'lista=%"+idutil+"-"+utilizator+"%' OR beneficiari='eu="+idutil+"-"+utilizator+"') AND ((inceputinterv<=NOW() AND sfarsitinterv>=NOW()) OR (inceputinterv=sfarsitinterv AND inceputinterv='1970-01-01 00:00:00')) order by datacerere desc, url asc ");
        java.util.Vector raspunsvect=dbcon.getVector(query);
         for (java.util.Iterator iter=raspunsvect.iterator();iter.hasNext();){
             permiteCerere(iter.next().toString(),"lista="+idutil+"-"+utilizator);
         }
    }
        public void interziceListaCereri(String utilizator){

         String idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+utilizator+"'", "id");
         String idgrup=dbcon.getString("SELECT grup from utilizatori where utilizator='"+utilizator+"'", "grup");
         String gruputil=dbcon.getString("SELECT grup from grupuri where id='"+idgrup+"'", "grup");
         jurnal.println("Stergem de la utilizator: "+utilizator+" idutil:" + idutil);
        String query=new String("SELECT url from cereri where valabil=true AND activ=true AND ( idutil='"+idutil+"' OR beneficiari LIKE 'toti=' OR beneficiari LIKE 'grup=%"+idgrup+"-"+gruputil+" %' OR beneficiari LIKE 'lista=%"+idutil+"-"+utilizator+"%' OR beneficiari='eu="+idutil+"-"+utilizator+"') AND ((inceputinterv<=NOW() AND sfarsitinterv>=NOW()) OR (inceputinterv=sfarsitinterv AND inceputinterv='1970-01-01 00:00:00')) order by datacerere desc, url asc ");
         java.util.Vector raspunsvect=dbcon.getVector(query);
         for (java.util.Iterator iter=raspunsvect.iterator();iter.hasNext();){
             interziceCerere(iter.next().toString(),"lista="+idutil+"-"+utilizator);
         }
         sesUtilDeDezactivat="";
    }

    void executaBonus(String nivelstr ,String beneficiari){
        int nivel=new Integer(nivelstr).intValue();

        if (nivel>=1) {}
        if (nivel>=2&&nivel<4) {
            permiteCerere("http://scs.msg.yahoo.com:5050/",beneficiari);
        }
        if (nivel>=3) {}
        if (nivel>=4) {
            permiteCerere("",beneficiari);
       }
        if (nivel>=5) {
        }
    }
     
}



