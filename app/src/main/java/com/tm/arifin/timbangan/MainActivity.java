package com.tm.arifin.timbangan;

import android.app.ActivityManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTitleView;
    private Toolbar mToolbar;

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
}
