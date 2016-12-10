import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Team 6: Coffee Beans
 * Sneha Akarapu, Kyle Watkins, Preston Jackson, Sarah Taff, Alejandro Delgadillo, Ben Deleus
 * 
 * CoffeeShop
 */

public class CoffeeShop extends JFrame implements ActionListener, ItemListener {	
	
	// holds all orderItems in your cart
	ArrayList<OrderItem> cart = new ArrayList<OrderItem>();
	
	JPanel eastPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel row1 = new JPanel();
	JPanel row2 = new JPanel();
	JPanel row3 = new JPanel();
	
	JPanel titlePanel = new JPanel();
	JPanel orderPanel = new JPanel();
	JPanel orderSettingsPanel = new JPanel();
	JPanel checkoutPanel = new JPanel();
	JFrame itemView = new JFrame();
	JFrame checkoutView = new JFrame();
	JLabel itemtotalLBL = new JLabel();
	JLabel itemNameLbl = new JLabel();
	JPanel radioPanel = new JPanel();
	JPanel sizePanel = new JPanel();
	JPanel tempPanel = new JPanel();

	JButton checkoutButton = new JButton("Check Out");
	JButton editItem = new JButton("Edit Selected Item");
	JButton removeItem = new JButton("Remove Selected Item");
	
	//Radio Buttons
	ButtonGroup sizeGroup = new ButtonGroup();
	JRadioButton smallRB = new JRadioButton("Small",true),
			mediumRB = new JRadioButton("Medium (+ $1.00)", false), 
			largeRB = new JRadioButton("Large (+ $3.00)", false);
	
	ButtonGroup tempGroup = new ButtonGroup();
	JRadioButton frozenRB = new JRadioButton("Frozen ", false),
			icedRB = new JRadioButton("Iced ", false),
			hotRB = new JRadioButton("Hot ", true);

	
	ButtonGroup paymentRB = new ButtonGroup();
	JRadioButton CashRB, CreditDebitRB, CheckRB;
	
	
	//Coffee Button Images
	ImageIcon darkIcon = new ImageIcon(new ImageIcon("dark.jpg").getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH));
	ImageIcon mediumIcon = new ImageIcon(new ImageIcon("medium.jpg").getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH));
	ImageIcon blondeIcon = new ImageIcon(new ImageIcon("blonde.jpg").getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH));
	ImageIcon mochaIcon = new ImageIcon(new ImageIcon("Mocha.jpeg").getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH));
	ImageIcon vanillaIcon = new ImageIcon(new ImageIcon("Vanilla.png").getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH));
	ImageIcon decafIcon = new ImageIcon(new ImageIcon("decaf.png").getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH));
	ImageIcon espressoIcon = new ImageIcon(new ImageIcon("espresso.png").getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH));
	ImageIcon cocoIcon = new ImageIcon(new ImageIcon("hotCoco.jpg").getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH));
	ImageIcon teaIcon = new ImageIcon(new ImageIcon("Tea.png").getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH));
	
	
	//Coffee Buttons
	//-----------------------------------
	JButton darkButton = new JButton(darkIcon);
	JButton mediumButton = new JButton(mediumIcon);
	JButton blondeButton = new JButton(blondeIcon);
	JButton mochaButton = new JButton(mochaIcon);
	JButton vanillaButton = new JButton(vanillaIcon);
	JButton decafButton = new JButton(decafIcon);
	JButton espressoButton = new JButton(espressoIcon);
	JButton cocoButton = new JButton(cocoIcon);
	JButton teaButton = new JButton(teaIcon);
	//-----------------------------------
	
	JLabel orderTotalLbl = new JLabel("$0.00");
	JLabel itemSizeLbl = new JLabel("Size:");
	JLabel itemTempLbl = new JLabel("Temperature:");
	
	DefaultTableModel model;
	JTable checkoutTable;
	JScrollPane sp;
	
	JButton addItem = new JButton("Add");
	JButton cancelItem = new JButton("Cancel");
	JButton cancelCheckout = new JButton("Cancel");
	
	//For edit selected item
	JButton doneEditing = new JButton("Done");
	
	int cartItems = 0;
	int numOrderItems = 0;
	BigDecimal orderTotal = new BigDecimal("0.00");
	BigDecimal one = new BigDecimal("1");
	BigDecimal three = new BigDecimal("3");
	
	Boolean editing = false;
	
	//sounds for adding and removing
	AudioClip addedSound, removedSound;
	
	//Order Item Class
	class OrderItem{
		String CoffeeType;
		String itemName;
		BigDecimal price;
		String size;
		String temp;
		
		public OrderItem(){
			itemName = "item";
			price = new BigDecimal("1.00");
			size = "Small";
			temp = "Hot ";
		}
		
		public OrderItem(String name){
			itemName = name;
		}
		
		//returns the string array - item description and price
		public String[] getRow() {
			
			//displays to the table with price + size cost
			if(size == "Medium"){
				price = price.add(one);
			}else if(size == "Large"){
				price = price.add(three);
			}
			
			NumberFormat money = NumberFormat.getCurrencyInstance();
			String price$ = money.format(price.doubleValue());
			String row[] = {"  " + size + " " + temp +itemName, "   " + price$};
			return row;
		}
		
	}

	public CoffeeShop() {
		
		// Table Settings
		//--------------------------------------------------
		model = new DefaultTableModel(0, 2) {
			public boolean isCellEditable(int row, int column) {
				return false; // makes table cells not-editable
			}
		};
		
		checkoutTable = new JTable(model);
		sp = new JScrollPane(checkoutTable);
		
		Object[] columns = { "    Order Items (" + numOrderItems +")" , " Price" };
		model.setColumnIdentifiers(columns);
		
		checkoutTable.setRowHeight(40);
		checkoutTable.setFont(new Font("Arial", Font.PLAIN, 13));

		checkoutTable.setGridColor(Color.BLACK);// colors grid lines
		
		checkoutTable.getTableHeader().setFont(new Font("Arial",Font.PLAIN, 25));
		checkoutTable.getColumnModel().getColumn(0).setPreferredWidth(240);
		checkoutTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		
		//--------------------------------------------------
		
		
		// big order total $$ label
		orderTotalLbl.setFont(new Font("Courier", Font.PLAIN, 40));
		orderTotalLbl.setForeground(new Color(128, 64, 0));
		orderTotalLbl.setBackground(Color.WHITE);
		orderTotalLbl.setOpaque(true);
		orderTotalLbl.setPreferredSize(new Dimension(315, 60));
		orderTotalLbl.setHorizontalAlignment(JLabel.CENTER);
		//-------------------------------------
		
		//color panels
		eastPanel.setBackground(new Color(191, 128, 64));
		buttonPanel.setBackground(new Color(191, 128, 64));
		mainPanel.setBackground(new Color(191, 128, 64));
		row1.setBackground(new Color(191, 128, 64));
		row2.setBackground(new Color(191, 128, 64));
		row3.setBackground(new Color(191, 128, 64));
		
		buttonPanel.setPreferredSize(new Dimension(320,50));
		eastPanel.setPreferredSize(new Dimension(350,this.getHeight()));
		
		//button action listeners
		addItem.addActionListener(this);
		cancelItem.addActionListener(this);
		editItem.addActionListener(this);
		removeItem.addActionListener(this);
		checkoutButton.addActionListener(this);
		darkButton.addActionListener(this);
		mediumButton.addActionListener(this);
		blondeButton.addActionListener(this);
		mochaButton.addActionListener(this);
		vanillaButton.addActionListener(this);
		decafButton.addActionListener(this);
		espressoButton.addActionListener(this);
		cocoButton.addActionListener(this);
		teaButton.addActionListener(this);
		doneEditing.addActionListener(this);
		
		//radio button item listeners
		smallRB.addItemListener(this);
		mediumRB.addItemListener(this);
		largeRB.addItemListener(this);
		frozenRB.addItemListener(this);
		hotRB.addItemListener(this);
		icedRB.addItemListener(this);
		
		//added all size radio buttons to size group
		sizeGroup.add(smallRB);
		sizeGroup.add(mediumRB);
		sizeGroup.add(largeRB);
		
		//resizing all of our buttons
		darkButton.setPreferredSize(new Dimension(225,225));
		mediumButton.setPreferredSize(new Dimension(225,225));
		blondeButton.setPreferredSize(new Dimension(225,225));
		mochaButton.setPreferredSize(new Dimension(225,225));
		vanillaButton.setPreferredSize(new Dimension(225,225));
		decafButton.setPreferredSize(new Dimension(225,225));
		espressoButton.setPreferredSize(new Dimension(225,225));
		cocoButton.setPreferredSize(new Dimension(225,225));
		teaButton.setPreferredSize(new Dimension(225,225));
		checkoutButton.setPreferredSize(new Dimension(320, 60));
		
		sp.setPreferredSize(new Dimension(320,530));

		//laying out the GUI
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(editItem);
		buttonPanel.add(removeItem);
		
		eastPanel.add(sp);
		eastPanel.add(buttonPanel);
		eastPanel.add(orderTotalLbl);
		eastPanel.add(checkoutButton);
		
		row1.add(darkButton);
		row1.add(Box.createHorizontalStrut(20));
		row1.add(mediumButton);
		row1.add(Box.createHorizontalStrut(20));
		row1.add(blondeButton);
		
		row2.add(mochaButton);
		row2.add(Box.createHorizontalStrut(20));
		row2.add(vanillaButton);
		row2.add(Box.createHorizontalStrut(20));
		row2.add(decafButton);
		
		row3.add(espressoButton);
		row3.add(Box.createHorizontalStrut(20));
		row3.add(cocoButton);
		row3.add(Box.createHorizontalStrut(20));
		row3.add(teaButton);
		
		mainPanel.setLayout(new GridLayout(3,1));	
		mainPanel.add(row1);
		mainPanel.add(row2);
		mainPanel.add(row3);
		
		add(mainPanel, BorderLayout.CENTER);
		add(eastPanel, BorderLayout.EAST);
	}

	public static void main(String[] args) {
		CoffeeShop app = new CoffeeShop();

		app.setSize(1120,750);//optimal app size
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setTitle("Coffee Beans' Coffee Shop");
		app.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == addItem){
			//ReEnables main JFrame
			this.setEnabled(true);
			
			//adds item from cart to the table
			//clears pop up frame
			model.insertRow(cartItems, cart.get(cartItems).getRow());
			
			orderTotal = orderTotal.add(cart.get(cartItems).price);
			orderTotalLbl.setText("$" + orderTotal);
			
			cartItems++;
			orderPanel.removeAll();
			orderSettingsPanel.removeAll();
			radioPanel.removeAll();
			sizePanel.removeAll();
			tempPanel.removeAll();
			itemView.dispose();
			
			//updates number of items displayed in header
			numOrderItems++;
			Object[] columns = { "    Order Items (" + numOrderItems +")" , " Price" };
			model.setColumnIdentifiers(columns);
			checkoutTable.getColumnModel().getColumn(0).setPreferredWidth(240);
			checkoutTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		}
		
		//-----East Panel Buttons-----------
		if(e.getSource() == editItem){
			//Disables main JFrame
			this.setEnabled(false);
			//edits selected item
			//brings up pop up window for changes
			//creates a temporary Edit Item
			
			if(checkoutTable.getSelectedRow() > -1) {
				editing = true;
				
				//take item price off of total
				orderTotal = orderTotal.subtract(cart.get(checkoutTable.getSelectedRow()).price);
				orderTotalLbl.setText("$" + orderTotal);
				
				//launches correct pop up screen
				if(cart.get(checkoutTable.getSelectedRow()).CoffeeType == "plain"){
					plainCoffeeFrame(cart.get(checkoutTable.getSelectedRow()).itemName, 4.00);
				}else if(cart.get(checkoutTable.getSelectedRow()).CoffeeType == "special"){
					specialCoffeeFrame(cart.get(checkoutTable.getSelectedRow()).itemName, 5.00);
				}else if(cart.get(checkoutTable.getSelectedRow()).CoffeeType == "non"){
					nonCoffeeFrame(cart.get(checkoutTable.getSelectedRow()).itemName, 3.00);
				}
				
				radioPanel.setPreferredSize(new Dimension(300,110));
				orderSettingsPanel.add(radioPanel, BorderLayout.CENTER);
				orderSettingsPanel.add(itemtotalLBL);
				
				itemView.add(orderSettingsPanel);
				itemView.add(orderPanel, BorderLayout.SOUTH);

				//alters pop up to have done button rather than add and cancel
				orderPanel.removeAll();
				orderPanel.add(doneEditing);
				
				//set RadioButtons the item's values
				sizeGroup.clearSelection();
				
				if(cart.get(checkoutTable.getSelectedRow()).size == "Small"){
					smallRB.setSelected(true);
				}else if(cart.get(checkoutTable.getSelectedRow()).size == "Medium"){
					mediumRB.setSelected(true);
				}else if(cart.get(checkoutTable.getSelectedRow()).size == "Large"){
					largeRB.setSelected(true);
				}
				
				tempGroup.clearSelection();
				
				if(cart.get(checkoutTable.getSelectedRow()).temp == "Frozen "){
					frozenRB.setSelected(true);
				}else if(cart.get(checkoutTable.getSelectedRow()).temp == "Iced "){
					icedRB.setSelected(true);
				}else if(cart.get(checkoutTable.getSelectedRow()).temp == "Hot "){
					hotRB.setSelected(true);
				}
				
			}
		}
		
		if(e.getSource() == doneEditing){
			//ReEnables main JFrame
			this.setEnabled(true);
			
			//closes editing dialogue
			//updates table and total price label
			
			//updates cart Item with newly created "edited item"
			cart.get(checkoutTable.getSelectedRow()).price = cart.get(cart.size()-1).price;
			cart.get(checkoutTable.getSelectedRow()).size = cart.get(cart.size()-1).size;
			cart.get(checkoutTable.getSelectedRow()).temp = cart.get(cart.size()-1).temp;
			
			//adds item price + size cost to Order Total Label
			if(cart.get(checkoutTable.getSelectedRow()).size == "Small"){
				orderTotal = orderTotal.add(cart.get(checkoutTable.getSelectedRow()).price);
			}else if(cart.get(checkoutTable.getSelectedRow()).size == "Medium"){
				orderTotal = orderTotal.add(cart.get(checkoutTable.getSelectedRow()).price.add(one));
			}else if(cart.get(checkoutTable.getSelectedRow()).size == "Large"){
				orderTotal = orderTotal.add(cart.get(checkoutTable.getSelectedRow()).price.add(three));
			}
			
			orderTotalLbl.setText("$" + orderTotal);
			
			//adds newly edited item to the table
			//removes old unedited item from table
			model.insertRow(checkoutTable.getSelectedRow(), cart.get(checkoutTable.getSelectedRow()).getRow());
			model.removeRow(checkoutTable.getSelectedRow()+1);
			
			//clears the pop up frame
			orderPanel.removeAll();
			orderSettingsPanel.removeAll();
			radioPanel.removeAll();
			sizePanel.removeAll();
			tempPanel.removeAll();
			itemView.dispose();
			editing = false;
			
			//takes out the temporary edit Item
			cart.remove(cart.size()-1);
		}
		
		//takes an item out of table and cart
		if(e.getSource() == removeItem){
			if(checkoutTable.getSelectedRow() > -1) {
				orderTotal = orderTotal.subtract(cart.get(checkoutTable.getSelectedRow()).price);
				orderTotalLbl.setText("$" + orderTotal);

				cart.remove(checkoutTable.getSelectedRow());
				model.removeRow(checkoutTable.getSelectedRow());
				cartItems--;
				
				//updates number of items displayed in header
				numOrderItems--;
				Object[] columns = { "    Order Items (" + numOrderItems +")" , " Price" };
				model.setColumnIdentifiers(columns);
				checkoutTable.getColumnModel().getColumn(0).setPreferredWidth(240);
				checkoutTable.getColumnModel().getColumn(1).setPreferredWidth(80);
			}
		}
		
		//Cancel order item - removes item from cart and closes window
		if(e.getSource() == cancelItem){
			//ReEnables main JFrame
			this.setEnabled(true);
			
			if(cartItems > -1){
				cart.remove(cart.size()-1);
			}

			orderPanel.removeAll();
			orderSettingsPanel.removeAll();
			radioPanel.removeAll();
			sizePanel.removeAll();
			tempPanel.removeAll();
			itemView.dispose();
		}
		
		//All Menu Items call a function that creates a pop up frame
		//plain coffees (top row) are $4.00
		//specialty coffees (middle row) are $5.00
		//non coffees (bottom row) are $6.00
		if(e.getSource() == darkButton){
			plainCoffeeFrame("Dark Roast Coffee", 4.00);
		}
		if(e.getSource() == mediumButton){
			plainCoffeeFrame("Medium Roast Coffee", 4.00);
		}
		if(e.getSource() == blondeButton){
			plainCoffeeFrame("Blonde Roast Coffee", 4.00);
		}
		if(e.getSource() == mochaButton){
			specialCoffeeFrame("Caffe Mocha", 5.00);
		}
		if(e.getSource() == vanillaButton){
			specialCoffeeFrame("Vanilla Latte", 5.00);
		}
		if(e.getSource() == decafButton){
			plainCoffeeFrame("Decaf Coffee", 4.00);
		}
		if(e.getSource() == espressoButton){
			nonCoffeeFrame("Espresso", 3.00);
		}
		if(e.getSource() == cocoButton){
			nonCoffeeFrame("Hot Chocolate", 3.00);
		}
		if(e.getSource() == teaButton){
			nonCoffeeFrame("Tea", 3.00);
		}
		
		//All Check Out screens carried out here
		if(e.getSource() == checkoutButton){
			//Disables main JFrame
			//this.setEnabled(false);
			
			checkoutView.setSize(400, 400);
			checkoutView.setTitle("Checkout");
			checkoutView.setVisible(true);
			
			checkoutPanel.add(cancelCheckout);
			checkoutView.add(checkoutPanel, BorderLayout.SOUTH);
			
			JPanel radiocheckoutPanel = new JPanel();
			JPanel radioPanelPanel = new JPanel();
			
			//Adding Radio Buttons for checkout
			CashRB = new JRadioButton("Cash", true);
			CashRB.addItemListener(this);
			CreditDebitRB = new JRadioButton("Credit or Debit Card", false);
			CreditDebitRB.addItemListener(this);
			CheckRB = new JRadioButton("Check", false);
			CheckRB.addItemListener(this);
			paymentRB.add(CashRB);
			paymentRB.add(CreditDebitRB);
			paymentRB.add(CheckRB);
		
			radiocheckoutPanel.add(CashRB);
			radiocheckoutPanel.add(CreditDebitRB);
			radiocheckoutPanel.add(CheckRB);
			radioPanelPanel.add(radiocheckoutPanel, new GridLayout(3,1));
			checkoutView.add(radioPanelPanel, BorderLayout.CENTER);
		}
	}
	
	//Creates Plain Coffee Pop Up Frame
	public void plainCoffeeFrame(String itemName, double price){
		//Disables main JFrame
		this.setEnabled(false);
		//Sets title name for later use
		OrderItem item = new OrderItem();
		cart.add(item);
		
		item.CoffeeType = "plain";
		item.itemName = itemName;
		
		itemView.setUndecorated(true);
		itemView.setLocation(this.getWidth() /3 , this.getHeight() / 4);
		itemView.setSize(300, 225);
		itemView.setTitle(item.itemName);
		itemView.setVisible(true);
		itemView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Coffee Price
		item.price = new BigDecimal(price);

		itemtotalLBL.setFont(new Font("Arial", Font.ITALIC, 20));
		itemtotalLBL.setText("Item Total: ");
		radioPanel.setPreferredSize(new Dimension(300,110));
		orderSettingsPanel.add(radioPanel, BorderLayout.CENTER);
		orderSettingsPanel.add(itemtotalLBL);
		
		itemView.add(orderSettingsPanel);
		orderPanel.add(cancelItem);
		orderPanel.add(addItem);
		itemView.add(orderPanel, BorderLayout.SOUTH);

		//adds item name to pop up
		itemNameLbl.setFont(new Font("Arial", Font.BOLD, 15));
		itemNameLbl.setText(item.itemName + "  ($4.00)");
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		titlePanel.add(itemNameLbl);
		itemView.add(titlePanel, BorderLayout.NORTH);
		
		
		//adding size radio buttons
		sizePanel.setLayout(new GridLayout(4,1));
		sizePanel.add(itemSizeLbl);
		sizePanel.add(smallRB);
		sizePanel.add(mediumRB);
		sizePanel.add(largeRB);
		
		//resetting selections
		sizeGroup.clearSelection();
		smallRB.setSelected(true);
		
		//adding temp radio buttons
		tempGroup.add(icedRB);
		tempGroup.add(hotRB);
		
		//resetting selections
		tempGroup.clearSelection();
		hotRB.setSelected(true);
		
		tempPanel.setLayout(new GridLayout(4,1));
		tempPanel.add(itemTempLbl);
		tempPanel.add(icedRB);
		tempPanel.add(hotRB);
		
		radioPanel.add(sizePanel);
		radioPanel.add(tempPanel);
	}
	
	//Creates Specialty Coffee Pop Up Frame
	public void specialCoffeeFrame(String itemName, double price){
		//Disables main JFrame
		this.setEnabled(false);
		//Sets title name for later use
		OrderItem item = new OrderItem();
		cart.add(item);
		
		item.CoffeeType = "special";
		item.itemName = itemName;
		itemView.setUndecorated(true);
		itemView.setLocation(this.getWidth() /3 , this.getHeight() / 4);
		itemView.setSize(300, 225);
		itemView.setTitle(item.itemName);
		itemView.setVisible(true);
		itemView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
		//adds item name to pop up
		itemNameLbl.setFont(new Font("Arial", Font.BOLD, 15));
		itemNameLbl.setText(item.itemName + "  ($5.00)");
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		titlePanel.add(itemNameLbl);
		itemView.add(titlePanel, BorderLayout.NORTH);
		
		//Coffee Price
		item.price = new BigDecimal(price);

		//------------
		itemtotalLBL.setFont(new Font("Arial", Font.ITALIC, 20));
		itemtotalLBL.setText("Item Total: ");
		radioPanel.setPreferredSize(new Dimension(300,110));
		orderSettingsPanel.add(radioPanel, BorderLayout.CENTER);
		orderSettingsPanel.add(itemtotalLBL);
		
		itemView.add(orderSettingsPanel);
		orderPanel.add(cancelItem);
		orderPanel.add(addItem);
		itemView.add(orderPanel, BorderLayout.SOUTH);

		sizePanel.setLayout(new GridLayout(4,1));
		sizePanel.add(itemSizeLbl);
		sizePanel.add(smallRB);
		sizePanel.add(mediumRB);
		sizePanel.add(largeRB);
		
		sizeGroup.clearSelection();
		smallRB.setSelected(true);
		
		//adding temp radio buttons
		tempGroup.add(frozenRB);
		tempGroup.add(icedRB);
		tempGroup.add(hotRB);
		
		tempGroup.clearSelection();
		hotRB.setSelected(true);
		
		tempPanel.setLayout(new GridLayout(4,1));
		tempPanel.add(itemTempLbl);
		tempPanel.add(frozenRB);
		tempPanel.add(icedRB);
		tempPanel.add(hotRB);
		
		radioPanel.add(sizePanel);
		radioPanel.add(tempPanel);
	}
	
	//Creates non coffee pop up frame
	public void nonCoffeeFrame(String itemName, double price){
		//Disables main JFrame
		this.setEnabled(false);
		//Sets title name for later use
		OrderItem item = new OrderItem();
		cart.add(item);
		
		item.CoffeeType = "non";
		item.itemName = itemName;
		itemView.setUndecorated(true);
		itemView.setLocation(this.getWidth() /3 , this.getHeight() / 4);
		itemView.setSize(300, 225);
		itemView.setTitle(item.itemName);
		itemView.setVisible(true);
		itemView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//adds item name to pop up
		itemNameLbl.setFont(new Font("Arial", Font.BOLD, 15));
		itemNameLbl.setText(item.itemName + "  ($3.00)");
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		titlePanel.add(itemNameLbl);
		itemView.add(titlePanel, BorderLayout.NORTH);
		
		//Coffee Price
		item.price = new BigDecimal(price);

		itemtotalLBL.setFont(new Font("Arial", Font.ITALIC, 20));
		itemtotalLBL.setText("Item Total: ");
		radioPanel.setPreferredSize(new Dimension(300,110));
		orderSettingsPanel.add(radioPanel, BorderLayout.CENTER);
		orderSettingsPanel.add(itemtotalLBL);
		
		itemView.add(orderSettingsPanel);
		orderPanel.add(cancelItem);
		orderPanel.add(addItem);
		itemView.add(orderPanel, BorderLayout.SOUTH);

		sizePanel.setLayout(new GridLayout(4,1));
		sizePanel.add(itemSizeLbl);
		sizePanel.add(smallRB);
		sizePanel.add(mediumRB);
		sizePanel.add(largeRB);
		
		sizeGroup.clearSelection();
		smallRB.setSelected(true);
		
		tempPanel.setLayout(new GridLayout(4,1));
		
		//HOT Chocolate can only be hot.
		if(itemName != "Hot Chocolate"){
			tempGroup.add(frozenRB);
			tempGroup.add(icedRB);
			tempGroup.add(hotRB);
			
			tempGroup.clearSelection();
			hotRB.setSelected(true);
			
			tempPanel.add(itemTempLbl);
			tempPanel.add(frozenRB);
			tempPanel.add(icedRB);
			tempPanel.add(hotRB);
			
		}else{
			item.temp = "";
		}
		
		radioPanel.add(sizePanel);
		radioPanel.add(tempPanel);
	}
	
	
	public void generateReceipt() {
		
		//generate receipt
		ReceiptPanel ReceiptPanel = new ReceiptPanel();
		JFrame printFrame = new JFrame();
		printFrame.setSize(new Dimension(400, 400+ 16* cart.size()));
		printFrame.add(ReceiptPanel);
		printFrame.setTitle("Coffee Shop Receipt");
		printFrame.setVisible(true);
		
		//submit print job
		PrinterJob printjob = PrinterJob.getPrinterJob();
		printjob.setPrintable(ReceiptPanel);
		if (printjob.printDialog()) {
			try {
				printjob.print();
			} catch(PrinterException x_x) {
				System.out.println("Error printing:" + x_x);
			}
		}
	}
	
	// custom JPanel for on screen receipt
	class ReceiptPanel extends JPanel implements Printable {
		Image receiptIMG = Toolkit.getDefaultToolkit().getImage("CoffeeShop.png");
		ImageIcon recieptIMGICON = new ImageIcon(receiptIMG.getScaledInstance(240,60, Image.SCALE_SMOOTH));

		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	//Radio Buttons happen here
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		//label format is in USD
		NumberFormat money = NumberFormat.getCurrencyInstance();
		String price$;
		
		//set item size based on radio button
		//displays item price + size cost to pop up label
		if(e.getSource() == smallRB){
			cart.get(cart.size() - 1).size = "Small";
			price$ = money.format(cart.get(cart.size() - 1).price.doubleValue());
			itemtotalLBL.setText("Item Total: "+  price$);
		} else if(e.getSource() == mediumRB){
			cart.get(cart.size() - 1).size = "Medium";
			price$ = money.format(cart.get(cart.size() - 1).price.add(one).doubleValue());
			itemtotalLBL.setText("Item Total: "+  price$);
		} else if(e.getSource() == largeRB){
			cart.get(cart.size() - 1).size = "Large";
			price$ = money.format(cart.get(cart.size() - 1).price.add(three).doubleValue());
			itemtotalLBL.setText("Item Total: "+  price$);
		}
		
		//sets item temp based on radio button
		if(e.getSource() == frozenRB){
			cart.get(cart.size() - 1).temp = "Frozen ";
		} else if(e.getSource() == icedRB){
			cart.get(cart.size() - 1).temp = "Iced ";
		} else if(e.getSource() == hotRB){
			cart.get(cart.size() - 1).temp = "Hot ";
		}

	}
	
}
