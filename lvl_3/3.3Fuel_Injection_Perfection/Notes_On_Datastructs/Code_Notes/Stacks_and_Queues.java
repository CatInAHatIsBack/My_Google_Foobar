package Notes_On_Datastructs.Code_Notes;

public class Stacks_and_Queues {
    
    static void data_Structs(){
        Stack stack = new Stack();
        stack.push(1);
        System.out.println(stack.peek());
        stack.push(2);
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());

    }
    static class bin_Node{
        Integer data;
        // Pointer left  | Less than
        bin_Node left;
        // Pointer right | Greater than 
        bin_Node right;

        public bin_Node(Integer data){
            this.data = data;
            left = null;
            right = null; 
        }
    }

    // (lifo)
    static class Stack{ 
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
    // (fifo) adds back, takes from front
    static class Queue {
        Integer data; 
        private Node head;
        private Node tail;
        public boolean isEmpty() {
            return head == null;
        }
        public Integer peek(){
            return head.data;
        }
        public void add(Integer data){
            Node node = new Node(data);
            if(tail != null){
                tail.next = node;
            }
            tail = node;
            if(head == null){
                head = node;
            }
        }
        public void remove(){
            head = head.next;
            if(head == null){
                tail = null;
            }

        }
    } 
    static class Node {
            Integer data;
            private Node next;

            public Node (){

            }
            public Node (Integer data){
                this.data = data;
            }
        }
    
    public static void main(String[] args) {
        data_Structs();  
    }
}
