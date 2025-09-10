package za.co.gundula.app.bioscope.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Represents a movie object.
 *
 * This class is Parcelable to allow it to be passed between Android components.
 */
public class BioScope implements Parcelable {

    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private ArrayList<Integer> genre_ids;
    private int id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;

    /**
     * Default constructor.
     */
    public BioScope() {
    }

    /**
     * Constructs a new BioScope object.
     *
     * @param poster_path       The path to the movie poster.
     * @param adult             Whether the movie is for adults.
     * @param overview          A brief overview of the movie.
     * @param release_date      The release date of the movie.
     * @param genre_ids         A list of genre IDs for the movie.
     * @param id                The unique ID of the movie.
     * @param original_title    The original title of the movie.
     * @param original_language The original language of the movie.
     * @param title             The title of the movie.
     * @param backdrop_path     The path to the movie backdrop image.
     * @param popularity        The popularity score of the movie.
     * @param vote_count        The number of votes the movie has received.
     * @param video             Whether the movie has a video.
     * @param vote_average      The average vote score of the movie.
     */
    public BioScope(String poster_path, boolean adult, String overview, String release_date, ArrayList<Integer> genre_ids, int id, String original_title, String original_language, String title, String backdrop_path, double popularity, int vote_count, boolean video, double vote_average) {
        this.poster_path = poster_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_title = original_title;
        this.original_language = original_language;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.video = video;
        this.vote_average = vote_average;
    }


    /**
     * Gets the path to the movie poster.
     * @return The path to the movie poster.
     */
    public String getPoster_path() {
        return poster_path;
    }

    /**
     * Sets the path to the movie poster.
     * @param poster_path The new path to the movie poster.
     */
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    /**
     * Checks if the movie is for adults.
     * @return true if the movie is for adults, false otherwise.
     */
    public boolean isAdult() {
        return adult;
    }

    /**
     * Sets whether the movie is for adults.
     * @param adult true if the movie is for adults, false otherwise.
     */
    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    /**
     * Gets the overview of the movie.
     * @return The overview of the movie.
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Sets the overview of the movie.
     * @param overview The new overview of the movie.
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * Gets the release date of the movie.
     * @return The release date of the movie.
     */
    public String getRelease_date() {
        return release_date;
    }

    /**
     * Sets the release date of the movie.
     * @param release_date The new release date of the movie.
     */
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    /**
     * Gets the list of genre IDs for the movie.
     * @return The list of genre IDs for the movie.
     */
    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    /**
     * Sets the list of genre IDs for the movie.
     * @param genre_ids The new list of genre IDs for the movie.
     */
    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    /**
     * Gets the unique ID of the movie.
     * @return The unique ID of the movie.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the movie.
     * @param id The new unique ID of the movie.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the original title of the movie.
     * @return The original title of the movie.
     */
    public String getOriginal_title() {
        return original_title;
    }

    /**
     * Sets the original title of the movie.
     * @param original_title The new original title of the movie.
     */
    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    /**
     * Gets the original language of the movie.
     * @return The original language of the movie.
     */
    public String getOriginal_language() {
        return original_language;
    }

    /**
     * Sets the original language of the movie.
     * @param original_language The new original language of the movie.
     */
    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    /**
     * Gets the title of the movie.
     * @return The title of the movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the movie.
     * @param title The new title of the movie.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the path to the movie backdrop image.
     * @return The path to the movie backdrop image.
     */
    public String getBackdrop_path() {
        return backdrop_path;
    }

    /**
     * Sets the path to the movie backdrop image.
     * @param backdrop_path The new path to the movie backdrop image.
     */
    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    /**
     * Gets the popularity score of the movie.
     * @return The popularity score of the movie.
     */
    public double getPopularity() {
        return popularity;
    }

    /**
     * Sets the popularity score of the movie.
     * @param popularity The new popularity score of the movie.
     */
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    /**
     * Gets the number of votes the movie has received.
     * @return The number of votes the movie has received.
     */
    public int getVote_count() {
        return vote_count;
    }

    /**
     * Sets the number of votes the movie has received.
     * @param vote_count The new number of votes the movie has received.
     */
    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    /**
     * Checks if the movie has a video.
     * @return true if the movie has a video, false otherwise.
     */
    public boolean isVideo() {
        return video;
    }

    /**
     * Sets whether the movie has a video.
     * @param video true if the movie has a video, false otherwise.
     */
    public void setVideo(boolean video) {
        this.video = video;
    }

    /**
     * Gets the average vote score of the movie.
     * @return The average vote score of the movie.
     */
    public double getVote_average() {
        return vote_average;
    }

    /**
     * Sets the average vote score of the movie.
     * @param vote_average The new average vote score of the movie.
     */
    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's marshaled representation.
     *
     * @return a bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Constructs a BioScope object from a Parcel.
     *
     * @param in The Parcel to read the object's data from.
     */
    private BioScope(Parcel in) {
        poster_path = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        release_date = in.readString();
        genre_ids = (ArrayList<Integer>) in.readSerializable();
        id = in.readInt();
        original_title = in.readString();
        original_language = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        popularity = in.readDouble();
        vote_count = in.readInt();
        video = in.readByte() != 0;
        vote_average = in.readDouble();

    }

    /**
     * A Creator that can be used to create BioScope objects from a Parcel.
     */
    public static final Creator<BioScope> CREATOR = new Creator<BioScope>() {
        /**
         * Create a new instance of the Parcelable class, instantiating it from the given Parcel whose data had previously been written by Parcelable.writeToParcel().
         *
         * @param in The Parcel to read the object's data from.
         * @return a new instance of the Parcelable class.
         */
        @Override
        public BioScope createFromParcel(Parcel in) {
            return new BioScope(in);
        }

        /**
         * Create a new array of the Parcelable class.
         *
         * @param size Size of the array.
         * @return an array of the Parcelable class, with every entry initialized to null.
         */
        @Override
        public BioScope[] newArray(int size) {
            return new BioScope[size];
        }
    };

    /**
     * Flatten this object in to a Parcel.
     *
     * @param parcel The Parcel in which the object should be written.
     * @param i      Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(poster_path);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeSerializable(genre_ids);
        parcel.writeInt(id);
        parcel.writeString(original_title);
        parcel.writeString(original_language);
        parcel.writeString(title);
        parcel.writeString(backdrop_path);
        parcel.writeDouble(popularity);
        parcel.writeInt(vote_count);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeDouble(vote_average);

    }
}
