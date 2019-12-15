package com.jk.demo.entities;

import java.util.Date;

public class Task {
    private String name;
    private String person;
    private String department;
    private String isdone;

    public String getIsdone() {
        return isdone;
    }

    public void setIsdone(String isdone) {
        this.isdone = isdone;
    }

    private Date begin;
    private Date end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", person='" + person + '\'' +
                ", department='" + department + '\'' +
                ", isdone='" + isdone + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
