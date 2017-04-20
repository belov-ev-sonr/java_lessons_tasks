package threads;


import log.LoggerOperation;

import java.io.File;
import java.io.IOException;

public class ThreadNewLogFile extends Thread {
    @Override
    public void run() {
        try {
            LoggerOperation.file = new File(LoggerOperation.catalog+LoggerOperation.countLogFile+".txt");
            if(!LoggerOperation.file.exists())
                LoggerOperation.file.createNewFile();
            LoggerOperation.logBuffer=new StringBuilder();
            LoggerOperation.countLogFile=0;
            LoggerOperation.countLogFile++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
