package rbtree;


/**
 * 红黑树实例
 */
public class RBTreeMain {
    public static void main(String[] args) {
        MyRBTree tree = new MyRBTree();
        //tree.insert(10);
        //tree.insert(8);
        //tree.insert(5);
        //tree.insert(9);
        //tree.insert(2);
        //tree.insert(7);
        //tree.insert(12);
        //tree.insert(11);
        //tree.insert(22);
        //tree.insert(19);
        //tree.insert(14);
        //tree.insert(20);
        //tree.insert(35);
        //tree.insert(30);
        //tree.insert(40);
        //tree.insert(120);
        tree.insert(10);
        tree.insert(20);
        tree.insert(39);
        tree.insert(41);
        tree.insert(55);
        tree.insert(77);
        tree.midForEach(tree.getRoot());

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
        this.data=data;
        this.color=color;
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

    //判断是否为空树
    public boolean isEmpty(){
        return root==null;
    }

    //插入
    public void insert(int data){
        Node newNode=new Node(data,true);
        //当前节点
        Node currentNode=root;
        //当前节点的父节点
        Node parentCurrentNode=root;
        if(isEmpty()){
            //空树
            root=newNode;
        }else{
            //标记是否为左节点
            boolean isLeftChild=false;
            while (currentNode!=null) {
                parentCurrentNode = currentNode;
                if (currentNode.getData() == data) {
                    return;
                } else if (currentNode.getData() > data) {
                    currentNode = currentNode.getLeftChild();
                    isLeftChild=true;
                } else {
                    currentNode = currentNode.getRightChild();
                    isLeftChild=false;
                }

            }
            currentNode=newNode;
            currentNode.setParentChild(parentCurrentNode);
            if(isLeftChild){
                parentCurrentNode.setLeftChild(currentNode);
            }else{
                parentCurrentNode.setRightChild(currentNode);
            }
        }
        //插入可能会使整个结构违反红黑规则，此方法做对应调整
        RBAdjustment(currentNode);
    }

    /**
     * 对插入的红黑规则进行调整
     * @param newNode 插入节点
     */
    private void RBAdjustment(Node newNode) {
        //父节点
        Node parentNode;
        //爷爷节点
        Node parentParentNode;
        //叔叔节点
        Node uncleNode;
        if(newNode==null){
            root.setColor(false);
            return;
        }

        while (newNode.getParentChild()!=null&&newNode.getParentChild().getColor()){
            //父节点赋值
            parentNode=newNode.getParentChild();
            //爷爷节点赋值
            parentParentNode=parentNode.getParentChild();
            //叔叔节点赋值
            if(parentParentNode.getLeftChild()==parentNode){
                uncleNode=parentParentNode.getRightChild();
            }else{
                uncleNode=parentParentNode.getLeftChild();
            }

            if(uncleNode!=null&&uncleNode.getColor()){
                //父节点和叔叔节点为红色时，处理
                uncleNode.setColor(false);
                parentNode.setColor(false);
                parentParentNode.setColor(true);
                newNode=parentParentNode;
                continue;
            }else{
                //父节点为红色，叔叔节点为黑色
                if(parentParentNode.getLeftChild()==parentNode){
                    //父节点为爷爷节点的左节点
                    Node temp=newNode;
                    newNode=parentNode;
                    parentNode=temp;
                    newNode.setParentChild(parentNode);
                    parentNode.setParentChild(parentParentNode);
                    parentParentNode.setLeftChild(parentNode);
                    leftRotate(newNode);
                    //此方法执行后会变成下面的情况
                }
                if(parentParentNode.getRightChild()==parentNode){
                    //父节点为爷爷节点的右节点
                    parentNode.setColor(false);
                    parentParentNode.setColor(true);
                    leftRotate(parentParentNode);
                }
            }

        }
        //循环完后吧root节点设为hose
        root.setColor(false);

    }

    //中序遍历 左子树 --> 根节点 --> 右子树
    public void midForEach(Node currentNode) {
        if (isEmpty()) {
            System.out.println("空树，遍历无效");
            return;
        } else if (currentNode != null) {
            midForEach(currentNode.getLeftChild());
            System.out.print(currentNode.getData() + " ");
            midForEach(currentNode.getRightChild());
        } else {
            return;
        }
    }

    //前序遍历 根节点  -->左子树 --> 右子树
    public void preForEach(Node currentNode) {
        if (isEmpty()) {
            System.out.println("空树，遍历无效");
            return;
        } else if (currentNode != null) {
            System.out.print(currentNode.getData() + " ");
            midForEach(currentNode.getLeftChild());
            midForEach(currentNode.getRightChild());
        } else {
            return;
        }
    }

    //后序遍历 左子树 --> 右子树-->根节点
    public void tailForEach(Node currentNode) {
        if (isEmpty()) {
            System.out.println("空树，遍历无效");
            return;
        } else if (currentNode != null) {
            midForEach(currentNode.getLeftChild());
            midForEach(currentNode.getRightChild());
            System.out.print(currentNode.getData() + " ");
        } else {
            return;
        }
    }

    //查找
    public Node find(int data){
        if(isEmpty()){
            System.out.println("空树");
            return null;
        }else{
            //当前节点
            Node currentNode=root;
            while (currentNode!=null){
                if(data>currentNode.getData()){
                    currentNode=currentNode.getRightChild();
                }else if(data<currentNode.getData()){
                    currentNode=currentNode.getLeftChild();
                }else{
                    return currentNode;
                }
            }
            System.out.println("找不到节点");
            return null;
        }
    }

    //删除指定数据
    public boolean delete(int data) {
        //当前节点
        Node currentNode = root;
        if (isEmpty()) {
            return false;
        } else {
            Node delNode = new Node(data);
            // 判断是否为左节点
            boolean isLeftChild = false;
            //父节点
            Node preCurrentNode=root;
            while (currentNode != null) {
                if (data == currentNode.getData()) {
                    //找到节点
                    if (currentNode.getRightChild() == null && currentNode.getLeftChild() == null) {
                        //没有分支情况
                        if (currentNode == root) {
                            root = null;
                            return true;
                        }
                        if (isLeftChild) {
                            preCurrentNode.setLeftChild(null);
                        } else {
                            preCurrentNode.setRightChild(null);
                        }
                        return true;
                    } else if (currentNode.getLeftChild() == null && currentNode.getRightChild() != null) {
                        //当前节点有右分支
                        if (currentNode == root) {
                            //当前节点为root节点
                            root = currentNode.getRightChild();
                            return true;
                        }
                        if (isLeftChild) {
                            preCurrentNode.setLeftChild(currentNode.getRightChild());
                        } else {
                            preCurrentNode.setRightChild(currentNode.getRightChild());
                        }
                        return true;
                    } else if (currentNode.getLeftChild() != null && currentNode.getRightChild() == null) {
                        //当前节点只有左分支
                        if (currentNode == root) {
                            root = currentNode.getLeftChild();
                            return true;
                        }
                        if (isLeftChild) {
                            preCurrentNode.setLeftChild(currentNode.getLeftChild());
                        } else {
                            preCurrentNode.setRightChild(currentNode.getLeftChild());
                        }
                        return true;
                    } else {
                        //当前节点有二个分支
                        //1.拿到右树上的最小值
                        Node rightMinNode = getRightMinNode(currentNode);
                        if (currentNode == root) {
                            //判断是否为root
                            root = rightMinNode;
                        }
                        //2.将父节点指向最小值节点
                        if (isLeftChild) {
                            preCurrentNode.setLeftChild(rightMinNode);
                        } else {
                            preCurrentNode.setRightChild(rightMinNode);
                        }
                        return true;
                    }
                } else {
                    preCurrentNode = currentNode;
                    //未找到节点
                    if (data > currentNode.getData()) {
                        currentNode = currentNode.getRightChild();
                        isLeftChild = false;
                    } else {
                        currentNode = currentNode.getLeftChild();
                        isLeftChild = true;
                    }
                }
            }
            System.out.println("未找到任何满足条件的节点");
            return false;
        }

    }

    /**
     * 删除后调整红黑规则
     * @param delNode 删除的节点目标
     */
    private void delRBAdjustment(Node delNode) {
        //父节点
        Node parentNode;
        //兄弟节点
        Node brotherNode;
        //侄子节点
        Node nephewNode;
        if(root==null){
            return;
        }
        if(delNode.getColor()){
            //当删除节点为红色节点时，不操作
            return;
        }
    }


    /**拿到右树上的最小值
     * @param myNode 目标节点
     * @return
     */
    private Node getRightMinNode(Node myNode) {
        //最小节点的父节点
        Node preNode = myNode.getRightChild();
        //最小节点
        Node minNode = myNode.getRightChild();
        while (minNode.getLeftChild() != null) {
            preNode = minNode;
            minNode = minNode.getLeftChild();
        }
        //执行到这里就拿到了最小值节点 minNode，及他的父节点preNode
        //1.判断最小节点是否有右节点，有就取代当前节点位置
        if (minNode.getRightChild() == null) {
            preNode.setLeftChild(null);
        } else {
            preNode.setLeftChild(minNode.getRightChild());
        }
        //2.接替删除节点的左右节点
        minNode.setLeftChild(myNode.getLeftChild());
        minNode.setRightChild(minNode.getRightChild());
        return minNode;
    }
}