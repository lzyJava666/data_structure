import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 递归求数的乘方
 */
public class NumberPower {
    public static void main(String[] args) {
        System.out.println(numberPower(2, 5));
        System.out.println(numberByStack(2,5));
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
        } else if (y < 0) {
            return -1;
        } else if (y > 1) {
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

    /**
     * 用栈取代递归实现一个数的乘方运算
     *
     * @param x 被乘方数
     * @param y 乘方数
     * @return 值
     */
    public static double numberByStack(int x, int y) {
        Stack<Params> stack = new Stack<>();
        boolean flag = true;
        int currentReturnValue = 1;
        int currentReturnAddress = 1;
        Params<Map> params;
        Map<String, Integer> map;
        while (flag) {
            switch (currentReturnAddress) {
                case 1: {
                    map = new HashMap<>();
                    map.put("x", x);
                    map.put("y", y);
                    params = new Params<>(map, 6);
                    stack.push(params);
                    currentReturnAddress = 2;
                }
                break;
                case 2: {
                    params = stack.peek();
                    map = params.getData();
                    x = map.get("x");
                    y = map.get("y");
                    if (x == 0 || x == 1 || y == 0) {
                        currentReturnValue = 1;
                        currentReturnAddress = 5;
                    } else if (y == 1) {
                        currentReturnValue *= x;
                        currentReturnAddress = 5;
                    } else {
                        if (y % 2 == 1) {
                            currentReturnValue = x;
                        }
                        currentReturnAddress = 3;
                    }
                }
                break;
                case 3: {
                    params = stack.peek();
                    map = params.getData();
                    map.put("x", map.get("x") * map.get("x"));
                    map.put("y", map.get("y") / 2);
                    params = new Params<>(map,4);
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
