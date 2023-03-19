package pojo.response;

public class IngredientsResponse {
    private Boolean success;
    private Ingredient[] data;

    public IngredientsResponse(Boolean success, Ingredient[] data) {
        this.success = success;
        this.data = data;
    }

    public IngredientsResponse() {}

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Ingredient[] getData() {
        return data;
    }

    public void setData(Ingredient[] data) {
        this.data = data;
    }
}
