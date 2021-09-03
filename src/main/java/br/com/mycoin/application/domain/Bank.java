package br.com.mycoin.application.domain;

import lombok.*;

import java.util.Date;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    private String name;
    private Integer invoiceDueDay;
}
