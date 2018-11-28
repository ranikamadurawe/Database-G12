package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import au.com.bytecode.opencsv.CSVWriter;
import net.proteanit.sql.DbUtils;

import logindetails.loginpage;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;

public class GenerateReport extends JFrame {

	private JPanel contentPane;
	final Connection c = loginpage.createLoginpage().getConnection();
	ArrayList<String> columnNames = new ArrayList<String>();
	ArrayList<Integer> columnDatatype = new ArrayList<Integer>();
	private JTextField start;
	private JTextField end;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateReport frame = new GenerateReport();
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
	public GenerateReport() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		start = new JTextField();
		start.setBounds(293, 103, 86, 20);
		contentPane.add(start);
		start.setColumns(10);
		
		end = new JTextField();
		end.setBounds(293, 134, 86, 20);
		contentPane.add(end);
		end.setColumns(10);
		
		table = new JTable();
		table.setBounds(31, 145, 1, 1);
		contentPane.add(table);
		
		JButton btnGenerateEmployeeDept = new JButton("Generate Employee Dept Report");
		btnGenerateEmployeeDept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreparedStatement ps = c.prepareStatement
							("select sum(salary), name from (select salary,eid,deptid from (select salary,eid from emppay left join paygrade on epid=pgid) as t1 left join employeedetails using(eid)) as t2 left join department using (deptid)");
					PreparedStatement ps2 = c.prepareStatement("select name, count(leaveid) as totalleaves from (select leaveid,deptid from leavesubmissions left join employeedetails using(eid)) as t1 left join department using(deptid) group by (deptid)");
					PreparedStatement ps3 = c.prepareStatement("select name,type, count(leaveid) as leaves from (select leaveid,deptid,type from leavesubmissions left join employeedetails using(eid)) as t1 left join department using(deptid) group by deptid,type");
					
					String csvfilename = "output.csv";
					CSVWriter writer = new CSVWriter(new FileWriter(csvfilename));
					
					ResultSet r1 = ps.executeQuery();
		            writer.writeAll(r1, true); 
		            
		            r1 = ps2.executeQuery();
		            writer.writeAll(r1, true);
		            
		            
		            r1 = ps3.executeQuery();
		            writer.writeAll(r1, true); 
		            
		            writer.close();
		            JOptionPane.showMessageDialog(null, "CSV File created");
		            
		            ps.close();
		            ps2.close();
		            ps3.close();
		            r1.close();
					
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error " + e);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error " + e);
					e.printStackTrace();
				} 
			}
		});
		btnGenerateEmployeeDept.setBounds(65, 21, 314, 23);
		contentPane.add(btnGenerateEmployeeDept);
		
		JButton btnNewButton = new JButton("Leave Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreparedStatement ps = c.prepareStatement("select * from leaveleft");
					PreparedStatement ps1 = c.prepareStatement("select eid,name,count(leaveid) as leavestaken from leavesubmissions join employeepersonal using(eid) where status='Accepted' group by eid");
					PreparedStatement ps2 = c.prepareStatement("select pgid,casual,annual,nopay,maturity from paygrade");
					PreparedStatement ps3 = c.prepareStatement("select name, count(leaveid) as totalleaves from (select leaveid,deptid from leavesubmissions left join employeedetails using(eid) where leavesubmissions.status='Accepted') as t1 left join department using(deptid) group by (deptid)");
					PreparedStatement ps4 = c.prepareStatement("select name,type, count(leaveid) as leaves from (select leaveid,deptid,type from leavesubmissions left join employeedetails using(eid)) as t1 left join department using(deptid) group by deptid,type");
					PreparedStatement ps5 = c.prepareStatement("select name, count(leaveid) as totalleaves from (select leaveid,deptid from leavesubmissions left join employeedetails using(eid) where leavesubmissions.status='Accepted' and date between ? and ?) as t1 left join department using(deptid) group by (deptid)");
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
					f.setLenient(false);
					String day1 = start.getText();
					java.util.Date utilDate = f.parse(day1);
					Date sqlDate = new Date(utilDate.getTime());
					String day2 = end.getText();
					java.util.Date utilDate2 = f.parse(day1);
					if(utilDate.compareTo(utilDate2) < 0) {
						JOptionPane.showMessageDialog(null, "Dates Invalid");
					}else {
					Date sqlDate2 = new Date(utilDate.getTime());
					ps5.setDate(1, sqlDate);
					ps5.setDate(2, sqlDate);
					

					
					String csvfilename = "output1.csv";
					CSVWriter writer = new CSVWriter(new FileWriter(csvfilename));
					
					ResultSet r1 = ps.executeQuery();
		            writer.writeAll(r1, true); 
		            
		            r1 = ps1.executeQuery();
		            writer.writeAll(r1, true);
		         
		            r1 = ps2.executeQuery();
		            writer.writeAll(r1, true); 
		            
		            r1 = ps3.executeQuery();
		            writer.writeAll(r1, true); 
		            
		            r1 = ps4.executeQuery();
		            writer.writeAll(r1, true); 
		            
		            r1 = ps5.executeQuery();
		            writer.writeAll(r1, true); 
		            
		            writer.close();
		            JOptionPane.showMessageDialog(null, "CSV File created");
		            
		            ps1.close();
		            ps2.close();
		            ps3.close();
		            ps4.close();
		            ps5.close();
		            r1.close();
					
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error " + e);
					e.printStackTrace();
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Error " + e);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error " + e);
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(65, 69, 314, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Employee Report");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreparedStatement p1 = c.prepareStatement("SELECT eid,name,contact FROM empcontact NATURAL join employeepersonal");
					PreparedStatement p2 = c.prepareStatement("SELECT eid,employeepersonal.name,empemergenct.name,phone,empemergenct.email FROM employeepersonal left join empemergenct using(eid) ");
					PreparedStatement p3 = c.prepareStatement("SELECT eid,name,nopay,maturity,annual,casual from employeepersonal natural left join leaveleft ");
					PreparedStatement p4 = c.prepareStatement("SELECT eid,name,salary from emppay left join paygrade on epid=pgid natural left join employeepersonal");
					PreparedStatement p5 = c.prepareStatement("select eid,type,date,enddate,reason,status from leavesubmissions natural left join employeepersonal");
				
					String csvfilename = "output2.csv";
					CSVWriter writer = new CSVWriter(new FileWriter(csvfilename));
					
					ResultSet r1 = p1.executeQuery();
		            writer.writeAll(r1, true); 
		            
		            r1 = p2.executeQuery();
		            writer.writeAll(r1, true);
		         
		            r1 = p3.executeQuery();
		            writer.writeAll(r1, true); 
		            
		            r1 = p4.executeQuery();
		            writer.writeAll(r1, true); 
		            
		            r1 = p5.executeQuery();
		            writer.writeAll(r1, true);
		           
		            p1.close();
		            p2.close();
		            p3.close();
		            p4.close();
		            p5.close();
		            r1.close();
		            
		            writer.close();
		            JOptionPane.showMessageDialog(null, "CSV File created");
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error " + e);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error " + e);
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(65, 181, 314, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Custom Report");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSetMetaData rsmd;
				PreparedStatement p;
				try {
					p = c.prepareStatement("select * from (select name,eid from employeepersonal left join employeedetails using(eid)) as t1 join employeeadditional using(eid)");
					ResultSet rs =p.executeQuery();
					rsmd = rs.getMetaData();
					ResultSet temp;
					String csvfilename = "output3.csv";
					CSVWriter writer = new CSVWriter(new FileWriter(csvfilename));
					System.out.println(rsmd.getColumnCount());
					for(int i=3;i<=rsmd.getColumnCount();i++) {
						p = c.prepareStatement("select eid,"+rsmd.getColumnLabel(i)+" from employeeadditional");
						temp = p.executeQuery();
						writer.writeAll(temp, true); 
					}
					p.close();
					rs.close();
					JOptionPane.showMessageDialog(null, "CSV File created");
					writer.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error " + e);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error " + e);
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(65, 227, 314, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(112, 106, 86, 14);
		contentPane.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(112, 137, 46, 14);
		contentPane.add(lblEndDate);
		

		

	}
}
