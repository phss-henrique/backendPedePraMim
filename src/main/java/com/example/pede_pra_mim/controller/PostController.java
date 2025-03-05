package com.example.pede_pra_mim.controller;

import com.example.pede_pra_mim.post.Post;
import com.example.pede_pra_mim.post.PostRequestDTO;
import com.example.pede_pra_mim.post.PostResponseDTO;
import com.example.pede_pra_mim.post.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository repository;

    @Value("${upload.directory}")
    private String uploadDir;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(consumes = "multipart/form-data")
    public void savePost(@RequestPart("data") String data, @RequestPart("imagem") MultipartFile imagem) {
        try {

            PostRequestDTO postData = objectMapper.readValue(data, PostRequestDTO.class);


            if (imagem == null || imagem.isEmpty()) {
                throw new IllegalArgumentException("A imagem é obrigatória para o post.");
            }


            String uniqueFileName = UUID.randomUUID().toString() + "_" + imagem.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, uniqueFileName);


            Files.createDirectories(filePath.getParent());
            Files.write(filePath, imagem.getBytes());

            String imageUrl = "/uploads/" + uniqueFileName;



            Post post = new Post(postData, imageUrl);
            repository.save(post);

        } catch (IOException e) {
            System.err.println("Erro ao salvar a imagem ou processar o JSON: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar o arquivo de imagem ou processar o JSON.", e);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
            throw e;
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        // Recupera todos os posts do banco de dados e ordena por ID
        List<PostResponseDTO> posts = repository.findAll().stream()
               // .sorted((p1, p2) -> Long.compare(p1.getId(), p2.getId())) // Ordena por ID (ascendente)
                .map(PostResponseDTO::new) // Converte para o DTO de resposta
                .toList();

        return ResponseEntity.ok(posts);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id_servicos}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id_servicos) {
        // Busca o post pelo ID no banco de dados
        Post post = repository.findById(id_servicos).orElse(null);

        // Verifica se o post foi encontrado
        if (post == null) {
            return ResponseEntity.notFound().build();  // Retorna 404 se o post não for encontrado
        }

        // Converte para o DTO e retorna a resposta
        PostResponseDTO postResponseDTO = new PostResponseDTO(post);
        return ResponseEntity.ok(postResponseDTO);
    }

   /* @GetMapping
    public List<PostResponseDTO> getAll() {
        // Retorna todos os posts mapeados para o DTO de resposta
        return repository.findAll().stream()
                .map(PostResponseDTO::new)
                .toList();
    }

    */
}
