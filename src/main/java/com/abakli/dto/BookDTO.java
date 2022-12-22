package com.abakli.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String edition;
    @NotBlank
    private String author;
    @NotBlank
    private String publisher;
    @Min(value = 0)
    @NotNull
    private Integer copies;
    private String source;
    private BigDecimal cost = BigDecimal.ZERO;
    @NotBlank
    private String remarks;
    private String key;
}