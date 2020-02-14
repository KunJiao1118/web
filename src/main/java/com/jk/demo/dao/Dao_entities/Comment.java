package com.jk.demo.dao.Dao_entities;

public class Comment {
    private String sid;
    private String oid;
    private String comment;
    public Comment() {
        this.sid = null;
        this.oid = null;
        this.comment = null;
    }
    public Comment(String sid, String oid, String comment) {
        this.sid = sid;
        this.oid = oid;
        this.comment = comment;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
