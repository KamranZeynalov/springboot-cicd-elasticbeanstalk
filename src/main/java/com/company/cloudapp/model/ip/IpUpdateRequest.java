package com.company.cloudapp.model.ip;


import com.company.cloudapp.constant.Environment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IpUpdateRequest {
    @NotNull
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Environment env;
}
