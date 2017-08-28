// Name:        Buth, Justin
// Project      #2
// Due Date:    10/30/15
// Course:      CS 245 01 F15
// Description: The assignment was to implement a simple integer calculator with basic features.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.StyleConstants;

class Calculator implements ActionListener {

    private JFrame frame = null;
    private JRootPane root = null;
    private JPanel panel = null;
    private JTextField displayScreen = null;
    private JPanel buttonGroup = null;
    private JPanel buttonPane = null;
    private Image appIcon = null;
    private JButton zero = null;
    private JButton one = null;
    private JButton two = null;
    private JButton three = null;
    private JButton four = null;
    private JButton five = null;
    private JButton six = null;
    private JButton seven = null;
    private JButton eight = null;
    private JButton nine = null;
    
    private JButton divide = null;
    private JButton add = null;
    private JButton subtract = null;
    private JButton multiply = null;
    
    private JButton equals = null;
    private JButton clear = null;
    
    private String expression = null;
    private boolean answerMode = false;
    private boolean firstOperand = true;
    private boolean initialState = true;
    private boolean aboutMode = false;
    
    public Calculator (){
        
        frame = new JFrame("Calculator");
        //appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Calculator.png"));

        //frame.setIconImage(appIcon);
        
        //displayScreen = new JTextArea(3, 10);
        displayScreen = new JTextField();
        displayScreen.setPreferredSize(new Dimension(250, 100));

        displayScreen.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        //displayScreen.setAlignmentX(StyleConstants.ALIGN_RIGHT);

        displayScreen.setFont(new Font("Courier", Font.PLAIN, 20));
        
        displayScreen.setBackground(Color.DARK_GRAY);
        displayScreen.setForeground(Color.WHITE);
        displayScreen.setText("0");
        
        panel = new JPanel(new BorderLayout(5, 5));

        panel.add(displayScreen, BorderLayout.NORTH);
       // panel.setBorder(new EmptyBorder(5, 1, 5, 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        frame.setSize(250, 385);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setResizable(false);
        
        buttonGroup = new JPanel(new GridLayout(4, 4, 1, 1));
        
        seven = new JButton("7");
        seven.setBackground(Color.LIGHT_GRAY);
        seven.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(seven);
        
        eight = new JButton("8");
        eight.setBackground(Color.LIGHT_GRAY);
        eight.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(eight);
        
        nine = new JButton("9");
        nine.setBackground(Color.LIGHT_GRAY);
        nine.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(nine);

        divide = new JButton("/");
        divide.setBackground(Color.PINK);
        divide.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(divide);

        four = new JButton("4");
        four.setBackground(Color.LIGHT_GRAY);
        four.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(four);
        
        five = new JButton("5");
        five.setBackground(Color.LIGHT_GRAY);
        five.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(five);
        
        six = new JButton("6");
        six.setBackground(Color.LIGHT_GRAY);
        six.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(six);

        multiply = new JButton("*");
        multiply.setBackground(Color.PINK);
        multiply.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(multiply);

        one = new JButton("1");
        one.setBackground(Color.LIGHT_GRAY);
        one.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(one);
        
        two = new JButton("2");
        two.setBackground(Color.LIGHT_GRAY);
        two.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(two);
        
        three = new JButton("3");
        three.setBackground(Color.LIGHT_GRAY);
        three.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(three);
        
        subtract = new JButton("-");
        subtract.setBackground(Color.PINK);
        subtract.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(subtract);


        zero = new JButton("0");
        zero.setBackground(Color.LIGHT_GRAY);
        zero.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(zero);

        clear = new JButton("C");
        clear.setBackground(Color.LIGHT_GRAY);
        clear.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(clear);

        equals = new JButton("=");
        equals.setBackground(Color.WHITE);
        equals.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(equals);

        add = new JButton("+");
        add.setBackground(Color.PINK);
        add.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonGroup.add(add);
        
        
        zero.addActionListener(this);
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);
        nine.addActionListener(this);
        add.addActionListener(this);
        subtract.addActionListener(this);
        multiply.addActionListener(this);
        divide.addActionListener(this);
        clear.addActionListener(this);
        equals.addActionListener(this);
        
        
        root = frame.getRootPane();
        root.setDefaultButton(equals);
        panel.add(buttonGroup, BorderLayout.CENTER);

       
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK){
            if (e.getActionCommand() == "C"){
                displayScreen.setText("c)2015 Justin Buth" + ")");
                aboutMode = true;
            }
        }
        
        switch (e.getActionCommand()){
            case "C": 
                if ((e.getModifiers() & ActionEvent.CTRL_MASK) != ActionEvent.CTRL_MASK){
                    displayScreen.setText("0");
                    initialState= true;
                    aboutMode = false;
                }
                break;
            case "1": 
                if ((firstOperand == false || initialState == true) && aboutMode == false){
                    displayScreen.setText("");
                    firstOperand = true;
                    initialState = false;
                }
                if (answerMode == true && aboutMode == false){
                    displayScreen.setText("");
                    answerMode = false;
                }
                if (displayScreen.getText().length() < 9 && aboutMode == false){
                    displayScreen.setText(displayScreen.getText() + "1");
                    expression += "1";
                }
                break;
            case "2": 
                if ((firstOperand == false || initialState == true) && aboutMode == false){
                    displayScreen.setText("");
                    firstOperand = true;
                    initialState = false;
                }
                if (answerMode == true && aboutMode == false){
                    displayScreen.setText("");
                    answerMode = false;
                }
                if (displayScreen.getText().length() < 9 && aboutMode == false){
                    displayScreen.setText(displayScreen.getText() + "2");
                    expression += "2";
                }
                break;
            case "3": 
                if ((firstOperand == false || initialState == true) && aboutMode == false){
                    displayScreen.setText("");
                    firstOperand = true;
                    initialState = false;
                }
                if (answerMode == true  && aboutMode == false){
                    displayScreen.setText("");
                    answerMode = false;
                }
                if (displayScreen.getText().length() < 9 && aboutMode == false){
                    displayScreen.setText(displayScreen.getText() + "3");
                    expression += "3";
                }
                break;
            case "4": 
                if ((firstOperand == false || initialState == true) && aboutMode == false){
                    displayScreen.setText("");
                    firstOperand = true;
                    initialState = false;
                }
                if (answerMode == true && aboutMode == false){
                    displayScreen.setText("");
                    answerMode = false;
                }
                if (displayScreen.getText().length() < 9 && aboutMode == false){
                    displayScreen.setText(displayScreen.getText() + "4");
                    expression += "4";
                }
                break;
            case "5": 
                if ((firstOperand == false || initialState == true) && aboutMode == false){
                    displayScreen.setText("");
                    firstOperand = true;
                    initialState = false;
                }
                if (answerMode == true && aboutMode == false){
                    displayScreen.setText("");
                    answerMode = false;
                }
                 if (displayScreen.getText().length() < 9 && aboutMode == false){
                    displayScreen.setText(displayScreen.getText() + "5");
                    expression += "5";
                }
                break;
            case "6": 
                if ((firstOperand == false || initialState == true) && aboutMode == false){
                    displayScreen.setText("");
                    firstOperand = true;
                    initialState = false;
                }
                if (answerMode == true && aboutMode == false){
                    displayScreen.setText("");
                    answerMode = false;
                }
                if (displayScreen.getText().length() < 9 && aboutMode == false){
                    displayScreen.setText(displayScreen.getText() + "6");
                    expression += "6";
                }
                break;
            case "7": 
                if ((firstOperand == false || initialState == true) && aboutMode == false){
                    displayScreen.setText("");
                    firstOperand = true;
                    initialState = false;
                }
                if (answerMode == true && aboutMode == false){
                    displayScreen.setText("");
                    answerMode = false;
                }
                if (displayScreen.getText().length() < 9 && aboutMode == false){
                    displayScreen.setText(displayScreen.getText() + "7");
                    expression += "7";
                }
                break;
            case "8": 
                if ((firstOperand == false || initialState == true) && aboutMode == false){
                    displayScreen.setText("");
                    firstOperand = true;
                    initialState = false;
                }
                if (answerMode == true && aboutMode == false){
                    displayScreen.setText("");
                    answerMode = false;
                }
                if (displayScreen.getText().length() < 9 && aboutMode == false){
                    displayScreen.setText(displayScreen.getText() + "8");
                    expression += "8";
                }
                break;
            case "9": 
                if ((firstOperand == false || initialState == true) && aboutMode == false){
                    displayScreen.setText("");
                    firstOperand = true;
                    initialState = false;
                }
                if (answerMode == true && aboutMode == false){
                    displayScreen.setText("");
                    answerMode = false;
                }
                if (displayScreen.getText().length() < 9 && aboutMode == false){
                    displayScreen.setText(displayScreen.getText() + "9");
                    expression += "9";
                }
                break;
            case "0": 
                if (firstOperand == false && aboutMode == false){
                    displayScreen.setText("");
                    firstOperand = true;
                }
                if (answerMode == true && aboutMode == false){
                    displayScreen.setText("");
                    answerMode = false;
                }
                if (aboutMode == false){
                    displayScreen.setText(displayScreen.getText() + "0");
                    expression += "0";
                }
                
                break;
            case "+": 
                displayScreen.setText(displayScreen.getText());
                expression = (displayScreen.getText() + "+");
                System.out.println(expression);
                firstOperand = false;
                break;
            case "-": 
                if (initialState == true && aboutMode == false){
                    displayScreen.setText("-");
                    expression = (displayScreen.getText() + "-");
                }
                else {
                    displayScreen.setText(displayScreen.getText());
                    expression = (displayScreen.getText() + "-");
                    System.out.println(expression);
                    firstOperand = false;
                }
                break;
            case "*": 
                displayScreen.setText(displayScreen.getText());
                expression = (displayScreen.getText() + "*");
                //System.out.println(expression);
                firstOperand = false;
                break;
            case "/": 
                 displayScreen.setText(displayScreen.getText());
                expression = (displayScreen.getText() + "/");
                //System.out.println(expression);
                firstOperand = false;
                break;
            case "=":
                if (answerMode == false){
                    String result = calculate(expression);
                    if (result.startsWith("-")){
                        result = result.substring(1, result.length());
                        displayScreen.setText(result + "-");
                    } else {
                        displayScreen.setText(result);
                        //aboutMode = true;
                       
                    }
                }
                answerMode = true;
                
                break;
                
        }
    }
    
    private String calculate(String expression){
       // expression = expression.substring(0, expression.length()-1);
        String [] equation = expression.split("\\+|\\-|\\/|\\*");

        long operand1 = Integer.parseInt(equation[0]);
        long operand2 = Integer.parseInt(equation[1]);
        
        //System.out.println("HERE: " + expression);
        
        if (expression.contains("+")){
            String sum = Long.toString(operand1 + operand2);
            if (sum.length() <= 10)
                return sum;
            else{
                aboutMode = true;
                return "Overflow";   
            }
        }
        else if (expression.contains("-")){
            return Long.toString(operand1 - operand2);
        }
        else if (expression.contains("*")){
            String product = Long.toString(operand1 * operand2);
            if (product.length() <= 10)
                return product;
            else{
                aboutMode = true;
                return "Overflow";   
            }
        }
        else if (expression.contains("/")){
            if (operand2 == 0){
                aboutMode = true;
                return "Div by 0";
            }
            return Long.toString(operand1 / operand2);
        }
        return null;
    }
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Calculator();
            }
        });
    }
}
