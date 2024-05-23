package com.example.springbootregistroalunos.RecordDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoRecordDto(@NotBlank String nome, @NotNull int idade) {
}
