package iteration;

import java.util.Iterator;

/**
 * 带有迭代器的双向链表接口
 * @param <T>
 */
public interface IterationDoubleWayInterface<T> extends DoubleWayInterface<T> {
    //返回迭代器
    Iterator<T> getIterator();
}
