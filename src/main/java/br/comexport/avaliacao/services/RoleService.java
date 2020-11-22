package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.RoleEntity;
import br.comexport.avaliacao.entities.UserEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.RoleParameter;
import br.comexport.avaliacao.repositories.RoleRepository;
import br.comexport.avaliacao.repositories.UserRepository;
import br.comexport.avaliacao.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Util util;

    public ResponseEntity<Object> applySave(RoleParameter roleParameter)  {

        ResponseEntity<Object> isvalid = validRole(roleParameter);
        if(isvalid != null)
            return isvalid;

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setDescription(roleParameter.getDescription());
        roleEntity.setEnabled(true);
        roleRepository.save(roleEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(roleEntity);
    }

    public List<RoleEntity> getAllRole(){
        return roleRepository.findAll();
    }

    public RoleEntity getRole(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
    }

    public ResponseEntity<?> deleteRole(Long id){

        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));

        List<UserEntity> userEntities = userRepository.selectUserRole(id);

        if (userEntities.size() == 0) {

            roleRepository.delete(role);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Role não pode ser excluída antes de excluir o usuário!"));
        }

    }

    public ResponseEntity<Object> updateRole(Long id, RoleParameter roleParameter){
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));

        ResponseEntity<Object> isvalid = validRole(roleParameter);
        if(isvalid != null)
            return isvalid;

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(id);
        roleEntity.setDescription(roleParameter.getDescription());
        roleEntity.setEnabled(roleParameter.isEnabled());

        roleRepository.save(roleEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(roleEntity);
    }

    public ResponseEntity<Object> validRole(RoleParameter roleParameter){
        if ( roleParameter.getDescription().isEmpty() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Descrição não foi informada!"));
        }
        return null;
    }

}
