package net.zarski.dojo.webstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String sessionId;


    public Cart(@JsonProperty("sessionId") String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }
}
