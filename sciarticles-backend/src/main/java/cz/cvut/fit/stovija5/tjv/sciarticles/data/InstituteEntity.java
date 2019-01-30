package cz.cvut.fit.stovija5.tjv.sciarticles.data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INSTITUTE")
public class InstituteEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "INSTITUTE_ID")
    private long id;
    
    @Column(name = "INAME")
    private String name;
    
    @Column(name = "STREET")
    private String street;
    
    @Column(name = "CITY")
    private String city;
    
    @Column(name = "COUNTRY")
    private String country;
    
    @OneToMany(mappedBy = "institute")
    private Set<AuthorEntity> authors; // https://en.wikibooks.org/wiki/Java_Persistence/ManyToOne
    
    public InstituteEntity() {
    }

    public InstituteEntity(long id, String name, String street, String city, String country, Set<AuthorEntity> authors) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.country = country;
        this.authors = authors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorEntity> authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InstituteEntity other = (InstituteEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
