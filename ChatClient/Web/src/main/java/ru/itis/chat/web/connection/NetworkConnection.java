package ru.itis.chat.web.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NetworkConnection {

    private static final String targetURL = "http://localhost:8090";

    private RestTemplate restTemplate;

    private static NetworkConnection instance = new NetworkConnection();

    private NetworkConnection() {
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {}
        });

        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
                jsonConverter.setObjectMapper(new ObjectMapper());
                jsonConverter.setSupportedMediaTypes(ImmutableList.of(new MediaType("application",
                        "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET),
                        new MediaType("text", "javascript",
                                MappingJackson2HttpMessageConverter.DEFAULT_CHARSET)));
            }
        }
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
