package br.com.mycoin.adapters.dtos;

import br.com.mycoin.application.domain.Situation;
import br.com.mycoin.application.domain.enums.Category;
import br.com.mycoin.application.domain.enums.EntryType;
import br.com.mycoin.application.domain.enums.Status;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class EntryDto {
    @ApiModelProperty(example = "VARIABLE_COST")
    @NotNull(message = "The entry type must not be null")
    private EntryType entryType;
    @ApiModelProperty(example = "true")
    @NotNull(message = "The 'credit' field must not be null")
    private Boolean credit;
    @ApiModelProperty(example = "FOOD")
    @NotNull(message = "The category must not be null")
    private Category category;
    @ApiModelProperty(example = "NuBank")
    private String bank;
    @ApiModelProperty(example = "12.50")
    @NotNull(message = "The value of the entry must not be null")
    private Double value;
    @ApiModelProperty(example = "3")
    private Integer instalmentsTotal;
    @ApiModelProperty(example = "1")
    private Integer instalmentNumber;
}
