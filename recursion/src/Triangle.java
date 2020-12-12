import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * 数字三角形最大路径递归算法
 */
public class Triangle {
    //存放具体数据的二维数组
    private static int[][] arr;
    //存放三角形行数
    private static int n;
    public static void main(String[] args) {
        /**
         *  先解决显示三角形问题
         */
        System.out.print("请输入数组三角形的行数：");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("请输入第"+(i+1)+"行，第"+(j+1)+"列的内容：");
                int data = in.nextInt();
                arr[i][j]=data;
            }
        }
        System.out.println("显示三角形");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("最大值为："+SumData(1,1));

    }

    /**
     * 递归求出最大路径
     * @param i 起点的X坐标
     * @param j 起点的Y坐标
     * @return  最大值
     */
    private static int SumData(int i,int j){
        if(i==n){
            //边界条件（满足条件时，结束递归）
            return arr[i-1][j-1];
        }else{
            int max =arr[i-1][j-1]+ Math.max(SumData(i + 1, j), SumData(i + 1, j + 1));
            return max;
        }
    }


}
