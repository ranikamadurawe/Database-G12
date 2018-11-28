package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logindetails.loginpage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddNewLocation extends JFrame {

	private JPanel contentPane;
	private JTextField branchname;
	private JTextField branchid;
	private JTextField address;
	final Connection c = loginpage.createLoginpage().getConnection();
	private JButton btnLoadLatest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewLocation frame = new AddNewLocation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String loadlatestid() {
    	try {
    		String findlast = "select branchid from branch order by branchid desc limit 1";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(findlast);
			rs.next();
			System.out.println(Arrays.toString(rs.getString(1).split("b")));
			int nextid = Integer.parseInt(rs.getString(1).split("b")[1])+1;
			String next;
			System.out.println(nextid);
			if(nextid < 10) {
				 next = "b000"+nextid;
			}else if(nextid < 100) {
				next = "b00"+nextid;
			}else if(nextid < 1000) {
				next = "b0"+nextid;
			}else {
				next = "b"+nextid;
			}
			rs.close();
			return next;	
		}catch(SQLException e) {
			
		}
		return null;
    }
	public AddNewLocation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBranchName = new JLabel("Branch Name");
		lblBranchName.setBounds(81, 57, 70, 14);
		contentPane.add(lblBranchName);
		
		JLabel lblBranchId = new JLabel("Branch ID");
		lblBranchId.setBounds(81, 82, 70, 14);
		contentPane.add(lblBranchId);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(81, 107, 46, 14);
		contentPane.add(lblAddress);
		
		branchname = new JTextField();
		branchname.setBounds(230, 54, 86, 20);
		contentPane.add(branchname);
		branchname.setColumns(10);
		
		branchid = new JTextField();
		branchid.setBounds(230, 79, 86, 20);
		contentPane.add(branchid);
		branchid.setColumns(10);
		
		address = new JTextField();
		address.setBounds(230, 104, 86, 20);
		contentPane.add(address);
		address.setColumns(10);
		
		
			branchid.setText(loadlatestid());	
		
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PreparedStatement ps;
				try {
					ps = c.prepareStatement("insert into branch(branchid,address,name) values (?,?,?)");
					ps.setString(1, branchid.getText());
					ps.setString(2, address.getText());
					ps.setString(3, branchname.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Added New Location");
					setVisible(false);
					ps.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error"+e);
					e.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(145, 173, 89, 23);
		contentPane.add(btnAdd);
		
		btnLoadLatest = new JButton("Load Latest");
		btnLoadLatest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				branchid.setText(loadlatestid());	
			}
		});
		btnLoadLatest.setBounds(145, 210, 89, 23);
		contentPane.add(btnLoadLatest);
	}

}
