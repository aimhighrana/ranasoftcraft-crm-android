package org.ranasoftcraft.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.ranasoftcraft.com.data.model.SharedPreferencesConstants;
import org.ranasoftcraft.com.databinding.ActivityMainBinding;
import org.ranasoftcraft.com.ui.login.LoginActivity;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check if the user already logged in then fine otherwise show login page
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesConstants.AFL_AUTHENTICATION_DETAILS,0);
        String userInfo =  sharedPreferences.getString("user_info",null);
        logger.info("User info " + userInfo);
        if(userInfo == null) {
            Intent intent = new Intent(this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return;
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}