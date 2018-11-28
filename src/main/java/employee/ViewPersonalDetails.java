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
		setBounds(100, 100, 449, 487);
		variable = new JPanel();
		variable.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(variable);
		variable.setLayout(null);
		
		JLabel eid = new JLabel("New label");
		eid.setBounds(169, 25, 204, 14);
		variable.add(eid);
		
		JLabel employeename = new JLabel("New label");
		employeename.setBounds(169, 50, 204, 14);
		variable.add(employeename);
		
		JLabel nic = new JLabel("New label");
		nic.setBounds(169, 75, 204, 14);
		variable.add(nic);
		
		JLabel nationality = new JLabel("New label");
		nationality.setBounds(169, 100, 204, 14);
		variable.add(nationality);
		
		JLabel maritalstatus = new JLabel("New label");
		maritalstatus.setBounds(169, 125, 204, 14);
		variable.add(maritalstatus);
		
		JLabel birthdate = new JLabel("New label");
		birthdate.setBounds(169, 150, 204, 14);
		variable.add(birthdate);
		
		JLabel address = new JLabel("New label");
		address.setBounds(169, 175, 204, 14);
		variable.add(address);
		
		JLabel email = new JLabel("New label");
		email.setBounds(169, 200, 204, 14);
		variable.add(email);
		
		JLabel status = new JLabel("New label");
		status.setBounds(169, 275, 212, 14);
		variable.add(status);
		
		JLabel title = new JLabel("New label");
		title.setBounds(169, 325, 204, 14);
		variable.add(title);
		
		JLabel supname = new JLabel("New label");
		supname.setBounds(169, 300, 204, 14);
		variable.add(supname);
		
		JLabel supid = new JLabel("New label");
		supid.setBounds(169, 375, 204, 14);
		variable.add(supid);
		
		JLabel deptname = new JLabel("New label");
		deptname.setBounds(169, 250, 204, 14);
		variable.add(deptname);
		
		JLabel building = new JLabel("New label");
		building.setBounds(169, 350, 204, 14);
		variable.add(building);
		
		JLabel officename = new JLabel("New label");
		officename.setBounds(169, 225, 204, 14);
		variable.add(officename);
		
		JLabel officeaddress = new JLabel("New label");
		officeaddress.setBounds(169, 400, 212, 14);
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
				
				JLabel lblEid = new JLabel("EID");
				lblEid.setBounds(29, 25, 46, 14);
				variable.add(lblEid);
				
				JLabel lblEmployeeName = new JLabel("Employee Name");
				lblEmployeeName.setBounds(29, 50, 82, 14);
				variable.add(lblEmployeeName);
				
				JLabel lblNic = new JLabel("NIC");
				lblNic.setBounds(29, 75, 46, 14);
				variable.add(lblNic);
				
				JLabel lblNationality = new JLabel("Nationality");
				lblNationality.setBounds(29, 100, 67, 14);
				variable.add(lblNationality);
				
				JLabel lblNewLabel = new JLabel("Marital status");
				lblNewLabel.setBounds(29, 125, 71, 14);
				variable.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Birthday");
				lblNewLabel_1.setBounds(29, 150, 46, 14);
				variable.add(lblNewLabel_1);
				
				JLabel lblAddress = new JLabel("Address");
				lblAddress.setBounds(29, 175, 46, 14);
				variable.add(lblAddress);
				
				JLabel lblEmail = new JLabel("Email");
				lblEmail.setBounds(29, 200, 46, 14);
				variable.add(lblEmail);
				
				JLabel lblOfficeName = new JLabel("Office name");
				lblOfficeName.setBounds(29, 225, 67, 14);
				variable.add(lblOfficeName);
				
				JLabel lblDepartmentName = new JLabel("Department name");
				lblDepartmentName.setBounds(29, 250, 93, 14);
				variable.add(lblDepartmentName);
				
				JLabel lblStatus = new JLabel("Status");
				lblStatus.setBounds(29, 275, 46, 14);
				variable.add(lblStatus);
				
				JLabel lblSupervisorName = new JLabel("Supervisor name");
				lblSupervisorName.setBounds(31, 300, 80, 14);
				variable.add(lblSupervisorName);
				
				JLabel lblTitle = new JLabel("Title");
				lblTitle.setBounds(29, 325, 46, 14);
				variable.add(lblTitle);
				
				JLabel lblBuilding = new JLabel("Building");
				lblBuilding.setBounds(29, 350, 46, 14);
				variable.add(lblBuilding);
				
				JLabel lblSupervisorId = new JLabel("Supervisor ID");
				lblSupervisorId.setBounds(29, 375, 82, 14);
				variable.add(lblSupervisorId);
				
				JLabel lblOfficeAddress = new JLabel("Office address");
				lblOfficeAddress.setBounds(31, 400, 80, 14);
				variable.add(lblOfficeAddress);
				
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
