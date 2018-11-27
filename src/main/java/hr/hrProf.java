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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		JButton btnOpenTable = new JButton("Open Table");
		btnOpenTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setsup setsup1 = new setsup();
				setsup1.open();
			}
		});
		btnOpenTable.setBounds(153, 177, 89, 23);
		contentPane.add(btnOpenTable);
		
		JButton btnAddEmployee = new JButton("Add employee");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddEmployee form = new AddEmployee();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnAddEmployee.setBounds(153, 108, 154, 23);
		contentPane.add(btnAddEmployee);
		
		final Connection c = loginpage.createLoginpage().getConnection();
		PreparedStatement p;
		try {
			p = c.prepareStatement("SELECT count(eid) FROM `employeedetails` WHERE supervisorid=?");
			p.setString(1, loginpage.geteid());
			ResultSet rs = p.executeQuery();
			
			if(rs.next()) {
				int d = rs.getInt(1);
				if(d>0) {
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
