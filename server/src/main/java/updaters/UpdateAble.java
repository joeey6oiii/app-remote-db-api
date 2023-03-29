package updaters;

import defaultClasses.IsUpdateable;

/**
 * An interface to be implemented by an updater.
 *
 * @param <T> an object of type T to update
 */

@FunctionalInterface
public interface UpdateAble<T extends IsUpdateable> {

    /**
     * Provides an update method.
     * <p>
     * Used to update values of an object's fields in the collection.
     *
     * @param object object to update
     */

    void update(T object);
}
