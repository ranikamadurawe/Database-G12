package manager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import logindetails.loginpage;

public class UpdateEmployee extends javax.swing.JFrame {
    
    static UpdateEmployee ue;
    Connection c;
    private String myid;
    private String subID;

    public UpdateEmployee() {
        initComponents();
        c = loginpage.createLoginpage().getConnection();
        
//        try {						//only for check
//			//c =( Connection)DriverManager.getConnection("jdbc:mysql://localhost/welfare", "root", "");
//		}catch(Exception e) {
//			System.out.println("Conn error");
//		}
//        System.out.println(c);
        
        myid = loginpage.geteid();
        subID = "";
        
        try{
        	Statement sInit = c.createStatement();
        	String queryInit = "";
        	ResultSet rsInit;
        	
        	//load departments
        	queryInit = "select deptid,name from department";
			rsInit = sInit.executeQuery(queryInit);
			while( rsInit.next() ) {
				jComboBox1.addItem(rsInit.getString(2));
			}
			rsInit.close();
			
			//load paygrades
        	queryInit = "select pgid,salary from paygrade";
			rsInit = sInit.executeQuery(queryInit);
			while( rsInit.next() ) {
				jComboBox2.addItem( rsInit.getString(1) );
			}
			rsInit.close();
			
			//load status
        	queryInit = "select distinct statusdesc from estatus";
			rsInit = sInit.executeQuery(queryInit);
			while( rsInit.next() ) {
				jComboBox3.addItem( rsInit.getString(1) );
			}
			rsInit.close();
			
			//load job titles
        	queryInit = "select distinct rolename from jobtitiles";
			rsInit = sInit.executeQuery(queryInit);
			while( rsInit.next() ) {
				jComboBox4.addItem( rsInit.getString(1) );
			}
			rsInit.close();
			
			jComboBox1.setSelectedItem(null);
			jComboBox2.setSelectedItem(null);
			jComboBox3.setSelectedItem(null);
			jComboBox4.setSelectedItem(null);
			
        }catch(Exception e){
        	System.out.println("Initial data loading error :"+e);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        bg = new ButtonGroup();
        bg.add(jRadioButton1);
        bg.add(jRadioButton2);
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID");

        jLabel2.setText("Name");

        jLabel3.setText("Nationality");

        jLabel4.setText("NIC");

        jLabel5.setText("Address");

        jRadioButton1.setText("Married");

        jRadioButton2.setText("Single");

        jLabel6.setText("Marital status");

        jLabel7.setText("Department");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        jLabel8.setText("Telephone");

        jLabel9.setText("E-mail");

        jLabel10.setText("Pay Grade");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        jLabel11.setText("Status");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        jLabel12.setText("Job Title");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Load");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton2)
                        .addGap(162, 162, 162))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(87, 87, 87)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(77, 77, 77)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGap(76, 76, 76)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jRadioButton1)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton2))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jButton1)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // load subordinate details
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    	//clear all previous values
    	jTextField2.setText("");
    	jTextField3.setText("");
    	jTextField4.setText("");
    	jTextField5.setText("");
    	jTextField6.setText("");
    	jTextField7.setText("");
    	jRadioButton1.setSelected(false);
    	jRadioButton2.setSelected(false);
    	jComboBox1.setSelectedItem(null);
		jComboBox2.setSelectedItem(null);
		jComboBox3.setSelectedItem(null);
		jComboBox4.setSelectedItem(null);
    	
    	subID = jTextField1.getText().toString();
    	System.out.println("subID = " + subID);
    	
    	try {
    		String querycheck = "SELECT supervisorid FROM employeedetails WHERE eid='" + subID + "'";
			Statement scheck = c.createStatement();
			ResultSet rscheck = scheck.executeQuery(querycheck);
			rscheck.next();
			if( myid.equals(subID) ) {
				JOptionPane.showMessageDialog(null, subID+" is your id!!!");
				return;
			}
			else if( !rscheck.getString(1).equals(myid) ) {
				JOptionPane.showMessageDialog(null, subID+" employee is not your subordinate!");
				return;
			}
    	}catch(Exception e) {
    		System.out.println("Checking ID Error :"+e);
			JOptionPane.showMessageDialog(null, "ID Verification Failed !!!");
			return;
    	}

    	
    	try{
	    	String query1 = "select * from (employeepersonal natural join "
								+ "(select eid,name as department,status,title from employeedetails left join department using(deptid)) as t1) "
								+ "left join "
								+ "(select eid,epid from emppay where eid='" + subID + "') as t2 "
								+ "using(eid) where eid='" + subID + "'";
	    	
	    	String query2 = "select contact from empcontact where eid='" + subID + "'";
	    	
			Statement s1 = c.createStatement();
			Statement s2 = c.createStatement();
			ResultSet rs1 = s1.executeQuery(query1);
			ResultSet rs2 = s2.executeQuery(query2);
			
			while( rs1.next() ) {

				jTextField2.setText(rs1.getString(4));		//name
				jTextField3.setText(rs1.getString(2));		//nationality
				jTextField4.setText(rs1.getString(3));		//nic
				jTextField5.setText(rs1.getString(7));		//address
				jTextField7.setText(rs1.getString(8));		//e-mail
				
				if( rs1.getString(5).equals("Single") ) {	//marital status
					jRadioButton2.setSelected(true);
				}else if( rs1.getString(5).equals("Married") ) {
					jRadioButton1.setSelected(true);
				}
				
				jComboBox1.setSelectedItem(rs1.getString(9));	//department
				jComboBox2.setSelectedItem(rs1.getString(12));	//paygrade
				jComboBox3.setSelectedItem(rs1.getString(10));	//status
				jComboBox4.setSelectedItem(rs1.getString(11));	//job title
				
			}
			
			String numbers = "";		//contact numbers
			while(rs2.next()) {
				numbers += rs2.getString(1) + " ;\t";
			}
			jTextField6.setText(numbers);
			
    	}catch(Exception e) {
    		System.out.println("Error : " + e);
    	}
    	
    }
    
    
    // update subordinate details
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

    	int dialogButton = JOptionPane.YES_NO_OPTION;
    	int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure to update user data?","Confirm Update",dialogButton);
    	
    	if(dialogResult == JOptionPane.YES_OPTION){
    		String eid = jTextField1.getText();		//eid
        	String name = jTextField3.getText();		//name
        	String nation = jTextField2.getText();		//nationality
        	String nic = jTextField4.getText();		//nic
        	String address = jTextField5.getText();		//address
        	String email = jTextField7.getText();		//e-mail
    		
        	String marital = "";			//marital
        	if( jRadioButton1.isSelected() ) {
        		marital = "Married";
        	}else if( jRadioButton2.isSelected() ) {
        		marital = "Single";
        	}
    		
    		String[] phones = jTextField6.getText().split(";");		//contact numbers
    		
    		String dept = jComboBox1.getSelectedItem().toString();	//department
    		String payg = jComboBox2.getSelectedItem().toString();	//paygrade
    		String status = jComboBox3.getSelectedItem().toString();	//status
    		String job = jComboBox4.getSelectedItem().toString();	//job title
    		
    		try{
    			c.setAutoCommit(false);
    			String query1 =  "UPDATE employeepersonal SET "
    								+ "name='" + name + "',"
    								+ "nic='" + nic + "',"
    								+ "nationality ='" + nation + "',"
    								+ "maritalstatus='" + marital + "',"
    								+ "address='" + address + "',"
    								+ "email='" + email + "' "
    							+ "WHERE eid='" + eid + "'";
    			
    			String query2 = "UPDATE employeedetails SET "
    								+ "deptid=(select deptid from department where name='" + dept + "'),"
    								+ "status='" + status + "',"
    								+ "title='" + job + "' "
    							+ "WHERE eid='" + eid + "'";
    			
    			String query3 = "UPDATE emppay SET epid='" + payg + "'";
    			
    			String query4 = "DELETE FROM empcontact WHERE eid='" + eid + "'";
    			String query5 = "INSERT INTO empcontact values";
    			for(int i=0; i<phones.length; i++) {
    				if( !phones[i].trim().equals("") ) {
    					query5 += "('" + eid + "','" + phones[i].trim() + "'),";
    				}
    			}
    			query5 = query5.substring(0, query5.lastIndexOf(","));
    			
    			Statement s = c.createStatement();
    			s.executeUpdate(query1);
    			s.executeUpdate(query2);
    			s.executeUpdate(query3);
    			s.executeUpdate(query4);
    			s.executeUpdate(query5);
    			
    			c.commit();
    			JOptionPane.showMessageDialog(null, "Successfully Updated");
    		}catch(Exception ex) {
    			try {
    				c.rollback();
    			} catch (SQLException e1) {
    				e1.printStackTrace();
    				System.out.println("Rollback error :" + e1);
    			}
    			System.out.println("Update query failed :"+ex);
    			JOptionPane.showMessageDialog(null, "Updation Failed !!!");
    		}
    	}
    	
    }

    /**
     * @param args the command line arguments
     */   
    
    public static void main(String args[]) {        
        ue=new UpdateEmployee();            
        ue.setTitle("Update Employee");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ue.setLocation(dim.width/2-ue.getSize().width/2, dim.height/2-ue.getSize().height/2);
        ue.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private ButtonGroup bg;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}