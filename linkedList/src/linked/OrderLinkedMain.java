package linked;

/**
 * 有序链表实例
 */
public class OrderLinkedMain {
    public static void main(String[] args) {
        OrderLinked orderLinked=new OrderLinked();
        orderLinked.add(5);
        orderLinked.forEach();
        orderLinked.add(2);
        orderLinked.forEach();
        orderLinked.add(15);
        orderLinked.forEach();
        orderLinked.deleteHead();
        orderLinked.add(1);
        orderLinked.forEach();
    }
}

/**
 * 有序链表实现
 */
class OrderLinked {
    int size;
    Node head;

    private class Node {
        //具体数据
        int data;
        //指向下个节点的指针
        Node next;

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
