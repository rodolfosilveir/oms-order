package br.com.ambev.oms_order_manager.adapter.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import br.com.ambev.oms_order_manager.adapter.in.rest.response.DefaultResponse;
import br.com.ambev.oms_order_manager.domain.exception.OrderNotFoundException;

@ExtendWith(MockitoExtension.class)
class RestResponseExceptionHandlerControllerTest {

    private RestResponseExceptionHandlerController exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new RestResponseExceptionHandlerController();
    }

    @ParameterizedTest
    @MethodSource("provideBadRequestExceptions")
    @DisplayName("Deve renderizar a exceção OrderAlreadyImportedException, handleBadRequestValidationExceptions")
    void shouldHandleBadRequestValidationExceptions(RuntimeException exception) {

        WebRequest request = mock(WebRequest.class);

        ResponseEntity<DefaultResponse<Object>> response = exceptionHandler.handleBadRequestValidationExceptions(exception, request);

        assertNotNull(response.getBody());
        assertEquals(400, response.getStatusCode().value());
    }

    private static Stream<Arguments> provideBadRequestExceptions() {
        return Stream.of(
           Arguments.of(new OrderNotFoundException("1"))
        );
    }
    
}