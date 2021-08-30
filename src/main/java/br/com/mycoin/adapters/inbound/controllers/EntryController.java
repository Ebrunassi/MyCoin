package br.com.mycoin.adapters.inbound.controllers;

import br.com.mycoin.adapters.dtos.EntryDto;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "New entry register")
@RestController
@RequestMapping("/mycoin")
@EnableAutoConfiguration
@Slf4j
public class EntryController {
    @Autowired
    private EntryServicePort entryServicePort;

    @ApiOperation(value = "Register an user")
    @ApiResponses(value = @ApiResponse(code = 200, message = "OK", response = String.class))
    @Transactional
    @RequestMapping(value = "/entry", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Entry> entryRegister(
            @Valid @RequestBody @ApiParam(value = "Registering a new entry") EntryDto entryDto){

        log.info("Registering a new entry..");
        Entry entry = new Entry();
        BeanUtils.copyProperties(entryDto, entry);
//
        return new ResponseEntity<>(entryServicePort.registerEntry(entry), HttpStatus.CREATED);
    }

}
