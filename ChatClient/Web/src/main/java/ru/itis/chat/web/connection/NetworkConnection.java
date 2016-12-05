package ru.itis.chat.web.connection;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class NetworkConnection {

    public static final String targetURL = "http://localhost:8090";

    private RestTemplate restTemplate;

    private static NetworkConnection instance;

    static {
        instance = new NetworkConnection();
    }

    private NetworkConnection() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public <T> ResponseEntity<T> exchange(@NotNull String URLPath, @NotNull HttpMethod method,
                                          @Nullable MultiValueMap<String, String> headers,
                                          @NotNull ParameterizedTypeReference<T> responseType) {
        HttpEntity request = new HttpEntity(headers);
        return restTemplate.exchange(targetURL + URLPath, method, request, responseType);
    }

    public <T> ResponseEntity<T> exchange(@NotNull String URLPath, @NotNull HttpMethod method,
                                          @Nullable MultiValueMap<String, String> headers,
                                          Map<String, String> parameters,
                                          @NotNull ParameterizedTypeReference<T> responseType) {
        HttpEntity request = new HttpEntity(headers);
        return restTemplate.exchange(targetURL + URLPath, method, request, responseType, parameters);
    }

    public <T> ResponseEntity<T> exchange(@NotNull String URLPath, @NotNull HttpMethod method,
                                          @Nullable MultiValueMap<String, String> headers,
                                          Class<T> responseType) {
        HttpEntity request = new HttpEntity(headers);
        return restTemplate.exchange(targetURL + URLPath, method, request, responseType);
    }

    public <T> ResponseEntity<T> exchange(@NotNull String URLPath, @NotNull HttpMethod method,
                                          @Nullable MultiValueMap<String, String> headers,
                                          Map<String, String> parameters, Class<T> responseType) {
        HttpEntity request = new HttpEntity(headers);
        return restTemplate.exchange(targetURL + URLPath, method, request, responseType, parameters);
    }

    public static NetworkConnection getInstance() {
        return instance;
    }
}
