package array;

/**
 * 无序数组
 */
public class WxArray {

    public static void main(String[] args) {
        //新增测试
        Array array = new Array(6);
        array.add(40);
        array.add(240);
        array.add(50);
        array.add(41);
        array.add(66);
        array.add(12);
        array.add(4);

        array.forEach();

        //删除测试
        array.delete(41);

        array.forEach();

        //查找测试
        System.out.println(array.find(12));

    }
}

/**
 * 无序数组实现
 */
class Array {
    //存储具体数据的数组
    int[] array;
    //当前数组存放数据数量
    int length;

    //判断是否还有空位
    public boolean isLength(){
        if(array.length>length){
            return true;
        }else{
            return false;
        }
    }

    public Array(int maxPage) {
        array = new int[maxPage];
        length = 0;
    }

    //插入
    public void add(int data) {
        if(isLength()) {
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
            for (int j = i; j < length-1; j++) {
                array[j] = array[j + 1];
            }
            length--;
        }
    }

    //遍历
    public void forEach() {
        for (int i = 0; i < length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }


}
