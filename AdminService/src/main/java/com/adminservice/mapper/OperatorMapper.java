package com.adminservice.mapper;

import com.adminservice.dto.OperatorDTO;
import com.adminservice.dto.RegisterOperatorDto;
import com.adminservice.model.Operator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class OperatorMapper {

    private final FlightMapper flightMapper;

    public OperatorMapper(FlightMapper flightMapper) {
        this.flightMapper = flightMapper;
    }

    /**
     * Method that maps an Operator to an OperatorDto.
     * @param operator
     * The entity of type Operator.
     * @return
     * A OperatorDto
     */
    public OperatorDTO toDto(Operator operator) {
        if (operator == null)
            return null;
        else {
            OperatorDTO operatorDTO = new OperatorDTO();
            operatorDTO.setId(operator.getId());
            operatorDTO.setIban(operator.getIban());
            operatorDTO.setName(operator.getName());
            operatorDTO.setApiSearch(operator.getApiSearch());
            operatorDTO.setAllFlights(flightMapper.entitiesToDTOs(operator.getAllFlights()));
            return operatorDTO;
        }
    }

    /**
     * Method that maps an OperatorDTO to an entity of type Operator.
     * @param registerOperatorDto
     * The DTO that contains the iban, the external api for searching and the name of the operator
     * @return
     * An entity of type Operator, with the associated given information. The id is not set and
     * the flights are initialized with an empty array list.
     */
    public Operator fromRegisterDto(RegisterOperatorDto registerOperatorDto) {
        return registerOperatorDto == null ?
                null :
                new Operator(
                        new ArrayList<>(),
                        registerOperatorDto.getName(),
                        registerOperatorDto.getIban(),
                        registerOperatorDto.getApiSearch());
    }
}
