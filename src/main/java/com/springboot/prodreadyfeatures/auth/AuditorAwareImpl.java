package com.springboot.prodreadyfeatures.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor() {
        //We now giving hardcoded value of the user  but in future we use spring security context as follows:
//         get Security context
//         get Authentication
//         get the principle
//         get the username
        return Optional.of("Pratham Kapadi");
    }
}
