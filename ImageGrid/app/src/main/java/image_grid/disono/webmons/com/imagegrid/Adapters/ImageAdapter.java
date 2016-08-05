package image_grid.disono.webmons.com.imagegrid.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import image_grid.disono.webmons.com.imagegrid.R;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-08-01 04:33 PM
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private final LayoutInflater mInflater;
    private Cursor cacheImages;
    private String[] cacheImagesArrays;

    public ImageAdapter(Context ctx) {
        mContext = ctx;
        mInflater = LayoutInflater.from(ctx);

        cacheImages = localImages();
        cacheImagesArrays = getAllImages();
    }

    @Override
    public int getCount() {
        return countImages();
    }

    @Override
    public Object getItem(int i) {
        return getImage(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView;
        View v = view;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
        }

        imageView = (ImageView) v.getTag(R.id.picture);
        imageView.setImageURI(Uri.parse(getImage(position)));

        return v;
    }

    private Cursor localImages() {
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media._ID;

        // Stores all the images from the gallery in Cursor
        return mContext.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
    }

    public int countImages() {
        Cursor cursor = cacheImages;

        // Total number of images
        if (cursor == null) {
            return 0;
        }

        return cursor.getCount();
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
