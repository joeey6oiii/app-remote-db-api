package helpFun;

import java.lang.reflect.InvocationTargetException;

/**
 * A class that allows to interact with object fields using <code>java.lang.reflect</code>.
 */

public class Reflection {

    /**
     * @param field field name
     * @return setter with the specified field name. Example: entering "name" will return "setName"
     */

    public static String castToSetter(String field) {
        return "set" + Character.toUpperCase(field.charAt(0)) + field.substring(1);
    }

    /**
     * @param field field name
     * @return getter with the specified field name. Example: entering "coordinates" will return "getCoordinates"
     */

    public static String castToGetter(String field) {
        return "get" + Character.toUpperCase(field.charAt(0)) + field.substring(1);
    }

    /**
     * Acts like a getter method.
     *
     * @param object the object with whose fields to interact
     * @param field the name of the field to interact with
     * @return the value of the specified field
     */

    public static Object getValue(Object object, String field) {
        field = castToGetter(field);
        Object value = null;
        try {
            value = object.getClass().getMethod(field).invoke(object);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {}
        return value;
    }

    /**
     * Acts like a setter method.
     *
     * @param object the object with whose fields to interact
     * @param field the name of the field to interact with
     * @param fieldClass the class of the field name
     * @param parameter the value to set
     */

    public static void setValue(Object object, String field, Class<?> fieldClass, Object parameter) {
        field = castToSetter(field);
        try {
            object.getClass().getMethod(field, fieldClass).invoke(object, parameter);
        } catch (SecurityException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {}
    }
}