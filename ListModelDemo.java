import javax.swing.*;   
import javax.swing.event.*;  
import java.awt.*;  
import java.awt.event.*;  
    
class ListModelDemo {   
   
  JList jlst;  
  JLabel jlab;  
  JScrollPane jscrlp;  
  JButton jbtnBuy;  
  JButton jbtnAddDel;  
  
  ListModelDemo() {   
    // Create a new JFrame container.   
    JFrame jfrm = new JFrame("JList ModelDemo");   
  
    // Specify FlowLayout manager.    
    jfrm.getContentPane().setLayout(new FlowLayout());   
  
    // Give the frame an initial size.   
    jfrm.setSize(180, 240);   
   
    // Terminate the program when the user closes the application.   
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
 
    // Create and populate a list model.   
    //  
    // First, create an instance of DefaultListModel. 
    DefaultListModel lm = new DefaultListModel(); 
 
    // Now, add items to the model. 
    lm.addElement("Winesap"); 
    lm.addElement("Cortland"); 
    lm.addElement("Red Delicious"); 
    lm.addElement("Golden Delicious"); 
    lm.addElement("Gala"); 
 
    // Create a JList by specifying the model. 
    jlst = new JList(lm);  
  
    // Add list to a scroll pane.  
    jscrlp = new JScrollPane(jlst);  
  
    // Set the preferred size of the scroll pane.  
    jscrlp.setPreferredSize(new Dimension(120, 90));  
  
    // Make a label that displays the selection.  
    jlab = new JLabel("Please Choose an Apple");  
  
    // Add selection listener for the list.  
    jlst.addListSelectionListener(new ListSelectionListener() {   
      public void valueChanged(ListSelectionEvent le) {   
        String what = "";  
  
        // Get the index of the changed item.  
        Object values[] = jlst.getSelectedValues();  
  
        // Confirm that one or more items have been selected.  
        if(values.length == 0) {  
          jlab.setText("Please Choose an Apple.");  
          return;  
        }  
  
        // Display the selected items.  
        for(int i = 0; i < values.length; i++)  
          what += values[i] + "<br>";  
  
        jlab.setText("<html>Current selection:<br>" + what);  
      }   
    });   
  
    // Make a button that "buys" the selected apple.  
    jbtnBuy = new JButton("Buy Apple");  
  
    // Add action listener for Buy Apple button.  
    jbtnBuy.addActionListener(new ActionListener() {   
      public void actionPerformed(ActionEvent ae) {   
        String what = "";  
  
        // Get the index of the changed item.  
        Object values[] = jlst.getSelectedValues();  
  
        // Confirm that one or more items have been selected.  
        if(values.length == 0) {  
          jlab.setText("No apple has been selected.");  
          return;  
        }  
  
        // Display the selected items.  
        for(int i = 0; i < values.length; i++)  
          what += values[i] + "<br>";  
  
        jlab.setText("<html>Apples Purchased:<br>" + what);  
      }   
    });   
 
    // Make a button that adds more apple selections.  
    jbtnAddDel = new JButton("Add More Varieties");  
  
    // Add action listener for Add More Apples button.  
    jbtnAddDel.addActionListener(new ActionListener() {   
      public void actionPerformed(ActionEvent ae) {   
 
        // Get a reference to the model. 
        DefaultListModel lm = (DefaultListModel) jlst.getModel();  
 
        // See if extra varieties already added. 
        if(lm.getSize() > 5) { 
          // If so, remove extra varieties. 
          for(int i=7; i > 4; i--) lm.remove(i); 
  
          jbtnAddDel.setText("Add More Varieties"); 
        } else { 
          // Add extra varieties. 
          lm.addElement("Fuji"); 
          lm.addElement("Granny Smith"); 
          lm.addElement("Jonathan"); 
          jbtnAddDel.setText("Remove Extra Varieties"); 
        }  
      }   
    });   
  
    // Add the list and label to the content pane.  
    jfrm.getContentPane().add(jscrlp);  
    jfrm.getContentPane().add(jbtnBuy);  
    jfrm.getContentPane().add(jbtnAddDel);  
    jfrm.getContentPane().add(jlab);  
   
    // Display the frame.   
    jfrm.setVisible(true);   
  }   
  
  public static void main(String args[]) {   
    // Create the frame on the event dispatching thread.   
    SwingUtilities.invokeLater(new Runnable() {   
      public void run() {   
        new ListModelDemo();   
      }   
    });    
  }   
}

