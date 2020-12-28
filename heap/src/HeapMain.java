/**
 * 堆实例
 */
public class HeapMain {
    public static void main(String[] args) {
        MyHeap heap = new MyHeap(15);
        heap.insert(5);
        heap.insert(20);
        heap.insert(22);
        heap.insert(1);
        heap.insert(9);
        heap.insert(210);
        heap.insert(522);
        heap.insert(12);
        heap.insert(4);
        heap.forEach();
        heap.change(3,220);
        heap.forEach();
        heap.delete();
        heap.forEach();
    }
}


/**
 * 堆实现类
 */
class MyHeap {
    //封装堆数据项的数组
    private Node[] heapArray;
    //当前数据数
    private int size;

    public MyHeap(int max) {
        this.size = 0;
        heapArray = new Node[max];
    }

    //判断是否为空堆
    public boolean isEmpty() {
        return size == 0;
    }

    //判断堆是否已满
    public boolean isFull() {
        return size == heapArray.length;
    }

    //插入操作
    public void insert(int data) {
        Node newNode = new Node(data);
        if (isFull()) {
            return;
        }
        //1.将数据插入到数组最后
        heapArray[size] = newNode;
        //2.向上移位，直到满足条件
        moveUp(size++);

    }

    //向上移位
    private void moveUp(int index) {
        //拿到父节点索引
        int parentIndex = (index - 1) / 2;
        //存放当前节点
        Node currentNode = heapArray[index];
        while (index > 0 && currentNode.getData() > heapArray[parentIndex].getData()) {
            heapArray[index] = heapArray[parentIndex];
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
        heapArray[index] = currentNode;
    }

    //删除根节点操作
    public void delete() {
        if (isEmpty()) {
            return;
        }
        //1.将数组最后元素覆盖根元素
        heapArray[0] = heapArray[--size];
        //2.向下移位
        moveDown(0);

    }

    /**
     * 向下移动
     *
     * @param i 从哪个坐标开始，直到叶节点
     */
    private void moveDown(int i) {
        //当前节点
        Node currentNode = heapArray[i];
        //左子节点索引
        int leftChildIndex;
        //右子节点索引
        int rightChildIndex;
        //标记哪个索引满足条件
        int ifIndex;
        //heapArray.length / 2   :代表叶节点
        while (i < heapArray.length / 2) {
            //左子节点索引
            leftChildIndex = 2 * i + 1;
            //右子节点索引
            rightChildIndex = leftChildIndex + 1;
            //判断右子节点有值
            if (rightChildIndex < heapArray.length - 1 && heapArray[leftChildIndex].getData() < heapArray[rightChildIndex].getData()) {
                ifIndex = rightChildIndex;
            } else {
                ifIndex = leftChildIndex;
            }
            if (heapArray[i].getData() > heapArray[ifIndex].getData()) {
                break;
            }
            heapArray[i] = heapArray[ifIndex];
            i = ifIndex;
        }
        heapArray[i] = currentNode;
    }

    //替换指定索引的值
    public void change(int index, int data) {
        if (index < 0 || index >= size) {
            return;
        }
        heapArray[index]=new Node(data);
        if (data > heapArray[index].getData()) {
            moveUp(index);
        } else {
            moveDown(index);
        }

    }

    //遍历打印
    public void forEach() {
        for (int i = 0; i < size; i++) {
            System.out.print(heapArray[i].getData()+" ");
        }
        System.out.println();
    }
}


/**
 * 堆的节点对象
 */
class Node {
    //具体数据值
    private int data;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
