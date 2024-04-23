package com.jira.connector.valueobject;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(key, issue.key) && Objects.equals(status, issue.status) && Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, status, description);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "key='" + key + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
