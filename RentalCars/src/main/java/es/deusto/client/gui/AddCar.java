package es.deusto.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JTextField;


import es.deusto.server.remote.IRemote;
import es.deusto.server.remote.Remote;

import javax.swing.JButton;

public class AddCar {

	private JFrame frame;
	private JPanel carPanel;
	private JTextField textFieldColour;
	private JTextField textFieldModel;
	private JTextField textFieldMat;
	private JTextField textFieldType;
	private JTextField textFieldBrand;
	private JTextField textFieldAccesories;
	private JTextField textFieldPrice;
	private JLabel lblBrand;
	private JLabel lblType;
	private JLabel lblColour;
	private JLabel lblModel;
	private	JLabel lblAccesories;
	private JLabel lblPrice;
	private JButton btnAddCar;
	
	IRemote server;

	private static int Mat;
	private static String brand;
	private static String colour;
	private static String model;
	private static String type;
	private static double price;
	private static String accesories;
	private static String email;
	private ShowcarsAdmin showCaradmin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCar window = new AddCar(email);
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
	public AddCar(String email) {
		// Create and set up the window.
				frame = new JFrame("Add car");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


				// Display the window.
				frame.setBounds(100, 100, 550, 470);
				frame.setVisible(true);
				frame.setBackground(SystemColor.window);

				// Initialize the contents of the frame.
				try {
					server = new Remote();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				this.email = email;
				initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		carPanel = new JPanel();
		carPanel.setBounds(0, 0, 734, 272);
		frame.getContentPane().add(carPanel, BorderLayout.CENTER);
		carPanel.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMatricula.setBounds(32, 29, 76, 14);
		carPanel.add(lblMatricula);
		
		JLabel lblNewLabel = new JLabel("Brand:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(32, 177, 57, 14);
		carPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Colour:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(32, 65, 46, 14);
		carPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Model:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(32, 104, 46, 14);
		carPanel.add(lblNewLabel_2);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblType.setBounds(32, 141, 46, 14);
		carPanel.add(lblType);
		
		JLabel lblAccesories = new JLabel("Accesories:");
		lblAccesories.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccesories.setBounds(32, 216, 76, 14);
		carPanel.add(lblAccesories);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrice.setBounds(32, 249, 46, 14);
		carPanel.add(lblPrice);
		
		textFieldColour = new JTextField();
		textFieldColour.setBounds(108, 63, 86, 20);
		carPanel.add(textFieldColour);
		textFieldColour.setColumns(10);
		
		textFieldModel = new JTextField();
		textFieldModel.setBounds(108, 102, 86, 20);
		carPanel.add(textFieldModel);
		textFieldModel.setColumns(10);
		
		textFieldMat = new JTextField();
		textFieldMat.setBounds(108, 27, 86, 20);
		carPanel.add(textFieldMat);
		textFieldMat.setColumns(10);
		
		textFieldType = new JTextField();
		textFieldType.setBounds(108, 139, 86, 20);
		carPanel.add(textFieldType);
		textFieldType.setColumns(10);
		
		textFieldBrand = new JTextField();
		textFieldBrand.setText("");
		textFieldBrand.setBounds(108, 175, 86, 20);
		carPanel.add(textFieldBrand);
		textFieldBrand.setColumns(10);
		
		textFieldAccesories = new JTextField();
		textFieldAccesories.setBounds(108, 214, 131, 20);
		carPanel.add(textFieldAccesories);
		textFieldAccesories.setColumns(10);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(108, 247, 86, 20);
		carPanel.add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		btnAddCar = new JButton("ADD CAR");
		btnAddCar.setBounds(291, 246, 89, 23);
		carPanel.add(btnAddCar);
		btnAddCar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Mat = Integer.parseInt(textFieldMat.getText());
				colour =textFieldColour.getText();
				model = textFieldModel.getText();
				type = textFieldType.getText();				
				brand = textFieldBrand.getText();				
				accesories = textFieldAccesories.getText();
				price = Double.valueOf(textFieldPrice.getText());
				
				
				
				try {
					server.addCar(Mat, colour, model,type,brand,accesories,price);
					showCaradmin = new ShowcarsAdmin(email);
					frame.dispose();
					frame.revalidate();
					frame.repaint();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}
}

