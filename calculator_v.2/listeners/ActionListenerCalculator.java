package listeners;

import log.LoggerOperation;
import objects.ArifmeticalCalculator;
import objects.StoreOperationNode;
import windowsForms.TargetWindowForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

public class ActionListenerCalculator implements ActionListener {

    public LinkedList<StoreOperationNode> stackOperation1 = new LinkedList<>();
    public LinkedList<StoreOperationNode> stackOperation2 = new LinkedList<>();


    private static String operation = new String();
    private ListIterator<StoreOperationNode> iter;
    private String temp = "";
    private String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};

    private StoreOperationNode node;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("C")) {
            operation = "";
            temp = "";
            node=null;
            TargetWindowForm.label.setText("0");
            TargetWindowForm.label2.setText("");
            return;
        }

        if (button.getText().equals("Del")) {
            temp = "";
            TargetWindowForm.label.setText("0");
            return;
        }
        if (button.getText().equals("+-")) {
            if(temp.length()==0)
                return;
            else{
                if(temp.charAt(0)=='-')
                    temp=temp.substring(1);
                else
                    temp="-"+temp;
                TargetWindowForm.label.setText(temp);
                return;
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            if (button.getText().equals(numbers[i])) {
                if (node!=null && temp.length()==0) {
                    temp = button.getText();
                } else
                    temp += button.getText();
                TargetWindowForm.label.setText(temp);
                return;
            }
        }
        if(node==null) {
            if(temp.length()==0)
                node= new StoreOperationNode(stackOperation1.getLast().getResult());
            else
                node = new StoreOperationNode(Double.parseDouble(temp));
            TargetWindowForm.label2.setText(TargetWindowForm.label2.getText()+String.valueOf(node.getArg1()));
            temp="";
        }
        if(node.getOperation()==null){
            node.setOperation(button.getText());
            TargetWindowForm.label2.setText(TargetWindowForm.label2.getText()+node.getOperation());
            return;
        }
        if(node.getArg2()==null){
            node.setArg2(Double.parseDouble(temp));
            TargetWindowForm.label2.setText(TargetWindowForm.label2.getText()+String.valueOf(node.getArg2()));
            temp="";
        }
        //поиск
        if(stackOperation1.size()>0){
            //обход первого кэша
            for (StoreOperationNode tempNode:stackOperation1) {
                if(tempNode.equals(node)){
                    node.setResult(tempNode.getResult());
                    stackOperation1.remove(tempNode);
                    break;
                }
            }
            //обход второго кэша
            if(node.getResult()==null)
                for (StoreOperationNode tempNode:stackOperation2) {
                    if(tempNode.equals(node)){
                        node.setResult(tempNode.getResult());
                        stackOperation2.remove(tempNode);
                        break;
                    }
                }
            }
        //вычисление результата, если в результате поиска ничего не найдено
        if(node.getResult()==null) {
            node.setResult(seachOperation(node));
        }

        if(stackOperation1.size()!=10)
            stackOperation1.add(node);
        else {
            stackOperation2.add(stackOperation1.getFirst());
            stackOperation1.removeFirst();
            stackOperation1.add(node);

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
