/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dacicopuser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ispires
 */
public class Main implements Runnable{
    static File file=new File("C:\\sys\\user.txt");
    public void run(){
        System.setSecurityManager(new ExitSecurityManager());
        try {
        while (true) {
        Thread.sleep(1000);
        }
        }
        catch (java.lang.InterruptedException e) {}
    }
        public class ExitSecurityManager extends SecurityManager {
  public static final int PERMIT_EXIT = 333;

  @Override
  public void checkExit(int status) {
    if (status != PERMIT_EXIT) {
      //Startup.logWrite("the application with tid " + Startup.getFocusTid() +
      //                 " called the illegal instruction: System.exit(0) ");
      //Startup.stopThread(Startup.getFocusTid());

        if (status==0) {
        file.delete();
        }
     }
  }
        @Override
     public void checkPermission(java.security.Permission perm) {}

        @Override
    public void checkPermission(java.security.Permission perm, Object context) {}
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        file.deleteOnExit();
        FileWriter filewr=new FileWriter(file);
        filewr.write(System.getProperty("user.name")+"\r\n");
        filewr.flush();
        filewr.close();
        
        Main server=new Main();
        Thread thread=new Thread(server);
        System.out.println("Porneste DacicopUser");
        thread.start();
    }

}
