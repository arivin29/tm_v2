package com.tm.arifin.timbangan.activity;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.tm.arifin.timbangan.AppConfig;
import com.tm.arifin.timbangan.MyApplication;
import com.tm.arifin.timbangan.R;
import com.tm.arifin.timbangan.adapter.PelangganAdapter;
import com.tm.arifin.timbangan.api.PelangganInterface;
import com.tm.arifin.timbangan.model.Pelanggan;
import com.tm.arifin.timbangan.model.PelangganRespon;
import com.tm.arifin.timbangan.service.PdamService;
import com.tm.arifin.timbangan.service.RecyclerItemClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PelangganActivity extends AppCompatActivity  implements View.OnFocusChangeListener, SearchView.OnQueryTextListener {

    private Toolbar mToolbar;
    private int queryLength;
    PelangganAdapter pelangganAdapter;
    List<Pelanggan> pelangganList;

    private SearchView searchView;
    private MenuItem searchMenuItem;
    SwipeRefreshLayout swipeContainer;
    String status="pending";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelanggan);

        this.mToolbar = ((Toolbar)findViewById(R.id.my_toolbar));
        setSupportActionBar(this.mToolbar);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(status);
                TextView loading = (TextView) findViewById(R.id.upload_loading);
                loading.setVisibility(View.INVISIBLE);
            }
        });

        swipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.level1,
                R.color.level2,
                R.color.level3);

        recyclerView = (RecyclerView) findViewById(R.id.list_pelanggan);

        supportInvalidateOptionsMenu();
        getData(status);

    }

    //===================================


    private void getData(String status) {
        swipeContainer.setRefreshing(false);

        PelangganInterface pelangganInterface = AppConfig.getClient().create(PelangganInterface.class);
        Call<PelangganRespon> call = pelangganInterface.getData(status);
        call.enqueue(new Callback<PelangganRespon>() {
            @Override
            public void onResponse(Call<PelangganRespon> call, Response<PelangganRespon> response) {
                Log.d("mulai", "dapat data");

                List<Pelanggan> pelanggen = response.body().getData();
                Log.e("jml daata", "" + pelanggen.size());
                pelangganAdapter = new PelangganAdapter(pelanggen);


                swipeContainer.setRefreshing(false);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(pelangganAdapter);

                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {
                                // do whatever
                                pelangganList = pelangganAdapter.pelangganList;
                                Log.e("clik", "standar" + pelangganList.get(position).getNamaPelanggan());

                                Intent i = new Intent(PelangganActivity.this, SurveyActivity.class);

                                AppConfig.NAMA_PELANGGAN = pelangganList.get(position).getNamaPelanggan().toString();

                                startActivityForResult(i, 1);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                                Log.e("clik", "panjang" + pelangganList.get(position).getNamaPelanggan());
                            }
                        })
                );

            }

            @Override
            public void onFailure(Call<PelangganRespon> call, Throwable t) {
                Log.e("erro","" + t);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pelanggan, menu);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchMenuItem = menu.findItem(R.id.action_search);
        searchView.setQueryHint("Nama Pelanggan..");
        searchView.setOnQueryTextFocusChangeListener(this);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            searchMenuItem.collapseActionView();
            searchView.setQuery("", false);
//            title.setVisibility(View.VISIBLE);
//            mapFragment.setVisibility(View.VISIBLE);
        } else {
//            title.setVisibility(View.INVISIBLE);
//            mapFragment.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
//        title.setVisibility(View.VISIBLE);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (!newText.isEmpty()) {
            queryLength = newText.length();
            pelangganAdapter.getFilter().filter(newText);
        } else {
            getData(status);
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_aktif) {

            status = "aktif";
            getData(status);
        }
        else if(id == R.id.action_pending)
        {
            status = "pending";
            getData(status);
        }
        else if(id == R.id.action_survey)
        {
            status = "survey";
            getData(status);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

    }
}
