package br.com.dextra.woman.controllers.converters;

import br.com.dextra.woman.models.entites.Woman;
import br.com.dextra.woman.models.dtos.WomanDTO;

public class WomanConverter {

    protected static Woman converterDTOToEntity(WomanDTO womanDTO) {
        return new Woman(womanDTO);
    }

    protected static WomanDTO converterEntityToDTO(Woman woman) {
        return new WomanDTO(woman);
    }

}