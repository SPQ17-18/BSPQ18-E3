package es.deusto.client.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import es.deusto.server.remote.IRemote;
import es.deusto.server.remote.Remote;

import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;



public class ShowDescriptionAdmin implements ActionListener {


	private JFrame frame;
	
	private JPanel carPanel;
	private JLabel lblImage;
	private JLabel lblBrand;
	private JTextField txtBrand;
	private JLabel lblModel;
	private JTextField txtModel;
	private JLabel lblType;
	private JTextField txtType;
	private JLabel lblColour;
	private JTextField txtColour;
	private	JLabel lblAccesories;
	private JTextPane txtAccesories;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JLabel lblMat;
	private JTextField txtMat;
	private JLabel lblRentR;
	private JTextField txtRentR;
	private JButton btnGoBack;
	private JTable rentsTable;
	//private ReviewTableModel m;
	private JScrollPane scrollRents;
	private JPanel rentPanel;
	private JLabel label;
	private JButton btnRemove;
	
	private JButton btnEditImage;
	private JButton btnEditBrand;
	private JButton btnEditModel;
	private JButton btnEditType;
	private JButton btnEditColour;
	private	JButton btnEditAccesories;
	private JButton btnEditPrice;
	private JButton btnEditMat;
	private JButton btnEditRentR;
	
	private JButton btnsaveChanges;
	
	private static String email;
	private static String brand;
	private static IRemote server;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowDescriptionAdmin window = new ShowDescriptionAdmin(brand, email);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowDescriptionAdmin(String title, String email) {
		
		// Create and set up the window.
		frame = new JFrame("Cars Rent");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Display the window.
		frame.setBounds(0, 0, 991, 661);
		frame.setVisible(true);
		frame.setBackground(SystemColor.window);
		
		// Initialize the contents of the frame.
		try {
			server = new Remote();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.email = email;
		this.brand = title;
		initializeShowAccesories();
	}
	
	/**
	 * Initialize the contents of the carSearch JPanel 
	 */
	private void initializeShowAccesories(){
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		carPanel = new JPanel();
		carPanel.setBounds(0, 0, 734, 272);
		frame.getContentPane().add(carPanel, BorderLayout.CENTER);
		carPanel.setLayout(null);
		
		lblImage = new JLabel("Image");
		lblImage.setBounds(46, 39, 232, 274);
		carPanel.add(lblImage);
		
		this.btnEditImage = new JButton(" Edit ");
		this.btnEditImage.setOpaque(false);
		this.btnEditImage.setContentAreaFilled(false);
		this.btnEditImage.setBorderPainted(false);
		this.btnEditImage.setBounds(46, 39, 260, 274);
		carPanel.add(this.btnEditImage);
		this.btnEditImage.addActionListener(this);
		
		lblBrand = new JLabel("Brand:");
		lblBrand.setBounds(357, 60, 32, 23);
		carPanel.add(lblBrand);
		
		lblModel = new JLabel("Model:");
		lblModel.setBounds(357, 94, 45, 23);
		carPanel.add(lblModel);
		
		lblType = new JLabel("Type: ");
		lblType.setBounds(357, 128, 111, 23);
		carPanel.add(lblType);
		
		lblColour = new JLabel("Colour: ");
		lblColour.setBounds(357, 162, 45, 23);
		carPanel.add(lblColour);
		
		lblAccesories = new JLabel("Accesories:  ");
		lblAccesories.setBounds(357, 230, 92, 23);
		carPanel.add(lblAccesories);
		
		lblPrice = new JLabel("Price: ");
		lblPrice.setBounds(674, 80, 64, 24);
		carPanel.add(lblPrice);
		
		lblRentR = new JLabel("RentR: ");
		lblRentR.setBounds(674, 136, 45, 23);
		carPanel.add(lblRentR);
		
		
		txtBrand = new JTextField(brand);
		txtBrand.setEditable(false);
		txtBrand.setBounds(415, 59, 177, 23);
		carPanel.add(txtBrand);
		txtBrand.setColumns(10);
		
		this.btnEditBrand = new JButton(" Edit ");
		this.btnEditBrand.setOpaque(false);
		this.btnEditBrand.setContentAreaFilled(false);
		this.btnEditBrand.setBorderPainted(false);
		this.btnEditBrand.setBounds(542, 59, 177, 23);
		carPanel.add(this.btnEditBrand);
		this.btnEditBrand.addActionListener(this);
		
		try {
			txtModel = new JTextField(server.getCarByBrand(brand).getModel());
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		txtModel.setEditable(false);
		txtModel.setColumns(10);
		txtModel.setBounds(415, 94, 177, 23);
		carPanel.add(txtModel);
		
		this.btnEditModel = new JButton(" Edit ");
		this.btnEditModel.setOpaque(false);
		this.btnEditModel.setContentAreaFilled(false);
		this.btnEditModel.setBorderPainted(false);
		this.btnEditModel.setBounds(542, 94, 177, 23);
		carPanel.add(this.btnEditModel);
		this.btnEditModel.addActionListener(this);
		
		try {
			txtType = new JTextField(server.getCarByBrand(brand).getType());
		} catch (RemoteException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		txtType.setEditable(false);
		txtType.setColumns(10);
		txtType.setBounds(415, 128, 177, 23);
		carPanel.add(txtType);
		
		this.btnEditType = new JButton(" Edit ");
		this.btnEditType.setOpaque(false);
		this.btnEditType.setContentAreaFilled(false);
		this.btnEditType.setBorderPainted(false);
		this.btnEditType.setBounds(542, 128, 177, 23);
		carPanel.add(this.btnEditType);
		this.btnEditType.addActionListener (this);
		
		try {
			txtColour = new JTextField(server.getCarByBrand(brand).getColour());
		} catch (RemoteException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		txtColour.setEditable(false);
		txtColour.setColumns(10);
		txtColour.setBounds(415, 162, 177, 23);
		carPanel.add(txtColour);
		
		this.btnEditColour = new JButton(" Edit ");
		this.btnEditColour.setOpaque(false);
		this.btnEditColour.setContentAreaFilled(false);
		this.btnEditColour.setBorderPainted(false);
		this.btnEditColour.setBounds(542, 162, 177, 23);
		carPanel.add(this.btnEditColour);
		this.btnEditColour.addActionListener(this);
		
		try {
			txtPrice = new JTextField(""+server.getCarByBrand(brand).getPrice());
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBounds(716, 80, 92, 23);
		carPanel.add(txtPrice);
		
		lblMat = new JLabel("Matricula: ");
		lblMat.setBounds(357, 196, 45, 23);
		carPanel.add(lblMat);
		
		try {
			txtMat = new JTextField("" + server.getCarByBrand(brand).getMat());
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		txtMat.setEditable(false);
		txtMat.setColumns(10);
		txtMat.setBounds(415, 196, 177, 23);
		carPanel.add(txtMat);
		
		this.btnEditMat = new JButton(" Edit ");
		this.btnEditMat.setOpaque(false);
		this.btnEditMat.setContentAreaFilled(false);
		this.btnEditMat.setBorderPainted(false);
		this.btnEditMat.setBounds(542, 196, 177, 23);
		carPanel.add(this.btnEditMat);
		this.btnEditMat.addActionListener(this);
		
		try {
			txtRentR = new JTextField("" + server.averageRatingByCar(brand)+ " /10");
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		txtRentR.setEditable(false);
		txtRentR.setColumns(10);
		txtRentR.setBounds(716, 136, 92, 23);
		carPanel.add(txtRentR);
		
		this.btnEditRentR = new JButton(" Edit ");
		this.btnEditRentR.setOpaque(false);
		this.btnEditRentR.setContentAreaFilled(false);
		this.btnEditRentR.setBorderPainted(false);
		this.btnEditRentR.setBounds(800, 136, 92, 23);
		carPanel.add(this.btnEditRentR);
		this.btnEditRentR.addActionListener(this);
		
		txtAccesories = new JTextPane();
		txtAccesories.setEditable(false);
		try {
			txtAccesories.setText(server.getCarByBrand(brand).getAccesories());
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		txtAccesories.setBounds(357, 251, 460, 58);
		carPanel.add(txtAccesories);
		
		this.btnEditAccesories = new JButton(" Edit ");
		this.btnEditAccesories.setOpaque(false);
		this.btnEditAccesories.setContentAreaFilled(false);
		this.btnEditAccesories.setBorderPainted(false);
		this.btnEditAccesories.setBounds(810, 251, 92, 23);
		carPanel.add(this.btnEditAccesories);
		this.btnEditAccesories.addActionListener(this);
		
		
		this.btnEditPrice = new JButton(" Edit ");
		this.btnEditPrice.setOpaque(false);
		this.btnEditPrice.setContentAreaFilled(false);
		this.btnEditPrice.setBorderPainted(false);
		this.btnEditPrice.setBounds(800, 81, 89, 23);
		carPanel.add(this.btnEditPrice);
		this.btnEditPrice.addActionListener(this);
		
		rentPanel = new JPanel();
		rentPanel.setBounds(0, 337, 975, 285);
		carPanel.add(rentPanel);
		rentPanel.setLayout(null);
		
		label = new JLabel("REVIEWS");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 5, 68, 17);
		rentPanel.add(label);
		
		// Create the JTable and the table model 
		TableModel rentTableModel = new RentTableModel(brand,server);
		rentsTable = new JTable(rentTableModel);
		((es.deusto.client.gui.RentTableModel) rentTableModel).setValues(server);
		//rentsTable.setBounds(106, 34, 682, 33);
		rentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rentsTable.setBackground(SystemColor.window);
		
		//Create the scroll pane and add the table to it
		scrollRents = new JScrollPane(rentsTable);
		scrollRents.setEnabled(false);
		scrollRents.getViewport().setBackground(Color.white);
		
		//Add the scroll pane to this panel.
		scrollRents.setBounds(106, 100, 682, 80);
		rentPanel.add(scrollRents);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(10, 251, 89, 23);
		rentPanel.add(btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowcarsAdmin showCarsAdmin = new ShowcarsAdmin(email);
				frame.dispose();
				frame.revalidate();
				frame.repaint();
			}
		});
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(699, 190, 89, 23);
		rentPanel.add(btnRemove);
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = rentsTable.getSelectedRow();
				try {
					server.deleteRent(server.getCarRents(brand).get(selectedRow).getid_rent());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				ShowDescriptionAdmin refresh = new ShowDescriptionAdmin(brand, email);
			}
		});
	
		btnsaveChanges = new JButton("Save Changes");
		btnsaveChanges.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				txtBrand.setEditable(false);
				txtModel.setEditable(false);
				txtType.setEditable(false);
				txtColour.setEditable(false);
				txtAccesories.setEditable(false);
				txtPrice.setEditable(false);
				txtMat.setEditable(false);
				txtRentR.setEditable(false);
				}
		});
		btnsaveChanges.setBounds(700, 251, 120, 23);
		rentPanel.add(btnsaveChanges);
		
	}
		

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton o = (JButton) arg0.getSource();
		if(o == btnEditBrand){
			this.txtBrand.setEditable(true);
		}else if(o == btnEditModel){
			this.txtModel.setEditable(true);
		}else if(o == btnEditType){
			this.txtType.setEditable(true);
		}else if(o == btnEditColour){
			this.txtColour.setEditable(true);
		}else if(o == btnEditMat){
			this.txtMat.setEditable(true);
		}else if(o == btnEditAccesories){
			this.txtAccesories.setEditable(true);
		}else if(o == btnEditPrice){
			this.txtPrice.setEditable(true);
		}else if(o == btnEditRentR){
			this.txtRentR.setEditable(true);
		}
	}
}