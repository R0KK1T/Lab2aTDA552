/**
 * Represents an object able to store objects
 */
public interface Storage<T> {

    /**
     * stores an object
     * @param t object to store
     */
    void load(T t);

    /**
     * releases a stored object
     * @param t object to release
     */
    void unload(T t);
}
