package ru.javaLessonTask.calculator.client.Start;

import ru.javaLessonTask.calculator.client.socketClient.ClientCanal;
import ru.javaLessonTask.calculator.client.windowsForms.RegistrationForm;

import javax.swing.*;
import java.io.IOException;

public class StartClient {

    public static void main(String[] args) throws IOException {
        RegistrationForm logger = new RegistrationForm();
        logger.registrForm.setSize(350,200);
        logger.registrForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logger.registrForm.setVisible(true);
        ClientCanal.getInstance();
    }

}
