package com.example.contactlistview.events;

public class ContactDetailEvent {
    private String id;
    private Boolean navigate;

    public ContactDetailEvent(String id, Boolean navigate) {
        this.id = id;
        this.navigate = navigate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getNavigate() {
        return navigate;
    }

    public void setNavigate(Boolean navigate) {
        this.navigate = navigate;
    }
}
