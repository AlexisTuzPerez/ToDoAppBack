package com.toDoApp.exceptions.dto;

import lombok.Builder;

import java.time.Instant;


@Builder
public record ErrorResponse(

        int status,
        String message,
        Instant timestamp
) {}