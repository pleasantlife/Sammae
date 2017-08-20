package android.jhyuk.com.sammae;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import static android.jhyuk.com.sammae.DetailActivity.REQ_CAMERA;

/**
 * Created by maxx on 2017. 8. 16..
 */

public class Camera {

    File photoFile;
    Uri fileUri;


    public void takePic(Activity activity) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                photoFile = createFile();
                if (photoFile != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        fileUri = FileProvider.getUriForFile(activity, "android.jhyuk.com.cameraex.provider", photoFile);
                        Log.i("fileUri","================="+fileUri);
                    } else {
                        fileUri = Uri.fromFile(photoFile);
                    }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    activity.startActivityForResult(intent, REQ_CAMERA);
                } else {
                    Toast.makeText(activity, "이미지 파일이 생성되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            activity.startActivityForResult(intent, REQ_CAMERA);
        }
    }

    private File createFile() throws Exception {

        String fileName = "Temp_" + String.valueOf(System.currentTimeMillis());
        File fileDir = new File(Environment.getExternalStorageDirectory() + "/TestCamera/");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        File tempFile = File.createTempFile(fileName, ".jpg", fileDir);
        return tempFile;
    }


}



