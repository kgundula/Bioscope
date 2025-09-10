package za.co.gundula.app.bioscope.model;

/**
 * Represents a movie trailer.
 *
 * This class contains information about a movie trailer, including its name, size, source, and type.
 */
public class MovieTrailer {

    private String name;
    private String size;
    private String source;
    private String type;

    /**
     * Default constructor.
     */
    public MovieTrailer() {
    }

    /**
     * Constructs a new MovieTrailer object.
     *
     * @param name   The name of the trailer.
     * @param size   The size of the trailer (e.g., "HD", "SD").
     * @param source The source of the trailer (e.g., a video ID).
     * @param type   The type of the trailer (e.g., "Trailer", "Teaser").
     */
    public MovieTrailer(String name, String size, String source, String type) {
        this.name = name;
        this.size = size;
        this.source = source;
        this.type = type;
    }

    /**
     * Gets the name of the trailer.
     * @return The name of the trailer.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the trailer.
     * @param name The new name of the trailer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the size of the trailer.
     * @return The size of the trailer.
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the trailer.
     * @param size The new size of the trailer.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the source of the trailer.
     * @return The source of the trailer.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of the trailer.
     * @param source The new source of the trailer.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Gets the type of the trailer.
     * @return The type of the trailer.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the trailer.
     * @param type The new type of the trailer.
     */
    public void setType(String type) {
        this.type = type;
    }
}
