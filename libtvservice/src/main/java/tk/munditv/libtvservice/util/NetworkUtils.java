package tk.munditv.libtvservice.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private final static String TAG = NetworkUtils.class.getSimpleName();

    public static Bitmap getBitmapFromURL(String src) {
        Log.d(TAG, "getBitmapFromURL()");
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            return BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            Log.e("NetworkUtils", "can't download bitmap");
            return null;
        }
    }
}
