// Name: Buth, Justin
// Project: #3
// Due: 11/20/15
// Course: cs-245-01-f15
//
// Description:
// A Rolodex that reads information from contacts.txt and shows a person's photo, name, and email address.

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Rolodex implements ActionListener{

	JFrame frame;
	JMenuBar menuBar;
	JTabbedPane tp;
	String[] contactsArr;
	ArrayList<String> names;
	ArrayList<String> emailAddresses;
	ArrayList<String> images;
	
	Rolodex() throws IOException{
		
		frame = new JFrame("Rolodex");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(525, 190);

		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		
		createMenu("File");
		createMenu("Tabs");
		createMenu("Help");
		
		populateContactInfo();
		createTabs();

		BufferedImage img = ImageIO.read(new File("Rolodex.png"));

		frame.setIconImage(img);
		
		frame.add(tp);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}

	public void populateContactInfo() throws IOException{

		String contact;
		String contacts = "";
		String delimiter = "[:]";
		try{
			FileReader fr = new FileReader("contacts.txt");
			BufferedReader br = new BufferedReader(fr);

			while ((contact = br.readLine()) != null){
				contacts += contact;
				contacts += ":";
			}
			
		} catch(IOException io){
			System.out.println(io.toString());
		}

		
		contactsArr = contacts.split(delimiter);
		names = new ArrayList<String>();
		emailAddresses = new ArrayList<String>();
		images = new ArrayList<String>();
		
		for (int i = 0; i < contactsArr.length; i++){
			if (i%3 == 0){
				names.add(contactsArr[i]);
			}
			else if (contactsArr[i].contains(".jpg")){
				images.add(contactsArr[i]);
			}
			else if (contactsArr[i].contains("@")){
				emailAddresses.add(contactsArr[i]);
			} else {
				emailAddresses.add("");
			}

		}
		System.out.println(names.toString());
		System.out.println(emailAddresses.toString());
		System.out.println(images.toString());
	}
	
	public void createTabs() throws IOException{
		tp = new JTabbedPane();
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tp.setTabPlacement(JTabbedPane.TOP);
		
		for (int i = -1; i < names.size(); i++){
			//JPanel panel = new JPanel();
			JPanel panel = new JPanel(new FlowLayout());
			BufferedImage img;
			ImageIcon icon;
			
			if (i == -1){
				img = ImageIO.read(new File("nopic.jpg"));
			    icon = new ImageIcon(img);
			} else {
			try{
				img = ImageIO.read(new File(images.get(i)));
				icon = new ImageIcon(img);
			} catch (IIOException ex){
				img = ImageIO.read(new File("nopic.jpg"));
			    icon = new ImageIcon(img);
			}
			}
			
			JPanel photo = new JPanel();
			JPanel info = new JPanel(new GridLayout(2, 1));
			JPanel nameField = new JPanel();
			JPanel emailField = new JPanel();
			
			JLabel image = new JLabel();
			image.setIcon(icon);
			nameField.add(new JLabel("Name:"));
			if (i == -1){
				JTextField name = new JTextField("Buth, Justin", 15);
				nameField.add(name);
			} else{
				JTextField name = new JTextField(names.get(i), 15);
				nameField.add(name);
			}

			emailField.add(new JLabel("Email:"));
			if (i == -1){
				JTextField email = new JTextField("jbuth@cpp.edu", 15);
				emailField.add(email);
			} else {
				JTextField email = new JTextField(emailAddresses.get(i), 15);
				emailField.add(email);
			}
			photo.add(image);
			info.add(nameField);
			info.add(emailField);
			panel.add(image);
			panel.add(info);
			if (i == -1){
				tp.addTab("Buth, Justin", panel);
			} else
				tp.addTab(names.get(i), panel);

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
			exit.addActionListener(this);
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
			
			top.addActionListener(this);
			right.addActionListener(this);
			bottom.addActionListener(this);
			left.addActionListener(this);
			
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
			
			scroll.addActionListener(this);
			wrap.addActionListener(this);
			
			layout.add(scroll);
			layout.add(wrap);
			menu.add(layout);
			menu.addSeparator();
			JMenuItem def = new JMenuItem("Defaults");
			def.setMnemonic('D');
			def.addActionListener(this);
			def.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
			menu.add(def);
			
		} else if (title.equals("Help")){
			menu.setMnemonic('H');
			JMenuItem about = new JMenuItem("About");
			about.setMnemonic('A');
			about.addActionListener(this);
			menu.add(about);
			about.addActionListener(this);
				
			
		}
		
	}
	
	public void actionPerformed(ActionEvent ae){
		String actionCmd = ae.getActionCommand();
		
		if (actionCmd.equals("About")){
			BufferedImage img;
			ImageIcon icon;

			try {
				img = ImageIO.read(new File("Rolodex.png"));
				icon = new ImageIcon(img);
				JOptionPane.showMessageDialog(frame, "<html>Rolodex version 0.1 <br> Copyright (c) 2015 J.Buth", "About", 0, icon);
			} catch (IOException e) {
				System.out.println(e.toString());
			}


		} else if (actionCmd.equals("Scroll")){
			tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		} else if (actionCmd.equals("Wrap")){
			tp.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		} else if (actionCmd.equals("Defaults")){
			tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			tp.setTabPlacement(JTabbedPane.TOP);
		} else if (actionCmd.equals("Top")){
			tp.setTabPlacement(JTabbedPane.TOP);
		} else if (actionCmd.equals("Bottom")){
			tp.setTabPlacement(JTabbedPane.BOTTOM);
		} else if (actionCmd.equals("Left")){
			tp.setTabPlacement(JTabbedPane.LEFT);
		} else if (actionCmd.equals("Right")){
			tp.setTabPlacement(JTabbedPane.RIGHT);
		} else if (actionCmd.equals("Exit")){
			frame.dispose();
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
