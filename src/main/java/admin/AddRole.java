package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logindetails.loginpage;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddRole extends JFrame {

	private JPanel contentPane;
	private JTextField Rolename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRole frame = new AddRole();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddRole() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		final Connection c = loginpage.createLoginpage().getConnection();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Rolename = new JTextField();
		Rolename.setBounds(224, 49, 86, 20);
		contentPane.add(Rolename);
		Rolename.setColumns(10);
		
		final JComboBox accesslevel = new JComboBox();
		accesslevel.setBounds(224, 97, 86, 22);
		contentPane.add(accesslevel);
		
		JLabel lblRoleName = new JLabel("Role Name");
		lblRoleName.setBounds(86, 52, 72, 14);
		contentPane.add(lblRoleName);
		
		JLabel lblAccessLevels = new JLabel("Access Levels");
		lblAccessLevels.setBounds(86, 101, 72, 14);
		contentPane.add(lblAccessLevels);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PreparedStatement ps;
				try {
					ps = c.prepareStatement("insert into jobtitiles(rolename,name) values (?,?)");
					ps.setString(2, accesslevel.getSelectedItem().toString());
					ps.setString(1, Rolename.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Inserted");
					

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error"+e);
					e.printStackTrace();
				}
			}
		});
		btnCreate.setBounds(138, 172, 89, 23);
		contentPane.add(btnCreate);
		
		try {
			String querydept = "select name from userroles";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(querydept);
			while(rs.next()) {
				accesslevel.addItem(rs.getString(1));
			}
		}catch(Exception e) {
			
		}
	}

}
