package org.ranasoftcraft.com.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.ranasoftcraft.com.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class EmployeeArrayAdapter extends RecyclerView.Adapter<EmployeeArrayAdapter.EmployeeViewHolder> {

    private List<Employee> dataSet;

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView textEmpName;
        TextView textPhoneNumber;

        public EmployeeViewHolder(View itemView) {
            super(itemView);
            this.textEmpName = (TextView) itemView.findViewById(R.id.textEmpName);
            this.textPhoneNumber = (TextView) itemView.findViewById(R.id.textPhoneNumber);
        }
    }

    public EmployeeArrayAdapter(List<Employee> data) {
        this.dataSet = data;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_list, parent, false);
        EmployeeViewHolder myViewHolder = new EmployeeViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final EmployeeViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textEmpName;
        textViewName.setText(dataSet.get(listPosition).getName());

        TextView textPhoneNumber = holder.textPhoneNumber;
        textPhoneNumber.setText(String.valueOf(dataSet.get(listPosition).getPhone()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
