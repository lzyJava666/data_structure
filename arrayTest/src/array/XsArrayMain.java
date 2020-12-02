package array;

/**
 * 稀疏数组案例
 */
public class XsArrayMain {
    public static void main(String[] args) {
        //方式一：二维数组存储，用空间换取时间，占用空间大，查询效率高
        int[][] array = new int[7][9];  //占用63
        array[1][1] = 3;
        array[3][0] = 1;
        array[3][1] = 4;
        array[4][2] = 7;
        array[5][5] = 5;

        //方式一输出
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println("--------分隔符--------");

        //方式二：使用压缩方式，执行效率较低，占用空间小
        Node[] nodes = new Node[6];
        nodes[0] = new Node(7, 9, 6);
        nodes[1] = new Node(1, 1, 3);
        nodes[2] = new Node(3, 0, 1);
        nodes[3] = new Node(3, 1, 4);
        nodes[4] = new Node(4, 2, 7);
        nodes[5] = new Node(5, 5, 5);

        //方式二输出
        for (int i = 0; i < nodes[0].getRow(); i++) {
            for (int j = 0; j < nodes[0].getCol(); j++) {
                //第一行为记录数据行，所以跳过0下标
                int k;
                for (k = 1; k < nodes.length; k++) {
                    if (nodes[k].getRow() == i && nodes[k].getCol() == j) {
                        break;
                    }
                }
                if(k<nodes.length){
                    System.out.print(nodes[k].getVal()+"  ");
                }else{
                    System.out.print(0+"  ");
                }
            }
            //换行
            System.out.println();
        }

    }
}

/**
 * 压缩稀疏数组的节点
 */
class Node {
    //行
    private int row;
    //列
    private int col;
    //具体数据
    private int val;

    public Node(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getVal() {
        return val;
    }
}
