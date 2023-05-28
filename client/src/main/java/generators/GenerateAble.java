package generators;

import defaultClasses.Generated;

/**
 * FunctionalInterface that allows a class to create a defaultClass.
 *
 * @author Dmitrii Chebanenko
 */

@FunctionalInterface
public interface GenerateAble {

    /**
     * Method to override.
     */

    Generated generate();
}
