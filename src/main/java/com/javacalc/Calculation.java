package com.javacalc;

import java.awt.event.*;
import java.util.*;

/** Class to deal with Calculator stack operations. **/
public class Calculation {

    protected static Stack<Double> stack = new Stack<>();

    static class EnterListener implements ActionListener {
        /** Action event for when the enter button is pressed.
         * Adds the current text field to the stack and operates on it,
         * or, if the default text field is empty but the result stack has,
         * a value, the result stack is pushed to the current stack.
         * Otherwise, an exception is thrown.
         * @param evt The given action event
         * @throws EmptyStackException if an operation is tried on an empty stack
         */
        public void actionPerformed(ActionEvent evt) {
            try {
                if (GUI.textField.getText().equals("") &&
                        !GUI.textFieldBottom.getText().equals("")) {
                    GUI.textField.setText(GUI.textFieldBottom.getText());
                    GUI.textFieldBottom.setText("");
                } else {
                    stack.push(Double.parseDouble(GUI.textField.getText()));
                    GUI.textField.setText("");
                    GUI.textFieldTop.setText(
                            stack.toString().replace("[", "").replace("]", ""));
                }
            } catch (NumberFormatException ex) {
                System.err.println(ex + ": Text field is empty");
            }
        }
    }

    static class FunctionListener implements ActionListener {
        /** Evalutes current operation.
         * Pops two operands off of the stack and applies the given operator
         * @param op Character of the operator
         * @return The result of operation
         */
        private double eval(char op) {
            double operand1 = stack.pop();
            double operand2 = stack.pop();

            double result =
                switch (op) {
                    case '+' -> operand2 + operand1;
                    case '-' -> operand2 - operand1;
                    case '*' -> operand2 * operand1;
                    case '/' -> operand2 / operand1;
                    case '^' -> Math.pow(operand2, operand1);
                    case '%' -> (operand2 / 100) * operand1;
                    default -> 0;
                };
            return result;
        }

        /** Action event for when any function is pressed.
         * Adds the current text field to the stack and operates on it,
         * or, if the text field is empty but the stack has operands in it,
         * pops the top element off the stack and operates on it.
         * @param evt The given action event
         * @throws EmptyStackException if an operation is tried on an empty stack
         */
        public void actionPerformed(ActionEvent evt) {
            char operator = evt.getActionCommand().charAt(0);
            boolean textFieldEmpty = GUI.textField.getText().equalsIgnoreCase("");
            String stackContents = stack.toString().replace("[", "").replace("]", "");
            try {
                if (textFieldEmpty) {
                    double result = stack.pop();
                    GUI.textField.setText(Double.toString(result));
                    GUI.textFieldTop.setText(stackContents);
                    if (stack.empty()) GUI.textFieldTop.setText("");
                }
                else {
                    stack.push(Double.parseDouble(GUI.textField.getText()));
                    double result = eval(operator);
                    GUI.textField.setText(Double.toString(result));
                    GUI.textFieldTop.setText(stackContents);
                    if (stack.empty()) GUI.textFieldTop.setText("");
                }
            } catch (EmptyStackException ex) {
                System.err.println(ex + ": Stack is empty");
            }

        }
    }

}
