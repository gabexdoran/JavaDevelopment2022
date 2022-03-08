package project02Package;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pointOfSale.Product;
import pointOfSale.ProductCollection;

public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Main Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		
		MainPanel panel = new MainPanel();
		panel.setSize(800, 500);
		
		frame.getContentPane().add(panel);
		frame.setResizable(true);
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					panel.doClose();
				}
		});
	}
}