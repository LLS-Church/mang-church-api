package mang.church.infra.api;

import lombok.AllArgsConstructor;
import mang.church.core.api.ClientApi;
import mang.church.core.domain.ClientModel;
import mang.church.core.domain.CreateClientDTO;
import mang.church.infra.mapper.ClientMapper;
import mang.church.infra.output.persistence.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class ClientApiImpl implements ClientApi {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public void saveClient(CreateClientDTO createClientDTO) {
        clientRepository.save(clientMapper.toEntity(createClientDTO));
    }

    @Override
    public Optional<ClientModel> findByEmail(String email) {
        var entity = clientRepository.findByEmail(email);
        return entity
                .map(clientMapper::toModel);
    }

    @Override
    public Optional<ClientModel> findByUsername(String username) {
        var entity = clientRepository.findByUsername(username);
        return entity
                .map(clientMapper::toModel);
    }
}
