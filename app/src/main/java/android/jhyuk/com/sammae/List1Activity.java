package android.jhyuk.com.sammae;

import android.content.Intent;
import android.jhyuk.com.sammae.domain.StoreInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class List1Activity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView recycler1;
    LinearLayout btnSearch, btnAddShop;
    ImageView imgAdd, imgSearch;
    FirstAdapter adapter;
    List<StoreInfo> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1);
        setWidget();
        setButton();

        adapter = new FirstAdapter(this, datas);
        recycler1.setAdapter(adapter);
        recycler1.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setWidget(){
        recycler1 = (RecyclerView) findViewById(R.id.recycler1);
        btnSearch = (LinearLayout) findViewById(R.id.btnSearch);
        btnAddShop = (LinearLayout) findViewById(R.id.btnAddShop);

        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgSearch = (ImageView) findViewById(R.id.imgSearch);
    }

    private void setButton(){
        btnSearch.setOnClickListener(this);
        btnAddShop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSearch:

                break;
            case R.id.btnAddShop:
                Intent intent = new Intent(this, AddShopInfoActivity.class);
                startActivity(intent);
                break;
        }
    }


}
