import java.util.Stack;

/**
 * 递归实现阶乘
 */
public class Factorial {
    public static void main(String[] args) {
        System.out.println(Factorial(5));
        System.out.println(FactorialByStack(5));
        System.out.println(Factorial(3));
        System.out.println(FactorialByStack(3));
    }

    /**
     * 阶乘算法
     * 思路：
     *      n! == n * (n-1)! == n*(n-1)*(n-2)!
     */
    public static int Factorial(int n){
        if(n>=0){
            if(n==0){
                //n==0便是边界条件
                return 1;
            }else{
                int tem=n*Factorial(n-1);
                return tem;
            }
        }else{
            //负数没有阶乘
            return -1;
        }
    }

    /**
     * 用栈取代递归实现阶乘
     */
    public static int FactorialByStack(int n){
        //临时变量
        Params<Integer> params;
        //当前返回值
        int currentReturnValue=1;
        //当前执行地址
        int currentReturnAddress=1;
        //控制循环变量
        boolean flag=true;
        //核心栈
        Stack<Params> stack=new Stack<>();
        while (flag){
            switch (currentReturnAddress){
                //初始化参数
                case 1:{
                    params=new Params<>(n,6);
                    //将对象放入栈中
                    stack.push(params);
                    //执行分支2
                    currentReturnAddress=2;
                }
                break;
                //判断是否满足边界条件
                case 2:{
                    params=stack.peek();
                    if(params.getData()==1){
                        //满足条件、跳转到分支5
                        currentReturnAddress=5;
                    }else{
                        //不满足边界条件，执行3
                        currentReturnAddress=3;
                    }
                }
                break;
                //用栈完成递归操作
                case 3:{
                    params=stack.peek();
                    //模拟递归
                    params=new Params<>(params.getData()-1,4);
                    //放入栈中
                    stack.push(params);
                    //回到第二分支
                    currentReturnAddress=2;
                }
                break;
                //模拟递归切换到返回时的操作，这里是累乘
                case 4:{
                    params=stack.peek();
                    currentReturnValue*=params.getData();
                    currentReturnAddress=5;
                }
                break;
                //循环执行累乘
                case 5:{
                    params=stack.pop();
                    currentReturnAddress=params.getReturnAddress();
                }
                break;
                //执行到这结束循环
                case 6:{
                    flag=false;
                }
            }
        }
        return currentReturnValue;
    }





}
