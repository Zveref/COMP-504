package edu.rice.comp504.util;

import edu.rice.comp504.model.paintobj.Ball;

import java.util.Random;

/**
 * Utility used to generate random numbers.
 */
public class RandUtil {
    /**
     * Generate a random number.
     * @param base  The mininum value
     * @param limit The maximum number from the base
     * @return A randomly generated number
     */
    public static int getRnd(int base, int limit) {
        return (int)Math.floor(Math.random() * limit + base);
    }

    public static String getColor(){
        String[] ColorField = {"red", "blue", "yellow", "green", "black", "purple", "orange", "gray", "brown"};
        int ColorIndex = new Random().nextInt(9);
        return ColorField[ColorIndex];
    }

    public static String changeColor(String color){
        String[] ColorField = {"red", "blue", "yellow", "green", "black", "purple", "orange", "gray", "brown"};
        int ColorIndex = new Random().nextInt(9);
        if(ColorField[ColorIndex].equals(color) && ColorField[ColorIndex].equals("black")){
            return "red";
        }
        return ColorField[ColorIndex].equals(color) ? "black" : ColorField[ColorIndex];
    }

    public static String getCharacter(){
        String[] CharaField = {"normal",  "slimmer", "colorful", "vanish", "hungry","faster", "fatter","slower","still", "fusion"};
        int ColorIndex = new Random().nextInt(9);
        return CharaField[ColorIndex];
    }

    public static boolean getBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
