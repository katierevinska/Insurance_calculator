package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectedRiskValidationTest {
    @InjectMocks
    private NotExistSelectedRiskValidation validation;
    @Mock
    private ValidationErrorFactory errorFactory;
    @Mock
    private ClassifierValueRepository valueRepository;
    @Mock
    private AgreementDTO request;
    @Test
    public void requestWithExistSelectedRiskTest(){
        ClassifierValue classifierValue = mock(ClassifierValue.class);
        when(request.getSelectedRisks()).thenReturn(List.of("EXIST_RISK_1", "EXIST_RISK_2"));
        when(valueRepository.findByClassifierTitleAndIc("RISK_TYPE", "EXIST_RISK_1")).thenReturn(Optional.of(classifierValue));
        when(valueRepository.findByClassifierTitleAndIc("RISK_TYPE", "EXIST_RISK_2")).thenReturn(Optional.of(classifierValue));
        List<ValidationErrorDTO> errors = validation.validateList(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    public void requestWithNotExistSelectedRiskTest(){
        ValidationErrorDTO error1 = mock(ValidationErrorDTO.class);
        when(request.getSelectedRisks()).thenReturn(List.of("NOT_EXIST_RISK_1", "NOT_EXIST_RISK_2"));
        when(valueRepository.findByClassifierTitleAndIc("RISK_TYPE", "NOT_EXIST_RISK_1")).thenReturn(Optional.empty());
        when(valueRepository.findByClassifierTitleAndIc("RISK_TYPE", "NOT_EXIST_RISK_2")).thenReturn(Optional.empty());
        when(errorFactory.buildError(eq("ERROR_CODE_9"), anyList()))
                .thenReturn(error1);
        List<ValidationErrorDTO> errors = validation.validateList(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(),2);
    }
}
