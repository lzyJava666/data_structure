package list;
import java.util.Arrays;

public class ListArray {
    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        arrayList.add(11);
        arrayList.add(22);

        arrayList.forEach();

        arrayList.delete(8);

        arrayList.forEach();

    }
}

class MyArrayList<T>{
    int length;
    Object[] array;

    public MyArrayList() {
        array=new Object[10];
        length=0;
    }

    public void add(Object obj){
        if(length==array.length){
            array=Arrays.copyOf(array,length+(length >> 1));
        }
        array[length++]=obj;
    }

    public void forEach(){
        for (int i = 0; i < length; i++) {
            System.out.print(array[i]+"  ");
        }
        System.out.println();
    }

    public void delete(Object obj){
        int i;
        for (i = 0; i < length; i++) {
            if(obj.equals(array[i])){
                break;
            }
        }
        if(i==length){
            System.out.println("没有找到元素："+obj);
        }else{
            for (int j = i; j < length; j++) {
                array[j]=array[j+1];
            }
            length--;
        }
    }
}
