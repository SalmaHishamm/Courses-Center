import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;

import java.sql.*;


public class CoursesProject {

	private JFrame frame;
	private JTextField username;
	private JPasswordField  password;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	//Main method was here.

	/**
	 * Create the application.
	 */
	public CoursesProject() {
		initialize();
		connect();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setAutoRequestFocus(false);
		frame.setBounds(100, 100, 1046, 695);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBackground(Color.white);
		frame.setTitle("Course Center");
		
		
		
		//connect();
		page_1();
		
	}
	
	
	Connection conn;
	PreparedStatement pst;
	private JButton btnNewButton;
	
	
	PreparedStatement pst1; //fill courses table
	PreparedStatement pst2; //fill instrucutors table
	PreparedStatement pst3; //delete from the table
	PreparedStatement pst4; //add to the  table
	PreparedStatement pst5; //search in the  table
	
	
	
	ResultSet rs1;  //fill courses table
	ResultSet rs2;  //fill instrucutors table 
	ResultSet rs3;  //delete from the table
	ResultSet rs4;  //add to the  table
	ResultSet rs5;  //search in the  table
	
	
	private JTable table_1;
	private JTextField txtDelete;
	private JTable table_2;
	private JTextField txtFCourseid;
	private JTextField txtFCoursename;
	private JTextField txtFinstid;
	private JComboBox comboBoxmajors;
	private JComboBox comboBoxmajors2;

	
	
	public void connect () {
		try {
		//	Class.forName("com.jdbc.mysql>Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", null);
			System.out.println("Connected With the database successfully");
		} catch (Exception e) {
			
			System.out.println("Error while connecting to the database");
		}
		
		
	}
	
	public void login () {
		try {
			Statement stmt = conn.createStatement();
			String sql ="Select * from users where name='" +username.getText()+" ' and password ='" + password.getText()+"' ";
			
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				page_2();
			}
			else 
				JOptionPane.showMessageDialog(null,"Incorrect username or password");
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}
	
	
	public void page_1() {
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(200,200,200) );
		panel1.setForeground(Color.RED);
		panel1.setBounds(5, 8, 1022, 641);
		frame.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		username = new JTextField();
		username.setBounds(125, 172, 213, 35);
		panel1.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(125, 236, 213, 35);
		panel1.add(password);
		password.setColumns(10);
		password.setEchoChar('*');
		
		JLabel lblNewLabel = new JLabel("Courses Center");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(475, 32, 245, 44);
		panel1.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(48, 183, 45, -50);
		panel1.add(lblNewLabel_1);
		
		lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(20, 178, 75, 25);
		panel1.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Password\r\n");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(20, 229, 105, 44);
		panel1.add(lblNewLabel_4);
		
		btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(80,105,255));
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(125, 312, 110, 35);
		panel1.add(btnNewButton);
		
		
		
		
	}
	
	
	public void page_2() {
		
		
		
		//System.out.print("2222");
		frame.getContentPane().removeAll();
		/*
		 * JButton trail = new JButton(); trail.setBounds(100,100,100,100);
		 * 
		 * frame.getContentPane().add(trail);
		 */
		
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setBackground(new Color(200, 205, 205));
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 1022, 208);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
				panel_2.add(scrollPane_1);
		
		
		table_1 = new JTable();
		table_1.setBackground(new Color(255,255,255));
		table_1.getTableHeader().setBackground(new Color(65,105,255));
		table_1.setEnabled(false);

		scrollPane_1.setViewportView(table_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(255,255,247));
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Search_Courses ();
				comboBoxmajors.setSelectedItem("Course Major");
				comboBoxmajors2.setSelectedItem("Instructor ID");

				
			}
			
			
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearch.setBounds(20, 477, 130, 45);
		panel_2.add(btnSearch);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(255,255,247));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Courses ();
				comboBoxmajors.setSelectedItem("Course Major");
				comboBoxmajors2.setSelectedItem("Instructor ID");
				
			}
		});
		btnAdd.setBounds(20, 422, 130, 45);
		panel_2.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255,255,247));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Delete_Course();
		
			}
		});
		btnDelete.setBounds(20, 585, 130, 45);
		panel_2.add(btnDelete);
		
		txtDelete = new JTextField();
		txtDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDelete.setText("Delete by ID");
		txtDelete.setBounds(182, 585, 250, 45);
		panel_2.add(txtDelete);
		txtDelete.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_2.setBounds(10, 228, 1010, 184);
		panel_2.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		table_2.setBackground(new Color(255,255,255));
		table_2.getTableHeader().setBackground(new Color(65,105,255));
		table_2.setEnabled(false);
		
		txtFCourseid = new JTextField();
		txtFCourseid.setBounds(182, 479, 176, 45);
		panel_2.add(txtFCourseid);
		txtFCourseid.setColumns(10);
		
		txtFCoursename = new JTextField();
		txtFCoursename.setBounds(381, 479, 198, 45);
		panel_2.add(txtFCoursename);
		txtFCoursename.setColumns(10);
		
		/*txtFinstid = new JTextField();
		txtFinstid.setBounds(612, 479, 214, 45);
		panel_2.add(txtFinstid);
		txtFinstid.setColumns(10);*/
		
		
		 comboBoxmajors2 = new JComboBox();
			comboBoxmajors2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			comboBoxmajors2.setModel(new DefaultComboBoxModel(new String[] {"Instructor ID","1", "2", "3", "4", "5","6"}));
			comboBoxmajors2.setBounds(612, 479, 214, 45);
			panel_2.add(comboBoxmajors2);
			
		
		
		
		 comboBoxmajors = new JComboBox();
		comboBoxmajors.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxmajors.setModel(new DefaultComboBoxModel(new String[] {"Course Major", "Computer Science ", "Engineering", "Pharmacy", "Mechatronics", "Management"}));
		comboBoxmajors.setBounds(847, 477, 160, 45);
		panel_2.add(comboBoxmajors);
		
		JLabel lblNewLabel = new JLabel("Courses ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(182, 455, 176, 13);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Course Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(381, 455, 198, 13);
		panel_2.add(lblNewLabel_1);
		
		/*
		 * JLabel lblNewLabel_2 = new JLabel("Instructor ID"); lblNewLabel_2.setFont(new
		 * Font("Tahoma", Font.PLAIN, 15)); lblNewLabel_2.setBounds(612, 455, 206, 13);
		 * panel_2.add(lblNewLabel_2);
		 */
		
		JButton btnNewButton = new JButton("View All");
		btnNewButton.setBackground(new Color(255,255,247));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtFCourseid.setText(" ");
				txtFCoursename.setText(" ");
				comboBoxmajors.setSelectedItem("Course Major");
				comboBoxmajors2.setSelectedItem("Instructor ID");
				//txtFinstid.setText(" ");
				
				fill_table_courses();
				//comboBoxmajors.setToolTipText("Course Major");
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(20, 530, 130, 45);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setBackground(new Color(255,255,247));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(182, 530, 130, 45);
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3,BorderLayout.SOUTH);
		
		//connect();
		//frame.getContentPane().add(panel_2);
		
		fill_table_courses();
		fill_table_instructors();
		
		
		
		frame.revalidate();
		frame.repaint();
		
	}
	
	
		
		/*
		 * JPanel panel_1 = new JPanel();
		 * panel_1.setBackground(SystemColor.activeCaption);
		 * panel_1.setForeground(Color.RED); panel_1.setBounds(0, 0, 1022, 641);
		 * frame.getContentPane().add(panel_1); panel_1.setLayout(null);
		 * 
		 * textField_1 = new JTextField(); textField_1.setBounds(125, 172, 213, 35);
		 * panel_1.add(textField_1); textField_1.setColumns(10);
		 * 
		 * textField_2 = new JTextField(); textField_2.setBounds(125, 236, 213, 35);
		 * panel_1.add(textField_2); textField_2.setColumns(10);
		 * 
		 * JLabel lblNewLabel = new JLabel("Login"); lblNewLabel.setFont(new
		 * Font("Tahoma", Font.BOLD, 29)); lblNewLabel.setBounds(475, 32, 245, 44);
		 * panel_1.add(lblNewLabel);
		 * 
		 * lblNewLabel_1 = new JLabel("New label"); lblNewLabel_1.setBounds(48, 183, 45,
		 * -50); panel_1.add(lblNewLabel_1);
		 * 
		 * lblNewLabel_3 = new JLabel("Username"); lblNewLabel_3.setFont(new
		 * Font("Tahoma", Font.PLAIN, 17)); lblNewLabel_3.setBounds(20, 178, 75, 25);
		 * panel_1.add(lblNewLabel_3);
		 * 
		 * lblNewLabel_4 = new JLabel("Password\r\n"); lblNewLabel_4.setFont(new
		 * Font("Tahoma", Font.PLAIN, 17)); lblNewLabel_4.setBounds(20, 229, 105, 44);
		 * panel_1.add(lblNewLabel_4);
		 * 
		 * 
		 * 
		 * JPanel panel_2 = new JPanel(); panel_2.setBounds(10, 10, 725, 581); JButton
		 * button2 = new JButton("New button2"); panel_2.add(button2);
		 */
	

public void fill_table_courses() {
		
		try {
			pst1 =conn.prepareStatement("select * from courses");
			rs1=pst1.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs1));

			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
public void fill_table_instructors() {
		
		try {
			pst2 =conn.prepareStatement("select * from instructors");
			rs2=pst2.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(rs2));

			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}


public void Delete_Course() {
	
	String d_id;
	d_id = txtDelete.getText().trim();
	
	try {
		pst3=conn.prepareStatement("delete from courses where id =?");
		pst3.setString(1, d_id);
		pst3.executeUpdate();
		
		
		fill_table_courses();
		
		txtDelete.setText("Delete by ID");
		
		
		
		
		
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}

public void Add_Courses () {
	
	String a_id = txtFCourseid.getText().trim() ;
	String a_name = txtFCoursename.getText().trim();
	String a_iid = comboBoxmajors2.getSelectedItem().toString();
	String a_major= comboBoxmajors.getSelectedItem().toString();
	
	
	
	if (!a_id.equals("") && !a_name.equals("") &&!a_iid.equals("Instructor ID")) {
	try {
		pst4=conn.prepareStatement("insert into courses (ID,Name,Instructors_ID,Major) values (?,?,?,?)");
		pst4.setString(1,a_id);
		pst4.setString(2,a_name);
		pst4.setString(3,a_iid);
		pst4.setString(4,a_major);
		
		pst4.executeUpdate();
		
		
		fill_table_courses();
		fill_table_instructors();
		
		txtFCourseid.setText(" ");
		txtFCoursename.setText(" ");
		//txtFinstid.setText(" ");
		
		
		
		
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}}
	
	
}



/*
 * public static String trim(String s) { if (s.charAt(0)==(' ')) {
 * s.substring(1); } return s;
 * 
 * }
 */
	

public void Search_Courses () {
	
	String s_id = txtFCourseid.getText().trim() ;
	String s_name = txtFCoursename.getText().trim();
	String s_iid = comboBoxmajors2.getSelectedItem().toString();
	String s_major= comboBoxmajors.getSelectedItem().toString();
	
	
	
	
	try {
		
		if (!s_id.equals("")) {
			pst5 =conn.prepareStatement("select * from courses where ID =?");
			pst5.setString(1,s_id);
			
		}
		else if (!s_name.equals("")) {
			  if (!s_iid.equals("Instructor ID")) {
				pst5 =conn.prepareStatement("select * from courses where Name like CONCAT('%',?,'%') and Instructors_ID =?");
				pst5.setString(1,s_name);
				pst5.setString(2,s_iid);
			  }
			  
				
			
			 else {
			   pst5 =conn.prepareStatement("select * from courses where Name like CONCAT('%',?,'%')");
			   pst5.setString(1,s_name);
			
		    }
	 }
		
	   else if (!s_major.equals("Course Major")) {
		      if (!s_iid.equals("Instructor ID")) {
					pst5 =conn.prepareStatement("select * from courses where Major =? and Instructors_ID =?");
					pst5.setString(1,s_major);
					pst5.setString(2,s_iid);
				  }
		      else if (!s_name.equals("")) {
					pst5 =conn.prepareStatement("select * from courses where Major =? and Name like CONCAT('%',?,'%')");
					pst5.setString(1,s_major);
					pst5.setString(2, s_name);  
		      }
		      
		      
		      
		      else  {
		    	  pst5 =conn.prepareStatement("select * from courses where Major = ?");
					pst5.setString(1,s_major);
		      }
		 
	    }
		else {
			pst5 =conn.prepareStatement("select * from courses where Instructors_ID =?");
			pst5.setString(1,s_iid);
			
		}
		rs5=pst5.executeQuery();
		table_1.setModel(DbUtils.resultSetToTableModel(rs5));
		
		//fill_table_courses();
		fill_table_instructors();
		
		/*
		 * txtFCourseid.setText(" "); txtFCoursename.setText(" ");
		 * s.setText(" ");
		 */
		
		
		
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
}

	
	
	
	
	
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
		//	public void run() {
			//	try {
					CoursesProject window = new CoursesProject();
					window.frame.setVisible(true);
					
					
					
			//	} catch (Exception e) {
			//		e.printStackTrace();
				//}
		//	}
	//	});
	}
}
