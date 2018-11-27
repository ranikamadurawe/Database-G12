package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logindetails.loginpage;
import manager.Managerhome;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminload extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public adminload() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome Admin");
		lblWelcomeAdmin.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblWelcomeAdmin.setBounds(194, 36, 271, 87);
		contentPane.add(lblWelcomeAdmin);
		
		JButton btnaddHR = new JButton("Add HR");
		btnaddHR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addhr form = new addhr();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnaddHR.setBounds(10, 225, 89, 23);
		contentPane.add(btnaddHR);
		
		JButton btnNewButton_1 = new JButton("Add new Paygrade");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddPayGrade form = new AddPayGrade();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(120, 216, 132, 41);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Add Dept");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDept form = new AddDept();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(262, 216, 101, 41);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Add Branch");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddNewLocation form = new AddNewLocation();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnNewButton.setBounds(373, 216, 101, 41);
		contentPane.add(btnNewButton);
		
		JButton btnAddRole = new JButton("Add role");
		btnAddRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddRole form = new AddRole();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnAddRole.setBounds(10, 148, 89, 23);
		contentPane.add(btnAddRole);
		
		JButton btnGenerateReport = new JButton("generate Report");
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GenerateReport form = new GenerateReport();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnGenerateReport.setBounds(120, 148, 150, 23);
		contentPane.add(btnGenerateReport);
		
		JButton btnAddNewColumn = new JButton("Add New Column");
		btnAddNewColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddNewEmployeeColumn form = new AddNewEmployeeColumn();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnAddNewColumn.setBounds(292, 148, 121, 41);
		contentPane.add(btnAddNewColumn);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(453, 24, 89, 23);
		contentPane.add(btnLogout);
		
		//Import from here for supervisor
		
		final Connection c = loginpage.createLoginpage().getConnection();
		PreparedStatement p;
		try {
			p = c.prepareStatement("SELECT count(eid) FROM `employeedetails` WHERE supervisorid=?");
			p.setString(1, loginpage.geteid());
			ResultSet rs = p.executeQuery();
			
			if(rs.next()) {
				int d = rs.getInt(1);
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
