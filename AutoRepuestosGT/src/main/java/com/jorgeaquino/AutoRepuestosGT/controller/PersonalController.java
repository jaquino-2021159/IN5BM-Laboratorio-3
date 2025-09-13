package com.jorgeaquino.AutoRepuestosGT.controller;

import com.jorgeaquino.AutoRepuestosGT.model.Personal;
import com.jorgeaquino.AutoRepuestosGT.service.PersonalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/personal")
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping
    public List<Personal> getAllPersonal() {
        return personalService.getAllPersonal();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> getPersonalById(@PathVariable Integer id) {
        try {
            Personal personal = personalService.getPersonalById(id);
            return ResponseEntity.ok(personal);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createPersonal(@Valid @RequestBody Personal personal) {
        Personal createdPersonal = personalService.savePersonal(personal);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Personal agregado exitosamente con ID: " + createdPersonal.getIdPersonal());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updatePersonal(@PathVariable Integer id, @Valid @RequestBody Personal personal) {
        personalService.updatePersonal(id, personal);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Personal con ID " + id + " actualizado exitosamente");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePersonal(@PathVariable Integer id) {
        personalService.deletePersonal(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Personal eliminado exitosamente");
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleConflictExceptions(RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }
}