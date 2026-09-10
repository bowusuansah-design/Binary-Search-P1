/**
 * Represents one node in a binary search tree.
 *
 * @param <T> the type of data stored in the node
 */
public class BinaryNode<T> {
    /** The value stored in this node. */
    T data;
    /** The left child of this node, or null when there is no left child. */
    BinaryNode<T> left;
    /** The right child of this node, or null when there is no right child. */
    BinaryNode<T> right;
    /** The parent of this node, or null when this node is the root. */
    BinaryNode<T> parent;

    /**
     * Creates a node containing the specified value.
     * @param data the value to store in the node
     */
    public BinaryNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    /**
     * Returns the value stored in this node.
     * @return the node's data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the value stored in this node.
     * @param data the new value for the node
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns the left child of this node.
     * @return the left child, or null if none exists
     */
    public BinaryNode<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child of this node.
     * @param left the node to use as the left child
     */
    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    /**
     * Returns the right child of this node.
     * @return the right child, or null if none exists
     */
    public BinaryNode<T> getRight() {
        return right;
    }

    /**
     * Sets the right child of this node.
     * @param right the node to use as the right child
     */
    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }

    /**
     * Returns the parent of this node.
     * @return the parent node, or null if this node is the root
     */
    public BinaryNode<T> getParent() {
        return parent;
    }

    /**
     * Sets the parent of this node.
     * @param parent the node to use as the parent
     */
    public void setParent(BinaryNode<T> parent) {
        this.parent = parent;
    }
}
