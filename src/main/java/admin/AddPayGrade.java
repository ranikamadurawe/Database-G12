package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logindetails.loginpage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPayGrade extends JFrame {

	private JPanel contentPane;
	private JTextField pgid;
	final Connection c = loginpage.createLoginpage().getConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPayGrade frame = new AddPayGrade();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String loadlatestid() {
    	try {
    		String findlast = "select pgid from paygrade order by pgid desc limit 1";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(findlast);
			rs.next();
			System.out.println(Arrays.toString(rs.getString(1).split("pg")));
			int nextid = Integer.parseInt(rs.getString(1).split("pg")[1])+1;
			String next;
			System.out.println(nextid);
			if(nextid < 10) {
				 next = "pg000"+nextid;
			}else if(nextid < 100) {
				next = "pg00"+nextid;
			}else if(nextid < 1000) {
				next = "pg0"+nextid;
			}else {
				next = "pg"+nextid;
			}
			return next;	
		}catch(SQLException e) {
			
		}
		return null;
    }
	public AddPayGrade() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPaygradeId = new JLabel("Paygrade ID");
		lblPaygradeId.setBounds(100, 46, 70, 14);
		contentPane.add(lblPaygradeId);
		
		JLabel lblNewLabel = new JLabel("Salary");
		lblNewLabel.setBounds(100, 71, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLeaveData = new JLabel("Leave Data");
		lblLeaveData.setBounds(35, 101, 86, 14);
		contentPane.add(lblLeaveData);
		
		JLabel lblCasual = new JLabel("Casual");
		lblCasual.setBounds(100, 133, 46, 14);
		contentPane.add(lblCasual);
		
		JLabel lblMaternity = new JLabel("Maternity");
		lblMaternity.setBounds(100, 158, 46, 14);
		contentPane.add(lblMaternity);
		
		JLabel lblNoPay = new JLabel("No pay");
		lblNoPay.setBounds(100, 189, 46, 14);
		contentPane.add(lblNoPay);
		
		JLabel lblAnnual = new JLabel("Annual");
		lblAnnual.setBounds(100, 216, 46, 14);
		contentPane.add(lblAnnual);
		
		final JSpinner moneywheel = new JSpinner();
		moneywheel.setBounds(262, 68, 98, 20);
		contentPane.add(moneywheel);
		
		final JSpinner cents = new JSpinner();
		cents.setBounds(370, 68, 30, 20);
		contentPane.add(cents);
		
		final JSpinner casual = new JSpinner();
		casual.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		casual.setBounds(262, 130, 46, 20);
		contentPane.add(casual);
		
		final JSpinner maternity = new JSpinner();
		maternity.setModel(new SpinnerNumberModel(50, 50, 50, 1));
		maternity.setBounds(262, 155, 46, 20);
		contentPane.add(maternity);
		
		final JSpinner nopay = new JSpinner();
		nopay.setBounds(262, 186, 46, 20);
		contentPane.add(nopay);
		
		final JSpinner annual = new JSpinner();
		annual.setBounds(262, 213, 46, 20);
		contentPane.add(annual);
		
		pgid = new JTextField();
		pgid.setBounds(262, 43, 98, 20);
		contentPane.add(pgid);
		pgid.setColumns(10);
		
		
			pgid.setText(loadlatestid());	
		
		
		JButton btnCreate = new JButton("Create ");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					String moneystring = (Integer)moneywheel.getValue()+"."+(Integer)cents.getValue();
					BigDecimal money = new BigDecimal(moneystring);
					PreparedStatement p = c.prepareStatement("insert into paygrade(pgid,casual,nopay,annual,maturity,salary) values (?,?,?,?,?,?) on duplicate key update casual=VALUES(casual),nopay=VALUES(nopay),annual=VALUES(annual),maturity=VALUES(maturity),salary=VALUES(salary)");
					p.setString(1, pgid.getText());
					p.setBigDecimal(6, money);
					p.setInt(2, (Integer)casual.getValue());
					p.setInt(3,  (Integer)nopay.getValue());
					p.setInt(4,  (Integer)annual.getValue());
					p.setInt(5,  (Integer)maternity.getValue());
					
					
					PreparedStatement query = c.prepareStatement("select * from paygrade where pgid = ?");
					query.setString(1, pgid.getText());
					
					ResultSet rs = query.executeQuery();
					if (rs.next()) {
						int reply = JOptionPane.showConfirmDialog(null,"Confirm", "Going to update an existing record confirm?",JOptionPane.YES_NO_OPTION);
						if(reply== JOptionPane.YES_OPTION) {
							p.executeUpdate();
							JOptionPane.showMessageDialog(null, "Add Successful");
						}else {
							
						}
					}else {
						p.executeUpdate();
						JOptionPane.showMessageDialog(null, "Add Successful");
						setVisible(false);
					}
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error when adding data"+e);
					e.printStackTrace();
				}
			}
		});
		btnCreate.setBounds(335, 189, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnLoadLatestId = new JButton("Load latest id");
		btnLoadLatestId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pgid.setText(loadlatestid());	
			}
		});
		btnLoadLatestId.setBounds(335, 227, 89, 23);
		contentPane.add(btnLoadLatestId);
	}
}
