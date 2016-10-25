package com.tm.arifin.timbangan;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arifin on 20/10/16.
 */

public class AppConfig {

    public static final String BASE_URL = "http://arivin.xyz/data_pdam/public/api/";
//    public static final String BASE_URL = Pengaturan.url;
        public static String NAMA_PELANGGAN = null;




    public static boolean LOGIN = false;
    private static Retrofit retrofit = null;

    public static Retrofit getClient()
    {

        Log.i("URL",BASE_URL);
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        // client.addInterceptor(logging);
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create()).build();


        }
        return retrofit;
    }
}
