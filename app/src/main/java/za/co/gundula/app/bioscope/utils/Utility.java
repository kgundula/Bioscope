package za.co.gundula.app.bioscope.utils;

/**
 * Contains utility methods.
 */
public class Utility {

    /**
     * Calculates a rating on a 5-star scale.
     *
     * @param ratings The rating to convert.
     * @return The rating on a 5-star scale.
     */
    public static float calculateRating(float ratings) {
        return (ratings / 10) * 5;
    }
}
