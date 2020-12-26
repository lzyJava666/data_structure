package linked;


import java.util.HashMap;

/**
 * 基于链地址法的哈希表实例
 */
public class HashLinkedMain {
    public static void main(String[] args) {
        HashLinked hash=new HashLinked(5);
        hash.insert(5);
        hash.insert(19);
        hash.forEach();
        hash.insert(1);
        hash.insert(11);
        hash.insert(777);
        hash.insert(129);
        hash.forEach();
        hash.delete(5);
        hash.forEach();
    }
}

/**
 * 基于链地址法的哈希表实现
 */
class HashLinked{
    //具体数据集
    private OrderLinked[] orderLinkeds;

    public HashLinked(int size){
        orderLinkeds=new OrderLinked[size];
        for (int i = 0; i < size; i++) {
            orderLinkeds[i]=new OrderLinked();
        }
    }

    //遍历输出
    public void forEach(){
        for (int i = 0; i < orderLinkeds.length; i++) {
            orderLinkeds[i].forEach();
        }
    }

    //哈希函数
    public int hashFunc(int key){
        return key % orderLinkeds.length;
    }

    //插入
    public void insert(int data){
        int hashData=hashFunc(data);
        orderLinkeds[hashData].add(data);
    }

    //删除
    public void delete(int data){
        int hashData=hashFunc(data);
        orderLinkeds[hashData].delete(data);
    }

    // 查找
    public Node find(int data){
        int hashData=hashFunc(data);
        return orderLinkeds[hashData].find(data);
    }


}

/**
 * 有序链表
 */
class OrderLinked {
    int size;
    Node head;

    public OrderLinked() {
        size = 0;
        head = null;
    }

    //判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //插入操作
    public void add(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            //空链表
            head = newNode;
            size++;
        } else {
            //标记当前节点
            Node current = head;
            //标记上一个节点
            Node UpCurrent = null;
            while (data > current.data) {
                if (current.next == null) {
                    //链表中没有比data大的数据
                    current.next = newNode;
                    size++;
                    return;
                } else {
                    UpCurrent = current;
                    current = current.next;
                }
            }
            //循环结束
            if (UpCurrent == null) {
                //当前数据比头节点大
                newNode.next = head;
                head = newNode;
                size++;
            } else {
                UpCurrent.next = newNode;
                newNode.next = current;
                size++;
            }
        }
    }

    //删除头节点
    public boolean deleteHead() {
        if (isEmpty()) {
            return false;
        } else {
            head = head.next;
            size--;
            return true;
        }
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
            if(current==null){
                continue;
            }
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

    //查找
    public Node find(int data) {
        Node currentNode = head;
        while (currentNode.next != null) {
            if (data == currentNode.data) {
                return currentNode;
            } else {
                currentNode = currentNode.next;
            }
        }
        return null;
    }

    //删除
    public void delete(int data) {
        Node currentNode = head;
        Node prentNode = null;
        while (data != currentNode.data && currentNode != null) {
            prentNode = currentNode;
            currentNode = currentNode.next;
        }
        if(currentNode!=null&&prentNode==null){
            head=head.next;
        }else{
            prentNode.next=currentNode.next;
        }
    }
}

/**
 * 有序链表节点类
 */
class Node {
    //具体数据
    public int data;
    //指向下个节点的指针
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
