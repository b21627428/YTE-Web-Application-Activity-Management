package com.example.demo.dto;

import com.example.demo.dto.activity.ActivityDTO;
import com.example.demo.dto.person.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QRCodeDTO {

    private PersonDTO person;
    private ActivityDTO activity;
}
