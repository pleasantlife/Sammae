package android.jhyuk.com.sammae;

import android.content.Context;
import android.content.Intent;
import android.jhyuk.com.sammae.domain.StoreInfo;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxx on 2017. 8. 17..
 */

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.Holder>{
    List<StoreInfo> datas = new ArrayList<>();
    StoreInfo storeInfo = new StoreInfo();
    Context context;
    public FirstAdapter(Context context, List<StoreInfo> datas){
        this.datas = datas;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_adapter_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        storeInfo = datas.get(position);
        holder.setPosition(position);
        holder.textShopName.setText(storeInfo.getCode() + storeInfo.getName());
        holder.address1.setText(storeInfo.getAddress());
        holder.address2.setText(storeInfo.getAddress2());
        holder.shopNum.setText(storeInfo.getTel());
        Glide.with(context).load(storeInfo.getStoreImage()).into(holder.imgMain);
        if(storeInfo.getMearsureComplete().get(position)){
            holder.imgMsrFinish.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        int position;
        TextView textShopName, address1, address2, shopNum;
        ImageView imgCall, imgMain;
        ImageView imgConsFinish, imgMsrFinish;
        public Holder(View v) {
            super(v);
            imgCall = v.findViewById(R.id.imgCall);
            imgMain = v.findViewById(R.id.imgMain);
            imgMsrFinish = v.findViewById(R.id.imgMsrFinish);
            imgConsFinish = v.findViewById(R.id.imgConsFinish);
            imgCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    call();
                }
            });
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("POSITION", position);
                    intent.putExtra("Value", datas.get(position));
                    context.startActivity(intent);
                }
            });

        }

        public void setPosition(int position){
            this.position = position;
        }
    }

    private void call(){
        String phoneNumber = storeInfo.getTel();
        Uri callUri = Uri.parse("tel:"+ phoneNumber);
        Intent intent = new Intent(Intent.ACTION_DIAL, callUri);
        context.startActivity(intent);
    }
}
