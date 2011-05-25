/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicopserv;

/**
 *
 * @author ispires
 */
public class DezactiveazaBonus implements Runnable{

        AccesDB dbcon = new AccesDB();
        AccesIptables  iptables=new AccesIptables();
        DacicopJurnal jurnal=new DacicopJurnal();
        public void dezactiveazaBonus(){
        String idbonus=new String("");
        int nivel=0;
        String data=new String();
        String beneficiari=new String("");
        String query=new String("SELECT id,nivel,data,beneficiari from bonus where expirat=true AND activ=true");
         
         java.util.Vector raspunsvect=dbcon.getVector(query);
         int nrvect=1;
         for (java.util.Iterator iter=raspunsvect.iterator();iter.hasNext();nrvect++){
            String next=iter.next().toString();
            if (nrvect==1) idbonus=next;
            if (nrvect==2) nivel=new Integer(next).intValue();
            if (nrvect==3) data=next;
            if (nrvect==4)
            {
                beneficiari=next;
                nrvect=0;
                if (nivel>=2&&nivel<4) {
                    if (beneficiari.startsWith("toti=")) {
                        java.util.Vector totivect=dbcon.getVector("SELECT ip from sesiuni where (datapornire<='"+data+"' AND datapornire=dataoprire) OR (datapornire<='"+data+"' AND dataoprire>='"+data+"' AND dataoprire<=(timestamp '"+data+"' + interval '15 minute'))");
                        for (java.util.Iterator iterbenef=totivect.iterator();iterbenef.hasNext();){
                        String ipses=iterbenef.next().toString();
                            iptables.accesIptables("http://scs.msg.yahoo.com:5050/", ipses+"-"+ipses, false);
                        }
                    }
                    if (beneficiari.startsWith("lista=")) {
                        if (!beneficiari.endsWith("="))
                        {
                        String beneficiaristr=beneficiari.split("=")[1];
                        String benefstr[]=beneficiaristr.split(",");
                        for (int i=0;i<benefstr.length;i++){
                            String id=benefstr[i].split("-")[0];
                            //java.util.Vector listavect =dbcon.getVector("SELECT ip from sesiuni where idutil='"+id+"' and (datapornire<='"+data+"' AND datapornire=dataoprire) OR (datapornire<='"+data+"' AND dataoprire>='"+data+"' AND dataoprire<=('"+data+"' + interval '15 minute')) order by datapornire desc");
                            String listavect=dbcon.getString("SELECT ip from sesiuni where idutil='"+id+"' and (datapornire<='"+data+"' AND datapornire=dataoprire) OR (datapornire<='"+data+"' AND dataoprire>='"+data+"' AND dataoprire<=(timestamp '"+data+"' + interval '15 minute')) order by datapornire desc","ip");
                            //for (java.util.Iterator iterbenef=listavect.iterator();iterbenef.hasNext();){
                                 //String ipses=iterbenef.next().toString();
                                 String ipses=listavect;
                                iptables.accesIptables("http://scs.msg.yahoo.com:5050/", ipses+"-"+ipses, false);
                            //}
                        }
                        }
                    }
                    if (beneficiari.startsWith("grup=")) {
                        String beneficiaristr=beneficiari.split("=")[1];
                        String idgrup=beneficiaristr.split("-")[0];
                        java.util.Vector listagrupvect=dbcon.getVector("SELECT sesiuni.ip from sesiuni JOIN utilizatori on utilizatori.id=sesiuni.idutil where utilizatori.grup='"+idgrup+"' and (sesiuni.datapornire<='"+data+"' AND sesiuni.datapornire=sesiuni.dataoprire) OR (sesiuni.datapornire<='"+data+"' AND sesiuni.dataoprire>='"+data+"' AND sesiuni.dataoprire<=(timestamp '"+data+"' + interval '15 minute'))");
                        for (java.util.Iterator iterbenef=listagrupvect.iterator();iterbenef.hasNext();){
                                String ipses=iterbenef.next().toString();
                                iptables.accesIptables("http://scs.msg.yahoo.com:5050/", ipses+"-"+ipses, false);
                            }
                    }
                }
                if (nivel>=4) {
                    if (beneficiari.startsWith("toti=")) {
                        java.util.Vector totivect=dbcon.getVector("SELECT ip from sesiuni where (datapornire<='"+data+"' AND datapornire=dataoprire) OR (datapornire<='"+data+"' AND dataoprire>='"+data+"' AND dataoprire<=(timestamp '"+data+"' + interval '15 minute'))");
                        for (java.util.Iterator iterbenef=totivect.iterator();iterbenef.hasNext();){
                        String ipses=iterbenef.next().toString();
                            iptables.accesIptables("", ipses+"-"+ipses, false);
                        }
                    }
                    if (beneficiari.startsWith("lista=")) {

                        if (!beneficiari.endsWith("="))
                        {
                        String beneficiaristr=beneficiari.split("=")[1];
                        String benefstr[]=beneficiaristr.split(",");

                        for (int i=0;i<benefstr.length;i++){
                            String id=benefstr[i].split("-")[0];
                            //java.util.Vector listavect =dbcon.getVector("SELECT ip from sesiuni where idutil='"+id+"' and (datapornire<='"+data+"' AND datapornire=dataoprire) OR (datapornire<='"+data+"' AND dataoprire>='"+data+"' AND dataoprire<=('"+data+"' + interval '15 minute'))");
                            String listavect=dbcon.getString("SELECT ip from sesiuni where idutil='"+id+"' and (datapornire<='"+data+"' AND datapornire=dataoprire) OR (datapornire<='"+data+"' AND dataoprire>='"+data+"' AND dataoprire<=(timestamp '"+data+"' + interval '15 minute')) order by datapornire desc","ip");
                            //for (java.util.Iterator iterbenef=listavect.iterator();iterbenef.hasNext();){
                                 //String ipses=iterbenef.next().toString();
                                 String ipses=listavect;
                                iptables.accesIptables("", ipses+"-"+ipses, false);
                            //}
                        }
                        }
                    }
                    if (beneficiari.startsWith("grup=")) {
                        String beneficiaristr=beneficiari.split("=")[1];
                        String idgrup=beneficiaristr.split("-")[0];
                        java.util.Vector listagrupvect=dbcon.getVector("SELECT sesiuni.ip from sesiuni JOIN utilizatori on utilizatori.id=sesiuni.idutil where utilizatori.grup='"+idgrup+"' and (sesiuni.datapornire<='"+data+"' AND sesiuni.datapornire=sesiuni.dataoprire) OR (sesiuni.datapornire<='"+data+"' AND sesiuni.dataoprire>='"+data+"' AND sesiuni.dataoprire<=(timestamp '"+data+"' + interval '15 minute'))");
                        for (java.util.Iterator iterbenef=listagrupvect.iterator();iterbenef.hasNext();){
                                String ipses=iterbenef.next().toString();
                                iptables.accesIptables("", ipses+"-"+ipses, false);
                            }
                    }
                }
                dbcon.update("UPDATE bonus set activ=false where id='"+idbonus+"'");
            }
         }
        };

         public DezactiveazaBonus(){
         jurnal.println("Dezactiveaza bonus din 5 in 5 secunde");
         }
             public void run(){
                 try{
                     while(true){
                                Thread.sleep(5000);
                                dbcon.update("UPDATE bonus set expirat=true where (data-NOW()) <= interval '0 secs'");
                                dezactiveazaBonus();
                                dezactiveazaSesiuniNeinchise();
                                //System.out.println("Gata " + System.currentTimeMillis());
                     }

                 }catch(Exception ex){
                     jurnal.println("Server "+ex.getMessage());
                     ex.printStackTrace();
                 }
          }

     public void dezactiveazaSesiuniNeinchise(){
     int nrelem=0;
     if (Main.sesiuniVector==null) Main.sesiuniVector=new java.util.Vector();
     if (!Main.sesiuniVector.isEmpty())
     for (java.util.Iterator iter=Main.sesiuniVector.iterator();iter.hasNext();nrelem++){
                 Object[] element=(Object[])iter.next();
                 //System.out.println("Sesiuni: " + element[0] + " - " + element[1] +" - "+ element[2] + " - " + element[3]);
                 if (dbcon.getDouble("select EXTRACT(SECONDS from NOW()-timestamp '"+element[3]+"') as secs","secs")>=5) {

                 if (element[1].toString().compareTo("neconectat")!=0){
                     String idutil=dbcon.getString("SELECT idutil from sesiuni where ip='"+element[0]+"' and datapornire=dataoprire order by datapornire desc", "idutil");
                     String utilizator=dbcon.getString("SELECT utilizator from utilizatori where id='"+idutil+"'", "utilizator");
                     int sesiune=dbcon.getInteger("SELECT id from sesiuni where ip='"+element[0]+"' and datapornire=dataoprire order by datapornire desc","id");
                     jurnal.println("ID-ul sesiunii fara deconectare este: " + sesiune );
                     if(!utilizator.isEmpty()&&sesiune!=0){
                        Main.sesUtilDeDezactivat=utilizator;
                     }
                  }
                 iter.remove();
                }
     }
    }
}