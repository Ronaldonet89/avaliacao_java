package br.comexport.avaliacao.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@ApiModel(value = "Error", description = "Modelo de resposta do erro")
@JsonPropertyOrder({"timestamp, status, error, message, path, errorDetails"})
public class Error implements Serializable {

    @ApiModelProperty(position = 1, example = "2019-11-13 12:00:00")
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @ApiModelProperty(position = 2, example = "500")
    @JsonProperty("status")
    private Integer status;
    @SuppressWarnings("all")
    @ApiModelProperty(position = 3, example = "Bad Request")
    @JsonProperty("error")
    private String error;

    @ApiModelProperty(position = 4, example = "Requisição possui campos inválidos")
    @JsonProperty("message")
    private String message;

    @ApiModelProperty(position = 5, example = "/comexport/v1")
    @JsonProperty("path")
    private String path;

    @ApiModelProperty(position = 6)
    @JsonProperty("errorDetails")
    private List<ErrorDetails> errorDetails;

    public Error(LocalDateTime timestamp, Integer status, String error, String message, String path,
                 List<ErrorDetails> errorDetails) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errorDetails = errorDetails;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public List<ErrorDetails> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(List<ErrorDetails> errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public String toString() {
        return "Error{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", errorDetails=" + errorDetails +
                '}';
    }
}



