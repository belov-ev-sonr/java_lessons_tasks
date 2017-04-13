package Start;

import windowsForms.RegistrationForm;

import javax.swing.*;

public class Start {
    private static RegistrationForm logger;

    public static void main(String[] args) {
        logger = new RegistrationForm();
        logger.registrForm.setSize(350,200);
        logger.registrForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logger.registrForm.setVisible(true);
//        if(ActionListenerRegistration.status==true){
//            logger.registrForm.setVisible(false);
//            window=new TargetWindowForm();
//            window.jframe.setSize(350,400);
//            window.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            window.jframe.setVisible(true);
//        }

//
    }

}
