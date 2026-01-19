package com.javacalc;

import java.awt.event.*;
import java.util.*;

/** Class to deal with Calculator stack operations. **/
public class Calculation {

    protected static Stack<Double> stack = new Stack<>();

    static class EnterListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            try {
                if (GUI.textField.getText().equals("") &&
                        !GUI.textFieldResult.getText().equals("")) {
                    GUI.textField.setText(GUI.textFieldResult.getText());
                    GUI.textFieldResult.setText("");
                }
                stack.push(Double.parseDouble(GUI.textField.getText()));
                GUI.textField.setText("");
                GUI.textFieldStack.setText(stack.toString().replace("[", "").replace("]", ""));
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
                    default -> 0;
                };
            return result;
        }

        /** Action event for when any function is pressed.
         * Adds the current text field to the stack and operates on it,
         * or, if the text field is empty but the stack has operands in it,
         * pops the top element off the stack and operates on it.
         * @param evt Character of the given operator
         * @throws EmptyStackException if operation is tried on an empty stack
         */
        public void actionPerformed(ActionEvent evt) {
            char operator = evt.getActionCommand().charAt(0);
            try {
                if (GUI.textField.getText().equalsIgnoreCase("")) {
                    double result = stack.pop();
                    GUI.textField.setText(Double.toString(result));
                    GUI.textFieldResult.setText(Double.toString(result));
                    GUI.textFieldStack.setText(stack.toString().replace("[", "").replace("]", ""));
                    if (stack.empty()) GUI.textFieldStack.setText("");
                }
                else {
                    stack.push(Double.parseDouble(GUI.textField.getText()));
                    double result = eval(operator);
                    GUI.textField.setText(Double.toString(result));
                    GUI.textFieldResult.setText(Double.toString(result));
                    GUI.textFieldStack.setText(stack.toString().replace("[", "").replace("]", ""));
                    if (stack.empty()) GUI.textFieldStack.setText("");
                }
            } catch (EmptyStackException ex) {
                System.err.println(ex + ": Stack is empty");
            }

        }
    }

}
