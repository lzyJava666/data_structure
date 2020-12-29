package digraph;

/**
 * 顶点类
 */
public class Vertex {
    /**
     * 具体数据，标签
     */
    private char label;
    /**
     * 是否已查看
     */
    private boolean isVisited;

    public boolean getVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public char getLabel() {
        return label;
    }

    public Vertex(char label) {
        this.label = label;
        this.isVisited=false;
    }
}
