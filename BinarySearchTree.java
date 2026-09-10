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

        int compare = newNode.data.compareTo(subtree.data);
        if (compare <= 0) {
            if (subtree.left == null) {
                subtree.left = newNode;
                newNode.parent = subtree;
            } else {
                addHelper(newNode, subtree.left);
            }
        } else {
            if (subtree.right == null) {
                subtree.right = newNode;
                newNode.parent = subtree;
            } else {
                addHelper(newNode, subtree.right);
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

        int compare = find.compareTo(subtree.data);
        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            return containsHelper(find, subtree.left);
        } else {
            return containsHelper(find, subtree.right);
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
        return 1 + sizeHelper(subtree.left) + sizeHelper(subtree.right);
    }

    /**
     * Tests insertion, size calculation, and value lookup with integers.
     * @return true if all checks pass
     */
    public boolean test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
         bst.add(8);
         bst.add(4);
         bst.add(12);
         bst.add(2);
         bst.add(6);
         bst.add(10);
         bst.add(14);

         return bst.size() == 7 &&
             bst.contains(8) &&
             bst.contains(4) &&
             bst.contains(2) &&
             bst.contains(14) &&
             !bst.contains(9);
    }

    /**
     * Tests insertion and value lookup with strings.
     * @return true if all checks pass; otherwise, false
     */
    public boolean test2() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
         bst.add("Mango");
         bst.add("Apple");
         bst.add("Peach");
         bst.add("Banana");
         bst.add("Orange");
         bst.add("Pear");

         return bst.size() == 6 &&
             bst.contains("Mango") &&
             bst.contains("Apple") &&
             bst.contains("Banana") &&
             bst.contains("Pear") &&
             !bst.contains("Cherry");
    }

    /**
     * Tests the empty state, size calculation, and clear operation.
     * @return true if all checks pass; otherwise, false
     */
    public boolean test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(5);
        bst.add(15);
        bst.add(25);
        bst.add(35);
        if (bst.isEmpty() || bst.size() != 7) return false;
        if (!bst.contains(20) || !bst.contains(5) || !bst.contains(35)) return false;

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

}