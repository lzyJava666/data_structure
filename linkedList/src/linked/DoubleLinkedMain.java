package linked;

/**
 * 双端链表实例
 */
public class DoubleLinkedMain {
    public static void main(String[] args) {
        DoubleLinked linked=new DoubleLinked();
        linked.addHead(2);
        linked.addHead(1);
        linked.addTail(3);
        linked.addTail(4);
        linked.forEach();
        linked.delete(3);
        linked.removeHead();
        linked.forEach();
    }
}

/**
 * 双端链表类实现
 */
class DoubleLinked{
    //数据个数
    int size;
    //头节点
    Node head;
    //尾节点
    Node tail;

    public DoubleLinked(){
        size=0;
        head=null;
        tail=null;
    }

    //判断是否为空链表
    public boolean isEmpty(){
        return size==0;
    }

    //添加头节点
    public Object addHead(Object data){
        Node newNode=new Node(data);
        if(isEmpty()){
            //空链表的时候，头节点=尾节点
            head=newNode;
            tail=newNode;
            size++;
            return newNode;
        }else{
            newNode.next=head;
            head=newNode;
            size++;
            return newNode;
        }
    }

    //尾部添加新节点
    public Object addTail(Object data){
        Node newNode=new Node(data);
        if(isEmpty()){
            //空链表的时候，头节点=尾节点
            head=newNode;
            tail=newNode;
            size++;
            return newNode;
        }else{
            tail.next=newNode;
            tail=newNode;
            size++;
            return newNode;
        }
    }

    //头部删除节点
    public Object removeHead(){
        Object res=null;
        if(isEmpty()){
            return res;
        }else if(size==1){
            //只有一个节点
            res=head.data;
            head=null;
            tail=null;
            size--;
            return res;
        }else{
            res=head.data;
            head=head.next;
            size--;
            return res;
        }
    }

    //遍历循环输出链表
    public void forEach(){
        if(isEmpty()){
            System.out.println("[]");
            return;
        }
        int len=size;
        Node current=head;
        while (len>0){
            if(current==head){
                //第一个元素
                System.out.print("["+current.data+"->");
            }else if(current.next==null){
                //最后一个元素
                System.out.print(current.data+"]");
            }else{
                System.out.print(current.data+"->");
            }
            len--;
            current=current.next;
        }
        System.out.println();
    }

    //删除指定节点
    public boolean delete(Object data){
        if(isEmpty()){
            return false;
        }
        Node current=head;
        Node present=head;
        while (!data.equals(current.data)){
            if(current.next==null){
                return false;
            }else{
                present=current;
                current=current.next;
            }
        }
        if(size==1){
            tail=null;
            head=null;
            size--;
            return true;
        }else if(current==head){
            head=head.next;
            size--;
            return true;
        }else if(current==tail){
            present.next=null;
            tail=present;
            size--;
            return true;
        }else{
            present.next=current.next;
            size--;
            return true;
        }

    }



    /**
     * 节点对象
     */
    private class Node{
        Object data;
        Node next;
        public Node(Object data){
            this.data=data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }
}