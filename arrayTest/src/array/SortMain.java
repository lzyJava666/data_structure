package array;

import java.util.Arrays;

/**
 * 排序算法
 */
public class SortMain {

    public static void main(String[] args) {
        //插入
        ArraySort array = new ArraySort(10);
        array.add(50);
        array.add(12);
        array.add(550);
        array.add(122);
        array.add(5);
        array.add(112);
        array.add(5510);
        array.add(70);
        array.add(32);
        array.forEach();
        //冒泡排序
        array.shellSort();
        array.forEach();

        System.out.println("-----------------------------");
        int[] arr=new int[]{5,12,66,11,2,0,6,1};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 递归快速排序
     *
     * @param arr   排序数组
     * @param left  左指针
     * @param right 右指针
     */
    public static void quickSort(int[] arr, int left, int right) {
        //边界条件
        if (left >= right) {
            return;
        } else {
            //返回临界点的坐标
            int centreIndex = centreQuick(arr, left, right);
            quickSort(arr, left, centreIndex - 1);
            quickSort(arr, centreIndex + 1, right);
        }

    }

    /**
     * 返回临界点坐标
     */
    private static int centreQuick(int[] arr, int left, int right) {
        int i = left;
        int j = right+1;
        int data = arr[left];
        if(arr.length>=3){
            // 取中值
            data=getCenter(arr,left,right);
        }
        while (true) {
            //当走到的数小于临界值时，继续走
            while (arr[++i] < data && i < right) {
            }
            //相反
            while (j > 0 && arr[--j] > data) {
            }
            if (i >= j) {
                break;
            } else {
                //对换位置
                swap(arr, i, j);
            }
        }
        swap(arr, left, j);
        return j;
    }

    /**
     * 获取中间数 并且将最大的数放最后，最小的数放中间，中间数放第一位
     * @param arr 数组
     * @param left 起始坐标
     * @param right 结束坐标
     * @return 值
     */
    private static int getCenter(int[] arr, int left, int right) {
        int center=(right-left)/2+left;
        if(arr[left]>arr[right]){
            swap(arr,left,right);
        }
        if(arr[center]>arr[right]){
            swap(arr,center,right);
        }
        if(arr[left]<arr[center]){
            swap(arr,left,center);
        }
        return arr[left];
    }

    //交换位置
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/**
 * 无序数组实现
 */
class ArraySort {
    //存储具体数据的数组
    int[] array;
    //当前数组存放数据数量
    int length;

    //判断是否还有空位
    public boolean isLength() {
        if (array.length > length) {
            return true;
        } else {
            return false;
        }
    }

    public ArraySort(int maxPage) {
        array = new int[maxPage];
        length = 0;
    }

    //插入
    public void add(int data) {
        if (isLength()) {
            array[length] = data;
            length++;
        }
    }

    //查到是否存在当前数据
    public boolean find(int data) {
        int i;
        for (i = 0; i < length; i++) {
            if (array[i] == data) {
                break;
            }
        }
        if (i == length) {
            System.out.println("数组中不存在当前数据");
            return false;
        } else {
            return true;
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

    //遍历
    public void forEach() {
        for (int i = 0; i < length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    //冒泡排序
    public void bubSort() {
        //外部循环变量
        int outFor;
        //内部循环变量
        int intFor;
        for (outFor = length - 1; outFor > 2; outFor--) {
            for (intFor = 0; intFor < outFor; intFor++) {
                if (array[intFor] < array[intFor + 1]) {
                    int data = array[intFor + 1];
                    array[intFor + 1] = array[intFor];
                    array[intFor] = data;
                }
            }
            intFor = 0;
        }
    }

    //选择排序
    public void selectSort() {
        //外部变量
        int outer;
        //内部循环变量
        int inter;
        //当前最小值的下标
        int min;

        for (outer = 0; outer < length - 1; outer++) {
            min = outer;
            for (inter = outer + 1; inter < length; inter++) {
                if (array[min] > array[inter]) {
                    min = inter;
                }
            }
            int data = array[min];
            array[min] = array[outer];
            array[outer] = data;
        }
    }

    //插入排序
    public void insertSort() {
        int outer;
        int inter;
        //临时数据存储
        int data;

        for (outer = 1; outer < length; outer++) {
            data = array[outer];
            for (inter = outer - 1; inter >= 0; inter--) {
                if (array[inter] > data) {
                    array[inter + 1] = array[inter];
                } else {
                    break;
                }
            }
            array[inter + 1] = data;
        }
    }

    //希尔排序
    public void shellSort() {
        //内循环变量
        int inter;
        //外循环变量
        int outer;
        //间隔
        int h;
        //临时变量
        int data;
        for (h = (int) (array.length / 2.2); h > 0; h = (int) (h / 2.2)) {
            for (outer = h; outer < array.length; outer++) {
                inter = outer;
                data = array[inter];
                while (inter - h >= 0 && data > array[inter - h]) {
                    array[inter] = array[inter - h];
                    inter -= h;
                }
                array[inter] = data;
            }
        }
    }


}
