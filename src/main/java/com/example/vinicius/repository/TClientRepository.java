package com.example.vinicius.repository;

import com.example.vinicius.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TClientRepository extends CrudRepository<Client, Long> {
    Client findById(long id);
}
