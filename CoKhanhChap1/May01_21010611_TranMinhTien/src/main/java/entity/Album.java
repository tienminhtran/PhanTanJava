package entity;

public class Album {
    //id	title	price	yearOfRelease	downloadLink	genre_id
    private String id;
    private String title;
    private double price;
    private int yearOfRelease;
    private String downloadLink;

    public Album() {
    }

    public Album(String id, String title, double price, int yearOfRelease, String downloadLink) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.yearOfRelease = yearOfRelease;
        this.downloadLink = downloadLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", yearOfRelease=" + yearOfRelease +
                ", downloadLink='" + downloadLink + '\'' +
                '}';
    }
}
