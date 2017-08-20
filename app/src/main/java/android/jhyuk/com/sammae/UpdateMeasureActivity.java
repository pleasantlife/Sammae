package android.jhyuk.com.sammae;

import android.content.DialogInterface;
import android.content.Intent;
import android.jhyuk.com.sammae.domain.RealModel;
import android.jhyuk.com.sammae.domain.StoreInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class UpdateMeasureActivity extends AppCompatActivity implements View.OnClickListener {

    EditText upRealSizeWidth, upRealSizeHeight, upRealSizeSurface, upRealSizeHowProcess, upRealSizeRemarks;
    ImageView upImgMeasure1, upImgMeasure2, imgConst1, imgConst2;
    Uri updateUri, firstupUri, secondupUri, thirdupUri, fourthupUri;
    Spinner upSpinnerMaterial;
    ArrayAdapter secondAdapter;
    Button btnUpRealSize;
    String[] items = {"0", "1", "2", "3", "4", "5"};
    String selectedItem;
    RealModel realModel = new RealModel();
    StoreInfo storeInfo = new StoreInfo();
    Bundle bundle;
    List<Boolean> constFlags = new ArrayList<>();


    public static int REQ_UPDATE = 123;
    public static final int UP_M_IMG1 = 456;
    public static final int UP_M_IMG2 = 776;
    public static final int UP_CON1 = 999;
    public static final int UP_CON2 = 888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_measure);

        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION", -1);
        if(position > -1){
            realModel = (RealModel) bundle.getSerializable("Value");
        }
        initView();
        setSpinner();
        setOnClick();
    }

    private void initView(){
        upRealSizeWidth = (EditText) findViewById(R.id.upRealSizeWidth);
        upRealSizeHeight = (EditText) findViewById(R.id.upRealSizeHeight);
        upRealSizeSurface = (EditText) findViewById(R.id.upRealSizeSurface);
        upRealSizeHowProcess = (EditText) findViewById(R.id.upRealSizeHowProcess);
        upRealSizeRemarks = (EditText) findViewById(R.id.upRealSizeRemarks);

        upSpinnerMaterial = (Spinner) findViewById(R.id.upSpinnerMaterial);

        upImgMeasure1 = (ImageView) findViewById(R.id.upImgMeasure1);
        upImgMeasure2 = (ImageView) findViewById(R.id.upImgMeasure2);
        imgConst1 = (ImageView) findViewById(R.id.imgConst1);
        imgConst2 = (ImageView) findViewById(R.id.imgConst2);

        btnUpRealSize = (Button) findViewById(R.id.btnUpRealSize);

        upRealSizeWidth.setText(realModel.getWidth());
        upRealSizeHeight.setText(realModel.getHeight());
        upRealSizeSurface.setText(realModel.getSurface());
        upRealSizeHowProcess.setText(realModel.getHowProcess());
        upRealSizeRemarks.setText(realModel.getRemarks());


        for(int i = 0; i < items.length; i++){
            if(items[i].equals(realModel.getMaterial())){
                upSpinnerMaterial.setSelection(i);
            }
        }

        Glide.with(this).load(realModel.getSurveyImage1()).into(upImgMeasure1);
        Glide.with(this).load(realModel.getSurveyImage2()).into(upImgMeasure2);

    }

    /*
        스피너 사용시 내부 데이터 배열 및 레이아웃은 ArrayAdapter로 연결함.
     */
    private void setSpinner(){
        upSpinnerMaterial = new Spinner(this);
        secondAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items);
        upSpinnerMaterial.setAdapter(secondAdapter);
        upSpinnerMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void setOnClick(){
        upImgMeasure1.setOnClickListener(this);
        upImgMeasure2.setOnClickListener(this);
        imgConst1.setOnClickListener(this);
        imgConst2.setOnClickListener(this);
        btnUpRealSize.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.upImgMeasure1:
                REQ_UPDATE = UP_M_IMG1;
                imageDialog();
                break;
            case R.id.upImgMeasure2:
                REQ_UPDATE = UP_M_IMG2;
                imageDialog();
                break;
            case R.id.imgConst1:
                REQ_UPDATE = UP_CON1;
                imageDialog();
                break;
            case R.id.imgConst2:
                REQ_UPDATE = UP_CON2;
                imageDialog();
                break;
            case R.id.btnUpRealSize:
                if(nullCheck(upRealSizeWidth)
                        && nullCheck(upRealSizeHeight)
                        && nullCheck(upRealSizeSurface)
                        && nullCheck(upRealSizeHowProcess)
                        && nullCheckPhoto(firstupUri)
                        && nullCheckPhoto(secondupUri)
                        && nullCheckPhoto(thirdupUri)
                        && nullCheckPhoto(fourthupUri)){
                    constFlags.add(true);
                    realModel.setConstructionCpl(constFlags);
                    storeInfo.setConstructionComplete(constFlags);

                    //TODO firebase upload

                    Intent intent = new Intent(this, List2Activity.class);
                    startActivity(intent);
                }
                break;
        }
    }


//    upRealSizeWidth = (EditText) findViewById(R.id.upRealSizeWidth);
//    upRealSizeHeight = (EditText) findViewById(R.id.upRealSizeHeight);
//    upRealSizeSurface = (EditText) findViewById(R.id.upRealSizeSurface);
//    upRealSizeHowProcess = (EditText) findViewById(R.id.upRealSizeHowProcess);
//    upRealSizeRemarks = (EditText) findViewById(R.id.upRealSizeRemarks);
//
//    upSpinnerMaterial = (Spinner) findViewById(R.id.upSpinnerMaterial);
//
//    upImgMeasure1 = (ImageView) findViewById(R.id.upImgMeasure1);
//    upImgMeasure2 = (ImageView) findViewById(R.id.upImgMeasure2);




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


    /* 사진 크게 보기 */
    private void picSizeUp(Uri updateUri){
            Intent intent = new Intent(UpdateMeasureActivity.this, PicActivity.class);
            intent.putExtra("imageUri", updateUri);
            startActivity(intent);
    }


    private void imageDialog(){
        /*
            다이얼로그에서 "크게 보기"메뉴를 선택했을 때 호출되는 리스너
         */
        DialogInterface.OnClickListener sizeUpListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(REQ_UPDATE){
                    case UP_M_IMG1:
                        if(firstupUri != null){
                            updateUri = firstupUri;
                            picSizeUp(updateUri);
                        } else {
                            Toast.makeText(UpdateMeasureActivity.this, "이미지를 업로드 하세요", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case UP_M_IMG2:
                        if(secondupUri != null){
                            updateUri = secondupUri;
                            picSizeUp(updateUri);
                        } else {
                            Toast.makeText(UpdateMeasureActivity.this, "이미지를 업로드 하세요", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case UP_CON1:
                        if(thirdupUri != null){
                            updateUri = thirdupUri;
                            picSizeUp(updateUri);
                        } else {
                            Toast.makeText(UpdateMeasureActivity.this, "이미지를 업로드 하세요", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case UP_CON2:
                        if(fourthupUri != null){
                            updateUri = fourthupUri;
                            picSizeUp(updateUri);
                        } else {
                            Toast.makeText(UpdateMeasureActivity.this, "이미지를 업로드 하세요", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };

        /*
            다이얼로그에서 "앨범 선택"메뉴를 선택했을 때 호출되는 리스너
         */
        DialogInterface.OnClickListener galleryListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "앱을 선택하세요"), REQ_UPDATE);
            }
        };

        /*
            다이얼로그에서 "취소" 메뉴를 선택했을 때 호출되는 리스너
         */

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               // dismiss() 함수로 다이얼로그를 종료시킴.
                dialogInterface.dismiss();
            }
        };


        new AlertDialog.Builder(this).setTitle("이미지 업로드 하기")
                .setPositiveButton("크게 보기", sizeUpListener)
                .setNegativeButton("앨범 선택", galleryListener)
                .setNeutralButton("취소", cancelListener).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case UP_M_IMG1:
                setImage(resultCode, data, upImgMeasure1);
                break;
            case UP_M_IMG2:
                setImage(resultCode, data, upImgMeasure2);
                break;
            case UP_CON1:
                setImage(resultCode, data, imgConst1);
                break;
            case UP_CON2:
                setImage(resultCode, data, imgConst2);
                break;
        }
    }

    private void setImage(int resultCode, Intent data, ImageView view){
        if(resultCode == RESULT_OK){
            switch (view.getId()){
                case R.id.upImgMeasure1:
                    firstupUri = data.getData();
                    Glide.with(this).load(firstupUri).into(upImgMeasure1);
                    break;
                case R.id.upImgMeasure2:
                    secondupUri = data.getData();
                    Glide.with(this).load(secondupUri).into(upImgMeasure2);
                    break;
                case R.id.imgConst1:
                    thirdupUri = data.getData();
                    Glide.with(this).load(thirdupUri).into(imgConst1);
                    break;
                case R.id.imgConst2:
                    fourthupUri = data.getData();
                    Glide.with(this).load(fourthupUri).into(imgConst2);
                    break;
            }
        }
    }
}
