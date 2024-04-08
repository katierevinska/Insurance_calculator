package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    default BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person){
        return BigDecimal.ZERO;
    }
    String getRiskIc();
}
