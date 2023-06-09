package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * Photo class.
 * <p>
 * This class represents a Photo.
 * A Photo is composed by a URI and a name.
 */

public class Photo implements Serializable {
    private String URI;
    private String name;

    /**
     * Instantiates a new Photo.
     *
     * @param URI      the uri
     * @param fileName the file name
     */
    public Photo(String URI, String fileName) {
        this.URI = URI;
        this.name = fileName;
    }
    /**
     * Gets uri.
     *
     * @return the uri
     */
    public String getURI() {
        return URI;
    }
    /**
     * Sets uri.
     *
     * @param URI the uri
     */
    public void setURI(String URI) {
        this.URI = URI;
    }
    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return name;
    }
    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        this.name = fileName;
    }
}
