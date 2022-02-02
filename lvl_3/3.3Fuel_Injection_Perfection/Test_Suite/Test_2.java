package Test_Suite;

import java.math.BigInteger;

public class Test_2 {
// need to do it as string it will be longer than int
    static bin_Rep binlist;
    static BigInteger value;
    static BigInteger bigInt2;
    static int shortest(String x){
        binlist = new bin_Rep();
        value = new BigInteger(x);
        bigInt2 = new BigInteger("2");
        int count = 0;
        convert(value);

        while(!binlist.isNextEmpty() && !binlist.isNextNextEmpty()){
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
        if(!binlist.isNextEmpty() && binlist.peek2() && binlist.peek()){
            binlist.pop();
            count += 2;
        }
        // 1 0
        
        if(!binlist.isNextEmpty() && binlist.peek2() && !binlist.peek()){
            count++;
        }
        // 1
        return count; 
    }

    static void convert(BigInteger val){
        BigInteger temp;
        BigInteger remainder;
        BigInteger quotient;
        temp = val;
        String tempForReverse = "";
        while(temp.toString() != "0"){
            remainder =  temp.mod(bigInt2);
            quotient = temp.divide(bigInt2);
            temp = quotient;
            tempForReverse += remainder;
        }

        for (int i = tempForReverse.length() - 1; i >= 0 ; i--) {
            if(tempForReverse.charAt(i) == '1'){
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
            return head.next == null;
        }
        public boolean isNextNextEmpty(){
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
        shortest("1");
    }
}
