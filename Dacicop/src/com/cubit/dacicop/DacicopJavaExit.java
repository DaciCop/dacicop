/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicop;

/**
 *
 * @author ispires
 */
public class DacicopJavaExit extends Thread{
//private DacicopView dacicop=null;
DacicopUtilizatori utilizator=null;
public void  run(){
         try{
                if (utilizator.conectat) {
                    utilizator.deconectare();
                    DacicopJurnal.println("Inchidere fara deconectare utilizator: "+utilizator.utilizator+" de la IP:" + utilizator.ip );
                }
         }catch(Exception ex){
         DacicopJurnal.println("Eroare: "+ex.getMessage());
     }
 }
 public DacicopJavaExit(DacicopUtilizatori utilizator){
     this.utilizator=utilizator;
 }
}