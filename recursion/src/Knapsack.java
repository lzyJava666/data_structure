
/**
 * 背包问题
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] data = new int[]{5, 11, 12, 20, 25,23};
        MyKnapsack k = new MyKnapsack(data);
        k.doKnapsack(23, 0);
    }

    private static class MyKnapsack {
        //@param data 数据项
        private int[] data;
        private boolean[] select;

        public MyKnapsack(int[] data) {
            this.data = data;
            // 用于标记是否被选中
            select = new boolean[data.length];
        }

        /**
         * 背包问题的解决
         *
         * @param value 背包总重量
         * @param index 从哪个数据项开始
         */
        public void doKnapsack(int value, int index) {
            if ((value > 0 && index >= data.length) || (value < 0)) {
                //所以数据项均没有组合方案
                return;
            }
            if (value == 0) {
                //已找到对应数据项
                System.out.print("找到的数据项为：");
                for (int i = 0; i < select.length; i++) {
                    if (select[i])
                        System.out.print( " "+ data[i]);
                }
                System.out.println();
                return;
            }
            //实现递归
            //计算下一个元素
            select[index] = true;
            doKnapsack(value - data[index], index + 1);
            //当前元素不满足条件
            select[index] = false;
            doKnapsack(value, index + 1);
        }
    }
}
