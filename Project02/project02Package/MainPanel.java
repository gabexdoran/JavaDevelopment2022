package project02Package;

import javax.swing.JPanel;

import pointOfSale.Product;
import pointOfSale.ProductCollection;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class MainPanel extends JPanel {
	private JTextField textFieldForSlider;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldName;
	private JTextField textFieldID;
	private JTextField textFieldPrice;
	private JTextField textFieldWeight;
	private JTextField textFieldQuantity;
	private JTextField textFieldCategory;
	private ProductCollection myProducts;
	
	public void doClose() {
		myProducts.writeFile("./testwrite.txt");
	}
	
	public MainPanel() {
		myProducts = new ProductCollection("./data.txt");
		
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 800, 500);
		add(tabbedPane);
		
		JPanel panelTitlePage = new JPanel();
		panelTitlePage.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Title Page", null, panelTitlePage, null);
		panelTitlePage.setLayout(null);
		
		JTextPane textPaneTitle = new JTextPane();
		textPaneTitle.setBackground(Color.LIGHT_GRAY);
		textPaneTitle.setText("My Store Manager:");
		textPaneTitle.setFont(new Font("Times New Roman", Font.BOLD, 32));
		textPaneTitle.setBounds(275, 11, 283, 44);
		panelTitlePage.add(textPaneTitle);
		
		JLabel lblKrogerImage = new JLabel("");
		lblKrogerImage.setIcon(new ImageIcon(MainPanel.class.getResource("/project02Package/krogerLogo.png")));
		lblKrogerImage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKrogerImage.setBounds(295, -113, 833, 574);
		panelTitlePage.add(lblKrogerImage);
		
		JTextPane txtpnByGabrielDoran = new JTextPane();
		txtpnByGabrielDoran.setText("By: Gabriel Doran\r\nJava Development Spring 2022");
		txtpnByGabrielDoran.setBackground(Color.LIGHT_GRAY);
		txtpnByGabrielDoran.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		txtpnByGabrielDoran.setBounds(275, 50, 184, 44);
		panelTitlePage.add(txtpnByGabrielDoran);
		
		JPanel panelHomePage = new JPanel();
		panelHomePage.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Home Page", null, panelHomePage, null);
		panelHomePage.setLayout(null);
	
		textFieldForSlider = new JTextField();
		textFieldForSlider.setBounds(55, 107, 505, 20);
		panelHomePage.add(textFieldForSlider);
		textFieldForSlider.setColumns(10);
		
		JSlider sliderViewByID = new JSlider();
		sliderViewByID.setValue(5);
		sliderViewByID.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Product currentProduct = null;
				String currentProductStr = " ";
				
				currentProduct = myProducts.retrieveProduct(sliderViewByID.getValue());
				currentProductStr = currentProduct.toString();
				textFieldForSlider.setText(currentProductStr);
			}
		});
		sliderViewByID.setSnapToTicks(true);
		sliderViewByID.setPaintLabels(true);
		sliderViewByID.setPaintTicks(true);
		sliderViewByID.setMinorTickSpacing(1);
		sliderViewByID.setMaximum(10);
		sliderViewByID.setMinimum(1);
		sliderViewByID.setBounds(55, 40, 333, 56);
		panelHomePage.add(sliderViewByID);
		
		JTextPane txtpnUseTheSlider = new JTextPane();
		txtpnUseTheSlider.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		txtpnUseTheSlider.setBackground(Color.LIGHT_GRAY);
		txtpnUseTheSlider.setText("Use the slider to view an item's details by ID:");
		txtpnUseTheSlider.setBounds(55, 11, 214, 20);
		panelHomePage.add(txtpnUseTheSlider);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(580, 40, 205, 56);
		panelHomePage.add(scrollPane);
		
		JTextPane txtpnDisplayDisc = new JTextPane();
		scrollPane.setViewportView(txtpnDisplayDisc);
		
		JRadioButton rdbtnDiscounted = new JRadioButton("Discounted");
		rdbtnDiscounted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator itr = myProducts.getIterator();
				Product currentProduct = null;
				
				while (itr.hasNext()) {
					currentProduct = (Product) itr.next();
					
					if (currentProduct.get_productDiscountCode().equalsIgnoreCase("5")) {
						txtpnDisplayDisc.setText(currentProduct.toString());
					}
				}
			}
		});
		buttonGroup.add(rdbtnDiscounted);
		rdbtnDiscounted.setBounds(451, 40, 109, 23);
		panelHomePage.add(rdbtnDiscounted);
		
		JRadioButton rdbtnNotDiscounted = new JRadioButton("Not Discounted");
		rdbtnNotDiscounted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator itr = myProducts.getIterator();
				Product currentProduct = null;
				
				while (itr.hasNext()) {
					currentProduct = (Product) itr.next();
					
					if (currentProduct.get_productDiscountCode().equalsIgnoreCase("0")) {
						txtpnDisplayDisc.setText(currentProduct.toString());
					}
				}
			}
		});
		buttonGroup.add(rdbtnNotDiscounted);
		rdbtnNotDiscounted.setBounds(451, 73, 109, 23);
		panelHomePage.add(rdbtnNotDiscounted);
		
		JTextPane txtpnSelectRadioButton = new JTextPane();
		txtpnSelectRadioButton.setBackground(Color.LIGHT_GRAY);
		txtpnSelectRadioButton.setText("Select to display a random discounted OR non-discounted food:");
		txtpnSelectRadioButton.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		txtpnSelectRadioButton.setBounds(451, 11, 334, 32);
		panelHomePage.add(txtpnSelectRadioButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(55, 171, 333, 257);
		panelHomePage.add(scrollPane_1);
		
		JTextArea textAreaDisplayAll = new JTextArea();
		scrollPane_1.setViewportView(textAreaDisplayAll);
		textAreaDisplayAll.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Iterator itr = myProducts.getIterator();
		
		while (itr.hasNext()) {
			textAreaDisplayAll.append(itr.next().toString() + "\n");
		}
		
		textFieldName = new JTextField();
		textFieldName.setBounds(456, 207, 86, 20);
		panelHomePage.add(textFieldName);
		textFieldName.setColumns(10);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setBackground(Color.LIGHT_GRAY);
		txtpnName.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtpnName.setText("Name:");
		txtpnName.setBounds(399, 207, 37, 20);
		panelHomePage.add(txtpnName);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setBackground(Color.LIGHT_GRAY);
		txtpnId.setText("ID:");
		txtpnId.setBounds(399, 238, 22, 20);
		panelHomePage.add(txtpnId);
		
		JTextPane txtpnPrice = new JTextPane();
		txtpnPrice.setBackground(Color.LIGHT_GRAY);
		txtpnPrice.setText("Price:");
		txtpnPrice.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtpnPrice.setBounds(399, 269, 37, 20);
		panelHomePage.add(txtpnPrice);
		
		JTextPane txtpnWeight = new JTextPane();
		txtpnWeight.setBackground(Color.LIGHT_GRAY);
		txtpnWeight.setText("Weight:");
		txtpnWeight.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtpnWeight.setBounds(399, 300, 44, 20);
		panelHomePage.add(txtpnWeight);
		
		JTextPane txtpnQuantity = new JTextPane();
		txtpnQuantity.setText("Quantity:");
		txtpnQuantity.setBackground(Color.LIGHT_GRAY);
		txtpnQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtpnQuantity.setBounds(399, 331, 51, 20);
		panelHomePage.add(txtpnQuantity);
		
		JTextPane txtpnCategory = new JTextPane();
		txtpnCategory.setText("Category:");
		txtpnCategory.setBackground(Color.LIGHT_GRAY);
		txtpnCategory.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtpnCategory.setBounds(399, 362, 51, 20);
		panelHomePage.add(txtpnCategory);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(456, 238, 86, 20);
		panelHomePage.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setText("");
		textFieldPrice.setBounds(456, 269, 86, 20);
		panelHomePage.add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		textFieldWeight = new JTextField();
		textFieldWeight.setBounds(456, 300, 86, 20);
		panelHomePage.add(textFieldWeight);
		textFieldWeight.setColumns(10);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(456, 331, 86, 20);
		panelHomePage.add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setBounds(456, 362, 86, 20);
		panelHomePage.add(textFieldCategory);
		textFieldCategory.setColumns(10);
		
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currentID = 0;
				
				currentID = Integer.parseInt(textFieldID.getText());
				
				myProducts.removeProduct(currentID);
				textAreaDisplayAll.update(textAreaDisplayAll.getGraphics());
			}
		});
		btnDeleteItem.setBounds(399, 393, 89, 23);
		panelHomePage.add(btnDeleteItem);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product currentProduct = new Product();
				
				currentProduct.set_productName(textFieldName.getText());
				currentProduct.set_productID(Integer.parseInt(textFieldID.getText()));
				currentProduct.set_productPrice(Double.parseDouble(textFieldPrice.getText()));
				currentProduct.set_productWeight(Double.parseDouble(textFieldWeight.getText()));
				currentProduct.set_productQuantity(Integer.parseInt(textFieldQuantity.getText()));
				currentProduct.set_productCategory(textFieldCategory.getText());
				
				myProducts.addProduct(currentProduct);
				textAreaDisplayAll.update(textAreaDisplayAll.getGraphics());
			}
		});
		btnAddItem.setBounds(399, 173, 89, 23);
		panelHomePage.add(btnAddItem);
		
		JTextPane txtpnrequiresAllFields = new JTextPane();
		txtpnrequiresAllFields.setText("(Requires all fields to be filled out with VALID values)");
		txtpnrequiresAllFields.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtpnrequiresAllFields.setBackground(Color.LIGHT_GRAY);
		txtpnrequiresAllFields.setBounds(498, 173, 275, 23);
		panelHomePage.add(txtpnrequiresAllFields);
		
		JTextPane txtpnonlyRequiresA = new JTextPane();
		txtpnonlyRequiresA.setText("(Only requires a VALID ID)");
		txtpnonlyRequiresA.setBackground(Color.LIGHT_GRAY);
		txtpnonlyRequiresA.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtpnonlyRequiresA.setBounds(498, 393, 178, 20);
		panelHomePage.add(txtpnonlyRequiresA);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuItemQuit = new JMenu();
		menuItemQuit.setText("Quit");
		menuBar.setBounds(0, 450, 101, 22);
		panelHomePage.add(menuBar);
		menuBar.add(menuItemQuit);
		menuItemQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doClose();
				System.exit(0);
			}
		});
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}