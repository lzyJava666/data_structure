package linked;

/**
 * 单向链表实例
 */
public class SingleLinkedMain {
    public static void main(String[] args) {
        MySingleLinked linked=new MySingleLinked();
        linked.addHead("A");
        System.out.println(linked.find("A"));
        linked.addHead("B");
        linked.addHead("C");
        linked.addHead("D");
        linked.forEach();
        linked.delete("C");
        linked.forEach();
        
    }
}

/**
 * 单向链表的实现
 */
class MySingleLinked{
    //当前链表数据个数
    int size;
    //存放头节点
    Node head;

    public MySingleLinked(){
        size=0;
        head=null;
    }

    private class Node{
        //具体数据
        Object data;
        //指向下个节点的指针
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

    //增加头节点
    public Object addHead(Object data){
        Node newNode=new Node(data);
        if(size==0){
            //空链表
            head=newNode;
            size++;
        }else{
            //将指针指向原链表头部，然后赋值给链表头部
            newNode.next=head;
            head=newNode;
            size++;
        }
        return newNode;
    }

    //判断链表是否为空
    public boolean isEmpty(){
        return size==0;
    }

    //删除头节点
    public Object deleteHead(){
        if(isEmpty()){
            return null;
        }else{
            //删除时，头节点指向下个节点
            Object data = head.data;
            head=head.next;
            size--;
            return data;
        }
    }

    //查找元素
    public Node find(Object data){
        if(isEmpty()){
            return null;
        }else{
            // 当前节点对象
            Node current=head;
            while (!data.equals(current.data)){
                if(current.next==null){
                    //最后一个元素了
                    return null;
                }
                current=current.next;
            }
            //指向到这里说明已经找到元素了
            return current;
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

    //  删除指定元素
    public boolean delete(Object data){
        if(isEmpty()){
            return false;
        }
        //标注当前节点对象
        Node current=head;
        //标注上一个节点对象
        Node upCurrent=head;
        while (!data.equals(current.data)){
            if(current==null){
                //最后一个元素了
                return false;
            }
            upCurrent=current;
            current=current.next;
        }
        //执行到这里说明current为指定删除的节点对象
        //执行删除操作，删除节点对象上个节点对象执行需删除节点对象的下一个对象（指向对象）
        upCurrent.next=current.next;
        size--;
        return true;
    }
}
