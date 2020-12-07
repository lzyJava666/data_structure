package queue;

/**
 * 优先级队列实例
 */
public class PriorityQueueMain {
    public static void main(String[] args) {
        PriorityQueue priorityQueue=new PriorityQueue(3);
        priorityQueue.add(5);
        priorityQueue.add(1);
        priorityQueue.add(15);
        priorityQueue.add(15);

        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.remove()+" ");
        }
    }


}


class PriorityQueue{
    int[] array;
    int length;

    public PriorityQueue(int maxSize){
        array=new int[maxSize];
        length=0;
    }

    //判断是否为空
    public boolean isEmpty(){
        return length==0;
    }

    //判断是否已满
    public boolean isFull(){
        return length==array.length;
    }

    //插入
    public void add(int data){
        if(isFull()){
            System.out.println("队列已满，无法插入");
            return;
        }
        if(isEmpty()){
            array[length++]=data;
        }else{
            int i=length-1;
            while (i>=0&&array[i]>data){
                array[i+1]=array[i];
                i--;
            }
            array[i+1]=data;
            length++;
        }
    }

    //查找元素
    public int find(){
        if(isEmpty()){
            return -999;
        }else{
            return array[length-1];
        }
    }

    //删除元素
    public int remove(){
        if(isEmpty()){
            return -999;
        }else{
            int o=array[length-1];
            array[length-1]=0;
            length--;
            return o;
        }
    }


}
