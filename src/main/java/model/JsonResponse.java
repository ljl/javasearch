package model;


public class JsonResponse {
    private String status;
    private String message;

    public JsonResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
