package org.timofeeva.docs.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.timofeeva.docs.dto.DocsProblem;
import org.timofeeva.docs.service.LocalizedMessageService;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionTranslator {

    private final LocalizedMessageService messageService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DocsProblem> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, NativeWebRequest request
    ) {
        log.error(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        String errorText = ex.getFieldErrors().stream().map(fieldError -> {
            String field = fieldError.getField();
            String error = fieldError.getDefaultMessage();
            if (error == null) throw new RuntimeException();
            return messageService.getLocalizedMessageWithArgs(error, new Object[]{field}, request);
        }).collect(Collectors.joining("; "));
        DocsProblem problem = new DocsProblem(errorText);
        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DocsProblem> handleNotFoundException(
            NotFoundException ex, NativeWebRequest request
    ) {
        log.error(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        DocsProblem problem = new DocsProblem(
                messageService.getLocalizedMessageWithArgs(
                        "validation.reference.default", new Object[]{ex.getField()}, request
                )
        );

        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DocsProblem> handleException(
            Exception ex, NativeWebRequest request
    ) {
        log.error(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return ResponseEntity.internalServerError()
                .body(new DocsProblem(
                        messageService.getLocalizedMessage("unknown.error", request))
                );
    }


}
