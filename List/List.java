

public class List<T> {
    private Node<T> last=null;
    private Node<T> first=null;

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



    public void push(T value){
        Node<T> node=new Node<T>(value,null,null);
        if(size==0) {
            last=node;
            first=node;
            size++;
            return;
        }
        last.next=node;
        node.previous=last;
        last=node;
        if(size==sizeDefault){
            first=first.next;
        }else {
            size++;
        }
    }

    public boolean pop(){
        if(size==0) {
            return false;
        }else{
            last=last.previous;
            last.next=null;
            size--;
            return true;
        }

    }

    public int getSize() {
        return size;
    }


}
