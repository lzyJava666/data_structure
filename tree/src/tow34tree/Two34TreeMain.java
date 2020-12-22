package tow34tree;

import java.util.Arrays;

/**
 * 234树实例
 */
public class Two34TreeMain {
    public static void main(String[] args) {
        MyTwo34Tree tree=new MyTwo34Tree();

    }
}

/**
 * 234树实现类
 */
class MyTwo34Tree{
    // 根节点
    private Node root;

    //查找节点
    public Node find(int data){
        Node currentNode=root;
        while (true){
            if(currentNode.getDataItemIndex(data)==-1){
                //当前节点没有找到数据
                if(currentNode.isLeafChild()){
                    //当前节点为叶节点
                    return null;
                }else{
                    //不是叶节点
                    for (int i = 0; i < 3; i++) {
                        if(currentNode.getDataItems()[i].getData()>data){
                            currentNode=currentNode.getChildNodes()[i];
                        }
                    }
                    currentNode=currentNode.getChildNodes()[3];
                }
            }else{
                return currentNode;
            }
        }
    }
}

/**
 * 数据项封装类
 */
class DataItem{
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
class Node{
    //父节点
    private Node parentNode;
    //存放数据项的数组
    private DataItem[] dataItems=new DataItem[3];
    //存放子节点数组
    private Node[] childNodes=new Node[4];
    //记录当前数据项个数
    private int dataItemSize;

    public Node() {
        dataItemSize=0;
        parentNode=null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "parentNode=" + parentNode +
                ", dataItems=" + Arrays.toString(dataItems) +
                ", sonNodes=" + Arrays.toString(childNodes) +
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
    public boolean isLeafChild(){
        return childNodes.length==0;
    }

    //返回当前节点数据项个数
    public int getSize(){
        return dataItemSize;
    }

    /**
     * 连接子节点
     * @param childNodeIndex：存放子节点数组的索引值
     * @param myNode 目标子节点
     */
    public void connectChild(int childNodeIndex,Node myNode){
        childNodes[childNodeIndex]=myNode;
        myNode.setParentNode(this);
    }

    /**
     * 断开子节点
     * @param childNodeIndex 断开目标的索引
     */
    public Node disconnectChild(int childNodeIndex){
        Node disNode=childNodes[childNodeIndex];
        disNode.setParentNode(null);
        childNodes[childNodeIndex]=null;
        return disNode;
    }

    //打印当前节点的所有数据项
    public void print(){
        DataItem dataItem;
        for (int i = 0; i < dataItemSize; i++) {
            dataItem=dataItems[i];
            System.out.print(dataItem.getData()+" ");
        }
        System.out.println();
    }

    //拿到当前节点指定索引的子节点
    public Node getChild(int childNodeIndex){
        return childNodes[childNodeIndex];
    }

    // 判断当前节点是否已满
    public boolean isFull(){
        return dataItemSize==3;
    }

    //给出一个数据项返回此数据项在节点中的位置
    public int getDataItemIndex(int data){
        for (int i = 0; i < dataItemSize; i++) {
            if(dataItems[i].getData()==data){
                return i;
            }
        }
        return -1;
    }

    //插入
    public void insert(int data){
        DataItem newDataItem=new DataItem(data);
        if(dataItemSize==0){
            dataItems[dataItemSize]=newDataItem;
            dataItemSize++;
        }
        if(isFull()){
            return;
        }
        for (int i = 2; i >=0; i--) {
            if(dataItems[i]==null){
                continue;
            }else{
                if(data<dataItems[i].getData()){
                    dataItems[i+1]=dataItems[i];
                    dataItems[i]=newDataItem;
                }else{
                    dataItems[i+1]=newDataItem;
                }
                dataItemSize++;
            }
        }
    }

}


