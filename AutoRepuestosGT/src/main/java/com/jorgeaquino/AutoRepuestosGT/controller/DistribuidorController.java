package com.jorgeaquino.AutoRepuestosGT.controller;

import com.jorgeaquino.AutoRepuestosGT.model.Distribuidor;
import com.jorgeaquino.AutoRepuestosGT.service.DistribuidorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/distribuidores")
public class DistribuidorController {

    private final DistribuidorService distribuidorService;

    public DistribuidorController(DistribuidorService distribuidorService) {
        this.distribuidorService = distribuidorService;
    }

    @GetMapping
    public List<Distribuidor> getAllDistribuidores(){
        return distribuidorService.getAllDistribuidores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Distribuidor> getDistribuidorById(@PathVariable Integer id){
        try {
            Distribuidor distribuidor = distribuidorService.getDistribuidorById(id);
            return ResponseEntity.ok(distribuidor);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createDistribuidor(@Valid @RequestBody Distribuidor distribuidor){
        Distribuidor createdDistribuidor = distribuidorService.saveDistribuidor(distribuidor);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Distribuidor agregado exitosamente con ID: " + createdDistribuidor.getIdDistribuidor());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateDistribuidor(@PathVariable Integer id, @Valid @RequestBody Distribuidor distribuidor) {
        Distribuidor updatedDistribuidor = distribuidorService.updateDistribuidor(id, distribuidor);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Distribuidor con ID " + id + " actualizado exitosamente");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteDistribuidor(@PathVariable Integer id){
        distribuidorService.deleteDistribuidor(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Distribuidor eliminado exitosamente");
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