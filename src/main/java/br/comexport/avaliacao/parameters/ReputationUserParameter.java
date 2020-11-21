package br.comexport.avaliacao.parameters;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ReputationUserParameter implements Serializable {

    @ApiModelProperty(position = 1, example = "Código de Usuário")
    private Long id_user;

    @ApiModelProperty(position = 2, example = "Score.")
    private BigDecimal score;

}
