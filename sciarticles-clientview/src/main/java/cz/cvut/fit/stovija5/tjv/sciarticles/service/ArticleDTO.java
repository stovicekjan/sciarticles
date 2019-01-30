package cz.cvut.fit.stovija5.tjv.sciarticles.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArticleDTO implements Serializable {
    private long id;
    private String title;
    private Integer issue;
    private Integer page;
    private LocalDateTime published;

    public ArticleDTO() {
    }

    public ArticleDTO(long id, String title, Integer issue, Integer page, LocalDateTime published) {
        this.id = id;
        this.title = title;
        this.issue = issue;
        this.page = page;
        this.published = published;
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

    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final ArticleDTO other = (ArticleDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
