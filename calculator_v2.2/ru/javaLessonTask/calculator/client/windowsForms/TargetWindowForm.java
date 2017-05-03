package ru.javaLessonTask.calculator.client.windowsForms;

import ru.javaLessonTask.calculator.client.listeners.ActionListenerCalculator;

import javax.swing.*;
import java.awt.*;

public class TargetWindowForm extends JFrame {
    public JFrame jframe;
    public static JLabel label;
    public static JLabel label2;

    ActionListenerCalculator listener = new ActionListenerCalculator();

    public String[] button_numbers = {"C","Del","+-","/","7","8","9","*","4","5","6","-","1","2","3","+","0",".","="};

    public TargetWindowForm(){
        jframe = new JFrame("Калькулятор");
        JPanel panel_target = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panel_arifmetic = new JPanel(new GridLayout(5,4));

        label = new JLabel("0");
        label2 = new JLabel("");
        label.setFont(new Font("Verdana",Font.BOLD,24));
        label2.setFont(new Font("Verdana",Font.BOLD,12));

        panel.add(label2,BorderLayout.NORTH);
        panel.add(label,BorderLayout.SOUTH);


        JButton button;
        for (int i = 0; i <button_numbers.length ; i++) {
            button= new JButton(button_numbers[i]);
                button.addActionListener(listener);
            panel_arifmetic.add(button);
        }

        panel_target.add(panel,BorderLayout.NORTH);
        panel_target.add(panel_arifmetic,BorderLayout.CENTER);
        jframe.add(panel_target);
    }
}
