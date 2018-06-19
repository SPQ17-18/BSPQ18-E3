package es.deusto.client.gui;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

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
import es.deusto.server.remote.Remote;


public class ShowCars {

	private JFrame frame;

	private JPanel carSearch;
	private JLabel lblSearch;
	private String textuser;
	private JComboBox cmbSearch;
	private String cmbSearchSelection;
	private JTextField textSearchUser;
	private JButton btnSearch;
	private JTable listOfCars;
	private JScrollPane scrollListCars;
	private JButton btnRefresh;
	private JButton btnLogOut;

	private static String email;
	private static String brand;
	private LogIn logIn;
	IRemote server;
	private ShowDescription showDescrip;
	//private String cl=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ShowCars(email);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowCars(String email) {
		
		// Create and set up the window.
		frame = new JFrame("Rental Car");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Display the window.
		frame.setBounds(0, 0, 991, 661);
		frame.setVisible(true);
		frame.setBackground(SystemColor.window);
		 frame.setLocationRelativeTo(null);
		frame.setSize(750, 600);
		
		// Initialize the contents of the frame.
		try {
			server = new Remote();
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
		carSearch.setLayout(new GridBagLayout());
		frame.getContentPane().add(carSearch, BorderLayout.CENTER);
		//Search.setVisible(false);
		
	/*	btnSearch = new JButton("Searaaaach");
		btnSearch.setVerticalAlignment(SwingConstants.TOP);
		btnSearch.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnSearch.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_btnAddACar = new GridBagConstraints();
		gbc_btnAddACar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddACar.gridx = 4;
		gbc_btnAddACar.gridy = 0;
		carSearch.add(btnSearch, gbc_btnAddACar);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					showDescrip = new ShowDescription(brand, email);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				frame.revalidate();
				frame.repaint();
			}
		});
		
	*/	
		
		// JLabel component about search message
		lblSearch = new JLabel("Car Search");
		lblSearch.setBackground(SystemColor.window);
		lblSearch.setForeground(new Color(0, 0, 0));
		lblSearch.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.fill= GridBagConstraints.BOTH;
		gbc_lblSearch.gridx = 1;
		gbc_lblSearch.gridy = 1;
		carSearch.add(lblSearch, gbc_lblSearch);
				
		// JComboBox component about choosing according what is the search
		cmbSearch = new JComboBox<Object>(Menu);
		cmbSearch.setBackground(SystemColor.window);
		cmbSearch.setForeground(new Color(0, 0, 0));
		cmbSearch.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		GridBagConstraints gbc_cmbSearch = new GridBagConstraints();
		gbc_cmbSearch.insets = new Insets(15, 0, 5, 5);
		gbc_cmbSearch.gridx = 1;
		gbc_cmbSearch.gridy = 2;
		gbc_cmbSearch.fill= GridBagConstraints.BOTH;
		carSearch.add(cmbSearch, gbc_cmbSearch);
		
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
		GridBagConstraints gbc_textSearchUser = new GridBagConstraints();
		gbc_textSearchUser.insets = new Insets(15, 0, 5, 5);
		gbc_textSearchUser.gridx = 2;
		gbc_textSearchUser.gridy = 2;
		gbc_textSearchUser.fill= GridBagConstraints.BOTH;
		carSearch.add(textSearchUser, gbc_textSearchUser);

		// JTable with all the available s
		
		// Create the JTable and the table model 
		final TableModel CarTableModel = new CarTableModel();
		((es.deusto.client.gui.CarTableModel) CarTableModel).setValues(server);
		 final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(CarTableModel);
		listOfCars = new JTable(CarTableModel);
		listOfCars.setRowHeight(40);
	    listOfCars.setSize(500,500 );
		listOfCars.setRowSorter(sorter);
		//listOfCars.set
		listOfCars.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOfCars.setBackground(SystemColor.window);
		listOfCars.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
    	//setViewportView(CarTableModel); //La tabla se verá dentro del panel de barras de desplazamiento   	
		//listOfCars.setVisible(true);
		
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
				
				
				try {
					showDescrip = new ShowDescription(brand, email);
					frame.dispose();
					frame.revalidate();
					frame.repaint();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		//Add the scroll pane to this panel.
		GridBagConstraints gbc_scrollListCars = new GridBagConstraints();
		gbc_scrollListCars.gridwidth = 2;
		gbc_scrollListCars.insets = new Insets(15, 0, 5, 5);
		gbc_scrollListCars.gridx = 1;
		gbc_scrollListCars.gridy = 3;
		gbc_scrollListCars.fill = GridBagConstraints.HORIZONTAL;
		carSearch.add(scrollListCars, gbc_scrollListCars);
		
		// Create JButton for search
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnSearch.setBackground(new Color(95, 158, 160));
		btnSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				String searchText = textSearchUser.getText();
				String types = (String) cmbSearch.getSelectedItem();
				if (searchText.length() != 0){
					if(types == "Brand"){
						RowFilter rowFilter = RowFilter.regexFilter(searchText, 0);
						((DefaultRowSorter<TableModel, Integer>) listOfCars.getRowSorter()).setRowFilter(rowFilter);
					}else if (types == "Model"){
						RowFilter rowFilter = RowFilter.regexFilter(searchText, 1);
						((DefaultRowSorter<TableModel, Integer>) listOfCars.getRowSorter()).setRowFilter(rowFilter);
					}else if(types == "Matricula"){
						RowFilter rowFilter = RowFilter.regexFilter(searchText, 2);
						((DefaultRowSorter<TableModel, Integer>) listOfCars.getRowSorter()).setRowFilter(rowFilter);	
					}
				}else{
					sorter.setRowFilter(null);
				}			
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(15, 0, 5, 0);
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 2;
		gbc_btnSearch.fill= GridBagConstraints.BOTH;
		carSearch.add(btnSearch, gbc_btnSearch);		
		
		// Create JButton for refreshing data of JTable
		btnRefresh = new JButton("Refresh");
		btnRefresh.setVerticalAlignment(SwingConstants.TOP);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorter.setRowFilter(null);
				textSearchUser.setText("");	
			}
		});
		btnRefresh.setBackground(UIManager.getColor("Button.light"));
		btnRefresh.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.insets = new Insets(0, 0, 5, 0);
		gbc_btnRefresh.gridx = 3;
		gbc_btnRefresh.gridy = 3;
		gbc_btnRefresh.fill= GridBagConstraints.HORIZONTAL;
		carSearch.add(btnRefresh, gbc_btnRefresh);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setVerticalAlignment(SwingConstants.TOP);
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logIn = new LogIn(null);
				frame.dispose();
				frame.revalidate();
				frame.repaint();
			}
		});
		btnLogOut.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnLogOut.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.gridx = 3;
		gbc_btnLogOut.gridy = 5;
		carSearch.add(btnLogOut, gbc_btnLogOut);
		
	}
	

	
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}

final class CarTableModel  extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] columnNames = { "Brand", "Colour", "Mat", "Type","Model", "Accesories", "Price" };
	String[][] data;
	//Object[][]data=null;
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
		//return data.size();
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		//return data.get(row);
		return data[row][col];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
//		data.get(row) = value;
		data[row][col]=(String) value;
        fireTableCellUpdated(row, col);
	}
	
	public void setValues(IRemote server) {
		System.out.println("********rellenando tabla");
		try {
			if(server.showCarsInStore().size()!= 0){
				//data = new ArrayList<>();
				System.out.println("********111111rellenando tabla");

				data=new String[server.showCarsInStore().size()][7];
				System.out.println("********22222222222rellenando tabla");

			}else{
					data = new String[0][7];
				}
			
			
			
			System.out.println("coches en tienda: " + server.showCarsInStore().size());
			List<Car> listCars = server.showCarsInStore();	
			for(int i = 0 ; i < listCars.size() ; i++)
				{
					System.out.println("********3333333333333rellenando tabla");
					Car tempCar = listCars.get(i);
					System.out.println("********+Coche: " + tempCar);

					data[i][0] = tempCar.getBrand();
					data[i][1] = tempCar.getColour();
					data[i][2] = "" + tempCar.getMat();
					data[i][3] = "" + tempCar.getType();
					data[i][4] = " "+tempCar.getModel();
					data[i][5] = " "+tempCar.getAccesories();
					data[i][6] = "" + tempCar.getPrice() + " €";
					/*data.add(new 
							Car(server.showCarsInStore().get(i).getMat(),
							server.showCarsInStore().get(i).getColour(),
							server.showCarsInStore().get(i).getBrand(),
							server.showCarsInStore().get(i).getModel(),
							server.showCarsInStore().get(i).getType(),
							server.showCarsInStore().get(i).getAccesories(),							
							server.showCarsInStore().get(i).getPrice()));*/
					System.out.println("********4444444444444444rellenando tabla");

				}
			
			// TODO: for que llame a setvalueat con los datos de cada columna
//			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}