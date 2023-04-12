import java.awt.EventQueue;
import java.util.*;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class ajoutFormat {

	private JFrame frame;
	private JTextField nomFormat;
	private JTextField datedebut;
	private JTextField datefin;
	private JTextField duree;
	private JTextField prix;
	public Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajoutFormat window = new ajoutFormat();
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
	public ajoutFormat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		String url="jdbc:mysql://localhost:3306/db";
		try {
			
		connection = DriverManager.getConnection(url,"root","wissal");
		System.out.println("connecté avec succes au serveur");
		}
		
		catch(Exception exp)
		{
			System.out.println(exp); }
		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 15));
		frame.getContentPane().setBackground(new Color(255, 218, 185));
		frame.getContentPane().setLayout(null);
		
		JLabel lblAjouterFormation = new JLabel("Ajouter Formation");
		lblAjouterFormation.setForeground(new Color(0, 0, 139));
		lblAjouterFormation.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblAjouterFormation.setBackground(new Color(0, 0, 139));
		lblAjouterFormation.setBounds(10, 21, 262, 67);
		frame.getContentPane().add(lblAjouterFormation);
		
		JLabel lblNewLabel = new JLabel("Nom de la formation :");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 99, 162, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date de d\u00E9but            :");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 151, 162, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date de fin                  :");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 201, 162, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		nomFormat = new JTextField();
		nomFormat.setBounds(182, 99, 291, 32);
		frame.getContentPane().add(nomFormat);
		nomFormat.setColumns(10);
		
		datedebut = new JTextField();
		datedebut.setBounds(182, 151, 134, 29);
		frame.getContentPane().add(datedebut);
		datedebut.setColumns(10);
		
		datefin = new JTextField();
		datefin.setBounds(182, 201, 134, 27);
		frame.getContentPane().add(datefin);
		datefin.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Dur\u00E9e                           :");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 264, 162, 17);
		frame.getContentPane().add(lblNewLabel_3);
		
		duree = new JTextField();
		duree.setBounds(182, 258, 134, 27);
		frame.getContentPane().add(duree);
		duree.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Prix                              :");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 308, 162, 29);
		frame.getContentPane().add(lblNewLabel_4);
		
		prix = new JTextField();
		prix.setBounds(182, 308, 134, 27);
		frame.getContentPane().add(prix);
		prix.setColumns(10);
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formations ac = new formations();
				formations.main(null);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 388, 224, 53);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="insert into formations(nomFormat,datedebut,datefin,duree,prix) values(?,?,?,?,?)";
				try {
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1, nomFormat.getText());
				pst.setString(2, datedebut.getText());
				pst.setString(3, datefin.getText());
				pst.setString(4, duree.getText());
				pst.setString(5, prix.getText());
				
				
				boolean b=pst.execute();
				
				formations forma = new formations();
				formations.main(null);
				
				}
				catch(Exception exp) {
					System.out.println(exp);
					JOptionPane.showMessageDialog(null, "Des champs érronés/vides","Erreur de saisie",JOptionPane.ERROR_MESSAGE);
				}
				nomFormat.setText(null);
				datedebut.setText(null);
				datefin.setText(null);
				duree.setText(null);
				prix.setText(null);
				
			}
			
		});
		btnNewButton_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton_1.setBounds(232, 388, 241, 53);
		frame.getContentPane().add(btnNewButton_1);
		frame.setBounds(100, 100, 495, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
