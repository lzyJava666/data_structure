package digraph;

/**
 * 五向图测试类
 */
public class GraphMain {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.insert('A');
        graph.insert('B');
        graph.insert('C');
        graph.insert('D');
        graph.insert('E');
        graph.insert('F');
        graph.insert('G');
        graph.insert('H');
        graph.insert('I');
        graph.connect(0,1);
        graph.connect(1,5);
        graph.connect(5,7);
        graph.connect(0,2);
        graph.connect(0,3);
        graph.connect(0,4);
        graph.connect(3,6);
        graph.connect(6,8);
        graph.bfsSearch();
        System.out.println();
        graph.dfsSearch();


    }
}
