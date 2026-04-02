package senai.centroweg.weg_workshop_api.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senai.centroweg.weg_workshop_api.application.dto.request.SchoolClassRequestDTO;
import senai.centroweg.weg_workshop_api.application.service.SchoolClassService;
import senai.centroweg.weg_workshop_api.domain.model.SchoolClass;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @PostMapping
    public ResponseEntity<SchoolClass> createClass(@RequestBody @Valid SchoolClassRequestDTO request) {

        SchoolClass newClass = schoolClassService.createClass(
                request.name(),
                request.teacherId(),
                request.studentIds()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(newClass);
    }

    @GetMapping
    public ResponseEntity<List<SchoolClass>> listAll() {
        return ResponseEntity.ok(schoolClassService.listAll());
    }
}