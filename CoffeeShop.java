import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

/*
 * Team 6: Coffee Beans
 * Sneha Akarapu, Kyle Watkins, Preston Jackson, Sarah Taff, Alejandro Delgadillo, Ben Deleus
 * 
 * CoffeeShop
 */

public class CoffeeShop extends JFrame implements ActionListener{

	// holds all orderItems in your cart
	ArrayList<OrderItem> cart = new ArrayList<OrderItem>();
	
	JPanel eastPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel row1 = new JPanel();
	JPanel row2 = new JPanel();
	JPanel row3 = new JPanel();

	JButton checkoutButton = new JButton("Check Out");
	JButton editItem = new JButton("Edit Selected Item");
	JButton removeItem = new JButton("Remove Selected Item");
	
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
		
		Color color = UIManager.getColor("Table.gridColor");
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		checkoutTable.setBorder(border);
		
		Object[] columns = { "      Order Item" , "  Price" };
		model.setColumnIdentifiers(columns);

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
		sp.setPreferredSize(new Dimension(320,550));

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
		
		app.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//app.setSize(1120, 790); if we dont want full screen
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setTitle("Coffee Beans' Coffee Shop");
		app.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//All Item Choice Buttons
		if(e.getSource() == darkButton){
			
		}
		if(e.getSource() == mediumButton){
			
		}
		if(e.getSource() == blondeButton){
			
		}
		if(e.getSource() == mochaButton){
			
		}
		if(e.getSource() == vanillaButton){
			
		}
		if(e.getSource() == decafButton){
			
		}
		if(e.getSource() == espressoButton){
			
		}
		if(e.getSource() == cocoButton){
			
		}
		if(e.getSource() == teaButton){
			
		}
		
		
		//-----East Panel Buttons-----------
		if(e.getSource() == editItem){
			
		}
		
		if(e.getSource() == removeItem){
			
		}
		
		//All Check Out screens carried out here
		if(e.getSource() == checkoutButton){
			
		}
		
	}
	
	
	//Order Item Class
	class OrderItem{
		
		String itemName;
		BigDecimal price;
		
		public OrderItem(){
			
		}
		
		//returns the string format - item description and price
		public String[] getRow() {
			NumberFormat money = NumberFormat.getCurrencyInstance();
			String price$ = money.format(price.doubleValue());
			
			String row[] = { itemName, price$};
			
			return row;
		}
		
	}
	
}
