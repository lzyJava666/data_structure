package rbtree;

/**
 * 红黑树实例
 */
public class RBTreeMain {
    public static void main(String[] args) {

    }
}

/**
 * 红黑树节点类
 */
class Node{
    //具体数据
    private int data;
    //左子节点
    private Node leftChild;
    //右字节点
    private Node rightChild;
    //父节点
    private Node parentChild;
    //颜色 true 红色，false：黑色
    private boolean color;

    public Node(int data) {
        data=this.data;
    }

    public Node(int data,boolean color) {
        data=this.data;
        color=this.color;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                ", parentChild=" + parentChild +
                ", color=" + color +
                '}';
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getParentChild() {
        return parentChild;
    }

    public void setParentChild(Node parentChild) {
        this.parentChild = parentChild;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}

class MyRBTree{
    //根节点
    private Node root;

    public Node getRoot() {
        return root;
    }

    //左旋
    public void leftRotate(Node x){
        Node y=x.getRightChild();
        //1.将y的左节点变成x的右节点
        x.setRightChild(y.getLeftChild());
        if(y.getLeftChild()!=null){
            //当Y的左节点有值时,将其父节点设为x
            y.getLeftChild().setParentChild(x);
        }
        //2.将x的父节点设为y的父节点
        y.setParentChild(x.getParentChild());
        if(x==root){
            //当x为根节点的时候
            root=y;
        }else{
            if(x==x.getParentChild().getLeftChild()){
                //当x等于父节点的左节点时
                x.getParentChild().setLeftChild(y);
            }else{
                x.getParentChild().setRightChild(y);
            }
        }
        //3.将y的左节点变为x，x的父节点变为y
        y.setLeftChild(x);
        x.setParentChild(y);
    }

    //右旋
    public void rightRotate(Node y){
        Node x=y.getLeftChild();
        //1.将x的右节点变为y的左节点
        y.setLeftChild(x.getRightChild());
        if(x.getRightChild()!=null){
            x.getRightChild().setParentChild(y);
        }
        //2.将y的父节点设为x的父节点
        x.setParentChild(y.getParentChild());
        if(y==root){
            root=x;
        }else{
            if(y==y.getParentChild().getLeftChild()){
                y.getParentChild().setLeftChild(x);
            }else{
                y.getParentChild().setRightChild(x);
            }
        }
        //3.将x的右节点变为y，y的父节点变为x
        x.setRightChild(y);
        y.setParentChild(x);
    }



}