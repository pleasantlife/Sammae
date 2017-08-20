package android.jhyuk.com.sammae;

import android.content.Context;
import android.content.Intent;
import android.jhyuk.com.sammae.domain.RealModel;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPS on 2017-08-18.
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.Holder> {

    List<RealModel> realModelList = new ArrayList<>();
    Context context;

    public SecondAdapter (List<RealModel> realModelList, Context context){
        this.realModelList = realModelList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_adapter_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        RealModel realModel = realModelList.get(position);
        holder.textRealSizeNumber.setText(position+1);
        holder.textRealSizeWidth.setText(realModel.getWidth());
        holder.textRealSizeHeight.setText(realModel.getHeight());
        holder.textRealSizeSurface.setText(realModel.getSurface());
        holder.textRealSizeHowProcess.setText(realModel.getHowProcess());
        holder.textRealSizeMaterial.setText(realModel.getMaterial());
        holder.textRealSizeRemarks.setText(realModel.getRemarks());
    }

    @Override
    public int getItemCount() {
        return realModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textRealSizeNumber, textRealSizeWidth, textRealSizeHeight, textRealSizeSurface, textRealSizeHowProcess, textRealSizeMaterial, textRealSizeRemarks, btnRealSizeEdit, btnRealSizeDel;
        ImageView imgRealSizePicture1, imgRealSizePicture2, imgRealSizePicture3, imgRealSizePicture4;
        int position;

        public Holder(View itemView) {
            super(itemView);

            imgRealSizePicture1 = (ImageView) itemView.findViewById(R.id.imgRealSizePicture1);
            imgRealSizePicture2 = (ImageView) itemView.findViewById(R.id.imgRealSizePicture2);
            imgRealSizePicture3 = (ImageView) itemView.findViewById(R.id.imgRealSizePicture3);
            imgRealSizePicture4 = (ImageView) itemView.findViewById(R.id.imgRealSizePicture4);

            textRealSizeNumber = (TextView) itemView.findViewById(R.id.textRealSizeNumber);
            textRealSizeWidth = (TextView) itemView.findViewById(R.id.textRealSizeWidth);
            textRealSizeHeight = (TextView) itemView.findViewById(R.id.textRealSizeHeight);
            textRealSizeSurface = (TextView) itemView.findViewById(R.id.textRealSizeSurface);
            textRealSizeHowProcess = (TextView) itemView.findViewById(R.id.textRealSizeHowProcess);
            textRealSizeMaterial = (TextView) itemView.findViewById(R.id.textRealSizeMaterial);
            textRealSizeRemarks = (TextView) itemView.findViewById(R.id.textRealSIzeRemarks);
            btnRealSizeEdit = (TextView) itemView.findViewById(R.id.btnRealSizeEdit);
            btnRealSizeEdit.setOnClickListener(this);
            btnRealSizeDel = (TextView) itemView.findViewById(R.id.btnRealSizeDel);
            btnRealSizeDel.setOnClickListener(this);

        }

        @Override
        public void onClick(View itemView) {
            switch(itemView.getId()){
                case R.id.btnRealSizeEdit:
                    Intent intent = new Intent(context, UpdateMeasureActivity.class);
                    intent.putExtra("POSITION", position);
                    intent.putExtra("Value", realModelList.get(position));
                    context.startActivity(intent);
                    break;
                case R.id.btnRealSizeDel:
                    break;
            }
        }
    }
}