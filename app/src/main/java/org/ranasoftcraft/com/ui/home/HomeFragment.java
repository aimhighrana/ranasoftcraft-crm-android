package org.ranasoftcraft.com.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import org.ranasoftcraft.com.R;
import org.ranasoftcraft.com.databinding.FragmentHomeBinding;

import java.util.Collections;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView attendanceTText = binding.attendanceTodayTitle;
//        attendanceTText.setText("Today attendance");

        final ListView listView = binding.attendanceToday;
        EmployeeArrayAdapter employeeArrayAdapter = new EmployeeArrayAdapter(inflater, new Employee()._defaultEmployee());
        listView.setAdapter(employeeArrayAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}