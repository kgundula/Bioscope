package za.co.gundula.app.bioscope.model;

/**
 * Created by kgundula on 16/07/28.
 */
public class MovieTrailer {

    private String name;
    private String size;
    private String source;
    private String type;

    public MovieTrailer() {
    }

    public MovieTrailer(String name, String size, String source, String type) {
        this.name = name;
        this.size = size;
        this.source = source;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
