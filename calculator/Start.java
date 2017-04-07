import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame implements ActionListener {
    private static JFrame window;
    public JLabel label;
    public JLabel label2;

    private int counterOperand=0;
    private boolean result = false;
    private float[] arg = new float[2];
    private float temp_arg=0;

    private static String operation= new String();
    private String temp="";
    private String[] numbers = {"0","1","2","3","4","5","6","7","8","9","."};
    private Start(){
        window = new JFrame("Калькулятор");
        JPanel panel_target = new JPanel(new BorderLayout());
        JPanel panel_arifmetic = new JPanel(new GridLayout(5,4));
        JPanel panel = new JPanel(new BorderLayout());
        label = new JLabel("0");
        label2 = new JLabel("");
        label.setFont(new Font("Verdana",Font.BOLD,24));
        label2.setFont(new Font("Verdana",Font.BOLD,12));
        panel.add(label2,BorderLayout.NORTH);
        panel.add(label,BorderLayout.SOUTH);
        JButton button = new JButton("CE");
        panel_arifmetic.add(button);
        button = new JButton("C");
        panel_arifmetic.add(button);
        button = new JButton("/");
        panel_arifmetic.add(button);
        button = new JButton("*");
        panel_arifmetic.add(button);
        button = new JButton("7");
        panel_arifmetic.add(button);
        button = new JButton("8");
        panel_arifmetic.add(button);
        button = new JButton("9");
        panel_arifmetic.add(button);
        button = new JButton("-");
        panel_arifmetic.add(button);
        button = new JButton("4");
        panel_arifmetic.add(button);
        button = new JButton("5");
        panel_arifmetic.add(button);
        button = new JButton("6");
        panel_arifmetic.add(button);
        button = new JButton("+");
        panel_arifmetic.add(button);
        button = new JButton("1");
        panel_arifmetic.add(button);
        button = new JButton("2");
        panel_arifmetic.add(button);
        button = new JButton("3");
        panel_arifmetic.add(button);
        button = new JButton("=");
        panel_arifmetic.add(button);
        button = new JButton("0");
        panel_arifmetic.add(button);
        button = new JButton(".");
        panel_arifmetic.add(button);
        addListenerInPanel(panel_arifmetic);

        panel_target.add(panel,BorderLayout.NORTH);
        panel_target.add(panel_arifmetic,BorderLayout.CENTER);
        window.add(panel_target);
    }

    private void addListenerInPanel(JPanel panel){
        for (Object node:panel.getComponents()) {
            if(node instanceof JButton)
                ((JButton) node).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("CE")) {
            result = false;
            counterOperand = 0;
            operation = "";
            temp = "";
            label.setText("0");
            label2.setText("");
            return;
        }

        if (button.getText().equals("C")) {
            temp = "";
            label.setText("0");
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (button.getText().equals(numbers[i])) {
                if (result) {
                    temp = button.getText();
                    result = false;
                } else
                    temp += button.getText();
                label.setText(temp);
                return;
            }
        }
        if (counterOperand == 2) {
            label2.setText(label2.getText() + arg[1]);
            temp_arg = arg[0];
            arg[0] = seachOperation(operation, arg);
            result = true;
            temp = String.valueOf(arg[0]);
            counterOperand = 0;
            label.setText(String.valueOf(arg[0]));

        } else {
            if (!temp.equals("")) {
                arg[counterOperand] = Float.parseFloat(temp);
            }
            counterOperand++;
            if(counterOperand==2){
                label2.setText(label2.getText() + arg[1]);
                temp_arg = arg[0];
                arg[0] = seachOperation(operation, arg);
                result = true;
                temp = String.valueOf(arg[0]);
                counterOperand = 1;
            }else {
                label.setText("");
                temp = "";
            }
        }
        operation=button.getText();
        if (button.getText().equals("=")) {
            label.setText(String.valueOf(arg[0]));
            label2.setText("");
            result = false;
            counterOperand = 0;
            operation = "";
            temp = "";
        } else
        label2.setText(arg[0] + operation);
    }


    private float seachOperation(String operat,float arg[]) {
        switch (operat) {
            case "+":
                return ArifmeticalCalculator.addUp(arg);
            case "-":
                return ArifmeticalCalculator.deduct(arg);
            case "/":
                return ArifmeticalCalculator.divide(arg);
            case "*":
                return ArifmeticalCalculator.multiply(arg);
            case "power(x,n)":
                return ArifmeticalCalculator.exponentiation(arg);
            case "+-":
                return ArifmeticalCalculator.changleSign(arg[0]);
            case "sqrt":
                return ArifmeticalCalculator.getRadical(arg[0]);

            default:
                return arg[1];
        }
    }

    public static void main(String[] args) {
        Start start = new Start();
        window.setSize(350,400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

}
