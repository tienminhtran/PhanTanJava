package entity;

public class Song {
//    id	name	runtime	lyric	fileLink
    private String id;
    private String name;
    private String runtime;
    private String lyric;
    private String fileLink;

    public Song() {
    }

    public Song(String id, String name, String runtime, String lyric, String fileLink) {
        this.id = id;
        this.name = name;
        this.runtime = runtime;
        this.lyric = lyric;
        this.fileLink = fileLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", runtime='" + runtime + '\'' +
                ", lyric='" + lyric + '\'' +
                ", fileLink='" + fileLink + '\'' +
                '}';
    }
}
