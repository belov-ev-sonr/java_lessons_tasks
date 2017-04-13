

public class Stack<T> {
    private class Node<T>{
        T value=null;
        Node<T> previous=null;
        Node<T> next=null;

        Node(T value, Node<T> previous, Node<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
    private int sizeDefault=10;
    private int size=0;


    private Node<T>[] stack = new Node[sizeDefault];


    public void push(T value){
        Node<T> node=new Node<T>(value,null,null);
        if(size==0) {
            stack[size] = node;
            size++;
            return;
        }
        if(size==sizeDefault){
            node.previous=stack[stack.length-1];
            stack[stack.length-1].next=node;
            for (int i = 0; i <sizeDefault-1 ; i++) {
                stack[i]=stack[i+1];
                if(i==0)
                    stack[i].previous=null;
            }
            stack[size-1]=node;
        }else {
            node.previous=stack[size-1];
            stack[size-1].next=node;
            stack[size] = node;
            size++;
        }
    }

    public boolean pop(){
        if(stack.length==0) {
            return false;
        }else{
            stack[size-1]=null;
            size--;
            stack[size-1].next=null;
            return true;
        }

    }

    public int getSize() {
        return size;
    }


}
