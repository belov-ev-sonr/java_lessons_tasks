package log;

import listeners.ActionListenerRegistration;
import objects.StoreOperationNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LoggerOperation {
    private static File file = new File("src/log/log.txt");
    private static StringBuilder logBuffer = new StringBuilder();

    public static void loggingRecord(StoreOperationNode node){
        logBuffer.append(ActionListenerRegistration.user+"    "+new Date(System.currentTimeMillis())+"   "+
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
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
