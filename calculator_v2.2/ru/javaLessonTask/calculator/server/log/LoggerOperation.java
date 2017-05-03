package ru.javaLessonTask.calculator.server.log;

import ru.javaLessonTask.calculator.StoreOperationNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LoggerOperation {
    public static String catalog="src/ru/javaLessonTask/calculator/server/log/log_";
    public static File file = new File(catalog+"0.txt");
    public static StringBuilder logBuffer = new StringBuilder();
    public static int countLogFile=1;
    public static int countRecords=0;


    public static void clearLogs(){
        for (int i=0; i<20; i++) {
            file = new File(catalog +i+".txt");
            file.delete();
        }
        file = new File(catalog +0+".txt");
    }

    public synchronized static void loggingRecord(StoreOperationNode node){
        logBuffer.append(node.getUser()+"    "+new Date(System.currentTimeMillis())+"   "+
                node.getArg1()+node.getOperation()+node.getArg2()+"="+node.getResult());
        logBuffer.append("\n");
        try {
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            PrintWriter writer = new PrintWriter(file.getAbsoluteFile());
            try {
                writer.println(logBuffer);
                countRecords++;
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
