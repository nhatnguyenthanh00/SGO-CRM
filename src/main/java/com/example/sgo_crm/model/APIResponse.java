package com.example.sgo_crm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse {

    private int statusCode;
    private String message;
    private Object data;

}
