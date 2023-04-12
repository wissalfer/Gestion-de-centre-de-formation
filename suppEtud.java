import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class suppEtud {

	private JFrame frame;
	private JTextField emailEtud;
	private JPasswordField pass;
	public Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					suppEtud window = new suppEtud();
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
	public suppEtud() {
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
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		
		JLabel lblSupprimerEnseignant = new JLabel("Supprimer Etudiant");
		lblSupprimerEnseignant.setForeground(new Color(0, 0, 139));
		lblSupprimerEnseignant.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblSupprimerEnseignant.setBackground(new Color(0, 0, 139));
		lblSupprimerEnseignant.setBounds(10, 11, 301, 67);
		frame.getContentPane().add(lblSupprimerEnseignant);
		
		JLabel lblEmailDeLenseignant = new JLabel("Email de l'etudiant :");
		lblEmailDeLenseignant.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblEmailDeLenseignant.setBounds(20, 91, 162, 29);
		frame.getContentPane().add(lblEmailDeLenseignant);
		
		JLabel lblMotDePasse = new JLabel("Mot de Passe              :");
		lblMotDePasse.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblMotDePasse.setBounds(20, 152, 162, 26);
		frame.getContentPane().add(lblMotDePasse);
		
		emailEtud = new JTextField();
		emailEtud.setColumns(10);
		emailEtud.setBounds(188, 89, 291, 38);
		frame.getContentPane().add(emailEtud);
		
		pass = new JPasswordField();
		pass.setBounds(188, 149, 291, 38);
		frame.getContentPane().add(pass);
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etudiants ac = new etudiants();
				etudiants.main(null);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(149, 187, 245));
		btnNewButton.setBounds(10, 240, 218, 48);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="delete from etudiants where ID_Etud = (select ID_Etud from etudiants where Email_Etud = ?)";
				String myPass=String.valueOf(pass.getPassword());
				try {
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1, emailEtud.getText());
				
				if (myPass.contains("admin")) {
					emailEtud.setText(null);
					pass.setText(null);
					etudiants ac = new etudiants();
					etudiants.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Des champs érronés/vides","Erreur de saisie",JOptionPane.ERROR_MESSAGE);
					emailEtud.setText(null);
					pass.setText(null);
				}
				
				boolean b=pst.execute();
				
				}
				catch(Exception exp) {
					System.out.println(exp);
					JOptionPane.showMessageDialog(null, "Des champs érronés/vides","Erreur de saisie",JOptionPane.ERROR_MESSAGE);
					emailEtud.setText(null);
					pass.setText(null);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton_1.setBackground(new Color(149, 187, 245));
		btnNewButton_1.setBounds(238, 240, 226, 48);
		frame.getContentPane().add(btnNewButton_1);
		frame.setBounds(100, 100, 503, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
