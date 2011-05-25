/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicopserv;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author ispires
 */
public class AccesIptables {

    DacicopJurnal jurnal=new DacicopJurnal();
    public AccesIptables(){
    }

    public void accesIptables(String adresa ,String ip, boolean activare){
       //jurnal.println("!!!!!!!!!!! Activam iptables adresa: " + adresa);
       String iptexec =new String();

       try {
        //jurnal.println(new java.net.URL("http://java.sun.com/index.html").getHost());
        java.net.URL url=null;
        String host="";
        int port=80;
        if (adresa.compareTo("")!=0) { url = new java.net.URL(adresa);
        host=url.getHost();
        port=url.getPort();
        if(url.getProtocol().startsWith("https")&&port==-1) port=443;
        }
        jurnal.println("Host, port si ip sunt : "+ host + " si " + ((port==-1)?80:port) + " si " + ip);
        if (activare) {
            iptexec=new String("/usr/sbin/iptables -t nat -A POSTROUTING -o eth1 -p tcp "+ ((adresa.compareTo("")==0)?"":"--dport " + ((port==-1)?80:port) + " -d " + host) + (ip.compareTo("")==0?"":" -m iprange --src-range " + ip ) + " -j MASQUERADE");
            Runtime.getRuntime().exec(iptexec);
            //jurnal.println("Executie inserare iptables:" + iptexec);
        }
        else{
            if (adresa.compareTo("")!=0){
            String procstr=null;
            Process proces=Runtime.getRuntime().exec("dig +short " + host);
            BufferedReader inproc=new BufferedReader(new InputStreamReader(proces.getInputStream()));
            while(((procstr = inproc.readLine()) != null)){
                iptexec = new String("/usr/sbin/iptables -t nat -D POSTROUTING -o eth1 -p tcp "+ ((adresa.compareTo("")==0)?"":"--dport " + ((port==-1)?80:port) + " -d " + procstr) + (ip.compareTo("")==0?"":" -m iprange --src-range " + ip ) + " -j MASQUERADE");
                Runtime.getRuntime().exec(iptexec);
                //jurnal.println("Executie stergere iptables:" + iptexec);
                //jurnal.println(procstr);
            }
            }
            else {
                iptexec = new String("/usr/sbin/iptables -t nat -D POSTROUTING -o eth1 -p tcp "+ (ip.compareTo("")==0?"":" -m iprange --src-range " + ip) + " -j MASQUERADE");
                Runtime.getRuntime().exec(iptexec);
                jurnal.println("Executie stergere iptables:" + iptexec);
            }
        }
       }
        catch (java.io.IOException e){ jurnal.println("Problema executie iptables: " + e.getMessage());}
        //catch (java.net.MalformedURLException u){ jurnal.println("Problema URL: " + u.getMessage());}
    }

}
