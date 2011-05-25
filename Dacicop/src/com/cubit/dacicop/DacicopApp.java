/*
 * DacicopApp.java
 */

package com.cubit.dacicop;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.zip.ZipException;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class DacicopApp extends SingleFrameApplication {
    
    static DacicopView dacicop =null;
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup()  {
        System.setSecurityManager(new ExitSecurityManager());
        try {
            dacicop=new DacicopView(this);

            DacicopAllowApp s=new DacicopAllowApp();
            Thread serv=new Thread(s);
            serv.setDaemon(true);
            serv.start();
        }
        catch (java.net.MalformedURLException e) { 

            DacicopJurnal.println(e.getMessage());
        }
        catch (ZipException e) {System.out.println("Eroare ZipException" + e.getMessage());}
        catch (IOException e) {
        java.io.OutputStream out=new java.io.OutputStream()
            {
                private StringBuilder string = new StringBuilder();
                @Override
                public void write(int b) throws IOException {
                    this.string.append((char) b );
                }

                //Netbeans IDE automatically overrides this toString()
                public String toString(){
                    return this.string.toString();
                }
            };
         java.io.PrintStream pw=new java.io.PrintStream(out);
         e.printStackTrace(pw);

        System.out.println("Eroare IOException " + out.toString());
        }
        catch (URISyntaxException e) {System.out.println("Eroare URISyntaxException" + e.getMessage());}
        show(dacicop);
        //dacicop.setVis(false)
    }
    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DacicopApp
     */
    public static DacicopApp getApplication() {
        return Application.getInstance(DacicopApp.class);
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
        if (dacicop.utilizator.conectat) dacicop.deconectareUtil();
        }
        DacicopJurnal.println(
                "IP: "+dacicop.utilizator.ip+" utilizator: "+dacicop.utilizator.utilizator+" - YOU ARE NOT ALLOWED TO CALL SYSTEM.EXIT(0) FROM YOUR APPLICATION!" );
         throw new SecurityException();
         }
     }

        @Override
     public void checkPermission(java.security.Permission perm) {}

        @Override
    public void checkPermission(java.security.Permission perm, Object context) {}
    }

    /**
     * Main method launching the application.
     */
    public static void main(final String[] args) throws java.net.MalformedURLException {
        
        final TrayIcon trayIcon;
        
        final Image image = Toolkit.getDefaultToolkit().getImage(new java.net.URL("http://cmcsinaia.ro/dacicop/sigla.jpeg"));
        //final Image image = Toolkit.getDefaultToolkit().getImage("resources/sigla.jpg");
        launch(DacicopApp.class, args);

        Runtime.getRuntime().addShutdownHook(new Thread(){
    //private DacicopView dacicop=null;
    public void  run(){
             try{
                 InetAddress address = null;
                 address=InetAddress.getLocalHost();
                 String ip=address.getHostAddress();
                 if (dacicop.utilizator.conectat) {
                    dacicop.deconectareUtil();
                 DacicopClient.trimite("jurnalnou"+"¶"+"jurnal"+"¶"+"jurnal"+"¶"+"Inchidere Dacicop la Exit fara deconectare de la IP:" + ip );
                 }
             }
                  catch (java.net.UnknownHostException e) {System.out.println(e.getMessage());}         catch(Exception ex){
             System.out.println("Eroare: "+ex.getMessage());
         }

     }

    });
    if (SystemTray.isSupported()) {

    SystemTray tray = SystemTray.getSystemTray();
    
    MouseListener mouseListener = new MouseListener() {
                
        public void mouseClicked(MouseEvent e) {
            //DacicopJurnal.println("Tray Icon - Mouse clicked!");
            //DacicopJurnal.println("visible ? :"+DacicopApp.getApplication().getMainView().getFrame().isVisible());
            
            if (dacicop.getFrame().isVisible()) dacicop.setVis(false);
            else dacicop.setVis(true);
        }

        public void mouseEntered(MouseEvent e) {
            //DacicopJurnal.println("Tray Icon - Mouse entered!");
        }

        public void mouseExited(MouseEvent e) {
            //DacicopJurnal.println("Tray Icon - Mouse exited!");
        }

        public void mousePressed(MouseEvent e) {
            //DacicopJurnal.println("Tray Icon - Mouse pressed!");
           
        }

        public void mouseReleased(MouseEvent e) {
            //DacicopJurnal.println("Tray Icon - Mouse released!");
        }
    };

    //ActionListener exitListener = new ActionListener() {
    //   public void actionPerformed(ActionEvent e) {
    //        DacicopJurnal.println("Iesire nu este permisa");
    //        //System.exit(0);
    //    }
    //};
          
    PopupMenu popup = new PopupMenu();
    //MenuItem defaultItem = new MenuItem("Exit");
    //defaultItem.addActionListener(exitListener);
    //popup.add(defaultItem);

    trayIcon = new TrayIcon(image, "CMC Internet - mai sigur", popup);

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            trayIcon.displayMessage("Action Event", 
                "A avut loc o actiune !",
                TrayIcon.MessageType.INFO);
        }
    };
            
    trayIcon.setImageAutoSize(true);
    trayIcon.addActionListener(actionListener);
    trayIcon.addMouseListener(mouseListener);

    try {
        tray.add(trayIcon);
    } catch (AWTException e) {
        System.err.println("TrayIcon could not be added.");
    }

} else {

    //  System Tray is not supported

    }
    }
}
