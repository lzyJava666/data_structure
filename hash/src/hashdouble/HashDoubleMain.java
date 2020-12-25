package hashdouble;

/**
 * 基于再哈希法的哈希表
 */
public class HashDoubleMain {
    public static void main(String[] args) {
        MyHashDouble hash=new MyHashDouble(5);
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
 * 基于再哈希法的哈希表
 */
class MyHashDouble {
    //存放哈希表数据项的数组
    private DataItem[] dataItems;
    //哈希表的默认长度
    private final int DEFAULT_ARRAY_SIZE = 17;
    //被删除的数据项
    private DataItem delDataItem;
    //当前数据项个数
    private int size;

    public MyHashDouble() {
        dataItems = new DataItem[DEFAULT_ARRAY_SIZE];
        delDataItem = new DataItem(-1);
    }

    public MyHashDouble(int num) {
        while (true) {
            if(isPrime(num)){
                break;
            }
            num++;
        }
        dataItems = new DataItem[num];
        delDataItem = new DataItem(-1);
    }

    //判断一个数是否为质数
    private boolean isPrime(int num) {
        if(num%2==0){
            return false;
        }
        for (int i =3; i <Math.sqrt(num) ; i+=2) {
            if(num%i==0){
                return false;
            }
        }
        return true;
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

    //哈希函数1--哈希化
    private int hashFunc1(int key) {
        return key % dataItems.length;
    }

    //哈希函数2--求步长
    private int hashFunc2(int key) {
        return 13- key % 13;
    }

    //插入
    public void insert(int data) {
        DataItem newDataItem = new DataItem(data);
        //计算步长
        int hashSize=hashFunc2(data);
        // 哈希化
        data = hashFunc1(data);
        if (isFull()) {
            //哈希表满时扩容
            dilatation();
        }
        while (dataItems[data] != null && dataItems[data].getData() != -1) {
            data = hashFunc1(hashSize+data);
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
        while (true){
            if(isPrime(laterSize)){
                break;
            }
            laterSize++;
        }
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
        int hashData = hashFunc1(data);
        int hashSize=hashFunc2(data);
        while (dataItems[hashData] != null) {
            if (data == dataItems[hashData].getData()) {
                return hashData;
            } else {
                hashData = hashFunc1(hashSize+hashData);
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
