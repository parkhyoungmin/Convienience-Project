package Order;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		OrderGui f = new OrderGui();
		
		f.pack(); //frame에 맞게 크기조절
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}