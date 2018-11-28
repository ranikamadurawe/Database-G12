package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logindetails.loginpage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddDept extends JFrame {

	private JPanel contentPane;
	private JTextField deptname;
	private JTextField deptID;
	private JTextField building;
	final Connection c = loginpage.createLoginpage().getConnection();

	/**
	 * Launch the application.
	 */
	
    public String loadlatestid() {
    	try {
			String findlast = "select deptid from department order by deptid desc limit 1";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(findlast);
			rs.next();
			System.out.println(Arrays.toString(rs.getString(1).split("d")));
			int nextid = Integer.parseInt(rs.getString(1).split("d")[1])+1;
			String next;
			System.out.println(nextid);
			if(nextid < 10) {
				 next = "d000"+nextid;
			}else if(nextid < 100) {
				next = "d00"+nextid;
			}else if(nextid < 1000) {
				next = "d0"+nextid;
			}else {
				next = "d"+nextid;
			}
			rs.close();
			return next;
			
		}catch(SQLException e) {
			
		}
		return null;
    }
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDept frame = new AddDept();
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
	public AddDept() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeptName = new JLabel("Dept. Name");
		lblDeptName.setBounds(55, 57, 103, 14);
		contentPane.add(lblDeptName);
		
		JLabel lblLocationName = new JLabel("Location Name");
		lblLocationName.setBounds(55, 96, 103, 14);
		contentPane.add(lblLocationName);
		
		JLabel lblDepartmentId = new JLabel("Department ID");
		lblDepartmentId.setBounds(55, 142, 138, 14);
		contentPane.add(lblDepartmentId);
		

		
		deptname = new JTextField();
		deptname.setBounds(226, 54, 86, 20);
		contentPane.add(deptname);
		deptname.setColumns(10);
		
		deptID = new JTextField();
		deptID.setBounds(226, 139, 86, 20);
		contentPane.add(deptID);
		deptID.setColumns(10);
		
		final JComboBox Locations = new JComboBox();
		Locations.setBounds(226, 92, 86, 22);
		contentPane.add(Locations);
		
	
		deptID.setText(loadlatestid());	
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				PreparedStatement ps;
				try {
					ps = c.prepareStatement("select branchid from branch where name = ?");
					ps.setString(1, Locations.getSelectedItem().toString());
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
						String branchid = rs.getString(1);
						PreparedStatement p1 = c.prepareStatement("insert into department(branchid,deptid,building,name) values (?,?,?,?)");
						p1.setString(1,branchid);
						p1.setString(2,deptID.getText());
						p1.setString(3,building.getText());
						p1.setString(4,deptname.getText());
						p1.executeUpdate();
						p1.close();
						JOptionPane.showMessageDialog(null, "Add Successful");
						setVisible(false);
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error"+e);
					e.printStackTrace();
				}
				
			}
		});
		btnSubmit.setBounds(80, 212, 89, 23);
		contentPane.add(btnSubmit);
		
		building = new JTextField();
		building.setBounds(226, 170, 86, 20);
		contentPane.add(building);
		building.setColumns(10);
		
		JLabel lblBuilding = new JLabel("Building");
		lblBuilding.setBounds(55, 173, 46, 14);
		contentPane.add(lblBuilding);
		
		JButton btnLoadLastId = new JButton("Load last id");
		btnLoadLastId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deptID.setText(loadlatestid());	
			}
		});
		btnLoadLastId.setBounds(192, 212, 89, 23);
		contentPane.add(btnLoadLastId);
		
		try {
			String querydept = "select branchid,name from branch";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(querydept);
			while(rs.next()) {
				Locations.addItem(rs.getString(2));
			}
		}catch(Exception e) {
			
		}
	}
}
