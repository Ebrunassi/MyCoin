package br.com.mycoin.application.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalData {
    @ApiModelProperty(example = "Paul Washer")
    @NotNull(message = "The user's name must not be null")
    private String name;
    @ApiModelProperty(example = "email")
    @NotNull(message = "The user's email must not be null")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
