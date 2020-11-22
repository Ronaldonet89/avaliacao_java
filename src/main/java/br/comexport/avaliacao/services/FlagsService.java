package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.FlagsEntity;
import br.comexport.avaliacao.entities.QuestionEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.FlagsParameter;
import br.comexport.avaliacao.repositories.FlagsRepository;
import br.comexport.avaliacao.repositories.QuestionRepository;
import br.comexport.avaliacao.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlagsService {

    @Autowired
    FlagsRepository flagsRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    Util util;

    public ResponseEntity<Object> applySave(FlagsParameter flagsParameter)  {

        ResponseEntity<Object> isvalid = validFlags(flagsParameter);
        if(isvalid != null)
            return isvalid;

        FlagsEntity flagsEntity = new FlagsEntity();
        flagsEntity.setDescription(flagsParameter.getDescription());
        flagsEntity.setEnabled(flagsParameter.isEnabled());
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

        List<QuestionEntity> questionEntity = questionRepository.selectQuetionFlads(flags.getId());
        if (questionEntity.size() == 0) {

            flagsRepository.delete(flags);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Flag não pode ser excluida antes de excluir a pergunta!"));
        }

    }

    public ResponseEntity<Object> updateFlags(Long id, FlagsParameter flagsParameter){
        ResponseEntity<Object> isvalid = validFlags(flagsParameter);
        if(isvalid != null)
            return isvalid;

        FlagsEntity flags = flagsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flags", "id", id));
        FlagsEntity flagsEntity = new FlagsEntity();
        flagsEntity.setId(id);
        flagsEntity.setDescription(flagsParameter.getDescription());
        flagsEntity.setEnabled(true);

        flagsRepository.save(flagsEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(flagsEntity);
    }

    public ResponseEntity<Object> validFlags(FlagsParameter flagsParameter){
        if ( flagsParameter.getDescription().isEmpty() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Descrição não foi informada!"));
        }
        return null;
    }

}
