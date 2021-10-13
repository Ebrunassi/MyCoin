package br.com.mycoin.adapters.inbound.controllers;

import br.com.mycoin.adapters.configuration.security.service.TokenAuthenticationService;
import br.com.mycoin.adapters.dtos.EntryDto;
import br.com.mycoin.adapters.response.ResponseModel;
import br.com.mycoin.application.domain.Entry;
import br.com.mycoin.application.ports.inbound.EntryServicePort;
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

@Api(value = "New entry register")
@RestController
@RequestMapping("/mycoin")
@EnableAutoConfiguration
@Slf4j
public class EntryController {
    @Autowired
    private EntryServicePort entryServicePort;

    @ApiOperation(value = "Register an entry")
    @ApiResponses(value = @ApiResponse(code = 200, message = "OK", response = String.class))
    @Transactional
    @RequestMapping(value = "/entry", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Entry> entryRegister(
            @RequestHeader("Authorization") @ApiParam(value = "JWT access token") String token,
            @Valid @RequestBody @ApiParam(value = "Registering a new entry") EntryDto entryDto){

        log.info("Registering a new entry..");
        String user = TokenAuthenticationService.authenticateToken(token);
        Entry entry = new Entry();
        entry.setUserId(user);

        BeanUtils.copyProperties(entryDto, entry);
        return new ResponseEntity<>(entryServicePort.registerEntry(entry), HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error(ex.getFieldErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(400).body(new ResponseModel(ex.getFieldErrors().get(0).getDefaultMessage()));
    }

}
