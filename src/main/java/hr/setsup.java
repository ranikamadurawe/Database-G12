package hr;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import javax.swing.JOptionPane;

import logindetails.loginpage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class setsup {

	protected Shell shlSetSupervisors;
	Connection c;
	private Table table;
	
	public setsup() {

		
		shlSetSupervisors = new Shell();
		shlSetSupervisors.setSize(450, 300);
		shlSetSupervisors.setText("Set Supervisors");
		
		table = new Table(shlSetSupervisors, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 60, 414, 157);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn clmEmployee = new TableColumn(table, SWT.LEFT);
		clmEmployee.setWidth(80);
		clmEmployee.setText("Employee ID");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(165);
		tblclmnNewColumn.setText("Employee Name");

		TableColumn clmSupervisor = new TableColumn(table, SWT.LEFT);
		clmSupervisor.setWidth(165);
		clmSupervisor.setText("Supervisor");
		
		Label lblSetSupervisors = new Label(shlSetSupervisors, SWT.NONE);
		lblSetSupervisors.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblSetSupervisors.setAlignment(SWT.CENTER);
		lblSetSupervisors.setBounds(10, 10, 414, 32);
		lblSetSupervisors.setText("Set Supervisors");
		
		c = loginpage.createLoginpage().getConnection();
		System.out.println(c);
		
		/*try {
			c =( Connection)DriverManager.getConnection("jdbc:mysql://localhost/welfare", "root", "");
		}catch(Exception e) {
			
		}*/
		
		Button btnCancel = new Button(shlSetSupervisors, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				dispose();
//	            new Gui1().setVisible(true);
			}
		});
		btnCancel.setBounds(50, 226, 75, 25);
		btnCancel.setText("Cancel");
		
		Button btnSave = new Button(shlSetSupervisors, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {			//click on save button
				TableItem[] tableItems = table.getItems();
				String query = "";
				boolean failed = false;
				try {
					PreparedStatement p = c.prepareStatement("update employeedetails set supervisorid=? where eid=?");
					for(int i=0; i<tableItems.length; i++) {
						if( ((Combo)tableItems[i].getData("selection")).getText() != "" ) {
							String selection = ((Combo)tableItems[i].getData("selection")).getText().split(":")[0];
							p.setString(1,selection.trim());
							p.setString(2,tableItems[i].getText(0));
							p.executeUpdate();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					failed = true;
					System.out.println("Update query failed :"+e1);
					JOptionPane.showMessageDialog(null, "Updation Failed !!!");
				}
				
				
				if(!failed) {
					JOptionPane.showMessageDialog(null, "Updation Successful !");
				}

				if( query != "" ) {

				}else{
					JOptionPane.showMessageDialog(null, "Supervisor fields are empty !!!");
				}
			}
		});
		btnSave.setBounds(304, 226, 75, 25);
		btnSave.setText("Save");
		

		ArrayList<String> userlist = new ArrayList<String>();
		
		try{
			String query1 = "select eid,name,supervisorid from employeedetails natural join employeepersonal";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query1);
			
			while( rs.next() ) {
				TableItem ti = new TableItem(table, SWT.NONE);
				ti.setText( 0, rs.getString(1) );
				ti.setText( 1, rs.getString(2) );
				userlist.add( rs.getString(1) + ":" + rs.getString(2) + ":" + rs.getString(3) );
			}
			TableItem[] items = table.getItems();
			
			for( int i=0; i<userlist.size(); i++ ) {
				TableEditor tableEditor = new TableEditor(table);
			    Combo combo = new Combo(table, SWT.NONE);
			    String[] parts = userlist.get(i).split(":");

			    if( !parts[2].equals("null") ) {				//set supervisor if not null
			    	combo.setText(parts[2]);
			    }

			    for(int j=0; j<userlist.size(); j++) {			//add employees to the combo box
			    	if( !parts[0].equals( (userlist.get(j).split(":"))[0] ) ) {		//not to add same user
			    		combo.add( userlist.get(j).split(":")[0] + " : " + userlist.get(j).split(":")[1] );
			    	}
			    }
		        tableEditor.grabHorizontal = true;
		        tableEditor.setEditor(combo, items[i], 2);
		        items[i].setData("selection", combo);
			}
			
			
		}catch(Exception e){
			System.out.println("Getting data failed :"+e);
			JOptionPane.showMessageDialog(null, "Error occured while getting data !!!");
		}
		
		
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			setsup window = new setsup();
			window.open();
		} catch (Exception e) {
			
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		shlSetSupervisors.open();
		shlSetSupervisors.layout();
		while (!shlSetSupervisors.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	
}
