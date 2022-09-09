package org.ranasoftcraft.com.ui.employee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ranasoftcraft.com.R;
import org.ranasoftcraft.com.data.model.SharedPreferencesConstants;
import org.ranasoftcraft.com.databinding.EmployeeCreateUpdateActivityBinding;
import org.ranasoftcraft.com.databinding.EmployeeListActivityBinding;
import org.ranasoftcraft.com.services.CustomRestTemplate;
import org.ranasoftcraft.com.ui.home.Employee;
import org.ranasoftcraft.com.ui.home.EmployeeArrayAdapter;
import org.ranasoftcraft.com.ui.home.Roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EmployeeListActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger(EmployeeListActivity.class.getName());

    private EmployeeListActivityBinding binding;

    private RecyclerView recyclerView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = EmployeeListActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesConstants.AFL_AUTHENTICATION_DETAILS,0);
        String _token = sharedPreferences.getString("a_token", null);
        logger.info("Auth key " + _token);

        JSONArray employeeList  =   new CustomRestTemplate().getEmployeeList(_token);

        logger.info(employeeList.toString());

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // convert into list of emp
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        List<Employee> employees = new ArrayList<>();
        for(int i =0;i<employeeList.length();i++) {
            try {
                String emp = employeeList.getString(i);
                Employee employee = objectMapper.readValue(emp, Employee.class);
                Set<String> rolesIds = employee.getRoles().stream().map(Roles::getRoleId).collect(Collectors.toSet());
                if(!rolesIds.contains("101")) {
                    employees.add(employee);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        RecyclerView.Adapter adapter = new EmployeeArrayAdapter(employees);
        recyclerView.setAdapter(adapter);

    }
}
