import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
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
	
	JPanel orderPanel = new JPanel();
	JPanel checkoutPanel = new JPanel();
	JFrame itemView = new JFrame();
	JFrame checkoutView = new JFrame();
	JLabel itemtotalLBL = new JLabel();
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
	
	DefaultTableModel model;
	JTable checkoutTable;
	JScrollPane sp;
	
	JButton addItem = new JButton("Add");
	JButton cancelItem = new JButton("Cancel");
	JButton cancelCheckout = new JButton("Cancel");
	
	//For edit selected item
	JButton doneEditing = new JButton("Done");
	
	int cartItems = 0;
	BigDecimal orderTotal = new BigDecimal("0.00");
	BigDecimal one = new BigDecimal("1");
	BigDecimal three = new BigDecimal("3");
	
	Boolean editing = false;
	
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
		
			
		//returns the string format - item description and price
		public String[] getRow() {
			
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
		
		Object[] columns = { "       Order Item" , " Price" };
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
		
		eastPanel.setBackground(new Color(191, 128, 64));
		buttonPanel.setBackground(new Color(191, 128, 64));
		mainPanel.setBackground(new Color(191, 128, 64));
		row1.setBackground(new Color(191, 128, 64));
		row2.setBackground(new Color(191, 128, 64));
		row3.setBackground(new Color(191, 128, 64));
		
		buttonPanel.setPreferredSize(new Dimension(320,50));
		eastPanel.setPreferredSize(new Dimension(350,this.getHeight()));
		
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
		
		//radio button action listeners
		smallRB.addItemListener(this);
		mediumRB.addItemListener(this);
		largeRB.addItemListener(this);
		frozenRB.addItemListener(this);
		hotRB.addItemListener(this);
		icedRB.addItemListener(this);
		
		//edit selected item button
		doneEditing.addActionListener(this);
		
		sizeGroup.add(smallRB);
		sizeGroup.add(mediumRB);
		sizeGroup.add(largeRB);
		
		
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

		app.setSize(1120,750);
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setTitle("Coffee Beans' Coffee Shop");
		app.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == addItem){
			//Add item should also close ItemView
			
			model.insertRow(cartItems, cart.get(cartItems).getRow());
			
			orderTotal = orderTotal.add(cart.get(cartItems).price);
			orderTotalLbl.setText("$" + orderTotal);
			
			cartItems++;
			orderPanel.removeAll();
			radioPanel.removeAll();
			sizePanel.removeAll();
			tempPanel.removeAll();
			itemView.dispose();
		}
		//-----East Panel Buttons-----------
		if(e.getSource() == editItem){
			if(checkoutTable.getSelectedRow() > -1) {
				editing = true;
				//cartItems++;
				
				orderTotal = orderTotal.subtract(cart.get(checkoutTable.getSelectedRow()).price);
				orderTotalLbl.setText("$" + orderTotal);
				
				if(cart.get(checkoutTable.getSelectedRow()).CoffeeType == "plain"){
					plainCoffeeFrame(cart.get(checkoutTable.getSelectedRow()).itemName, 4.00);
				}else if(cart.get(checkoutTable.getSelectedRow()).CoffeeType == "special"){
					specialCoffeeFrame(cart.get(checkoutTable.getSelectedRow()).itemName, 5.00);
				}else if(cart.get(checkoutTable.getSelectedRow()).CoffeeType == "non"){
					nonCoffeeFrame(cart.get(checkoutTable.getSelectedRow()).itemName, 3.00);
				}
				
				orderPanel.removeAll();
				orderPanel.add(doneEditing);
				orderPanel.add(itemtotalLBL, BorderLayout.SOUTH);
				
				
				
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
				
				
				/*
				OrderItem item = cart.get(checkoutTable.getSelectedRow());
				//cartItems--;
				
				//Sets title name for later use
				itemView.setUndecorated(true);
				itemView.setLocation(this.getWidth() /3 , this.getHeight() / 4);
				itemView.setSize(300, 300);
				itemView.setTitle(item.itemName);
				itemView.setVisible(true);
				itemView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				orderPanel.add(doneEditing);
				itemView.add(orderPanel, BorderLayout.SOUTH);
				
				//Coffee Name and Price
				//NumberFormat money = NumberFormat.getCurrencyInstance();
				//String price$ = money.format(item.price.doubleValue());
				//------------
				itemtotalLBL.setText("Item Total: ");
				orderPanel.add(itemtotalLBL, BorderLayout.SOUTH);
				itemView.add(radioPanel, BorderLayout.CENTER);
				
				//adding size radio buttons
				sizePanel.setLayout(new GridLayout(3,1));
				sizePanel.add(smallRB);
				sizePanel.add(mediumRB);
				sizePanel.add(largeRB);
				
				
				sizeGroup.clearSelection();
				
				if(item.size == "Small"){
					smallRB.setSelected(true);
				}else if(item.size == "Medium"){
					mediumRB.setSelected(true);
				}else if(item.size == "Large"){
					largeRB.setSelected(true);
				}	
				
				//adding temp radio buttons
				tempGroup.add(frozenRB);
				tempGroup.add(icedRB);
				tempGroup.add(hotRB);
				
				//resetting selections
				tempPanel.setLayout(new GridLayout(3,1));
				tempPanel.add(frozenRB);
				tempPanel.add(icedRB);
				tempPanel.add(hotRB);
				
				tempGroup.clearSelection();
				
				if(item.temp == "Frozen "){
					frozenRB.setSelected(true);
				}else if(item.temp == "Iced "){
					icedRB.setSelected(true);
				}else if(item.temp == "Hot "){
					hotRB.setSelected(true);
				}
				
				
				radioPanel.add(sizePanel);
				radioPanel.add(tempPanel);
				orderTotal = orderTotal.subtract(item.price);
				orderTotalLbl.setText("$" + orderTotal);
				
				*/
			}
		}
		
		if(e.getSource() == doneEditing){
			
			cart.get(checkoutTable.getSelectedRow()).price = cart.get(cart.size()-1).price;
			cart.get(checkoutTable.getSelectedRow()).size = cart.get(cart.size()-1).size;
			cart.get(checkoutTable.getSelectedRow()).temp = cart.get(cart.size()-1).temp;
			
			//OrderItem item = cart.get(checkoutTable.getSelectedRow());
			
			//cartItems--;
			//int row = checkoutTable.getSelectedRow();
			//model.removeRow(checkoutTable.getSelectedRow());
			//model.insertRow(row, item.getRow());
			
			if(cart.get(checkoutTable.getSelectedRow()).size == "Small"){
				orderTotal = orderTotal.add(cart.get(checkoutTable.getSelectedRow()).price);
			}else if(cart.get(checkoutTable.getSelectedRow()).size == "Medium"){
				orderTotal = orderTotal.add(cart.get(checkoutTable.getSelectedRow()).price.add(one));
			}else if(cart.get(checkoutTable.getSelectedRow()).size == "Large"){
				orderTotal = orderTotal.add(cart.get(checkoutTable.getSelectedRow()).price.add(three));
			}
			
			orderTotalLbl.setText("$" + orderTotal);
			
			
			model.insertRow(checkoutTable.getSelectedRow(), cart.get(checkoutTable.getSelectedRow()).getRow());
			model.removeRow(checkoutTable.getSelectedRow()+1);
			
			orderPanel.removeAll();
			radioPanel.removeAll();
			sizePanel.removeAll();
			tempPanel.removeAll();
			itemView.dispose();
			editing = false;
			cart.remove(cart.size()-1);
		}
		
		if(e.getSource() == removeItem){
			if(checkoutTable.getSelectedRow() > -1) {
				
				orderTotal = orderTotal.subtract(cart.get(checkoutTable.getSelectedRow()).price);
				orderTotalLbl.setText("$" + orderTotal);

				cart.remove(checkoutTable.getSelectedRow());
				model.removeRow(checkoutTable.getSelectedRow());
				cartItems--;
			}
		}
		//----------------------------------
		//Cancel order item - removes item from cart and closes window
		if(e.getSource() == cancelItem){
			
			if(cartItems > -1){
				cart.remove(cart.size()-1);
			}

			orderPanel.removeAll();
			radioPanel.removeAll();
			sizePanel.removeAll();
			tempPanel.removeAll();
			itemView.dispose();
		}
		//All JButtons Point to functions
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
			specialCoffeeFrame("Decaf Coffee", 5.00);
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
	//Function for Regular Coffees
	public void plainCoffeeFrame(String itemName, double price){
		//Sets title name for later use
		
		OrderItem item = new OrderItem();
		cart.add(item);
		
		item.CoffeeType = "plain";
		item.itemName = itemName;
		itemView.setUndecorated(true);
		itemView.setLocation(this.getWidth() /3 , this.getHeight() / 4);
		itemView.setSize(300, 300);
		itemView.setTitle(item.itemName);
		itemView.setVisible(true);
		itemView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		orderPanel.add(cancelItem);
		orderPanel.add(addItem);
		itemView.add(orderPanel, BorderLayout.SOUTH);
		
		//Coffee Name and Price
		item.price = new BigDecimal(price);

		itemtotalLBL.setText("Item Total: ");
		orderPanel.add(itemtotalLBL, BorderLayout.SOUTH);
		itemView.add(radioPanel, BorderLayout.CENTER);
		
		//adding size radio buttons
		sizePanel.setLayout(new GridLayout(3,1));
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
		
		tempPanel.setLayout(new GridLayout(3,1));
		tempPanel.add(icedRB);
		tempPanel.add(hotRB);
		
		radioPanel.add(sizePanel);
		radioPanel.add(tempPanel);
	}
	//Function for specialty coffees (frozen)
	public void specialCoffeeFrame(String itemName, double price){
		//Sets title name for later use
		OrderItem item = new OrderItem();
		cart.add(item);
		
		item.CoffeeType = "special";
		item.itemName = itemName;
		itemView.setUndecorated(true);
		itemView.setLocation(this.getWidth() /3 , this.getHeight() / 4);
		itemView.setSize(300, 300);
		itemView.setTitle(item.itemName);
		itemView.setVisible(true);
		itemView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		orderPanel.add(cancelItem);
		orderPanel.add(addItem);
		itemView.add(orderPanel, BorderLayout.SOUTH);
		
		//Coffee Name and Price
		item.price = new BigDecimal(price);

		//------------
		itemtotalLBL.setText("Item Total: ");
		orderPanel.add(itemtotalLBL, BorderLayout.SOUTH);
		itemView.add(radioPanel, BorderLayout.CENTER);
		
		sizePanel.setLayout(new GridLayout(3,1));
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
		
		tempPanel.setLayout(new GridLayout(3,1));
		tempPanel.add(frozenRB);
		tempPanel.add(icedRB);
		tempPanel.add(hotRB);
		
		radioPanel.add(sizePanel);
		radioPanel.add(tempPanel);
	}
	
	//Function for all other items besides coffees
	public void nonCoffeeFrame(String itemName, double price){
		//Sets title name for later use
		OrderItem item = new OrderItem();
		cart.add(item);
		
		item.CoffeeType = "non";
		item.itemName = itemName;
		itemView.setUndecorated(true);
		itemView.setLocation(this.getWidth() /3 , this.getHeight() / 4);
		itemView.setSize(300, 300);
		itemView.setTitle(item.itemName);
		itemView.setVisible(true);
		itemView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		orderPanel.add(cancelItem);
		orderPanel.add(addItem);
		itemView.add(orderPanel, BorderLayout.SOUTH);
		
		//Coffee Name and Price
		item.price = new BigDecimal(price);

		itemtotalLBL.setText("Item Total: ");
		orderPanel.add(itemtotalLBL, BorderLayout.SOUTH);
		itemView.add(radioPanel, BorderLayout.CENTER);
		
		sizePanel.setLayout(new GridLayout(3,1));
		sizePanel.add(smallRB);
		sizePanel.add(mediumRB);
		sizePanel.add(largeRB);
		
		sizeGroup.clearSelection();
		smallRB.setSelected(true);
		
		tempPanel.setLayout(new GridLayout(3,1));
		
		//HOT Chocolate can only be hot.
		if(itemName != "Hot Chocolate"){
			tempGroup.add(frozenRB);
			tempGroup.add(icedRB);
			tempGroup.add(hotRB);
			
			tempGroup.clearSelection();
			hotRB.setSelected(true);
			
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
		ReceiptPanel ReceiptPanel = new ReceiptPanel();
		JFrame printscreen = new JFrame();
		
		
	}
	
	// custom JPanel for on screen receipt
	class ReceiptPanel extends JPanel implements Printable {

		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		NumberFormat money = NumberFormat.getCurrencyInstance();
		String price$;
		
		/*
		if(editing){
			if(e.getSource() == smallRB){
				cart.get(checkoutTable.getSelectedRow()).size = "Small";
				price$ = money.format(cart.get(checkoutTable.getSelectedRow()).price.doubleValue());
				itemtotalLBL.setText("Item Total: "+  price$);
			} else if(e.getSource() == mediumRB){
				cart.get(checkoutTable.getSelectedRow()).size = "Medium";
				price$ = money.format(cart.get(checkoutTable.getSelectedRow()).price.add(one).doubleValue());
				itemtotalLBL.setText("Item Total: "+  price$);
			} else if(e.getSource() == largeRB){
				cart.get(checkoutTable.getSelectedRow()).size = "Large";
				price$ = money.format(cart.get(checkoutTable.getSelectedRow()).price.add(three).doubleValue());
				itemtotalLBL.setText("Item Total: "+  price$);
			}
			
			if(e.getSource() == frozenRB){
				cart.get(checkoutTable.getSelectedRow()).temp = "Frozen ";
			} else if(e.getSource() == icedRB){
				cart.get(checkoutTable.getSelectedRow()).temp = "Iced ";
			} else if(e.getSource() == hotRB){
				cart.get(checkoutTable.getSelectedRow()).temp = "Hot ";
			}
			
		}else if(!editing){
			
			*/
		
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
			
			if(e.getSource() == frozenRB){
				cart.get(cart.size() - 1).temp = "Frozen ";
			} else if(e.getSource() == icedRB){
				cart.get(cart.size() - 1).temp = "Iced ";
			} else if(e.getSource() == hotRB){
				cart.get(cart.size() - 1).temp = "Hot ";
			
		}

	}
	
}
