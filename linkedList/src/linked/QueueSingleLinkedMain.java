package linked;

import java.util.LinkedList;

/**
 * 基于链表实现队列实例
 */
public class QueueSingleLinkedMain {
    public static void main(String[] args) {
        QueueSingleLinked queue=new QueueSingleLinked();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.forEach();
        System.out.println(queue.delete());
        queue.add(5);
        queue.forEach();
    }
}

class QueueSingleLinked{

    MySingleLinked linked;

    public QueueSingleLinked(){
        linked=new MySingleLinked();
    }

    public Object add(Object data){
        return linked.addHead(data);
    }

    public Object delete(){
        return linked.deleteHead();
    }

    public boolean isEmpty(){
        return linked.isEmpty();
    }

    public void forEach(){
        linked.forEach();
    }


}
