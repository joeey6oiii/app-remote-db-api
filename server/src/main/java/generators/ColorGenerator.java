package generators;

import defaultClasses.Color;
import defaultClasses.Generated;

import java.util.Scanner;

/**
 * A class that allows to create a constant of the {@link Color} class.
 */

public class ColorGenerator implements Generate {

    /**
     * List the values of the {@link Color} enum and asks to input color name
     *
     * @return color from the {@link Color} enum by its name
     */

    @Override
    public Generated generate() {
        System.out.print("Available colors: " + Color.listValues() + "\nInput color: Color (nullable)\n$ ");
        return Color.getColorByName(new Scanner(System.in).nextLine());
    }
}
