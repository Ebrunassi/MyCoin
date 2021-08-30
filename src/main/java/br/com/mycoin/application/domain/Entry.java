package br.com.mycoin.application.domain;

import br.com.mycoin.application.domain.enums.Category;
import br.com.mycoin.application.domain.enums.EntryType;
import br.com.mycoin.application.domain.enums.Status;
import lombok.*;

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
    private String avoiceId;
    private EntryType entryType;
    private Boolean credit;
    private Category category;
    private String bank;
    private Double value;
    private Integer instalmentsTotal;
    private Integer instalmentNumber;
    private Date creationDate;
    private Status status;
    private List<Situation> situationHistory;
}
