package Test_Visuals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.BasicStroke;


public class Game_Canvas extends JPanel{
    int height;
    int width;
    
    int resolution = 40;
    static Stack stack;
    static String rev;
    Game_Canvas(int width, int height){
        this.height = height;
        this.width = width + 400;

        stack = new Stack();
        
        this.setPreferredSize(new Dimension(this.width, height));
        this.setBackground(Color.orange); 
        convertToBinary(6);
        rev = convertStackString(stack);

        init();
        
    }
    public static void init() {

    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g.create();
        g2.translate(width-150,400);
        for (int i = 0; i < rev.length(); i++) {
            drawTwosPlace(g2,i+"", rev.charAt(rev.length()-i-1) +""); 
            g2.translate(-100,0);
        }
        
    }
    void drawTwosPlace(Graphics2D g2, String pow, String bin){
        g2.setStroke(new BasicStroke(10));
        g2.drawRect(0,0,100,100);
        g2.setColor(Color.blue);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        g2.drawString( pow, 55, 40);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 60)); 
        g2.drawString("2",30,80);
        g2.drawString(bin,30,-20);
        g2.setColor(Color.black);
    }
    public static void convertToBinary(int x){
        
        int temp;
        int remainder;
        int quotient;
        temp = x;
        // String trying = "";
        while(temp > 0){
            remainder = temp % 2;
            quotient = temp / 2;
            temp = quotient;
            stack.push(remainder);
        }
                
    }
    // convert from stack to string
    static String convertStackString(Stack stack){
        String reversedString = "";
        Stack temp = new Stack();
        while(!stack.isEmpty()){
            System.out.print(stack.peek());
            reversedString += stack.peek();
            temp.push(stack.peek());
            stack.pop();
        }
        System.out.println(" : first");
        while(!temp.isEmpty()){
            System.out.print(temp.peek());
            stack.push(temp.peek());
            temp.pop();
        }
        System.out.println(" : second");
        System.out.println();
        

        return reversedString;
    }





    // ==============================//
    // ==============================//
    // (lifo)
    static class Stack{ 
        class Node{
            Integer data;
            Node next;
            public Node(){
            }
        }
        Integer data;
        Node top;
        Stack(){
            this.top = null;
        }
        // take off top[]
        public void push(Integer data){
            Node temp = new Node();
            temp.data = data;
            temp.next = top;
            top = temp;
        }  
        public void pop(){
            if (top == null){
                System.out.println(" no top ");
                return;
            }
            top = top.next;
        }
        public boolean isEmpty(){
            return top == null;
        }
        public int peek(){
            if(!isEmpty()){
                return top.data;
            }
            else {
                System.out.println(" Is Empty ");
                return -1;
            }
        }
    } 
}
