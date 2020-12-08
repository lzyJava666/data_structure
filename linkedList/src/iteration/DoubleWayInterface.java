package iteration;

/**
 * 基本双向链表接口
 */
public interface DoubleWayInterface<T> {
    //返回节点数
    int getSize();
    //头部插入数据
    void addHead(T data);
    //尾部插入数据
    void addTail(T data);
}
