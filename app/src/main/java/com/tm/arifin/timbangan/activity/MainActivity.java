package com.tm.arifin.timbangan.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tm.arifin.timbangan.AppConfig;
import com.tm.arifin.timbangan.MyApplication;
import com.tm.arifin.timbangan.R;
import com.tm.arifin.timbangan.api.KapalInterface;
import com.tm.arifin.timbangan.db.Db_kapals;
import com.tm.arifin.timbangan.help.SocketKirim;
import com.tm.arifin.timbangan.model.Kapal;
import com.tm.arifin.timbangan.service.KapalService;

import java.net.URISyntaxException;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTitleView;
    private Toolbar mToolbar;
    Socket xSocket;

    private int sabolum = 0;
    KapalService kapalService;

    Kapal kapal;
    Db_kapals db_kapal;

    protected MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mToolbar = ((Toolbar)findViewById(R.id.my_toolbar));
        this.mTitleView = ((TextView)findViewById(R.id.toolbar_title));
        setSupportActionBar(this.mToolbar);
        this.mTitleView.setText(getString(R.string.home));

        if (Build.VERSION.SDK_INT >= 21) {
            setTaskDescription(new ActivityManager.TaskDescription(getString(R.string.home)));
        }
        supportInvalidateOptionsMenu();
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, new ContentFragment(), "com.javarticles.android.Home").commitAllowingStateLoss();

        kapalService =new KapalService(getApplicationContext());

        Button buttonTimbang = (Button)findViewById(R.id.next_timbang);
        buttonTimbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ambil id
                if(AppConfig.BACA_BARCODE != null)
                {
                    Intent i = new Intent(MainActivity.this, TimbangActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Mohon Baca Barcode",Toast.LENGTH_SHORT).show();
                }

            }
        });

        app = (MyApplication)getApplication();
        xSocket = app.getSocket();

        kapal = new Kapal();
        db_kapal = new Db_kapals(this);
        BacaBarcode();

    }



    /*----------------------------------------------*/
    //    Barcode
    /*----------------------------------------------*/
    EditText text_barcode;
    ImageView id_barcode;
    private void BacaBarcode()
    {
        text_barcode = (EditText) findViewById(R.id.kode_barcode);
        id_barcode = (ImageView) findViewById(R.id.baca_barcode);
        id_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.initiateScan();
            }
        });
    }

    private void confirmAmbilDataKapal(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apa Kamu Yakin Untuk Menganti Data ini?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        getDataKapal();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    String keyUnik;
    TextView nama_kapal, alat_tangkap, no_izin, pemilik;
    private void getDataKapal()
    {
        class getData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Proses Data...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Log.e("data","berhasil di ambil" + s);
            }

            @Override
            protected String doInBackground(Void... params) {
                try {
                    KapalInterface kapalInterface = AppConfig.getClient().create(KapalInterface.class);
                    Call<Kapal> call = kapalInterface.getData(AppConfig.BACA_BARCODE);
                    call.enqueue(new Callback<Kapal>() {
                        @Override
                        public void onResponse(Call<Kapal> call, Response<Kapal> response) {
                            kapal = response.body();
                            if(kapal.getIdKapal() > 0)
                            {
                                keyUnik = UUID.randomUUID().toString();
                                kapal.setKeyUnik(keyUnik);

                                nama_kapal = (TextView) findViewById(R.id.nama_kapal);
                                no_izin = (TextView)findViewById(R.id.kode_izin);
                                alat_tangkap = (TextView)findViewById(R.id.alat_tangkap);

                                //tuka text
                                nama_kapal.setText(kapal.getNamaKapal().toUpperCase());
                                no_izin.setText(kapal.getNoIzin().toUpperCase());
                                alat_tangkap.setText(kapal.getAlatTangkap().toUpperCase());

                                AppConfig.NAMA_KAPAL = kapal.getNamaKapal().toUpperCase();

                                //kirim ka soket
                                SocketKirim socketKirim = new SocketKirim();
                                socketKirim.kirimHasilKapal(getApplicationContext(),xSocket,db_kapal,kapal);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Data kapal Tidak ditemukan",Toast.LENGTH_SHORT).show();

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                                alertDialogBuilder.setMessage("Data kapal Tidak ditemukan");


                                alertDialogBuilder.setNegativeButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {

                                            }
                                        });

                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();

                            }


                        }

                        @Override
                        public void onFailure(Call<Kapal> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Data Tidak ditemukan, gagal koneksi",Toast.LENGTH_SHORT).show();
                            Log.e("eroor load data", t.getMessage());
                        }
                    });


                }
                catch (Exception e)
                {

                }
                return null;
            }
        }
        getData ge = new getData();
        ge.execute();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

//        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
//            if(resultCode == Activity.RESULT_OK)
//                bt.connect(data);
//        } else if(requestCode == BluetoothState.REQUEST_ENABLE_BT) {
//            if(resultCode == Activity.RESULT_OK) {
//                bt.setupService();
//                bt.startService(BluetoothState.DEVICE_ANDROID);
////                DashboardActivity.setup();
//            } else {
//                Toast.makeText(getApplicationContext()
//                        , "Bluetooth was not enabled."
//                        , Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }
//        else
//        {
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (scanningResult != null) {
                String scanContent = scanningResult.getContents();
                //String scanFormat = scanningResult.getFormatName();
                text_barcode.setText(scanContent);
                AppConfig.BACA_BARCODE = scanContent;
                confirmAmbilDataKapal();

            }else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
            }
        //}

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
