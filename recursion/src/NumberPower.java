import java.util.Stack;

/**
 * 递归求数的乘方
 */
public class NumberPower {
    public static void main(String[] args) {
        System.out.println(numberPower(2, 10));
    }

    /**
     * 递归求一个数的乘方
     *
     * @param x 被乘方数
     * @param y 乘方数
     * @return 值
     */
    public static double numberPower(int x, int y) {
        double number = 0;
        if (x == 0) {
            return 1;
        } else if (x == 1) {
            return 1;
        }else if(y<0){
            return -1;
        }
        else if (y > 1) {
            if (Math.abs(y) % 2 == 1) {
                //奇数
                number = numberPower(x * x, Math.abs(y) / 2) * x;
            } else {
                //偶数
                number = numberPower(x * x, Math.abs(y) / 2);
            }
        } else if (y == 0) {
            return 1;
        } else {
            return x;
        }
       return number;
    }



}
