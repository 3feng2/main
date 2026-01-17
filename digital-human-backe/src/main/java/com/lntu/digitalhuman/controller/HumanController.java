package com.lntu.digitalhuman.controller;

import com.lntu.digitalhuman.entity.HumanData;
import com.lntu.digitalhuman.service.HumanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/human")
@RequiredArgsConstructor
public class HumanController {

    private final HumanService humanService;

    @GetMapping
    public ResponseEntity<List<HumanData>> getAllHumanData() {
        List<HumanData> humanDataList = humanService.getAllHumanData();
        return ResponseEntity.ok(humanDataList);
    }

    @PostMapping
    public ResponseEntity<HumanData> createHumanData(@RequestBody HumanData humanData) {
        HumanData savedData = humanService.saveHumanData(humanData);
        return ResponseEntity.ok(savedData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HumanData> getHumanDataById(@PathVariable String id) {
        HumanData humanData = humanService.getHumanDataById(id);
        if (humanData != null) {
            return ResponseEntity.ok(humanData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HumanData> updateHumanData(@PathVariable String id, @RequestBody HumanData humanData) {
        HumanData existingData = humanService.getHumanDataById(id);
        if (existingData != null) {
            humanData.setId(id);
            HumanData updatedData = humanService.saveHumanData(humanData);
            return ResponseEntity.ok(updatedData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHumanData(@PathVariable String id) {
        HumanData existingData = humanService.getHumanDataById(id);
        if (existingData != null) {
            humanService.deleteHumanData(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<HumanData>> searchHumanDataByName(@RequestParam String name) {
        List<HumanData> results = humanService.findByName(name);
        return ResponseEntity.ok(results);
    }
}