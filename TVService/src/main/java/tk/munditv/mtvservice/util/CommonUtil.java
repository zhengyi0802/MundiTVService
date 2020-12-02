//from Allshare
package tk.munditv.mtvservice.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class CommonUtil {

    private static final String TAG = CommonUtil.class.getSimpleName();

    public static boolean checkNetState(Context context) {
        Log.d(TAG, "checkNetState()");
        boolean netstate = false;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }

    public static void showToask(Context context, String tip) {
        Log.d(TAG, "showToask()");

        Toast.makeText(context, tip, Toast.LENGTH_SHORT).show();
    }

    public static boolean hasSDCard() {
        Log.d(TAG, "hasSDCard()");

        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            // log.e("No sdcard");
            return false;
        }
        return true;
    }

    public static String getRootFilePath() {
        Log.d(TAG, "getRootFilePath()");

        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";// filePath:/sdcard/
        } else {
            return Environment.getDataDirectory().getAbsolutePath() + "/data/"; // filePath:
                                                                                // /data/data/
        }
    }

    public static int getScreenWidth(Context context) {
        Log.d(TAG, "getScreenWidth()");

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    public static int getScreenHeight(Context context) {
        Log.d(TAG, "getScreenHeight()");

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    public static ViewSize getFitSize(Context context, MediaPlayer mediaPlayer) {
        Log.d(TAG, "getFitSize()");

        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        double fit1 = videoWidth * 1.0 / videoHeight;

        int width2 = getScreenWidth(context);
        int height2 = getScreenHeight(context);
        double fit2 = width2 * 1.0 / height2;

        Log.e(TAG, "videoWidth = " + videoWidth + ", videoHeight = " + videoHeight + ",fit1 = "
                + fit1);
        Log.e(TAG, "width2 = " + width2 + ", height2 = " + height2 + ",fit2 = " + fit2);

        double fit = 1;
        if (fit1 > fit2) {
            fit = width2 * 1.0 / videoWidth;
        } else {
            fit = height2 * 1.0 / videoHeight;
        }

        Log.d(TAG, "fit = " + fit);

        ViewSize viewSize = new ViewSize();
        viewSize.width = (int) (fit * videoWidth);
        viewSize.height = (int) (fit * videoHeight);

        return viewSize;
    }

    public static class ViewSize {
        public int width = 0;
        public int height = 0;
    }

}
