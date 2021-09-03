package br.com.mycoin.application.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

//@Builder
//@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Account {
    @Id
    private String id;
    private String username;
    private String password;
    private PersonalData personalData;
    private List<Bank> banks;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String id, String username, String password, PersonalData personalData, List<Bank> banks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.personalData = personalData;
        this.banks = banks;
    }
    public Account(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }
}
