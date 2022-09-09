package org.ranasoftcraft.com.ui.employee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.ranasoftcraft.com.MainActivity;
import org.ranasoftcraft.com.data.model.SharedPreferencesConstants;
import org.ranasoftcraft.com.databinding.EmployeeCreateUpdateActivityBinding;
import org.ranasoftcraft.com.databinding.LoginActivityBinding;
import org.ranasoftcraft.com.services.CustomRestTemplate;
import org.ranasoftcraft.com.ui.home.Employee;

import java.util.logging.Logger;

public class CreateUpdateEmployeeActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger(CreateUpdateEmployeeActivity.class.getName());

    private EmployeeCreateUpdateActivityBinding binding;

    @Override
    protected void onCreate(@Nullable @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = EmployeeCreateUpdateActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createUpdateEmpBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String phone = binding.empPhoneNumber.getText().toString();
                String name = binding.empName.getText().toString();
                String address = binding.empAddress.getText().toString();

                logger.info(phone + " " + name + " " + address );

                if(phone == null || "".equals(phone) || phone.length() <10) {
                    Toast.makeText(getApplicationContext(), "Phone number must be 10 digit", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(name == null || "".equals(name)) {
                    Toast.makeText(getApplicationContext(), "Name is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(address == null || "".equals(address)) {
                    Toast.makeText(getApplicationContext(), "Address is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesConstants.AFL_AUTHENTICATION_DETAILS,0);
                String _token = sharedPreferences.getString("a_token", null);
                logger.info("Auth key " + _token);

                Employee employee = new Employee()
                        .setAddress(address)
                        .setEmail("")
                        .setPhone(Long.parseLong(phone))
                        .setUsername(phone)
                        .setName(name)
                        .setPassword(phone);

                boolean status =   new CustomRestTemplate().createUpdateEmployee(employee, _token);
                if(status) {
                    Toast.makeText(getApplicationContext(), "Saved successfully !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getParentActivityIntent());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong !", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
