package mang.church.core.usecase;

import mang.church.core.domain.CreateClientDTO;

public interface CreateClientUseCase {
    void execute(CreateClientDTO createClientDTO);
}
