package com.azhar.kamus.model;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 31-03-2020.
 */

public class ModelKamus implements Serializable {

    private int id;
    private String kata;
    private String deskripsi;

    public ModelKamus(String kata, String deskripsi) {
        this.kata = kata;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public String getKata() {
        return kata;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
