package tow34tree;

import java.util.Arrays;

/**
 * 234树实例
 */
public class Two34TreeMain {
    public static void main(String[] args) {
        MyTwo34Tree tree = new MyTwo34Tree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(20);
        tree.insert(29);
        tree.insert(1);
        tree.insert(55);
        System.out.println(tree.find(20));
    }
}

/**
 * 234树实现类
 */
class MyTwo34Tree {
    // 根节点
    private Node root;

    public MyTwo34Tree(){
        root=new Node();
    }

    //查找节点
    public Node find(int data) {
        Node currentNode = root;
        while (true) {
            if (currentNode.getDataItemIndex(data) == -1) {
                //当前节点没有找到数据
                if (currentNode.isLeafChild()) {
                    //当前节点为叶节点
                    return null;
                } else {
                    //不是叶节点
                    currentNode = getNextChild(currentNode, data);
                }
            } else {
                return currentNode;
            }
        }
    }

    /**
     * 拿到循环下一步的Node节点
     *
     * @param myNode 当前node节点
     * @param data   查询的值
     * @return 下一步的节点对象
     */
    public Node getNextChild(Node myNode, int data) {
        int i;
        for (i = 0; i < myNode.getSize(); i++) {
            if (myNode.getDataItems()[i].getData() > data) {
                return myNode.getChildNodes()[i];
            }
        }
        return myNode.getChildNodes()[i];
    }

    //插入节点
    public void insert(int data) {
        //插入数据项
        DataItem newDataItem = new DataItem(data);
        //当前操作节点
        Node currentNode = root;

        while (true) {
            if (currentNode.isFull()) {
                //满数据项，执行分裂
                split(currentNode);
                currentNode=currentNode.getParentNode();
                currentNode=getNextChild(currentNode,data);
            } else {
                //未满节点
                //情况1：当前节点为叶节点，且数据项未满
                if (currentNode.isLeafChild()) {
                    // 叶节点
                    currentNode.insert(data);
                    return;
                } else {
                    //非叶节点
                    //循环继续往下执行
                    int i;
                    for (i = 0; i < currentNode.getSize(); i++) {
                        if (data < currentNode.getDataItems()[i].getData()) {
                            break;
                        }
                    }
                    currentNode = currentNode.getChildNodes()[i];
                }
            }
        }


    }

    //分裂
    private void split(Node currentNode) {
        //当前节点的父节点
        Node parentNode;
        //当前节点的第三子节点
        Node node3 = currentNode.disconnectChild(2);
        //当前节点的第四子节点
        Node node4 = currentNode.disconnectChild(3);
        //当前节点的第三数据项
        DataItem dataItem3 = currentNode.removeTailChild();
        //当前节点的第二数据项
        DataItem dataItem2 = currentNode.removeTailChild();
        // 分裂后的右边节点
        Node rightNode;
        //标记父节点插入新数据的索引值
        int parentAddIndex;

        //初始化父节点
        if (currentNode == root) {
            //当前节点为root节点
            root = new Node();
            parentNode = root;
            parentNode.connectChild(0, currentNode);
        } else {
            //当前节点不是root节点
            parentNode = currentNode.getParentNode();
        }
        //初始化分裂后的右节点
        rightNode=new Node();
        rightNode.connectChild(0,node3);
        rightNode.connectChild(1,node4);
        rightNode.insert(dataItem3.getData());

        parentAddIndex=parentNode.insert(dataItem2.getData());
        //父节点连接右节点
        parentNode.connectChild(parentAddIndex+1,rightNode);
        //临时变量
        Node linshiNode;
        for (int i = parentNode.getSize()-1; i >parentAddIndex ; i--) {
            linshiNode=parentNode.disconnectChild(i);
            parentNode.connectChild(i+1,linshiNode);
        }
    }

}

/**
 * 数据项封装类
 */
class DataItem {
    /**
     * 具体数据
     */
    private int data;

    public DataItem(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void displayItem(){
        System.out.println("/"+data);
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "data=" + data +
                '}';
    }
}

/**
 * 234树的节点类
 */
class Node {
    //父节点
    private Node parentNode;
    //存放数据项的数组
    private DataItem[] dataItems = new DataItem[3];
    //存放子节点数组
    private Node[] childNodes = new Node[4];
    //记录当前数据项个数
    private int dataItemSize;

    public Node() {
        dataItemSize = 0;
        parentNode = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                ", dataItems=" + Arrays.toString(dataItems) +
                ", childNodes=" + Arrays.toString(childNodes) +
                ", dataItemSize=" + dataItemSize +
                '}';
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public DataItem[] getDataItems() {
        return dataItems;
    }

    public void setDataItems(DataItem[] dataItems) {
        this.dataItems = dataItems;
    }

    public Node[] getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Node[] childNodes) {
        this.childNodes = childNodes;
    }

    //返回是否为叶节点
    public boolean isLeafChild() {
        return childNodes[0]==null;
    }

    //返回当前节点数据项个数
    public int getSize() {
        return dataItemSize;
    }

    /**
     * 连接子节点
     *
     * @param childNodeIndex：存放子节点数组的索引值
     * @param myNode                     目标子节点
     */
    public void connectChild(int childNodeIndex, Node myNode) {
        childNodes[childNodeIndex] = myNode;
        if(myNode==null){

        }else
        myNode.setParentNode(this);
    }

    /**
     * 断开子节点
     *
     * @param childNodeIndex 断开目标的索引
     */
    public Node disconnectChild(int childNodeIndex) {
        Node disNode = childNodes[childNodeIndex];
        childNodes[childNodeIndex] = null;
        return disNode;
    }

    //打印当前节点的所有数据项
    public void print() {
        DataItem dataItem;
        for (int i = 0; i < dataItemSize; i++) {
            dataItem = dataItems[i];
            System.out.print(dataItem.getData() + " ");
        }
        System.out.println();
    }

    //拿到当前节点指定索引的子节点
    public Node getChild(int childNodeIndex) {
        return childNodes[childNodeIndex];
    }

    // 判断当前节点是否已满
    public boolean isFull() {
        return dataItemSize == 3;
    }

    //给出一个数据项返回此数据项在节点中的位置
    public int getDataItemIndex(int data) {
        for (int i = 0; i < dataItemSize; i++) {
            if (dataItems[i].getData() == data) {
                return i;
            }
        }
        return -1;
    }

    //删除并返回尾部子节点
    public DataItem removeTailChild() {
        DataItem delDataItem = dataItems[dataItemSize - 1];
        dataItems[--dataItemSize] = null;
        return delDataItem;
    }

    //插入
    public int insert(int data) {
        DataItem newDataItem = new DataItem(data);
        if (dataItemSize == 0) {
            dataItems[dataItemSize] = newDataItem;
            return dataItemSize++;
        }
        if (isFull()) {
            return -1;
        }
        for (int i = 2; i >= 0; i--) {
            if (dataItems[i] == null) {
                continue;
            } else {
                if (data < dataItems[i].getData()) {
                    dataItems[i + 1] = dataItems[i];
                    dataItems[i] = newDataItem;
                    dataItemSize++;
                    return i;
                } else {
                    dataItems[i + 1] = newDataItem;
                    dataItemSize++;
                    return i + 1;
                }
            }
        }
        return -1;
    }

}


