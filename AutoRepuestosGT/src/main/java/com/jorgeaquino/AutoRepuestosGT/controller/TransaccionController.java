package com.jorgeaquino.AutoRepuestosGT.controller;

import com.jorgeaquino.AutoRepuestosGT.model.Transaccion;
import com.jorgeaquino.AutoRepuestosGT.service.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @GetMapping
    public List<Transaccion> getAllTransacciones() {
        return transaccionService.getAllTransacciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> getTransaccionById(@PathVariable Integer id) {
        try {
            Transaccion transaccion = transaccionService.getTransaccionById(id);
            return ResponseEntity.ok(transaccion);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createTransaccion(@Valid @RequestBody Transaccion transaccion) {
        Transaccion createdTransaccion = transaccionService.saveTransaccion(transaccion);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Transacción agregada exitosamente con ID: " + createdTransaccion.getIdTransaccion());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateTransaccion(@PathVariable Integer id, @Valid @RequestBody Transaccion transaccion) {
        transaccionService.updateTransaccion(id, transaccion);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Transacción con ID " + id + " actualizada exitosamente");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTransaccion(@PathVariable Integer id) {
        transaccionService.deleteTransaccion(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Transacción eliminada exitosamente");
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