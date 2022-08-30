package org.ranasoftcraft.com.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ranasoftcraft.com.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmployeeCreateUpdate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmployeeCreateUpdate extends Fragment {


    public EmployeeCreateUpdate() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment EmployeeCreateUpdate.
     */
    public static EmployeeCreateUpdate newInstance() {
        EmployeeCreateUpdate fragment = new EmployeeCreateUpdate();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.employee_create_update_activity, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}