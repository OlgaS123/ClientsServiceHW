package com.example.clientsservice.services.data.qualifiers;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Qualifier("accountServiceDb")
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, TYPE, PARAMETER})
public @interface AccountServiceQualifier {
}
