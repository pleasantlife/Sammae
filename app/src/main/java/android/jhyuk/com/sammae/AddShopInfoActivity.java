package android.jhyuk.com.sammae;

import android.content.Intent;
import android.jhyuk.com.sammae.domain.StoreInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddShopInfoActivity extends AppCompatActivity {

    private EditText editAddDate;
    private EditText editShopCode;
    private EditText editShopName;
    private EditText editAddress1;
    private EditText editAddress2;
    private EditText editTel;
    private EditText editFax;
    private EditText editLocation;
    private EditText editShopManagerNum;
    private EditText editManagerName;
    Button btnPost;
    ArrayAdapter arrayAdapter;
    String telecom;
    StoreInfo storeInfo = new StoreInfo();
    Spinner telecomSpinner;
    String item[] = {"SkTelecom", "KT", "LgUplus", "3사 전부"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_info);
        initView();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, item);
        telecomSpinner.setAdapter(arrayAdapter);

    }


    /* 뷰 세팅 */
    private void initView() {

        btnPost = (Button) findViewById(R.id.btnPost);

        editAddDate = (EditText) findViewById(R.id.editAddDate);
        editShopCode = (EditText) findViewById(R.id.editShopCode);
        editShopName = (EditText) findViewById(R.id.editShopName);
        editAddress1 = (EditText) findViewById(R.id.editAddress1);
        editAddress2 = (EditText) findViewById(R.id.editAddress2);
        editTel = (EditText) findViewById(R.id.editTel);
        editFax = (EditText) findViewById(R.id.editFax);
        editLocation = (EditText) findViewById(R.id.editLocation);
        telecomSpinner = (Spinner) findViewById(R.id.telecomSpinner);
        editShopManagerNum = (EditText) findViewById(R.id.editShopManagerNum);
        editManagerName = (EditText) findViewById(R.id.editManagerName);
        btnPost = (Button) findViewById(R.id.btnPost);
        editAddDate.setText(setDate());
        telecomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                telecom = item[i];
                Log.i("telecom", "========================="+telecom);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /* 입력된 데이터 세팅 */
    private void dataInput(){

        storeInfo.setCode(editShopCode.getText().toString());
        storeInfo.setName(editShopName.getText().toString());
        storeInfo.setAddress(editAddress1.getText().toString());
        storeInfo.setAddress2(editAddress2.getText().toString());
        storeInfo.setTel(editTel.getText().toString());
        storeInfo.setFax(editFax.getText().toString());
        storeInfo.setLocalArea(editLocation.getText().toString());
        storeInfo.setPhone(editShopManagerNum.getText().toString());
        storeInfo.setManager(editManagerName.getText().toString());
        storeInfo.setCreateDate(setDate());

    }



    /* 매장 추가한 날짜 자동 입력 */
    public String setDate(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String addDate = sdf.format(date);
        return addDate;
    }

    /* 등록 버튼 */
    public void post(View view){
        dataInput();


        Intent intent = new Intent(this, List1Activity.class);
        startActivity(intent);

    }
}
