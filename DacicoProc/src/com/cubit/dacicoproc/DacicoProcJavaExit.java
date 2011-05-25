/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicoproc;

import java.net.InetAddress;

/**
 *
 * @author ispires
 */
public class DacicoProcJavaExit extends Thread{
//private DacicopView dacicop=null;
public void  run(){
         try{
            InetAddress address = null;
             address=InetAddress.getLocalHost();
             String ip=address.getHostAddress();
             DacicoProcJurnal.println("Inchidere fara DacicoProc la Exit de la IP:" + ip );
            }
              catch (java.net.UnknownHostException e) {DacicoProcJurnal.println(e.getMessage());}         catch(Exception ex){
         DacicoProcJurnal.println("Eroare: "+ex.getMessage());
     }
 }
 public DacicoProcJavaExit(){
 }
}