package br.com.mycoin.adapters.dtos;

import br.com.mycoin.application.domain.Situation;
import br.com.mycoin.application.domain.enums.Category;
import br.com.mycoin.application.domain.enums.EntryType;
import br.com.mycoin.application.domain.enums.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ApiModelProperty(example = "20")
    @Min(value = 1, message = "The remember date must be greater than zero")
    @Max(value = 31, message = "The remember date must be less than 32")
    private Integer rememberDate;       // This field is required if the entry type is equal to FIXED_COST
    @ApiModelProperty(example = "25")
    @Min(value = 1, message = "The due date must be greater than zero")
    @Max(value = 31, message = "The due date must be less than 32")
    private Integer dueDate;            // This field is required if the entry type is equal to FIXED_COST

    @AssertTrue(message = "Fixed costs entries must have a remember date and due date")
    private boolean isFixedCostOk(){
        if(this.entryType == EntryType.FIXED_COST)
            return this.rememberDate != null && dueDate != null;
        else
            return true;
    }

}
