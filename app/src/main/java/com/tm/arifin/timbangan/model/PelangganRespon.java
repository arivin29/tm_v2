package com.tm.arifin.timbangan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arifin on 24/10/16.
 */

public class PelangganRespon {

    private int total;

    @SerializedName("data")
    @Expose
    private List<Pelanggan> data;

    public int getTotal()
    {
        return total;
    }

    public List<Pelanggan> getData()
    {
        return data;
    }
}
