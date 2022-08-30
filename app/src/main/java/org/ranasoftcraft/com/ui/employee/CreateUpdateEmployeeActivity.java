package org.ranasoftcraft.com.ui.employee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.ranasoftcraft.com.databinding.EmployeeCreateUpdateActivityBinding;
import org.ranasoftcraft.com.databinding.LoginActivityBinding;

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
            @Override
            public void onClick(View view) {
                String phone = binding.empPhoneNumber.getText().toString();
                String name = binding.empName.getText().toString();
                String address = binding.empAddress.getText().toString();

                logger.info(phone + " " + name + " " + address );

                Toast.makeText(getApplicationContext(), "Ready to save !", Toast.LENGTH_SHORT).show();



            }
        });
    }
}
