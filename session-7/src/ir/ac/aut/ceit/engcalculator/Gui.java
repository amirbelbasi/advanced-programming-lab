package ir.ac.aut.ceit.engcalculator;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * calculator
 *
 * @author Amirali Belbasi
 * @version 0.1
 */
public class Gui implements ActionListener {
    private JFrame frame;
    private JPanel simpleCalcPanel;
    private JPanel proCalcPanel;
    private JTabbedPane tabbedPane;
    private JTextArea display;
    private JScrollPane scrollPane;

    public Gui() {
        init();
    }

    private void init() {
        frame = new JFrame("Calculator");
        frame.setSize(500, 500);
        frame.setLocation(100, 100);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawElements();
        frame.setVisible(true);
    }

    private void drawElements() {
        drawTextArea();
        drawKeyboard();
    }

    private void drawKeyboard() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setSize(500, 400);
        drawSimpleKeyboard();
        drawProKeyboard();
        frame.add(tabbedPane);
    }

    private void drawProKeyboard() {
        proCalcPanel = new JPanel(new GridLayout(5, 7));
        proCalcPanel.setLocation(0, 0);
        proCalcPanel.setSize(500, 400);

        JButton btn = new JButton("back");
        btn.addActionListener(this);
        proCalcPanel.add(btn);

        btn = new JButton("clear");
        btn.addActionListener(this);
        proCalcPanel.add(btn);

        btn = new JButton("log");
        btn.addActionListener(this);
        proCalcPanel.add(btn);

        btn = new JButton("exp");
        btn.addActionListener(this);
        proCalcPanel.add(btn);

        JButton sinBtn = new JButton("sin");
        sinBtn.addActionListener(this);
        proCalcPanel.add(sinBtn);

        JButton tanBtn = new JButton("tan");
        tanBtn.addActionListener(this);
        proCalcPanel.add(tanBtn);

        JButton shift = new JButton("Shift");
        shift.setBackground(Color.BLUE);
        shift.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(shift.getBackground() != Color.RED) {
                    shift.setBackground(Color.RED);
                    sinBtn.setText("cos");
                    tanBtn.setText("cot");
                } else {
                    shift.setBackground(Color.BLUE);
                    sinBtn.setText("sin");
                    tanBtn.setText("tan");
                }
            }
        });
        proCalcPanel.add(shift);

        btn = new JButton("pi");
        btn.addActionListener(this);
        proCalcPanel.add(btn);

        btn = new JButton("neper");
        btn.addActionListener(this);
        proCalcPanel.add(btn);

        for(int i = 1; i <= 10; ++i) {
            btn = new JButton("" + (i % 10));
            btn.addActionListener(this);
            proCalcPanel.add(btn);
        }
        btn = new JButton("+");
        btn.addActionListener(this);
        proCalcPanel.add(btn);
        btn = new JButton("-");
        btn.addActionListener(this);
        proCalcPanel.add(btn);
        btn = new JButton("*");
        btn.addActionListener(this);
        proCalcPanel.add(btn);
        btn = new JButton("/");
        btn.addActionListener(this);
        proCalcPanel.add(btn);
        btn = new JButton("%");
        btn.addActionListener(this);
        proCalcPanel.add(btn);
        btn = new JButton("=");
        btn.addActionListener(this);
        proCalcPanel.add(btn);


        tabbedPane.add("Pro Keyboard", proCalcPanel);
    }

    private void drawSimpleKeyboard() {
        simpleCalcPanel = new JPanel(new GridLayout(4, 5));
        simpleCalcPanel.setLocation(0, 0);
        simpleCalcPanel.setSize(500, 400);

        JButton btn = new JButton("back");
        btn.addActionListener(this);
        simpleCalcPanel.add(btn);

        btn = new JButton("clear");
        btn.addActionListener(this);
        simpleCalcPanel.add(btn);

        for(int i = 1; i <= 10; ++i) {
            btn = new JButton("" + (i % 10));
            btn.addActionListener(this);
            simpleCalcPanel.add(btn);
        }
        btn = new JButton("+");
        btn.addActionListener(this);
        simpleCalcPanel.add(btn);
        btn = new JButton("-");
        btn.addActionListener(this);
        simpleCalcPanel.add(btn);
        btn = new JButton("*");
        btn.addActionListener(this);
        simpleCalcPanel.add(btn);
        btn = new JButton("/");
        btn.addActionListener(this);
        simpleCalcPanel.add(btn);
        btn = new JButton("%");
        btn.addActionListener(this);
        simpleCalcPanel.add(btn);
        btn = new JButton("=");
        btn.addActionListener(this);
        simpleCalcPanel.add(btn);

        tabbedPane.add("Simple Keyboard", simpleCalcPanel);
    }

    private void drawTextArea() {
        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Arial", 12, 12));
        scrollPane = new JScrollPane(display);
        scrollPane.setSize(500, 100);
        scrollPane.setLocation(0, 0);
        frame.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(((JButton) e.getSource()).getText().equals("pi"))
            display.append("3.14");
        else if(((JButton) e.getSource()).getText().equals("neper"))
            display.append("2.71");
        else if(!((JButton) e.getSource()).getText().equals("clear") && !((JButton) e.getSource()).getText().equals("back"))
            display.append(((JButton) e.getSource()).getText());
        else if(((JButton) e.getSource()).getText().equals("clear"))
            display.setText("");
        else {
            try {
                display.setText(display.getText(0, display.getText().length() - 1));
            } catch (BadLocationException badLocationException) { }
        }
    }
}