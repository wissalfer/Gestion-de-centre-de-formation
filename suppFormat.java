import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class suppFormat {

	private JFrame frame;
	private JTextField nomFormat;
	private JPasswordField passwordField;
	public Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					suppFormat window = new suppFormat();
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
	public suppFormat() {
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
		frame.setBounds(100, 100, 502, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSupprimerFormation = new JLabel("Supprimer Formation");
		lblSupprimerFormation.setForeground(new Color(0, 0, 139));
		lblSupprimerFormation.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblSupprimerFormation.setBackground(new Color(0, 0, 139));
		lblSupprimerFormation.setBounds(10, 11, 262, 67);
		frame.getContentPane().add(lblSupprimerFormation);
		
		JLabel lblNewLabel = new JLabel("Nom de la formation :");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 100, 162, 29);
		frame.getContentPane().add(lblNewLabel);
		
		nomFormat = new JTextField();
		nomFormat.setColumns(10);
		nomFormat.setBounds(182, 94, 291, 38);
		frame.getContentPane().add(nomFormat);
		
		JLabel lblMotDePasse = new JLabel("Mot de Passe              :");
		lblMotDePasse.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblMotDePasse.setBounds(10, 149, 162, 26);
		frame.getContentPane().add(lblMotDePasse);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 149, 291, 38);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="delete from formations where ID_format = (select ID_format from formations where Nom_format = ?)";
				String myPass=String.valueOf(passwordField.getPassword());
				try {
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1, nomFormat.getText());
				
				if (myPass.contains("admin")) {
					nomFormat.setText(null);
					passwordField.setText(null);
					formations ac = new formations();
					formations.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Des champs érronés/vides","Erreur de saisie",JOptionPane.ERROR_MESSAGE);
					nomFormat.setText(null);
					passwordField.setText(null);
				}
				
				boolean b=pst.execute();
				
				}
				catch(Exception exp) {
					System.out.println(exp);
					JOptionPane.showMessageDialog(null, "Des champs érronés/vides","Erreur de saisie",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton_1.setBackground(new Color(149, 187, 245));
		btnNewButton_1.setBounds(237, 218, 226, 48);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formations ac = new formations();
				formations.main(null);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(149, 187, 245));
		btnNewButton.setBounds(10, 218, 218, 48);
		frame.getContentPane().add(btnNewButton);
	}
}
