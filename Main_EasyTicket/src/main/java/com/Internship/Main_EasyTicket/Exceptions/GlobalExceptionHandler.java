package com.Internship.Main_EasyTicket.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
            String error = String.format("Invalid parameter: '%s' should be of type '%s'", ex.getName(), ex.getRequiredType().getSimpleName());

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler(DuplicateEmailException.class)
        public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException ex) {

            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {


            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(NoContentException.class)
        public ResponseEntity<String> handleNoContentException(NoContentException ex) {

            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
        }
        @ExceptionHandler(DuplicateGroupNameException.class)
        public ResponseEntity<String> handleDuplicateGroupNameException(DuplicateGroupNameException ex) {

            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(GroupNotFoundException.class)
        public ResponseEntity<String> handleGroupNotFoundException(GroupNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(TechnicianNotFoundException.class)
        public ResponseEntity<String> handleTechnicianNotFoundException(TechnicianNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


