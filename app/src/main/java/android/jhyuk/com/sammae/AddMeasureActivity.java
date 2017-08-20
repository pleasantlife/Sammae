package android.jhyuk.com.sammae;

import android.content.DialogInterface;
import android.content.Intent;
import android.jhyuk.com.sammae.domain.RealModel;
import android.jhyuk.com.sammae.domain.StoreInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.jhyuk.com.sammae.DetailActivity.REQ_CAMERA;


public class AddMeasureActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddRealSize;
    EditText editRealSizeWidth, editRealSizeHeight, editRealSizeSurface, editRealSizeHowProcess, editRealSizeRemarks;
    ImageView imgMeasure1, imgMeasure2;
    Spinner spinnerMaterial;
    String[] items = {"0", "1", "2", "3", "4", "5"};
    ArrayAdapter spinnerAdapter;
    Camera camera = new Camera();
    AlertDialog alertDialog;
    Uri imageUri, m1Uri, m2Uri;
    String selectedItem;
    List<Boolean> flags = new ArrayList<>();
    RealModel realModel = new RealModel();
    StoreInfo storeInfo = new StoreInfo();

    static int REQ_CODE = 99999;
    static final int CAM_IMGMEA1 = 111;
    static final int CAM_IMGMEA2 = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measure);

        initView();
        getSpinnerData();

    }

    private void initView() {
        btnAddRealSize = (Button) findViewById(R.id.btnAddRealSize);
        editRealSizeWidth = (EditText) findViewById(R.id.editRealSizeWidth);
        editRealSizeHeight = (EditText) findViewById(R.id.editRealSizeHeight);
        editRealSizeSurface = (EditText) findViewById(R.id.editRealSizeSurface);
        editRealSizeHowProcess = (EditText) findViewById(R.id.editRealSizeHowProcess);
        spinnerMaterial = (Spinner) findViewById(R.id.spinnerMaterial);
        editRealSizeRemarks = (EditText) findViewById(R.id.editRealSizeRemarks);
        imgMeasure1 = (ImageView) findViewById(R.id.imgMeasure1);
        imgMeasure2 = (ImageView) findViewById(R.id.imgMeasure2);
        setOnClickListeners();
    }

    /* 입력된 값을 도메인에 세팅 */
    private void setReaSizeData(){
        realModel.setWidth(editRealSizeWidth.getText().toString());
        realModel.setHeight(editRealSizeHeight.getText().toString());
        realModel.setSurface(editRealSizeSurface.getText().toString());
        realModel.setHowProcess(editRealSizeHowProcess.getText().toString());
        realModel.setSurveyImage1(m1Uri.toString());
        realModel.setSurveyImage2(m2Uri.toString());


    }
    /* 스피너에서 선택한 값 저장하기 */
    private void getSpinnerData() {
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items);
        spinnerMaterial.setAdapter(spinnerAdapter);
        spinnerMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = items[i];
                realModel.setMaterial(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setOnClickListeners() {
        btnAddRealSize.setOnClickListener(this);
        imgMeasure1.setOnClickListener(this);
        imgMeasure2.setOnClickListener(this);
    }
    /* editText에 데이터가 입력되었는지 확인하는 함수 */
    private boolean nullCheck(EditText editText){
        boolean result = editText.getText().toString().equals(null);
        return !result;
    }
    /* 등록하기 버튼을 누르기 전에 사진이 올라와있는지 확인하는 함수 */
    private boolean nullCheckPhoto(Uri uri){
        boolean result = false;
        if(uri == null){
            result = false;
        }else {
            result = true;
        }
        return result;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddRealSize:
                if(nullCheck(editRealSizeHeight)
                        && nullCheck(editRealSizeWidth)
                        && nullCheck(editRealSizeSurface)
                        && nullCheck(editRealSizeHowProcess)
                        && nullCheckPhoto(m1Uri)
                        && nullCheckPhoto(m2Uri)) {
                    Log.e("status=====", 0+"");
                    Log.i("flags", "============================"+flags.size());
                    flags.add(true);                        //실측완료 됬다는 sign 추가
                    Log.i("flags2", "============================"+flags.size());
                    realModel.setMearsureCpl(flags);        //sign을 담은 리스트를 RealMedel 과 StoreInfo에 세팅
                    storeInfo.setMearsureComplete(flags);

                    //TODO firebase upload

                    Toast.makeText(AddMeasureActivity.this, "Uploading...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, List2Activity.class);
                    startActivity(intent);
                } else {
                    Log.e("status=====", 1+"");
                    Toast.makeText(AddMeasureActivity.this, "Please fill forms", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imgMeasure1:
                REQ_CODE = CAM_IMGMEA1;
                imageDialog(REQ_CODE);
                break;
            case R.id.imgMeasure2:
                REQ_CODE = CAM_IMGMEA2;
                imageDialog(REQ_CODE);
                break;

        }
    }

    /* 이미지 선택 및 보기 선택하는 다이얼로그창 띄워주는 함수 */
    private void imageDialog(int req) {

        DialogInterface.OnClickListener neutralButtonListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //원래 카메라가 실행되게 하려고 했지만.. 흑흑...
                //camera.takePic(AddMeasureActivity.this);
                alertDialog.dismiss();
            }
        };

        DialogInterface.OnClickListener galleryListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "앱을 선택하세요"), REQ_CODE);
            }
        };

        DialogInterface.OnClickListener picSizeUpListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(REQ_CODE){
                    case CAM_IMGMEA1 :
                        if(m1Uri != null){
                            imageUri = m1Uri;
                            dividePic(imageUri);
                        } else {
                            Toast.makeText(AddMeasureActivity.this, " 이미지를 업로드 하세요 ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case CAM_IMGMEA2 :
                        if(m2Uri != null) {
                            imageUri = m2Uri;
                            dividePic(imageUri);
                        } else {
                            Toast.makeText(AddMeasureActivity.this, " 이미지를 업로드 하세요 ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };




        alertDialog = new AlertDialog.Builder(this)
                .setTitle(" 이미지 업로드 하기 ")
                .setNeutralButton("취소하기", neutralButtonListener)
                .setNegativeButton("사진 크게 보기", picSizeUpListener)
                .setPositiveButton("갤러리에서 선택", galleryListener).show();
    }


    private void dividePic(Uri imageUri){
        Intent intent = new Intent(AddMeasureActivity.this, PicActivity.class);
        intent.putExtra("imageUri", imageUri);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CAMERA:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                /*
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        imageUri = data.getData();
                    } else {
                        imageUri = camera.fileUri;
                    }
                    MediaScannerConnection.scanFile(this, new String[]{camera.photoFile.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });
                    */
                switch(REQ_CODE){
                    case CAM_IMGMEA1:
                        m1Uri = imageUri;
                        Glide.with(this).load(imageUri).into(imgMeasure1);
                        break;
                    case CAM_IMGMEA2:
                        m2Uri = imageUri;
                        Glide.with(this).load(imageUri).into(imgMeasure2);
                        break;
                }



                break;
            case CAM_IMGMEA1:
                setImage(resultCode, data, imgMeasure1);
                break;
            case CAM_IMGMEA2:
                setImage(resultCode, data, imgMeasure2);
                break;
        }
    }

    private void setImage(int resultCode, Intent data, ImageView view) {
        if (resultCode == RESULT_OK) {
            switch (view.getId()) {
                case R.id.imgMeasure1:
                    m1Uri = data.getData();
                    Glide.with(this).load(m1Uri).into(imgMeasure1);
                    break;
                case R.id.imgMeasure2:
                    m2Uri = data.getData();
                    Glide.with(this).load(m2Uri).into(imgMeasure2);
                    break;
            }
        }
    }
}