package java;

import java.io.Serializable;
import java.util.Objects;

/**
 * Клас у якому описується базова станція, якогось транспорту
 */
public class Station implements Serializable {
    private int Id;
    private String name;

    public Station(){
        name = "";
    }

    public Station(String name, int id) {
        assert (!Objects.equals(name, ""));
        this.name = name;
        Id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }
}
