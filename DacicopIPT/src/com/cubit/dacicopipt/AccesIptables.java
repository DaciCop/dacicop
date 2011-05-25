/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicopipt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import sun.reflect.LangReflectAccess;

/**
 *
 * @author ispires
 */
public class AccesIptables  {

    java.util.Vector listaIPtables = new java.util.Vector();
    boolean run=true;
    
    public AccesIptables(){
    }

    public void run  () {
    //    while (run){
        try {
            Thread.sleep(100);
            synchronized (listaIPtables)  {
            for (java.util.Iterator it=listaIPtables.iterator();it.hasNext();)
        {
        try {
            String iptablescmd=it.next().toString();
            Process ipt=Runtime.getRuntime().exec(iptablescmd);
            //ipt.getInputStream().close();
            //ipt.getOutputStream().close();
             try{
                    ipt.waitFor();
                    ipt.destroy();
                }
            catch (java.lang.InterruptedException e) {}
        }
        catch (java.io.IOException e) { }
        it.remove();
        }
        }
        }catch (java.lang.InterruptedException e) {}
      //  run=false;
      //  }
    }

    public void accesIptables(String adresa ,String ip, boolean activare){
       String iptexec =new String();

       try {
        java.net.URL url=null;
        String host="";
        int port=80;
        if (adresa.compareTo("")!=0) { url = new java.net.URL(adresa);
        host=url.getHost();
        port=url.getPort();
        if(url.getProtocol().startsWith("https")&&port==-1) port=443;
        }
        //jurnal.println("Host, port si ip sunt : "+ host + " si " + ((port==-1)?80:port) + " si " + ip);
        if (activare) {
            iptexec=new String("/usr/sbin/iptables -t nat -A POSTROUTING -o eth1 -p tcp "+ ((adresa.compareTo("")==0)?"":"--dport " + ((port==-1)?80:port) + " -d " + host) + (ip.compareTo("")==0?"":" -m iprange --src-range " + ip ) + " -j MASQUERADE");
            synchronized (listaIPtables) {listaIPtables.add(iptexec);}
        }
        else{
            if (adresa.compareTo("")!=0){
            String procstr=null;
            Process proces=Runtime.getRuntime().exec("dig +short " + host);
            try{
                    proces.waitFor();
            }
            catch (java.lang.InterruptedException e) {}
            BufferedReader inproc=new BufferedReader(new InputStreamReader(proces.getInputStream()));
            while(((procstr = inproc.readLine()) != null)){
                iptexec = new String("/usr/sbin/iptables -t nat -D POSTROUTING -o eth1 -p tcp "+ ((adresa.compareTo("")==0)?"":"--dport " + ((port==-1)?80:port) + " -d " + procstr) + (ip.compareTo("")==0?"":" -m iprange --src-range " + ip ) + " -j MASQUERADE");
                synchronized (listaIPtables) {listaIPtables.add(iptexec);}
            }
            inproc.close();
            proces.destroy();
            }
            else {
                iptexec = new String("/usr/sbin/iptables -t nat -D POSTROUTING -o eth1 -p tcp "+ (ip.compareTo("")==0?"":" -m iprange --src-range " + ip) + " -j MASQUERADE");
                synchronized (listaIPtables) {listaIPtables.add(iptexec);}
             //   jurnal.println("Executie stergere iptables:" + iptexec);
            }
        }
       }
    catch (java.io.IOException e){
     //   Main.jurnal.println("Problema executie iptables: " + e.getMessage());
    }
    }
}
