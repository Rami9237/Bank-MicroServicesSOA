package com.example.barememicroservice.controller;

import com.example.barememicroservice.dto.BaremeDto;
import com.example.barememicroservice.service.BaremeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/baremes")
public class BaremeController {

    private final BaremeService baremeService;

    public BaremeController(BaremeService baremeService) {
        this.baremeService = baremeService;
    }

    @PostMapping
    public ResponseEntity<BaremeDto> addBareme(@RequestBody BaremeDto baremeDto) {
        return ResponseEntity.ok(baremeService.addBareme(baremeDto));
    }

    @GetMapping
    public ResponseEntity<Page<BaremeDto>> getAllBaremes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(baremeService.getAllBaremes(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaremeDto> getBaremeById(@PathVariable Long id) {
        return ResponseEntity.ok(baremeService.getBaremeById(id));
    }

    @PutMapping
    public ResponseEntity<BaremeDto> updateBareme(@RequestBody BaremeDto baremeDto) {
        return ResponseEntity.ok(baremeService.updateBareme(baremeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBareme(@PathVariable Long id) {
        baremeService.deleteBareme(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/optimal")
    public ResponseEntity<BaremeDto> findOptimalBareme(
            @RequestParam int duree,
            @RequestParam double montant) {
        return ResponseEntity.ok(baremeService.findOptimalBareme(duree, montant));
    }
}

