package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface TravelAgreementFieldValidation {
    Optional<ValidationErrorDTO> validate(AgreementDTO request);
    List<ValidationErrorDTO> validateList(AgreementDTO request);

}
