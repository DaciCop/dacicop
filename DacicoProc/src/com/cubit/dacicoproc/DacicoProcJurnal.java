/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicoproc;

/**
 *
 * @author ispires
 */
public class DacicoProcJurnal {

    public DacicoProcJurnal()
    {
    }
    static public void println(String mesaj){
        DacicoProcClient.trimite("jurnalnou"+"¶"+"jurnal"+"¶"+"jurnal"+"¶"+mesaj);
    }

}
