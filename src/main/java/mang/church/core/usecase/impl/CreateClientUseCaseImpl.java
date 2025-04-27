package mang.church.core.usecase.impl;

import lombok.AllArgsConstructor;
import mang.church.core.api.ClientApi;
import mang.church.core.domain.CreateClientDTO;
import mang.church.core.exception.ClientAlreadyExistsException;
import mang.church.core.usecase.CreateClientUseCase;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private ClientApi clientApi;

    @Override
    public void execute(CreateClientDTO createClientDTO) throws ClientAlreadyExistsException {
        validateUser(createClientDTO);
        clientApi.saveClient(createClientDTO);
    }
    private void validateUser(CreateClientDTO createClientDTO){
        var clientByEmail = clientApi.findByEmail(createClientDTO.getEmail());
        if(clientByEmail.isPresent()){
            throw new ClientAlreadyExistsException("This email is already in use.");
        }
        var clientByUsername = clientApi.findByUsername(createClientDTO.getUsername());
        if(clientByUsername.isPresent()){
            throw new ClientAlreadyExistsException("This username is already in use.");
        }
    }
}
