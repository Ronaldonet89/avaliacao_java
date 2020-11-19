package br.comexport.avaliacao.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value = "ErrorDetails", description = "Informação detalhada dos erros ocorridos.")
@JsonPropertyOrder({"uniqueId, informationCode, message, field, args"})
public class ErrorDetails implements Serializable {

    @ApiModelProperty(position = 1, example = "10cb71fc-a126-4708-bb74-34dcd6ae47d5")
    @JsonProperty("uniqueId")
    private final String uniqueId;

    @ApiModelProperty(position = 2, example = "Pattern")
    @JsonProperty("informationCode")
    private final String informationCode;

    @ApiModelProperty(position = 3, example = "Parametro de serviço não é válido")
    @JsonProperty("message")
    private String message;

    @ApiModelProperty(position = 5, example = "K")
    @JsonProperty("args")
    private final String[] args;

    public ErrorDetails(String uniqueId, String informationCode, String message, String[] args) {
        this.uniqueId = uniqueId;
        this.informationCode = informationCode;
        this.message = message;
        this.args = args;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getInformationCode() {
        return informationCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "uniqueId='" + uniqueId + '\'' +
                ", informationCode='" + informationCode + '\'' +
                ", message=" + message +
                ", args=" + args +
                '}';
    }
}
