package com.amicta.kazt.quislock.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amicta.kazt.quislock.model.ModelBPMainMenuItem;
import com.example.kazt.quislock.R;
import com.example.kazt.quislock.databinding.LayoutPopupBpTukarBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazt on 4/14/17.
 */

public class RecyclerBPMenuMainAdapter extends RecyclerView.Adapter<RecyclerBPMenuMainAdapter.BPMMItemViewHolder> {
    private List<ModelBPMainMenuItem> items;
    private Context c;
    private Dialog dialog;

    public RecyclerBPMenuMainAdapter(Context context, List<ModelBPMainMenuItem> itemsData) {
        this.items = itemsData;
        this.c = context;
    }

    @Override
    public BPMMItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_inflate_bpmmitem_card, parent, false);
        return new BPMMItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BPMMItemViewHolder holder, int position) {
        ModelBPMainMenuItem itemData = items.get(position);
        holder.ivInflateBPMMItemIkon.setImageResource(itemData.getImgItem());
        if (position == 2){
            holder.tvInflateBPMMItemRupiah.setText("Matematika Kelas 6");
        }else if (position == 3){
            holder.tvInflateBPMMItemRupiah.setText("IPA kelas 8");
        }else if (position == 4){
            holder.tvInflateBPMMItemRupiah.setText("Bahasa indonesia Kelas 7");
        }else if (position == 5){
            holder.tvInflateBPMMItemRupiah.setText("IPS kelas 10");
        }
        holder.tvInflateBPMMItemPoint.setText(""+itemData.getPoint());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class BPMMItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivInflateBPMMItemIkon;
        public TextView tvInflateBPMMItemRupiah;
        public TextView tvInflateBPMMItemPoint;
        public Button btnInflateBPMMItemRedeem;

        public BPMMItemViewHolder(View itemView) {
            super(itemView);
            ivInflateBPMMItemIkon = (ImageView) itemView.findViewById(R.id.iv_inflate_bpmmitem_ikon);
            tvInflateBPMMItemRupiah = (TextView) itemView.findViewById(R.id.tv_inflate_bpmmitem_rupiah);
            tvInflateBPMMItemPoint = (TextView) itemView.findViewById(R.id.tv_inflate_bpmmitem_point);
            btnInflateBPMMItemRedeem = (Button) itemView.findViewById(R.id.btn_inflate_bpmmitem_redeem);
            btnInflateBPMMItemRedeem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new Dialog(c);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    LayoutPopupBpTukarBinding binding = LayoutPopupBpTukarBinding.inflate(LayoutInflater.from(c),null);
                    dialog.setContentView(binding.getRoot());
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    Window window = dialog.getWindow();
                    WindowManager.LayoutParams params = window.getAttributes();
                    params.gravity = Gravity.CENTER;
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                    binding.btnPopupBpKonfirmasiTidak.setOnClickListener(v1 -> dialog.dismiss());
                    binding.btnPopupBpKonfirmasiYa.setOnClickListener(v1 -> dialog.dismiss());
                }
            });
        }
    }
}
