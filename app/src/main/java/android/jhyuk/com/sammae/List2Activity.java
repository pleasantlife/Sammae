package android.jhyuk.com.sammae;

import android.content.Intent;
import android.jhyuk.com.sammae.domain.RealModel;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class List2Activity extends AppCompatActivity implements View.OnClickListener {
    SecondAdapter adapter;
    RecyclerView recyclerReal;

    LinearLayout btnCam, btnGoShopList, btnAddMeasure, recycler_item;
    TextView textShopName, textAddr1, textAddr2, textTel;

    List<RealModel> modelList;
    Camera camera = new Camera();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);

        initView();
        settingOnClick();


        setTitleText(textShopName, textAddr1, textAddr2, textTel);




    }

    private void initView(){
        btnCam = (LinearLayout) findViewById(R.id.btnCam);
        btnGoShopList = (LinearLayout) findViewById(R.id.btnGoShopList);
        btnAddMeasure = (LinearLayout) findViewById(R.id.btnAddMeasure);
        textShopName = (TextView) findViewById(R.id.textShopName);
        textAddr1 = (TextView) findViewById(R.id.textAddr1);
        textAddr2 = (TextView) findViewById(R.id.textAddr2);
        textTel = (TextView) findViewById(R.id.textTel);

        recyclerReal = (RecyclerView) findViewById(R.id.recyclerReal);

        recycler_item = (LinearLayout) findViewById(R.id.recycler_item);
    }

    private void settingOnClick(){
        btnCam.setOnClickListener(this);
        btnGoShopList.setOnClickListener(this);
        btnAddMeasure.setOnClickListener(this);
        recycler_item.setOnClickListener(this);
    }

    private void setTitleText(TextView shopName, TextView addr1, TextView addr2, TextView tel){
        Intent intent = getIntent();
        shopName.setText(intent.getStringExtra("name"));
        addr1.setText(intent.getStringExtra("Addr1"));
        addr2.setText(intent.getStringExtra("Addr2"));
        tel.setText(intent.getStringExtra("Tel"));
    }

    private void setRecycler(){
        adapter = new SecondAdapter(modelList, this);
        recyclerReal.setAdapter(adapter);
        recyclerReal.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnCam:
                camera.takePic(this);
                break;
            case R.id.btnGoShopList:
                Intent intentGoList = new Intent(List2Activity.this, List1Activity.class);
                startActivity(intentGoList);
                finish();
                break;
            case R.id.btnAddMeasure:
                Intent intentAdd = new Intent(List2Activity.this, AddMeasureActivity.class);
                startActivity(intentAdd);
                break;
            case R.id.recycler_item:
                Intent intent = new Intent(List2Activity.this, UpdateMeasureActivity.class);
                startActivity(intent);
                break;
        }
    }
}
