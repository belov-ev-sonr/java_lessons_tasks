package ru.javaLessonTask.calculator.client.socketClient;

import ru.javaLessonTask.calculator.StoreOperationNode;
import ru.javaLessonTask.calculator.client.windowsForms.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCanal {

    private static ClientCanal instance;
    private int PORT=2110;

    private ClientCanal() throws IOException {}

    public static ClientCanal getInstance() throws IOException {
        if(instance==null)
            instance=new ClientCanal();
        return instance;
    }


    public boolean sendClientData(String message) {
        try(Socket socket = new Socket("localhost",PORT)){
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(message);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Object temp = inputStream.read();
            if(!temp.equals(0))
                return true;
            else
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка соединения с сервером");
            RegistrationForm.labelInfo.setText("Ошибка соединения с сервером");
            return false;
        }
    }


    public double sendClientData(StoreOperationNode node) throws ClassNotFoundException {
        try(Socket socket = new Socket("localhost",PORT)){
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
