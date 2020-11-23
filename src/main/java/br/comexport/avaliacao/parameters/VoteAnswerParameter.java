package br.comexport.avaliacao.parameters;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class VoteAnswerParameter implements Serializable {

    @ApiModelProperty(position = 1, example = "Código do Usuário")
    private Long id_user;

    @ApiModelProperty(position = 2, example = "Código da pergunta")
    private Long id_question;

    @ApiModelProperty(position = 3, example = "Código da resposta")
    private Long id_answer;

    @ApiModelProperty(position = 4, example = "Score")
    private Double score;

}
