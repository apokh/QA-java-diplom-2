package pojo.response;

public class UserDataSuccess {
    private Boolean success;
    private UserResponse user;

    public UserDataSuccess(Boolean success, UserResponse user) {
        this.success = success;
        this.user = user;
    }

    public UserDataSuccess() {}

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
