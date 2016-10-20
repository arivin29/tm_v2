package com.tm.arifin.timbangan.activity;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tm.arifin.timbangan.Adapter.IkanAdaptor;
import com.tm.arifin.timbangan.AppConfig;
import com.tm.arifin.timbangan.R;
import com.tm.arifin.timbangan.api.IkanInterface;
import com.tm.arifin.timbangan.model.Ikan;
import com.tm.arifin.timbangan.model.IkanRespon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arifin on 20/10/16.
 */

public class TimbangActivity extends AppCompatActivity {

    private TextView mTitleView;
    private Toolbar mToolbar;
    ListView listView, listView_timbang;
    EditText pilihanIkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timbang_activity);

        this.mToolbar = ((Toolbar)findViewById(R.id.my_toolbar));
        this.mTitleView = ((TextView)findViewById(R.id.toolbar_title));
        setSupportActionBar(this.mToolbar);
        this.mTitleView.setText(AppConfig.NAMA_KAPAL);

        mTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        if (Build.VERSION.SDK_INT >= 21) {
            setTaskDescription(new ActivityManager.TaskDescription(getString(R.string.home)));
        }
        supportInvalidateOptionsMenu();
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, new ContentFragment(), "com.javarticles.android.Home").commitAllowingStateLoss();

        getDataIkan();

    }

    /**--------------------------------------------------*/
    //DATA IKAN
    /**--------------------------------------------------*/
    private void getDataIkan()
    {
        Log.d("mulai", "Cari data");
        pilihanIkan = (EditText)findViewById(R.id.pilihIkan);

        listView = (ListView) findViewById(R.id.list_ikan);

        IkanInterface ikanInterface = AppConfig.getClient().create(IkanInterface.class);
        Call<IkanRespon> call = ikanInterface.getData();
        call.enqueue(new Callback<IkanRespon>() {
            @Override
            public void onResponse(Call<IkanRespon> call, Response<IkanRespon> response) {
                Log.d("mulai", "dapat data");
                List<Ikan> ikan = response.body().getListIkan();
                final IkanAdaptor ikanAdaptor = new IkanAdaptor(getApplicationContext(),ikan,pilihanIkan);
                listView.setAdapter(ikanAdaptor);


                EditText cariIkan = (EditText) findViewById(R.id.cari_ikan);
                cariIkan.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        Log.e("pesan", charSequence + "sds");
                        ikanAdaptor.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }

            @Override
            public void onFailure(Call<IkanRespon> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"APlikasi gagal load Data, cek koneksi internet",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_pengaturan) {

            Log.e("Click_menu","Pengaturan");
            return true;
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
