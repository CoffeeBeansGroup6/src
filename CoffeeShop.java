import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Team 6: Coffee Beans
 * Sneha Akarapu, Kyle Watkins, Preston Jackson, Sarah Taff, Alejandro Delgadillo, Ben Deleus
 * 
 * CoffeeShop
 */

public class CoffeeShop extends JFrame implements ActionListener{

	JPanel eastPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel mainPanel = new JPanel();

	JButton checkoutButton = new JButton("Check Out");
	JButton editItem = new JButton("Edit Selected Item");
	JButton removeItem = new JButton("Remove Selected Item");
	
	
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
		buttonPanel.setPreferredSize(new Dimension(320,50));
		eastPanel.setPreferredSize(new Dimension(350,this.getHeight()));
		
		editItem.addActionListener(this);
		removeItem.addActionListener(this);
		checkoutButton.addActionListener(this);
		
		checkoutButton.setPreferredSize(new Dimension(320, 60));
		
		sp.setPreferredSize(new Dimension(320,550));

		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(editItem);
		buttonPanel.add(removeItem);
		
		eastPanel.add(sp);
		eastPanel.add(buttonPanel);
		eastPanel.add(orderTotalLbl);
		eastPanel.add(checkoutButton);

		add(mainPanel, BorderLayout.CENTER);
		add(eastPanel, BorderLayout.EAST);
	}

	public static void main(String[] args) {
		CoffeeShop app = new CoffeeShop();
		
		app.setExtendedState(JFrame.MAXIMIZED_BOTH);//makes the app full screen!
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setTitle("Coffee Shop");
		app.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
