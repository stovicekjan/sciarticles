package cz.cvut.fit.stovija5.tjv.sciarticles.restclient;

import javax.ws.rs.WebApplicationException;

/**
 * An exception that occurs during/after communication with the web service or
 * during inconsistency in data or because of incorrect request.
 */
public class ArticlesClientException extends RuntimeException {
    
    public ArticlesClientException(WebApplicationException cause) {
        super(cause);
    }
}