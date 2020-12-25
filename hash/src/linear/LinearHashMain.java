package linear;


/**
 * 基于线性探测的哈希表
 */
public class LinearHashMain {
    public static void main(String[] args) {
        myLinearHash hash=new myLinearHash(5);
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
 * 基于线性探测的哈希表
 */
class myLinearHash {
    //存放哈希表数据项的数组
    private DataItem[] dataItems;
    //哈希表的默认长度
    private final int DEFAULT_ARRAY_SIZE = 16;
    //被删除的数据项
    private DataItem delDataItem;
    //当前数据项个数
    private int size;

    public myLinearHash() {
        dataItems = new DataItem[DEFAULT_ARRAY_SIZE];
        delDataItem = new DataItem(-1);
    }

    public myLinearHash(int num) {
        dataItems = new DataItem[num];
        delDataItem = new DataItem(-1);
    }

    //判断是否为空哈希表
    public boolean isEmpty() {
        return size == 0;
    }

    //判断是否为满
    public boolean isFull() {
        return size == dataItems.length;
    }

    //循环遍历
    public void forEach() {
        for (int i = 0; i < dataItems.length; i++) {
            if (dataItems[i] != null)
                System.out.print(dataItems[i].getData() + " ");
            else
                System.out.print("*" + " ");
        }
        System.out.println();
    }

    //哈希函数
    private int hashFunc(int key) {
        return key % dataItems.length;
    }

    //插入
    public void insert(int data) {
        DataItem newDataItem = new DataItem(data);
        // 哈希化
        data = hashFunc(data);
        if (isFull()) {
            //哈希表满时扩容
            dilatation();
        }
        while (dataItems[data] != null && dataItems[data].getData() != -1) {
            data = hashFunc(++data);
        }
        dataItems[data] = newDataItem;
        size++;
    }

    //扩容操作
    private void dilatation() {
        //扩容前容量
        int frontSize = dataItems.length;
        //扩容后容量
        int laterSize = dataItems.length << 2;
        //扩容前数组
        DataItem[] frontDataItems = dataItems;
        dataItems = new DataItem[laterSize];
        for (int i = 0; i < frontSize; i++) {
            insert(frontDataItems[i].getData());
        }
    }

    //查找操作,返回索引
    public int find(int data) {
        if (isEmpty()) {
            return -1;
        }
        int hashData = hashFunc(data);
        while (dataItems[hashData] != null) {
            if (data == dataItems[hashData].getData()) {
                return hashData;
            } else {
                hashData = hashFunc(++hashData);
            }
        }
        return -1;
    }

    //删除操作
    public void delete(int data) {
        int delIndex = find(data);
        if (-1 == delIndex) {
            System.out.println("没有删除项");
            return;
        } else {
            dataItems[delIndex] = delDataItem;
            size--;
        }
    }
}


/**
 * 封装数据项的类
 */
class DataItem {
    //具体数据
    private int data;

    public DataItem(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
