package com.adminservice.service;

import com.adminservice.dto.CompressedOperatorDto;
import com.adminservice.exception.InvalidParameterException;
import com.adminservice.model.Operator;
import com.adminservice.repository.OperatorRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that contains the business logic for an Operator.
 */
@Service
@Transactional
public class OperatorService {

    private final OperatorRepository operatorRepository;

    public OperatorService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    /**
     * Method that handles the creation of an Operator based on an DTO. The method ensures that
     * the name, iban and the external api were not previously used for registering an operator.
     * @param compressedOperatorDto
     * The DTO which contains the information about the operator.
     * @return
     * In case of success, the id of the newly created operator.
     * @throws InvalidParameterException
     * If the method receives a previously used iban, name or external api, it throws an InvalidParameterException
     * with a detailed message. The exception will be caught by the RestControllerAdvice
     * (ExceptionHandlerAdvice class).
     */
    public int createOperator(CompressedOperatorDto compressedOperatorDto)
            throws InvalidParameterException {
        if(operatorRepository.existsByName(compressedOperatorDto.getName())){
            throw new InvalidParameterException("Name " + compressedOperatorDto.getName() + " already used.");
        }
        else if(operatorRepository.existsByIban(compressedOperatorDto.getIban())){
            throw new InvalidParameterException("IBAN " + compressedOperatorDto.getIban() + " already used.");
        }
        else if(operatorRepository.existsByApiSearch(compressedOperatorDto.getApiSearch())){
            throw new InvalidParameterException("API search " + compressedOperatorDto.getApiSearch() + " already used.");
        }
        Operator operator = new Operator(
                new ArrayList<>(),
                compressedOperatorDto.getName(),
                compressedOperatorDto.getIban(),
                compressedOperatorDto.getApiSearch());
        return saveOperator(operator).getId();
    }

    /**
     * Method that will retrieve the details on operator based on its id. If no operator exists
     * with the received parameter, it throws InvalidParameterException.
     * @param operatorId
     * The id of the operator for which we want the details.
     * @return
     * An entity of type Operator, with all the fields of the operator associated with the received id.
     */
    public Operator findById(Integer operatorId) {
        Optional<Operator> operator = operatorRepository.findById(operatorId);
        if (operator.isEmpty())
            throw new InvalidParameterException("No operator registered with id " + operatorId);
        return operator.get();
    }

    /**
     * Method that will retrieve an operator based on its name. The method checks first
     * if there is any operator registered with the received parameter. If yes, the method
     * returns the details of the operator.
     * @param name
     * The name of the operator for which we want to retrieve the details.
     * @return
     * An Operator object, which has the name equal with the received parameter.
     * @throws InvalidParameterException
     * If the method receives a name for which there is no registered operator, it throws
     * an InvalidParameterException, which will be caught by the exception handler advice.
     */
    public Operator findByName(String name) {
        Optional<Operator> optionalOperator = operatorRepository.findByName(name);
        if(optionalOperator.isEmpty()){
            throw new InvalidParameterException("No operator registered with name " + name);
        }
        return optionalOperator.get();
    }

    /**
     * Method that will update the information about an operator. The method checks if there
     * is an operator registered with the received parameter. If yes, it updates its details(except
     * the flights).
     * @param compressedOperatorDto
     * The new details of the operator.
     * @param operatorId
     * The id of the operator for which the method updates the details.
     * @return
     * The updated Operator entity, which has the id equal with the received parameter.
     */
    public Operator updateOperator(CompressedOperatorDto compressedOperatorDto, Integer operatorId) {
        Optional<Operator> optionalOperator = operatorRepository.findById(operatorId);
        if(optionalOperator.isEmpty()){
            throw new InvalidParameterException("No operator registered with id " + operatorId);
        }
        var operator = optionalOperator.get();
        operator.setName(compressedOperatorDto.getName());
        operator.setIban(compressedOperatorDto.getIban());
        operator.setApiSearch(compressedOperatorDto.getApiSearch());
        operator = saveOperator(operator);
        return operator;
    }

    /**
     * Method that will delete data of an operator. The methods will also delete all the
     * associated flights for the operator, since a flight is not standalone (can't exist
     * without an operator).
     * @param operatorId
     * The id of the operator for which the method deletes the data.
     * @return
     * A boolean, which is the status of deleting the data.
     */
    public boolean deleteOperator(Integer operatorId) {
        Optional<Operator> operator = operatorRepository.findById(operatorId);
        if(operator.isEmpty()){
            throw new InvalidParameterException("No operator registered with id " + operatorId);
        }
        operatorRepository.deleteById(operatorId);
        return !operatorRepository.existsById(operatorId);
    }

    /**
     * Method which will save the new state of an operator.
     * @param operator
     * The operator state which needs to be persisted.
     * @return
     * The new state of the operator after saving.
     */
    public Operator saveOperator(Operator operator) {
        return operatorRepository.save(operator);
    }

    /**
     * Method that will retrieve all the operators which perform a certain route. The method takes
     * into consideration if the destination is present in request or not and adjusts the search.
     * @param leaving
     * The city from which the flight starts.
     * @param destination
     * The city where the flight ends.
     * @return
     * The list of operators which perform the certain route.
     */
    public List<Operator> findOperatorsByRoute(String leaving, String destination) {
        List<Operator> operators;
        if (destination != null) {
            operators = operatorRepository.findOperatorsByLeavingAndDestination(leaving, destination);
        } else {
            operators = operatorRepository.findOperatorsByLeaving(leaving);
        }
        return operators;
    }

    public List<Operator> findAll() {
        return operatorRepository.findAll();
    }
}
