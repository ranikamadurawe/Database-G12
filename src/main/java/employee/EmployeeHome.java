package employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.addhr;
import employee.AddEmergency;
import employee.ApplyLeave;
import employee.CheckLeaveStatus;
import employee.ViewPersonalDetails;
import hr.setsup;
import logindetails.loginpage;
import manager.Managerhome;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeHome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeHome frame = new EmployeeHome();
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
	public EmployeeHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Apply Leave");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ApplyLeave form = new ApplyLeave();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnNewButton.setBounds(183, 82, 138, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Check Leave");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CheckLeaveStatus form = new CheckLeaveStatus();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(34, 116, 136, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View Personal Details");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ViewPersonalDetails form = new ViewPersonalDetails();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(183, 116, 138, 23);
		contentPane.add(btnNewButton_2);
		
		

		JButton btnNewButton_5 = new JButton("Add Emergency");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddEmergency form = new AddEmergency();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
			
		});
		btnNewButton_5.setBounds(34, 82, 136, 23);
		contentPane.add(btnNewButton_5);
		
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
