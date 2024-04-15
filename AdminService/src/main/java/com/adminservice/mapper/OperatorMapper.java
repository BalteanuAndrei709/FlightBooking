package com.adminservice.mapper;

import com.adminservice.dto.OperatorDTO;
import com.adminservice.dto.CompressedOperatorDto;
import com.adminservice.model.Operator;
import org.springframework.stereotype.Component;

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
     * Method that maps an Operator to an OperatorDto.
     * @param operator
     * The entity of type Operator.
     * @return
     * A CompressedOperatorDto
     */
    public CompressedOperatorDto toCompressedDto(Operator operator) {
        if (operator == null)
            return null;
        else {
            CompressedOperatorDto compressedOperatorDto = new CompressedOperatorDto();
            compressedOperatorDto.setIban(operator.getIban());
            compressedOperatorDto.setName(operator.getName());
            compressedOperatorDto.setApiSearch(operator.getApiSearch());
            return compressedOperatorDto;
        }
    }
}
