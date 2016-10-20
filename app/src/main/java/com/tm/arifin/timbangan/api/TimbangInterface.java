package com.tm.arifin.timbangan.api;

import com.tm.arifin.timbangan.model.Timbang;
import com.tm.arifin.timbangan.model.TimbangRespon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by IT on 06/09/2016.
 */
public interface TimbangInterface {

    @GET("timbang/last")
    Call<TimbangRespon> getData(@Query("id") int id);

    @POST("timbang/simpan")
    Call<Timbang> simpanData(@Body Timbang timbang);
}
