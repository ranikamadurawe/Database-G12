package logindetails;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.sql.*;
import java.sql.DriverManager;

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
		this.conn = null;
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
		username.setBounds(196, 71, 86, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		final JPasswordField password = new JPasswordField();
		password.setBounds(197, 135, 114, 20);
		contentPane.add(password);
		
		JButton btnSignuo = new JButton("Signuo");
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
					PreparedStatement ps = conn.prepareStatement("select password,title from useraccount join employeedetails using(eid) where eid = ?");
					ps.setString(1, usernamegiven);
					ResultSet rs = ps.executeQuery();
					int count = 0;
					while(rs.next()) {
						String pass= rs.getString(1);
						title = rs.getString(2);
						if(pass.equals(passwordgiven)) {
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
							}
						}else {
							JOptionPane.showMessageDialog(null, "Incorrect Password or EmployeeID. Make sure to create your account first");
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
