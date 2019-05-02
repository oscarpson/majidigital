package com.joslabs.majidigitalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MpesaAdapter extends RecyclerView.Adapter<MpesaAdapter.MpesaViewHolder> {
    Context context;
    List<MpesaModel>mpesaModelList;

    public MpesaAdapter(Context context, List<MpesaModel> mpesaModelList) {
        this.context = context;
        this.mpesaModelList = mpesaModelList;
    }

    @NonNull
    @Override
    public MpesaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MpesaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mpesa_layer,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MpesaViewHolder holder, int position) {

        holder.txtgeodData.setText(mpesaModelList.get(position).getTransID());
        holder.txtname.setText("Name: "+mpesaModelList.get(position).getFirstName()+"\t"+mpesaModelList.get(position).getLastName());
        holder.txtCustType.setText("Id: "+mpesaModelList.get(position).getTransID());
        holder.txtPhone.setText("Phone: "+mpesaModelList.get(position).getMSISDN());
        holder.txtZone.setText("Ref: "+mpesaModelList.get(position).getBillRefNumber());
        holder.txtBilling.setText("Amount: "+mpesaModelList.get(position).getTransAmount());
        holder.txt_location.setText("Time: "+mpesaModelList.get(position).getTransTime());

            holder.txtstatus.setText("Mpesa");
            holder.view.setBackgroundResource(R.color.green);


    }

    @Override
    public int getItemCount() {
        // return userModelList.size();
        return (mpesaModelList!=null)?mpesaModelList.size():0;

    }

    public class MpesaViewHolder extends RecyclerView.ViewHolder {
        public TextView txtstatus,txtname,txtCustType,txtPhone,txtZone,txtBilling,txtgeodData,txt_location;
        public View view;
        public MpesaViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.status_view);
            txtstatus=itemView.findViewById(R.id.txt_status);
            txtname=itemView.findViewById(R.id.txt_name);
            txtCustType=itemView.findViewById(R.id.txt_cust_type);
            txtPhone=itemView.findViewById(R.id.txt_phone);
            txtZone=itemView.findViewById(R.id.txt_zone);
            txtBilling=itemView.findViewById(R.id.txt_billing);
            txtgeodData=itemView.findViewById(R.id.txtgeodata);
            txt_location=itemView.findViewById(R.id.txt_location);
        }
    }
}
