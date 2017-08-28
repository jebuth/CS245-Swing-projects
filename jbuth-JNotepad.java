// Name:        Buth, Justin
// Project:     #4
// Due Date:    12/4/15
// Course:      CS 245 01 F15
// Description: My version of Notepad.

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.border.BevelBorder;
import java.text.*;

public class JNotepad implements ActionListener{
    private JFrame frame;
    private JScrollPane jsp;
    private JTextArea jta;
    private String font;
    private String fontStyle;
    private int fontSize;
    private JDialog jd;
    private String[] allFonts;
    private JList fontList;
    private String[] allStyles;
    private JList styleList;
    private ArrayList<Integer> sizes;
    private JList sizeList;
    private boolean wordWrapSelected;
    private JCheckBoxMenuItem wordWrap;
    private JCheckBoxMenuItem statusBar;
    private JPanel sBar;
    private JLabel info;
    
    
    JNotepad(){
        wordWrapSelected = true;
        allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontList = new JList(allFonts);
        allStyles = new String[]{"Regular", "Italic", "Bold", "Bold Italic"};
        styleList = new JList(allStyles);
        sizes = new ArrayList<Integer>();
        for (int i = 8; i < 74; i+=2){
            sizes.add((Integer)i);
        }
        sizeList = new JList(sizes.toArray());

        frame = new JFrame("JNotepad");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        sBar = new JPanel();
        sBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(sBar, BorderLayout.SOUTH);
        sBar.setPreferredSize(new Dimension(frame.getWidth(), 23));
        
        //Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("JNotepad.png"));
        //frame.setIconImage(icon);
        
        font = "Courier New";
        fontStyle = "Regular";
        fontSize = 12;
        
        createMenus();
        jta = new JTextArea();
        jta.setFont(new Font(font, Font.PLAIN, fontSize));
        jta.setLineWrap(true);
        
        jsp = new JScrollPane(jta);
        
       
        
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        frame.add(jsp);
        frame.setVisible(true);
    }
    
    
    public void setFontStats(String font, String style, int size){
        this.font = font;
        this.fontStyle = style;
        this.fontSize = size;
        
        switch (style){
            case "Regular":
                jta.setFont(new Font(font, Font.PLAIN, fontSize));
                break;
            case "Italic":
                jta.setFont(new Font(font, Font.ITALIC, fontSize));
                break;
            case "Bold":
                jta.setFont(new Font(font, Font.BOLD, fontSize));
                break;
            case "Bold Italic":
                jta.setFont(new Font(font, Font.BOLD+Font.ITALIC, fontSize));
                break;
        }
        
        
    }
    
    public void createMenus(){
        JMenuBar menuBar = new JMenuBar();
        // File =====================================
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        JMenuItem neww = new JMenuItem("New");
        neww.setMnemonic('N');
        neww.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        JMenuItem open = new JMenuItem("Open...");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        open.addActionListener(this);
        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        JMenuItem saveAs = new JMenuItem("Save As...");
        saveAs.addActionListener(this);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic('x');
        file.add(neww);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(exit);
        menuBar.add(file);
        // Edit =====================================
        
        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('E');

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        JMenuItem delete = new JMenuItem("Delete");
        //delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE));
        JMenuItem find = new JMenuItem("Find");
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        find.addActionListener(this);
        JMenuItem findNext = new JMenuItem("Find Next");
        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        JMenuItem timeDate = new JMenuItem("Time/Date");
        //timeDate.setMnemonic(KeyEvent.VK_F5);
        timeDate.addActionListener(this);
        
        
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(delete);
        edit.addSeparator();
        edit.add(find);
        edit.add(findNext);
        edit.addSeparator();
        edit.add(selectAll);
        edit.add(timeDate);
        menuBar.add(edit);
        
         // Format =====================================
        JMenu format = new JMenu("Format");
        format.setMnemonic('O');
        wordWrap = new JCheckBoxMenuItem("Word Wrap", true);

        wordWrap.setMnemonic('W');
        wordWrap.addActionListener(this);
        JMenuItem font = new JMenuItem("Font");
        font.setMnemonic('F');
        font.addActionListener(this);
        format.add(wordWrap);
        format.add(font);
        menuBar.add(format);
        
        // View =====================================
        JMenu view = new JMenu("View");
        view.setMnemonic('V');
        statusBar = new JCheckBoxMenuItem("Status Bar");
        if (wordWrap.isSelected()){
            statusBar.setEnabled(false);
        } else{
            statusBar.setEnabled(true);
        }
        statusBar.setMnemonic('S');
        statusBar.addActionListener(this);
        
        view.add(statusBar);
        menuBar.add(view);
        
        // Help =====================================
        JMenu help = new JMenu("Help");
        help.setMnemonic('H');
        JMenuItem viewHelp = new JMenuItem("View Help");
        viewHelp.setMnemonic('H');
        JMenuItem about = new JMenuItem("About JNotepad");
        about.addActionListener(this);
        
        help.add(viewHelp);
        help.addSeparator();
        help.add(about);
        menuBar.add(help);
        frame.setJMenuBar(menuBar);
    }
    
    public void actionPerformed(ActionEvent ae){
        switch (ae.getActionCommand()){
            case "Open...":
                JFileChooser jfc = new JFileChooser();
                String [] extensions = new String[]{"txt", "java"};
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", extensions);
                jfc.setFileFilter(filter);
                int result = jfc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION){
                    File file = jfc.getSelectedFile();
                    try{
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line = null;
                        while ((line = br.readLine())!= null){
                            jta.append(line + "\n");
                        }
                        br.close();
                    }
                    catch (IOException e){
                        System.out.println(e.toString());
                    }
                }
                
                break;
            case "Save As...":
                JFileChooser jfc2 = new JFileChooser();
                int result2 = jfc2.showOpenDialog(frame);
                if (result2 == JFileChooser.APPROVE_OPTION){
                    try{
                        FileWriter fw = new FileWriter(jfc2.getSelectedFile()+".txt");
                        frame.setTitle("JNotepad - " + jfc2.getSelectedFile());
                        fw.write(jta.getText());
                        fw.close();
                    }
                    catch (IOException e){
                        System.out.println(e.toString());
                    }
                }
                break;
            case "Save":
                
                break;
                
            case "Find...":
                
                break;
            case "About JNotepad":
                JOptionPane.showMessageDialog(frame, "<html> Copyright (c) 2015 J Buth", "About JNotepad", 1, null);
                break;    
                
            case "Font":
                System.out.println("font");
                JPanel fontPanel1 = new JPanel(new BorderLayout());
                fontPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
                JPanel fontPanel2 = new JPanel(new BorderLayout());
                fontPanel2.setBorder(BorderFactory.createLoweredBevelBorder());
                JPanel fontPanel3 = new JPanel(new BorderLayout());
                fontPanel3.setBorder(BorderFactory.createLoweredBevelBorder());
                
               // fontPanel1.setSize(225,350);
                

                int fontIndex = 0;
                for (int i = 0; i < allFonts.length; i++){
                    if (allFonts[i].equals("Courier New"))
                        fontIndex = i;
                }
                //JList fontList = new JList(allFonts);
                //fontList.set
               
                JScrollPane fontPane = new JScrollPane(fontList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                fontPane.setPreferredSize(new Dimension(200, 100));
                
                fontPanel1.add(new JLabel("Select Font:"), BorderLayout.NORTH);
                fontPanel1.add(fontPane);
                
                //font = fontList.getSelectedValue().toString();
                
                //String [] allStyles = {"Regular", "Italic", "Bold", "Bold Italic"};
                //JList styleList = new JList(allStyles);
                styleList.setSelectedIndex(0);
                JScrollPane stylePane = new JScrollPane(styleList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                stylePane.setPreferredSize(new Dimension(80, 95));
                
                fontPanel2.add(new JLabel("Font Style:"), BorderLayout.NORTH);
                fontPanel2.add(stylePane);
                
                
                
                /*ArrayList<Integer> sizes = new ArrayList<Integer>();
                for (int i = 8; i < 74; i+=2){
                   sizes.add((Integer)i);
                }
                JList sizeList = new JList(sizes.toArray());*/
                sizeList.setSelectedIndex(2);
                JScrollPane sizePane = new JScrollPane(sizeList);
                sizePane.setPreferredSize(new Dimension(90,95));
                fontPanel3.add(new JLabel("Size:"), BorderLayout.NORTH);
                fontPanel3.add(sizePane);
                
                
                
                jd = new JDialog();
                jd.setLayout(new FlowLayout());
                jd.setSize(450,300);
                jd.add(fontPanel1);
                jd.add(fontPanel2);
                jd.add(fontPanel3);
                
                JPanel buttonPanel = new JPanel(new FlowLayout());
                JButton ok = new JButton("Ok");
                JButton cancel = new JButton("Cancel");
                
                ok.addActionListener(this);
                cancel.addActionListener(this);
                
                buttonPanel.add(ok);
                buttonPanel.add(cancel);
                
                jd.add(buttonPanel);
                
                jd.setVisible(true);
                break;
            case "Ok":
                font = fontList.getSelectedValue().toString();
                fontStyle = styleList.getSelectedValue().toString();
                fontSize = (int)sizeList.getSelectedValue();
                
                setFontStats(font, fontStyle, fontSize);
                
                System.out.println(font + "\n" + fontStyle + "\n" + fontSize);
                jd.dispose();
                break;
            case "Cancel":
                jd.dispose();
                break;
            case "Word Wrap":
                if (!wordWrap.isSelected()){
                    jta.setLineWrap(false);
                    statusBar.setEnabled(true);
                }
                else
                    jta.setLineWrap(true);
                break;
            case "Status Bar":
                if (statusBar.isSelected()){
                    int lineCount = jta.getLineCount();
                    int colCount = jta.getColumns();
                    info = new JLabel("Ln:" + lineCount + " Col:" + colCount);
                    info.setHorizontalAlignment(SwingConstants.RIGHT);
                    sBar.add(info);
                    frame.revalidate();

                } else
                    info = new JLabel("");
                break;
            case "Time/Date":
                DateFormat df = new SimpleDateFormat("h:mm a MM/dd/yyyy");
                Date date = new Date();
                jta.append(df.format(date));
                break;
            default:
                
                break;
   
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new JNotepad();
            }
        });

    }
    
}
