package Build_Maze;


public class Queue {
    private static class Node {
        Cell cell;
        private Node next;
        private Node (Cell cell){
            this.cell = cell;
        }
    }
    private Node head;
    private Node tail;

    public boolean isEmpty() {
        return head == null;
    }
    public Cell peek(){
        return head.cell;
    }
    public void add(Cell cell){
        Node node = new Node(cell);
        if(tail != null){
            tail.next = node;
        }
        tail = node;
        if(head == null){
            head = node;
        }
    }
    public Cell remove(){
        Cell cell = head.cell;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return cell;
    }
}
