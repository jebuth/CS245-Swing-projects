import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class Rolodex implements ActionListener{

	JFrame frame;
	JMenuBar menuBar;
	String[] contacts;
	
	Rolodex() throws IOException{
		populateContactInfo();
		
		frame = new JFrame("Rolodex");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);

		menuBar = new JMenuBar();
		
		createMenu("File");
		createMenu("Tabs");
		createMenu("Help");
		
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}

	public void populateContactInfo() throws IOException{
		String contact;
		try{
			FileReader fr = new FileReader("contacts.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String delimiter = "[:]";
			while ((contact = br.readLine()) != null){
				contacts = contact.split(delimiter);
			}
		} catch(IOException io){
			
		}
		

		
		for (int i = 0; i < contacts.length; i++){
			System.out.println(contacts[i]);
		}
		
	}
	
	public void createMenu(String title){
		JMenu menu = new JMenu(title);
		menuBar.add(menu);
		
		if (title.equals("File")){
			menu.setMnemonic('F');
			JMenuItem open = new JMenuItem("Open");
			open.setMnemonic('O');
			open.setEnabled(false);
			menu.add(open);
			menu.addSeparator();
			JMenuItem exit = new JMenuItem("Exit");
			exit.setMnemonic('X');
			menu.add(exit);
		} else if (title.equals("Tabs")){
			menu.setMnemonic('T');
			JMenu placement = new JMenu("Placement");
			menu.add(placement);
			placement.setMnemonic('P');
			JMenuItem top = new JMenuItem("Top");
			top.setMnemonic('T');
			JMenuItem right = new JMenuItem("Right");
			right.setMnemonic('R');
			JMenuItem bottom = new JMenuItem("Bottom");
			bottom.setMnemonic('B');
			JMenuItem left = new JMenuItem("Left");
			left.setMnemonic('L');
			placement.add(top);
			placement.add(right);
			placement.add(bottom);
			placement.add(left);
			JMenu layout = new JMenu("Layout");
			layout.setMnemonic('L');
			JMenuItem scroll = new JMenuItem("Scroll");
			scroll.setMnemonic('S');
			JMenuItem wrap = new JMenuItem("Wrap");
			wrap.setMnemonic('W');
			layout.add(scroll);
			layout.add(wrap);
			menu.add(layout);
			menu.addSeparator();
			JMenuItem def = new JMenuItem("Defaults");
			def.setMnemonic('D');
			def.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
			menu.add(def);
			
		} else if (title.equals("Help")){
			menu.setMnemonic('H');
			JMenuItem about = new JMenuItem("About");
			about.setMnemonic('A');
			about.addActionListener(this);
			menu.add(about);
				
			
		}
		
	}
	
	public void actionPerformed(ActionEvent ae){
		String actionCmd = ae.getActionCommand();
		
		if (actionCmd.equals("About")){
			JOptionPane.showMessageDialog(frame, "what the fuck");
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try {
					new Rolodex();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

}
