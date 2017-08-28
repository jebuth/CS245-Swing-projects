// Justin Buth
// SwingDemo.java

import javax.swing.*;

class SwingDemo{

	SwingDemo(){

		JFrame frame = new JFrame("Title");
		frame.setSize(400, 200);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("Label");

		frame.getContentPane().add(label);

		frame.setVisible(true);

	}

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new SwingDemo();
			}
		});

	}

}