package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.UserEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.errors.Error;
import br.comexport.avaliacao.errors.ErrorDetails;
import br.comexport.avaliacao.parameters.UserParameter;
import br.comexport.avaliacao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> applySave(UserParameter userParameter)  {

        ResponseEntity<Object> isvalid = validUser(userParameter);
        if(isvalid != null)
            return isvalid;

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userParameter.getName());
        userEntity.setEmail(userParameter.getEmail());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = format.parse(userParameter.getBirthdate());
            userEntity.setBirthdate(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userEntity);
    }

    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }

    public UserEntity getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public ResponseEntity<?> deleteUser(Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateUser(Long id, UserParameter userParameter){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        ResponseEntity<Object> isvalid = validUser(userParameter);
        if(isvalid != null)
            return isvalid;

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userParameter.getName());
        userEntity.setEmail(user.getEmail());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = format.parse(userParameter.getBirthdate());
            userEntity.setBirthdate(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userEntity);
    }

    public Object exceptError(String message, String detalheMens){
        List<ErrorDetails> errorDetails = new ArrayList<>();
        ErrorDetails errorDet = new ErrorDetails(UUID.randomUUID().toString(), "400", message, null);
        errorDetails.add(errorDet);
        return new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), detalheMens, "/comexport/v1", errorDetails);
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public boolean isDataValid(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public ResponseEntity<Object> validUser(UserParameter userParameter){
        if ( userParameter.getName().isEmpty() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptError("Dados inválidos","Nome não informado!"));
        }
        if ( userParameter.getEmail().isEmpty() || !isValidEmailAddress(userParameter.getEmail()) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptError("Dados inválidos","Email não informado ou inválido!"));
        }

        if ( userParameter.getBirthdate().isEmpty() || !isDataValid(userParameter.getBirthdate()) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exceptError("Dados inválidos","Data nascimento não informado ou inválida!"));
        }
        return null;
    }
}
