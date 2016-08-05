package camera_frame.disono.webmons.com.cameraframe;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class OverlayView extends View {
    private String TAG = "OverlayView";

    Context mContext;
    Paint contentPaint;
    Bitmap bmp;

    private String[] cacheImagesArrays;
    private Cursor cacheImages;

    public int camWidth;
    public int camHeight;

    public OverlayView(Context context, int width, int height) {
        super(context);
        mContext = context;

        camWidth = width;
        camHeight = height;

        contentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        cacheImages = localImages();
        cacheImagesArrays = getAllImages();

        // setDrawingCacheEnabled(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bmp != null) {
            Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
            Rect dest = new Rect(0, 0, camWidth, camHeight);
            canvas.drawBitmap(bmp, src, dest, null);

            File folder = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "client_photo");

            try {
                // getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(folder.toString() + File.separator + unixTime() + "-app.jpg")));
                saveSignature(canvas);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            super.onDraw(canvas);
        }
    }

    public void saveSignature(Canvas canvas) {
        try {
            File folder = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "client_photo");

            bmp.compress(Bitmap.CompressFormat.PNG, 80, new FileOutputStream(new File(folder.toString() + File.separator + unixTime() + "-app.jpg")));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * Unix time
     *
     * @return
     */
    private long unixTime() {
        return System.currentTimeMillis() / 1000L;
    }

    public void addFrame(int index) {
        bmp = BitmapFactory.decodeFile(getImage(index));
        invalidate();
    }

    private Cursor localImages() {
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media._ID;

        // Stores all the images from the gallery in Cursor
        return mContext.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, MediaStore.Images.Media.DATA + " like ? ", new String[]{"%frames%"}, orderBy);
    }

    public String[] getAllImages() {
        Cursor cursor = cacheImages;
        String[] arrPath = new String[0];

        // Total number of images
        if (cursor == null) {
            return arrPath;
        }

        int count = cursor.getCount();

        // Create an array to store path to all the images
        arrPath = new String[count];

        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            // Store the path of the image
            arrPath[i] = cursor.getString(dataColumnIndex);
            Log.i("PATH", arrPath[i]);
        }

        return arrPath;
    }

    public String getImage(int index) {
        String[] arrPath = cacheImagesArrays;

        return arrPath[index];
    }
}
