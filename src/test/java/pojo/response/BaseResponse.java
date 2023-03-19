package pojo.response;

public class BaseResponse {
    private Boolean success;
    private String message;

    public BaseResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public BaseResponse() {}

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
