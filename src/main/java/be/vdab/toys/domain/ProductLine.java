package be.vdab.toys.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "productlines")
public class ProductLine {
    @Id
    private long id;
    private String name;
    private String description;
    @Version
    private long version;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getVersion() {
        return version;
    }
}
