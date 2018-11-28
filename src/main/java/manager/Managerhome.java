package manager;

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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Managerhome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Managerhome frame = new Managerhome();
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
	public Managerhome() {
		if ( loginpage.getrole().equals("employee")) {
			loginpage.createLoginpage().conn=null;
			try {
				loginpage.createLoginpage().conn = DriverManager.getConnection("jdbc:mysql://localhost/welfare", "manager", "man");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Add Additional");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddAdditional form = new AddAdditional();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(116, 109, 138, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Accept Leaves");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AcceptLeaves form = new AcceptLeaves();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_3.setBounds(34, 48, 136, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Update Employee");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				UpdateEmployee form = new UpdateEmployee();
				form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(185, 48, 136, 23);
		contentPane.add(btnNewButton_4);
	}
}
