package ru.itis.chat.services.interfaces;

import com.sun.istack.internal.Nullable;
import org.springframework.util.MultiValueMap;

public interface RegistrationService {

    void postRegistration(@Nullable MultiValueMap<String, String> headers);
    boolean httpStatusCodeIs2xxSuccessful();
    boolean httpStatusCodeIs4xxClientError();
    boolean httpStatusCodeIs5xxServerError();

}
