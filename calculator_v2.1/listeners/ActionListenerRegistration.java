package listeners;

import cryptionAndDecryption.Cryptographer;
import windowsForms.RegistrationForm;
import windowsForms.TargetWindowForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ActionListenerRegistration implements ActionListener{

    File file = new File("src/txtFiles/users.txt");
    public static String user;
    private String password;
    private JButton button;

    private static TargetWindowForm window;
    //шифрование ключа
    private static String getCipherKey(String str){
        return Cryptographer.encrypt(str);
    }
    //дешифрование ключа
    private static String getDecipherKey(String str){
        return Cryptographer.encrypt(str);
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
            try {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                    String s;
                    while((s=bufferedReader.readLine())!=null){
                        if(s.equals(user+" "+getCipherKey(password))){
                            RegistrationForm.registrForm.setVisible(false);
                            window=new TargetWindowForm();
                            window.jframe.setSize(350,400);
                            window.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            window.jframe.setVisible(true);
                            return;
                        }
                    }
                }
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        }else if(button.getText().equals("Регистрация")){
            formationUsers();
            try {
                if(!file.exists()){
                    file.createNewFile();
                }
                //считываем файл
                ArrayList<String> sb = new ArrayList<String>();
                try {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                        String s;
                        while((s=bufferedReader.readLine())!=null){
                            sb.add(s);
                        }
                    }
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                }
                for (int i = 0; i <sb.size() ; i++) {
                    if(sb.get(i).equals(user+" "+getCipherKey(password))) {
                        System.out.println("Запись существует");
                        return;
                    }
                }
                //записываем нового пользователя
                PrintWriter writer = new PrintWriter(file.getAbsoluteFile());

                try {
                    if(sb.size()!=0){
                        for (int i = 0; i <sb.size() ; i++) {
                            writer.println(sb.get(i));
                        }
                    }
                    writer.println(user+" "+getCipherKey(password));
                } finally {
                    writer.close();
                }
                RegistrationForm.registrForm.setVisible(false);
                window=new TargetWindowForm();
                window.jframe.setSize(350,400);
                window.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.jframe.setVisible(true);
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        }
    }

        private void formationUsers(){
            user=RegistrationForm.textField.getText();
            password = RegistrationForm.passwordField.getText();
        }
}
