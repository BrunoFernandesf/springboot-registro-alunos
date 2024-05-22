package com.example.springbootregistroalunos.RecordDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoRecordDto(@NotNull String nome, @NotBlank int idade) {
}
