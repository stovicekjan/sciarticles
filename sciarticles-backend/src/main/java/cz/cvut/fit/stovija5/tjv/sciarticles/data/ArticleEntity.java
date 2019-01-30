package cz.cvut.fit.stovija5.tjv.sciarticles.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ARTICLE")
public class ArticleEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTICLE_ID")
    private long id;
    
    @Column(name = "TITLE")
    private String title;
    
    @Column(name = "ISSUE")
    private Integer issue;
    
    @Column(name = "PAGE")
    private Integer page;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PUBLISHED")
    private Date published;

    @ManyToMany(mappedBy="articles", cascade =
        {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<AuthorEntity> authors;

    public ArticleEntity() {
    }

    public ArticleEntity(long id, String title, Integer issue, Integer page, Date published, Set<AuthorEntity> authors) {
        this.id = id;
        this.title = title;
        this.issue = issue;
        this.page = page;
        this.published = published;
        this.authors = authors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Set<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorEntity> authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ArticleEntity other = (ArticleEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    


}
