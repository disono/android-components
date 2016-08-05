package camera_frame.disono.webmons.com.cameraframe;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    FrameLayout frameLayout;

    Button btn_add_frame;
    ArDisplayView arDisplay;
    OverlayView arContent;

    int indexImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.main_layout);
        arDisplay = new ArDisplayView(this);
        frameLayout.addView(arDisplay);

        btn_add_frame = (Button) findViewById(R.id.btn_add_frame);
        btn_add_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arContent != null) {
                    Log.i("MainActivity", "Working");
                    arContent.addFrame(indexImage);
                    arDisplay.takePhoto();
                    indexImage++;
                }
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                arContent = new OverlayView(getApplicationContext(), arDisplay.camWidth, arDisplay.camHeight);
                frameLayout.addView(arContent);
            }
        }, 300);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
