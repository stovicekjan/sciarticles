package cz.cvut.fit.stovija5.tjv.sciarticles.data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class AuthorEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHOR_ID")
    private long id;
    
    @Column(name = "ANAME")
    private String name;
    
    @Column(name = "SURNAME")
    private String surname;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INSTITUTE_ID")
    private InstituteEntity institute;
    
    @ManyToMany(cascade =
        {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "AUTHOR_2_ARTICLE",
            joinColumns = @JoinColumn(name = "AUTHOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "ARTICLE_ID")
    )
    private Set<ArticleEntity> articles;

    public AuthorEntity() {
    }

    public AuthorEntity(long id, String name, String surname, InstituteEntity institute, Set<ArticleEntity> articles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.institute = institute;
        this.articles = articles;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public InstituteEntity getInstitute() {
        return institute;
    }

    public void setInstitute(InstituteEntity institute) {
        this.institute = institute;
    }

    public Set<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleEntity> articles) {
        this.articles = articles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final AuthorEntity other = (AuthorEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
