package com.javacalc;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/** Class to display Calculator GUI. **/
public class GUI {

    /** Components to hold GUI elements. */
    private static JFrame frame;
    private static JPanel panel, panelText, panelButtons;
    protected static JTextField textField, textFieldTop, textFieldBottom;

    /** Arrays for storing GUI elements. */
    // Array to store the numbers 0-9
    private static JButton[] numButGrid = new JButton[10];
    // Array to store the calculator functions
    private static JButton[] funButGrid = new JButton[11];
    private JButton butAdd, butSub, butMul, butSqr, butPcnt;
    private JButton butDiv, butPer, butClr, butClrAll, butEnt, butDel;

    /** Action listeners for calculator operations. */
    private static Calculation.EnterListener entListener =
        new Calculation.EnterListener();
    private static Calculation.FunctionListener funListener =
        new Calculation.FunctionListener();
    private static ButtonListener buttonListener = new ButtonListener();

    /** Graphical user interface constructor. */
    public GUI() {
        // Create main panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create text panel
        panelText = new JPanel();
        panelText.setLayout(new BoxLayout(panelText, BoxLayout.Y_AXIS));

        // Create button panel
        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(6, 4));

        // Create text fields
        textFieldTop = new JTextField(20);
        textFieldTop.setEditable(false);
        textFieldTop.setFocusable(false);

        textField = new JTextField(20);
        textField.setEditable(false);

        textFieldBottom = new JTextField(20);
        textFieldBottom.setEditable(false);
        textFieldBottom.setFocusable(false);

        // Assign each number to a space in the number button grid array
        for (int i = 0; i <= 9; i++) {
            numButGrid[i] = new JButton(Integer.toString(i));
            numButGrid[i].addActionListener(buttonListener);
        }

        // Create buttons for each function operation
        butAdd = new JButton("+");
        butSub = new JButton("-");
        butDiv = new JButton("/");
        butMul = new JButton("*");
        butSqr = new JButton("^");
        butClr = new JButton("C");
        butClrAll = new JButton("CE");
        butPer = new JButton(".");
        butEnt = new JButton("Enter");
        butDel = new JButton("Del");
        butPcnt = new JButton("%");

        // Operations
        funButGrid[0] = butAdd;
        funButGrid[1] = butSub;
        funButGrid[2] = butDiv;
        funButGrid[3] = butMul;
        funButGrid[4] = butSqr;
        funButGrid[5] = butPcnt;
        for (int i = 0; i <= 5; i++)
            funButGrid[i].addActionListener(funListener);

        // Modifiers
        funButGrid[6] = butPer;
        funButGrid[7] = butDel;
        funButGrid[8] = butClr;
        funButGrid[9] = butClrAll;
        for (int i = 6; i <= 9; i++)
            funButGrid[i].addActionListener(buttonListener);

        // Enter
        funButGrid[10] = butEnt;
        funButGrid[10].addActionListener(entListener);

        // Build GUI
        // Add text fields
        panelText.add(textFieldTop);
        panelText.add(textField);
        panelText.add(textFieldBottom);

        // Add buttons
        panelButtons.add(butClr);
        panelButtons.add(butClrAll);
        panelButtons.add(butDel);
        panelButtons.add(butAdd);
        for (int i = 7; i <= 9; i++)
            panelButtons.add(numButGrid[i]);
        panelButtons.add(butSub);
        for (int i = 4; i <= 6; i++)
            panelButtons.add(numButGrid[i]);
        panelButtons.add(butMul);
        for (int i = 1; i <= 3; i++)
            panelButtons.add(numButGrid[i]);
        panelButtons.add(butDiv);
        panelButtons.add(numButGrid[0]);
        panelButtons.add(butPer);
        panelButtons.add(butSqr);
        panelButtons.add(butPcnt);

        // Construct panel
        panel.add(panelText);

        panel.add(panelButtons);
        panel.add(butEnt);

        // Construct frame
        frame = new JFrame("Calculator");
        frame.setSize(230,315);

        frame.add(panel);

        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new GUI();
    }

    static class ButtonListener implements ActionListener {
        /** Action event for when any non-operation button is pressed.
         * C   clears the default text field, but leaves all others and the stack alone.
         * CE  clears all text fields, and clears the stack.
         * Del deletes one character from the default text field.
         * 0-9 (any single digit) will append the digit to the default text field
         * @param evt The given action event
         */
        public void actionPerformed(ActionEvent evt) {
            String actCom = evt.getActionCommand();

            switch (actCom) {
                case "C" :
                    textFieldBottom.setText(textField.getText());
                    textField.setText("");
                    break;
                case "CE" :
                    textField.setText("");
                    textFieldTop.setText("");
                    textFieldBottom.setText("");
                    Calculation.stack.clear();
                    break;
                case "Del" :
                    if (!textField.getText().equalsIgnoreCase(""))
                        textField.setText(
                                textField.getText()
                                .substring(0, textField.getText().length() -1));
                    else
                        textField.setText("");
                    break;
                default : 
                    if (actCom.matches("[0-9]")) {
                        textField.setText(GUI.textField.getText().concat(actCom));
                    }
                    break;
            }
        }
    }

}
