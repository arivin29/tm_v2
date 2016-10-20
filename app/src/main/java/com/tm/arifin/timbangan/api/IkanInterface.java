package com.tm.arifin.timbangan.api;

import com.tm.arifin.timbangan.model.IkanRespon;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by IT on 05/09/2016.
 */
public interface IkanInterface {
    @GET("ikan")
    Call<IkanRespon> getData();

}
