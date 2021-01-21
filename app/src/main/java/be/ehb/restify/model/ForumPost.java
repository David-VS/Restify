package be.ehb.restify.model;

import be.ehb.restify.R;

public class ForumPost {

    private int userId, id;
    private String title, body;
    private int specialisation;

    public ForumPost() {
    }

    public ForumPost(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.specialisation = R.string.specialisation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(int specialisation) {
        this.specialisation = specialisation;
    }

    @Override
    public String toString() {
        return "ForumPost{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
