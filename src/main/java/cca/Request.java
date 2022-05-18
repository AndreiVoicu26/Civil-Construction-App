package cca;

public class Request {
    private User contractant;
    private String request;
    private String response;
    private String status;

    public Request(User contractant, String request, String status, String response) {
        this.contractant = contractant;
        this.request = request;
        this.status = status;
        this.response = response;
    }

    public User getContractant() {
        return contractant;
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