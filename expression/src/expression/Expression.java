package expression;


import java.util.Stack;

/**
 * 解析算术表达式实例
 */
public class Expression {
    public static void main(String[] args) {
        String input = "(555-(11+122)*5-(152-5)*12)+9*8-11";
        double inputValue= (555-(11+122)*5-(152-5)*12)+9*8-11;
        System.out.println(input+"值："+inputValue+" ");
        double v = changeExpression(input);
        System.out.println(v);

    }

    /**
     * 将中缀表达式转变成后缀表达式
     *
     * @param input 中缀表达式
     * @return 后缀表达式 计算后的值
     */
    public static double changeExpression(String input) {
        //存放运算符的s1
        Stack<Character> s1 = new Stack<>();
        //存放中间结果的栈
        Stack<Double> s2 = new Stack<>();
        //存放临时字符
        char myChar;
        //从左到又扫描
        for (int i = 0; i < input.length(); i++) {
            //当前扫描到的字符
            myChar = input.charAt(i);
            //扫描到数字
            if (Character.isDigit(myChar)) {
                /**
                 *截取到所有数字
                 */
                //数字后面的字符的下标
                int lastIndex = getNumberLastIndex(input, i);

                if (i == -1) {
                    throw new RuntimeException("当前算术表达式不合法");
                }
                s2.push(Double.parseDouble(input.substring(i, lastIndex)));
                i = lastIndex - 1;
                //扫描到运算符
            } else if (isOperator(myChar)) {
                while ((!s1.isEmpty()) && s1.peek() != '(' && priorityComparison(myChar, s1.peek()) <= 0) {
                    double num1 = (double) s2.pop();
                    double num2 = (double) s2.pop();
                    //运算并把值压入s2
                    s2.push(cal(num1, num2, s1.pop()));
                }
                s1.push(myChar);
            } else if (myChar == '(') {
                s1.push(myChar);
            } else if (myChar == ')') {
                char ch;
                while ((ch = s1.pop()) != '(') {
                    double num1 = (double) s2.pop();
                    double num2 = (double) s2.pop();
                    //运算并把值压入s2
                    s2.push(cal(num1, num2, ch));
                }
            }

        }
        while (!s1.isEmpty()) {
            double num1 = (double) s2.pop();
            double num2 = (double) s2.pop();
            //运算并把值压入s2
            s2.push(cal(num1, num2, s1.pop()));
        }
        //while (!s2.isEmpty()) {
        //    System.out.print(s2.pop() + " ");
        //}
        return s2.pop();
    }

    /**
     * 计算
     *
     * @param num1   计算数
     * @param num2   被计算数
     * @param myChar 运算符
     * @return 值
     */
    private static double cal(double num1, double num2, char myChar) {
        switch (myChar) {
            case '+':
                return num2 + num1;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
        }
        return 0;
    }

    /**
     * 比较优先级
     *
     * @param s1 第一个元素
     * @param s2 第二个元素
     * @return s1 > s2 =1  s1 == s2 =0   s1 < s2 -1
     */
    private static int priorityComparison(char s1, Character s2) {
        if (s1 == '+' || s1 == '-') {
            return s2 == '*' || s2 == '/' ? -1 : 0;
        } else {
            return s2 == '+' || s2 == '-' ? 1 : 0;
        }
    }

    //判断是否扫描到运算符
    private static boolean isOperator(char myChar) {
        if (myChar == '+' || myChar == '-' || myChar == '/' || myChar == '*') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 拿到数字后面字符的下标
     *
     * @param input 操作的字符串
     * @param i     从哪个下标开始
     * @return 字符串后面字符的下标
     */
    private static int getNumberLastIndex(String input, int i) {
        for (int j = i; j < input.length(); j++) {
            char c = input.charAt(j);
            if (c == '.') {
                continue;
            } else if (!Character.isDigit(c)) {
                return j;
            } else if (j == input.length() - 1) {
                return input.length();
            }
        }
        return -1;
    }
}
