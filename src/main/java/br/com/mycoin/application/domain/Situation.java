package br.com.mycoin.application.domain;

import br.com.mycoin.application.domain.enums.EventEnum;
import br.com.mycoin.application.domain.enums.StatusEnum;
import lombok.*;

import java.util.Date;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Situation {
    private StatusEnum status;
    private EventEnum event;
    private Date processDate;
}
