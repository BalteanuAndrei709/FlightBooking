package com.adminservice.mapper;


import com.adminservice.dto.OperatorDTO;
import com.adminservice.model.Operator;
import org.springframework.stereotype.Component;

@Component
public class OperatorMapper {

    public OperatorDTO toDto(Operator operator) {
        if (operator == null)
            return null;
        else {
            OperatorDTO operatorDTO = new OperatorDTO();
            operatorDTO.setId(operator.getId());
            operatorDTO.setIban(operator.getIban());
            operatorDTO.setName(operator.getName());
            operatorDTO.setApiSearch(operator.getApiSearch());
            operatorDTO.setAllFlights(operator.getAllFlights());
            return operatorDTO;
        }
    }

    public Operator toEntity(OperatorDTO operatorDTO) {
        if (operatorDTO == null) {
            return null;
        } else {
            Operator operator = new Operator();
            operator.setId(operatorDTO.getId());
            operator.setIban(operatorDTO.getIban());
            operator.setName(operatorDTO.getName());
            operator.setApiSearch(operatorDTO.getApiSearch());
            operator.setAllFlights(operatorDTO.getAllFlights());
            return operator;
        }
    }
}
