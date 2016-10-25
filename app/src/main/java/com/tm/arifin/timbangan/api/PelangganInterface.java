package com.tm.arifin.timbangan.api;

import com.tm.arifin.timbangan.model.Pelanggan;
import com.tm.arifin.timbangan.model.PelangganRespon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by IT on 10/09/2016.
 */
public interface PelangganInterface {
    @GET("pelanggan/json")
    Call<PelangganRespon> getData(@Query("status") String status);

    @POST("timbang/simpan")
    Call<Pelanggan> simpanData(@Body Pelanggan pelanggan);
}
