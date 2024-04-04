package com.jira.connector.valueobject;

public class Issue {
    private String key;
    private String status;
    private String description;
    
    public Issue() {}
    
    public Issue(String key, String status, String description) {
        this.key = key;
        this.status = status;
        this.description = description;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
