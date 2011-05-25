/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cubit.dacicoproc;

/**
 *
 * @author ispires
 */
public class DacicoProcClient {

      final static  String    serverIPname = "192.168.35.1";     // 192.168.35.1 server IP name
      //final static  String    serverIPname = "127.0.0.1";     // 192.168.35.1 server IP name
      final static  int       serverPort   = 9999;                      // server port number
      private static String ascii = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz*&");

    public static String tichetgen(){
//		info = "|||"+host+"|||"+useragent+"|||"+ip+"|||"+user+"|||"+pass+"|||"+data+"|||"+sessionid+"|||"+cookie+"|||"+numefis+"|||";
		ascii = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz*&";
	return ascii;
        }
	public static String gen(String degen)
	{
		String tichet=new String("INCEP");
		String nr64str=new String();
		for (int i=0; i<degen.length();i++)
		{
			int nr64 =degen.charAt(i)/64;
			String stri=Integer.toString(i);

			if (nr64!=1) nr64str=nr64str+nr64+"0000000000".substring(0,10-stri.length())+i;
			//tichet = tichet+ascii.charAt(degen.charAt(i)%64);
            tichet = tichet+ascii.charAt(degen.charAt(i)%64);
		}
//		System.out.println(nr64str);
		return nr64str+tichet;
	}
//	ascii codes  48-57 numere 0123456789
//				65-90 litere mari ABCDEFGHIJKLMNOPQRSTUVWXYZ
//				97-122 litere mici 	abcdefghijklmnopqrstuvwxyz
/*
		 // Encode a String into bytes
		 String inputString = "blahblahblah??";
		 byte[] input = inputString.getBytes("UTF-8");

		 // Compress the bytes
		 byte[] output = new byte[100];
		 Deflater compresser = new Deflater();
		 compresser.setInput(input);
		 compresser.finish();
		 int compressedDataLength = compresser.deflate(output);

		 // Decompress the bytes
		 Inflater decompresser = new Inflater();
		 decompresser.setInput(output, 0, compressedDataLength);
		 byte[] result = new byte[100];
		 int resultLength = decompresser.inflate(result);
		 decompresser.end();

		 // Decode the bytes into a String
		 String outputString = new String(result, 0, resultLength, "UTF-8");
*/


	public static String ungen(String tichet)
	{
		String tic= new String();
		String cap= new String();
			if (tichet.indexOf("INCEP")!=0) cap=tichet.substring(0, tichet.indexOf("INCEP"));
		String coada=new String(tichet.substring(tichet.indexOf("INCEP")+5));
		//System.out.println(coada);
		int j=0;
		for(int a=0;a<coada.length();a++)
		{
			int inmul=1,poz=-1;
			if (cap.length()!=0&&(j*11)!=cap.length()) {
				poz=Integer.parseInt(cap.substring(j*11+1, (j+1)*11));
				}
			//System.out.println(poz);
			if (poz==a) {
				inmul=Integer.parseInt(String.valueOf(cap.charAt(j*11)));
				if ((j*11)!=cap.length()) j++;
			}
			tic=tic + (char)(64*inmul+ascii.indexOf(coada.charAt(a)));
			//System.out.println(tic);
		}
//		System.out.println(tic);
		return tic;
	}



public static String trimite(String detrimis) {

      java.net.Socket         sock = null;                              // Socket object for communicating
      java.io.PrintWriter     pw   = null;                              // socket output to server
      java.io.BufferedReader  br   = null;                              // socket input from server
      String answer=new String("");
      try {

            sock = new java.net.Socket(serverIPname,serverPort);       // create socket and connect
            pw   = new java.io.PrintWriter(sock.getOutputStream(), true);  // create reader and writer
            br   = new java.io.BufferedReader(new java.io.InputStreamReader(sock.getInputStream()));
            //System.out.println("Connected to Server");

            pw.println(gen(detrimis));                      // send msg to the server
            //System.out.println("Sent message to server");
            String ans=br.readLine();
            answer = ungen(ans);                              // get data from the server
            //System.out.println("Response from the server neprelucrat >" + ans);
            //System.out.println("Response from the server >" + answer);
            pw.flush();
            pw.close();                                                 // close everything
            //br.close();
            //sock.close();
      } catch (Throwable e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
      }
      return answer;
    }
}