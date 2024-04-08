package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectedRisksPremiumCalculatorTest {
    @InjectMocks
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;
    @Mock
    private AgreementDTO agreementDTO;
    @Mock
    private PersonDTO personDTO;
@Test
public void calculateWithNotExistSelectedRisksPremium(){
    when(agreementDTO.getSelectedRisks()).thenReturn(List.of("RISK_1", "RISK_2"));

    TravelRiskPremiumCalculator calculator1 = mock(TravelRiskPremiumCalculator.class);
    when(calculator1.getRiskIc()).thenReturn("RISK_1");
    when(calculator1.calculatePremium(agreementDTO, personDTO)).thenReturn(BigDecimal.valueOf(2));

    List<TravelRiskPremiumCalculator> riskPremiumCalculators = List.of(calculator1);
    ReflectionTestUtils.setField(selectedRisksPremiumCalculator, "riskPremiumCalculators", riskPremiumCalculators);
    Throwable thrown = assertThrows(RuntimeException.class,
            ()->selectedRisksPremiumCalculator.calculateSelectedRisksPremium(agreementDTO, personDTO));

    assertEquals(thrown.getMessage(), "risk with riskIc RISK_2 not supported by system");
}
    @Test
    public void calculateSelectedRisksPremium(){
        when(agreementDTO.getSelectedRisks()).thenReturn(List.of("RISK_1", "RISK_2"));

        TravelRiskPremiumCalculator calculator1 = mock(TravelRiskPremiumCalculator.class);
        when(calculator1.getRiskIc()).thenReturn("RISK_1");
        when(calculator1.calculatePremium(agreementDTO, personDTO)).thenReturn(BigDecimal.valueOf(2));

        TravelRiskPremiumCalculator calculator2 = mock(TravelRiskPremiumCalculator.class);
        when(calculator2.getRiskIc()).thenReturn("RISK_2");
        when(calculator2.calculatePremium(agreementDTO, personDTO)).thenReturn(BigDecimal.valueOf(3));

        List<TravelRiskPremiumCalculator> riskPremiumCalculators = List.of(calculator1, calculator2);
        ReflectionTestUtils.setField(selectedRisksPremiumCalculator, "riskPremiumCalculators", riskPremiumCalculators);
        List<RiskDTO> risks = selectedRisksPremiumCalculator.calculateSelectedRisksPremium(agreementDTO, personDTO);
        assertEquals(risks.size(), 2);

        assertEquals(risks.get(0).getRiskIc(), "RISK_1");
        assertEquals(risks.get(0).getPremium(), BigDecimal.valueOf(2));

        assertEquals(risks.get(1).getRiskIc(), "RISK_2");
        assertEquals(risks.get(1).getPremium(), BigDecimal.valueOf(3));

    }
}
