package entity;

import java.time.LocalDate;

public class Aritst {
    //id	name	birthDate	url
    private String id;
    private String name;
    private LocalDate birthDate;
    private String url;

    public Aritst() {
    }

    public Aritst(String id, String name, LocalDate birthDate, String url) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.url = url;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Aritst{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", url='" + url + '\'' +
                '}';
    }
}
