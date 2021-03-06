package br.com.mycoin.application.domain;

import br.com.mycoin.application.domain.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entry {

    private String id;
    private String userId;
    private EntryType entryType;
    private String bank;
    private Boolean credit;
    private Category category;
    private Double value;
    private Date creationDate;
    private StatusEnum status;
    private EventEnum event;

    private String avoiceId;
    private Integer instalmentsTotal;
    private Integer instalmentNumber;

    private Integer rememberDate;
    private Integer dueDate;

    private List<Situation> situationHistory;

    public void addSituation(StatusEnum status, EventEnum event){
        this.situationHistory.add(Situation.builder()
                .status(status)
                .event(event)
                .processDate(new Date())
                .build());

        updateCurrentStatus(status, event);
    }
    public void updateCurrentStatus(StatusEnum status, EventEnum event){
        this.status = status;
        this.event = event;
    }
}
