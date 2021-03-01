package br.com.dextra.woman.services.impl;

import br.com.dextra.woman.models.entites.Woman;
import br.com.dextra.woman.repositories.WomanRepository;
import br.com.dextra.woman.services.WomanService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WomanServiceImpl implements WomanService {

    private final WomanRepository repository;

    public WomanServiceImpl(WomanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Woman createNewWoman(Woman woman) {
        return repository.save(woman);
    }


    @Override
    public Woman getWomanById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Grande mulher: %d." + id)));
    }

    @Override
    public List<Woman> getWomanByName(String name) {
        return repository.findWomanByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Woman> getAllWoman() {
        return repository.findAll();
    }

    @Override
    public void deleteWomanById(Long id) {
        getWomanById(id);

        repository.deleteById(id);
    }

}