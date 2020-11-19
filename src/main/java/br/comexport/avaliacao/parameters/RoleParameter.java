package br.comexport.avaliacao.parameters;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RoleParameter implements Serializable {

    @ApiModelProperty(position = 1, example = "Descrição")
    private String description;

    @ApiModelProperty(position = 2, example = "Habilitado.")
    private boolean enabled;

}
