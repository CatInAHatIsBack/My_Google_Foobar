package Test_Suite;

import java.util.Map;
import java.util.HashMap;


public class Binary_Test {

    static Stack stack = new Stack();

    public static void deci_To_Binary_Test(int x){
        convert(x);
        while(x > 0){
            
        }
    }

    public static void convert(int x){
        
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
        // print stack 
        while(!stack.isEmpty()){
            System.out.print(stack.peek());
            stack.pop();
        }
        System.out.println();
       
        while(true){

        }
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
    // ==============================//
    // ==============================//
    static class binaryBuilder{
        Map<Integer, Boolean> binary_Map = new HashMap<Integer, Boolean>();        
        binaryBuilder() {
        }
    }
    static class binaryTree{
        binaryTree(){
        }
    }
    public static void main(String[] args) {
        int x = 200;
        deci_To_Binary_Test(x);

    }
    
}
