package com.javacalc;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/** Class to display Calculator GUI. **/
public class GUI {

    private static JFrame frame;
    private static JPanel panel, panelText, panelButtons;
    protected static JTextField textField, textFieldStack, textFieldResult;

    private static JButton[] numButGrid = new JButton[10];
    private static JButton[] funButGrid = new JButton[11];
    private JButton butAdd, butSub, butMul, butSqr, butPcnt;
    private JButton butDiv, butPer, butClr, butClrAll, butEnt, butDel;

    private static Calculation.EnterListener entListener =
        new Calculation.EnterListener();
    private static Calculation.FunctionListener funListener =
        new Calculation.FunctionListener();

    private static ButtonListener buttonListener = new ButtonListener();

    public GUI() {
        // Create panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panelText = new JPanel();
        panelText.setLayout(new BoxLayout(panelText, BoxLayout.Y_AXIS));

        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(6, 4));

        // Create text fields
        textFieldStack = new JTextField(20);
        textFieldStack.setEditable(false);
        textFieldStack.setFocusable(false);

        textField = new JTextField(20);
        textField.setEditable(false);

        textFieldResult = new JTextField(20);
        textFieldResult.setEditable(false);
        textFieldResult.setFocusable(false);

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
        panelText.add(textFieldStack);
        panelText.add(textField);
        panelText.add(textFieldResult);

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
        panelButtons.add(butSqr);
        panelButtons.add(numButGrid[0]);
        panelButtons.add(butDiv);
        panelButtons.add(butPcnt);
        panelButtons.add(butPer);

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
        public void actionPerformed(ActionEvent evt) {
            String actCom = evt.getActionCommand();

            switch (actCom) {
                case "C" :
                    textField.setText("");
                    break;
                case "CE" :
                    textField.setText("");
                    textFieldStack.setText("");
                    textFieldResult.setText("");
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
