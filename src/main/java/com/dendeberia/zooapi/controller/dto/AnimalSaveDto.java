package com.dendeberia.zooapi.controller.dto;

import com.dendeberia.zooapi.dao.domain.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnimalSaveDto {

    @Valid
    private Animal animal;
}
