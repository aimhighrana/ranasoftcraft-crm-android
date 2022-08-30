package org.ranasoftcraft.com.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.button.MaterialButton;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.FragmentTransaction;
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

import org.ranasoftcraft.com.MainActivity;
import org.ranasoftcraft.com.R;
import org.ranasoftcraft.com.databinding.FragmentHomeBinding;
import org.ranasoftcraft.com.ui.employee.CreateUpdateEmployeeActivity;
import org.ranasoftcraft.com.ui.login.LoginActivity;

import java.util.Collections;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final MaterialCardView attendanceTText = binding.todayEmpStat;
        final MaterialButton addNewEmployee = binding.addNewEmployee;

        attendanceTText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ser for employee list
            }
        });

        addNewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateUpdateEmployeeActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}