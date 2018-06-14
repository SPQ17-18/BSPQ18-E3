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

import es.deusto.server.data.Rent;
import es.deusto.server.remote.IRemote;
import es.deusto.server.remote.Remote;


public class ShowDescription {

	private JFrame frame;
	
	private JPanel carPanel;
	private JLabel lblImage;
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
	private JLabel lblRentR;
	private JTextField txtRentR;
	private JButton btnRent;
	private JButton btnGoBack;
	private JTable rentsTable;
	private JScrollPane scrollRents;
	private JPanel rentPanel;
	private JLabel label;
	private JTextPane txtRent;
	private JButton btnSend;
	private JComboBox<Integer> cmbRentR;
	private JLabel lCmbRentR;
	
	
	private String email;
	private static String brand;
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
	public ShowDescription(String brand, String email) throws RemoteException {
		
		
		// Create and set up the window.
		frame = new JFrame("Car Rent");
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
		this.brand = brand;
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
		
		lblImage = new JLabel("Image");
		lblImage.setBounds(46, 39, 232, 274);
		carPanel.add(lblImage);
		
		lblBrand = new JLabel("Brand:");
		lblBrand.setBounds(357, 60, 32, 23);
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
		
		lblRentR = new JLabel("RentR: ");
		lblRentR.setBounds(674, 136, 45, 23);
		carPanel.add(lblRentR);
		
		txtBrand = new JTextField(brand);
		txtBrand.setEditable(false);
		txtBrand.setBounds(415, 59, 177, 23);
		carPanel.add(txtBrand);
		txtBrand.setColumns(10);
		
		txtModel = new JTextField(server.
				getCarByBrand(brand).
				getModel());
		txtModel.setEditable(false);
		txtModel.setColumns(10);
		txtModel.setBounds(415, 94, 177, 23);
		carPanel.add(txtModel);
		
		txtType = new JTextField(server.getCarByBrand(brand).getType());
		txtType.setEditable(false);
		txtType.setColumns(10);
		txtType.setBounds(415, 128, 177, 23);
		carPanel.add(txtType);
		
		txtColor = new JTextField(server.getCarByBrand(brand).getColour());
		txtColor.setEditable(false);
		txtColor.setColumns(10);
		txtColor.setBounds(415, 162, 177, 23);
		carPanel.add(txtColor);
		
		txtPrice = new JTextField(""+server.getCarByBrand(brand).getPrice());
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBounds(716, 80, 92, 23);
		carPanel.add(txtPrice);
		
		lblMat = new JLabel("Mat: ");
		lblMat.setBounds(357, 196, 45, 23);
		carPanel.add(lblMat);
		
		txtMat = new JTextField("" + server.getCarByBrand(brand).getMat());
		txtMat.setEditable(false);
		txtMat.setColumns(10);
		txtMat.setBounds(415, 196, 177, 23);
		carPanel.add(txtMat);
		
		
		
		txtAccesories = new JTextPane();
		txtAccesories.setEditable(false);
		txtAccesories.setText(server.getCarByBrand(brand).getAccesories());
		txtAccesories.setBounds(357, 251, 460, 58);
		carPanel.add(txtAccesories);
		
		btnRent = new JButton("RENT");
		btnRent.setBounds(818, 81, 89, 23);
		carPanel.add(btnRent);
		btnRent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					server.rentCar(email, brand);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog (null, "You have rent "+brand, "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		rentPanel = new JPanel();
		rentPanel.setBounds(0, 337, 975, 285);
		carPanel.add(rentPanel);
		rentPanel.setLayout(null);
		
		label = new JLabel("Rents");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 5, 68, 17);
		rentPanel.add(label);
		
		txtRent = new JTextPane();
		txtRent.setBounds(106, 34, 682, 33);
		rentPanel.add(txtRent);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(780, 70, 89, 23);
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					server.addRent(server.getCarByBrand(brand),new Rent(txtRent.getText(), cmbRentR.getSelectedIndex()),server.getClient(email));
					JOptionPane.showMessageDialog(null, "Your comment and rate SEND!", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
					txtRent.setText("");
					cmbRentR.setSelectedIndex(0);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		rentPanel.add(btnSend);
		
		lblRentR = new JLabel("Rate:");
		lblRentR.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRentR.setBounds(699, 73, 68, 17);
		rentPanel.add(lblRentR);
		
		cmbRentR = new JComboBox<Integer>();
		for(int i=0;i<11;i++){
			cmbRentR.addItem(i);
		}
		cmbRentR.setBounds(735,70,40,25);
		rentPanel.add(cmbRentR);
		
		// Create the JTable and the table model 
		TableModel rentTableModel = new RentTableModel(brand, server);
		rentsTable = new JTable(rentTableModel);
		((es.deusto.client.gui.RentTableModel) rentTableModel).setValues(server);
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
				ShowCars showcars = new ShowCars(email);
				frame.dispose();
				frame.revalidate();
				frame.repaint();
			}
		});
		
	}
}

class RentTableModel extends AbstractTableModel {

/**
 * 
 */
private static final long serialVersionUID = 1L;

String[] columnNames = {"USER", "COMMENT"};
Object[][] data = null;

String brand;

public RentTableModel(String brand, IRemote server) {
	this.brand=brand;
}

@Override
public int getColumnCount() {
	return columnNames.length;
}

@Override
public String getColumnName(int col) {
	 return columnNames[col];
}

@Override
public int getRowCount() {
	 return data.length;
}

@Override
public Object getValueAt(int row, int col) {
	return data[row][col];
}

@Override
public boolean isCellEditable(int row, int col) {
	return false;
}

@Override
public void setValueAt(Object value, int row, int col) {
	data[row][col] = value;
    fireTableCellUpdated(row, col);
}

public void setValues(IRemote server) {
	try {
		if(server.getCarRents(brand).size()!= 0){
			data = new String[server.getCarRents(brand).size()][2];
		}
		else{
			data = new String[0][2];
		}
		for (int i = 0; i < server.getCarRents(brand).size(); i++)
		{
			data[i][0] = server.getCarRents(brand).get(i).getClient().getEmail();
			data[i][1] = server.getCarRents(brand).get(i).getId_rent();
		}
	} catch (RemoteException e) {
		e.printStackTrace();
	}
}

}

