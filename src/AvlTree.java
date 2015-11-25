/**
 * http://www.cise.ufl.edu/~nemo/cop3530/AVL-Tree-Rotations.pdf
 * Created by Martin on 25-11-2015.
 */
public class AvlTree {

    private AvlNode root;

    public AvlTree(){
        root = null;
    }

    public void insert (Comparable newNode){
        root = insert(newNode, root);
    }


    private AvlNode insert(Comparable newNode, AvlNode root){
        if (root == null){
            root = new AvlNode(newNode, null, null);
        } else if (newNode.compareTo(root.getValue()) < 0){
            root.setLeftNode(insert(newNode, root.getLeftNode()));
            if (height(root.getLeftNode()) - height(root.getRightNode()) >= 2){
                if (newNode.compareTo(root.getLeftNode().getValue()) < 0){
                    root = rotateWithLeftChild(root);
                } else {
                    root = doubleWithLeftChild(root);
                }
            }
        }else if (newNode.compareTo(root.getValue()) > 0){
            root.setRightNode(insert(newNode, root.getRightNode()));
            if (height(root.getRightNode()) - height(root.getLeftNode()) >= 2){
                if (newNode.compareTo(root.getRightNode().getValue()) > 0){
                    root = rotateWithRightChild(root);
                } else {
                    root = doubleWithRightChild(root);
                }
            }
        }
        root.setHeight(Math.max(height(root.getLeftNode()), height(root.getRightNode())) + 1);
        return root;
    }

    private int height(AvlNode node){
        return node == null ? -1 : node.getHeight();
    }

    private AvlNode rotateWithLeftChild(AvlNode rootNode){
        AvlNode chilNode = rootNode.getLeftNode();
        rootNode.setLeftNode(chilNode.getRightNode());
        chilNode.setRightNode(rootNode);
        rootNode.setHeight(Math.max(height(rootNode.getLeftNode()), height(rootNode.getRightNode())) + 1);
        chilNode.setHeight(Math.max(height(chilNode.getLeftNode()), rootNode.getHeight()) + 1);
        return chilNode;
    }

    private AvlNode rotateWithRightChild(AvlNode rootNode){
        AvlNode childNode = rootNode.getRightNode();
        rootNode.setRightNode(childNode.getLeftNode());
        childNode.setLeftNode(rootNode);
        rootNode.setHeight(Math.max(height(rootNode.getLeftNode()), height(rootNode.getRightNode())) + 1);
        childNode.setHeight(Math.max(height(childNode.getRightNode()), rootNode.getHeight()) + 1);
        return childNode;
    }

    private AvlNode doubleWithLeftChild(AvlNode node){
        node.setLeftNode(rotateWithRightChild(node.getLeftNode()));
        return rotateWithLeftChild(node);
    }

    private AvlNode doubleWithRightChild(AvlNode node){
        node.setRightNode(rotateWithLeftChild(node.getRightNode()));
        return rotateWithRightChild(node);
    }

    /*public void printTree(){
        if(this.root == null){
            System.out.println("Empty tree");
        } else {
            printTree(this.root);
        }
    }

    private void printTree(AvlNode node){
        if (node != null){
            printTree(node.getLeftNode());
            System.out.println(node.getValue());
            printTree(node.getRightNode());
        }
    }*/

    public String printTree(){
        StringBuilder stringBuilder = new StringBuilder();
        printTree(this.root, "", stringBuilder);
        return stringBuilder.toString();
    }

    private void printTree(AvlNode node, String indent, StringBuilder stringBuilder){
        stringBuilder.append(node.getValue() + "\n");
        if (node.getLeftNode() != null){
            if (node.getRightNode() == null){
                printLastChild(node.getLeftNode(), indent, stringBuilder);
            } else {
                printNormalChild(node.getLeftNode(), indent, stringBuilder);
                printLastChild(node.getRightNode(), indent, stringBuilder);
            }
        }
    }

    private void printLastChild(AvlNode node, String indent, StringBuilder stringBuilder) {
        stringBuilder.append(indent);
        stringBuilder.append("|-");
        stringBuilder.append("--");
        printTree(node, indent + "| ", stringBuilder);
    }

    private void printNormalChild(AvlNode node, String indent, StringBuilder stringBuilder){
        stringBuilder.append(indent);
        stringBuilder.append("|_");
        stringBuilder.append("__");
        printTree(node, "  ", stringBuilder);
    }

    public static void main(String[] args){
        AvlTree tree = new AvlTree();

        tree.insert(1);
        tree.insert(4);
        tree.insert(5);
        tree.insert(15);
        tree.insert(166);
        tree.insert(188);
        tree.insert(12);
        tree.insert(95);
        tree.insert(48);
        tree.insert(156);

        System.out.print(tree.printTree()); // print de tree van links onder naar rechts onder

    }
}
