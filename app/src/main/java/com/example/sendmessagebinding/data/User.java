package com.example.sendmessagebinding.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Clase que contiene la información del usuario que ha iniciado sesión en la aplicación
 */
public class User implements Serializable, Parcelable {
    private String name;
    private String email;

    /**
     * Contructor con los parámetros
     * @param name
     * @param email
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
