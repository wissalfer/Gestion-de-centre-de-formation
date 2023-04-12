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

public class ajoutEns {

	private JFrame frame;
	private JTextField nomEns;
	private JTextField prenEns;
	private JTextField emailEns;
	private JTextField spec;
	private JTextField sal;
	public Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajoutEns window = new ajoutEns();
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
	public ajoutEns() {
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
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		
		JLabel lblAjouterFormation = new JLabel("Ajouter Enseingnant");
		lblAjouterFormation.setForeground(new Color(0, 0, 139));
		lblAjouterFormation.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblAjouterFormation.setBackground(new Color(0, 0, 139));
		lblAjouterFormation.setBounds(10, 21, 262, 67);
		frame.getContentPane().add(lblAjouterFormation);
		
		JLabel lblNewLabel = new JLabel("Nom de l'enseignant     :");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 99, 162, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prénom de l'enseignant:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 151, 162, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email                             :");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 201, 162, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		nomEns = new JTextField();
		nomEns.setBounds(182, 99, 291, 32);
		frame.getContentPane().add(nomEns);
		nomEns.setColumns(10);
		
		prenEns = new JTextField();
		prenEns.setBounds(182, 151, 291, 29);
		frame.getContentPane().add(prenEns);
		prenEns.setColumns(10);
		
		emailEns = new JTextField();
		emailEns.setBounds(182, 201, 291, 27);
		frame.getContentPane().add(emailEns);
		emailEns.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Spécialité                      :");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 264, 291, 17);
		frame.getContentPane().add(lblNewLabel_3);
		
		spec = new JTextField();
		spec.setBounds(182, 258, 291, 27);
		frame.getContentPane().add(spec);
		spec.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Salaire par heure          :");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 308, 162, 29);
		frame.getContentPane().add(lblNewLabel_4);
		
		sal = new JTextField();
		sal.setBounds(182, 308, 291, 27);
		frame.getContentPane().add(sal);
		sal.setColumns(10);
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.setBackground(new Color(237, 127, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enseignants ac = new enseignants();
				enseignants.main(null);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton.setBounds(320, 388, 130, 49);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql2="insert into enseignants(Nom_Ens,Prenom_Ens,Email_Ens,Specialite,Salaire_par_hr) values(?,?,?,?,?)";
				try {
				PreparedStatement pst=connection.prepareStatement(sql2);
				pst.setString(1, nomEns.getText());
				pst.setString(2, prenEns.getText());
				pst.setString(3, emailEns.getText());
				pst.setString(4, spec.getText());
				pst.setString(5, sal.getText());

				boolean b=pst.execute();

				
				enseignants ens = new enseignants();
				enseignants.main(null);
				
				}
				catch(Exception exp) {
					System.out.println(exp);
					JOptionPane.showMessageDialog(null, "Des champs érronés/vides","Erreur de saisie",JOptionPane.ERROR_MESSAGE);
				}
				nomEns.setText(null);
				prenEns.setText(null);
				emailEns.setText(null);
				spec.setText(null);
				sal.setText(null);
				
			}
			
		});
		btnNewButton_1.setBackground(new Color(149, 187, 245));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton_1.setBounds(180, 388, 130, 49);
		frame.getContentPane().add(btnNewButton_1);
		frame.setBounds(100, 100, 495, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
