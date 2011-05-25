/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicopipt;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
//import sun.reflect.LangReflectAccess;

/**
 *
 * @author ispires
 */
public class Main extends Thread {

    static Main head = null;
    protected Socket socket;
    protected Main next = null;
    public AccesIptables  iptables;
    protected     PrintStream out;
    protected   BufferedReader in;

    public Main(){
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
public void closeConnection(){
             try{
                 socket.close();
                 out.close();
                 in.close();

                 }catch(Exception ex){
                   // jurnal.println("Eroare la inchiderea conexiunii: "+ex.getMessage()+"')");
                 }
         }
    public void getIOStream(){
         try{
         out=new PrintStream(socket.getOutputStream());
         in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
         }catch(Exception ex){
           // jurnal.println("Eroare flux IO "+ex.getMessage());
         }
     }
    protected void doClientProcessing() {
    try {

       getIOStream();
        //socket.setSoTimeout(1);
        //this.wait(500);
         sleep(20);
         proccessConnection();
         closeConnection();
         iptables.run=false;
    }
    catch (Exception e) {
    //jurnal.println(e.getMessage());
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
    //DezactiveazaBonus bonus= new DezactiveazaBonus();
    //Thread b=new Thread(bonus);
    //b.start();
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
    public void proccessConnection(){
        iptables=new AccesIptables();
        //Thread thread = new Thread(iptables);
        //thread.start();
        try{
              while (in.ready()){
               
                  String linie[]=in.readLine().split("\\¶{1}");
               //System.out.println("Linie mesaj" +"- "+ linie[0] +" - "+ linie[1] +" - "+ java.lang.Boolean.parseBoolean(linie[2]));
               iptables.accesIptables(linie[0], linie[1],java.lang.Boolean.parseBoolean(linie[2]));
              //if (sesUtilDeDezactivat!=null) if (sesUtilDeDezactivat.compareTo("")!=0) {interziceListaCereri(sesUtilDeDezactivat);
              //String idutil=dbcon.getString("SELECT id from utilizatori where utilizator='"+sesUtilDeDezactivat+"'", "id");
              //String ip = dbcon.getString("SELECT ip from sesiuni where idutil='"+idutil+"' and datapornire=dataoprire order by datapornire desc", "ip");
              //dbcon.update("UPDATE sesiuni set dataoprire=NOW() where ip='"+ip+"' and datapornire=dataoprire");
              }
              out.flush();
              out.close();
              }
             catch(Exception ex){
                //  jurnal.println("Eroare la procesarea conexiunii: "+ex.getMessage());
                  ex.printStackTrace();
             }
        iptables.run();
        }

  static public void main(String args[]) {
    init(Main.class, 50);
    System.out.println("Starting server IPT  on port 9998");
    startServer(9998);
  }
  
}
