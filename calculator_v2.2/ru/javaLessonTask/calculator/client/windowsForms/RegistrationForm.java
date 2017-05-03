package ru.javaLessonTask.calculator.client.windowsForms;

import ru.javaLessonTask.calculator.client.listeners.ActionListenerRegistration;

import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JFrame {
    public static JFrame registrForm;
    private JLabel label;
    public static TextField textField;
    public static JLabel labelInfo = new JLabel();
    public static JPasswordField passwordField;
    private JButton button;

    public ActionListenerRegistration reg = new ActionListenerRegistration();


    public RegistrationForm(){
        registrForm = new JFrame("Регистрация");
        JPanel panel = new JPanel(new GridLayout(5,1));
        label = new JLabel("Логин");
        textField = new TextField();
        panel.add(label);
        panel.add(textField);
        passwordField = new JPasswordField();
        label = new JLabel("Пароль");
        panel.add(label);
        panel.add(passwordField);
        button = new JButton("Войти");
        button.addActionListener(reg);
        panel.add(button);
        button = new JButton("Регистрация");
        button.addActionListener(reg);
        panel.add(button);
        panel.add(labelInfo);
        registrForm.add(panel);
    }
}
