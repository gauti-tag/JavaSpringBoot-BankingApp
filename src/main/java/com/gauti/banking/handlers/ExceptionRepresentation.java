
package com.gauti.banking.handlers;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) // ne va pas retourner une valeur null si une donnée n'existe pas
class ExceptionRepresentation {

    private String errorMessage; 
    private String errorSource; 
    private Set<String> validationErrors; 

}
