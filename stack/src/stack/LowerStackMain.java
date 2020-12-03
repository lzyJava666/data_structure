package stack;

import javax.jws.Oneway;
import java.util.Arrays;
import java.util.Stack;

/**
 * 栈的实例
 */
public class LowerStackMain {
    public static void main(String[] args) {
        MyStack<Character> stack=new MyStack<>();
        //等价于下面jdk自带的stack
        Stack<Character> stack1=new Stack<>();

        /**
         * 实现字符串倒序排列
         */
        String demoString="hello World!";
        char[] chars = demoString.toCharArray();
        for(char c:chars){
            stack.push(c);
        }
        System.out.println(demoString);
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
        }
        System.out.println();

        /**
         * 实现检验标签对功能
         */
        String labelString="<[12({555})53]>";
        char[] labelChars = labelString.toCharArray();
        char c1;
        for(char c:labelChars){
            if(c == '<'||c=='['||c=='('||c=='{'){
                stack1.push(c);
            }else if(c=='>'||c==']'||c=='}'||c==')'){
                c1=stack1.pop();
                if((c1=='<'&&c=='>')||(c1=='['&&c==']')||(c1=='{'&&c=='}')||(c1=='('&&c==')')){

                }else{
                    System.out.println("符号：" + c + " 与符号：" + c1 + "不匹配");
                }
            }
        }
        if(!stack1.isEmpty()){
            System.out.println("还有标签没有匹配");
        }

    }
}

class MyStack<T>{
    // 存放栈的数组
    Object[] array;
    //栈的指针--永远指向栈顶
    int top;
    //栈的实际大小
    int length;

    public MyStack(){
        array=new Object[10];
        top=-1;
        length=10;
    }

    //是否满栈
    public boolean isFull(){
        return (top == (length-1));
    }

    //是否为空栈
    public boolean isEmpty(){
        return (top == -1);
    }

    //入栈
    public void push(Object obj){
        if(isFull()){
            //满栈
            if((length << 1) - Integer.MAX_VALUE >0){
                length=Integer.MAX_VALUE;
            }else{
                //容量翻二倍
                length=length << 1;
            }
            //扩容
            array=Arrays.copyOf(array,length);
        }
        array[++top]=obj;
    }

    // 弹栈
    public Object pop(){
        if(isEmpty()){
           return null;
        }else{
            Object o = array[top];
            array[top--]=null;
            return o;
        }
    }

    //查看
    public Object peek(){
        if(isEmpty()){
            return null;
        }else{
            return array[top];
        }
    }
}
