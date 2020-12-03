package queue;

/**
 * 单向队列实例
 */
public class OneQueueMain {
    public static void main(String[] args) {
        OneQueue oneQueue=new OneQueue(3);
        oneQueue.push(5);
        oneQueue.push(10);
        oneQueue.push(15);
        //oneQueue.push(20);
        System.out.println(oneQueue.pop());
        System.out.println(oneQueue.find());
        System.out.println(oneQueue.find());
    }
}

/**
 * 单向队列
 */
class OneQueue{
    //存放队列数据的数组
    Object[] array;
    //队列的总长度
    int size;
    //队列的当前长度
    int length;
    //头指针(用于写)
    int rear;
    //尾指针（用于读）
    int front;

    public OneQueue(){
        //默认长度10
        array=new Object[10];
        size=10;
        length=0;
        rear=-1;
        front=0;
    }

    public OneQueue(int queueSize){
        array=new Object[queueSize];
        size=queueSize;
        length=0;
        rear=-1;
        front=-0;
    }

    //判断队中是否有数据
    public boolean isEmpty(){
        return (length==0);
    }

    //判断队是否已满
    public boolean isFull(){
        return (length==size);
    }

    //返回队长度
    public int getSize(){
        return size;
    }

    //入队
    public void push(Object obj){
        if (isFull()){
            System.out.println("当前队列已满");
        }else{
            if(rear+1==size){
                rear=-1;
            }
            array[++rear]=obj;
            length++;
        }
    }

    //出队
    public Object pop(){
        if(isEmpty()){
            System.out.println("当前是空队列");
            return null;
        }else{
            //front指向的数据删除
            Object o = array[front];
            array[front]=null;
            if(front+1==size){
                front=0;
            }else{
                front++;
            }
            return o;
        }
    }

    //查看队内第一个数据
    public Object find(){
        if(isEmpty()){
            return null;
        }else{
            return array[front];
        }
    }
}
