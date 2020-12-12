/**
 * 用栈实现递归的中间类
 */
public class Params<T> {
    //具体数据
    private T data;
    //下一跳地址
    private int returnAddress;

    public Params(T data, int returnAddress) {
        this.data = data;
        this.returnAddress = returnAddress;
    }

    public T getData() {
        return data;
    }

    public int getReturnAddress() {
        return returnAddress;
    }
}
