package mang.church.infra.mapper;

import mang.church.core.domain.CreateClientDTO;
import mang.church.infra.output.persistence.entity.ClientEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientMapperTest {
    private final ClientMapper mapper = Mappers.getMapper(ClientMapper.class);

    @Test
    @DisplayName("Should map CreateClientDTO to ClientEntity")
    void shouldMapCreateClientDTOToClientEntity() {
        var createClientDTO = CreateClientDTO.builder()
                .email("email@email.com")
                .username("username")
                .password("12312321")
                .build();
        var clientEntity = mapper.toEntity(createClientDTO);
        assertNotNull(clientEntity);
        assertNotNull(clientEntity.getPassword());
        assertNotNull(clientEntity.getUsername());
        assertNotNull(clientEntity.getEmail());
    }
    @Test
    @DisplayName("Should map ClientEntity to ClientModel")
    void shouldMapClientEntityToClientModel() {
        var clientEntity = ClientEntity.builder()
                .email("email")
                .clientId(1L)
                .createdAt(LocalDateTime.now())
                .password("1231223")
                .username("userna,e")
                .build();
        var clientModel = mapper.toModel(clientEntity);

        assertNotNull(clientModel);
        assertNotNull(clientModel.email());
        assertNotNull(clientModel.username());
    }
}