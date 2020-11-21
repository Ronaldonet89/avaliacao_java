package br.comexport.avaliacao.parameters;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class QuestionParameter implements Serializable {

    @ApiModelProperty(position = 1, example = "Código do Usuário")
    private Long id_user;

    @ApiModelProperty(position = 2, example = "Código da flag")
    private Long id_flag;

    @ApiModelProperty(position = 3, example = "Comentário")
    private String comment;

    @ApiModelProperty(position = 4, example = "Resolvido")
    private boolean resolved;

}
