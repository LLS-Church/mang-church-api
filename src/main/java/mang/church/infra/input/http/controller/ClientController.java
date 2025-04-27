package mang.church.infra.input.http.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mang.church.core.domain.CreateClientDTO;
import mang.church.core.exception.ClientAlreadyExistsException;
import mang.church.core.usecase.CreateClientUseCase;
import mang.church.infra.exception.model.GenericErrorDTO;
import mang.church.infra.exception.model.InvalidFieldErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private final CreateClientUseCase createCandidateUseCase;

    @PostMapping("")
    @Tag(name = "Client", description = "Client API")
    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid field", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = InvalidFieldErrorDTO.class)
                            )
                    )
            }),
            @ApiResponse(responseCode = "412", description = "Client already exists", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericErrorDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericErrorDTO.class)
                    )
            })
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CreateClientDTO createClientDTO) {
        try{
            createCandidateUseCase.execute(createClientDTO);
            return new ResponseEntity<>("", HttpStatus.CREATED);
        }catch (ClientAlreadyExistsException e){
            return new ResponseEntity<>(buildErrorDTO(e.getMessage()),HttpStatus.PRECONDITION_FAILED);
        }catch (Exception e){
            return new ResponseEntity<>(buildErrorDTO(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    private GenericErrorDTO buildErrorDTO(String message) {
        return GenericErrorDTO.builder()
                .message(message)
                .build();
    }
}
