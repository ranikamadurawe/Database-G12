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

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField eid;
	private JPasswordField pass;
	private JPasswordField reconfirm;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		eid = new JTextField();
		eid.setBounds(209, 51, 86, 20);
		contentPane.add(eid);
		eid.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(209, 98, 71, 20);
		contentPane.add(pass);
		
		reconfirm = new JPasswordField();
		reconfirm.setBounds(209, 161, 71, 20);
		contentPane.add(reconfirm);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(!pass.getText().equals(reconfirm.getText())) {
						JOptionPane.showMessageDialog(null, "Passwords do not match");
					}else {
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/welfare", "loggingin", "okay");
						
						PreparedStatement ps = conn.prepareStatement( "select * from useraccount where eid = ?");
						ps.setString(1, eid.getText());
						Statement s = conn.createStatement();
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Account Already exists please Login");
						}else {
							PreparedStatement p = conn.prepareStatement("insert into useraccount values(?,?)");
							p.setString(1, eid.getText());
							p.setString(2, pass.getText());
							p.executeUpdate();
							PreparedStatement ps2 = conn.prepareStatement( "select password,title from useraccount join employeedetails using(eid) where eid = ?");
							ps2.setString(1, eid.getText());
							ResultSet rs2 = ps2.executeQuery();
							String title;
							while(rs2.next()) {
								
								title = rs2.getString(2);
								PreparedStatement ps3 = conn.prepareStatement("select cuname,cpass from jobtitiles join userroles using(name) where rolename = ?");
								ps3.setString(1, title);
								System.out.println(title);
								ResultSet rs3 = ps3.executeQuery();
								while(rs3.next()) {
									String connectionname = rs3.getString(1);
									String connectionpass = rs3.getString(2);
									conn = null;
									System.out.println(connectionname);
									loginpage.createLoginpage().conn = DriverManager.getConnection("jdbc:mysql://localhost/welfare", connectionname, connectionpass);
									loginpage.eid = eid.getText();
									if(title.equals("admin")) {
										adminload loadview = new adminload();
										loadview.setVisible(true);
										setVisible(false);
										loginpage.role = "admin";
									}else if(title.equals("hr")) {
										hrProf loadview = new hrProf();
										loadview.setVisible(true);
										setVisible(false);
										loginpage.role ="hr";
									}else  {
										EmployeeHome loadview = new EmployeeHome();
										loadview.setVisible(true);
										setVisible(false);
										loginpage.role = "employee";
									}
								}
							}
						}
					}
					
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnSignUp.setBounds(259, 213, 89, 23);
		contentPane.add(btnSignUp);
		
		btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				loginpage log = new loginpage();
				log.setVisible(true);
				setVisible(false);
			}
		});
		btnLogin.setBounds(93, 213, 89, 23);
		contentPane.add(btnLogin);
	}
}
