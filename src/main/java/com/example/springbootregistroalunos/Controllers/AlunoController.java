package com.example.springbootregistroalunos.Controllers;

import com.example.springbootregistroalunos.Models.AlunoModel;
import com.example.springbootregistroalunos.RecordDto.AlunoRecordDto;
import com.example.springbootregistroalunos.Repositories.AlunoRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AlunoController {
    @Autowired
    AlunoRepository alunoRepository;

    @PostMapping("/alunos")
    public ResponseEntity<AlunoModel> createAlunos(@RequestBody @Valid AlunoRecordDto alunoRecordDto){
        var alunoModel = new AlunoModel();
        BeanUtils.copyProperties(alunoRecordDto, alunoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoRepository.save(alunoModel));
    }

    @GetMapping("/alunos")
    public ResponseEntity<List<AlunoModel>> listarAlunos(){
        return ResponseEntity.status(HttpStatus.OK).body(alunoRepository.findAll());
    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<Object> listarUmAluno(@PathVariable(value="id")UUID id){
        Optional<AlunoModel> alunoO = alunoRepository.findById(id);
        if(alunoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(alunoO.get());
    }

    @PutMapping("alunos/{id}")
    public ResponseEntity<Object> alterarAluno(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid AlunoRecordDto alunoRecordDto){
        Optional<AlunoModel> alunoO = alunoRepository.findById(id);
        if(alunoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        var alunoModel = alunoO.get();
        BeanUtils.copyProperties(alunoRecordDto, alunoModel);
        return ResponseEntity.status(HttpStatus.OK).body(alunoRepository.save(alunoModel));
    }

    @DeleteMapping("alunos/{id}")
    public ResponseEntity<Object> deleteAlunos(@PathVariable(value = "id") UUID id){
        Optional<AlunoModel> alunoO = alunoRepository.findById(id);
        if(alunoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        alunoRepository.delete(alunoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
    }
}
