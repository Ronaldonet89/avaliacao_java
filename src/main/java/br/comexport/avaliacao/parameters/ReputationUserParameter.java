package br.comexport.avaliacao.parameters;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class ReputationUserParameter implements Serializable {

    @ApiModelProperty(position = 1, example = "Código de Usuário")
    private Long id_user;

    @ApiModelProperty(position = 2, example = "Score.")
    private Double score;

}
