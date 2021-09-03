package br.com.mycoin.adapters.dtos;

import br.com.mycoin.application.domain.Bank;
import br.com.mycoin.application.domain.PersonalData;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @ApiModelProperty(example = "login")
    @NotNull(message = "The username must not be null")
    private String username;
    @ApiModelProperty(example = "password")
    @NotNull(message = "The password must not be null")
    private String password;
    @Valid
    private PersonalDataDto personalData;
    private List<BankDto> banks;

}
