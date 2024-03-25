package com.adminservice.service;

import com.adminservice.dto.OperatorDTO;
import com.adminservice.exception.OperatorException;
import com.adminservice.mapper.OperatorMapper;
import com.adminservice.model.Flight;
import com.adminservice.model.Operator;
import com.adminservice.repository.OperatorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.OpenDataException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OperatorService {

    @Autowired
    OperatorRepository operatorRepository;
    @Autowired
    OperatorMapper operatorMapper;

    public Optional<OperatorDTO> findById(Integer id){
        Optional<Operator> operator = operatorRepository.findById(id);
        if(operator.isPresent())
        return operator.map(operatorMapper::toDto);
        else
            throw new OperatorException("Operator id does not exist.");
    }


    //schimbare cu FlightDTO
    public Optional<List<Flight>> findFlightsByOperatorName(String name){
        boolean isPresent;
        isPresent = operatorRepository.findByName(name).isPresent();
        if(isPresent){
            return operatorRepository.findByName(name)
                    .map(Operator::getAllFlights);
        } else
            throw new OperatorException("This operator has no flights available.");


    }

    public void createOperator(OperatorDTO operatorDTO){
        Operator operator = operatorMapper.toEntity(operatorDTO);
        operatorRepository.save(operator);

    }

    public boolean updateOperator(OperatorDTO operatorDTO, Integer id){
        boolean isOperatorPresent;
        Optional<Operator> optionalOperator = operatorRepository.findById(id);
        isOperatorPresent = optionalOperator.isPresent();
        if(isOperatorPresent){

            OperatorDTO existingOperatorDto = operatorMapper.toDto(optionalOperator.get());
            existingOperatorDto.setName(operatorDTO.getName());
            existingOperatorDto.setIban(operatorDTO.getIban());
            existingOperatorDto.setApiSearch(operatorDTO.getApiSearch());
            existingOperatorDto.setAllFlights(operatorDTO.getAllFlights());

            Operator operator = operatorMapper.toEntity(existingOperatorDto);
            operatorRepository.save(operator);
            return isOperatorPresent;
        } else
            throw new OperatorException("Operator id does not exist.");



    }

    public boolean deleteOperator(Integer id){
        Optional<Operator> operator = operatorRepository.findById(id);
        if(operator.isPresent()){
            operatorRepository.deleteById(id);
            return true;
        } else
            throw new OperatorException("Operator id does not exist.");

    }


}
