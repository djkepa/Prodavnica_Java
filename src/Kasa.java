import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseWheelListener;
import java.awt.print.PrinterException;
import java.awt.event.MouseWheelEvent;
import javax.swing.border.LineBorder;
import javax.swing.Icon;

public class Kasa extends JFrame {

	private JPanel contentPane;
	private JTextField txtSifra;
	private JTextField txtNaziv;
	private JTextField txtCena;
	private JTextField txtIznos;
	private JTextField txtUkupno;
	private JTextField txtPlatiti;
	private JTextField txtBilans;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kasa frame = new Kasa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Kasa() {
		initialize();
		Connect();
//		ocitavanje_tabele();
		}
	
	
	 Connection konekcija;
	 PreparedStatement priprema;
	 ResultSet rs;
	 private JTable table;
	 private JTable tabela;
	 
	 public void Connect()
	     {
	         try {
	             Class.forName("com.mysql.jdbc.Driver");
	             konekcija = DriverManager.getConnection("jdbc:mysql://localhost/prodavnica", "root","");
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
		setBounds(0, 0, 1105, 738);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210,240,189));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(60,132,132));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 255, 255), new Color(160, 160, 160)), "PROIZVOD", TitledBorder.LEADING, TitledBorder.TOP,new Font("Tahoma", Font.BOLD, 14), new Color(255, 255, 255)));
		panel.setBounds(10, 11, 762, 202);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0160IFRA");
		lblNewLabel.setForeground(new Color(255, 240, 245));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(32, 38, 126, 29);
		panel.add(lblNewLabel);
		
		JLabel lblNazivProizvoda = new JLabel("NAZIV");
		lblNazivProizvoda.setForeground(Color.WHITE);
		lblNazivProizvoda.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNazivProizvoda.setBounds(178, 38, 139, 29);
		panel.add(lblNazivProizvoda);
		
		JLabel lblCenaProizvoda = new JLabel("CENA");
		lblCenaProizvoda.setForeground(Color.WHITE);
		lblCenaProizvoda.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCenaProizvoda.setBounds(517, 38, 93, 29);
		panel.add(lblCenaProizvoda);
		
		JLabel lblKonacno = new JLabel("IZNOS");
		lblKonacno.setForeground(Color.WHITE);
		lblKonacno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKonacno.setBounds(633, 38, 99, 29);
		panel.add(lblKonacno);
		
		JLabel lblKolicina = new JLabel("KOLI\u010CINA");
		lblKolicina.setForeground(Color.WHITE);
		lblKolicina.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKolicina.setBounds(416, 38, 80, 29);
		panel.add(lblKolicina);
		
		txtSifra = new JTextField();
		txtSifra.setForeground(new Color(24, 52, 52));
		txtSifra.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtSifra.setBackground(new Color(192, 234, 161));
		txtSifra.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode() == KeyEvent.VK_ENTER)
	        {
	            String id = txtSifra.getText();   
	            try {
		             
	                priprema = konekcija.prepareStatement("select * from proizvodi where id = ?");
	                priprema.setString(1, id);
	                rs = priprema.executeQuery();
	                
	                
	                if(rs.next() == false)
	                {     
	                	JOptionPane.showMessageDialog(null, "Proizvod nije pronadjen!");
	                }
	                else
	                {
	                     String naziv = rs.getString("Naziv");
	                     String cena = rs.getString("Cena");
	                     txtNaziv.setText(naziv.trim());
	                     txtCena.setText(cena.trim());
	                     txtIznos.setText(cena.trim());
	                }
	            } 
	            catch (SQLException ex) {
	                Logger.getLogger(Kasa.class.getName()).log(Level.SEVERE, null, ex);
	           }
	        }
		}
		});
		txtSifra.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSifra.setBounds(32, 78, 126, 35);
		panel.add(txtSifra);
		txtSifra.setColumns(10);
		
		txtNaziv = new JTextField();
		txtNaziv.setForeground(new Color(24, 52, 52));
		txtNaziv.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtNaziv.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNaziv.setColumns(10);
		txtNaziv.setBounds(178, 78, 217, 35);
		panel.add(txtNaziv);
		
		txtCena = new JTextField();
		txtCena.setForeground(new Color(24, 52, 52));
		txtCena.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtCena.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCena.setColumns(10);
		txtCena.setBounds(517, 78, 93, 35);
		panel.add(txtCena);
		
		txtIznos = new JTextField();
		txtIznos.setForeground(new Color(24, 52, 52));
		txtIznos.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtIznos.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtIznos.setColumns(10);
		txtIznos.setBounds(633, 78, 99, 35);
		panel.add(txtIznos);

		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 224, 762, 393);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		tabela = new JTable();
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sifra", "Naziv", "Kolicina", "Cena", "Ukupno"
			}
		));
		tabela.setBounds(10, 11, 742, 442);
		panel_2.add(tabela);
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		panel_2.add(scrollPane);
		scrollPane.setBounds(0, 0, 762, 393);
		Font font = new Font("", 1,22);
		tabela.setFont(font);
		tabela.setRowHeight(25);
		tabela.getTableHeader().setFont(font);
		tabela.getTableHeader().setOpaque(false);
		tabela.getTableHeader().setBackground(new Color(92,156,148));
		tabela.getTableHeader().setForeground(new Color(255,255,255));
		tabela.setBackground(new Color(192, 234, 161));
		
		TableColumnModel columnModel = tabela.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(20);
		columnModel.getColumn(1).setPreferredWidth(230);
		columnModel.getColumn(2).setPreferredWidth(20);
		
		JSpinner txtKolicina = new JSpinner();
		txtKolicina.setForeground(new Color(24, 52, 52));
		txtKolicina.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtKolicina.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		txtKolicina.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int qty = Integer.parseInt(txtKolicina.getValue().toString());
		        int price = Integer.parseInt(txtCena.getText());
		        int tot = qty * price;
		        
		        txtIznos.setText(String.valueOf(tot));
			}
		});
		txtKolicina.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtKolicina.setBounds(416, 78, 80, 35);
		panel.add(txtKolicina);
		
		JButton btnNewButton = new JButton("DODAJ");
		btnNewButton.setBorder(new LineBorder(new Color(0, 128, 128), 2));
		btnNewButton.setForeground(new Color(24, 52, 52));
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
	        if(txtNaziv.getText().trim().isEmpty() && txtCena.getText().trim().isEmpty() && txtIznos.getText().trim().isEmpty()){ 
	        	JOptionPane.showMessageDialog(null, "Polja moraju biti popunjena!");
	        } else {
	        	
	        	DefaultTableModel model = new DefaultTableModel();
		        model = (DefaultTableModel)tabela.getModel();
		        model.addRow(new Object[]
		        {
		           txtSifra.getText(),
		           txtNaziv.getText(),
		           txtKolicina.getValue().toString(),
		           txtCena.getText(),
		           txtIznos.getText(),
		        });
		        
	        	int sum = 0;
                    
		        for(int i = 0; i<tabela.getRowCount(); i++)
		        {
		            sum = sum + Integer.parseInt(tabela.getValueAt(i, 4).toString());
		        }
		        
		        txtUkupno.setText(Integer.toString(sum));
		           
		        txtSifra.setText("");
		        txtNaziv.setText("");
		        txtKolicina.setValue(1);
		        txtCena.setText("");
		        txtIznos.setText("");
		        txtSifra.requestFocus();
	        }
	        
	       
		}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(570, 136, 162, 42);
		panel.add(btnNewButton);
		
		JButton btnIzlaz = new JButton("");
		btnIzlaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tabela.getSelectedRow();
				
			if(i >= 0) {
				DefaultTableModel model = new DefaultTableModel();
		        model = (DefaultTableModel)tabela.getModel();
				model.removeRow(i);
				
				int sum = 0;
				for(int i1 = 0; i1<tabela.getRowCount(); i1++)
		        {
		            sum = sum + Integer.parseInt(tabela.getValueAt(i1, 4).toString());
		        }
		        
		        txtUkupno.setText(Integer.toString(sum));
				}
			else {
				JOptionPane.showMessageDialog(null, "Nije moguce obrisati, selektujte samo jedan proizvod u tabeli!");
			}
			double konacno = 0;
			for(int i1 = 0; i < table.getRowCount(); i1++) {
				int iznos = Integer.parseInt((String )tabela.getValueAt(i1, 3));
				konacno+=iznos;
			}
			
			
			}
		});
		btnIzlaz.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		btnIzlaz.setForeground(new Color(47, 79, 79));
		btnIzlaz.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIzlaz.setBackground(new Color(237, 249, 228));
		btnIzlaz.setBounds(490, 136, 53, 42);
		panel.add(btnIzlaz);
		
		ImageIcon buttonIcon = new ImageIcon("C:\\Users\\KORISNIK\\Desktop\\Prodavnica\\assets\\kanta.png"); 
		Image image = buttonIcon.getImage(); 
		Image newimg = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH); 
		buttonIcon = new ImageIcon(newimg);  
		btnIzlaz.setIcon(buttonIcon);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 234, 161));
		panel_1.setBounds(782, 11, 297, 285);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("UKUPNO");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(24, 37, 98, 25);
		panel_1.add(lblNewLabel_1);
		
		txtUkupno = new JTextField();
		txtUkupno.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtUkupno.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtUkupno.setColumns(10);
		txtUkupno.setBounds(132, 32, 139, 35);
		panel_1.add(txtUkupno);
		
		txtPlatiti = new JTextField();
		txtPlatiti.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtPlatiti.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPlatiti.setColumns(10);
		txtPlatiti.setBounds(132, 90, 139, 35);
		panel_1.add(txtPlatiti);
		
		JLabel lblNewLabel_1_1 = new JLabel("ZA PLACANJE");
		lblNewLabel_1_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(24, 95, 98, 25);
		panel_1.add(lblNewLabel_1_1);
		
		txtBilans = new JTextField();
		txtBilans.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtBilans.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtBilans.setColumns(10);
		txtBilans.setBounds(132, 151, 139, 35);
		panel_1.add(txtBilans);
		txtBilans.setEditable(false);
		
		
		JLabel lblNewLabel_1_2 = new JLabel("BILANS");
		lblNewLabel_1_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(24, 156, 98, 25);
		panel_1.add(lblNewLabel_1_2);
		
		JButton btnNewButton_1 = new JButton("RA\u010CUN");
		btnNewButton_1.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		btnNewButton_1.setForeground(new Color(47, 79, 79));
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bilans();
				Racun();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(120, 212, 151, 49);
		panel_1.add(btnNewButton_1);
		
		ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\KORISNIK\\Desktop\\Prodavnica\\assets\\racun.png"); 
		Image image1 = imageIcon1.getImage(); 
		Image newimg1 = image1.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon1 = new ImageIcon(newimg1);  
		
		JLabel lblNewLabel_2 = new JLabel(imageIcon1);
		lblNewLabel_2.setBounds(24, 203, 71, 58);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton_1_2 = new JButton("MENI");
		btnNewButton_1_2.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		btnNewButton_1_2.setForeground(new Color(24, 52, 52));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni meni = new Meni();
				meni.setVisible(true);	
				dispose();
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_2.setBounds(401, 628, 175, 49);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_2_1 = new JButton("IZLAZ");
		btnNewButton_1_2_1.setForeground(new Color(47, 79, 79));
		btnNewButton_1_2_1.setBackground(Color.GRAY);
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_2_1.setBounds(10, 628, 175, 49);
		contentPane.add(btnNewButton_1_2_1);
		
		JButton btnNewButton_1_2_1_1 = new JButton("O\u010CISTI");
		btnNewButton_1_2_1_1.setBorder(new LineBorder(new Color(47, 79, 79), 2));
		btnNewButton_1_2_1_1.setForeground(new Color(24, 52, 52));
		btnNewButton_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)tabela.getModel();
		        model.getDataVector().removeAllElements();
	 		    model.fireTableDataChanged();
	 		    txtUkupno.setText("");
		        txtPlatiti.setText("");
		        txtBilans.setText("");
		        txtSifra.setText("");
			}
		});
		btnNewButton_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_2_1_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1_2_1_1.setBounds(205, 628, 175, 49);
		contentPane.add(btnNewButton_1_2_1_1);
		
	}
	
	public void Bilans() {
		int ukupno = Integer.parseInt(txtUkupno.getText());
		int platiti = Integer.parseInt(txtPlatiti.getText());
		int bil = platiti - ukupno;
		txtBilans.setText(String.valueOf(bil));
	}
	public void Racun() {
		JTextArea txtRacun = new JTextArea();
     	txtRacun.setFont(new Font("Monospaced", Font.PLAIN, 14));
     	txtRacun.setBounds(782, 307, 297, 381);
     	contentPane.add(txtRacun);
     	JScrollPane scrollPane_1 = new JScrollPane(txtRacun);
		scrollPane_1.setBounds(780, 307, 299, 381);
		contentPane.add(scrollPane_1);
		String ukupno = txtUkupno.getText();
        String platiti = txtPlatiti.getText();
        String bilans = txtBilans.getText();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel)tabela.getModel();
        txtRacun.setText(txtRacun.getText() + "******************************************************\n");
        txtRacun.setText(txtRacun.getText() + "           RACUN                                    \n");
        txtRacun.setText(txtRacun.getText() + "*******************************************************\n");
        txtRacun.setText(txtRacun.getText() + "Prozivod" + "\t" + "Cena" + "\t" + "Iznos" + "\n"  );
         for(int i = 0; i < model.getRowCount(); i++) {
             String naziv = (String)model.getValueAt(i, 1);
             String cena = (String)model.getValueAt(i, 3);
             String iznos = (String)model.getValueAt(i, 4); 
             txtRacun.setText(txtRacun.getText() + naziv  + "\t" + cena + "\t" + iznos  + "\n"  );
         }
         txtRacun.setText(txtRacun.getText() + "\n");     
         txtRacun.setText(txtRacun.getText() + "\t" + "\t" + "Ukupno : " + ukupno + "\n");
         txtRacun.setText(txtRacun.getText() + "\t" + "\t" + "Platio : " + platiti + "\n");
         txtRacun.setText(txtRacun.getText() + "\t" + "\t" + "Kusur : " + bilans + "\n");
         txtRacun.setText(txtRacun.getText() + "\n");
         txtRacun.setText(txtRacun.getText() + "*******************************************************\n");
         txtRacun.setText(txtRacun.getText() + "           HVALA DODJITE NAM OPET             \n");
         JButton btnNewButton_1_1 = new JButton("STAMPAJ");
 		 btnNewButton_1_1.addActionListener(new ActionListener() {
 		 public void actionPerformed(ActionEvent e) {
 			 try {
 				    DefaultTableModel model = (DefaultTableModel)tabela.getModel();
 		            model.getDataVector().removeAllElements();
 	 				model.fireTableDataChanged();
 		            txtRacun.print();
 		        } catch (PrinterException ex) {
 		            Logger.getLogger(Kasa.class.getName()).log(Level.SEVERE, null, ex);
 		        } 
 		 }
 		 });
 		 btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
 		 btnNewButton_1_1.setBounds(597, 628, 175, 49);
 		 contentPane.add(btnNewButton_1_1);
	}
}




