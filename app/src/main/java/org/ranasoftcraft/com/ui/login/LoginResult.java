package  org.ranasoftcraft.com.ui.login;

import android.support.annotation.Nullable;

import java.util.Map;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {

    private LoggedInUserView success;

    private Integer error;

    private Map<String, String> tokens;

    LoginResult(Integer error) {
        this.error = error;
    }

    LoginResult(Map<String, String> tokens) {
        this.tokens = tokens;
    }

    Map<String, String> getSuccess() {
        return tokens;
    }

    Integer getError() {
        return error;
    }
}