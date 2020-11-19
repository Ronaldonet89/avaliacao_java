package br.comexport.avaliacao.controllers;

import br.comexport.avaliacao.entities.UserEntity;
import br.comexport.avaliacao.parameters.UserParameter;
import br.comexport.avaliacao.services.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.comexport.avaliacao.errors.Error;

@RestController
@RequestMapping(value = "/comexport/v1")
public class UserController {

    @Autowired
    UserService userService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = UserEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })

    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {

        return userService.getAllUser();
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postUser(@RequestBody UserParameter userParameter)  {

        return userService.applySave(userParameter);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> putAllUser(@PathVariable(value = "id") Long id,
                                 @RequestBody UserParameter userParameter) {

        return userService.updateUser(id, userParameter);
    }

}
