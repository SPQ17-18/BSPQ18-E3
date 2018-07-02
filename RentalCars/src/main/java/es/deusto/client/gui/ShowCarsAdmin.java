package es.deusto.client.gui;

/*SHOW CARS ADMIN: Accessing as an admin (role = 1), all the cars in the database will be displayed in a table.
 * The admin cad ADD & DELETE cars.
 * */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import es.deusto.server.data.Car;
import es.deusto.server.remote.IRemote;
//import es.deusto.server.remote.Remote;
import es.deusto.server.remote.CarsRemote;


public class ShowCarsAdmin {

	private JFrame frame;

	private JPanel carSearch;
	private JLabel lblSearch;
	private JPanel panel;
	private JPanel btnPanel;
	private String textuser;
	private JComboBox/*<Object>*/ cmbSearch;
	private String cmbSearchSelection;
	private JTextField textSearchUser;
	private JButton btnSearch;
	private JTable listOfCars;
	private JScrollPane scrollListCars;
	private JButton btnRefresh;
	private JButton btnAddCar;
	private JButton btnDeleteCar;
	private JButton btnLogOut;
	//private JPanel pcar;

	private static String email;
	private static String brand;
	private LogIn logIn;
	private AddCar addCar;
	IRemote server;
	//private ShowDescriptionAdmin showDescriptionAdmin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ShowCarsAdmin(email);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowCarsAdmin(String email) {
		
		// Create and set up the window.
		frame = new JFrame("Rental Car");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Display the window.
		frame.setBounds(0, 0, 991, 661);
		frame.setVisible(true);
		frame.setBackground(SystemColor.window);
		 frame.setLocationRelativeTo(null);
		frame.setSize(750, 600);
		frame.setLayout(new BorderLayout());
		
		// Initialize the contents of the frame.
		try {
			server = new CarsRemote();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.email = email;
		initializeShowCars();
	}
	
	/**
	 * Initialize the contents of the CarSearch JPanel 
	 */
	private void initializeShowCars(){
		
		// initialization of items used
		String[] Menu = {"Brand", "Model", "Matricula"};

		// Beginning of car Search JPanel
		carSearch = new JPanel();
		carSearch.setBackground(SystemColor.window);
		carSearch.setLayout(new BorderLayout());
		
		frame.getContentPane().add(carSearch, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setLayout(new FlowLayout());
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		btnPanel = new JPanel();
		btnPanel.setBackground(SystemColor.window);
		btnPanel.setLayout(new FlowLayout());
		frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);

	
		btnAddCar = new JButton("Add car");
		btnAddCar.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnAddCar.setBackground(SystemColor.controlHighlight);
		
		btnAddCar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addCar = new AddCar(email);
				frame.dispose();
				frame.revalidate();
				frame.repaint();
			}
		});
		btnPanel.add(btnAddCar);
		
		// JLabel component about search message
		lblSearch = new JLabel("Car Search");
		lblSearch.setSize(100, 100);
		lblSearch.setBackground(SystemColor.window);
		lblSearch.setForeground(new Color(0, 0, 0));
		lblSearch.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
				
		// JComboBox component about choosing according what is the search
		cmbSearch = new JComboBox<Object>(Menu);
		cmbSearch.setSize(100, 100);
		cmbSearch.setBackground(SystemColor.window);
		cmbSearch.setForeground(new Color(0, 0, 0));
		cmbSearch.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		panel.add(cmbSearch);
		
		// JTextField component about the text that is going to be searched
		textSearchUser = new JTextField("");
		textSearchUser.setBackground(SystemColor.window);
		textSearchUser.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				cmbSearchSelection = (String) cmbSearch.getSelectedItem();
				textuser = textSearchUser.getText();
				if (textuser.equals("Enter text here")) textSearchUser.setText("");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String textuser = textSearchUser.getText();
				if (textuser.equals("")) textSearchUser.setText("");
				
			}
		});
		
		textSearchUser.setForeground(SystemColor.textInactiveText);
		textSearchUser.setColumns(20);
		textSearchUser.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		panel.add(textSearchUser);

		// JTable with all the available 
		
		// Create the JTable and the table model 
		final TableModel CarTableModel = new CarTableModel();
		JPanel panel = new JPanel(new BorderLayout());
		
		((es.deusto.client.gui.CarTableModel) CarTableModel).setValues(server);
		 final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(CarTableModel);
		listOfCars = new JTable(CarTableModel);
		listOfCars.setRowHeight(40);
		listOfCars.setRowSorter(sorter);
		listOfCars.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOfCars.setBackground(SystemColor.window);
		listOfCars.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
		listOfCars.setVisible(true);
    	
		
		//Create the scroll pane and add the table to it
		scrollListCars = new JScrollPane(listOfCars);
		scrollListCars.setEnabled(false);
		scrollListCars.getViewport().setBackground(Color.white);
		Dimension d = listOfCars.getPreferredSize();
		scrollListCars.setPreferredSize(
		    new Dimension(d.width,listOfCars.getRowHeight()*listOfCars.getRowCount()+25));
		
		listOfCars.addMouseListener(new MouseAdapter() { // Mouse Listener -> go to a specific car
			@Override
			public void mouseClicked(MouseEvent e) {
				//Select a row to see the description window
				listOfCars.getSelectedRow();
				String brand = (String) CarTableModel.getValueAt(listOfCars.getSelectedRow(), 0);
				
				
				/*showDescriptionAdmin = new ShowDescriptionAdmin(brand, email);
				frame.dispose();
				frame.revalidate();
				frame.repaint();*/

			}
		});
		
		//Add the scroll pane to this panel.
		carSearch.add(scrollListCars, BorderLayout.CENTER);
		
		// Create JButton for search
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnSearch.setBackground(new Color(95, 158, 160));
		btnSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				String searchText = textSearchUser.getText();
				String type = (String) cmbSearch.getSelectedItem();
				if (searchText.length() != 0){
					if(type == "Brand"){
						@SuppressWarnings("rawtypes")
						RowFilter rowFilter = RowFilter.regexFilter(searchText, 0);
						((DefaultRowSorter<TableModel, Integer>) listOfCars.getRowSorter()).setRowFilter(rowFilter);
					}else if (type == "Model"){
						@SuppressWarnings("rawtypes")
						RowFilter rowFilter = RowFilter.regexFilter(searchText, 1);
						((DefaultRowSorter<TableModel, Integer>) listOfCars.getRowSorter()).setRowFilter(rowFilter);
						System.out.println("seleccionado MODEL");
					}else if(type == "Matricula"){
						@SuppressWarnings("rawtypes")
						RowFilter rowFilter = RowFilter.regexFilter(searchText, 2);
						((DefaultRowSorter<TableModel, Integer>) listOfCars.getRowSorter()).setRowFilter(rowFilter);	
					}
				}else{
					sorter.setRowFilter(null);
				}			
			}
		});
		
		btnPanel.add(btnSearch);
		
		// Create JButton for refreshing data of JTable
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorter.setRowFilter(null);
				textSearchUser.setText("");	
			}
		});
		btnRefresh.setBackground(UIManager.getColor("Button.light"));
		btnRefresh.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		
		btnPanel.add(btnRefresh);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//logIn = new LogIn(null);
				frame.dispose();
				frame.revalidate();
				frame.repaint();
			}
		});
		btnLogOut.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnLogOut.setBackground(SystemColor.controlHighlight);
		
		btnPanel.add(btnLogOut);
		
		btnDeleteCar = new JButton("Delete");
		btnDeleteCar.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnDeleteCar.setBackground(SystemColor.controlHighlight);
		
		
		btnDeleteCar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = listOfCars.getSelectedRow();
				try {
					server.deleteCar(Integer.parseInt((String) CarTableModel.getValueAt(selectedRow, 2)));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				ShowCarsAdmin refresh = new ShowCarsAdmin(email);
				
			}
		});
		btnPanel.add(btnDeleteCar);
		
		
		frame.revalidate();
		frame.repaint();
	
	
	

	
}

}


