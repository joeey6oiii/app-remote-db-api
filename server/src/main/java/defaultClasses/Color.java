package defaultClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A class whose constant is used in the another class.
 * <p>
 * Contains getter and other methods.
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Color implements Generated {
    RED("red"),
    ORANGE("orange"),
    WHITE("white");

    private final String colorName;
    private final static Map<String, Color> colors = Arrays.stream(Color.values()).collect(Collectors.toMap(k->k.colorName, v->v));

    /**
     * Private constructor for naming the constants.
     *
     * @param colorName the name of the color
     */

    Color(String colorName){
        this.colorName = colorName;
    }

    /**
     * @return list of the available colors
     */

    public static ArrayList<Color> listValues(){
        return new ArrayList<>(Arrays.asList(Color.values()));
    }

    /**
     * @param colorName the name of the color
     * @return {@link Color} if list contains the specified name of the color, otherwise null
     */

    public static Color getColorByName (String colorName){
        return colors.get(colorName);
    }

    /**
     * This method is being used by {@link JsonCreator} at the stage of reading from a file and creating objects. It tries
     * to find a color by the specified color name. If there is no matching color by the color name, the value is assigned
     * as null. Example: hairColor=true -> hairColor=null.
     *
     * @param colorName the name of the color
     * @return {@link Color} if list contains the specified name of the color, otherwise null
     */

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    static Color findValue(@JsonProperty("colorName") String colorName) {
        if (Arrays.stream(Color.values()).anyMatch(e -> e.name().equalsIgnoreCase(colorName))) {
            return Arrays.stream(Color.values()).filter(e -> e.name().equalsIgnoreCase(colorName)).findAny().get();
        }
        return null;
    }
}