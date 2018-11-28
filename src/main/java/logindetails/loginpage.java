package logindetails;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.security.MessageDigest;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import admin.adminload;
import employee.EmployeeHome;
import hr.hrProf;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.sql.*;

import javax.swing.JLabel;

public class loginpage extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	public Connection conn;
	public static String eid;
	public static loginpage load;
	public static String role;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	public Connection getConnection() {
		return this.conn;
	}
	
	public static String geteid() {
		return eid;
	}
	
	public static String getrole() {
		return role;
	}
	
	public void clearConnecton() {
		try {
			this.conn.close();
			this.conn = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static loginpage createLoginpage() {
		if(load == null) {
			load = new loginpage();
		}
		return load;
	}
	
	public loginpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(104, 192, 89, 23);
		contentPane.add(btnLogin);
		
		username = new JTextField();
		username.setBounds(196, 71, 115, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		final JPasswordField password = new JPasswordField();
		password.setBounds(197, 135, 114, 20);
		contentPane.add(password);
		
		JButton btnSignuo = new JButton("Signup");
		btnSignuo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				signup signup = new signup();
				signup.setVisible(true);
				setVisible(false);
			}
		});
		btnSignuo.setBounds(236, 192, 89, 23);
		contentPane.add(btnSignuo);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setBounds(104, 74, 82, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(104, 138, 89, 14);
		contentPane.add(lblPassword);
		

		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String usernamegiven = username.getText();
				String passwordgiven = password.getText()+"";
				String connectionname;
				String connectionpass;
				String title;
				
				
				boolean loaded = false;
				try {
					System.out.println(usernamegiven);
					conn = DriverManager.getConnection("jdbc:mysql://localhost/welfare", "loggingin", "okay");
					CallableStatement cstmt = conn.prepareCall("{? = call hashpass(?)}");
					cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
					cstmt.setString(2, passwordgiven);
					cstmt.execute();
					
					PreparedStatement ps = conn.prepareStatement("select title from (select eid  from useraccount where eid = ? and password=?) as t2 join employeedetails using(eid) ");
					ps.setString(1, usernamegiven);
					ps.setString(2, cstmt.getString(1));
					ResultSet rs = ps.executeQuery();
					int count = 0;
					while(rs.next()) {
						title = rs.getString(1);
							PreparedStatement ps2 = conn.prepareStatement("select cuname,cpass from jobtitiles join userroles using(name) where rolename = ?");
							ps2.setString(1, title);
							ResultSet rs2 = ps2.executeQuery();
							while(rs2.next()) {
								connectionname = rs2.getString(1);
								connectionpass = rs2.getString(2);
								conn = null;
								System.out.println("d");
								conn = DriverManager.getConnection("jdbc:mysql://localhost/welfare", connectionname, connectionpass);
								eid = usernamegiven;
								if(title.equals("admin")) {
									adminload loadview = new adminload();
									loadview.setVisible(true);
									setVisible(false);
									role = "admin";
									loaded = true;
								}else if(title.equals("hr")) {
									hrProf loadview = new hrProf();
									loadview.setVisible(true);
									setVisible(false);
									role ="hr";
									loaded = true;
								}else {
									EmployeeHome loadview = new EmployeeHome();
									loadview.setVisible(true);
									setVisible(false);
									role = "employee";
									loaded = true;
								}
								password.setText("");
								username.setText("");
							}
						
						count++;
					}
					if(count==0 && !loaded) {
						JOptionPane.showMessageDialog(null, "Incorrect Password or EmployeeID. Make sure to create your account first");
					}


				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});

		
	}
}
