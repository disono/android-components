package image_grid.disono.webmons.com.imagegrid;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import image_grid.disono.webmons.com.imagegrid.Adapters.ImageAdapter;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-08-01 04:33 PM
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid();
    }

    private void grid() {
        final ImageAdapter imageAdapter = new ImageAdapter(this);

        GridView gridView = (GridView) findViewById(R.id.grid_image);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.i("POSITION", "Image Path: " + imageAdapter.getImage(position));
            }
        });
    }
}
