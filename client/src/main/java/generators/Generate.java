package generators;

import defaultClasses.Generated;

import java.io.IOException;

/**
 * FunctionalInterface that allows a class to create a defaultClass
 *
 * @author Dmitrii Chebanenko
 */
@FunctionalInterface
public interface Generate {
    /**
     * Method to override
     */
    Generated generate();
}
