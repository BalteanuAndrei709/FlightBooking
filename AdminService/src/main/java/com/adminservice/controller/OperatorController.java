package com.adminservice.controller;

import com.adminservice.dto.OperatorDTO;
import com.adminservice.dto.RegisterOperatorDto;
import com.adminservice.mapper.OperatorMapper;
import com.adminservice.service.OperatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1.0/operator")
public class OperatorController {

    private  final OperatorService operatorService;
    private final OperatorMapper operatorMapper;

    public OperatorController(OperatorService operatorService, OperatorMapper operatorMapper) {
        this.operatorService = operatorService;
        this.operatorMapper = operatorMapper;
    }

    /**
     * Method that registers a new operator. It receives:
     * - name -> the name of the operator
     * - iban -> the iban of the operator which will be used by payment service to transfer money when
     *           the user books a flight
     * - apiSearch -> the external API of the operator, where detailed flight search will be done.
     * @param registerOperatorDto
     * The DTO that contains information about the operator.
     * @return
     * In case of success, the newly created id of the operator.
     */
    @PostMapping()
    public ResponseEntity<?> registerOperator(@RequestBody RegisterOperatorDto registerOperatorDto){
        var operatorId = operatorService.createOperator(registerOperatorDto);
        var uri = ServletUriComponentsBuilder.fromUriString("/api/v1.0/operator").path("/{operatorId}").buildAndExpand(operatorId).toUri();
        return ResponseEntity.created(uri).body("Operator registered successfully. Operator id : " + operatorId);
    }

    /**
     * Method that will retrieve an operator details based on its id.
     * @param id
     * The id of the operator which we want to retrieve the details for.
     * @return
     * The details of the operator which has the id equal with the received path variable.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OperatorDTO> getOperatorById(@PathVariable Integer id) {
        var operator = operatorService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(operatorMapper.toDto(operator));
    }

    /**
     * Method that will retrieve an operator details based on its name.
     * @param name
     * The name of the operator which we want to retrieve the details for.
     * @return
     * The details of the operator which has the name equal with the received path variable.
     */
    @GetMapping()
    public ResponseEntity<OperatorDTO> getOperatorByName(@RequestParam String name){
        var operator = operatorService.findByName(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(operatorMapper.toDto(operator));
    }

    /**
     * Method that will handle the HTTP request related to updating data of an operator.
     * This method will not handle the updates for flights. The PUT method from FlightController
     * should be used for this scenario.
     * @param registerOperatorDto
     * The new details of the operator.
     * @param id
     * The id of the operator for which we want to update the data.
     * @return
     * An OperatorDTO, with the updated details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OperatorDTO> updateOperator(@RequestBody RegisterOperatorDto registerOperatorDto,
                                                      @PathVariable Integer id){
        var operator = operatorService.updateOperator(registerOperatorDto, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(operatorMapper.toDto(operator));
    }

    /**
     * Method that will handle the HTTP request related to deleting data of an operator.
     * @param id
     * The id of the operator for which the data needs to be deleted.
     * @return
     * A boolean, which is the status of deleting the data.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperator(@PathVariable Integer id){
        var status = operatorService.deleteOperator(id);
        return status ?
                ResponseEntity
                .status(HttpStatus.OK)
                .body("Operator with id " + id + " successfully deleted.") :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error at deleting operator with id " + id + ".");
    }

}
