package cz.cvut.fit.stovija5.tjv.sciarticles.service;

import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorDTO implements Serializable {
    private long id;
    private String name;
    private String surname;
    //private InstituteDTO institute;
    private Set<ArticleDTO> articles;

    public AuthorDTO() {
    }

    public AuthorDTO(long id, String name, String surname, Set<ArticleDTO> articles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        //this.institute = institute;
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

    /*
    public InstituteDTO getInstitute() {
        return institute;
    }

    public void setInstitute(InstituteDTO institute) {
        this.institute = institute;
    }
*/

    public Set<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleDTO> articles) {
        this.articles = articles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final AuthorDTO other = (AuthorDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
