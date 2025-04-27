package mang.church.core.usecase.impl;


import mang.church.core.api.ClientApi;
import mang.church.core.domain.ClientModel;
import mang.church.core.domain.CreateClientDTO;
import mang.church.core.exception.ClientAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateClientUseCaseImplTest {
    @Mock
    private ClientApi clientApi;
    @InjectMocks
    private CreateClientUseCaseImpl createClientUseCase;

    @BeforeEach
    void setUp(){
        openMocks(this);
    }

    @Test
    @DisplayName("Should save the client successfully")
    void shouldSaveClientSuccessfully(){
        var createClientDTO = mock(CreateClientDTO.class);

        when(clientApi.findByEmail(any())).thenReturn(Optional.empty());
        when(clientApi.findByUsername(any())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> createClientUseCase.execute(createClientDTO));

        verify(clientApi, times(1)).saveClient(createClientDTO);
    }

    @Test
    @DisplayName("Should throw ClientAlreadyExistsException if user exists by email")
    void shouldThrowExceptionWhenUserAlreadyExistsByEmail(){
        var createClientDTO = mock(CreateClientDTO.class);
        var clientModelMock = mock(ClientModel.class);

        when(clientApi.findByEmail(any())).thenReturn(Optional.of(clientModelMock));

        assertThrows(ClientAlreadyExistsException.class, () -> createClientUseCase.execute(createClientDTO));
        verify(clientApi, never()).saveClient(createClientDTO);
    }
    @Test
    @DisplayName("Should throw ClientAlreadyExistsException if user exists by username")
    void shouldThrowExceptionWhenUserAlreadyExistsByUsername(){
        var createClientDTO = mock(CreateClientDTO.class);
        var clientModelMock = mock(ClientModel.class);

        when(clientApi.findByEmail(any())).thenReturn(Optional.empty());
        when(clientApi.findByUsername(any())).thenReturn(Optional.of(clientModelMock));

        assertThrows(ClientAlreadyExistsException.class, () -> createClientUseCase.execute(createClientDTO));
        verify(clientApi, never()).saveClient(createClientDTO);
    }
}