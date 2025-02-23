package lukas.wide_tech_backend_2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatusCode;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {

    private HttpStatusCode status;
    private Map<String, String> message;
    private Object detail;

    public void CustomResponse(HttpStatusCode status, Map<String, String> message, Object detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }

    // Getters and setters
    public HttpStatusCode getStatus() {
        return status;
    }

    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

}
