package digraph;
import java.util.Stack;

/**
 * 无向图实例
 */
public class Graph {
    //顶点类
    private Vertex[] vertices;
    //领接矩阵
    private int[][] dataArray;
    //保存的顶点数
    private int size;
    //深度优先搜索（DFS)
    private Stack<Integer> dfsStack;
    //广度优先搜索（BFS）
    private OneQueue bfsQueue;
    //默认顶点数
    private final int DEFAULT_VERTICE_Length = 20;

    //初始化
    public Graph() {
        vertices = new Vertex[DEFAULT_VERTICE_Length];
        dataArray = new int[DEFAULT_VERTICE_Length][DEFAULT_VERTICE_Length];
        for (int i = 0; i < DEFAULT_VERTICE_Length; i++) {
            for (int j = 0; j < DEFAULT_VERTICE_Length; j++) {
                dataArray[i][j] = 0;
            }
        }
        size = 0;
        dfsStack = new Stack();
        bfsQueue = new OneQueue();
    }

    //判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //判断是否满
    public boolean isFulle() {
        return size == DEFAULT_VERTICE_Length;
    }

    //打印指定顶点的值
    public void displayVertex(int index) {
        System.out.print(vertices[index].getLabel()+" ");
    }

    //插入
    public void insert(char data) {
        vertices[size++] = new Vertex(data);
    }

    //连接
    public void connect(int i, int j) {
        dataArray[i][j] = 1;
        dataArray[j][i] = 1;
    }

    //将所有元素的查看记录情况
    private void clear() {
        for (int i = 0; i < size; i++) {
            vertices[i].setVisited(false);
        }
    }

    //深度优先搜索算法
    public void dfsSearch() {
        //将第一个顶点标记为已读
        vertices[0].setVisited(true);
        //打印第一个顶点
        displayVertex(0);
        //压入栈
        dfsStack.push(0);
        //存放临时变量
        int v;
        while (!dfsStack.isEmpty()) {
            // 拿到栈顶点数据的相邻节点索引
            v = getStackAdjoinOne(dfsStack.peek());
            if (v == -1) {
                //结果为-1 说明已经没有相邻元素了
                dfsStack.pop();
            } else {
                //压入栈
                dfsStack.push(v);
                vertices[v].setVisited(true);
                displayVertex(v);
            }
        }
        clear();
    }

    //广度优先搜索
    public void bfsSearch() {
        //将第一个元素标记，打印，加入队列
        vertices[0].setVisited(true);
        displayVertex(0);
        bfsQueue.push(0);
        //临时变量
        int v;
        int v2;
        while (!bfsQueue.isEmpty()) {
            v= (int) bfsQueue.pop();
            while ((v2=getStackAdjoinOne(v))!=-1){
                vertices[v2].setVisited(true);
                displayVertex(v2);
                bfsQueue.push(v2);
            }
        }
        clear();
    }

    /**
     * 拿到栈顶点数据的相邻节点索引
     *
     * @param i 栈顶第一个元素值
     * @return 相邻的索引
     */
    private int getStackAdjoinOne(Integer i) {
        for (int j = 0; j < size; j++) {
            if (dataArray[i][j] == 1 && !vertices[j].getVisited()){
                return j;
            }
        }
        return -1;
    }


}
