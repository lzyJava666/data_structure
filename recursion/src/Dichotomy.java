import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 二分查找
 */
public class Dichotomy {
    public static void main(String[] args) {
        int[] arr = {5, 7, 9, 11, 16, 17};
        int dichotomy = dichotomy(arr, 9, 0, arr.length - 1);
        System.out.println(dichotomy);
        System.out.println(dichotomyByStack(arr, -2, 0, arr.length - 1));
    }

    /**
     * 二分查找（递归）查找到对应数据下标
     *
     * @param array 有序数组
     * @param value 查找的值
     * @param top   上行下标
     * @param tail  下行下标
     * @return 找不到返回-1
     */
    public static int dichotomy(int[] array, int value, int top, int tail) {
        //中间下标
        int tem = (tail - top) / 2 + top;
        if (top > tail) {
            //边界条件1
            return -1;
        } else if (array[tem] == value) {
            //边界条件2
            return tem;
        } else {
            if (value > array[tem]) {
                return dichotomy(array, value, tem + 1, tail);
            } else {
                return dichotomy(array, value, top, tem - 1);
            }
        }
    }

    public static int dichotomyByStack(int[] array, int value, int top, int tail) {
        Stack<Params> stack = new Stack<>();
        boolean flag = true;
        int currentReturnAddress = 1;
        int currentReturnValue=-1;
        Params<Map> params;
        Map<String, Integer> map;
        int tem;
        while (flag) {
            switch (currentReturnAddress) {
                case 1: {
                    map = new HashMap<>();
                    map.put("top", top);
                    map.put("tail", tail);
                    params = new Params<>(map, 6);
                    stack.push(params);
                    currentReturnAddress = 2;
                }
                break;
                case 2: {

                    params = stack.peek();
                    top = Integer.parseInt(params.getData().get("top").toString());
                    tail = Integer.parseInt(params.getData().get("tail").toString());
                    tem = (tail - top) / 2 + top;
                    if (top > tail) {
                        currentReturnValue = -1;
                        currentReturnAddress = 5;
                    }else if (value == array[tem]) {
                        currentReturnValue = tem;
                        currentReturnAddress = 5;
                    } else {
                        currentReturnAddress = 3;
                    }
                }
                break;
                case 3: {
                    params=stack.peek();
                    map=params.getData();
                    tem=((map.get("tail")-map.get("top"))/2+map.get("top"));
                    if(value>array[tem]){
                        map.put("top",tem+1);
                    }else{
                        map.put("tail",tem-1);
                    }
                    params=new Params<>(map,4);
                    stack.push(params);
                    currentReturnAddress=2;
                }
                break;
                case 4:{
                    currentReturnAddress=5;
                }
                break;
                case 5:{
                    currentReturnAddress=6;
                }
                break;
                case 6:{
                    flag=false;
                }
            }
        }
        return currentReturnValue;
    }

}
