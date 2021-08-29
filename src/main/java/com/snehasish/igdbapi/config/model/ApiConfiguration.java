package com.snehasish.igdbapi.config.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiConfiguration {
    @Id
    private Integer id;
    private String baseUrl;
    private String defaultMethod;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ApiConfiguration that = (ApiConfiguration) o;

        return Objects.equals(baseUrl, that.baseUrl);
    }

    @Override
    public int hashCode() {
        return 846699849;
    }
}
