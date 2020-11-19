package br.comexport.avaliacao.controllers;

import br.comexport.avaliacao.entities.RoleEntity;
import br.comexport.avaliacao.errors.Error;
import br.comexport.avaliacao.parameters.RoleParameter;
import br.comexport.avaliacao.services.RoleService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/comexport/v1")
public class RoleController {

    @Autowired
    RoleService roleService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = RoleEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })

    @GetMapping("/roles")
    public List<RoleEntity> getAllRoles() {

        return roleService.getAllRole();
    }

    @PostMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postRole(@RequestBody RoleParameter roleParameter)  {

        return roleService.applySave(roleParameter);
    }

    @GetMapping(value = "/role/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoleEntity getRole(@PathVariable(value = "id") Long id) {
        return roleService.getRole(id);
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable(value = "id") Long id) {
        return roleService.deleteRole(id);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable(value = "id") Long id,
                                 @RequestBody RoleParameter roleParameter) {

        return roleService.updateRole(id, roleParameter);
    }

}
