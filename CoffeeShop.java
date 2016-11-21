import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Team 6: Coffee Beans
 * Sneha Akarapu, Kyle Watkins, Preston Jackson, Sarah Taff, Alejandro Delgadillo, Ben Deleus
 * 
 * CoffeeShop
 */

public class CoffeeShop extends JFrame implements ActionListener{

	JPanel mainScreen = new JPanel();
	JButton checkout = new JButton("Check out");
	
	public CoffeeShop() {
		
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
