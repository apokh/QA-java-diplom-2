package pojo.response;

public class OrdersByAllResponse {
    private Boolean success;
    private OrderByAllResponse[] orders;
    private Integer total;
    private Integer totalToday;

    public OrdersByAllResponse(Boolean success, OrderByAllResponse[] orders, Integer total, Integer totalToday) {
        this.success = success;
        this.orders = orders;
        this.total = total;
        this.totalToday = totalToday;
    }

    public OrdersByAllResponse() {}

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public OrderByAllResponse[] getOrders() {
        return orders;
    }

    public void setOrders(OrderByAllResponse[] orders) {
        this.orders = orders;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(Integer totalToday) {
        this.totalToday = totalToday;
    }
}
