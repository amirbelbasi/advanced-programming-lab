package ir.ac.aut.ceit.calculator;


import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

/**
 * simple calculator
 *
 * @author Amirali Belbasi
 * @version 0.2
 */
public class Gui implements KeyListener, ActionListener {
    private JFrame mainFrame;
    private JMenuBar menuBar;
    private JPanel panel;
    private JTextArea textArea;
    private long currentValue;
    private String operation;
    private static final String COMMAND_PLUS = "+";
    private static final String COMMAND_MINUS = "-";
    private static final String COMMAND_DIVIDE = "/";
    private static final String COMMAND_MULTIPLY = "*";
    private static final String COMMAND_MOD = "%";
    private static final String COMMAND_EMPTY = "#";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_COPY = "copy";
    private static final String COMMAND_CLEAR = "clear";
    private static final String COMMAND_BACK = "back";
    private static final String COMMAND_EQUAL = "=";
    private static final String COMMAND_NUMBER = "number";

    public Gui(){
        init();
        drawElements();
        mainFrame.addKeyListener(this);
        mainFrame.setFocusable(true);
        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
        mainFrame.setSize(new Dimension(300, 500));
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void drawElements() {
        drawMenu();
        drawTextField();
        drawKeys();
    }

    public void addBtn(JButton btn, JPanel cmp) {
        btn.addActionListener(this);
        btn.addKeyListener(this);
        cmp.add(btn);
    }

    private void drawKeys() {
        panel.setSize(new Dimension(300, 400));
        panel.setLayout(new GridLayout(9, 3));
        panel.setLocation(0, 0);

        JButton btn = new JButton("clear");
        btn.setActionCommand(COMMAND_CLEAR);
        addBtn(btn, panel);

        btn = new JButton("back");
        btn.setActionCommand(COMMAND_BACK);
        addBtn(btn, panel);

        for(int i = 0; i < 10; ++i) {
            btn = new JButton("" + i);
            btn.setActionCommand(COMMAND_NUMBER);
            addBtn(btn, panel);
        }

        btn = new JButton("*");
        btn.setActionCommand(COMMAND_MULTIPLY);
        addBtn(btn, panel);

        btn = new JButton("/");
        btn.setActionCommand(COMMAND_DIVIDE);
        addBtn(btn, panel);

        btn = new JButton("%");
        btn.setActionCommand(COMMAND_MOD);
        addBtn(btn, panel);

        btn = new JButton("+");
        btn.setActionCommand(COMMAND_PLUS);
        addBtn(btn, panel);

        btn = new JButton("-");
        btn.setActionCommand(COMMAND_MINUS);
        addBtn(btn, panel);

        btn = new JButton("=");
        btn.setActionCommand(COMMAND_EQUAL);
        addBtn(btn, panel);

        mainFrame.add(panel);
    }

    private void drawTextField() {
        textArea.setSize(new Dimension(300, 50));
        textArea.setEditable(false);
        mainFrame.add(textArea);
    }

    private void drawMenu() {
        JMenu menu = new JMenu("menu");
        menu.setMnemonic(KeyEvent.VK_M);

        JMenuItem copy = new JMenuItem("copy");
        copy.addActionListener(this);
        copy.setActionCommand(COMMAND_COPY);
        copy.setMnemonic(KeyEvent.VK_C);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));

        JMenuItem exit = new JMenuItem("exit");
        exit.addActionListener(this);
        exit.setActionCommand(COMMAND_EXIT);
        exit.setMnemonic(KeyEvent.VK_X);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        menu.add(copy);
        menu.add(exit);
        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);
    }

    private void init() {
        mainFrame = new JFrame("Calculator");
        menuBar = new JMenuBar();
        panel = new JPanel();
        textArea = new JTextArea();
        currentValue = 0;
        operation = COMMAND_EMPTY;
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char text = e.getKeyChar();
        if(text >= '0' && text <= '9') {
            textArea.append("" + text);
        }
        switch ("" + text) {
            case "\b":
                try {
                    textArea.setText(textArea.getText(0, textArea.getText().length() - 1));
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }
                break;
            case COMMAND_DIVIDE:
                push();
                textArea.setText("");
                operation = COMMAND_DIVIDE;
                break;
            case COMMAND_MULTIPLY:
                push();
                textArea.setText("");
                operation = COMMAND_MULTIPLY;
                break;
            case COMMAND_MOD:
                push();
                textArea.setText("");
                operation = COMMAND_MOD;
                break;
            case COMMAND_PLUS:
                push();
                textArea.setText("");
                operation = COMMAND_PLUS;
                break;
            case COMMAND_MINUS:
                push();
                textArea.setText("");
                operation = COMMAND_MINUS;
                break;
            case COMMAND_EQUAL:
                push();
                textArea.setText("" + currentValue);
                operation = COMMAND_EQUAL;
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case COMMAND_EXIT:
                System.exit(0);
                break;
            case COMMAND_COPY:
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection("" + currentValue), null);
                break;
            case COMMAND_BACK:
                try {
                    textArea.setText(textArea.getText(0, textArea.getText().length() - 1));
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }
                break;
            case COMMAND_CLEAR:
                textArea.setText("");
                break;
            case COMMAND_DIVIDE:
                push();
                textArea.setText("");
                operation = COMMAND_DIVIDE;
                break;
            case COMMAND_MULTIPLY:
                push();
                textArea.setText("");
                operation = COMMAND_MULTIPLY;
                break;
            case COMMAND_MOD:
                push();
                textArea.setText("");
                operation = COMMAND_MOD;
                break;
            case COMMAND_PLUS:
                push();
                textArea.setText("");
                operation = COMMAND_PLUS;
                break;
            case COMMAND_MINUS:
                push();
                textArea.setText("");
                operation = COMMAND_MINUS;
                break;
            case COMMAND_EQUAL:
                push();
                textArea.setText("" + currentValue);
                operation = COMMAND_EQUAL;
                break;
            case COMMAND_EMPTY:
                push();
                textArea.setText("");
                operation = COMMAND_EMPTY;
                break;
        }
    }

    private long displayToNumber() {
        long ret = 0;
        try {
            ret = Long.parseLong(textArea.getText());
        } catch (Exception e) {}
        return ret;
    }

    private void push() {
        switch (operation) {
            case COMMAND_EMPTY:
                currentValue = displayToNumber();
                break;
            case COMMAND_DIVIDE:
                currentValue /= displayToNumber();
                break;
            case COMMAND_MULTIPLY:
                currentValue *= displayToNumber();
                break;
            case COMMAND_MOD:
                currentValue %= displayToNumber();
                break;
            case COMMAND_PLUS:
                currentValue += displayToNumber();
                break;
            case COMMAND_MINUS:
                currentValue -= displayToNumber();
                break;
        }
    }
}