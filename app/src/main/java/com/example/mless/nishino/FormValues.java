package com.example.mless.nishino;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class FormValues implements Parcelable {
    private String surName, firstName;
    private String sex;
    private String phone;
    private List<String> hobby;
    private String work;

    FormValues() {
        this.surName = "";
        this.firstName = "";
        this.sex = "";
        this.phone = "";
        this.hobby = new ArrayList<>();
        this.work = "";
    }

    protected FormValues(Parcel in) {
        surName = in.readString();
        firstName = in.readString();
        sex = in.readString();
        phone = in.readString();
        hobby = in.createStringArrayList();
        work = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(surName);
        dest.writeString(firstName);
        dest.writeString(sex);
        dest.writeString(phone);
        dest.writeStringList(hobby);
        dest.writeString(work);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FormValues> CREATOR = new Creator<FormValues>() {
        @Override
        public FormValues createFromParcel(Parcel in) {
            return new FormValues(in);
        }

        @Override
        public FormValues[] newArray(int size) {
            return new FormValues[size];
        }
    };

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
