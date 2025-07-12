package com.company.cloudapp.model.contract;

import com.company.cloudapp.model.RequestFilter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ContractFilter extends RequestFilter {
    private String contractName;
}
