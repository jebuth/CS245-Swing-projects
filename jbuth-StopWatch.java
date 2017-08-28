// Name:        Buth, Justin
// Project:     #1
// Due Date:    10/13/15
// Course:      CS 245 01 F15
// Description: A simple StopWatch program from the example on page 24 of 
//              the textbook with slight modifications.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class StopWatch implements ActionListener {

    JLabel jlab;
    long start; 

    StopWatch() {
        JFrame jfrm = new JFrame("J. Buth's Stopwatch");

        jfrm.getContentPane().setLayout(new FlowLayout());

        jfrm.setSize(230, 90);

        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton jbtnStart = new JButton("Start");
        JButton jbtnStop = new JButton("Stop");
 
        jbtnStart.addActionListener(this);
        jbtnStop.addActionListener(this);

        jfrm.getContentPane().add(jbtnStart);
        jfrm.getContentPane().add(jbtnStop);

        jlab = new JLabel("Press Start to begin timing.");
 
        jfrm.getContentPane().add(jlab);
 
        jfrm.setVisible(true);
    }
 
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Start")) {
            start = ae.getWhen(); // Start value //
            jlab.setText("Stopwatch is Running...");
        } else {
            jlab.setText("Elapsed time is " + (double) (ae.getWhen() - start) / 1000); // Current value - start value
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StopWatch();
            }
        });
    }

}