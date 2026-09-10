import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

        int compare = nodeData(newNode).compareTo(nodeData(subtree));
        if (compare <= 0) {
            if (nodeLeft(subtree) == null) {
                setNodeLeft(subtree, newNode);
                setNodeParent(newNode, subtree);
            } else {
                addHelper(newNode, nodeLeft(subtree));
            }
        } else {
            if (nodeRight(subtree) == null) {
                setNodeRight(subtree, newNode);
                setNodeParent(newNode, subtree);
            } else {
                addHelper(newNode, nodeRight(subtree));
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

        int compare = find.compareTo(nodeData(subtree));
        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            return containsHelper(find, nodeLeft(subtree));
        } else {
            return containsHelper(find, nodeRight(subtree));
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
        return 1 + sizeHelper(nodeLeft(subtree)) + sizeHelper(nodeRight(subtree));
    }

    /** Reads a node value from either its getter or its data field. */
    @SuppressWarnings("unchecked")
    protected T nodeData(BinaryNode<T> node) {
        return (T) readNodeMember(node, "getData", "data");
    }

    /** Reads a node's left child from either its getter or its left field. */
    protected BinaryNode<T> nodeLeft(BinaryNode<T> node) {
        return (BinaryNode<T>) readNodeMember(node, "getLeft", "left");
    }

    /** Reads a node's right child from either its getter or its right field. */
    protected BinaryNode<T> nodeRight(BinaryNode<T> node) {
        return (BinaryNode<T>) readNodeMember(node, "getRight", "right");
    }

    /** Sets a node's left child through its setter or left field. */
    protected void setNodeLeft(BinaryNode<T> node, BinaryNode<T> child) {
        writeNodeMember(node, child, "setLeft", "left");
    }

    /** Sets a node's right child through its setter or right field. */
    protected void setNodeRight(BinaryNode<T> node, BinaryNode<T> child) {
        writeNodeMember(node, child, "setRight", "right");
    }

    /** Sets a node's parent through its setter or parent field. */
    protected void setNodeParent(BinaryNode<T> node, BinaryNode<T> parent) {
        writeNodeMember(node, parent, "setParent", "parent");
    }

    protected Object readNodeMember(BinaryNode<T> node, String methodName, String fieldName) {
        try {
            Method method = findMethod(node.getClass(), methodName);
            if (method != null) {
                method.setAccessible(true);
                return method.invoke(node);
            }
            Field field = findField(node.getClass(), fieldName);
            field.setAccessible(true);
            return field.get(node);
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Unable to read BinaryNode member", exception);
        }
    }

    protected void writeNodeMember(BinaryNode<T> node, BinaryNode<T> value,
                                   String methodName, String fieldName) {
        try {
            Method method = findMethod(node.getClass(), methodName);
            if (method != null) {
                method.setAccessible(true);
                method.invoke(node, value);
                return;
            }
            Field field = findField(node.getClass(), fieldName);
            field.setAccessible(true);
            field.set(node, value);
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Unable to write BinaryNode member", exception);
        }
    }

    protected Method findMethod(Class<?> nodeClass, String methodName) {
        for (Class<?> current = nodeClass; current != null; current = current.getSuperclass()) {
            for (Method method : current.getDeclaredMethods()) {
                if (method.getName().equals(methodName) && method.getParameterCount() == 0) {
                    return method;
                }
                if (method.getName().equals(methodName) && method.getParameterCount() == 1) {
                    return method;
                }
            }
        }
        return null;
    }

    protected Field findField(Class<?> nodeClass, String fieldName) {
        for (Class<?> current = nodeClass; current != null; current = current.getSuperclass()) {
            try {
                return current.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
                // Continue searching parent classes.
            }
        }
        throw new IllegalStateException("BinaryNode member not found: " + fieldName);
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