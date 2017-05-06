package ru.javaLessonTask.calculator.client.listeners;

import ru.javaLessonTask.calculator.client.socketClient.ClientCanal;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class ListenerClosedWindow implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(ClientCanal.socket!=null)
            try {
                ClientCanal.socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
