import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class formations {

	private JFrame frame;
	private JTable table;
	public Connection connection = null;
	final Object[]row=new Object[5];
	public DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formations window = new formations();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public formations() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		String url="jdbc:mysql://localhost:3306/db";
		try {
			
		connection = DriverManager.getConnection(url,"root","wissal");
		System.out.println("connect� avec succes au serveur");
		}
		
		catch(Exception exp)
		{
			System.out.println(exp); }
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 617, 642);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel_3 = new JLabel("Centre de formation ");
		lblNewLabel_3.setForeground(new Color(0, 0, 139));
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_3.setBackground(new Color(0, 0, 139));
		lblNewLabel_3.setBounds(20, 26, 248, 67);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		Image img3 = new ImageIcon(this.getClass().getResource("./img/images-removebg-preview.png")).getImage();
		Image newimg3 = img3.getScaledInstance(200, 50, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel_4.setIcon(new ImageIcon(newimg3));
		lblNewLabel_4.setBounds(35, 11, 207, 123);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Ajouter Formation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ajoutFormat format_aj = new ajoutFormat(); 
				ajoutFormat.main(null);
			}
		});
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBackground(new Color(149, 187, 245));
		btnNewButton.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnNewButton.setBounds(10, 190, 160, 56);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Supprimer Formation");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				suppFormat format_sup = new suppFormat(); 
				suppFormat.main(null);
			}
		});
		btnNewButton_3.setForeground(Color.white);
		btnNewButton_3.setBackground(new Color(149, 187, 245));
		btnNewButton_3.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnNewButton_3.setBounds(203, 190, 180, 55);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(25, 548, 378, 29);
		frame.getContentPane().add(lblNewLabel_1);


		JLabel lblNew = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("./img/pp.png")).getImage();
		Image newimg = img.getScaledInstance(200, 170, java.awt.Image.SCALE_SMOOTH);
		lblNew.setIcon(new ImageIcon(newimg));
		lblNew.setBounds(360, 20, 200, 170);
		frame.getContentPane().add(lblNew);
		
		/*JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 139));
		lblNewLabel_1_1.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(389, 548, 207, 29);
		frame.getContentPane().add(lblNewLabel_1_1);*/
		
		/*JLabel lblNewLabel_2 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("./img/formation-logo.png")).getImage();
		//lblNewLabel_2.setIcon(new ImageIcon(img));
		Image newimg = img.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
		lblNewLabel_2.setIcon(new ImageIcon(newimg));
		lblNewLabel_2.setBounds(247, 492, 100, 102);
		frame.getContentPane().add(lblNewLabel_2);*/
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 263, 569, 218);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Tout Voir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					try {
						String sql="select * from formations";
						PreparedStatement pst=connection.prepareStatement(sql);
						ResultSet rs=pst.executeQuery();
						while(rs.next())
						{row[0]=rs.getInt("ID_format");
						row[1]=rs.getString("Nom_format");
						row[2]=rs.getInt("ID_Ens");
						row[3]=rs.getString("Duree");
						row[4]=rs.getInt("Prix_dt");
						model.addRow(row);
						}
						}
						catch(Exception exp)
						{
						System.out.println(exp);
						}
					
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnNewButton_2.setBackground(new Color(149, 187, 245));
		btnNewButton_2.setBounds(415, 190, 160, 56);
		frame.getContentPane().add(btnNewButton_2);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model=new DefaultTableModel();
		Object[]column= {"ID_format","Nom_format","ID_Ens","Duree","Prix_dt"};
		
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		JButton btnNewButton_4 = new JButton("Annuler");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accueil ac = new accueil();
				accueil.main(null);
			}
		});
		btnNewButton_4.setForeground(new Color(255, 250, 205));
		btnNewButton_4.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNewButton_4.setBackground(new Color(237, 127, 16));
		btnNewButton_4.setBounds(479, 500, 100, 49);
		frame.getContentPane().add(btnNewButton_4);

	}
}
