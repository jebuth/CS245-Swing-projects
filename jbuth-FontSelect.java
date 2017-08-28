// Name:        Buth, Justin
// Homework:    #2
// Due:         10/30/15
// Course:      cs-245-01-f15
//
// Description: A GUI that uses two Lists. The first list contains all the system fonts.
// The second list is initially empty. The user selects fonts from the first list and
// adds them to the second.

import javax.swing.*;   
import java.awt.*;  
import java.awt.GraphicsEnvironment;
import java.awt.event.*; 
import java.util.ArrayList;

class FontSelect implements ActionListener{
    
    Container c;
    JList list1;
    JList list2;
    JLabel systemFontsLabel;
    JLabel selectedLabel;
    JButton add;
    JButton print;
    JScrollPane list1pane;
    JScrollPane list2pane;
    JPanel left;
    JPanel mid;
    JPanel right;
    JLabel spacer;
    ArrayList<String> fonts;
    
    DefaultListModel selectedFonts;
    DefaultListModel systemFonts;
    
    int[] victims = null;
    ArrayList<String> chosenFonts = new ArrayList<String>();
    
    FontSelect(){
        JFrame frame = new JFrame("Font Select");

        frame.setSize(590, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        mid = new JPanel();
        //mid.setLayout(new BoxLayout(mid, BoxLayout.Y_AXIS));
        mid.setLayout(new GridLayout(3, 1));
        right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        
        //====================================
        systemFonts = new DefaultListModel();
        fonts = new ArrayList<String>();
       
        populateList(fonts);
        
        for (String fontName : fonts){
            systemFonts.addElement(fontName);
        }
      
        list1 = new JList(systemFonts);
        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list1pane = new JScrollPane(list1);
        list1pane.setPreferredSize(new Dimension(210, 150));
        list1pane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        systemFontsLabel = new JLabel("System Fonts:");
        
        left.add(systemFontsLabel, BorderLayout.WEST);
        left.add(list1pane, BorderLayout.WEST);
        // ===================================
        
        add = new JButton("Add >>");
        add.addActionListener(this);
        print = new JButton("Print");
        print.addActionListener(this);
        
        mid.add(add);
        spacer = new JLabel("");
        mid.add(spacer);
        mid.add(print);
        
        // ===================================
        
        selectedFonts = new DefaultListModel();
        
        list2 = new JList(selectedFonts);
        list2pane = new JScrollPane(list2);
        list2pane.setPreferredSize(new Dimension(210, 150));
        list2pane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        selectedLabel = new JLabel("Selected:");
        
        right.add(selectedLabel);
        right.add(list2pane);
        
        // ===================================
        left.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 6));
        mid.setBorder(BorderFactory.createEmptyBorder(90,10, 90, 10));
        right.setBorder(BorderFactory.createEmptyBorder(20,6, 20, 20));
        
        frame.add(left, BorderLayout.WEST);
        frame.add(mid, BorderLayout.CENTER);
        frame.add(right, BorderLayout.EAST);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    void populateList(ArrayList<String> list){
        String [] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String s : allFonts){
            list.add(s);
        }
    }
    
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new FontSelect();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add >>")){
            victims = list1.getSelectedIndices();
            for (int victim : victims){
                chosenFonts.add(list1.getModel().getElementAt(victim).toString());
            }
            
            for (int i = (victims.length-1); i >= 0; i--){
                systemFonts.remove(victims[i]);
            }

        }
        
        else if (e.getActionCommand().equals("Print")){
            selectedFonts.clear();
            if (chosenFonts != null){
                for (String victim : chosenFonts){
                    selectedFonts.addElement(victim);
 
                }
            }
            
        }
    }
}