package mang.church.infra.api;


import mang.church.core.domain.ClientModel;
import mang.church.core.domain.CreateClientDTO;
import mang.church.infra.mapper.ClientMapper;
import mang.church.infra.output.persistence.entity.ClientEntity;
import mang.church.infra.output.persistence.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ClientApiImplTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;
    @InjectMocks
    private ClientApiImpl clientApi;

    @BeforeEach
    void setUp(){
        openMocks(this);
    }

    @Test
    @DisplayName("Should save client successfully")
    void shouldSaveClientSuccessfully(){
        var createClientDTO = mock(CreateClientDTO.class);
        var clientEntity = mock(ClientEntity.class);

        when(clientMapper.toEntity(any())).thenReturn(clientEntity);

        assertDoesNotThrow(() ->clientApi.saveClient(createClientDTO));
    }
    @Test
    @DisplayName("Should find customer by username successfully")
    void shouldFindCustomerByUsernameSuccessfully(){
        var clientModel = mock(ClientModel.class);
        var clientEntity = mock(ClientEntity.class);

        when(clientRepository.findByUsername("username")).thenReturn(Optional.of(clientEntity));
        when(clientMapper.toModel(clientEntity)).thenReturn(clientModel);

        var result = clientApi.findByUsername("username");

        assertTrue(result.isPresent());
        assertEquals(clientModel, result.get());
    }
    @Test
    @DisplayName("Should find customer by email successfully")
    void shouldFindCustomerByEmailSuccessfully(){
        var clientModel = mock(ClientModel.class);
        var clientEntity = mock(ClientEntity.class);

        when(clientRepository.findByEmail("email@email.com")).thenReturn(Optional.of(clientEntity));
        when(clientMapper.toModel(clientEntity)).thenReturn(clientModel);

        var result = clientApi.findByEmail("email@email.com");

        assertTrue(result.isPresent());
        assertEquals(clientModel, result.get());
    }

}