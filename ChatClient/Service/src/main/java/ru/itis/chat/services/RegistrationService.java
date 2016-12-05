package ru.itis.chat.services;

import com.sun.istack.internal.Nullable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.CurrentUser;
import ru.itis.chat.web.connection.NetworkConnection;

public class RegistrationService {

    private ResponseEntity<UserDto> responseEntity;

    private static RegistrationService instance = new RegistrationService();

    private NetworkConnection networkConnection = NetworkConnection.getInstance();

    private RegistrationService() {}

    public void postRegistration(@Nullable MultiValueMap<String, String> headers) {
        responseEntity = networkConnection.exchange("/registration", HttpMethod.POST, headers, UserDto.class);
        CurrentUser currentUser = CurrentUser.getInstance();
        currentUser.setId(responseEntity.getBody().getId());
        currentUser.setLastName(responseEntity.getBody().getLastName());
        currentUser.setFirstName(responseEntity.getBody().getFirstName());
        currentUser.setToken(responseEntity.getHeaders().getFirst("token"));
    }

    public boolean httpStatusCodeIs2xxSuccessful() {
        return responseEntity.getStatusCode().is2xxSuccessful();
    }

    public boolean httpStatusCodeIs4xxClientError() {
        return responseEntity.getStatusCode().is4xxClientError();
    }

    public boolean httpStatusCodeIs5xxServerError() {
        return responseEntity.getStatusCode().is5xxServerError();
    }

    public static RegistrationService getInstance() {
        return instance;
    }
}
