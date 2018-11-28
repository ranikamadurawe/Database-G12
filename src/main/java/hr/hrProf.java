package hr;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logindetails.loginpage;
import manager.Managerhome;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import java.awt.Font;

public class hrProf extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public hrProf() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOpenTable = new JButton("Set Supervisors");
		btnOpenTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setsup setsup1 = new setsup();
				setsup1.open();
			}
		});
		btnOpenTable.setBounds(128, 153, 154, 23);
		contentPane.add(btnOpenTable);
		
		JButton btnAddEmployee = new JButton("Add employee");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddEmployee form = new AddEmployee();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnAddEmployee.setBounds(128, 107, 154, 23);
		contentPane.add(btnAddEmployee);
		
		JLabel lblHrHome = new JLabel("HR Home");
		lblHrHome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHrHome.setBounds(251, 33, 75, 32);
		contentPane.add(lblHrHome);
		
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginpage.createLoginpage().clearConnecton();
				loginpage form = loginpage.createLoginpage();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(128, 198, 154, 23);
		contentPane.add(btnNewButton);
		
		final Connection c = loginpage.createLoginpage().getConnection();
		PreparedStatement p;
		try {
			CallableStatement cstmt = c.prepareCall("{? = call getsup(?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.setString(2, loginpage.geteid());
			cstmt.execute();
			
			
			
			if(cstmt.getInt(1)>0) {
				if(true) {
					JButton btnSupervisor = new JButton("Supervisor");
					btnSupervisor.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Managerhome form = new Managerhome();
							form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							form.setVisible(true);
						}
					});
					
					btnSupervisor.setBounds(30, 36, 89, 23);
					contentPane.add(btnSupervisor);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
