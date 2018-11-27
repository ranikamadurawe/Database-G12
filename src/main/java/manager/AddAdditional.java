package manager;

import java.awt.BorderLayout;
import net.proteanit.sql.DbUtils;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.TableItem;

import java.sql.ResultSetMetaData;

import logindetails.loginpage;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class AddAdditional extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	ArrayList<String> columnNames = new ArrayList<String>();
	ArrayList<Integer> columnDatatype = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAdditional frame = new AddAdditional();
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
	public AddAdditional() {
		final Connection c = loginpage.createLoginpage().getConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ResultSetMetaData rsmd;
		
		try {
			PreparedStatement p = c.prepareStatement("select * from (select name,eid from employeepersonal left join employeedetails using(eid) where supervisorid = ?) as t1 join employeeadditional using(eid)");
			if(loginpage.getrole().equals("admin")) {
				p = c.prepareStatement("select * from (select name,eid from employeepersonal left join employeedetails using(eid) where supervisorid = ? or supervisorid is null) as t1 join employeeadditional using(eid)");
			}		
			p.setString(1, loginpage.geteid());
			System.out.println("ds");
			ResultSet rs =p.executeQuery();
			rsmd = rs.getMetaData();
			
			for(int i=3;i<=rsmd.getColumnCount();i++) {
				columnNames.add(rsmd.getColumnLabel(i));
				columnDatatype.add(rsmd.getColumnType(i));
			}
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 30, 402, 175);
			contentPane.add(scrollPane);
			
			table_1 = new JTable();
			scrollPane.setViewportView(table_1);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String insertcolumns= " "+columnNames.get(0)+"= ? ";
				String insertvalues="";
				if(columnNames.isEmpty()) {
					
				}else {
					for(int i=1;i<columnNames.size();i++) {
						insertcolumns += ", "+columnNames.get(i)+"= ? ";
					}
					String query = "update employeeadditional set "+insertcolumns+" where eid = ?";
					System.out.println(query);
					System.out.println(columnDatatype.get(0)+" "+columnDatatype.get(1) );
					try {
						PreparedStatement ps = c.prepareStatement(query);
						for(int j=1; j<=table_1.getRowCount();j++) {
							int i =0;
							boolean dataerror = false;
							for(;i<columnNames.size();i++) {
								System.out.println(columnNames.size());
								if(table_1.getModel().getValueAt(j-1, i+2) == null) {
									JOptionPane.showMessageDialog(null, "Enter a value for "+columnNames.get(i));
									dataerror = true;
								}else {
									if(columnDatatype.get(i)==12) {
										ps.setString(i+1, (String)table_1.getModel().getValueAt(j-1, i+2));
									}else if(columnDatatype.get(i)==4) {
											System.out.println(table_1.getModel().getValueAt(j-1, i+2));
											ps.setInt(i+1, Integer.valueOf(table_1.getModel().getValueAt(j-1, i+2).toString()));	
									}else if(columnDatatype.get(i)==91) {
										String chosenbday = (String)table_1.getModel().getValueAt(j-1, i+2);
										java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(chosenbday);
										Date sqlDate = new Date(utilDate.getTime());
										ps.setDate(i+1, sqlDate);
									}else if(columnDatatype.get(i)==3) {
										BigDecimal money = new BigDecimal((String)table_1.getModel().getValueAt(j-1, i+2));
										ps.setBigDecimal(i+1, money);
									}else {
										ps.setString(i+1, (String)table_1.getModel().getValueAt(j-1, i+2));
									}
								}
								
							}
							if(!dataerror) {
								ps.setString(i+1,  (String)table_1.getModel().getValueAt(j-1, 0));
								ps.executeUpdate();
							}
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Error "+e);
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				
				
			}
		});
		btnUpdate.setBounds(166, 227, 89, 23);
		contentPane.add(btnUpdate);
		
	}
}
