package hr;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;

import logindetails.loginpage;


public class AddEmployee extends javax.swing.JFrame {
    
    static AddEmployee ae;
    Connection c = loginpage.createLoginpage().getConnection();
    
    public String loadlatestid() {
    	try {
			String findlast = "select eid from employeedetails order by eid desc limit 1";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(findlast);
			rs.next();
			System.out.println(Arrays.toString(rs.getString(1).split("e")));
			int nextid = Integer.parseInt(rs.getString(1).split("e")[1])+1;
			String next;
			System.out.println(nextid);
			if(nextid < 10) {
				 next = "e000"+nextid;
			}else if(nextid < 100) {
				next = "e00"+nextid;
			}else if(nextid < 1000) {
				next = "e0"+nextid;
			}else {
				next = "e"+nextid;
			}
			rs.close();
			return next;	
		}catch(SQLException e) {
			
		}
		return null;
    }

    public AddEmployee() {
    	
    	
        initComponents();
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        jRadioButton1.setSelected(true);
        jTextField1.setText("e");
        jTextField1.setText(this.loadlatestid());
        
        
        
//        try {						//only for check 			/////////////////////////////////**
//			c =( Connection)DriverManager.getConnection("jdbc:mysql://localhost/welfare", "root", "");
//		}catch(Exception e) {
//			System.out.println("Conn error");
//		}
        System.out.println(c);
        
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
				if(!rsInit.getString(1).equals("admin")) {
					jComboBox4.addItem( rsInit.getString(1) );
				}
				
			}
			rsInit.close();
			
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jLabel8.setText("Telephone");

        jLabel9.setText("E-mail");

        jLabel10.setText("Pay Grade");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jLabel11.setText("Status");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jLabel12.setText("Job Title");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jRadioButton1)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jButton1)))
                        .addContainerGap(17, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
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

    
    // add employee details
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	int dialogButton = JOptionPane.YES_NO_OPTION;
    	int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure to add user data?","Confirm Adding",dialogButton);
    	
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
    		
    		//invalid id
    		if( eid=="" || !((Character)eid.charAt(0)).equals('e') ) {
    			JOptionPane.showMessageDialog(null, "Please enter a valid id");
    			jTextField1.setText("e");
    			jTextField1.setText(this.loadlatestid());
    			
    			return;
    		}else if( eid.length() != 5 ) {
    			JOptionPane.showMessageDialog(null, "ID should be 5 characters length");
    			jTextField1.setText("e");
    			jTextField1.setText(this.loadlatestid());
    			return;
    		}
    		
    		//id already exist
    		try {
    			String idquery = "SELECT eid from employeepersonal";
        		Statement ids = c.createStatement();
    			ResultSet idrs = ids.executeQuery(idquery);
    			
    			while( idrs.next() ) {
    				if( idrs.getString(1).equals(eid) ) {
    					JOptionPane.showMessageDialog(null, "Id already exists !!!");
    	    			jTextField1.setText("e");
    					jTextField1.setText(this.loadlatestid());
    	    			return;
    				}
    			}
    			idrs.close();
    			ids.close();
    		}catch(Exception e) {
    			System.out.println("ID loading error :"+e);
    			JOptionPane.showMessageDialog(null, "Error occured while checking for id!");
    			return;
    		}
    		
    		try{
    			//load leave left
    			String queryL = "select annual,casual,maturity,nopay from paygrade where pgid='" + payg + "'";
    			Statement sL = c.createStatement();
    			ResultSet rsL = sL.executeQuery(queryL);
    			rsL.next();
    			int annual = Integer.valueOf(rsL.getString(1));
    			int casual = Integer.valueOf(rsL.getString(2));
    			int maturity = Integer.valueOf(rsL.getString(3));
    			int nopay = Integer.valueOf(rsL.getString(4));
    			rsL.close();
    			sL.close();
    			
    			System.out.println("annual = " + annual);
    			System.out.println("cassual = " + casual);
    			System.out.println("mat = " + maturity);
    			System.out.println("nopay = " + nopay);
    			
    			c.setAutoCommit(false);
    			
    			String query1 = "INSERT INTO employeedetails(eid,deptid,status,title) VALUES("
						+ "'" + eid + "',"
						+ "(select deptid from department where name='" + dept + "'),"
						+ "'" + status + "',"
						+ "'" + job + "')";
    			
    			String query2 =  "INSERT INTO employeepersonal(eid,name,nic,nationality,maritalstatus,address,email) VALUES("
    					+ "'" + eid + "','" + name + "','" + nic + "','" + nation + "','" + marital + "','" + address + "','" + email + "')";
    			
    			String query3 = "INSERT INTO emppay(eid,epid,epf) VALUES("
    					+ "'" + eid + "','" + payg + "',10)";		//epf ****
    			
    			String query5 = "INSERT INTO empcontact values";
    			for(int i=0; i<phones.length; i++) {
    				if( !phones[i].trim().equals("") ) {
    					query5 += "('" + eid + "','" + phones[i].trim() + "'),";
    				}
    			}
    			query5 = query5.substring(0, query5.lastIndexOf(","));
    			
    			PreparedStatement p7 = c.prepareStatement("insert into leaveleft(eid,annual,casual,maturity,nopay) values(?,?,?,?,?)");
				p7.setString(1, eid);
				p7.setInt(2, annual);
				p7.setInt(3, casual);
				p7.setInt(4, maturity);
				p7.setInt(5, nopay);
    			
    			PreparedStatement p = c.prepareStatement("insert into employeeadditional(eid) values(?)");
    			p.setString(1, eid);
    			
    			Statement s = c.createStatement();
    			s.executeUpdate(query1);
    			s.executeUpdate(query2);
    			s.executeUpdate(query3);
    			p.executeUpdate();
    			s.executeUpdate(query5);
    			p7.executeUpdate();
    			
    			c.commit();
    			    			
    			JOptionPane.showMessageDialog(null, "Successfully Added");
    			
    			jTextField1.setText("e");
    			jTextField1.setText(this.loadlatestid());
    			jTextField2.setText("");
    			jTextField3.setText("");
    			jTextField4.setText("");
    			jTextField5.setText("");
    			jTextField6.setText("");
    			jTextField7.setText("");
    			
    		}catch(Exception ex) {
    			try {
    				c.rollback();
    			} catch (SQLException e1) {
    				e1.printStackTrace();
    				System.out.println("Rollback error :" + e1);
    			}
    			System.out.println("Insert query failed :"+ex);
    			JOptionPane.showMessageDialog(null, ex.getMessage());
    		}
    	}
    	
    }
    
    
    public static void main(String args[]) {
        
        ae=new AddEmployee();
        ae.setTitle("Add Employee");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ae.setLocation(dim.width/2-ae.getSize().width/2, dim.height/2-ae.getSize().height/2);
        ae.setVisible(true);
            
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
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