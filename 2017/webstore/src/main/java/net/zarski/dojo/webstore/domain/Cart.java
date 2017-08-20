package net.zarski.dojo.webstore.domain;

public class Cart {
    private String sessionId;

    public Cart(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }
}
