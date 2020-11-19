package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.RoleEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.RoleParameter;
import br.comexport.avaliacao.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ResponseEntity<Object> applySave(RoleParameter roleParameter)  {

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

        roleRepository.delete(role);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateRole(Long id, RoleParameter roleParameter){
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setDescription(roleParameter.getDescription());
        roleEntity.setEnabled(true);

        roleRepository.save(roleEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(roleEntity);
    }

}
