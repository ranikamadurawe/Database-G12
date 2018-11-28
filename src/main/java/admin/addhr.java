package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.awt.event.MouseEvent;
import logindetails.loginpage;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addhr extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField nationality;
	private JTextField nic;
	private JTextField eid;
	private JComboBox deptNames;
	private JTextField phone;
	private JTextField email;
	final Connection c = loginpage.createLoginpage().getConnection();
	/**
	 * Launch the application.
	 */
	public String loadlatestid() {
    	try {
    		String findlast = "select eid from employeedetails order by eid desc limit 1";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(findlast);
			rs.next();
			System.out.println(Arrays.toString(rs.getString(1).split("e")));
			int nextid = Integer.parseInt(rs.getString(1).split("e")[1])+1;
			String next;
			System.out.println(nextid);
			if(nextid < 10) {
				 next = "e000"+nextid;
			}else if(nextid < 100) {
				next = "e00"+nextid;
			}else if(nextid < 1000) {
				next = "e0"+nextid;
			}else {
				next = "e"+nextid;
			}
			return next;	
		}catch(SQLException e) {
			
		}
		return null;
    }
	public addhr() {
		
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = new JTextField();
		name.setBounds(308, 61, 86, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		nationality = new JTextField();
		nationality.setBounds(308, 92, 86, 20);
		contentPane.add(nationality);
		nationality.setColumns(10);
		
		nic = new JTextField();
		nic.setBounds(308, 128, 86, 20);
		contentPane.add(nic);
		nic.setColumns(10);
		
		final JComboBox married = new JComboBox();
		married.setModel(new DefaultComboBoxModel(new String[] {"married", "single", "divorced"}));
		married.setBounds(308, 159, 86, 22);
		contentPane.add(married);
		
		final JTextPane address = new JTextPane();
		address.setBounds(212, 198, 191, 46);
		contentPane.add(address);
		
		eid = new JTextField();
		eid.setBounds(308, 30, 86, 20);
		contentPane.add(eid);
		eid.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
	
		btnSubmit.setBounds(114, 479, 89, 23);
		contentPane.add(btnSubmit);
		
		deptNames = new JComboBox();
		deptNames.setBounds(308, 255, 86, 22);
		contentPane.add(deptNames);
		
		JLabel lblEid = new JLabel("eid");
		lblEid.setBounds(97, 33, 46, 14);
		contentPane.add(lblEid);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(97, 64, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblNationality = new JLabel("nationality");
		lblNationality.setBounds(97, 95, 73, 14);
		contentPane.add(lblNationality);
		
		JLabel lblNic = new JLabel("nic");
		lblNic.setBounds(97, 131, 46, 14);
		contentPane.add(lblNic);
		
		JLabel lblMaritialStatus = new JLabel("maritial status");
		lblMaritialStatus.setBounds(97, 163, 73, 14);
		contentPane.add(lblMaritialStatus);
		
		JLabel lblAddress = new JLabel("address");
		lblAddress.setBounds(97, 197, 46, 14);
		contentPane.add(lblAddress);
		
		JLabel lblDept = new JLabel("dept");
		lblDept.setBounds(97, 259, 46, 14);
		contentPane.add(lblDept);
		
		phone = new JTextField();
		phone.setBounds(308, 298, 86, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel lblPhone = new JLabel("phone");
		lblPhone.setBounds(97, 301, 46, 14);
		contentPane.add(lblPhone);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(97, 339, 46, 14);
		contentPane.add(lblEmail);
		
		email = new JTextField();
		email.setBounds(308, 336, 86, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		final JSpinner year = new JSpinner();
		year.setBounds(97, 445, 66, 20);
		contentPane.add(year);
		
		final JSpinner month = new JSpinner();
		month.setBounds(229, 445, 46, 20);
		contentPane.add(month);
		
		final JSpinner date = new JSpinner();
		date.setBounds(341, 445, 53, 20);
		contentPane.add(date);
		
		JLabel lblNewLabel = new JLabel("Year");
		lblNewLabel.setBounds(53, 448, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Month");
		lblNewLabel_1.setBounds(173, 445, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date");
		lblNewLabel_2.setBounds(285, 448, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		final JSpinner epfspin = new JSpinner();
		epfspin.setBounds(308, 378, 30, 20);
		contentPane.add(epfspin);
		
		JLabel lblEpfPercentage = new JLabel("epf percentage");
		lblEpfPercentage.setBounds(97, 381, 108, 14);
		contentPane.add(lblEpfPercentage);
		
		
		
		try {
			String querydept = "select deptid,name from department";
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(querydept);
			while(rs.next()) {
				deptNames.addItem(rs.getString(2));
			}
			
			
			eid.setText(loadlatestid());
			
			JButton btnLoadLatest = new JButton("Load latest");
			btnLoadLatest.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					eid.setText(loadlatestid());
				}
			});
			btnLoadLatest.setBounds(230, 479, 89, 23);
			contentPane.add(btnLoadLatest);
			
			JLabel lblBirthday = new JLabel("Birthday");
			lblBirthday.setBounds(43, 415, 46, 14);
			contentPane.add(lblBirthday);
		}catch(Exception e) {
			
		}
		
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				boolean added = false;
				try {
					System.out.println(deptNames.getSelectedItem().toString());
					PreparedStatement ps = c.prepareStatement("select deptid from department where name = ?");
					ps.setString(1, deptNames.getSelectedItem().toString());
					ResultSet rs = ps.executeQuery();
		
					while(rs.next()) {
						String deptid = rs.getString(1);
						c.setAutoCommit(false);
						PreparedStatement p1 = c.prepareStatement("insert into employeedetails(eid,deptid,status,title) values (?,?,?,?)");
						p1.setString(1,eid.getText());
						p1.setString(2,deptid);
						p1.setString(3,"permanent");
						p1.setString(4,"hr");
						
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						format.setLenient(false);
						String chosenbday = (Integer)year.getValue()+"-"+(Integer)month.getValue()+"-"+(Integer)date.getValue();
						java.util.Date utilDate = format.parse(chosenbday);
						Date sqlDate = new Date(utilDate.getTime());
						
						PreparedStatement p2 = c.prepareStatement("insert into employeepersonal(eid,name,nic,nationality,maritalstatus,address,email,birthdate) values (?,?,?,?,?,?,?,?)");
						p2.setString(1,eid.getText());
						p2.setString(2,name.getText());
						p2.setString(3,nic.getText());
						p2.setString(4,nationality.getText());
						p2.setString(5,married.getSelectedItem().toString());
						p2.setString(6,address.getText());
						p2.setString(7,email.getText());
						p2.setDate(8, sqlDate);
						
						PreparedStatement p3 = c.prepareStatement("insert into empcontact(eid,contact) values(?,?)");
						p3.setString(1, eid.getText());
						p3.setString(2, phone.getText());
						
						PreparedStatement p4 = c.prepareStatement("insert into emppay(eid,epid,epf) values(?,?,?)");
						p4.setString(1, eid.getText());
						p4.setString(2, "pg0001");
						p4.setInt(3, (Integer)epfspin.getValue());
						
						PreparedStatement p5 = c.prepareStatement("insert into employeeadditional(eid) values(?)");
						p5.setString(1, eid.getText());
						
						
						
						
						p1.executeUpdate();
						p2.executeUpdate();
						p3.executeUpdate();
						p4.executeUpdate();
						p5.executeUpdate();
						PreparedStatement p6 = c.prepareStatement("select maturity,nopay,casual,annual from paygrade where pgid = 'pg0001'");
						
						ResultSet rs1 = p6.executeQuery();
						rs1.next();
						int maturity = rs1.getInt(1);
						int nopay = rs1.getInt(2);
						int casual = rs1.getInt(3);
						int annual = rs1.getInt(4);
						PreparedStatement p7 = c.prepareStatement("insert into leaveleft(eid,annual,casual,maturity,nopay) values(?,?,?,?,?)");
						p7.setString(1, eid.getText());
						p7.setInt(2, annual);
						p7.setInt(3, casual);
						p7.setInt(4, maturity);
						p7.setInt(5, nopay);
						p7.executeUpdate();
						c.commit();
						added = true;
						JOptionPane.showMessageDialog(null, "Add Successful");
						setVisible(false);
						ps.close();
						rs.close();
						p1.close();
						p2.close();
						p3.close();
						p4.close();
						p5.close();
						p6.close();
						p7.close();
					}
				}catch(Exception e) {
					try {
						c.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(!added) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					
				}
			}
		});
		
	}
}
 