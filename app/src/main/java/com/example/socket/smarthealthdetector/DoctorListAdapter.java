package com.example.socket.smarthealthdetector;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP-User on 4/9/2017.
 */

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.MyViewHolder> {

    private List<Doctor> doctorList;
    public DoctorListAdapter(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Doctor doctor=doctorList.get(position);
        holder.name.setText(doctor.getName());
        holder.phone.setText(doctor.getContactNo());
        holder.address.setText(doctor.getAddress());
        holder.speciality.setText(doctor.getSpeciality());
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,address,phone,speciality;
        MyViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.row_name);
            address= (TextView) itemView.findViewById(R.id.row_address);
            phone= (TextView) itemView.findViewById(R.id.row_phone);
            speciality= (TextView) itemView.findViewById(R.id.row_speciality);

        }
    }
}
