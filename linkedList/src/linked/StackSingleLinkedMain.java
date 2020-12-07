package linked;

/**
 * 用链表实现栈实例
 */
public class StackSingleLinkedMain {
    public static void main(String[] args) {
        MyStackSingleLinked stackSingleLinked=new MyStackSingleLinked();
        stackSingleLinked.push(1);
        stackSingleLinked.push(11);
        stackSingleLinked.push(111);
        stackSingleLinked.forEach();
        System.out.println(stackSingleLinked.pop());
        stackSingleLinked.forEach();
    }
}

/**
 * 实现底层为链表的栈
 */
class MyStackSingleLinked {

    MySingleLinked linked;

    public MyStackSingleLinked() {
        linked = new MySingleLinked();
    }

    //压栈
    public void push(Object data){
        linked.addHead(data);
    }

    //弹栈
    public Object pop(){
        return linked.deleteHead();
    }

    //遍历
    public void forEach(){
        linked.forEach();
    }
}
