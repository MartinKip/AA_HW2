/**
 * Created by Martin on 25-11-2015.
 */
public class AvlNode {

    private Comparable value;
    private AvlNode leftNode;
    private AvlNode rightNode;
    private int height;

    public AvlNode(Comparable value){
        this(value, null, null);
    }

    public AvlNode(Comparable value, AvlNode leftNode, AvlNode rightNode){
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.height = 0;
    }

    public Comparable getValue(){
        return this.value;
    }

    public AvlNode getLeftNode() {
        return this.leftNode;
    }

    public void setLeftNode(AvlNode leftNode){
        this.leftNode = leftNode;
    }

    public AvlNode getRightNode(){
        return this.rightNode;
    }

    public void setRightNode(AvlNode rightNode){
        this.rightNode = rightNode;
    }

    public int getHeight(){
        return this.height;
    }

    public void setHeight(int height){
        this.height = height;
    }

}
