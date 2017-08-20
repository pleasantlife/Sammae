package android.jhyuk.com.sammae;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.jhyuk.com.sammae.domain.StoreInfo;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback{
    LinearLayout btnCam, btnGoMearsureList, btnList;
    ImageView img,img2,img3, imgCam, imgList, imgGoMeasureList, imgMainView;
    TextView textShopName, textAddr1, textAddr2, textPhoneNum;

    //각 이미지 별로 Uri를 별도 지정하여 사용함.
    Uri imageUri, firstUri, secondUri, thirdUri;
    Camera camera = new Camera();
    StoreInfo storeInfo = new StoreInfo();
    Intent intent;
    GoogleMap gmap;
    LatLng latLng;
    Marker marker;
    Bundle bundle;
    int position = -1;


    static final int REQ_PERMISSION = 99;

    /*
        아래 코드들을 통해 다이얼로그에서 특정 행동으로 넘어갈 때,
        어느 이미지뷰에서 출발했는지를 구분지을 수 있게 코드를 지정함.
     */
    static final int REQ_CAMERA = 100;
    static final int REQ_GALLERY_IMG = 1;
    static final int REQ_GALLERY_IMG2 = 2;
    static final int REQ_GALLERY_IMG3 = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        intent = getIntent();
        position = intent.getIntExtra("POSITION", -1);
        if(position > -1 ){
            storeInfo = (StoreInfo) bundle.getSerializable("Value");
        }
        setWidget();
        setListener();
        setMap();
        setValue();

        if(Build.VERSION.SDK_INT >= M){
            checkPermission();
        } else {
            init();
        }
    }

    private void init(){
        btnCam.setEnabled(true);

        img.setEnabled(true);
        img2.setEnabled(true);
        img3.setEnabled(true);

    }

    private void setWidget(){

        img = (ImageView) findViewById(R.id.img);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        imgCam = (ImageView) findViewById(R.id.imgCam);
        imgList = (ImageView) findViewById(R.id.imgList);
        imgGoMeasureList = (ImageView) findViewById(R.id.imgGoMearsureList);
        imgMainView = (ImageView) findViewById(R.id.imgMainView);

        textShopName = (TextView) findViewById(R.id.textShopName);
        textAddr1 = (TextView) findViewById(R.id.textAddr1);
        textAddr2 = (TextView) findViewById(R.id.textAddr2);
        textPhoneNum = (TextView) findViewById(R.id.textPhoneNum);

        btnGoMearsureList = (LinearLayout) findViewById(R.id.btnGoMearsureList);
        btnList = (LinearLayout) findViewById(R.id.btnList);
        btnCam = (LinearLayout) findViewById(R.id.btnCam);
        btnCam.setEnabled(false);

        img.setEnabled(false);
        img2.setEnabled(false);
        img3.setEnabled(false);
    }

    /* 리스트에서 선택한 항목의 데이터를 위젯에 연결(text) */
    private void setValue(){
        textShopName.setText(storeInfo.getCode() +" "+ storeInfo.getName());
        textAddr1.setText(storeInfo.getAddress());
        textAddr2.setText(storeInfo.getAddress2());
        textPhoneNum.setText(storeInfo.getTel());
    }


    @TargetApi(M)
    public void checkPermission() {   //권한이 승인이 되어있는지 확인
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                init();
        } else {
            String permissions[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            requestPermissions(permissions, REQ_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQ_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                init();
            }else{
                Toast.makeText(this, "권한 승인해야 앱을 사용할수 있습니다", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /* 리스너 세팅 */
    private void setListener(){

        btnCam.setOnClickListener(this);
        btnGoMearsureList.setOnClickListener(this);
        btnList.setOnClickListener(this);
        img.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
    }

    /* 카메라 버튼과 이미지뷰 클릭시 동작 */
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnCam:
                camera.takePic(this);
                break;
            case R.id.btnList:
                intent = new Intent(DetailActivity.this, List1Activity.class);
                startActivity(intent);
                break;
            /*
                실측 리스트 액티비티 상단에 가게명, 주소, 전화번호가 기록되어야 하기 때문에,
                버튼 클릭시 intent를 통해 액티비티를 이동하면서 함께 보냄.
             */
            case R.id.btnGoMearsureList:
                intent = new Intent(DetailActivity.this, List2Activity.class);
                String shopName = storeInfo.getCode() +" "+ storeInfo.getName();
                intent.putExtra("name", shopName);
                intent.putExtra("Addr1", storeInfo.getAddress());
                intent.putExtra("Addr2", storeInfo.getAddress2());
                intent.putExtra("Tel", storeInfo.getTel());
                startActivity(intent);
                break;
            case R.id.img:
                imageDialog(REQ_GALLERY_IMG);
                break;
            case R.id.img2:
                imageDialog(REQ_GALLERY_IMG2);
                break;
            case R.id.img3:
                imageDialog(REQ_GALLERY_IMG3);
                break;
        }
    }

    /* 인텐트 분기 처리 */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CAMERA:
                imageUri = null;
                if(resultCode == RESULT_OK){
                    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                        imageUri = data.getData();
                    }else{
                        imageUri = camera.fileUri;

                    }
                    MediaScannerConnection.scanFile(this,new String[] { camera.photoFile.getAbsolutePath() }, null, new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });
                }
                break;
            case REQ_GALLERY_IMG:
                setImage(resultCode, data, img);
                break;
            case REQ_GALLERY_IMG2:
                setImage(resultCode, data, img2);
                break;
            case REQ_GALLERY_IMG3:
                setImage(resultCode, data, img3);
                break;
        }
    }

    /* 선택한 이미지뷰에 이미지 넣기 */
    private void setImage(int resultCode, Intent data, ImageView view){
        if(resultCode == RESULT_OK){
            switch(view.getId()){
                case R.id.img:
                    firstUri = data.getData();
                    Glide.with(this).load(firstUri).into(view);
                    Glide.with(this).load(firstUri).into(imgMainView);
                    break;
                case R.id.img2:
                    secondUri = data.getData();
                    Glide.with(this).load(secondUri).into(view);
                    break;
                case R.id.img3:
                    thirdUri = data.getData();
                    Glide.with(this).load(thirdUri).into(view);
                    break;
            }
        }
    }

    /* 이미지 선택 및 보기 선택하는 다이얼로그를 띄우는 함수 */
    private void imageDialog(final int requestCode){

        DialogInterface.OnClickListener picSizeUpListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(requestCode){
                    case REQ_GALLERY_IMG :
                        dividePic(firstUri);
                        break;
                    case REQ_GALLERY_IMG2 :
                        dividePic(secondUri);
                        break;
                    case REQ_GALLERY_IMG3 :
                        dividePic(thirdUri);
                        break;
                }
            }
        };
        DialogInterface.OnClickListener galleryListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "앱을 선택하세요"), requestCode);
            }
        };
        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };
        new AlertDialog.Builder(this).setTitle(" 이미지 업로드 하기 ")
                .setPositiveButton("크게 보기", picSizeUpListener)
                .setNegativeButton("앨범 선택", galleryListener)
                .setNeutralButton("취소", cancelListener).show();
    }


    /*
       다이얼로그에서 '크게 보기'를 선택시, 사진만 띄우는 액티비티로 이동하는 함수.
       인텐트를 통해 이동하는 액티비티에는 라이브러리를 이용하여 사진의 확대/축소가 가능한 이미지뷰 세팅되어 있음.
   */
    private void dividePic(Uri imageUri){
        //imageUri 가 null이 아니라는 건, 곧 사진이 보여지고 있다는 뜻이므로 인텐트로 액티비티 이동.
        if(imageUri != null) {
            Intent intent = new Intent(DetailActivity.this, PicActivity.class);
            intent.putExtra("imageUri", imageUri);
            startActivity(intent);
        } else {
            //아직 사진을 등록하지 않았으므로 등록할 것을 유도.
            Toast.makeText(DetailActivity.this, "이미지를 업로드 하세요", Toast.LENGTH_SHORT).show();
        }
    }

    /*맵 등록*/
    public void setMap(){
        getSupportFragmentManager().findFragmentById(R.id.map_fragment);
    }

    /* 지도가 준비되면 호출 되는 함수 */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        gmap = googleMap;
//        latLng = findGeoPoint(/* 도메인 클래스의 주소값 변수가져오기 */);
//        if (latLng != null) {
//            marker = gmap.addMarker(new MarkerOptions()
//                    .position(latLng)
//                    .title(/* 도메인 클래스의 매장명 가져오기 */)
//                    .icon(BitmapDescriptorFactory.defaultMarker()));
//            marker.showInfoWindow();
//            gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
//        }
    }

    /* 주소값을 위경도로 바꿔주는 함수 */
    public LatLng findGeoPoint(String address) {
        Geocoder coder = new Geocoder(this);
        List<Address> addr = null;
        try {
            addr = coder.getFromLocationName(address, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address lating = addr.get(0);
        double lat = lating.getLatitude();
        double lon = lating.getLongitude();
        latLng = new LatLng(lat, lon);
        return latLng;
    }
}
