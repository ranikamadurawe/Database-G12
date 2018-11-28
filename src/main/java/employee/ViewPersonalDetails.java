package employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;

import logindetails.loginpage;
import javax.swing.JLabel;

public class ViewPersonalDetails extends JFrame {

	private JPanel variable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPersonalDetails frame = new ViewPersonalDetails();
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
	public ViewPersonalDetails() {
		final Connection c = loginpage.createLoginpage().getConnection();
        
       
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		variable = new JPanel();
		variable.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(variable);
		variable.setLayout(null);
		
		JLabel eid = new JLabel("New label");
		eid.setBounds(139, 25, 46, 14);
		variable.add(eid);
		
		JLabel employeename = new JLabel("New label");
		employeename.setBounds(139, 52, 46, 14);
		variable.add(employeename);
		
		JLabel nic = new JLabel("New label");
		nic.setBounds(139, 77, 46, 14);
		variable.add(nic);
		
		JLabel nationality = new JLabel("New label");
		nationality.setBounds(139, 103, 46, 14);
		variable.add(nationality);
		
		JLabel maritalstatus = new JLabel("New label");
		maritalstatus.setBounds(139, 128, 46, 14);
		variable.add(maritalstatus);
		
		JLabel birthdate = new JLabel("New label");
		birthdate.setBounds(139, 156, 46, 14);
		variable.add(birthdate);
		
		JLabel address = new JLabel("New label");
		address.setBounds(125, 184, 34, 7);
		variable.add(address);
		
		JLabel email = new JLabel("New label");
		email.setBounds(139, 200, 46, 14);
		variable.add(email);
		
		JLabel status = new JLabel("New label");
		status.setBounds(295, 25, 46, 14);
		variable.add(status);
		
		JLabel title = new JLabel("New label");
		title.setBounds(295, 52, 46, 14);
		variable.add(title);
		
		JLabel supname = new JLabel("New label");
		supname.setBounds(295, 89, 46, 14);
		variable.add(supname);
		
		JLabel supid = new JLabel("New label");
		supid.setBounds(295, 128, 46, 14);
		variable.add(supid);
		
		JLabel deptname = new JLabel("New label");
		deptname.setBounds(295, 156, 46, 14);
		variable.add(deptname);
		
		JLabel building = new JLabel("New label");
		building.setBounds(295, 184, 46, 14);
		variable.add(building);
		
		JLabel officename = new JLabel("New label");
		officename.setBounds(295, 216, 46, 14);
		variable.add(officename);
		
		JLabel officeaddress = new JLabel("New label");
		officeaddress.setBounds(295, 236, 46, 14);
		variable.add(officeaddress);
		
		 try {
				PreparedStatement p = c.prepareStatement("select * from (select x.*, y.name as deptname,building,branchid from (select t.eid as employeeid,t.name as employeename,t.nic,t.nationality,t.maritalstatus,t.birthdate,t.address,t.email,t.status,t.deptid,t.title,s.name,s.eid from (SELECT * FROM employeepersonal natural join employeedetails where eid=?) as t left join (SELECT * FROM employeepersonal natural join employeedetails) as s on (t.supervisorid=s.eid)) as x join department as y using(deptid)) as k join branch as l using(branchid) ");
				p.setString(1, loginpage.geteid());
				ResultSet r = p.executeQuery();
				r.next();
				eid.setText(r.getString(2));
				employeename.setText(r.getString(3));
				nic.setText(r.getString(4));
				nationality.setText(r.getString(5));
				maritalstatus.setText(r.getString(6));
				birthdate.setText(r.getString(7));
				address.setText(r.getString(8));
				email.setText(r.getString(9));
				status.setText(r.getString(10));
				title.setText(r.getString(12));
				supname.setText(r.getString(13));
				supid.setText(r.getString(14));
				deptname.setText(r.getString(15));
				building.setText(r.getString(16));
				officename.setText(r.getString(17));
				officeaddress.setText(r.getString(18));
				
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
