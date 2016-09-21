package de.greenrobot.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "QUICK_SEARCH".
 */
public class QuickSearch {

    private Long id;
    private java.util.Date add_time;
    /** Not-null value. */
    private String content;

    public QuickSearch() {
    }

    public QuickSearch(Long id) {
        this.id = id;
    }

    public QuickSearch(Long id, java.util.Date add_time, String content) {
        this.id = id;
        this.add_time = add_time;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(java.util.Date add_time) {
        this.add_time = add_time;
    }

    /** Not-null value. */
    public String getContent() {
        return content;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setContent(String content) {
        this.content = content;
    }

}
