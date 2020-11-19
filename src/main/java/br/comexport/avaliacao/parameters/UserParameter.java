package br.comexport.avaliacao.parameters;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserParameter implements Serializable {

    @ApiModelProperty(position = 1, example = "Nome do Usuário.")
    private String name;

    @ApiModelProperty(position = 2, example = "Email do Usuário.")
    private String email;

    @ApiModelProperty(position = 3, example = "Data Nascimento do Usuário.")
    private String birthdate;

}
