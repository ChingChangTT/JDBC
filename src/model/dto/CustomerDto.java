package model.dto;

import lombok.Builder;

import java.sql.Date;

@Builder
public record CustomerDto(
        Integer id,
        String name,
        String email,
        String bio
) {}