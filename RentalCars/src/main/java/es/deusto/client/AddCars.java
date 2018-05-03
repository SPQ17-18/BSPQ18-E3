package es.deusto.client;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import es.deusto.server.remote.IRemote;
import es.deusto.server.remote.Remote;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

public class AddCars {

	private JFrame frame;

	private JPanel carPanel;
	private JLabel lblImage;
	private JLabel lblBrand;
	private JTextField txtBrand;
	private JLabel lblType;
	private JTextField txtType;
	private JLabel lblColour;
	private JTextField txtColour;
	private JLabel lblModel;
	private JTextField txtModel;
	private	JLabel lblAccesories;
	private JTextPane txtAccesories;
	//private JLabel lblPrice;
	//private JTextField txtPrice;
	private JLabel lblID;
	private JTextField txtID;
	private JLabel lblRank;
	private JTextField txtRank;

	private JButton btnAddcars;
	IRemote server;
	
	private static int ID;
	private static String brand;
	private static String colour;
	private static String model;
	private static String type;
	//private static double price;
	private static String accesories;
	//private static String img;
	
	private static String email;
	
	private ShowcarsAdmin showCaradmin;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCars window = new AddCars(email);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public AddCars(String email) {
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
		initializeAddCars();
	}

	/**
	 * Initialize the contents of the carSearch JPanel 
	 */
	private void initializeAddCars(){
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		carPanel = new JPanel();
		carPanel.setBounds(0, 0, 734, 272);
		frame.getContentPane().add(carPanel, BorderLayout.CENTER);
		carPanel.setLayout(null);

		lblBrand = new JLabel("brand:");
		lblBrand.setBounds(25, 30, 32, 23);
		carPanel.add(lblBrand);

		txtBrand = new JTextField();
		txtBrand.setBounds(100, 30, 150, 23);
		carPanel.add(txtBrand);
		txtBrand.setColumns(10);
		
		lbltype = new JLabel("type:");
		lbltype.setBounds(25, 80, 52, 23);
		carPanel.add(lbltype);

		txttype = new JTextField();
		txttype.setColumns(10);
		txttype.setBounds(100, 80, 150, 23);
		carPanel.add(txttype);

		lblcolour = new JLabel("colour: ");
		lblcolour.setBounds(25, 130, 72, 23);
		carPanel.add(lblcolour);

		txtcolour = new JTextField();
		txtcolour.setColumns(10);
		txtcolour.setBounds(100, 130, 150, 23);
		carPanel.add(txtcolour);

		lblModel = new JLabel("model: ");
		lblModel.setBounds(25, 180, 92, 23);
		carPanel.add(lblModel);

		txtModel = new JTextField();
		txtModel.setColumns(10);
		txtModel.setBounds(100, 180, 150, 23);
		carPanel.add(txtModel);

		lblAccesories = new JLabel("accesories:  ");
		lblAccesories.setBounds(25, 230, 112, 23);
		carPanel.add(lblAccesories);

		txtAccesories = new JTextPane();
		txtAccesories.setBounds(100, 230, 400, 40);
		carPanel.add(txtAccesories);

		//lblPrice = new JLabel("Price: ");
		//lblPrice.setBounds(25, 280, 132, 23);
		//carPanel.add(lblPrice);

		//txtPrice = new JTextField();
		//txtPrice.setColumns(10);
		//txtPrice.setBounds(100, 280, 150, 23);
		//carPanel.add(txtPrice);

		lblID = new JLabel("ID: ");
		lblID.setBounds(25, 330, 152, 23);
		carPanel.add(lblID);

		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(100, 330, 150, 23);
		carPanel.add(txtID);
/*
		lblImage = new JLabel("Image");
		lblImage.setBounds(300, 140, 150, 23);
		carPanel.add(lblImage);
*/
		//img = null;
		
		btnAddcars = new JButton("ADD car");
		btnAddcars.setBounds(350, 350, 150, 23);
		carPanel.add(btnAddcars);
		btnAddcars.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Brand = txtBrand.getText();
				type = txttype.getText();
				colour = txtcolour.getText();
				model = txtModel.getText();
				accesories = txtAccesories.getText();
				//price = Double.valueOf(txtPrice.getText());
				ID = Integer.parseInt(txtID.getText());
				//img
				
				try {
					server.addcar(ID, brand, colour, model, type, accesories);
					showcaradmin = new ShowcarsAdmin(email);
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
