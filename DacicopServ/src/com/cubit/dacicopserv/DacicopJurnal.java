/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicopserv;

/**
 *
 * @author ispires
 */
public class DacicopJurnal {
    AccesDB dbcon = new AccesDB();
    public DacicopJurnal()
    {
    }
    public void println(String mesaj){
        dbcon.update("INSERT into jurnal(jurnal) VALUES('"+mesaj+"')");
    }

}
