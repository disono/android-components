package camera_frame.disono.webmons.com.cameraframe;

import android.content.Context;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ArDisplayView extends SurfaceView implements SurfaceHolder.Callback, Camera.PictureCallback {
    public static final String TAG = "ArDisplayView";
    Camera mCamera;
    Camera.Parameters params;
    SurfaceHolder mHolder;

    public int camWidth;
    public int camHeight;

    private Context mContext;
    private boolean safeToTakePicture = false;

    public ArDisplayView(Context context) {
        super(context);
        mContext = context;

        mHolder = getHolder();
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mCamera = Camera.open();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int width, int height) {
        params = mCamera.getParameters();
        List<Camera.Size> prevSizes = params.getSupportedPreviewSizes();
        for (Camera.Size s : prevSizes) {
            if ((s.height <= height) && (s.width <= width)) {
                camWidth = width;
                camHeight = height;
                Log.i("Sizes", "Sizes: " + camWidth + " = " + camHeight);

                params.setPreviewSize(s.width, s.height);
                break;
            }
        }

        startPreviewCamera();

        safeToTakePicture = true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        releaseCamera();
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private void startPreviewCamera() {
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setParameters(params);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takePhoto() {
        if (safeToTakePicture && mCamera != null) {
            safeToTakePicture = false;

            mCamera.takePicture(null, null, this);
        }
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.v(TAG, "Getting output media file");

        String file = photoFolder() + unixTime() + ".jpg";
        File newFile = new File(file);

        try {
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(data);
            fos.close();

            // no path to picture, return
            safeToTakePicture = true;

            // release camera
            releaseCamera();

            // reopen camera
            mCamera = Camera.open();
            startPreviewCamera();
        } catch (IOException e) {
            Log.v(TAG, e.getMessage());
        }
    }

    /**
     * Photo folder
     *
     * @return
     */
    private String photoFolder() {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "client_photo");

        if (!folder.exists()) {
            folder.mkdir();
        }

        return folder.toString() + File.separator;
    }

    /**
     * Unix time
     *
     * @return
     */
    private long unixTime() {
        return System.currentTimeMillis() / 1000L;
    }
}
