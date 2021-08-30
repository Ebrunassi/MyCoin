package br.com.mycoin.adapters.dtos;

import br.com.mycoin.application.domain.PersonalData;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AccountDto {
    @ApiModelProperty(example = "login")
    @NotNull(message = "The username must not be null")
    private String username;
    @ApiModelProperty(example = "password")
    @NotNull(message = "The password must not be null")
    private String password;
    @Valid
    private PersonalData personalData;

    public AccountDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
}
