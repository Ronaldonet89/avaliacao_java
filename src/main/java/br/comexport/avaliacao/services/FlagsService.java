package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.FlagsEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.FlagsParameter;
import br.comexport.avaliacao.repositories.FlagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlagsService {

    @Autowired
    FlagsRepository flagsRepository;

    public ResponseEntity<Object> applySave(FlagsParameter flagsParameter)  {

        FlagsEntity flagsEntity = new FlagsEntity();
        flagsEntity.setDescription(flagsParameter.getDescription());
        flagsEntity.setEnabled(true);
        flagsRepository.save(flagsEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(flagsEntity);
    }

    public List<FlagsEntity> getAllFlags(){
        return flagsRepository.findAll();
    }

    public FlagsEntity getFlags(Long id) {
        return flagsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flags", "id", id));
    }

    public ResponseEntity<?> deleteFlags(Long id){
        FlagsEntity flags = flagsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flags", "id", id));

        flagsRepository.delete(flags);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateFlags(Long id, FlagsParameter flagsParameter){
        FlagsEntity flags = flagsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flags", "id", id));
        FlagsEntity flagsEntity = new FlagsEntity();
        flagsEntity.setDescription(flagsParameter.getDescription());
        flagsEntity.setEnabled(true);

        flagsRepository.save(flagsEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(flagsEntity);
    }

}
