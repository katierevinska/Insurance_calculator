package lv.javaguru.travel.insurance.core.services.blacklist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorDTO {
    private String errorCode;
    private String errorDescription;
}
