package br.com.dextra.woman.controllers;

import br.com.dextra.woman.controllers.converters.WomanConverter;
import br.com.dextra.woman.models.entites.Woman;
import br.com.dextra.woman.models.dtos.WomanDTO;
import br.com.dextra.woman.services.WomanService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("v1/women")
@OpenAPIDefinition(info = @Info(title = "Woman Dextra!", description = "Esperamos vocês..."))
public class WomanController extends WomanConverter {

    private final WomanService service;

    public WomanController(WomanService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Salve uma nova grande mulher")
    public Woman createNewWoman(@Valid @RequestBody WomanDTO womanDTO) {
        Woman woman = converterDTOToEntity(womanDTO);

        return service.createNewWoman(woman);
    }

    @GetMapping("/{id}")
    @Operation(description = "Consulte uma grande mulher salva")
    public WomanDTO getWomanById(@PathParam("{id}") Long id) {
        Woman woman = service.getWomanById(id);

        WomanDTO womanDTO = converterEntityToDTO(woman);

        return womanDTO;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Consulte todas as grandes mulheres salvas")
    public List<WomanDTO> getAllWomen() {
        List<Woman> women = service.getAllWoman();

        List<WomanDTO> womanDTOList = women.stream()
                .map(woman -> converterEntityToDTO(woman))
                .collect(Collectors.toList());

        return womanDTOList;
    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Consulte todas as grandes mulheres salvas")
    public List<WomanDTO> getAllWomen(@RequestParam("name") String name) {
        List<Woman> women = service.getWomanByName(name);

        List<WomanDTO> womanDTOList = women.stream()
                .map(woman -> converterEntityToDTO(woman))
                .collect(Collectors.toList());

        return womanDTOList;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Grande mulher não localizada"),
            @ApiResponse(responseCode = "200", description = "Grande mulher deletada")
    })
    @Operation(description = "Delete uma grande mulher")
    public void deleteWomanById(@PathParam("{id}") Long id) {
        service.deleteWomanById(id);
    }

}