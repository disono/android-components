package com.webmons.disono.toolbar;

/**
 * Author: Archie Disono on 2016-03-19.
 * Website: http://webmons.com
 * Email: webmonsph@gmail.com / disono.apd@gmail.com
 * License: The MIT License (MIT)
 */

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ActionBar actionBar = getSupportActionBar();

        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(MainActivity.this, "Refresh", Toast.LENGTH_SHORT).show();

                break;
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.show_toolbar:
                // show actionbar
                if (actionBar != null) {
                    actionBar.show();
                }
                break;
            case R.id.hide_toolbar:
                // hide actionbar
                if (actionBar != null) {
                    actionBar.hide();
                }
                break;
            default:
                break;
        }

        return true;
    }
}
