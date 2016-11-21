import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * Team 6: Coffee Beans
 * Sneha Akarapu, Kyle Watkins, Preston Jackson, Sarah Taff, Alejandro Delgadillo, Ben Deleus
 * 
 * CoffeeShop
 */

public class CoffeeShop extends JFrame implements ActionListener{

	JPanel mainScreen = new JPanel();
	DefaultListModel data = new DefaultListModel();
	JList orderList = new JList(data);
	JScrollPane jsp = new JScrollPane(orderList);
	JButton checkout = new JButton("Check out");
	
	public CoffeeShop() {
		checkout.addActionListener(this);
		mainScreen.add(jsp);
		mainScreen.add(checkout);
		add(mainScreen);
		
	}

	public static void main(String[] args) {
		CoffeeShop app = new CoffeeShop();
		
		app.setExtendedState(JFrame.MAXIMIZED_BOTH);//makes the app full screen!
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setTitle("Coffee Shop");
		app.setVisible(true);
		//Sneha
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
