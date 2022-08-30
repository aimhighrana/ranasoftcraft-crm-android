package org.ranasoftcraft.com.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Patterns;

import org.ranasoftcraft.com.data.model.LoggedInUser;
import org.ranasoftcraft.com.R;
import org.ranasoftcraft.com.services.CustomRestTemplate;

import java.util.Map;
import java.util.logging.Logger;

public class LoginService {

    private CustomRestTemplate customRestTemplate;

    public LoginService() {
        this.customRestTemplate = new CustomRestTemplate();
    }

    private final Logger logger = Logger.getLogger(LoginService.class.getName());

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LoginResult login(String username, String password) {
        logger.info("username {"+username+"} password {"+password+"}");
        if(username == null || "".equalsIgnoreCase(username)) {
            return new LoginResult(401);
        }
        if(password == null || "".equalsIgnoreCase(password)) {
            return new LoginResult(401);
        }

        Map<String,String> tokens = customRestTemplate.login(username, password);
        if(tokens !=null) {
            return new LoginResult(tokens);
        } else {
            return new LoginResult(401);
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}