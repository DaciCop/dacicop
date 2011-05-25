/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicop;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

 /**
 *
 * @author ispires
 */
public class DacicopUtilizatori {

    String utilizator = new String("neconectat");
    String parola = new String("neconectat");
    String rol= new String("utilizator");
    String grup= new String("");
    String idgrup=new String("");
    String id= new String("");
    String parolaDB5=new String("neconectat");
    String ip=new String("0.0.0.0");


    boolean conectat=false;
    DacicopClient client=new DacicopClient();
    byte[] hash=null;
    InetAddress address =null;

    DacicopUtilizatori(String utilizator,String parola){
    this.utilizator=utilizator;
    this.parola=parola;
    this.parolaDB5="neconectat";
    try{
    address =InetAddress.getLocalHost();
    ip=address.getHostAddress();
    }
    catch (UnknownHostException e) {
            e.printStackTrace();}

    }

    boolean conectare(){

    java.security.MessageDigest digest = null;
    java.lang.StringBuffer macstrbuff = new java.lang.StringBuffer();
    
    //String ip = new String("0.0.0.0");
    try{
        digest=java.security.MessageDigest.getInstance("MD5");
    }
    catch(java.security.NoSuchAlgorithmException e){DacicopJurnal.println(e.getMessage());}
    digest.update(parola.getBytes());
    hash = digest.digest();
    try{
    
    
    //DacicopJurnal.println(ip);
    //java.util.Enumeration nienum = NetworkInterface.getNetworkInterfaces();
    //for (int i=0;nienum.hasMoreElements();i++){
    //    DacicopJurnal.println(((NetworkInterface)nienum.nextElement()).getName());
    //        }
    NetworkInterface ni = null;
      if (System.getProperty("os.name").startsWith("Windows")) ni=NetworkInterface.getByInetAddress(address);
      else ni=NetworkInterface.getByName("eth0");
    byte[] mac = ni.getHardwareAddress();

    //DacicopJurnal.println("Adressaaaa macccc mac " + mac.length);
    for (int i = 0; i < mac.length; i++) {
        macstrbuff.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
    }

    java.lang.StringBuffer hashstrbuff = new java.lang.StringBuffer();

    for (int i = 0; i < hash.length; i++) {
        hashstrbuff.append(String.format("%02X%s", hash[i], (i < hash.length - 1) ? "" : ""));
    }
    parolaDB5=hashstrbuff.toString();
    String rezultcon=client.trimite("conectare"+"¶"+utilizator+"¶"+ parolaDB5 +"¶"+ macstrbuff.toString() +"¶"+ip);
    String[] mesaje = rezultcon.split("\\¶{1}");
    if (mesaje[1].compareTo(parolaDB5)==0) {
        conectat=true;
        id=mesaje[0];
        grup=mesaje[3];
        idgrup=mesaje[4];
        rol=mesaje[2];
    } else conectat=false;

    }
    catch (java.net.SocketException e){}
    return conectat;
    }
    void deconectare(){
    java.security.MessageDigest digest = null;
    java.lang.StringBuffer macstrbuff = new java.lang.StringBuffer();
    //String ip = new String("0.0.0.0");
        try{
        digest=java.security.MessageDigest.getInstance("MD5");
    }
    catch(java.security.NoSuchAlgorithmException e){DacicopJurnal.println(e.getMessage());}
    //DacicopJurnal.println("Parrrrrroooola este : " + parola);
    digest.update(parola.getBytes());
    hash = digest.digest();
    try{

    NetworkInterface ni = null;
      if (System.getProperty("os.name").startsWith("Windows")) ni=NetworkInterface.getByInetAddress(address);
      else ni=NetworkInterface.getByName("eth0");
    byte[] mac = ni.getHardwareAddress();

    for (int i = 0; i < mac.length; i++) {
        macstrbuff.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
    }

    java.lang.StringBuffer hashstrbuff = new java.lang.StringBuffer();

    for (int i = 0; i < hash.length; i++) {
        hashstrbuff.append(String.format("%02X%s", hash[i], (i < hash.length - 1) ? "" : ""));
    }
    parolaDB5=hashstrbuff.toString();
    String raspuns=client.trimite("deconectare"+"¶"+utilizator+"¶"+ parolaDB5 +"¶"+macstrbuff.toString()+"¶"+ip);
    //DacicopJurnal.println("Raspuns deconectare 111 "+ raspuns + " si parola " + parolaDB5);
    if (raspuns.compareTo(parolaDB5)==0)
    {
        //DacicopJurnal.println("Deconectare  dddd");
        conectat=false;
        parolaDB5="neconectat";
        utilizator="neconectat";
        parola="neconectat";
    }

    }
    catch (java.net.SocketException e){}
    };
    String verifrol(String utilizator){
    return client.trimite("verifrol"+"¶"+utilizator);
    }
}
