package queue;

import java.util.Deque;

/**
 * 双端队列实例
 */
public class DoubleEndQueueMain {
    public static void main(String[] args) {

        DoubleEndQueue doubleEndQueue=new DoubleEndQueue();
        doubleEndQueue.addFirst(21);
        doubleEndQueue.addFirst(22);
        doubleEndQueue.addFirst(23);
        doubleEndQueue.addFirst(24);
        doubleEndQueue.addFirst(25);
        doubleEndQueue.addFirst(26);
        doubleEndQueue.addLast(11);
        doubleEndQueue.addLast(12);
        doubleEndQueue.addLast(13);
        doubleEndQueue.addLast(14);
        doubleEndQueue.addLast(15);
        doubleEndQueue.addLast(16);
        doubleEndQueue.addLast(17);
        doubleEndQueue.addLast(18);
        doubleEndQueue.addLast(19);
        doubleEndQueue.addLast(110);
        doubleEndQueue.addLast(111);
        System.out.println(doubleEndQueue.getLast());
        System.out.println(doubleEndQueue.getFirst());
        while (!doubleEndQueue.isEmpty()){
            System.out.println(doubleEndQueue.getFirst());
            doubleEndQueue.removeFirst();
        }
    }

}

/**
 * 自定义双端队列
 */
class DoubleEndQueue {
    //存放数据的底层数组
    Object[] array;
    //数组总长度
    int length;
    //头指针
    int head;
    //尾指针
    int tail;

    public DoubleEndQueue() {
        array = new Object[16];
        length = 16;
        head = 0;
        tail = 0;
    }

    //判断是否为空队列
    public boolean isEmpty() {
        return (head == tail);
    }

    //取模运算
    public int getMod(int index) {
        //相当于(index) & (length - 1)
        if (index < 0) {
            return index + length;
        }
        if (index >= length) {
            return index - length;
        }
        return index;
    }

    //头部入队
    public void addFirst(Object o) {
        //getMod  相当于 (head - 1) & (length- 1)
        array[head = getMod(head - 1)] = o;
        //head=getMod(head - 1);
        //array[head]=o;
        if (head == tail) {
            //扩容
            doubleCapacity();
        }
    }

    //尾部入队
    public void addLast(Object o) {
        array[tail] = o;
        tail = getMod(tail + 1);
        if (head == tail) {
            //扩容
            doubleCapacity();
        }
    }

    //头部取数据
    public Object getFirst(){
        if(isEmpty()){
            System.out.println("当前取不到任何数据");
            return null;
        }else{
            return array[head];
        }
    }

    //尾部取数据
    public Object getLast(){
        if(isEmpty()){
            System.out.println("当前取不到任何数据");
            return null;
        }else{
            return array[getMod(tail-1)];
        }
    }

    //头部删数据
    public void removeFirst(){
        if(isEmpty()){
            System.out.println("当前队列为空");
        }else{
            array[head]=null;
            head=getMod(head+1);
        }
    }

    //尾部删数据
    public void removeLast(){
        if(isEmpty()){
            System.out.println("当前队列为空");
        }else{
            tail=getMod(tail-1);
            array[tail]=null;
        }
    }

    //扩容
    private void doubleCapacity() {
        //数组长度翻倍
        length = length << 1;
        Object[] newArray = new Object[length];
        /**
         *    Object src : 原数组
         *    int srcPos : 从元数据的起始位置开始
         * 　　Object dest : 目标数组
         * 　　int destPos : 目标数组的开始起始位置
         * 　　int length  : 要copy的数组的长度
         */
        System.arraycopy(array, head, newArray, 0, array.length - head);
        System.arraycopy(array, 0, newArray, array.length - head, head);
        head = 0;
        tail = array.length;
        array = newArray;
    }

}
