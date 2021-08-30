package br.com.mycoin.adapters.inbound.controllers;

import br.com.mycoin.adapters.dtos.AccountDto;
import br.com.mycoin.adapters.response.ResponseModel;
import br.com.mycoin.application.domain.Account;
import br.com.mycoin.application.ports.inbound.LoginServicePort;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "MyCoin - Login Service")
@RestController
@RequestMapping("/mycoin")
@EnableAutoConfiguration
@Slf4j
public class LoginController {
    @Autowired
    private LoginServicePort loginService;

    @ApiOperation(value = "Register an user")
    @ApiResponses(value = @ApiResponse(code = 200, message = "OK", response = String.class))
    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Account> registerUser(
            @Valid @RequestBody @ApiParam(value = "User's information for register") AccountDto register){

        log.info("Registering a new account..");
        Account account = new Account();
        BeanUtils.copyProperties(register, account);

        return new ResponseEntity<>(loginService.registerAccount(account), HttpStatus.CREATED);
    }

    // Este método pegará todos os erros referentes ao não preenchimento de um campo obrigatório
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(400).body(new ResponseModel(ex.getFieldErrors().get(0).getDefaultMessage()));
    }
}