/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicopserv;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ispires
 */
public class AccesDB {
    
    private String user=new String("dacicop");
	private String pass=new String("pocicad");
	//Connection con=null;
	public AccesDB(){
	}

	public java.util.Vector getVector(String query){
		Statement stmt=null;
		java.util.Vector result=new java.util.Vector();
		ResultSet rst=null;
		Connection con=null;
        try {

        Class.forName("org.postgresql.Driver").newInstance();
		con=DriverManager.getConnection("jdbc:postgresql:dacicop",user,pass);

        stmt=con.createStatement();
		rst=stmt.executeQuery(query);
		int columnCount=rst.getMetaData().getColumnCount();
//		result.add(new java.lang.Integer(columnCount));
//		System.out.println(query);
		for (;rst.next();) for(int i=1;i<=columnCount;i++) result.add(rst.getObject(i));
		//stmt.close();
		//rst.close();
		} 
		catch (NullPointerException nl) { System.out.println("NullPointerException");  } // trimite e-mail cu probleme

        catch (SQLException esql) {System.out.println("probleme DB SQL " + esql.getMessage());} //probleme DB
		catch (IllegalAccessException goaway) {System.out.println("probleme logare DB "+ goaway.getMessage());} //probleme logare DB
		catch (InstantiationException ex) {System.out.println("Instantiation exception " + ex.getMessage());} //Instantiation exception
		catch (ClassNotFoundException clsnf) {System.out.println("Nu gasim driver Mysql " + clsnf.getMessage());} //Nu gasim driver Mysql
        finally {
            try { rst.close(); } catch(Exception e) { }
            try { stmt.close(); } catch(Exception e) { }
            try { con.close(); } catch(Exception e) { }
        }

        return result;
	}
	public String getString(String query,String field){
		Statement stmt=null;
		String result=new String ();
        Connection con=null;

		ResultSet rst=null;
		try {
        Class.forName("org.postgresql.Driver").newInstance();
		con=DriverManager.getConnection("jdbc:postgresql:dacicop",user,pass);

        stmt=con.createStatement();
		rst=stmt.executeQuery(query);
//		System.out.println(query);
		if (rst.next()) result=rst.getString(field);
		//stmt.close();
		//rst.close();
		} 
		catch (NullPointerException nl) { System.out.println("NullPointerException");  } // trimite e-mail cu probleme
 		catch (SQLException esql) {System.out.println("probleme DB SQL " + esql.getMessage());} //probleme DB
		catch (IllegalAccessException goaway) {System.out.println("probleme logare DB "+ goaway.getMessage());} //probleme logare DB
		catch (InstantiationException ex) {System.out.println("Instantiation exception " + ex.getMessage());} //Instantiation exception
		catch (ClassNotFoundException clsnf) {System.out.println("Nu gasim driver Mysql " + clsnf.getMessage());} //Nu gasim driver Mysql
        finally {
            try { rst.close(); } catch(Exception e) { }
            try { stmt.close(); } catch(Exception e) { }
            try { con.close(); } catch(Exception e) { }
        }
        return result;

	}
    public java.sql.Timestamp getTimestamp(String query,String field){
		Statement stmt=null;
		java.sql.Timestamp result=null;
        Connection con=null;
		ResultSet rst=null;
		try {
        Class.forName("org.postgresql.Driver").newInstance();
		con=DriverManager.getConnection("jdbc:postgresql:dacicop",user,pass);

        stmt=con.createStatement();
		rst=stmt.executeQuery(query);
//		System.out.println(query);
		if (rst.next()) result=rst.getTimestamp(field);
		//stmt.close();
		//rst.close();
		} 

        catch (NullPointerException nl) { System.out.println("NullPointerException");  } // trimite e-mail cu probleme
 		catch (SQLException esql) {System.out.println("probleme DB SQL " + esql.getMessage());} //probleme DB
		catch (IllegalAccessException goaway) {System.out.println("probleme logare DB "+ goaway.getMessage());} //probleme logare DB
		catch (InstantiationException ex) {System.out.println("Instantiation exception " + ex.getMessage());} //Instantiation exception
		catch (ClassNotFoundException clsnf) {System.out.println("Nu gasim driver Mysql " + clsnf.getMessage());} //Nu gasim driver Mysql
        finally {
            try { rst.close(); } catch(Exception e) { }
            try { stmt.close(); } catch(Exception e) { }
            try { con.close(); } catch(Exception e) { }
        }
        return result;
	}
	public double getDouble(String query, String field){
		Statement stmt=null;
//		String result=new String ();
		ResultSet rst=null;
        Connection con=null;
		double result=0;
		try {
        Class.forName("org.postgresql.Driver").newInstance();
		con=DriverManager.getConnection("jdbc:postgresql:dacicop",user,pass);

            stmt=con.createStatement();
		rst=stmt.executeQuery(query);
//		System.out.println(query);
		if (rst.next()) result=rst.getDouble(field);
		//stmt.close();
		//rst.close();
		} 
		catch (NullPointerException nl) { System.out.println("NullPointerException");  } // trimite e-mail cu probleme
 		catch (SQLException esql) {System.out.println("probleme DB SQL " + esql.getMessage());} //probleme DB
		catch (IllegalAccessException goaway) {System.out.println("probleme logare DB "+ goaway.getMessage());} //probleme logare DB
		catch (InstantiationException ex) {System.out.println("Instantiation exception " + ex.getMessage());} //Instantiation exception
		catch (ClassNotFoundException clsnf) {System.out.println("Nu gasim driver Mysql " + clsnf.getMessage());} //Nu gasim driver Mysql
        finally {
            try { rst.close(); } catch(Exception e) { }
            try { stmt.close(); } catch(Exception e) { }
            try { con.close(); } catch(Exception e) { }
        }
        return result;
	}
	public int getInteger(String query,String field){
		Statement stmt=null;
		int result=0;
		ResultSet rst=null;
        Connection con=null;
        try {
        Class.forName("org.postgresql.Driver").newInstance();
		con=DriverManager.getConnection("jdbc:postgresql:dacicop",user,pass);

            stmt=con.createStatement();
		rst=stmt.executeQuery(query);
//		System.out.println(query);
		if (rst.next()) result=rst.getInt(field);
		//stmt.close();
		//rst.close();
		} 
		catch (NullPointerException nl) { System.out.println("NullPointerException");  } // trimite e-mail cu probleme
 		catch (SQLException esql) {System.out.println("probleme DB SQL " + esql.getMessage());} //probleme DB
		catch (IllegalAccessException goaway) {System.out.println("probleme logare DB "+ goaway.getMessage());} //probleme logare DB
		catch (InstantiationException ex) {System.out.println("Instantiation exception " + ex.getMessage());} //Instantiation exception
		catch (ClassNotFoundException clsnf) {System.out.println("Nu gasim driver Mysql " + clsnf.getMessage());} //Nu gasim driver Mysql
        finally {
            try { rst.close(); } catch(Exception e) { }
            try { stmt.close(); } catch(Exception e) { }
            try { con.close(); } catch(Exception e) { }
        }
        return result;
	}

	public int update(String query){
		Statement stmt=null;
        Connection con=null;
		int result=0;
		try {
        Class.forName("org.postgresql.Driver").newInstance();
		con=DriverManager.getConnection("jdbc:postgresql:dacicop",user,pass);

        stmt=con.createStatement();
		result=stmt.executeUpdate(query);
		//stmt.close();
		} 
		catch (NullPointerException nl) { System.out.println("NullPointerException");  } // trimite e-mail cu probleme
 		catch (SQLException esql) {System.out.println("probleme DB SQL " + esql.getMessage());} //probleme DB
		catch (IllegalAccessException goaway) {System.out.println("probleme logare DB "+ goaway.getMessage());} //probleme logare DB
		catch (InstantiationException ex) {System.out.println("Instantiation exception " + ex.getMessage());} //Instantiation exception
		catch (ClassNotFoundException clsnf) {System.out.println("Nu gasim driver Mysql " + clsnf.getMessage());} //Nu gasim driver Mysql
        finally {
            try { stmt.close(); } catch(Exception e) { }
            try { con.close(); } catch(Exception e) { }
        }
        return result;
	}
}
