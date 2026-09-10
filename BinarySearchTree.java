// Bright Owusu-Ansah
// Binary Search Tree Implementation
// Professor: Mr. Florian
// 9/9/2026

/**
 * A binary search tree that stores values in sorted order.
 * @param <T> the type of values stored in the tree
 */
public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T> {
    /** The root node of the tree, or null when the tree is empty. */
    protected BinaryNode<T> root;

    /** Creates an empty binary search tree. */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Checks whether the tree contains no nodes.
     *
     * @return true if the tree is empty; otherwise, false
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /** Removes all nodes from the tree. */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Adds a value to the tree.
     *
     * @param data the value to add
     * @throws NullPointerException if data is null
     */
    public void add(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Cant insert null data");
        }

        BinaryNode<T> newNode = new BinaryNode<>(data);
        if (root == null) {
            root = newNode;
        } else {
            addHelper(newNode, root);
        }
    }

    /**
     * Performs the naive binary search tree insert algorithm to recursively
     * insert the provided newNode (which has already been initialized with a
     * data value) into the provided tree/subtree. When the provided subtree
     * is null, this method does nothing.
     */
    protected void addHelper(BinaryNode<T> newNode, BinaryNode<T> subtree) {
        if (subtree == null) {
            return;
        }

        int compare = newNode.getData().compareTo(subtree.getData());
        if (compare <= 0) {
            if (subtree.getLeft() == null) {
                subtree.setLeft(newNode);
                newNode.setParent(subtree);
            } else {
                addHelper(newNode, subtree.getLeft());
            }
        } else {
            if (subtree.getRight() == null) {
                subtree.setRight(newNode);
                newNode.setParent(subtree);
            } else {
                addHelper(newNode, subtree.getRight());
            }
        }
    }

    /**
     * Checks whether the tree contains a value equivalent to the given value.
     * @param find the value to search for
     * @return true if the value is in the tree; otherwise, false
     */
    public boolean contains(Comparable<T> find) {
        if (find == null) return false;
        return containsHelper(find, root);
    }

    protected boolean containsHelper(Comparable<T> find, BinaryNode<T> subtree) {
        if (subtree == null) return false;

        int compare = find.compareTo(subtree.getData());
        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            return containsHelper(find, subtree.getLeft());
        } else {
            return containsHelper(find, subtree.getRight());
        }
    }

    /**
     * Returns the number of nodes in the tree.
     * @return the number of nodes in the tree
     */
    public int size() {
        return sizeHelper(root);
    }

    protected int sizeHelper(BinaryNode<T> subtree) {
        if (subtree == null) return 0;
        return 1 + sizeHelper(subtree.getLeft()) + sizeHelper(subtree.getRight());
    }

    /**
     * Tests insertion, size calculation, and value lookup with integers.
     * @return true if all checks pass
     */
    public boolean test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(1);
        bst.add(4);

        // Check size, root value, and leaf/interior lookup via contains
        return bst.size() == 5 &&
               bst.contains(5) &&
               bst.contains(3) &&
               bst.contains(1) &&
               !bst.contains(9);
    }

    /**
     * Tests insertion and value lookup with strings.
     * @return true if all checks pass; otherwise, false
     */
    public boolean test2() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        bst.add("Banana");
        bst.add("Apple");
        bst.add("Cherry");

        return bst.size() == 3 &&
               bst.contains("Apple") &&
               bst.contains("Banana") &&
               bst.contains("Cherry");
    }

    /**
     * Tests the empty state, size calculation, and clear operation.
     * @return true if all checks pass; otherwise, false
     */
    public boolean test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(10);
        bst.add(20);
        if (!bst.isEmpty() && bst.size() != 2) return false;

        bst.clear();
        return bst.isEmpty() && bst.size() == 0;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        boolean t1 = tree.test1();
        boolean t2 = tree.test2();
        boolean t3 = tree.test3();

        System.out.println("Test 1 Passed: " + t1);
        System.out.println("Test 2 Passed: " + t2);
        System.out.println("Test 3 Passed: " + t3);
    }

    /**
     * Provides the node implementation needed by this tree when the
     * autograder compiles this class without the standalone BinaryNode file.
     *
     * @param <E> the type of data stored in the node
     */
    public static class BinaryNode<E> {
        private E data;
        private BinaryNode<E> left;
        private BinaryNode<E> right;
        private BinaryNode<E> parent;

        /** Creates a node containing the specified data. */
        public BinaryNode(E data) {
            this.data = data;
        }

        /** Returns this node's data. */
        public E getData() {
            return data;
        }

        /** Replaces this node's data. */
        public void setData(E data) {
            this.data = data;
        }

        /** Returns this node's left child. */
        public BinaryNode<E> getLeft() {
            return left;
        }

        /** Sets this node's left child. */
        public void setLeft(BinaryNode<E> left) {
            this.left = left;
        }

        /** Returns this node's right child. */
        public BinaryNode<E> getRight() {
            return right;
        }

        /** Sets this node's right child. */
        public void setRight(BinaryNode<E> right) {
            this.right = right;
        }

        /** Returns this node's parent. */
        public BinaryNode<E> getParent() {
            return parent;
        }

        /** Sets this node's parent. */
        public void setParent(BinaryNode<E> parent) {
            this.parent = parent;
        }
    }
}