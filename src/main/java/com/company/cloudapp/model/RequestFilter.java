package com.company.cloudapp.model;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestFilter {
    @Min(value = 0)
    private int page;
    @Min(value = 1)
    private int size;
}
