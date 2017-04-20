package listeners;

import log.LoggerOperation;
import threads.RunnableClearCache;
import threads.ThreadNewLogFile;
import objects.ArifmeticalCalculator;
import objects.DoubleCache;
import objects.StoreOperationNode;
import windowsForms.TargetWindowForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

public class ActionListenerCalculator implements ActionListener {

    private DoubleCache cache = new DoubleCache();
    private static String operation = new String();
    private String temp = "";
    private String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
    private Thread arh;
    private Thread cacheClearer;

    private StoreOperationNode node;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        //pressed button "C"
        if (button.getText().equals("C")) {
            operation = "";
            temp = "";
            node = null;
            TargetWindowForm.label.setText("0");
            TargetWindowForm.label2.setText("");
            return;
        }
        //pressed button "Del"
        if (button.getText().equals("Del")) {
            temp = "";
            TargetWindowForm.label.setText("0");
            return;
        }
        //pressed button "+-"
        if (button.getText().equals("+-")) {
            if (temp.length() == 0)
                return;
            else {
                if (temp.charAt(0) == '-')
                    temp = temp.substring(1);
                else
                    temp = "-" + temp;
                TargetWindowForm.label.setText(temp);
                return;
            }
        }
        //pressed buttons 0-9,.
        for (int i = 0; i < numbers.length; i++) {

            if (button.getText().equals(numbers[i])) {
                if (node != null && temp.length() == 0) {
                    temp = button.getText();
                } else
                    temp += button.getText();
                TargetWindowForm.label.setText(temp);
                return;
            }
        }
        //adding the first argument
        if (node == null) {
            if (temp.length() == 0)
                try {
                    node = new StoreOperationNode(cache.getFirst().getResult());
                } catch (NoSuchElementException e1) {
                    return;
                }
            else
                try {
                    node = new StoreOperationNode(Double.parseDouble(temp));
                } catch (NumberFormatException e1) {
                    temp = "";
                    TargetWindowForm.label.setText("0");
                    return;

                }
            TargetWindowForm.label2.setText(TargetWindowForm.label2.getText() + String.valueOf(node.getArg1()));
            temp = "";
        }
        //pressing the operation key again
        if ((button.getText().equals("*") || button.getText().equals("/") || button.getText().equals("-") || button.getText().equals("+"))
                && node.getOperation() != null) {
            node.setOperation(button.getText());
            TargetWindowForm.label2.setText(TargetWindowForm.label2.getText().substring(0, TargetWindowForm.label2.getText().length() - 1) + node.getOperation());
        }
        //adding the operator
        if (node.getOperation() == null) {
            node.setOperation(button.getText());
            TargetWindowForm.label2.setText(TargetWindowForm.label2.getText() + node.getOperation());
            return;
        }
        //adding the second argument
        if (node.getArg2() == null) {
            try {
                node.setArg2(Double.parseDouble(temp));
                TargetWindowForm.label2.setText(TargetWindowForm.label2.getText() + String.valueOf(node.getArg2()));
                temp = "";
            } catch (NumberFormatException e1) {
                temp = "";
                TargetWindowForm.label.setText("0");
                return;
            }
        }


        //seaching in cache
        if (!cache.searchElement(node)) {
            //decide result, if nothing is found in cache
            if (node.getResult() == null) {
                node.setResult(seachOperation(node));
                //start cache clear thread
                if (DoubleCache.cacheOperation2.size() == 10) {
                    cacheClearer = new Thread(new RunnableClearCache());
                    cacheClearer.start();
                }
                synchronized (cache) {
                    if (cacheClearer != null) {
                        try {
                            cacheClearer.join();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        cache.push(node);
                    }
                }
            }
        }
        //write in file (logging)
        if (LoggerOperation.countRecords == 10) {
            arh = new ThreadNewLogFile();
            arh.start();
        }
        if (cacheClearer != null){
            try {
                arh.join();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        LoggerOperation.loggingRecord(node);
        TargetWindowForm.label.setText(String.valueOf(node.getResult()));
        if(!button.getText().equals("=")){
            node=new StoreOperationNode(node.getResult());
            node.setOperation(button.getText());
            TargetWindowForm.label2.setText(TargetWindowForm.label2.getText()+node.getOperation());
        }else {
            node=null;
            TargetWindowForm.label2.setText("");
        }
    }

    private double seachOperation(StoreOperationNode node) {
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
