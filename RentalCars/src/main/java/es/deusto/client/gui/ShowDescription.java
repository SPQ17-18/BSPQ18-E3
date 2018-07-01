package es.deusto.client.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;

import es.deusto.server.remote.*;


public class ShowDescription {

	private JFrame frame;
	
	private JPanel carPanel;
	private JLabel lblBrand;
	private JTextField txtBrand;
	private JLabel lblModel;
	private JTextField txtModel;
	private JLabel lblType;
	private JTextField txtType;
	private JLabel lblColor;
	private JTextField txtColor;
	private	JLabel lblAccesories;
	private JTextPane txtAccesories;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JLabel lblMat;
	private JTextField txtMat;
	
	private JButton btnRent;
	private JButton btnGoBack;
	
	private JPanel rentPanel;
	
	private static String email;
	private static String brand;
	private static int mat;
	private static String model;
	private static String type;
	private static String colour;
	private static String accesories;
	private static double price;
	
	
	
	
	
	private Client client;
	private static int id_rent=0;
	private static IRemote server;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 * @param user 
	 * @param user 
	 * @throws RemoteException 
	 */
	public ShowDescription(Client client, Car car) throws RemoteException {
		this.client = client;
		new Rent();
		// Create and set up the window.
		frame = new JFrame("Car Rent");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Display the window.
		frame.setBounds(0, 0, 991, 661);
		frame.setVisible(true);
		frame.setBackground(SystemColor.window);
		
		// Initialize the contents of the frame.
		try {
			server = new CarsRemote();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.email = client.getEmail();
		this.brand = car.getBrand();
		this.mat = car.getMat();
		this.model = car.getModel();
		this.type = car.getType();
		this.colour = car.getColour();
		this.accesories = car.getAccesories();
		this.price = car.getPrice();
		
		
	
		
		initializeShowAccesories();
	}
	
	/**
	 * Initialize the contents of the carSearch JPanel 
	 * @throws RemoteException 
	 */
	private void initializeShowAccesories() throws RemoteException{
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		carPanel = new JPanel();
		carPanel.setBounds(0, 0, 734, 272);
		frame.getContentPane().add(carPanel, BorderLayout.CENTER);
		carPanel.setLayout(null);
		
		
		lblBrand = new JLabel("Brand:");
		lblBrand.setBounds(357, 60, 42, 23);
		carPanel.add(lblBrand);
		
		lblModel = new JLabel("Model:");
		lblModel.setBounds(357, 94, 45, 23);
		carPanel.add(lblModel);
		
		lblType = new JLabel("Type: ");
		lblType.setBounds(357, 128, 111, 23);
		carPanel.add(lblType);
		
		lblColor = new JLabel("Color: ");
		lblColor.setBounds(357, 162, 45, 23);
		carPanel.add(lblColor);
		
		lblAccesories = new JLabel("Accesories:  ");
		lblAccesories.setBounds(357, 230, 92, 23);
		carPanel.add(lblAccesories);
		
		lblPrice = new JLabel("Price: ");
		lblPrice.setBounds(674, 80, 64, 24);
		carPanel.add(lblPrice);
		
		txtBrand = new JTextField(colour);
		txtBrand.setEditable(false);
		txtBrand.setBounds(415, 59, 177, 23);
		carPanel.add(txtBrand);
		txtBrand.setColumns(10);

		
		System.out.println("server.getCarByMat(mat): -" + "MATRICULA: " + mat + " -" + server.getCarBymat(mat));
		
		
		txtModel = new JTextField(model);
		txtModel.setEditable(false);
		txtModel.setColumns(10);
		txtModel.setBounds(415, 94, 177, 23);
		carPanel.add(txtModel);
		
		txtType = new JTextField(type);
		txtType.setEditable(false);
		txtType.setColumns(10);
		txtType.setBounds(415, 128, 177, 23);
		carPanel.add(txtType);
		
		txtColor = new JTextField(brand);
		txtColor.setEditable(false);
		txtColor.setColumns(10);
		txtColor.setBounds(415, 162, 177, 23);
		carPanel.add(txtColor);
		
		String precio =""+price;
		txtPrice = new JTextField(precio);
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBounds(716, 80, 92, 23);
		carPanel.add(txtPrice);
		
		
		lblMat = new JLabel("Mat: ");
		lblMat.setBounds(357, 196, 45, 23);
		carPanel.add(lblMat);
		
		String matricula =""+mat;
		txtMat = new JTextField(matricula);
		txtMat.setEditable(false);
		txtMat.setColumns(10);
		txtMat.setBounds(415, 196, 177, 23);
		carPanel.add(txtMat);
		
		
		
		txtAccesories = new JTextPane();
		txtAccesories.setEditable(false);
		txtAccesories.setText(accesories);
		txtAccesories.setBounds(357, 251, 460, 58);
		carPanel.add(txtAccesories);
		
		btnRent = new JButton("RENT");
		btnRent.setBounds(818, 81, 89, 23);
		carPanel.add(btnRent);
		
		
		
			
		
		
		btnRent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					id_rent=id_rent+1;
					int matricula=Integer.parseInt(txtMat.getText());
					String email=client.getEmail();
					System.out.println("MATRICULAAAAA----------->"+matricula);
					
					System.out.println("ID_RENT----------->"+id_rent);
					System.out.println("EMAIL DEL CLIENTE-------------->" + client.getEmail());
					server.addRent1(id_rent, matricula, email);
					
					
					
				
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog (null, "You have rent the car: "+mat + "\n Price: "+ price + "€ ", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		rentPanel = new JPanel();
		rentPanel.setBounds(0, 337, 975, 285);
		carPanel.add(rentPanel);
		rentPanel.setLayout(null);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(10, 251, 89, 23);
		rentPanel.add(btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShowCars(client);
				frame.dispose();
				frame.revalidate();
				frame.repaint();
			}
		});
		
	}
}


