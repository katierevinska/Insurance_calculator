package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class TravelRequestPersonalCodeValidation extends TravelPersonFieldValidationImpl{
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreementDTO, PersonDTO personDTO){
        return personDTO.getPersonalCode() == null
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_17"))
                : Optional.empty();
    }
}
