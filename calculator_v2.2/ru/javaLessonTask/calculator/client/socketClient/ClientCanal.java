package ru.javaLessonTask.calculator.client.socketClient;

import ru.javaLessonTask.calculator.StoreOperationNode;
import ru.javaLessonTask.calculator.client.windowsForms.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCanal {

    private static ClientCanal instance;
    private static int PORT=2110;
    public static Socket socket;

    private ClientCanal() throws IOException {}

    public static ClientCanal getInstance() throws IOException {
        if(instance==null) {
            instance = new ClientCanal();
            try {
                socket = new Socket("localhost",PORT);
            } catch (IOException e) {
                RegistrationForm.labelInfo.setText("Сервер не отвечает");
                System.out.println("Сервер не отвечает (ClientCanal-23)");
            }
        }
        return instance;
    }


    public boolean sendClientData(String message) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(message);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Object temp = inputStream.read();
            if (!temp.equals(0))
                return true;
            else
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка соединения с сервером");
            RegistrationForm.labelInfo.setText("Ошибка соединения с сервером");
            return false;
        }catch (NullPointerException e1){
            System.out.println("Соединение не создано");
            RegistrationForm.labelInfo.setText("Соединение не создано");
            e1.printStackTrace();
            return false;
        }
    }


    public double sendClientData(StoreOperationNode node) throws ClassNotFoundException {
        try{
            ObjectOutputStream outputStream =new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(node);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object obj = inputStream.readObject();
            return (double)obj;

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
