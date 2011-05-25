/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicop;

/**
 *
 * @author ispires
 */
public class DacicopBonus {
    int nivel=0;
    String nivelstr=new String();
    public DacicopBonus()
    {
        //nivel=new Integer(DacicopClient.trimite("verificabonus"+"¶"+DacicopApp.dacicop.utilizator.utilizator+"¶"+DacicopApp.dacicop.utilizator.parolaDB5)).intValue();
        nivelstr=new String(DacicopClient.trimite("verificabonus"+"¶"+"neconectat"+"¶"+"neconectat"));
        if(!nivelstr.isEmpty()) nivel=new Integer(nivelstr).intValue();
        else nivel=0;
    }
    public int verificaBonus(){
        nivelstr=DacicopClient.trimite("verificabonus"+"¶"+DacicopApp.dacicop.utilizator.utilizator+"¶"+DacicopApp.dacicop.utilizator.parolaDB5);
        if(!nivelstr.isEmpty()) nivel=new Integer(nivelstr).intValue();
        else nivel=0;
    //System.out.println("Bonus este la nivel: " +  nivel);
        return nivel;
    }
}
