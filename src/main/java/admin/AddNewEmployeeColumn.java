package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logindetails.loginpage;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddNewEmployeeColumn extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnAdd;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewEmployeeColumn frame = new AddNewEmployeeColumn();
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
	public AddNewEmployeeColumn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ColumnName");
		lblNewLabel.setBounds(82, 52, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(82, 142, 46, 14);
		contentPane.add(lblType);
		
		textField = new JTextField();
		textField.setBounds(231, 49, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Text", "Number", "Date", "Phone", "Email"}));
		comboBox.setBounds(231, 138, 89, 22);
		contentPane.add(comboBox);
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final Connection c = loginpage.createLoginpage().getConnection();
				try {
					PreparedStatement ps;
					if(comboBox.getSelectedIndex()==0) {
							System.out.println("alter table 'employeeadditional' add '"+textField.getText()+"' VARCHAR(255)");
						 ps = c.prepareStatement("alter table `employeeadditional` add `"+textField.getText()+"` VARCHAR(255)");
						 
					}else if (comboBox.getSelectedIndex()==1) {
						ps = c.prepareStatement("alter table `employeeadditional` add `"+textField.getText()+"` INT");
					}else if (comboBox.getSelectedIndex()==2) {
						ps = c.prepareStatement("alter table `employeeadditional` add `"+textField.getText()+"` DATE");
					}else if (comboBox.getSelectedIndex()==3) {
						ps = c.prepareStatement("alter table `employeeadditional` add `"+textField.getText()+"` VARCHAR(10)");
					}else {
						ps = c.prepareStatement("alter table `employeeadditional` add `"+textField.getText()+"` VARCHAR(50)");
					}
					ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAdd.setBounds(143, 203, 89, 23);
		contentPane.add(btnAdd);
	}

}
