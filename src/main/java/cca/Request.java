package cca;

public class Request {
    private User user;
    private String request;
    private String response;
    private String status;

    public Request(User user, String request, String status, String response) {
        this.user = user;
        this.request = request;
        this.status = status;
        this.response = response;
    }

    public User getUser() {
        return user;
    }

    public String getRequest() {
        return request;
    }

    public String getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }
}