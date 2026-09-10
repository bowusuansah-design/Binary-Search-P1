/**
 * Defines an abstract data type for a collection of comparable values.
 * @param <T> the type of values stored in the collection
 */
public interface SortedCollection<T extends Comparable<T>> {

    /**
     * Adds a value to the collection.
     * @param data the value to add
     * @throws NullPointerException if {@code data} is null
     */
    void add(T data) throws NullPointerException;

    /**
     * Determines whether the collection contains a value equivalent to the
     * specified value.
     *
     * @param find the value to search for
     * @return true if the collection contains the specified value; otherwise,
     *         false
     */
    boolean contains(Comparable<T> find);

    /**
     * Returns the number of values currently stored in the collection.
     *
     * @return the collection size
     */
    int size();

    /**
     * Determines whether the collection contains no values.
     *
     * @return true if the collection is empty; otherwise, false
     */
    boolean isEmpty();

    /**
     * Removes all values from the collection.
     */
    void clear();
}