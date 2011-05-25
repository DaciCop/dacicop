/*
 * DacicopView.java
 */

package com.cubit.dacicop;

import java.awt.Toolkit;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.URL;
import java.util.Vector;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The application's main frame.
 */
public class DacicopView extends FrameView {
    final JFrame frm =this.getFrame();
    public DacicopUtilizatori utilizator = null;
    public DacicopBonus dacicopbonus=null;
    javax.swing.table.DefaultTableModel jTable4Model=null;
    javax.swing.table.DefaultTableModel jTable1Model=null;
    javax.swing.table.DefaultTableModel jTable5Model=null;
    javax.swing.table.DefaultTableModel jTable3Model=null;
    int tabCurent=0;
    public void setVis(boolean visibil){
    if (visibil) {frm.setVisible(true);
                  frm.setState(JFrame.NORMAL);
                    }
    else frm.setVisible(false);
    }


    public DacicopView(final SingleFrameApplication app) throws java.net.MalformedURLException {
        super(app);
        
        frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frm.setIconImage(Toolkit.getDefaultToolkit().getImage(new URL("http://cmcsinaia.ro/dacicop/sigla.jpeg")));
        frm.setDefaultLookAndFeelDecorated(true);

        frm.addWindowListener(new WindowAdapter(){
                    @Override
        public void windowClosing(WindowEvent evt){
                                    //DacicopJurnal.println("-----Closing window");
                                   setVis(false);
        }
        });
        frm.addWindowListener(new WindowAdapter(){
                    @Override
        public void windowIconified(WindowEvent evt){
                        //DacicopJurnal.println("-----Minimizing window");
                                    setVis(false);

        }
        });

        jTable4Model=new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, new Boolean(false), null, null, null, null, null, null},
                {null, null, null, null, new Boolean(false), null, null, null, null, null, null},
                {null, null, null, null, new Boolean(false), null, null, null, null, null, null},
                {null, null, null, null, new Boolean(false), null, null, null, null, null, null},
                
            },
            new String [] {
                "Adresa URL", "Inceput interval", "Sfarsit interval", "Beneficiari", "Activ", "inceput", "sfarsit", "comentariu", "idcerere" , "Nume solicitant", "Grup"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        jTable5Model=new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id mesaj", "Subiect", "De La", "Catre", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        


        jTable1Model=new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "", null, null, null, null, null, null, null},

            },
            new String [] {
                "Utilizator", "Nume", "Prenume", "Grup", "Rol", "IP acces", "activ", "ID utilizator", "Conectat de la IP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

       jTable3Model=new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id bonus", "Nivel bonus", "Beneficiari", "Timp rămas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        
        initComponents();
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setEnabledAt(4, false);
        jTabbedPane1.setEnabledAt(6, false);
        //jTabbedPane1.setEnabledAt(5, false);
        jCheckBox1.setEnabled(false);
        jCheckBox1.setVisible(false);
        jLabel36.setVisible(false);
        jTextField7.setVisible(false);
        jButton6.setVisible(false);
        jButton6.setEnabled(false);
        jTextField12.setVisible(false);
        jLabel45.setVisible(false);
        jLabel47.setVisible(false);
        jComboBox30.setVisible(false);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jTextField3.requestFocusInWindow();

        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jTabbedPane1.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    int index = jTabbedPane1.getSelectedIndex();
                    if (index==1) {reloadTable4();
                    reloadjComboBox30();
                    }
                    if (index==2) {
                        reloadTable1();
                        reloadjComboBox24();
                        reloadjComboBox28();
                    }
                    if (index==4) {
                        reloadTable5();
                        reloadjComboBox26();
                    }
                    if (index==3) {
                        reloadTable3();
                    }
                    if (index==6) {
                        reloadjComboBox29();
                        jComboBox29.doLayout();
                        if (jComboBox29.getItemCount()>0){
                        for(int i=0;i<jComboBox29.getItemCount();i++) {
                            if (jComboBox29.getItemAt(i).toString().startsWith(utilizator.ip))
                            {
                                jComboBox29.setSelectedIndex(i);
                            }
                        }
                         
                        jCheckBox8.setSelected(new Boolean(DacicopClient.trimite("adminpermis"+"¶"+"admin"+"¶"+"nimda"+"¶"+jComboBox29.getSelectedItem().toString())).booleanValue());
                        }
                    }

                    if (tabCurent==2) 
                        if (index==1) {
                            int[] colSelect=jTable1.getSelectedRows();
                            StringBuffer listaId=new StringBuffer("");
                            for (int i=0;i<colSelect.length;i++) listaId.append(jTable1.getValueAt(colSelect[i], 7)+"-"+jTable1.getValueAt(colSelect[i], 0)+",");
                            if (listaId.length()>0) listaId.deleteCharAt(listaId.length()-1);
                            jTextField7.setText(listaId.toString());
                        }
                        if (index==3) {
                            int[] colSelect=jTable1.getSelectedRows();
                            StringBuffer listaId=new StringBuffer("");
                            for (int i=0;i<colSelect.length;i++) listaId.append(jTable1.getValueAt(colSelect[i], 7)+"-"+jTable1.getValueAt(colSelect[i], 0)+",");
                            if (listaId.length()>0) listaId.deleteCharAt(listaId.length()-1);
                            jTextField5.setText(listaId.toString());

                        }
                    tabCurent=index;
                }
            });


        ResourceMap resourceMap = getResourceMap();

        //jTable4.setModel( jTable4Model );
        jTable4.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable4.columnModel.title0")); // NOI18N
        jTable4.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable4.columnModel.title1")); // NOI18N
        jTable4.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable4.columnModel.title2")); // NOI18N
        jTable4.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable4.columnModel.title3")); // NOI18N
        jTable4.getColumnModel().getColumn(4).setMinWidth(35);
        jTable4.getColumnModel().getColumn(4).setPreferredWidth(35);
        jTable4.getColumnModel().getColumn(4).setMaxWidth(35);
        jTable4.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable4.columnModel.title4")); // NOI18N
        jTable4.getColumnModel().getColumn(5).setMinWidth(1);
        jTable4.getColumnModel().getColumn(5).setPreferredWidth(1);
        jTable4.getColumnModel().getColumn(5).setMaxWidth(1);
        jTable4.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable4.columnModel.title5")); // NOI18N
        jTable4.getColumnModel().getColumn(6).setMinWidth(1);
        jTable4.getColumnModel().getColumn(6).setPreferredWidth(1);
        jTable4.getColumnModel().getColumn(6).setMaxWidth(1);
        jTable4.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable4.columnModel.title6")); // NOI18N
        jTable4.getColumnModel().getColumn(7).setMinWidth(1);
        jTable4.getColumnModel().getColumn(7).setPreferredWidth(1);
        jTable4.getColumnModel().getColumn(7).setMaxWidth(1);
        jTable4.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable4.columnModel.title7")); // NOI18N
        jTable4.getColumnModel().getColumn(8).setMinWidth(1);
        jTable4.getColumnModel().getColumn(8).setPreferredWidth(1);
        jTable4.getColumnModel().getColumn(8).setMaxWidth(1);
        jTable4.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable4.columnModel.title8")); // NOI18N
        jTable4.getColumnModel().getColumn(9).setMinWidth(100);
        jTable4.getColumnModel().getColumn(9).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(9).setMaxWidth(170);
        jTable4.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("jTable4.columnModel.title9")); // NOI18N
        jTable4.getColumnModel().getColumn(10).setMinWidth(60);
        jTable4.getColumnModel().getColumn(10).setPreferredWidth(60);
        jTable4.getColumnModel().getColumn(10).setMaxWidth(70);
        jTable4.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("jTable4.columnModel.title10")); // NOI18N
        // status bar initialization - message timeout, idle icon and busy animation, etc

        

        
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setMinWidth(70);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(70);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setMinWidth(70);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(70);
        jTable1.getColumnModel().getColumn(5).setMinWidth(1);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(1);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setMinWidth(1);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(1);
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setMinWidth(1);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(7).setMaxWidth(1);
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setMinWidth(110);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(110);
        jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N


        jScrollPane8.setViewportView(jTable5);
        jTable5.setName("jTable5");
        jTable5.getColumnModel().getColumn(0).setMinWidth(50);
        jTable5.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable5.getColumnModel().getColumn(0).setMaxWidth(70);
        jTable5.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable5.columnModel.title0")); // NOI18N
        jTable5.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable5.columnModel.title1")); // NOI18N
        jTable5.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable5.columnModel.title2")); // NOI18N
        jTable5.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable5.columnModel.title3")); // NOI18N
        jTable5.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable5.columnModel.title4")); // NOI18N

        jTable3.setName("jTable3"); // NOI18N
        jScrollPane3.setName("jScrollPane3"); // NOI18N
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getColumn(0).setMinWidth(1);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTable3.getColumnModel().getColumn(0).setMaxWidth(1);
        jTable3.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable3.columnModel.title0")); // NOI18N
        jTable3.getColumnModel().getColumn(1).setMinWidth(75);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(75);
        jTable3.getColumnModel().getColumn(1).setMaxWidth(75);
        jTable3.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable3.columnModel.title1")); // NOI18N
        jTable3.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable3.columnModel.title2")); // NOI18N
        jTable3.getColumnModel().getColumn(3).setMinWidth(75);
        jTable3.getColumnModel().getColumn(3).setPreferredWidth(75);
        jTable3.getColumnModel().getColumn(3).setMaxWidth(150);
        jTable3.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable3.columnModel.title3")); // NOI18N


        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        utilizator=new DacicopUtilizatori("neconectat","neconectat");
        dacicopbonus=new DacicopBonus();
    }


    //@Action
    //public void showAboutBox() {
    //    if (aboutBox == null) {
    //        JFrame mainFrame = DacicopApp.getApplication().getMainFrame();
    //        aboutBox = new DacicopAboutBox(mainFrame);
    //        aboutBox.setLocationRelativeTo(mainFrame);
    //    }
    //    DacicopApp.getApplication().show(aboutBox);
    //}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox();
        jComboBox17 = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jComboBox19 = new javax.swing.JComboBox();
        jComboBox20 = new javax.swing.JComboBox();
        jComboBox21 = new javax.swing.JComboBox();
        jComboBox22 = new javax.swing.JComboBox();
        jComboBox23 = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox30 = new javax.swing.JComboBox();
        jLabel47 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jComboBox24 = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        jComboBox25 = new javax.swing.JComboBox();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox28 = new javax.swing.JComboBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jComboBox16 = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jComboBox26 = new javax.swing.JComboBox();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jComboBox27 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel imageLabel = new javax.swing.JLabel();
        javax.swing.JLabel versionLabel = new javax.swing.JLabel();
        javax.swing.JLabel vendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel homepageLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVersionLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel appHomepageLabel = new javax.swing.JLabel();
        javax.swing.JLabel appDescLabel = new javax.swing.JLabel();
        javax.swing.JLabel appTitleLabel = new javax.swing.JLabel();
        javax.swing.JLabel imageLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel imageLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel versionLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel vendorLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel homepageLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel appVersionLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel appVendorLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel appHomepageLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel appDescLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel appTitleLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jCheckBox8 = new javax.swing.JCheckBox();
        jComboBox29 = new javax.swing.JComboBox();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusPanel = new javax.swing.JPanel();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(500, 500));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(500, 500));
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.cubit.dacicop.DacicopApp.class).getContext().getResourceMap(DacicopView.class);
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jPasswordField1.setText(resourceMap.getString("jPasswordField1.text")); // NOI18N
        jPasswordField1.setName("jPasswordField1"); // NOI18N
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Conectare", jPanel1);

        jPanel6.setName("jPanel6"); // NOI18N

        jTextField6.setText(resourceMap.getString("jTextField6.text")); // NOI18N
        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField6FocusGained(evt);
            }
        });

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Toti", "2009", "2010", "2011", "2012", "2013", "2014" }));
        jComboBox2.setName("jComboBox2"); // NOI18N

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        jLabel31.setText(resourceMap.getString("jLabel31.text")); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N

        jLabel32.setText(resourceMap.getString("jLabel32.text")); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tot anul", "Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie" }));
        jComboBox4.setName("jComboBox4"); // NOI18N

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Toate", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBox6.setName("jComboBox6"); // NOI18N

        jLabel33.setText(resourceMap.getString("jLabel33.text")); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N

        jLabel34.setText(resourceMap.getString("jLabel34.text")); // NOI18N
        jLabel34.setName("jLabel34"); // NOI18N

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jComboBox10.setName("jComboBox10"); // NOI18N

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jComboBox17.setName("jComboBox17"); // NOI18N

        jLabel35.setText(resourceMap.getString("jLabel35.text")); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pentru mine", "Grup", "Toti" }));
        jComboBox18.setName("jComboBox18"); // NOI18N
        jComboBox18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox18ActionPerformed(evt);
            }
        });

        jCheckBox1.setSelected(true);
        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Toti", "2009", "2010", "2011", "2012", "2013", "2014" }));
        jComboBox19.setName("jComboBox19"); // NOI18N

        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tot anul", "Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie" }));
        jComboBox20.setName("jComboBox20"); // NOI18N

        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Toate", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBox21.setName("jComboBox21"); // NOI18N

        jComboBox22.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jComboBox22.setName("jComboBox22"); // NOI18N

        jComboBox23.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jComboBox23.setName("jComboBox23"); // NOI18N

        jLabel36.setText(resourceMap.getString("jLabel36.text")); // NOI18N
        jLabel36.setName("jLabel36"); // NOI18N

        jTextField7.setEditable(false);
        jTextField7.setName("jTextField7"); // NOI18N

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setName("jTextArea3"); // NOI18N
        jScrollPane7.setViewportView(jTextArea3);

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jButton14.setText(resourceMap.getString("jButton14.text")); // NOI18N
        jButton14.setName("jButton14"); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jTable4.setModel(jTable4Model);
        jTable4.setName("jTable4"); // NOI18N
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable4);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setName("jLabel3"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "admin", "prof", "clasele a 9-a", "clasele a 10-a", "clasele a 11-a", "clasele a 12-a", "clasele a 13-a", "9A", "9B", "9C", "9D" }));
        jComboBox30.setName("jComboBox30"); // NOI18N

        jLabel47.setText(resourceMap.getString("jLabel47.text")); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel28)))
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(92, 92, 92)
                .addComponent(jLabel35)
                .addGap(3, 3, 3)
                .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addGap(4, 4, 4)
                .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addGap(33, 33, 33)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel25)
                    .addComponent(jLabel27)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel26)
                    .addComponent(jLabel28)
                    .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(jLabel35)
                    .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cereri", jPanel6);

        jPanel7.setName("jPanel7"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jTable1.setModel(jTable1Model);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jLabel37.setText(resourceMap.getString("jLabel37.text")); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N

        jTextField8.setText(resourceMap.getString("jTextField8.text")); // NOI18N
        jTextField8.setName("jTextField8"); // NOI18N

        jLabel38.setText(resourceMap.getString("jLabel38.text")); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N

        jTextField9.setText(resourceMap.getString("jTextField9.text")); // NOI18N
        jTextField9.setName("jTextField9"); // NOI18N

        jLabel39.setText(resourceMap.getString("jLabel39.text")); // NOI18N
        jLabel39.setName("jLabel39"); // NOI18N

        jTextField10.setText(resourceMap.getString("jTextField10.text")); // NOI18N
        jTextField10.setName("jTextField10"); // NOI18N

        jLabel40.setText(resourceMap.getString("jLabel40.text")); // NOI18N
        jLabel40.setName("jLabel40"); // NOI18N

        jComboBox24.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "admin", "prof", "9A", "9B", "9C", "9D" }));
        jComboBox24.setSelectedIndex(2);
        jComboBox24.setName("jComboBox24"); // NOI18N

        jLabel41.setText(resourceMap.getString("jLabel41.text")); // NOI18N
        jLabel41.setName("jLabel41"); // NOI18N

        jComboBox25.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "admin", "prof", "utilizator" }));
        jComboBox25.setSelectedIndex(2);
        jComboBox25.setName("jComboBox25"); // NOI18N

        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel42.setText(resourceMap.getString("jLabel42.text")); // NOI18N
        jLabel42.setName("jLabel42"); // NOI18N

        jTextField11.setEditable(false);
        jTextField11.setText(resourceMap.getString("jTextField11.text")); // NOI18N
        jTextField11.setName("jTextField11"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox28.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "192.168.35.150", "192.168.35.151" }));
        jComboBox28.setName("jComboBox28"); // NOI18N

        jCheckBox7.setSelected(true);
        jCheckBox7.setText(resourceMap.getString("jCheckBox7.text")); // NOI18N
        jCheckBox7.setName("jCheckBox7"); // NOI18N

        jButton13.setText(resourceMap.getString("jButton13.text")); // NOI18N
        jButton13.setName("jButton13"); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton15.setText(resourceMap.getString("jButton15.text")); // NOI18N
        jButton15.setName("jButton15"); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jPasswordField2.setText(resourceMap.getString("jPasswordField2.text")); // NOI18N
        jPasswordField2.setName("jPasswordField2"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(1, 1, 1)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPasswordField2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(1, 1, 1)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39)
                                .addGap(1, 1, 1)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel42)
                                .addGap(2, 2, 2)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jCheckBox7)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel40)
                                    .addGap(4, 4, 4)
                                    .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel41)
                                    .addGap(1, 1, 1)
                                    .addComponent(jComboBox25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jComboBox25, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        jPanel5.setName("jPanel5"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jTextField5.setEditable(false);
        jTextField5.setName("jTextField5"); // NOI18N

        jCheckBox2.setText(resourceMap.getString("jCheckBox2.text")); // NOI18N
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText(resourceMap.getString("jCheckBox3.text")); // NOI18N
        jCheckBox3.setName("jCheckBox3"); // NOI18N
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.setText(resourceMap.getString("jCheckBox4.text")); // NOI18N
        jCheckBox4.setName("jCheckBox4"); // NOI18N
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.setText(resourceMap.getString("jCheckBox5.text")); // NOI18N
        jCheckBox5.setName("jCheckBox5"); // NOI18N
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox6.setText(resourceMap.getString("jCheckBox6.text")); // NOI18N
        jCheckBox6.setName("jCheckBox6"); // NOI18N
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable3.setModel(jTable3Model);
        jTable3.setName("jTable3"); // NOI18N
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "lista", "toti" }));
        jComboBox16.setName("jComboBox16"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel23)
                .addContainerGap(290, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(248, 248, 248))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bonus", jPanel5);

        jPanel4.setName("jPanel4"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jScrollPane8.setName("jScrollPane8"); // NOI18N

        jTable5.setModel(jTable5Model);
        jTable5.setName("jTable5"); // NOI18N
        jTable5.getTableHeader().setReorderingAllowed(false);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable5);

        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel43.setText(resourceMap.getString("jLabel43.text")); // NOI18N
        jLabel43.setName("jLabel43"); // NOI18N

        jComboBox26.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "admin", "prof", "clase a 9-a", "clase a 10-a", "clase a 11-a", "clase a 12-a", "clase a 13-a" }));
        jComboBox26.setName("jComboBox26"); // NOI18N

        jLabel44.setText(resourceMap.getString("jLabel44.text")); // NOI18N
        jLabel44.setName("jLabel44"); // NOI18N

        jLabel45.setText(resourceMap.getString("jLabel45.text")); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N

        jTextField12.setText(resourceMap.getString("jTextField12.text")); // NOI18N
        jTextField12.setName("jTextField12"); // NOI18N

        jComboBox27.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grup", "Utilizator" }));
        jComboBox27.setName("jComboBox27"); // NOI18N
        jComboBox27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44)
                .addGap(5, 5, 5)
                .addComponent(jComboBox26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(1, 1, 1)
                        .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 336, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(jComboBox26, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(jComboBox27, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox26, jComboBox27, jLabel43, jLabel44, jLabel45, jTextField12});

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        imageLabel.setName("imageLabel"); // NOI18N

        versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | java.awt.Font.BOLD));
        versionLabel.setName("versionLabel"); // NOI18N

        vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | java.awt.Font.BOLD));
        vendorLabel.setName("vendorLabel"); // NOI18N

        homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | java.awt.Font.BOLD));
        homepageLabel.setName("homepageLabel"); // NOI18N

        appVersionLabel.setName("appVersionLabel"); // NOI18N

        appVendorLabel.setName("appVendorLabel"); // NOI18N

        appHomepageLabel.setName("appHomepageLabel"); // NOI18N

        appDescLabel.setName("appDescLabel"); // NOI18N

        appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | java.awt.Font.BOLD, appTitleLabel.getFont().getSize()+4));
        appTitleLabel.setName("appTitleLabel"); // NOI18N

        imageLabel1.setName("imageLabel1"); // NOI18N

        imageLabel2.setIcon(resourceMap.getIcon("imageLabel2.icon")); // NOI18N
        imageLabel2.setName("imageLabel2"); // NOI18N

        versionLabel1.setFont(versionLabel1.getFont().deriveFont(versionLabel1.getFont().getStyle() | java.awt.Font.BOLD));
        versionLabel1.setText(resourceMap.getString("versionLabel1.text")); // NOI18N
        versionLabel1.setName("versionLabel1"); // NOI18N

        vendorLabel1.setFont(vendorLabel1.getFont().deriveFont(vendorLabel1.getFont().getStyle() | java.awt.Font.BOLD));
        vendorLabel1.setText(resourceMap.getString("vendorLabel1.text")); // NOI18N
        vendorLabel1.setName("vendorLabel1"); // NOI18N

        homepageLabel1.setFont(homepageLabel1.getFont().deriveFont(homepageLabel1.getFont().getStyle() | java.awt.Font.BOLD));
        homepageLabel1.setText(resourceMap.getString("homepageLabel1.text")); // NOI18N
        homepageLabel1.setName("homepageLabel1"); // NOI18N

        appVersionLabel1.setText(resourceMap.getString("appVersionLabel1.text")); // NOI18N
        appVersionLabel1.setName("appVersionLabel1"); // NOI18N

        appVendorLabel1.setText(resourceMap.getString("appVendorLabel1.text")); // NOI18N
        appVendorLabel1.setName("appVendorLabel1"); // NOI18N

        appHomepageLabel1.setText(resourceMap.getString("appHomepageLabel1.text")); // NOI18N
        appHomepageLabel1.setName("appHomepageLabel1"); // NOI18N

        appDescLabel1.setText(resourceMap.getString("appDescLabel1.text")); // NOI18N
        appDescLabel1.setName("appDescLabel1"); // NOI18N

        appTitleLabel1.setFont(appTitleLabel1.getFont().deriveFont(appTitleLabel1.getFont().getStyle() | java.awt.Font.BOLD, appTitleLabel1.getFont().getSize()+4));
        appTitleLabel1.setText(resourceMap.getString("appTitleLabel1.text")); // NOI18N
        appTitleLabel1.setName("appTitleLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageLabel)
                    .addComponent(imageLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(versionLabel)
                            .addComponent(vendorLabel)
                            .addComponent(homepageLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(appVersionLabel)
                            .addComponent(appVendorLabel)
                            .addComponent(appHomepageLabel)))
                    .addComponent(appDescLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(appTitleLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(imageLabel1)
                    .addContainerGap(552, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(204, 204, 204)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(versionLabel1)
                                .addComponent(vendorLabel1)
                                .addComponent(homepageLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(appVersionLabel1)
                                .addComponent(appVendorLabel1)
                                .addComponent(appHomepageLabel1)))
                        .addComponent(appDescLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                        .addComponent(appTitleLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(appTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appDescLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(versionLabel)
                            .addComponent(appVersionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vendorLabel)
                            .addComponent(appVendorLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(homepageLabel)
                            .addComponent(appHomepageLabel)))
                    .addComponent(imageLabel)
                    .addComponent(imageLabel2))
                .addContainerGap(483, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(imageLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(appTitleLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(appDescLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(versionLabel1)
                        .addComponent(appVersionLabel1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vendorLabel1)
                        .addComponent(appVendorLabel1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(homepageLabel1)
                        .addComponent(appHomepageLabel1))
                    .addContainerGap(560, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jCheckBox8.setText(resourceMap.getString("jCheckBox8.text")); // NOI18N
        jCheckBox8.setName("jCheckBox8"); // NOI18N
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });

        jComboBox29.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "192.168.35.150", "192.168.35.151" }));
        jComboBox29.setName("jComboBox29"); // NOI18N
        jComboBox29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox8)
                    .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(359, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 3, Short.MAX_VALUE)
                .addContainerGap())
        );

        statusPanel.setName("statusPanel"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 393, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents
    private void reloadTable4(){
                    //int index = jTabbedPane1.getSelectedIndex();
                    //String title = jTabbedPane1.getTitleAt(index);
                    //DacicopJurnal.println("index = " +
                    //                        index);
                    //DacicopJurnal.println("title = " +
 					//    title);
                        String raspuns=DacicopClient.trimite("listacereri"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5);
                        java.io.BufferedReader raspunsbufread = new java.io.BufferedReader(new java.io.StringReader(raspuns));
//                        DacicopJurnal.println("Numar coloane tabela4 este: "+jTable4Model.getRowCount());
                        jTable4Model.setRowCount(0);
                        try{
                            while(raspunsbufread.ready()){
                                //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!! --- inaintecitire");
                                String linieraspuns=raspunsbufread.readLine();
                                //DacicopJurnal.println(linieraspuns);
                                if (linieraspuns!=null){
                                String[] linie=linieraspuns.split("\\¹{1}");
                                Vector linievector = new Vector();
                                //System.err.println("!!!!----" + linie);
                                for (int i=0;i<linie.length;i++){ if (linie[i].compareTo("false")==0||linie[i].compareTo("true")==0) linievector.add(new java.lang.Boolean(linie[i]));
                                    else linievector.add(linie[i]);
                                //System.err.println("!!!!----" + linie[i]);
                                }
                                jTable4Model.addRow(linievector);

                                }else break;
                            }
                        } catch (java.io.IOException f) {DacicopJurnal.println(f.getMessage());}
                        jTable4.revalidate();
    }
    private void reloadTable5(){
                    //int index = jTabbedPane1.getSelectedIndex();
                    //String title = jTabbedPane1.getTitleAt(index);
                    //DacicopJurnal.println("index = " +
                    //                        index);
                    //DacicopJurnal.println("title = " +
 					//    title);
                        String raspuns=DacicopClient.trimite("listamesaje"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5);
                        java.io.BufferedReader raspunsbufread = new java.io.BufferedReader(new java.io.StringReader(raspuns));
//                        DacicopJurnal.println("Numar coloane tabela4 este: "+jTable4Model.getRowCount());
                        jTable5Model.setRowCount(0);
                        try{
                            while(raspunsbufread.ready()){
                                //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!! --- inaintecitire");
                                String linieraspuns=raspunsbufread.readLine();
                                //DacicopJurnal.println(linieraspuns);
                                if (linieraspuns!=null){
                                String[] linie=linieraspuns.split("\\¹{1}");
                                Vector linievector = new Vector();
                                //System.err.println("!!!!----" + linie);
                                for (int i=0;i<linie.length;i++) linievector.add(linie[i]);
                                //System.err.println("!!!!----" + linie[i]);
                                jTable5Model.addRow(linievector);

                                }else break;
                            }
                        } catch (java.io.IOException f) {DacicopJurnal.println(f.getMessage());}
                        jTable5.revalidate();
    }
    private void reloadTable1(){
                    
                    //String title = jTabbedPane1.getTitleAt(index);
                    //DacicopJurnal.println("index = " +
                    //                        index);
                    //DacicopJurnal.println("title = " +
 					//    title);
                    String raspuns=DacicopClient.trimite("listautil"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5);
                        java.io.BufferedReader raspunsbufread = new java.io.BufferedReader(new java.io.StringReader(raspuns));
//                        DacicopJurnal.println("Numar coloane tabela4 este: "+jTable4Model.getRowCount());
                        jTable1Model.setRowCount(0);
                        try{
                            while(raspunsbufread.ready()){
                                //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!! --- inaintecitire");
                                String linieraspuns=raspunsbufread.readLine();
                                //DacicopJurnal.println(linieraspuns);
                                if (linieraspuns!=null){
                                String[] linie=linieraspuns.split("\\¹{1}");
                                Vector linievector = new Vector();
                                //System.err.println("!!!!----" + linie);
                                for (int i=0;i<linie.length;i++){ if (linie[i].compareTo("false")==0||linie[i].compareTo("true")==0) linievector.add(new java.lang.Boolean(linie[i]));
                                    else linievector.add(linie[i]);
                                //System.err.println("!!!!----" + linie[i]);
                                }
                                jTable1Model.addRow(linievector);

                                }else break;
                            }
                        } catch (java.io.IOException f) {DacicopJurnal.println(f.getMessage());}
                        jTable1.revalidate();
    }
    private void reloadTable3(){
                    String raspuns=DacicopClient.trimite("listabonus"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5);
                        java.io.BufferedReader raspunsbufread = new java.io.BufferedReader(new java.io.StringReader(raspuns));

                        jTable3Model.setRowCount(0);
                        try{
                            while(raspunsbufread.ready()){

                                String linieraspuns=raspunsbufread.readLine();

                                if (linieraspuns!=null){
                                String[] linie=linieraspuns.split("\\¹{1}");
                                Vector linievector = new Vector();
                                String[] timpramas=linie[linie.length-1].split("\\s{1}");
                                String strtimpramas=new String("0 minute");
                                for (int i=0;i<timpramas.length;i++) {
                                if (timpramas[i].startsWith("0")) {i++;}
                                        else {strtimpramas=timpramas[i]+" "+timpramas[i+1]+" ";
                                        break;}
                                }
                                linie[linie.length-1]=strtimpramas;
                                for (int i=0;i<linie.length;i++) {
                                    linievector.add(linie[i]);
                                }
                                jTable3Model.addRow(linievector);

                                }else break;
                            }
                        } catch (java.io.IOException f) {DacicopJurnal.println(f.getMessage());}
                        jTable3.revalidate();
    }
        private void reloadjComboBox24(){

                    //String title = jTabbedPane1.getTitleAt(index);
                    //DacicopJurnal.println("index = " +
                    //                        index);
                    //DacicopJurnal.println("title = " +
 					//    title);
                    String raspuns=DacicopClient.trimite("listagrup"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5);
                        java.io.BufferedReader raspunsbufread = new java.io.BufferedReader(new java.io.StringReader(raspuns));
//                        DacicopJurnal.println("Numar coloane tabela4 este: "+jTable4Model.getRowCount());
                        jComboBox24.removeAllItems();
                        try{
                            while(raspunsbufread.ready()){
                                //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!! --- inaintecitire");
                                String linieraspuns=raspunsbufread.readLine();
                                //DacicopJurnal.println(linieraspuns);
                                if (linieraspuns!=null){
                                String[] linie=linieraspuns.split("\\¹{1}");
                                for (int i=0;i<linie.length;i++){ jComboBox24.addItem(linie[i]);
                                }

                                }else break;
                            }
                        } catch (java.io.IOException f) {DacicopJurnal.println(f.getMessage());}
                        jComboBox24.revalidate();
    }
        private void reloadjComboBox30(){

                    //String title = jTabbedPane1.getTitleAt(index);
                    //DacicopJurnal.println("index = " +
                    //                        index);
                    //DacicopJurnal.println("title = " +
 					//    title);
                    String raspuns=DacicopClient.trimite("listagrup"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5);
                        java.io.BufferedReader raspunsbufread = new java.io.BufferedReader(new java.io.StringReader(raspuns));
//                        DacicopJurnal.println("Numar coloane tabela4 este: "+jTable4Model.getRowCount());
                        jComboBox30.removeAllItems();
                        try{
                            while(raspunsbufread.ready()){
                                //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!! --- inaintecitire");
                                String linieraspuns=raspunsbufread.readLine();
                                //DacicopJurnal.println(linieraspuns);
                                if (linieraspuns!=null){
                                String[] linie=linieraspuns.split("\\¹{1}");
                                for (int i=0;i<linie.length;i++){ jComboBox30.addItem(linie[i]);
                                }

                                }else break;
                            }
                        } catch (java.io.IOException f) {DacicopJurnal.println(f.getMessage());}
                        jComboBox30.revalidate();
    }
             private void reloadjComboBox26(){

                    //String title = jTabbedPane1.getTitleAt(index);
                    //DacicopJurnal.println("index = " +
                    //                        index);
                    //DacicopJurnal.println("title = " +
 					//    title);
                    String raspuns=DacicopClient.trimite("listagrup"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5);
                        java.io.BufferedReader raspunsbufread = new java.io.BufferedReader(new java.io.StringReader(raspuns));
//                        DacicopJurnal.println("Numar coloane tabela4 este: "+jTable4Model.getRowCount());
                        jComboBox26.removeAllItems();
                        try{
                            while(raspunsbufread.ready()){
                                //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!! --- inaintecitire");
                                String linieraspuns=raspunsbufread.readLine();
                                //DacicopJurnal.println(linieraspuns);
                                if (linieraspuns!=null){
                                String[] linie=linieraspuns.split("\\¹{1}");
                                for (int i=0;i<linie.length;i++){ jComboBox26.addItem(linie[i]);
                                }

                                }else break;
                            }
                        } catch (java.io.IOException f) {DacicopJurnal.println(f.getMessage());}
                        jComboBox26.addItem("toti");
                        jComboBox26.addItem("director");
                        jComboBox26.revalidate();
    }
           private void reloadjComboBox28(){

                    //String title = jTabbedPane1.getTitleAt(index);
                    //DacicopJurnal.println("index = " +
                    //                        index);
                    //DacicopJurnal.println("title = " +
 					//    title);
                    String raspuns=DacicopClient.trimite("listaip"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5);
                        java.io.BufferedReader raspunsbufread = new java.io.BufferedReader(new java.io.StringReader(raspuns));
//                        DacicopJurnal.println("Numar coloane tabela4 este: "+jTable4Model.getRowCount());
                        jComboBox28.removeAllItems();
                        try{
                            while(raspunsbufread.ready()){
                                //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!! --- inaintecitire");
                                String linieraspuns=raspunsbufread.readLine();
                                //DacicopJurnal.println(linieraspuns);
                                if (linieraspuns!=null){
                                String[] linie=linieraspuns.split("\\¹{1}");
                                for (int i=0;i<linie.length;i++){ jComboBox28.addItem(linie[i]);
                                }
                                }else break;
                            }
                        } catch (java.io.IOException f) {DacicopJurnal.println(f.getMessage());}
                        jComboBox28.revalidate();
    }
    private void reloadjComboBox29(){

                    //String title = jTabbedPane1.getTitleAt(index);
                    //DacicopJurnal.println("index = " +
                    //                        index);
                    //DacicopJurnal.println("title = " +
 					//    title);
                    String raspuns=DacicopClient.trimite("listaip"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5);
                        java.io.BufferedReader raspunsbufread = new java.io.BufferedReader(new java.io.StringReader(raspuns));
//                        DacicopJurnal.println("Numar coloane tabela4 este: "+jTable4Model.getRowCount());
                        jComboBox29.removeAllItems();
                        try{
                            while(raspunsbufread.ready()){
                                //DacicopJurnal.println("!!!!!!!!!!!!!!!!!!!!! --- inaintecitire");
                                String linieraspuns=raspunsbufread.readLine();
                                //DacicopJurnal.println(linieraspuns);
                                if (linieraspuns!=null){
                                String[] linie=linieraspuns.split("\\¹{1}");
                                for (int i=0;i<linie.length;i++){ jComboBox29.addItem(linie[i]);
                                }
                                }else break;
                            }
                        } catch (java.io.IOException f) {DacicopJurnal.println(f.getMessage());}
                        jComboBox29.revalidate();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      String beneficiari=new String(jTextField7.getText());
        if (jComboBox18.getSelectedIndex()==0) beneficiari="eu="+utilizator.id+"-"+utilizator.utilizator;
        if (jComboBox18.getSelectedIndex()==1) {
            beneficiari="grup="+(jComboBox30.getSelectedIndex()+1)+"-"+jComboBox30.getSelectedItem().toString();
        }
        if (jComboBox18.getSelectedIndex()==2) beneficiari="toti=";
        if (jComboBox18.getSelectedIndex()==3) beneficiari="lista="+beneficiari;
        DacicopClient.trimite("cerere"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+jTextField6.getText()+"¶"+jComboBox2.getSelectedIndex()+"¶"+jComboBox4.getSelectedIndex()+"¶"+jComboBox6.getSelectedIndex()+"¶"+jComboBox10.getSelectedIndex()+"¶"+jComboBox17.getSelectedIndex()+"¶"+jComboBox19.getSelectedIndex()+"¶"+jComboBox20.getSelectedIndex()+"¶"+jComboBox21.getSelectedIndex()+"¶"+jComboBox22.getSelectedIndex()+"¶"+jComboBox23.getSelectedIndex()+"¶"+beneficiari+"¶"+(jTextArea3.getText().isEmpty()?"lipsa comentariu":jTextArea3.getText().replace('\r', '¼').replace('\n', '½'))+"¶"+jCheckBox1.isSelected());
      reloadTable4();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

      if (!utilizator.conectat) {
          utilizator.parola=new String(jPasswordField1.getPassword());
          utilizator.utilizator=jTextField3.getText();
          utilizator.conectare();

          if(!utilizator.conectat){
          jLabel21.setText("Combinatia utilizator si parola gresita");
          jButton2.setText("Conectare");
          }
          else{
          jTabbedPane1.setEnabledAt(1, true);
          jTabbedPane1.setEnabledAt(4, true);
          jTabbedPane1.setEnabledAt(5, true);
          if (utilizator.rol.compareTo("1")==0||utilizator.rol.compareTo("2")==0){
          jTabbedPane1.setEnabledAt(2, true);
          jTabbedPane1.setEnabledAt(3, true);
          if (utilizator.rol.compareTo("1")==0) jTabbedPane1.setEnabledAt(6, true);
          jCheckBox1.setEnabled(true);
          jCheckBox1.setVisible(true);
          jButton6.setVisible(true);
          jButton6.setEnabled(true);
          jLabel36.setVisible(true);
          jTextField7.setVisible(true);
          jComboBox18.addItem(new String("Lista"));
          }
          jLabel21.setText("Conectat");
          jButton2.setText("Deconectare");
          jPasswordField1.setText("");
          jTabbedPane1.setSelectedIndex(1);
          dacicopbonus=new DacicopBonus();
          }
      }
      else
      {
          deconectareUtil();
      }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void deconectareUtil(){
        utilizator.deconectare();
          //utilizator.conectat=false;
          jTextField3.setText("");
          jPasswordField1.setText("");
          jTabbedPane1.setEnabledAt(1, false);
          jTabbedPane1.setEnabledAt(2, false);
          jTabbedPane1.setEnabledAt(3, false);
          jTabbedPane1.setEnabledAt(4, false);
          jTabbedPane1.setEnabledAt(6, false);
          jCheckBox1.setEnabled(false);
          jCheckBox1.setVisible(false);
          jButton6.setVisible(false);
          jButton6.setEnabled(false);
          jLabel36.setVisible(false);
          jTextField7.setVisible(false);
          if (jComboBox18.getItemCount()>3) for(int i=3;i<jComboBox18.getItemCount();i++) jComboBox18.removeItemAt(i);
          jLabel21.setText("Deconectat");
          jButton2.setText("Conectare");
          jTabbedPane1.setSelectedIndex(0);
    }
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int[] selectedRows=jTable4.getSelectedRows();
        for (int i=0;i<selectedRows.length;i++){
                DacicopClient.trimite("stergecerere"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+jTable4.getValueAt(selectedRows[i], 8));
        }
        reloadTable4();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int[] selectedRows=jTable4.getSelectedRows();
        StringBuffer adrese=new StringBuffer();
        for (int i=0;i<selectedRows.length;i++){
                adrese.append(jTable4.getValueAt(selectedRows[i], 0));
                adrese.append(" ");
        }
        try{
            if (System.getProperty("os.name").startsWith("Windows")) Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + adrese.toString());
            else Runtime.getRuntime().exec("firefox " + adrese.toString());
        }
        catch (java.io.IOException e) {DacicopJurnal.println(e.getMessage());}
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusGained
        if (jTextField6.getText().compareTo("http://www.cubit.ro/")==0) jTextField6.setText("http://");
    }//GEN-LAST:event_jTextField6FocusGained

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String beneficiari=new String(jTextField7.getText());
        if (jComboBox18.getSelectedIndex()==0) beneficiari="eu="+utilizator.id+"-"+utilizator.utilizator;
        if (jComboBox18.getSelectedIndex()==1) {
            beneficiari="grup="+(jComboBox30.getSelectedIndex()+1)+"-"+jComboBox30.getSelectedItem().toString();
            //beneficiari="grup="+utilizator.idgrup+"-"+utilizator.grup;
        }
        if (jComboBox18.getSelectedIndex()==2) beneficiari="toti=";
        if (jComboBox18.getSelectedIndex()==3) beneficiari="lista="+beneficiari;
        //int[] selectedRows=jTable4.getSelectedRows();
        //StringBuffer adrese=new StringBuffer();
        //for (int i=0;i<selectedRows.length;i++){
                //String[] intervincep=jTable4.getValueAt(selectedRows[i], 5).toString().split("\\¶{1}");
                //String[] intervsfar=jTable4.getValueAt(selectedRows[i], 6).toString().split("\\¶{1}");
                //DacicopClient.trimite("cerere"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+jTable4.getValueAt(selectedRows[i], 0)+"¶"+intervincep[0]+"¶"+intervincep[1]+"¶"+intervincep[2]+"¶"+intervincep[3]+"¶"+intervincep[4]+"¶"+intervsfar[0]+"¶"+intervsfar[1]+"¶"+intervsfar[2]+"¶"+intervsfar[3]+"¶"+intervsfar[4]+"¶"+jComboBox18.getSelectedIndex()+"¶"+jTable4.getValueAt(selectedRows[i], 7)+"¶"+jCheckBox1.isSelected());
                
        //}
        if (jTable4.getSelectedRowCount()==1) DacicopClient.trimite("cerere"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+jTextField6.getText()+"¶"+jComboBox2.getSelectedIndex()+"¶"+jComboBox4.getSelectedIndex()+"¶"+jComboBox6.getSelectedIndex()+"¶"+jComboBox10.getSelectedIndex()+"¶"+jComboBox17.getSelectedIndex()+"¶"+jComboBox19.getSelectedIndex()+"¶"+jComboBox20.getSelectedIndex()+"¶"+jComboBox21.getSelectedIndex()+"¶"+jComboBox22.getSelectedIndex()+"¶"+jComboBox23.getSelectedIndex()+"¶"+beneficiari+"¶"+(jTextArea3.getText().isEmpty()?"lipsa comentariu":jTextArea3.getText().replace('\r', '¼').replace('\n', '½'))+"¶"+jCheckBox1.isSelected()+"¶"+jTable4.getValueAt(jTable4.getSelectedRow(), 8));
        reloadTable4();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
       String[] intervincep=jTable4.getValueAt(jTable4.getSelectedRow(), 5).toString().split("\\¶{1}");
       String[] intervsfar=jTable4.getValueAt(jTable4.getSelectedRow(), 6).toString().split("\\¶{1}");
       jTextField6.setText(jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString());
           jComboBox2.setSelectedIndex(new java.lang.Integer(intervincep[0]).intValue());
           jComboBox4.setSelectedIndex(new java.lang.Integer(intervincep[1]).intValue());
           jComboBox6.setSelectedIndex(new java.lang.Integer(intervincep[2]).intValue());
           jComboBox10.setSelectedIndex(new java.lang.Integer(intervincep[3]).intValue());
           jComboBox17.setSelectedIndex(new java.lang.Integer(intervincep[4]).intValue());
           jComboBox19.setSelectedIndex(new java.lang.Integer(intervsfar[0]).intValue());
           jComboBox20.setSelectedIndex(new java.lang.Integer(intervsfar[1]).intValue());
           jComboBox21.setSelectedIndex(new java.lang.Integer(intervsfar[2]).intValue());
           jComboBox22.setSelectedIndex(new java.lang.Integer(intervsfar[3]).intValue());
           jComboBox23.setSelectedIndex(new java.lang.Integer(intervsfar[4]).intValue());

           String beneficiar=jTable4.getValueAt(jTable4.getSelectedRow(), 3).toString();
           String benef[] =beneficiar.split("=");
           
           if (benef[0].compareTo("eu")==0) jComboBox18.setSelectedIndex(0);
           else if (benef[0].compareTo("grup")==0)  {
               jComboBox18.setSelectedIndex(1);
               jComboBox30.setSelectedIndex(new Integer(benef[1].split("-")[0]).intValue()-1);
           }
           else if (benef[0].compareTo("toti")==0)    jComboBox18.setSelectedIndex(2);
           else if (benef[0].compareTo("lista")==0)     {
               jComboBox18.setSelectedIndex(3);
           }
           if (benef.length>1) jTextField7.setText(benef[1]); else jTextField7.setText("");

           jTextArea3.setText(jTable4.getValueAt(jTable4.getSelectedRow(), 7).toString().replace('¼','\r').replace('½','\n'));
           jCheckBox1.setSelected(new Boolean(jTable4.getValueAt(jTable4.getSelectedRow(), 4).toString()).booleanValue());

    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     int[] selectedRows=jTable4.getSelectedRows();
        for (int i=0;i<selectedRows.length;i++){
                DacicopClient.trimite("aprobacerere"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+jTable4.getValueAt(selectedRows[i], 8));
        }
        reloadTable4();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped
            if (evt.getKeyChar()==evt.VK_ENTER) jButton2ActionPerformed(null);         // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1KeyTyped

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       DacicopClient.trimite("creareutil"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+jTextField8.getText()+"¶"+new String(jPasswordField2.getPassword())+"¶"+jTextField9.getText()+"¶"+jTextField10.getText()+"¶"+(jComboBox24.getSelectedIndex()+1)+"¶"+(jComboBox25.getSelectedIndex()+1)+"¶"+(jComboBox28.getSelectedIndex()+1)+"¶"+jCheckBox7.isSelected()); // TODO add your handling code here:
       reloadTable1();
       jPasswordField2.setText("");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       DacicopClient.trimite("creareutil"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+jTextField8.getText()+"¶"+new String(jPasswordField2.getPassword())+"¶"+jTextField9.getText()+"¶"+jTextField10.getText()+"¶"+(jComboBox24.getSelectedIndex()+1)+"¶"+(jComboBox25.getSelectedIndex()+1)+"¶"+(jComboBox28.getSelectedIndex()+1)+"¶"+jCheckBox7.isSelected()+"¶"+jTable1.getValueAt(jTable1.getSelectedRow(), 7));
       reloadTable1();
       jPasswordField2.setText("");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    jTextField8.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
    jTextField9.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
    jTextField10.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
    jTextField11.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString());
    jCheckBox7.setSelected(new java.lang.Boolean(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString()).booleanValue());
    jComboBox24.setSelectedIndex(new Integer(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString().split("-")[0]).intValue()-1);
    jComboBox25.setSelectedIndex(new Integer(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString().split("-")[0]).intValue()-1);
    jComboBox28.setSelectedIndex(new Integer(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString()).intValue()-1);
    // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String adresaIP=new String(jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString());

        if (adresaIP.compareTo("neconectat")!=0)
        try{
            Process proces=Runtime.getRuntime().exec("C:\\Program Files\\RealVNC\\VNC4\\vncviewer.exe UserName=ViewOnly " + adresaIP+":5959");
            //proces.waitFor();
        } catch(java.io.IOException e) {DacicopJurnal.println("Eroare executie vnc: "+ e.getMessage());}
        //catch(java.lang.InterruptedException e) {DacicopJurnal.println("Eroare intrerupere vnc: "+ e.getMessage());}
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        String adresaIP=new String(jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString());

        if (adresaIP.compareTo("neconectat")!=0)
        try{
            Process proces=Runtime.getRuntime().exec("C:\\Program Files\\RealVNC\\VNC4\\vncviewer.exe UserName=Admin " + adresaIP+":5959");
            //proces.waitFor();
        } catch(java.io.IOException e) {DacicopJurnal.println("Eroare executie vnc: "+ e.getMessage());}
        //catch(java.lang.InterruptedException e) {DacicopJurnal.println("Eroare intrerupere vnc: "+ e.getMessage());}
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
      // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jComboBox27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox27ActionPerformed
        if (jComboBox27.getSelectedIndex()==0) {
            jComboBox26.setVisible(true);
            jTextField12.setVisible(false);
            jLabel45.setVisible(false);
            jLabel44.setVisible(true);
        }
        else {
            jComboBox26.setVisible(false);
            //jComboBox26.setEnabled(false);
            jTextField12.setVisible(true);
            jLabel45.setVisible(true);
            jLabel44.setVisible(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox27ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DacicopClient.trimite("mesajnou"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+jTextField4.getText()+"¶"+utilizator.id+"-"+utilizator.utilizator+"¶"+(jComboBox27.getSelectedIndex()==0?jComboBox26.getSelectedItem().toString():jTextField12.getText())+"¶"+jTextArea1.getText()+"¶"+"true");
        reloadTable5();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox18ActionPerformed
            if (jComboBox18.getSelectedIndex()==1)
            {
                jLabel47.setVisible(true);
                jComboBox30.setVisible(true);
                jComboBox30.setSelectedIndex(new Integer(utilizator.idgrup).intValue()-1);
            }
            else {
                jLabel47.setVisible(false);
                jComboBox30.setVisible(false);
            }// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox18ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int i[]=jTable5.getSelectedRows();
        for (int j=0;j<i.length;j++)
        DacicopClient.trimite("stergemesaj"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+jTable5.getValueAt(i[j], 0));// TODO add your handling code here:
        reloadTable5();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        jTextField4.setText(jTable5.getValueAt(jTable5.getSelectedRow(), 1).toString());
        jTextArea1.setText(DacicopClient.trimite("continutmesaj"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+jTable5.getValueAt(jTable5.getSelectedRow(), 0)));// TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void nivelBonus(int nivel){
    Object[] checkBox = {jCheckBox3,jCheckBox4,jCheckBox2,jCheckBox5,jCheckBox6};
    jCheckBox3.setSelected(false);
    jCheckBox4.setSelected(false);
    jCheckBox2.setSelected(false);
    jCheckBox5.setSelected(false);
    jCheckBox6.setSelected(false);
    for (int i=0; i<nivel;i++) ((javax.swing.JCheckBox)checkBox[i]).setSelected(true);

    }

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
         nivelBonus(2);
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        nivelBonus(3);// TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        nivelBonus(4);// TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        nivelBonus(5); // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
       nivelBonus(1); // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        javax.swing.JCheckBox[] checkBox = {jCheckBox3,jCheckBox4,jCheckBox2,jCheckBox5,jCheckBox6};
        int nivel=0;
        for (int i=4;i>=0;i--) {
            if (checkBox[i].isSelected()) {
            nivel=i+1;
            break;
        }
        }
        String beneficiari=new String("toti=");
        if (jComboBox16.getSelectedIndex()==0) beneficiari=new String("lista="+jTextField5.getText());
        else if (jComboBox16.getSelectedIndex()==1) beneficiari=new String("toti=");
        else if (jComboBox16.getSelectedIndex()==2) beneficiari=new String("grup="+jComboBox16.getSelectedItem().toString());
        DacicopClient.trimite("bonusnou"+"¶"+utilizator.utilizator+"¶"+utilizator.parolaDB5+"¶"+nivel+"¶"+beneficiari);      // TODO add your handling code here:
        reloadTable3();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        if (!jCheckBox8.isSelected()) DacicopClient.trimite("interziceadmin"+"¶"+"admin"+"¶"+"nimda"+"¶"+(jComboBox29.getSelectedIndex()+1));
        else  DacicopClient.trimite("permiteadmin"+"¶"+"admin"+"¶"+"nimda"+"¶"+(jComboBox29.getSelectedIndex()+1));// TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void jComboBox29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox29ActionPerformed
       String ip=new String();
       if (jComboBox29.getItemCount()>0)
       {
           ip=jComboBox29.getSelectedItem().toString();
           String raspuns=DacicopClient.trimite("adminpermis"+"¶"+"admin"+"¶"+"nimda"+"¶"+ip);

       //System.out.println("Admin permis: "+ip+" - " + raspuns);
          jCheckBox8.setSelected(new Boolean(raspuns).booleanValue());
       // jCheckBox8.setSelected(new Boolean(DacicopClient.trimite("adminpermis"+"¶"+"admin"+"¶"+"nimda"+"¶"+jComboBox29.getSelectedItem().toString())).booleanValue());
        // TODO add your handling code here:
       }
    }//GEN-LAST:event_jComboBox29ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox16;
    private javax.swing.JComboBox jComboBox17;
    private javax.swing.JComboBox jComboBox18;
    private javax.swing.JComboBox jComboBox19;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox20;
    private javax.swing.JComboBox jComboBox21;
    private javax.swing.JComboBox jComboBox22;
    private javax.swing.JComboBox jComboBox23;
    private javax.swing.JComboBox jComboBox24;
    private javax.swing.JComboBox jComboBox25;
    private javax.swing.JComboBox jComboBox26;
    private javax.swing.JComboBox jComboBox27;
    private javax.swing.JComboBox jComboBox28;
    private javax.swing.JComboBox jComboBox29;
    private javax.swing.JComboBox jComboBox30;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

}
