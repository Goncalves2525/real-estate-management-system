package pt.ipp.isep.dei.esoft.project.domain;

public class Photo {
    private String URI;
    private String name;

    public Photo(String URI, String fileName) {
        this.URI = URI;
        this.name = fileName;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getFileName() {
        return name;
    }

    public void setFileName(String fileName) {
        this.name = fileName;
    }
}
