package com.callor.hello.myaddrbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by callor on 2017-04-18.
 */

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.VHolder> {

    static List<AddrTableVO> addrDTO;
    static Context context;

    public RcAdapter(Context context, List<AddrTableVO> addrDTO){
        this.context = context;
        this.addrDTO = addrDTO;
    }

    @Override
    public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayout  = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.item_row,null);
        VHolder viewHolder = new VHolder(itemLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VHolder holder, int position) {
        holder.name.setText(addrDTO.get(position).getSname());
        holder.birth.setText(addrDTO.get(position).getSbirth());
        holder.tel.setText(addrDTO.get(position).getStel());
    }

    @Override
    public int getItemCount() {
        return addrDTO.size();
    }

    public class VHolder extends RecyclerView.ViewHolder {

            TextView name,birth,tel;
            public VHolder(View itemView) {
                super(itemView);

                 name = (TextView) itemView.findViewById(R.id.list_name);
                 birth = (TextView) itemView.findViewById(R.id.list_birth);
                 tel = (TextView) itemView.findViewById(R.id.list_tel);

            }
    }
}
