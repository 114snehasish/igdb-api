package com.snehasish.igdbapi.config.repository;

import com.snehasish.igdbapi.config.model.ApiConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ApiConfigurationRepository extends CrudRepository<ApiConfiguration, Integer> {
}
