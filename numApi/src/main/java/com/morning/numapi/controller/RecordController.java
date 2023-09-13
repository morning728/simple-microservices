package com.morning.numapi.controller;


import com.morning.numapi.model.DTO.RecordDTO;
import com.morning.numapi.model.Record;
import com.morning.numapi.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/sorted")
    public List<Record> getSortedRecords(@RequestParam(name = "username", required = true) String username){
        return recordService.findByUsernameSortedByDate(username);
    }
    @GetMapping("/sorted-reversed")
    public List<Record> getReversedSortedRecords(@RequestParam(name = "username", required = true) String username){
        return recordService.findByUsernameSortedByDateReversed(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRecordById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(recordService.findById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity getRecords(@RequestParam(name = "username", required = false) String username) {
        if(username == null) return new ResponseEntity<>(recordService.findAll(), HttpStatus.OK);
        return new ResponseEntity<>(recordService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity addNewRecord(@RequestBody RecordDTO dto){
        try {
            recordService.addRecord(dto.toRecord());
        } catch (Exception e){
            return (ResponseEntity) ResponseEntity.of(ProblemDetail.forStatus(404)); // FIX
        }
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity updateRecord(@PathVariable(name = "id") Long id, @RequestBody RecordDTO dto) {////////////////////////
        try {
            recordService.updateRecord(dto.toRecord());
        } catch (Exception e){
            return (ResponseEntity) ResponseEntity.of(ProblemDetail.forStatus(404)); // FIX
        }
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecord(@PathVariable(name = "id") Long id) {////////////////////////
        try {
            recordService.deleteRecord(id);
        } catch (Exception e){
            return (ResponseEntity) ResponseEntity.of(ProblemDetail.forStatus(404)); // FIX
        }
        return ResponseEntity.ok(200);
    }

}