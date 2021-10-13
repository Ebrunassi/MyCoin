package br.com.mycoin.adapters.outbound.persistence.entity;

import br.com.mycoin.application.domain.Situation;
import br.com.mycoin.application.domain.enums.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "entries")
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntryEntity {
    @Id
    private String id;
    private String userId;
    private String avoiceId;
    private EntryType entryType;
    private Boolean credit;
    private Category category;
    private String bank;
    private Double value;
    private Integer instalmentsTotal;
    private Integer instalmentNumber;
    private Date creationDate;
    private StatusEnum status;
    private EventEnum event;
    private List<Situation> situationHistory;
}
