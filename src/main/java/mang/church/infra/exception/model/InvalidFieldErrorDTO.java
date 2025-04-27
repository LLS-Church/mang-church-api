package mang.church.infra.exception.model;

import lombok.Builder;

@Builder
public record InvalidFieldErrorDTO(String message, String field) {}
