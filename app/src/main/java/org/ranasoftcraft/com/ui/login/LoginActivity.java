package  org.ranasoftcraft.com.ui.login;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
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

import org.ranasoftcraft.com.R;
import org.ranasoftcraft.com.databinding.ActivityMainBinding;
import org.ranasoftcraft.com.databinding.LoginActivityBinding;

public class LoginActivity extends AppCompatActivity {

    private LoginService loginViewModel = null;
private LoginActivityBinding binding;

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
                if(result.getSuccess() != null) {
                    updateUiWithUser(result.getSuccess());
                } else {
                    showLoginFailed(result.getError());
                }

            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        ActivityMainBinding b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    private void showLoginFailed(Integer errorString) {
        Toast.makeText(getApplicationContext(), "Signing failed!", Toast.LENGTH_SHORT).show();
    }
}