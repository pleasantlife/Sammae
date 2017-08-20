package android.jhyuk.com.sammae;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PicActivity extends AppCompatActivity {
    ImageView bigPicture;
    Bundle bundle;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        Intent intent = getIntent();
        bundle = intent.getExtras();
        imageUri = bundle.getParcelable("imageUri");
        bigPicture = (ImageView) findViewById(R.id.bigPicture);
        Glide.with(this).load(imageUri).into(bigPicture);
    }
}
