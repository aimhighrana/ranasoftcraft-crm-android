package  org.ranasoftcraft.com.ui.login;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.ranasoftcraft.com.MainActivity;
import org.ranasoftcraft.com.R;
import org.ranasoftcraft.com.data.model.SharedPreferencesConstants;
import org.ranasoftcraft.com.databinding.ActivityMainBinding;
import org.ranasoftcraft.com.databinding.LoginActivityBinding;
import org.ranasoftcraft.com.ui.home.Employee;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {

    private LoginService loginViewModel = null;
private LoginActivityBinding binding;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.loginViewModel = new LoginService();

     binding = LoginActivityBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());


        final EditText username = binding.container.findViewById(R.id.username);
        final EditText password = binding.container.findViewById(R.id.password);
        final Button loginButton = binding.container.findViewById(R.id.login_btn);
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(username.getText().toString(),
                        password.getText().toString());
            }
        };
        username.addTextChangedListener(afterTextChangedListener);
        password.addTextChangedListener(afterTextChangedListener);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(username.getText().toString(),
                            password.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                LoginResult result = loginViewModel.login(username.getText().toString(),
                        password.getText().toString());
                if(result.getSuccess() !=null) {
                    updateUiWithUser(result.getSuccess());
                } else {
                    showLoginFailed(result.getError());
                }

            }
        });
    }

    private void updateUiWithUser(Map<String,String> tokens) {
        // decode the token and set to the shared storage
        SharedPreferences sharedPreferences =  getSharedPreferences(SharedPreferencesConstants.AFL_AUTHENTICATION_DETAILS,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("a_token",tokens.get("a_token"));
        editor.putString("r_token",tokens.get("r_token"));

        // try to decode the jwt and get payload
        JWT jwt = new JWT(tokens.get("a_token"));
        String subjects =  jwt.getSubject();
        logger.info("Subjects " + subjects);
        editor.putString("user_info",subjects);
        editor.commit();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showLoginFailed(Integer errorString) {
        Toast.makeText(getApplicationContext(), "Signing failed!", Toast.LENGTH_SHORT).show();
    }
}