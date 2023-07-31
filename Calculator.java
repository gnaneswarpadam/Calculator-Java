
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Calculator implements ActionListener{

    JFrame frame;
    JButton digit1,digit2,digit3,digit4,digit5,digit6,digit7,digit8,digit9,digit0;
    JButton opAdd,opSub,opMul,opDiv;
    JButton bEqual;
    JButton bDot,bC;
    JLabel display;
    String s = "";

    Calculator(){

        frame = new JFrame("Calculator");
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 200, 400, 500);
        panel.setBackground(Color.GRAY);
        Font font = new Font("Calibri",Font.BOLD,40);

        display = new JLabel("Start Calculating");
        display.setBounds(0, 0, 400, 200);
        display.setFont(font);

        digit1 = new JButton("1");
        digit1.setBounds(100, 0, 100, 100);
        digit1.setFont(font);
        digit2 = new JButton("2");
        digit2.setBounds(200, 0, 100, 100);
        digit2.setFont(font);
        digit3 = new JButton("3");
        digit3.setBounds(300, 0, 100, 100);
        digit3.setFont(font);
        digit4 = new JButton("4");
        digit4.setBounds(100, 100, 100, 100);
        digit4.setFont(font);
        digit5 = new JButton("5");
        digit5.setBounds(200, 100, 100, 100);
        digit5.setFont(font);
        digit6 = new JButton("6");
        digit6.setBounds(300, 100, 100, 100);
        digit6.setFont(font);
        digit7 = new JButton("7");
        digit7.setBounds(100, 200, 100, 100);
        digit7.setFont(font);
        digit8 = new JButton("8");
        digit8.setBounds(200, 200, 100, 100);
        digit8.setFont(font);
        digit9 = new JButton("9");
        digit9.setBounds(300, 200, 100, 100);
        digit9.setFont(font);
        digit0 = new JButton("0");
        digit0.setBounds(200, 300, 100, 100);
        digit0.setFont(font);
        
        opAdd = new JButton("+");
        opAdd.setBounds(0, 0, 100, 100);
        opAdd.setFont(font);
        opSub = new JButton("-");
        opSub.setBounds(0, 100, 100, 100);
        opSub.setFont(font);
        opMul = new JButton("*");
        opMul.setBounds(0, 200, 100, 100);
        opMul.setFont(font);
        opDiv = new JButton("/");
        opDiv.setBounds(0, 300, 100, 100);
        opDiv.setFont(font);

        bDot = new JButton(".");
        bDot.setBounds(100, 300, 100, 100);
        bDot.setFont(font);
        bC = new JButton("C");
        bC.setBounds(300, 300, 100, 100);
        bC.setFont(font);
        bEqual = new JButton("=");
        bEqual.setBounds(0, 400, 400, 100);
        bEqual.setFont(font);

        digit1.addActionListener(this);
        digit2.addActionListener(this);
        digit3.addActionListener(this);
        digit4.addActionListener(this);
        digit5.addActionListener(this);
        digit6.addActionListener(this);
        digit7.addActionListener(this);
        digit8.addActionListener(this);
        digit9.addActionListener(this);
        digit0.addActionListener(this);
        opAdd.addActionListener(this);
        opSub.addActionListener(this);
        opMul.addActionListener(this);
        opDiv.addActionListener(this);
        bDot.addActionListener(this);
        bC.addActionListener(this);
        bEqual.addActionListener(this);
        

        panel.add(digit1);
        panel.add(digit2);
        panel.add(digit3);
        panel.add(digit4);
        panel.add(digit5);
        panel.add(digit6);
        panel.add(digit7);
        panel.add(digit8);
        panel.add(digit9);
        panel.add(digit0);
        panel.add(opAdd);
        panel.add(opSub);
        panel.add(opMul);
        panel.add(opDiv);
        panel.add(bDot);
        panel.add(bC);
        panel.add(bEqual);

        frame.add(display);
        frame.add(panel);

        frame.setSize(420, 750);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e){
        Object ob = e.getSource();
        
        if(!ob.equals(bEqual) && !ob.equals(bC)){
            s = s + e.getActionCommand().toString();
            display.setText(s);
        }
        else if(ob.equals(bC)){
            s = "";
            display.setText(s);
        }
        else{
            display.setText(calculation(s));
        }
    }

    public static String calculation(String a){
        ArrayList<String> num = new ArrayList<>();
        ArrayList<Character> op = new ArrayList<>();
        boolean bd = (a.contains(".")) ? true : false ;
        Float result = 0f;
        String temp = "";
        for(int i=0;i<a.length();i++){
            char ch = a.charAt(i);
            if(ch=='+' || ch=='-' || ch=='*' ||ch=='/'){
                num.add(temp);
                temp = "";
                op.add(ch);
            }
            else{
                temp += ch;
            }
        }
        num.add(temp);

        for(int i=0;i<op.size();i++){
            char ch = op.get(i);
            if(ch=='*'){
                Float p = Float.parseFloat(num.remove(i)) * Float.parseFloat(num.remove(i));
                num.add(i, p.toString());
                op.remove(i);
                i--;
            }
            else if(ch=='/'){
                Float p = Float.parseFloat(num.remove(i)) / Float.parseFloat(num.remove(i));
                num.add(i, p.toString());
                op.remove(i);
                i--;
            }
        }

        result = Float.parseFloat(num.get(0));
        for(int i=0;i<op.size();i++){
            char ch = op.get(i);
            if(ch=='+'){
                result += Float.parseFloat(num.get(i+1));
            }
            else if(ch=='-'){
                result -= Float.parseFloat(num.get(i+1));
            }
        }
        
        if(!bd){
            String t = result.toString();
            return t.substring(0,t.length()-2);
        }
        return result.toString();
    }
    

    public static void main(String args[]){
        new Calculator();
    }
}
