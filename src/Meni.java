import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Meni extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Meni frame = new Meni();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Meni() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 225, 450, 330);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210,240,189));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnProizvodi = new JButton("PROIZVODI");
		btnProizvodi.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		btnProizvodi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proizvodi proizvodi = new Proizvodi();
				proizvodi.setVisible(true);	
				dispose();
			}
		});
		btnProizvodi.setBackground(new Color(237, 249, 228));
		btnProizvodi.setForeground(new Color(60,132,132));
		btnProizvodi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnProizvodi.setBounds(278, 138, 121, 47);
		contentPane.add(btnProizvodi);
		
		JButton btnKasa = new JButton("KASA");
		btnKasa.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		btnKasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kasa kasa = new Kasa();
				kasa.setVisible(true);	
				dispose();
			}
		});
		btnKasa.setBackground(new Color(237, 249, 228));
		btnKasa.setForeground(new Color(60,132,132));
		btnKasa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKasa.setBounds(32, 138, 121, 47);
		contentPane.add(btnKasa);
		
		
		JButton btnIzlaz = new JButton("IZLAZ");
		btnIzlaz.setBackground(new Color(237, 249, 228));
		btnIzlaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnIzlaz.setForeground(new Color(47, 79, 79));
		btnIzlaz.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIzlaz.setBounds(126, 222, 176, 58);
		contentPane.add(btnIzlaz);
		
		ImageIcon buttonIcon = new ImageIcon("C:\\Users\\KORISNIK\\Desktop\\Prodavnica\\assets\\izlaz.png"); // load the image to a imageIcon
		Image image = buttonIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		buttonIcon = new ImageIcon(newimg);  // transform it back
		btnIzlaz.setIcon(buttonIcon);
		
		
		ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\KORISNIK\\Desktop\\Prodavnica\\assets\\kasa.png"); // load the image to a imageIcon
		Image image1 = imageIcon1.getImage(); // transform it
		Image newimg1 = image1.getScaledInstance(95, 95,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon1 = new ImageIcon(newimg1);  // transform it back
		
		JLabel labelKasa = new JLabel(imageIcon1);
		labelKasa.setBounds(32, 21, 121, 106);
		contentPane.add(labelKasa);
		
		ImageIcon imageIcon2 = new ImageIcon("C:\\Users\\KORISNIK\\Desktop\\Prodavnica\\assets\\prozivodi.png"); // load the image to a imageIcon
		Image image2 = imageIcon2.getImage(); // transform it
		Image newimg2 = image2.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon2 = new ImageIcon(newimg2);  // transform it back
		
		JLabel labelProizvodi = new JLabel(imageIcon2);
		labelProizvodi.setBounds(278, 21, 121, 106);
		contentPane.add(labelProizvodi);
	}
}
