import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Home extends JFrame {
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	void disp(String n)
	{
		
		try
		{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/pb?user=root&password=root");
String query="select * from contact where name like '"+n+"%'";
PreparedStatement statement=connection.prepareStatement(query);
ResultSet result=statement.executeQuery();
java.sql.ResultSetMetaData rsmd=   result.getMetaData();
DefaultTableModel model=new DefaultTableModel();
for(int i=1;i<=rsmd.getColumnCount();i++)
{
	
	model.addColumn(rsmd.getColumnName(i));
}

while(result.next())
{
	String row[]=new String[rsmd.getColumnCount()];
	for(int i=1;i<=rsmd.getColumnCount();i++)
	{
		row[i-1]=result.getString(i);
	}
	model.addRow(row);
}
table.setModel(model);
connection.close();

		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

		void disp()
		{
			try
			{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/pb?user=root&password=root");
	String query="select * from contact";
	PreparedStatement statement=connection.prepareStatement(query);
	ResultSet result=statement.executeQuery();

	java.sql.ResultSetMetaData rsmd=   result.getMetaData();
	DefaultTableModel model=new DefaultTableModel();
	for(int i=1;i<=rsmd.getColumnCount();i++)
	{
		
		model.addColumn(rsmd.getColumnName(i));
	}

	while(result.next())
	{
		String row[]=new String[rsmd.getColumnCount()];
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
			row[i-1]=result.getString(i);
		}
		model.addRow(row);
	}
	table.setModel(model);
	connection.close();

			}

			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		}
		

	public Home() {
		getContentPane().setBackground(new Color(144, 238, 144));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Contacts");
		lblNewLabel.setBackground(new Color(238, 130, 238));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(170, 11, 222, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 88, 360, 276);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
	
		
		table = new JTable();
		table.setBackground(new Color(250, 235, 215));
		panel.add(table, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		scrollPane.setVisible(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(238, 232, 170));
		panel_1.setBounds(380, 124, 194, 240);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 31, 61, 14);
		panel_1.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(81, 28, 103, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("City:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 84, 52, 14);
		panel_1.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(81, 81, 103, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Phone:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 136, 61, 14);
		panel_1.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(81, 133, 103, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/pb?user=root&password=root");
					String queryString="insert into contact values(?,?,?)";
					PreparedStatement statement=connection.prepareStatement(queryString);
					statement.setString(1,textField.getText());
					statement.setString(2,textField_1.getText());
					statement.setString(3,textField_2.getText());
					int i=statement.executeUpdate();
					System.out.println(i);
					connection.close();
					JOptionPane.showMessageDialog(null, "Data Saved");
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
					disp();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				
			}
			
			
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.setBounds(10, 185, 86, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/pb?user=root&password=root");
					String queryString="delete from  contact where  name = ?";
					PreparedStatement statement=connection.prepareStatement(queryString);
					System.out.println(textField.getText());
					statement.setString(1,textField.getText().toString());
					
					int i=statement.executeUpdate();
					//System.out.println(i);
					connection.close();
					if(i>0)
					{
					JOptionPane.showMessageDialog(null, "Data Deleted");
				    textField.setText(null);
					}
				    else {
						JOptionPane.showMessageDialog(null, "Enter Valid Name");
					}
				
					disp();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				
			}
		});
		btnNewButton_1.setBounds(98, 185, 86, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("UPDATE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/pb?user=root&password=root");
					String queryString="update  contact set city=?,phone=?  where  name = ?";
					PreparedStatement statement=connection.prepareStatement(queryString);
					System.out.println(textField.getText());
					statement.setString(1,textField_1.getText());
					statement.setString(2,textField_2.getText());
					statement.setString(3,textField.getText());
					int i=statement.executeUpdate();
					//System.out.println(i);
					connection.close();
					if(i>0)
					{
					JOptionPane.showMessageDialog(null, "Data Updated");
				    textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
					}
				    else {
						JOptionPane.showMessageDialog(null, "Enter Valid Name");
					}
				
					disp();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				
				
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(255, 0, 255));
		btnNewButton_2.setBounds(53, 219, 89, 23);
		panel_1.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Contact Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(399, 88, 149, 25);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel("Search By Name :");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 60, 103, 14);
		getContentPane().add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				disp(textField_3.getText());
				
			}
		});
		
		textField_3.setBounds(109, 57, 134, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		disp();
	}
}
