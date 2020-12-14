/**
 * 组合问题
 */
public class Combination {
    public static void main(String[] args) {
        Comb c=new Comb(new char[]{'A','B','C','D','E'});
        c.doCombination(3,0);
    }

    private static class Comb{
        //存放字符数据的数组
        private char[] data;
        // 选择器
        private boolean[] select;

        public Comb(char[] data) {
            this.data = data;
            select=new boolean[data.length];
        }

        /**
         * 递归解决组合问题
         * @param selectNum 选择人数
         * @param index 从哪个下标开始
         */
        public void doCombination(int selectNum,int index){
            if(selectNum==0){
                //找到了一组
                for (int i = 0; i < select.length; i++) {
                    if(select[i])
                    System.out.print(data[i]+" ");
                }
                System.out.println();
                return;
            }
            //边界条件
            if(index>=data.length){
                //所有组合已经选完
                return;
            }
            //实现递归
            //思路 ： 在5个人中找3个  等于 在4个人中找2个加4个人中找3个的组合
            //index目标选择  4个人中找2个情况
            select[index]=true;
            doCombination(selectNum-1,index+1);
            //index目标不选择，4个人中找3个情况
            select[index]=false;
            doCombination(selectNum,index+1);
        }
    }
}
