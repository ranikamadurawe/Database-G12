package employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logindetails.loginpage;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddEmergency extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField phone;
	private JTextField mail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmergency frame = new AddEmergency();
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
	public AddEmergency() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = new JTextField();
		name.setBounds(160, 72, 86, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(160, 103, 86, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		mail = new JTextField();
		mail.setBounds(160, 134, 86, 20);
		contentPane.add(mail);
		mail.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final Connection c = loginpage.createLoginpage().getConnection();
		        try {
					PreparedStatement p = c.prepareStatement("insert into empemergenct(eid,name,phone,email) values (?,?,?,?)");
					p.setString(1, loginpage.geteid());
					p.setString(2, name.getText());
					p.setString(3, phone.getText());
					p.setString(4, mail.getText());
					p.executeUpdate();
					p.setString(1, loginpage.geteid());
					p.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(157, 177, 89, 23);
		contentPane.add(btnAdd);
	}
}
