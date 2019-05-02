package com.joslabs.majidigitalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MeterStatusAdapter extends RecyclerView.Adapter<MeterStatusAdapter.MeterViewHolder> {
    Context context;
    List<UserModel>userModelList;

    public MeterStatusAdapter(Context context, List<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public MeterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.status_layer,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeterViewHolder holder, int position) {
       holder.txtgeodData.setText(userModelList.get(position).getCity());
       holder.txtname.setText("Name: "+userModelList.get(position).getFirst_name());
       holder.txtCustType.setText("C.Type: "+userModelList.get(position).getCustomer_type());
       holder.txtPhone.setText("Phone: "+userModelList.get(position).getMobile1());
       holder.txtZone.setText("Zone: "+userModelList.get(position).getZone());
       holder.txtBilling.setText("Billing: "+userModelList.get(position).getBillingplans_name());
       if (userModelList.get(position).getStatus()== "0"){
           holder.txtstatus.setText("Deactivated");
           holder.view.setBackgroundResource(R.color.red);
       }
       else {
           holder.txtstatus.setText("Active");
           holder.view.setBackgroundResource(R.color.green);
       }

    }

    @Override
    public int getItemCount() {
       // return userModelList.size();
        return (userModelList!=null)?userModelList.size():0;

    }

    public class MeterViewHolder extends RecyclerView.ViewHolder {
        public TextView txtstatus,txtname,txtCustType,txtPhone,txtZone,txtBilling,txtgeodData;
        public View view;
        public MeterViewHolder(@NonNull View itemView) {
            super(itemView);

            view=itemView.findViewById(R.id.status_view);
            txtstatus=itemView.findViewById(R.id.txt_status);
            txtname=itemView.findViewById(R.id.txt_name);
            txtCustType=itemView.findViewById(R.id.txt_cust_type);
            txtPhone=itemView.findViewById(R.id.txt_phone);
            txtZone=itemView.findViewById(R.id.txt_zone);
            txtBilling=itemView.findViewById(R.id.txt_billing);
            txtgeodData=itemView.findViewById(R.id.txtgeodata);
        }
    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
