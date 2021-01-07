import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Proizvodi extends JFrame {

	private JPanel contentPane;
	private JTable tabela;
	private JTextField txtNaziv;
	private JTextField txtCena;
	private JTextField txtSifra;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proizvodi frame = new Proizvodi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Proizvodi() {
		initialize();
		Connect();
		ocitavanje_tabele();
		}
	
	 Connection konekcija;
	 PreparedStatement priprema;
	 ResultSet rs;
	 
	 public void Connect() {
		 try {
             Class.forName("com.mysql.jdbc.Driver");
             konekcija = DriverManager.getConnection("jdbc:mysql://localhost/prodavnica", "root","");
         } catch (ClassNotFoundException ex) {
        	 ex.printStackTrace();
         } catch (SQLException ex) {
        	 ex.printStackTrace();
         }
     }
	 
	 public void ocitavanje_tabele() {
		 try {
			 priprema = konekcija.prepareStatement("select * from proizvodi");
			 rs = priprema.executeQuery();
			 tabela.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (SQLException e) {
        	 e.printStackTrace();
         } 
     }
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1105, 738);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210,240,189));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PROIZVODI");
		lblNewLabel.setForeground(new Color(76,140,140));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(438, 21, 209, 43);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 234, 161));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 92, 1069, 265);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 234, 161));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 389, 407, 299);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("NAZIV ");
		lblNewLabel_2.setForeground(new Color(36, 79, 79));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(26, 29, 90, 31);
		panel_1.add(lblNewLabel_2);
		
		txtNaziv = new JTextField();
		txtNaziv.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNaziv.setForeground(new Color(36, 79, 79));
		txtNaziv.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtNaziv.setBounds(126, 29, 259, 31);
		panel_1.add(txtNaziv);
		txtNaziv.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("KATEGORIJA");
		lblNewLabel_2_1.setForeground(new Color(36, 79, 79));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(26, 85, 90, 31);
		panel_1.add(lblNewLabel_2_1);
		
		txtCena = new JTextField();
		txtCena.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCena.setForeground(new Color(36, 79, 79));
		txtCena.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtCena.setColumns(10);
		txtCena.setBounds(126, 143, 259, 31);
		panel_1.add(txtCena);
		
		JLabel lblNewLabel_2_2 = new JLabel("CENA ");
		lblNewLabel_2_2.setForeground(new Color(36, 79, 79));
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(26, 143, 90, 31);
		panel_1.add(lblNewLabel_2_2);
		
		final JComboBox<String> getKategorija = new JComboBox<String>();
		getKategorija.setFont(new Font("Tahoma", Font.BOLD, 12));
		getKategorija.setForeground(new Color(36, 79, 79));
		getKategorija.setBackground(new Color(241, 250, 235));
		getKategorija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getKategorija.setBounds(126, 86, 259, 31);
		panel_1.add(getKategorija);
		getKategorija.addItem("Pica");
		getKategorija.addItem("Hrana");
		getKategorija.addItem("Kozmetika");
		getKategorija.setSelectedItem(null);
		
		JButton btnNewButton = new JButton("DODAJ");
		btnNewButton.setForeground(new Color(36, 79, 79));
		btnNewButton.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv, kategorija, cena;
				naziv = txtNaziv.getText();
		
				cena = txtCena.getText();
				if(naziv.trim().isEmpty() || cena.trim().isEmpty()){ 
		        	JOptionPane.showMessageDialog(null, "Polja moraju biti popunjena!");
		        } else {
		        	try {
		        		kategorija = getKategorija.getSelectedItem().toString();
		        		priprema = konekcija.prepareStatement("insert into proizvodi(Naziv,Kategorija,Cena)values(?,?,?)");
		        		priprema.setString(1, naziv);
		        		priprema.setString(2, kategorija);
		        		priprema.setString(3, cena);
		        		priprema.executeUpdate();
						JOptionPane.showMessageDialog(null, "Uspesno ste dodali proizvod!");					 
						ocitavanje_tabele();  
						txtNaziv.setText("");
						getKategorija.setSelectedItem(null);
						txtCena.setText("");
						txtNaziv.requestFocus();
				        
		        	} catch (SQLException e1) {
					  e1.printStackTrace();
		        	} 
		        }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(26, 227, 103, 47);
		panel_1.add(btnNewButton);
		
		JButton btnOcistiPolja = new JButton("O\u010CISTI");
		btnOcistiPolja.setForeground(new Color(36, 79, 79));
		btnOcistiPolja.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		btnOcistiPolja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNaziv.setText("");
            	getKategorija.setSelectedItem(null);
            	txtCena.setText("");
            	txtNaziv.requestFocus();
			}
		});
		btnOcistiPolja.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOcistiPolja.setBounds(154, 227, 103, 47);
		panel_1.add(btnOcistiPolja);
		
		JButton btnAzuriraj = new JButton("A\u017DURIRAJ");
		btnAzuriraj.setForeground(new Color(36, 79, 79));
		btnAzuriraj.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		btnAzuriraj.setBounds(282, 227, 103, 47);
		panel_1.add(btnAzuriraj);
		btnAzuriraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String naziv,kategorija,cena,sifra;
				 naziv = txtNaziv.getText();
				 kategorija = getKategorija.getSelectedItem().toString();
				 cena = txtCena.getText();
				 sifra  = txtSifra.getText();
				 try {
					 priprema = konekcija.prepareStatement("update proizvodi set Naziv= ?,Kategorija=?,Cena=? where id =?");
					 priprema.setString(1, naziv);
					 priprema.setString(2, kategorija);
					 priprema.setString(3, cena);
					 priprema.setString(4, sifra);
					 priprema.executeUpdate();
		             JOptionPane.showMessageDialog(null, "Uspesno ste promenili proizvod!");
		             ocitavanje_tabele();
		             txtNaziv.setText("");
		             getKategorija.setSelectedItem(null);
		             txtCena.setText("");
		             txtSifra.setText("");
		             txtNaziv.requestFocus();
				 } catch (SQLException e1) {
					 e1.printStackTrace();
				 }
			}
		});
		btnAzuriraj.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("KATALOG PROIZVODA");
		lblNewLabel_1.setForeground(new Color(76,140,140));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 67, 143, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("REGISTRACIJA PROIZVODA");
		lblNewLabel_1_1.setForeground(new Color(76,140,140));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 364, 180, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(192, 234, 161));
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1.setBounds(672, 389, 407, 158);
		contentPane.add(panel_1_1);
		
		JLabel lblNewLabel_2_3 = new JLabel("\u0160IFRA PROIZVODA");
		lblNewLabel_2_3.setForeground(new Color(36, 79, 79));
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_3.setBounds(26, 29, 134, 31);
		panel_1_1.add(lblNewLabel_2_3);
		
		txtSifra = new JTextField();
		txtSifra.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSifra.setForeground(new Color(36, 79, 79));
		txtSifra.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtSifra.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			try {
				String id = txtSifra.getText();
		        priprema = konekcija.prepareStatement("select Naziv,Kategorija,Cena from Proizvodi where id = ?");
		        priprema.setString(1, id);
		        ResultSet rs = priprema.executeQuery();
		        if(rs.next()==true) {
		        	String naziv = rs.getString(1);
		            String kategorija = rs.getString(2);
		            String cena = rs.getString(3);
		            txtNaziv.setText(naziv);
		            getKategorija.setSelectedItem(kategorija);
		            txtCena.setText(cena);
		        } else {
		            txtNaziv.setText("");
		            getKategorija.setSelectedItem(null);
		            txtCena.setText("");
		        	   } 
		        } catch (SQLException ex) {}
			}
		});
		txtSifra.setColumns(10);
		txtSifra.setBounds(154, 29, 231, 31);
		panel_1_1.add(txtSifra);
		
		JButton btnObrisati = new JButton("OBRI\u0160I");
		btnObrisati.setForeground(new Color(36, 79, 79));
		btnObrisati.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		btnObrisati.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String sifra;
            sifra  = txtSifra.getText();
            if(sifra.trim().isEmpty()){
            	JOptionPane.showMessageDialog(null, "Pogresan unos!");
            } else {
            	try {	
            		priprema = konekcija.prepareStatement("delete from proizvodi where id =?");
            		priprema.setString(1, sifra);
                	priprema.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Uspesno ste obrisali proizvod!");
                    ocitavanje_tabele(); 
                    txtNaziv.setText("");
                    getKategorija.setSelectedItem(null);
                    txtCena.setText("");
                    txtSifra.setText("");
                    txtNaziv.requestFocus();
                 } catch (SQLException e1) {
                	 e1.printStackTrace();
                 }
            }
		}
		});
		btnObrisati.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnObrisati.setBounds(282, 85, 103, 47);
		panel_1_1.add(btnObrisati);
		
		
		ImageIcon imageIcon3 = new ImageIcon("C:\\Users\\KORISNIK\\Desktop\\Prodavnica\\assets\\kanta.png"); // load the image to a imageIcon
		Image image3 = imageIcon3.getImage(); // transform it
		Image newimg3 = image3.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon3 = new ImageIcon(newimg3);  // transform it back
		
		
		JLabel labelKanta = new JLabel(imageIcon3);
		labelKanta.setBounds(217, 85, 55, 47);
		panel_1_1.add(labelKanta);
		
		JButton btnKasa = new JButton("KASA");
		btnKasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kasa kasa = new Kasa();
				kasa.setVisible(true);	
				dispose();
			}
		});
		btnKasa.setForeground(new Color(36, 79, 79));
		btnKasa.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		btnKasa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnKasa.setBounds(825, 618, 103, 47);
		contentPane.add(btnKasa);
		
		JButton btnMeni = new JButton("MENI");
		btnMeni.setForeground(new Color(36, 79, 79));
		btnMeni.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		btnMeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni meni = new Meni();
				meni.setVisible(true);	
				dispose();
			}
		});
		btnMeni.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMeni.setBounds(953, 618, 103, 47);
		contentPane.add(btnMeni);
		
		JButton btnIzlaz = new JButton("IZLAZ");
		btnIzlaz.setForeground(new Color(241, 250, 235));
		btnIzlaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnIzlaz.setBackground(new Color(76,140,140));
		btnIzlaz.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIzlaz.setBounds(693, 618, 103, 47);
		contentPane.add(btnIzlaz);
		
		tabela = new JTable();
		tabela.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				int row =  tabela.getSelectedRow();
	            String id = (tabela.getModel().getValueAt(row, 0).toString());
	            priprema = konekcija.prepareStatement("select Naziv,Kategorija,Cena, id from Proizvodi where id = ?");
	            priprema.setString(1, id);
	            ResultSet rs = priprema.executeQuery();
	            while(rs.next()) {
	            	String naziv = rs.getString(1);
	            	String kategorija = rs.getString(2);
	            	String cena = rs.getString(3);
	            	String sifra = rs.getString(4);
	            	txtNaziv.setText(naziv);
	            	getKategorija.setSelectedItem(kategorija);
	            	txtCena.setText(cena);
	            	txtSifra.setText(sifra);
	            }   
			} catch (SQLException ex) {}																	
			}
		});
		tabela.setForeground(new Color(36, 79, 79));
		JScrollPane scrollPane = new JScrollPane(tabela);
		tabela.setBounds(10, 11, 1049, 243);
		panel.add(scrollPane);
		scrollPane.setBounds(10, 11, 1049, 243);
		
		Font font = new Font("", 1,22);
		tabela.setFont(font);
		tabela.setRowHeight(25);
		tabela.getTableHeader().setFont(font);
		tabela.getTableHeader().setOpaque(false);
		tabela.getTableHeader().setBackground(new Color(76,140,140));
		tabela.getTableHeader().setForeground(new Color(255,255,255));
		tabela.setBackground(new Color(241, 250, 235));
		
		ImageIcon imageIcon2 = new ImageIcon("C:\\Users\\KORISNIK\\Desktop\\Prodavnica\\assets\\popravka.png"); // load the image to a imageIcon
		Image image2 = imageIcon2.getImage(); // transform it
		Image newimg2 = image2.getScaledInstance(160, 155,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon2 = new ImageIcon(newimg2);  // transform it back
		
		JLabel labelSredina = new JLabel(imageIcon2);
		labelSredina.setBounds(438, 389, 214, 158);
		contentPane.add(labelSredina);
	}
}
