package br.com.mycoin.adapters.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDataDto {
    @ApiModelProperty(example = "Paul Washer")
    @NotNull(message = "The user's name must not be null")
    private String name;
    @ApiModelProperty(example = "email")
    @NotNull(message = "The user's email must not be null")
    private String email;

}
