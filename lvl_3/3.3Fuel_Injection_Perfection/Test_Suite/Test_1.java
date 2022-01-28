package Test_Suite;


public class Test_1 {

    static bin_Rep binlist;
    
    static int shortest(int x){
        
        binlist = new bin_Rep();
        int count = 0; 
        convert(x);
        while(!binlist.isNextEmpty()){
            // 1 1
            if(binlist.peek2() && binlist.peek()){
                binlist.plus();
                count++;
                continue;
            }
            // 1 0
            if(binlist.peek2() && !binlist.peek()){
                binlist.pop();
                count++;
                continue;
            }
            // 0 1
            if(!binlist.peek2() && binlist.peek()){
                binlist.pop();
                count += 2;
                continue;
            }
            // 0 0
            if(!binlist.peek2() && !binlist.peek()){
                binlist.pop();
                count++;
                continue;
            }
        }
        // 1 1        
        if(binlist.peek2() && binlist.peek()){
            count += 2;
        }
        // 1 0
        if(binlist.peek2() && !binlist.peek()){
            count++;
        }
        // 1
        if(binlist.peek()){
            return count;
        }
        System.out.println(count);
        return count; 
    }

    static void convert(int x){
        int temp;
        int remainder;
        int quotient;
        temp = x;
        String tempForRev= "";
        while(temp > 0){
            remainder = temp % 2;
            quotient = temp / 2;
            temp = quotient;
            tempForRev += remainder;
            
        }
        for (int i = tempForRev.length() - 1; i >= 0 ; i--) {
            if(tempForRev.charAt(i) == '1'){
               binlist.push(true);
            }
            else{
               binlist.push(false);
            }      
        }
    }
    static class bin_Rep{
        class Node{
            // 0 is false 1 is true;
            boolean isOne = false;
            Node next;
        }
        Node head;
        bin_Rep(){
            this.head = null;
        }
        public void push(boolean in){
            Node temp = new Node();
            temp.isOne = in;
            temp.next = head;
            head = temp;
        }
        // no need to check if empty as i will have base cases for 1,10 an 11
        public void pop(){
            head = head.next;
        }
        public boolean isEmpty(){
            return head == null;
        }
        public boolean isNextEmpty(){
            return head.next.next == null;
        }
        public boolean peek(){
            return head.isOne;
        }
        public boolean peek2(){
            return head.next.isOne;
        }
        public void plus(){
            int count = 0;
            while(!this.isEmpty() && head.isOne != false ){
                this.pop();               
                count++;
            }
            if(this.isEmpty()){
                this.push(true); 
            }
            else{
                head.isOne = true;
            }
            for (int i = 0; i < count; i++) {
                this.push(false);
            }
        }    

    }
    
    public static void main(String[] args) {
        shortest(15);
    }
}
