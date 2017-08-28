
//
// Name: Buth, Justin
// Homework: #4
// Due: 11/13/15
// Course: cs-245-01-f15
//
// Description:
// Basic example of JFileChooser and Dialogs


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NotepadMenu implements ActionListener {

    JLabel label;

    NotepadMenu() {
        JFrame frame = new JFrame("Untitled - Notepad");
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Select a menu");

        JMenuBar menubar = new JMenuBar();

        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic('F');

        JMenuItem jmiNew = new JMenuItem("New", 'N');
        jmiNew.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N,
                        InputEvent.CTRL_MASK));
        jmFile.add(jmiNew);

        JMenuItem jmiOpen = addMenuItem(jmFile, "Open...", 'O');  
            jmiOpen.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_O,
                        InputEvent.CTRL_MASK));       

        jmiOpen.addActionListener(this);

        JMenuItem jmiSave = addMenuItem(jmFile, "Save", 'S');
        JMenuItem jmiSaveAs = addMenuItem(jmFile, "Save As..", 'A');
         
        jmFile.addSeparator();
        JMenuItem jmiPageSetup = addMenuItem(jmFile, "Page Setup...", 'u');
        JMenuItem jmiPrint = addMenuItem(jmFile, "Print...", 'P');
        jmFile.addSeparator();
        JMenuItem jmiExit = addMenuItem(jmFile, "Exit", 'x');
        jmiExit.setAccelerator(KeyStroke.getKeyStroke("control X"));
        jmiExit.addActionListener(ae -> {
            System.exit(0);
        });

        JMenu jmEdit = new JMenu("Edit");
        jmEdit.setMnemonic('E');

        JMenuItem jmgoto = new JMenuItem("Goto...", 'G');
        jmgoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
        jmgoto.addActionListener(this);
        jmEdit.add(jmgoto);


        JMenu jmFormat = new JMenu("Format");
        jmFormat.setMnemonic('o');

        JCheckBoxMenuItem jcbmiWordWrap = new JCheckBoxMenuItem("Word Wrap", false);
        jcbmiWordWrap.setMnemonic('W');
        jmFormat.add(jcbmiWordWrap);
        JMenuItem jmiFont = addMenuItem(jmFormat, "Font...", 'F');

        JMenu jmView = new JMenu("View");
        jmView.setMnemonic('V');

        JMenu jmHelp = new JMenu("Help");
        jmHelp.setMnemonic('H');

        // add menus to the main menubar
        menubar.add(jmFile);
        menubar.add(jmEdit);
        menubar.add(jmFormat);
        menubar.add(jmView);
        menubar.add(jmHelp);

        jmiNew.addActionListener(this);
        jmiExit.addActionListener(this);

        frame.add(label);

        frame.setJMenuBar(menubar);

        frame.setVisible(true);
    }

    private static JMenuItem addMenuItem(JMenu jm, String text, char mmemonic) {
        JMenuItem jmi = new JMenuItem(text, mmemonic);
        jm.add(jmi);

        return jmi;
    }

    public void actionPerformed(ActionEvent ae) {
        // Get the action command from the menu selection. 
        String comStr = ae.getActionCommand();
   
        if (comStr.equals("Open...")){
            JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION){
                label.setText(fc.getSelectedFile().getAbsolutePath());
            }
            
        }
        else if (comStr.equals("Goto...")){
            String lineNumber = JOptionPane.showInputDialog("Line Number:");
            label.setText(lineNumber);
        }

        else
            label.setText(comStr + " Selected");
    }

    public static void main(String args[]) {
        // Create the frame on the event dispatching thread.   
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NotepadMenu();
            }
        });
    }
}