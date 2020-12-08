package iteration;

import java.util.Iterator;

/**
 * 带有迭代器的双向链表实现
 */
public class IterationDoubleWayMain {
    public static void main(String[] args) {
        IterationDoubleWay<Integer> way=new IterationDoubleWay<>();
        way.addHead(2);
        way.addHead(1);
        way.addTail(3);
        Iterator<Integer> iterator = way.getIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

/**
 * 带有迭代器的双向链表
 */
class IterationDoubleWay<T> implements IterationDoubleWayInterface<T>{
    private int size;
    private Node head;
    private Node tail;

    @Override
    public Iterator<T> getIterator() {
        return new IterationList();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void addHead(T data) {
        Node newNode=new Node(data);
        if(size==0){
            head=newNode;
            tail=newNode;
        }else{
            newNode.next=head;
            head.prev=newNode;
            head=newNode;
        }
        size++;
    }

    @Override
    public void addTail(T data) {
        Node newNode=new Node(data);
        if(size==0){
            head=newNode;
            tail=newNode;
        }else{
            tail.next=newNode;
            newNode.prev=tail;
            tail=newNode;
        }
        size++;
    }

    private class Node{
        private T data;
        private Node next;
        private Node prev;

        public Node(T data){
            this.data=data;
        }
    }

    private class IterationList implements Iterator<T>{

        private Node nextNode;

        public IterationList(){
            nextNode=head;
        }

        @Override
        public boolean hasNext() {
            return nextNode!=null;
        }

        @Override
        public T next() {
            if(hasNext()){
                T data=nextNode.data;
                nextNode=nextNode.next;
                return data;
            }else
            return null;
        }
    }
}
