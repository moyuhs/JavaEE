package com.java.domain;

public class Student {
    private String sno;
    private String sname;
    private String ssex;
    private Integer sage;
    private String department;
    private String sclass;
    private String sphone;

    public Student() {
    }

    public Student(String sno, String sname, String ssex, Integer sage, String department, String sclass, String sphone) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.sage = sage;
        this.department = department;
        this.sclass = sclass;
        this.sphone = sphone;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sage=" + sage +
                ", department='" + department + '\'' +
                ", sclass='" + sclass + '\'' +
                ", sphone='" + sphone + '\'' +
                '}';
    }

    public void sleep() {

        System.out.println( "sleep..." );
    }
}
