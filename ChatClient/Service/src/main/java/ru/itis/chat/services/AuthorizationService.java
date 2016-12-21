package ru.itis.chat.services;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.CurrentUser;
import ru.itis.chat.web.connection.NetworkConnection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AuthorizationService {

    private ResponseEntity<UserDto> responseEntity;

    private static AuthorizationService instance = new AuthorizationService();

    private NetworkConnection networkConnection = NetworkConnection.getInstance();

    private AuthorizationService() {}

    public void postAuthorization(@NotNull String URLPath, @Nullable MultiValueMap<String, String> headers) {
        responseEntity = networkConnection.exchange(URLPath, HttpMethod.POST, headers, UserDto.class);
        if (responseEntity.hasBody()) {
            CurrentUser currentUser = CurrentUser.getInstance();
            currentUser.setId(responseEntity.getBody().getId());
            currentUser.setLastName(responseEntity.getBody().getLastName());
            currentUser.setFirstName(responseEntity.getBody().getFirstName());
            currentUser.setToken(responseEntity.getHeaders().getFirst("token"));

            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Service/src/main/resources/CurrentUser.ser"))) {
                outputStream.writeObject(currentUser);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
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

    public static AuthorizationService getInstance() {
        return instance;
    }

}
