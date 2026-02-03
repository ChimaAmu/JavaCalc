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
    private JButton butDiv, butPer, butClr, butClrAll, butEql, butDel;

    private static Calculation.EnterListener eqlListener =
        new Calculation.EnterListener();
    private static Calculation.FunctionListener funListener =
        new Calculation.FunctionListener();

    private static ButtonListener buttonListener = new ButtonListener();

    public GUI() {
        frame = new JFrame("Calculator");
        frame.setSize(330,250);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panelText = new JPanel();
        panelText.setLayout(new BoxLayout(panelText, BoxLayout.Y_AXIS));

        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(5, 3));

        textFieldStack = new JTextField(20);
        textFieldStack.setEditable(false);
        textFieldStack.setFocusable(false);
        panelText.add(textFieldStack);

        textField = new JTextField(20);
        textField.setEditable(false);
        panelText.add(textField);

        textFieldResult = new JTextField(20);
        textFieldResult.setEditable(false);
        textFieldResult.setFocusable(false);
        panelText.add(textFieldResult);
        panel.add(panelText);

        for (int i = 0; i <= 9; i++) {
            numButGrid[i] = new JButton(Integer.toString(i));
            numButGrid[i].addActionListener(buttonListener);
            panelButtons.add(numButGrid[i]);
        }


        butAdd = new JButton("+");
        butAdd.setMnemonic(KeyEvent.VK_PLUS);
        butSub = new JButton("-");
        butDiv = new JButton("/");
        butMul = new JButton("*");
        butSqr = new JButton("^");
        butClr = new JButton("C");
        butClrAll = new JButton("CE");
        butPer = new JButton(".");
        butEql = new JButton("Enter");
        butDel = new JButton("Del");
        butPcnt = new JButton("%");


        // Functions
        funButGrid[0] = butAdd;
        funButGrid[1] = butSub;
        funButGrid[2] = butDiv;
        funButGrid[3] = butMul;
        funButGrid[4] = butSqr;
        funButGrid[5] = butPcnt;
        for (int i = 0; i <= 5; i++)
            funButGrid[i].addActionListener(funListener);

        // Buttons
        funButGrid[6] = butPer;
        funButGrid[7] = butDel;
        funButGrid[8] = butClr;
        funButGrid[9] = butClrAll;
        // Enter
        for (int i = 6; i <= 9; i++)
            funButGrid[i].addActionListener(buttonListener);

        funButGrid[10] = butEql;
        funButGrid[10].addActionListener(eqlListener);


        panelButtons.add(butAdd);
        panelButtons.add(butSub);
        panelButtons.add(butMul);
        panelButtons.add(butSqr);
        panelButtons.add(butDiv);
        panelButtons.add(butPcnt);
        panelButtons.add(butDel);
        panelButtons.add(butClr);
        panelButtons.add(butClrAll);
        panelButtons.add(butPer);
        panelButtons.add(butEql);

        panel.add(panelButtons);

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
                    System.out.println("C TEST");
                    textField.setText("");
                    break;
                case "CE" :
                    System.out.println("CE TEST");
                    textField.setText("");
                    textFieldStack.setText("");
                    textFieldResult.setText("");
                    Calculation.stack.clear();
                    break;
                case "Del" :
                    System.out.println("DEL TEST");
                    if (!textField.getText().equalsIgnoreCase(""))
                        textField.setText(
                                textField.getText()
                                .substring(0, textField.getText().length() -1));
                    else
                        textField.setText("");
                    break;
                default : 
                    if (actCom.matches("[0-9]")) {
                        System.out.println("NUM TEST");
                        textField.setText(GUI.textField.getText().concat(actCom));
                    }
                    break;
            }
        }
    }

}
