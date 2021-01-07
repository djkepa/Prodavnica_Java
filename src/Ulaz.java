import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

public class Ulaz extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel lblSifra;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	private ImageIcon image1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ulaz frame = new Ulaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ulaz() {
		initialize();
		Connect();
		}
	
	 Connection konekcija;
	 PreparedStatement priprema;
	 ResultSet rs;
	 
	 public void Connect()
	     {
	         try {
	             Class.forName("com.mysql.jdbc.Driver");
	             Connection konekcija = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
	         }
	         catch (ClassNotFoundException ex) 
	         {
	           ex.printStackTrace();
	         }
	         catch (SQLException ex) 
	         {
	         	   ex.printStackTrace();
	         }
	 
	     }
	 
	public void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 225, 450, 330);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210,240,189));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("ULAZ");
		btnNewButton.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		btnNewButton.setBackground(new Color(192, 234, 161));
		btnNewButton.setForeground(new Color(76,140,140));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection konekcija = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/prodavnica", "root", "");
					Statement stmt = (Statement) konekcija.createStatement();
					String sql = "Select * from radnik where Ime='"+textField.getText()+"' and Sifra='"+passwordField.getText().toString()+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Uspesno ste se ulogovali!");
						Meni meni = new Meni();
						meni.setVisible(true);	
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Uneli ste pogresno korisnicko ime ili sifru, pokusajte ponovo!");
						textField.setText("");
						passwordField.setText("");
						textField.requestFocus();
					}
					konekcija.close();
				} catch(Exception e1) {
					System.out.print(e1);;
				}
			
			}
		});
		btnNewButton.setBounds(141, 236, 140, 44);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(76,140,140));
		passwordField.setBorder(new EmptyBorder(0, 0, 0, 0));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(215, 183, 126, 34);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setForeground(new Color(76,140,140));
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(215, 136, 126, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("KORISNIK");
		lblNewLabel.setForeground(new Color(76,140,140));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(92, 136, 113, 29);
		contentPane.add(lblNewLabel);
		
		lblSifra = new JLabel("SIFRA");
		lblSifra.setForeground(new Color(76,140,140));
		lblSifra.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSifra.setBounds(92, 185, 76, 29);
		contentPane.add(lblSifra);
		
		ImageIcon imageIcon2 = new ImageIcon("C:\\Users\\KORISNIK\\Desktop\\Prodavnica\\assets\\prodavnica.png"); // load the image to a imageIcon
		Image image2 = imageIcon2.getImage(); // transform it
		Image newimg2 = image2.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon2 = new ImageIcon(newimg2);  // transform it back
		
		JLabel lblNewLabel_4 = new JLabel(imageIcon2);
		lblNewLabel_4.setBounds(116, 11, 186, 114);
		contentPane.add(lblNewLabel_4);
		
		
		

		
	}
}
