package br.com.mycoin.adapters.dtos;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDto {
    private String name;
    private Integer invoiceDueDay;
}
