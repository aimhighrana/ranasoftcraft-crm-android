package  org.ranasoftcraft.com.ui.login;

import android.support.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {

    private LoggedInUserView success;

    private Integer error;

    LoginResult(Integer error) {
        this.error = error;
    }

    LoginResult(LoggedInUserView success) {
        this.success = success;
    }

    LoggedInUserView getSuccess() {
        return success;
    }

    Integer getError() {
        return error;
    }
}