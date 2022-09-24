package com.example.onlinedelivery;

public class User
{
    String uid;
    String mail;
    String pass;
    String cpass;
    String name;
    String mobile;
    int bdate;

    public User(String uid, String mail, String pass, String cpass, String name, String mobile, int bdate) {
        this.uid = uid;
        this.mail = mail;
        this.pass = pass;
        this.cpass = cpass;
        this.name = name;
        this.mobile = mobile;
        this.bdate = bdate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getBdate() {
        return bdate;
    }

    public void setBdate(int bdate) {
        this.bdate = bdate;
    }
}
