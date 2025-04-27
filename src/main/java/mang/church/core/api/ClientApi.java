package mang.church.core.api;

import mang.church.core.domain.ClientModel;
import mang.church.core.domain.CreateClientDTO;

import java.util.Optional;

public interface ClientApi {
    void saveClient(CreateClientDTO createClientDTO);
    Optional<ClientModel> findByEmail(String email);
    Optional<ClientModel> findByUsername(String username);
}
