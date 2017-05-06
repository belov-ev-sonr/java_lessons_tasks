package ru.javaLessonTask.calculator.server.fileWorker;


import java.io.*;
import java.util.ArrayList;

public class writeAndReadFile {

    public static boolean seachingInFile(String data, File file){
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                String s;
                while((s=bufferedReader.readLine())!=null){
                    if(s.equals(data)){
                        return true;
                    }
                }
                return false;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }
    }



    public static boolean writeFile(String data, File file){
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            //read file
            ArrayList<String> sb = new ArrayList<String>();
            try {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                    String s;
                    while((s=bufferedReader.readLine())!=null){
                        sb.add(s);
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                return false;
            }
            for (int i = 0; i <sb.size() ; i++) {
                if(sb.get(i).equals(data)) {
                    System.out.println("Запись существует");
                    return true;
                }
            }
            //write new user
            PrintWriter writer = new PrintWriter(file.getAbsoluteFile());

            try {
                if(sb.size()!=0){
                    for (int i = 0; i <sb.size() ; i++) {
                        writer.println(sb.get(i));
                    }
                }
                writer.println(data);
            } finally {
                writer.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }
        return true;
    }

}
