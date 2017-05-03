package ru.javaLessonTask.calculator.client.listeners;

import ru.javaLessonTask.calculator.client.cryptionAndDecryption.Cryptographer;
import ru.javaLessonTask.calculator.client.socketClient.ClientCanal;
import ru.javaLessonTask.calculator.client.windowsForms.RegistrationForm;
import ru.javaLessonTask.calculator.client.windowsForms.TargetWindowForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionListenerRegistration implements ActionListener{

    public static String user;
    private String password;
    private JButton button;
    private Cryptographer cryptographer = new Cryptographer();

    private static TargetWindowForm window;
    //шифрование ключа
    private String getCipherKey(String str){
        return cryptographer.encrypt(str);
    }
    //дешифрование ключа
    private String getDecipherKey(String str){
        return cryptographer.encrypt(str);
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        if(e.getSource() instanceof JButton){
            button=(JButton) e.getSource();
        }else{
            return;
        }
        if(button.getText().equals("Войти")){
            formationUsers();
            if(password.equals("")||user.equals("")) {
                RegistrationForm.labelInfo.setText("Заполните поля");
                return;
            }
            autorizationOfUser();
        }else if(button.getText().equals("Регистрация")){
            registrationOfUser();
        }
    }

    private void openTargetWindow(){
        RegistrationForm.registrForm.setVisible(false);
        window=new TargetWindowForm();
        window.jframe.setSize(350,400);
        window.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.jframe.setVisible(true);
    }

    private void autorizationOfUser(){

        try {
            if(ClientCanal.getInstance().sendClientData("aut"+" "+user+" "+getCipherKey(password))){
                openTargetWindow();
            }else{
                System.out.println("Не удалось авторизоваться");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registrationOfUser(){
        try {
            if(ClientCanal.getInstance().sendClientData("reg"+" "+user+" "+getCipherKey(password))){
                openTargetWindow();
            }else{
                System.out.println("Не удалось зарегестрировать пользователя");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void formationUsers(){
        user=RegistrationForm.textField.getText();
        password = RegistrationForm.passwordField.getText();
    }
}
