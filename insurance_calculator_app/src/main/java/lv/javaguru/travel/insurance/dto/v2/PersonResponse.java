package lv.javaguru.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import lv.javaguru.travel.insurance.dto.util.BigDecimalSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {
    @JsonAlias("person_first_name")
    private String personFirstName;
    @JsonAlias("person_last_name")
    private String personLastName;
    @JsonAlias("personBirthDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @JsonAlias("personal_code")
    private String personalCode;
    @JsonAlias("medical_risk_limit_level")
    private String medicalRiskLimitLevel;
    @JsonSerialize(using = BigDecimalSerializer.class)
    @JsonAlias("person_premium")
    private BigDecimal personPremium;
    @JsonAlias("person_risks")
    private List<TravelRisk> personRisks;
}
