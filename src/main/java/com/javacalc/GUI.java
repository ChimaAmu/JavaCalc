package com.javacalc;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/** Class to display Calculator GUI. **/
public class GUI {

    private static JFrame frame;
    protected static JTextField textField, textFieldStack, textFieldResult;
    private static JButton[] numButGrid = new JButton[10];
    private static JButton[] funButGrid = new JButton[9];
    private JButton butAdd, butSub, butMul;
    private JButton butDiv, butPer, butClr, butClrAll, butEql, butDel;
    private static JPanel panel, panelText, panelButtons;

    private static Calculation.EnterListener eqlListener = new Calculation.EnterListener();
    private static Calculation.FunctionListener funListener = new Calculation.FunctionListener();
    private static NumberListener numListener = new NumberListener();
    private static ClearListener clrListener = new ClearListener();
    private static ClearAllListener clrAllListener = new ClearAllListener();
    private static DeleteListener delListener = new DeleteListener();


    public GUI() {
        frame = new JFrame("Calculator");
        frame.setSize(260,250);

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
            numButGrid[i].addActionListener(numListener);
            panelButtons.add(numButGrid[i]);
        }

        butAdd = new JButton("+");
        butSub = new JButton("-");
        butDiv = new JButton("/");
        butMul = new JButton("*");
        butClr = new JButton("C");
        butClrAll = new JButton("CE");
        butPer = new JButton(".");
        butEql = new JButton("Enter");
        butDel = new JButton("Del");

        funButGrid[0] = butAdd;
        funButGrid[1] = butSub;
        funButGrid[2] = butDiv;
        funButGrid[3] = butMul;
        funButGrid[4] = butPer;
        funButGrid[5] = butDel;
        funButGrid[6] = butClr;
        funButGrid[7] = butClrAll;
        funButGrid[8] = butEql;

        funButGrid[0].addActionListener(funListener);
        funButGrid[1].addActionListener(funListener);
        funButGrid[2].addActionListener(funListener);
        funButGrid[3].addActionListener(funListener);
        funButGrid[4].addActionListener(numListener);
        funButGrid[5].addActionListener(delListener);
        funButGrid[6].addActionListener(clrListener);
        funButGrid[7].addActionListener(clrAllListener);
        funButGrid[8].addActionListener(eqlListener);

        panelButtons.add(butAdd);
        panelButtons.add(butSub);
        panelButtons.add(butMul);
        panelButtons.add(butDiv);
        panelButtons.add(butDel);
        panelButtons.add(butClr);
        panelButtons.add(butClrAll);
        panelButtons.add(butPer);
        panelButtons.add(butEql);

        panel.add(panelButtons);

        frame.add(panel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new GUI();
    }

    static class NumberListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String num = evt.getActionCommand();
                textField.setText(GUI.textField.getText().concat(num));
        }
    }

    static class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            textField.setText("");
        }
    }

    static class ClearAllListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            textField.setText("");
            textFieldStack.setText("");
            textFieldResult.setText("");
            Calculation.stack.clear();
        }
    }

    static class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (!textField.getText().equalsIgnoreCase(""))
                textField.setText(textField.getText().substring(0, textField.getText().length() -1));
            else
                textField.setText("");
        }
    }
}
