package br.comexport.avaliacao.util;

import br.comexport.avaliacao.errors.Error;
import br.comexport.avaliacao.errors.ErrorDetails;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Util {
    public Object exceptError(String message, String detalheMens){
        List<ErrorDetails> errorDetails = new ArrayList<>();
        ErrorDetails errorDet = new ErrorDetails(UUID.randomUUID().toString(), "400", message, null);
        errorDetails.add(errorDet);
        return new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), detalheMens, "/comexport/v1", errorDetails);
    }
}
