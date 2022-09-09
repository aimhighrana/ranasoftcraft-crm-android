package org.ranasoftcraft.com.ui.home;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.button.MaterialButton;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.ranasoftcraft.com.MainActivity;
import org.ranasoftcraft.com.R;
import org.ranasoftcraft.com.data.model.LoggedInUser;
import org.ranasoftcraft.com.data.model.SharedPreferencesConstants;
import org.ranasoftcraft.com.databinding.EmployeeAttendanceBinding;
import org.ranasoftcraft.com.databinding.FragmentHomeBinding;
import org.ranasoftcraft.com.ui.employee.CreateUpdateEmployeeActivity;
import org.ranasoftcraft.com.ui.employee.EmployeeListActivity;
import org.ranasoftcraft.com.ui.login.LoginActivity;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public static final int RequestPermissionCode = 1;

    private ImageView attendanceEmployeeImage;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SharedPreferencesConstants.AFL_AUTHENTICATION_DETAILS, 0);
        String userInfo =  sharedPreferences.getString("user_info",null);
        LoggedInUser loggedInUser = null;
        try{
            loggedInUser = new ObjectMapper().readValue(userInfo, LoggedInUser.class);
        }catch (Exception e) {
            e.printStackTrace();
        }

        final MaterialCardView attendanceTText = binding.todayEmpStat;
        final MaterialButton addNewEmployee = binding.addNewEmployee;
        final MaterialButton getEmployeeList = binding.getEmployeeList;
        final EmployeeAttendanceBinding attendance = binding.attendanceCard;

        final MaterialButton attendance_in_out_btn = binding.attendanceCard.attendanceInOutBtn;

        attendanceEmployeeImage = binding.attendanceCard.attendanceEmployeeImg;

        if(loggedInUser.getRoles().contains("ADMIN")) {
            attendance.getRoot().setVisibility(View.GONE);
        } else {
            binding.empActionsLayout.setVisibility(View.GONE);
            binding.todayEmpStatLayout.setVisibility(View.GONE);
        }

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

        getEmployeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EmployeeListActivity.class);
                startActivity(intent);
            }
        });

        attendance_in_out_btn.setOnClickListener(new View.OnClickListener() {
            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                EnableRuntimePermission();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 0);


            }
        });






        return root;
    }


    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {
            Toast.makeText(getActivity(),"CAMERA permission allows us to punch in.",     Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            attendanceEmployeeImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @androidx.annotation.NonNull String[] permissions, @NonNull @androidx.annotation.NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission Granted !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Permission Canceled !", Toast.LENGTH_LONG).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}