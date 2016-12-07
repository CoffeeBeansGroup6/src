import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class CoffeeShop extends JFrame implements ActionListener, ItemListener{

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

	JButton checkoutButton = new JButton("Check Out");
	JButton editItem = new JButton("Edit Selected Item");
	JButton removeItem = new JButton("Remove Selected Item");
	
	//Radio Buttons
	ButtonGroup size = new ButtonGroup();
	JRadioButton smallRB, mediumRB, largeRB;
	
	ButtonGroup temp = new ButtonGroup();
	JRadioButton frozenRB, icedRB, hotRB;
	
	
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
	
	int cartItems = 0;
	BigDecimal orderTotal = new BigDecimal("0.00");
	
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
		
		Object[] columns = { "      Order Item" , "  Price" };
		model.setColumnIdentifiers(columns);
		
		checkoutTable.setFont(new Font("Arial", Font.PLAIN, 25));
		checkoutTable.setRowHeight(40);
		checkoutTable.setGridColor(Color.BLACK);// colors grid lines
		
		checkoutTable.getTableHeader().setFont(new Font("Arial",Font.PLAIN, 25));
		checkoutTable.getColumnModel().getColumn(0).setPreferredWidth(220);
		checkoutTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		//--------------------------------------------------
		
		
		// fancy order total $$ label
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
			OrderItem item = new OrderItem();
			cart.add(item);
			model.insertRow(cartItems, cart.get(cartItems).getRow());
			
			orderTotal = orderTotal.add(cart.get(cartItems).price);
			orderTotalLbl.setText("$" + orderTotal);
			cartItems++;
		}
		
//		//Radio Buttons
//		ButtonGroup size = new ButtonGroup();
//		JRadioButton smallRB, mediumRB, largeRB;
//		
//		ButtonGroup temp = new ButtonGroup();
//		JRadioButton frozenRB, icedRB, hotRB;
		//All Item Choice Buttons
		
		if(e.getSource() == darkButton){
			itemView.setSize(400, 400);
			itemView.setTitle("Dark Roast Coffee");
			itemView.setVisible(true);
			
			orderPanel.add(cancelItem);
			orderPanel.add(addItem);
			itemView.add(orderPanel);
			
			smallRB = new JRadioButton("Small", true);
			smallRB.addItemListener(this);
			mediumRB = new JRadioButton("Medium", false);
			mediumRB.addItemListener(this);
			largeRB = new JRadioButton("Large", false);
			largeRB.addItemListener(this);
		
			
		}
		if(e.getSource() == mediumButton){
			itemView.setSize(400, 400);
			itemView.setTitle("Medium Roast Coffee");
			itemView.setVisible(true);
			
			orderPanel.add(cancelItem);
			orderPanel.add(addItem);
			itemView.add(orderPanel);
		}
		if(e.getSource() == blondeButton){
			itemView.setSize(400, 400);
			itemView.setTitle("Blonde Roast Coffee");
			itemView.setVisible(true);
			
			orderPanel.add(cancelItem);
			orderPanel.add(addItem);
			itemView.add(orderPanel);
			
		}
		if(e.getSource() == mochaButton){
			itemView.setSize(400, 400);
			itemView.setTitle("Cafe Mocha");
			itemView.setVisible(true);
			
			orderPanel.add(cancelItem);
			orderPanel.add(addItem);
			itemView.add(orderPanel);
			
		}
		if(e.getSource() == vanillaButton){
			itemView.setSize(400, 400);
			itemView.setTitle("Vanilla Latte");
			itemView.setVisible(true);
			
			orderPanel.add(cancelItem);
			orderPanel.add(addItem);
			itemView.add(orderPanel);
			
		}
		if(e.getSource() == decafButton){
			itemView.setSize(400, 400);
			itemView.setTitle("Decaf Coffee");
			itemView.setVisible(true);
			
			orderPanel.add(cancelItem);
			orderPanel.add(addItem);
			itemView.add(orderPanel);
			
		}
		if(e.getSource() == espressoButton){
			itemView.setSize(400, 400);
			itemView.setTitle("Espresso");
			itemView.setVisible(true);
			
			orderPanel.add(cancelItem);
			orderPanel.add(addItem);
			itemView.add(orderPanel);
			
		}
		if(e.getSource() == cocoButton){
			itemView.setSize(400, 400);
			itemView.setTitle("Hot Cocoa");
			itemView.setVisible(true);
			
			orderPanel.add(cancelItem);
			orderPanel.add(addItem);
			itemView.add(orderPanel);
			
		}
		if(e.getSource() == teaButton){
			itemView.setSize(400, 400);
			itemView.setTitle("Tea");
			itemView.setVisible(true);
			
			orderPanel.add(cancelItem);
			orderPanel.add(addItem);
			itemView.add(orderPanel);
			
		}
		
		
		//-----East Panel Buttons-----------
		if(e.getSource() == editItem){
			
		}
		
		if(e.getSource() == removeItem){
			
		}
		
		//All Check Out screens carried out here
		if(e.getSource() == checkoutButton){
			checkoutView.setSize(400, 400);
			checkoutView.setTitle("Checkout");
			checkoutView.setVisible(true);
			
			checkoutPanel.add(cancelCheckout);
			checkoutView.add(checkoutPanel);
		}
		
	}
	
	
	//Order Item Class
	class OrderItem{
		String itemName;
		BigDecimal price;
		
		public OrderItem(){
			itemName = "item";
			price = new BigDecimal("1.00");
		}
		
		public OrderItem(String name){
			itemName = name;
		}
			
		//returns the string format - item description and price
		public String[] getRow() {
			NumberFormat money = NumberFormat.getCurrencyInstance();
			String price$ = money.format(price.doubleValue());
			
			String row[] = {"  " + itemName," " + price$};
			
			return row;
		}
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
