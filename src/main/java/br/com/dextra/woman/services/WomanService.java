package br.com.dextra.woman.services;

import br.com.dextra.woman.models.entites.Woman;

import java.util.List;

public interface WomanService {

    Woman createNewWoman(Woman woman);
    List<Woman> getAllWoman();
    Woman getWomanById(Long id);
    List<Woman> getWomanByName(String name);
    void deleteWomanById(Long id);

}