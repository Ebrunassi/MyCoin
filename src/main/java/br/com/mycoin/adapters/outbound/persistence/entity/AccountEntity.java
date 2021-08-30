package br.com.mycoin.adapters.outbound.persistence.entity;


import br.com.mycoin.application.domain.PersonalData;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Document(collection = "users")
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    private String id;
    @ApiModelProperty(example = "login")
    @NotNull(message = "The username must not be null")
    private String username;
    @ApiModelProperty(example = "password")
    @NotNull(message = "The password must not be null")
    private String password;
    @Valid
    private PersonalData personalData;

    public AccountEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

