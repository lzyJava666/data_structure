package array;

/**
 * 有序算法的主程序入口
 */
public class YxArrayMain {
    public static void main(String[] args) {

    }
}

/**
 * 有序算法
 */
class YxArray{
    //存储有序数组的对象
    public int[] array;
    //当前数据总数
    public int length;

    public YxArray(int maxSize){
        array=new int[maxSize];
        length=0;
    }


    //判断当前数组是否已满
    public boolean isLength(){
        if(length<array.length) {
            return true;
        }else{
            return false;
        }
    }

    //插入
    public void add(int data){
        //查看数组是否已满
        if(!isLength()) return;
        //找到比当前值小的数
        int i;
        for (i = 0; i < length; i++) {
            if(data<array[i]){
                //找到了
                break;
            }
        }
        if(i<length){
            //数组中存在比data大的数，则i以后的所有数向后移一位
            for (int j = length; j >i; j--) {
                array[j+1]=array[j];
            }
            //data插入到i处
            array[i]=data;
        }else{
            //数组最后不存在比data大的数
            array[i]=data;
        }
        length++;
    }

    //删除
    public void delete(int data){
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

}
