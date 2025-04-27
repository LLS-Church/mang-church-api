package mang.church.core.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class CreateClientDTO {
    @Email(message = "O campo [email] deve ser um campo válido.")
    private String email;
    @NotBlank(message = "O campo [username] é um campo obrigatório.")
    @Pattern(regexp = "^[a-z0-9_-]{3,15}$", message = "O campo [username] deve conter apenas letras minúsculas, números, traços e sublinhados")
    private String username;
    @Length(min = 6, message = "O campo [password] deve ter no mínimo 6 caracteres")
    private String password;
}
