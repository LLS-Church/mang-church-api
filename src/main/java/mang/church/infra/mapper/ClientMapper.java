package mang.church.infra.mapper;

import mang.church.core.domain.ClientModel;
import mang.church.core.domain.CreateClientDTO;
import mang.church.infra.output.persistence.entity.ClientEntity;
import mang.church.infra.output.persistence.utils.Encrypter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330", imports = {Encrypter.class})
public interface ClientMapper {
    @Mapping(target = "password", expression = ("java(Encrypter.encryptPassword(clientModel.getPassword()))"))
    ClientEntity toEntity(CreateClientDTO clientModel);

    ClientModel toModel(ClientEntity clientEntity);
}
