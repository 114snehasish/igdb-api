package com.snehasish.igdbapi.config.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class ApiCredentials {
    @Id
    private Integer id;
    private String clientId;
    private String clientSecret;
    private String bearerToken;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ApiCredentials that = (ApiCredentials) o;

        return Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return 537814919;
    }
}
