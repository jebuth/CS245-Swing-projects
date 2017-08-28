// Name:		Buth, Justin
// Homework:	#1
// Due Date:	10/12/15
// Course:		CS 245 01 F15
// Description: Create a simple ImageViewer class that will display an image of types GIF, JPG, or PNG.

import javax.swing.*;

class ImageViewer{

	ImageViewer(String arg){

		int lastSlash = arg.lastIndexOf("\\");
		String filename = arg.substring(lastSlash+1, arg.length());

		JFrame frame = new JFrame("Justin Buth's Image Viewer");

		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon image = new ImageIcon(arg);

		JLabel label = new JLabel(filename, image, SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.BOTTOM);
		label.setHorizontalTextPosition(SwingConstants.CENTER);

		frame.getContentPane().add(label);

		frame.setVisible(true);

	}

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new ImageViewer(args[0]);
			}
		});
	}

}