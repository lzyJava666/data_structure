package linked;

/**
 * 双向链表实例
 */
public class DoubleWayLinkedMain {
    public static void main(String[] args) {
        DoubleWayLinked linked=new DoubleWayLinked();
        linked.addHead("B");
        linked.addHead("A");
        linked.forEach();
        linked.addTail("C");
        linked.addTail("d");
        linked.forEach();
        System.out.println(linked.removeHead());
        System.out.println(linked.removeTail());
        linked.forEach();
    }
}

/**
 * 双向链表实现
 */
class DoubleWayLinked{
    private class Node{
        //具体数据
        private Object data;
        //指向下个节点
        private Node next;
        //指向上个节点
        private Node prev;
        public Node(Object data) {
            this.data = data;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    ", prev=" + prev +
                    '}';
        }
    }
    //总数据数
    private int size;
    //头节点
    private Node head;
    //尾节点
    private Node tail;

    public DoubleWayLinked() {
        size=0;
        head=null;
        tail=null;
    }

    //判断是否为空
    public boolean isEmpty(){
        return size==0;
    }

    //新增头节点
    public void addHead(Object data){
        Node newNode=new Node(data);
        if(isEmpty()){
            head=newNode;
            tail=newNode;
        }else{
            newNode.next=head;
            head.prev=newNode;
            head=newNode;
        }
        size++;
    }

    //新增尾节点
    public void addTail(Object data){
        Node newNode=new Node(data);
        if(isEmpty()){
            head=newNode;
            tail=newNode;
        }else{
            tail.next=newNode;
            newNode.prev=tail;
            tail=newNode;
        }
        size++;
    }

    //删除头节点
    public Object removeHead(){
        Object res=null;
        if(isEmpty()){
            return res;
        }
        res=head.data;
        if(size==1){
            head=null;
            tail=null;
        }else{

            head=head.next;
            head.prev=null;
        }
        size--;
        return res;
    }

    //删除尾节点
    public Object removeTail(){
        Object res=null;
        if(isEmpty()){
            return res;
        }
        res=tail.data;
        if(size==1){
            head=null;
            tail=null;
        }else{
            tail=tail.prev;
            tail.next=null;
        }
        size--;
        return res;
    }

    //遍历
    public void forEach() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        int len = size;
        Node current = head;
        while (len > 0) {
            if (current == head) {
                //第一个元素
                System.out.print("[" + current.data + "->");
            } else if (current.next == null) {
                //最后一个元素
                System.out.print(current.data + "]");
            } else {
                System.out.print(current.data + "->");
            }
            len--;
            current = current.next;
        }
        System.out.println();
    }
}
