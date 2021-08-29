package com.snehasish.igdbapi.config.repository;

import com.snehasish.igdbapi.config.model.ApiCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ApiCredentialsRepository extends CrudRepository<ApiCredentials, Integer> {
}
