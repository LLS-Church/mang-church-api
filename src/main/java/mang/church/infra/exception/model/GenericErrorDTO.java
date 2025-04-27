package mang.church.infra.exception.model;

import lombok.Builder;

@Builder
public record GenericErrorDTO(String message){
}
