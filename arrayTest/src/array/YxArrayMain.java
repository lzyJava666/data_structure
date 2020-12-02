package array;

import java.util.LinkedList;

/**
 * 有序算法的主程序入口
 */
public class YxArrayMain {
    public static void main(String[] args) {
        //新增测试
        YxArray array = new YxArray(10);

        array.add(59);
        array.add(11);
        array.add(22);
        array.add(666);
        array.add(21);
        array.add(51);
        array.add(1);
        array.add(9);
        array.add(559);
        array.add(121);
        array.add(521);
        array.add(115);

        array.forEach();

        //删除测试
        array.delete(121);

        array.forEach();

        //查找测试
        System.out.println(array.find(51));
    }
}

/**
 * 有序算法
 */
class YxArray {
    //存储有序数组的对象
    public int[] array;
    //当前数据总数
    public int length;

    public YxArray(int maxSize) {
        array = new int[maxSize];
        length = 0;
    }


    //判断当前数组是否已满
    public boolean isLength() {
        if (length < array.length) {
            System.out.println("数组未满");
            return true;
        } else {
            System.out.println("数组已满");
            return false;
        }
    }

    //插入
    public void add(int data) {
        //查看数组是否已满
        if (isLength()) {
            //找到比当前值小的数
            int i;
            for (i = 0; i < length; i++) {
                if (data < array[i]) {
                    //找到了
                    break;
                }
            }
            if (i < length) {
                //数组中存在比data大的数，则i以后的所有数向后移一位
                for (int j = length; j > i; j--) {
                    array[j] = array[j-1];
                }
                //data插入到i处
                array[i] = data;
            } else {
                //数组最后不存在比data大的数
                array[i] = data;
            }
            length++;
        }
    }

    //删除
    public void delete(int data) {
        //1.查找到数据
        int i;
        for (i = 0; i < length; i++) {
            if (array[i] == data) {
                //查到数据
                break;
            }
        }
        if (i == length) {
            System.out.println("数组中不存在当前数据:" + data);
        } else {
            //2.从后往前覆盖数据
            for (int j = i; j < length - 1; j++) {
                array[j] = array[j + 1];
            }
            length--;
        }
    }

    //遍历数组
    public void forEach() {
        for (int i = 0; i < length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    //查找
    public boolean find(int data) {
        //当前比对的最小下标
        int minData = 0;
        //当前的最大下标
        int maxData = length - 1;
        //当前比较的下标
        int myData;

        //遍历查找
        while (true) {
            myData = (maxData + minData) / 2;
            if (minData > maxData) {
                return false;
            }
            //判断
            if (data == array[myData]) {
                return true;
            } else if (data > array[myData]) {
                minData = myData + 1;
            } else {
                maxData = myData - 1;
            }
        }

    }

}
