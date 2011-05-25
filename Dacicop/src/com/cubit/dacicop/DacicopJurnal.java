/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicop;

/**
 *
 * @author ispires
 */
public class DacicopJurnal {

    public DacicopJurnal()
    {
    }
    static public void println(String mesaj){
        DacicopClient.trimite("jurnalnou"+"¶"+"jurnal"+"¶"+"jurnal"+"¶"+mesaj);
    }

}
