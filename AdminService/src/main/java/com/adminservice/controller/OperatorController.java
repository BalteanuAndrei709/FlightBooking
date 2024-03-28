package com.adminservice.controller;


import com.adminservice.dto.FlightDTO;
import com.adminservice.dto.OperatorDTO;
import com.adminservice.model.Flight;
import com.adminservice.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operator")
public class OperatorController {

    private  final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OperatorDTO>> getOperatorById(@PathVariable Integer id){
        return new ResponseEntity<>(operatorService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/operatorName/{operatorName}")
    public ResponseEntity<Optional<List<FlightDTO>>> getFlightsByOperatorName(@PathVariable String operatorName){
        return new ResponseEntity<>(operatorService.findFlightsByOperatorName(operatorName), HttpStatus.OK);
    }

    @PostMapping("/addOperator")
    public ResponseEntity<Void> addOperator(@RequestBody OperatorDTO operatorDTO){
        operatorService.createOperator(operatorDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/updateOperator/{id}")
    public ResponseEntity<Boolean> updateOperator(@RequestBody OperatorDTO operatorDTO, @PathVariable Integer id){
        return new ResponseEntity<>(operatorService.updateOperator(operatorDTO, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteOperator/{id}")
    public ResponseEntity<Boolean> deleteOperator(@PathVariable Integer id){
        return new ResponseEntity<>(operatorService.deleteOperator(id), HttpStatus.OK);
    }

}
