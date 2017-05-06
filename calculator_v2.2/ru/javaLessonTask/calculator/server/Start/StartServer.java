package ru.javaLessonTask.calculator.server.Start;

import ru.javaLessonTask.calculator.StoreOperationNode;
import ru.javaLessonTask.calculator.server.ThreadNewLogFile;
import ru.javaLessonTask.calculator.server.fileWorker.writeAndReadFile;
import ru.javaLessonTask.calculator.server.log.LoggerOperation;
import ru.javaLessonTask.calculator.server.objects.ArifmeticalCalculator;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {
    private static File users = new File("src/ru/javaLessonTask/calculator/server/users.txt");
    private static Thread arh;
    private static int PORT=2110;

    private static class Client extends Thread {
        private Socket socket;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;

        public Client(Socket socket) throws IOException {
            this.socket = socket;
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        }

        public void run() {
                Object request = null;
                try {
                    request = inputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    if (request instanceof String) {
                        String temp = (String) request;
                        if (temp.substring(0, 3).equals("aut")) {
                            if (writeAndReadFile.seachingInFile(temp.substring(4), users))
                                outputStream.write(1);
                            else
                                outputStream.write(0);
                        } else if (temp.substring(0, 3).equals("reg")) {
                            if (writeAndReadFile.writeFile(temp.substring(4), users))
                                outputStream.write(1);
                            else
                                outputStream.write(0);
                        } else
                            outputStream.write(0);


                    } else if (request instanceof StoreOperationNode) {
                        StoreOperationNode node = (StoreOperationNode) request;
                        node.setResult(seachOperation(node));
                        outputStream.writeObject(node.getResult());
                        logging(node);
                    }
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    public static void main(String[] args) throws ClassNotFoundException {
        LoggerOperation.clearLogs();
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            while(true){
                Socket socket = serverSocket.accept();
                try{
                    new Client(socket).start();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logging(StoreOperationNode node){
        //write in file (logging)
        if (LoggerOperation.countRecords == 10) {
            arh = new ThreadNewLogFile();
            arh.start();
        }
        LoggerOperation.loggingRecord(node);
    }

    private static double seachOperation(StoreOperationNode node) {
        switch (node.getOperation()) {
            case "+":
                return ArifmeticalCalculator.addUp(node.getArg1(),node.getArg2());
            case "-":
                return ArifmeticalCalculator.deduct(node.getArg1(),node.getArg2());
            case "/":
                return ArifmeticalCalculator.divide(node.getArg1(),node.getArg2());
            case "*":
                return ArifmeticalCalculator.multiply(node.getArg1(),node.getArg2());
            default:
                return node.getArg1();
        }
    }

}
