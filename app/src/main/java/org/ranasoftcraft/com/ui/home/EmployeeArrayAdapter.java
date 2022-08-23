package org.ranasoftcraft.com.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.ranasoftcraft.com.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class EmployeeArrayAdapter extends BaseAdapter {

    List<Employee> employees = new ArrayList<>();
    LayoutInflater inflter;

    public EmployeeArrayAdapter(LayoutInflater inflter , List<Employee> employees) {
        this.employees = employees;
        this.inflter = inflter;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Employee getItem(int i) {
        return employees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.employee_list, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView phone = (TextView) view.findViewById(R.id.phone);
        name.setText(employees.get(i).getUsername());
        phone.setText(String.valueOf(employees.get(i).getPhone()));
        return view;
    }
}
