package com.tm.arifin.timbangan.service;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by arifin on 20/10/16.
 */

public class PdamService {

    public static Context context;
    public PdamService(Context context)
    {
        this.context = context;
    }

    EditText nama_kapa, alat_tangkap, no_izin, pemilik;
    public String simpanKapal()
    {

        return "ok";
    }
}
