package com.webmons.disono.toolbar;

/**
 * @author Archie Disono on ${DATE}.
 * @url http://webmons.com
 * @email webmonsph@gmail.com / disono.apd@gmail.com
 * @lincense The MIT License (MIT)
 */

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
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
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(MainActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
                HomeFragment homeFragment = new HomeFragment();

                // add the fragment
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.activityParentView, homeFragment)
                        .addToBackStack(null) // add to history stack
                        .commit();

                // ensure all transactions is finished
                fragmentManager.executePendingTransactions();

                break;
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();

                ContextualToolbar contextualToolbar = new ContextualToolbar();

                // add the fragment
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.activityParentView, contextualToolbar)
                        .addToBackStack(null) // add to history stack
                        .commit();

                // ensure all transactions is finished
                fragmentManager.executePendingTransactions();

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

                    // dimming the navigation buttons
                    getWindow()
                            .getDecorView()
                            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                }
                break;
            case R.id.change_title_toolbar:
                // change title
                if (actionBar != null) {
                    actionBar.setSubtitle("");
                    actionBar.setTitle("Updated Title");
                }
                break;
            case R.id.immersive_toolbar:
                hideSystemUI();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 0) {
            // back to history stack
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    // using immersive full screen mode
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE // remove the following flag for version < 19
        );
    }
}
