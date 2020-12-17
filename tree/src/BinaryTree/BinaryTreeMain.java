package BinaryTree;

/**
 * 二叉树实例
 */
public class BinaryTreeMain {
    public static void main(String[] args) {
        MyBinaryTree tree = new MyBinaryTree();
        tree.insert(10);
        tree.insert(8);
        tree.insert(5);
        tree.insert(9);
        tree.insert(2);
        tree.insert(7);
        tree.insert(12);
        tree.insert(11);
        tree.insert(22);
        tree.insert(19);
        tree.insert(14);
        tree.insert(20);
        tree.insert(35);
        tree.insert(30);
        tree.insert(40);
        tree.insert(120);
        tree.midForEach(tree.getRoot());
        System.out.println();
        System.out.println(tree.delete(19));
        tree.midForEach(tree.getRoot());
        System.out.println();
        System.out.println(tree.find(51));
        System.out.println(tree.find(555));
    }
}

/**
 * 二叉树的节点类
 */
 class Node {
    //具体数据
    private int data;
    //指向的左节点
    private Node leftChild;
    //指向的右节点
    private Node rightChild;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
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
}

/**
 * 二叉树实现类
 */
class MyBinaryTree {
    //根节点
    private Node root;

    public Node getRoot() {
        return root;
    }

    //判断是否为空树
    public boolean isEmpty() {
        return root == null;
    }

    //新增节点
    public boolean insert(int data) {
        //新增的节点
        Node newNode = new Node(data);
        if (isEmpty()) {
            //空树
            root = newNode;
            return true;
        } else {
            //不是空树,定义一个当前节点
            Node currentNode = root;
            // 定义一个当前节点的父节点
            Node preNode;
            while (currentNode != null) {
                preNode = currentNode;
                if (data > currentNode.getData()) {
                    //新增节点大于当前节点
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        preNode.setRightChild(newNode);
                        return true;
                    }
                } else if (data < currentNode.getData()) {
                    //新增节点小于当前节点
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        preNode.setLeftChild(newNode);
                        return true;
                    }
                } else {
                    //新增节点等于当前节点
                    System.out.println("树中已存在当前数据");
                    return false;
                }
            }
        }
        System.out.println("新增时发生未知错误");
        return false;
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
    public Node find(int data) {
        if (isEmpty()) {
            System.out.println("空树");
            return null;
        } else {
            //定义一个当前节点
            Node currentNode = root;
            while (currentNode != null) {
                if (data > currentNode.getData()) {
                    currentNode = currentNode.getRightChild();
                } else if (data < currentNode.getData()) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    return currentNode;
                }
            }
            System.out.println("找不到数据对应节点");
            return null;
        }
    }

    //删除指定数据
    public boolean delete(int data) {
        if (isEmpty()) {
            return false;
        } else {
            Node delNode = new Node(data);
            //当前节点
            Node currentNode = root;
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
