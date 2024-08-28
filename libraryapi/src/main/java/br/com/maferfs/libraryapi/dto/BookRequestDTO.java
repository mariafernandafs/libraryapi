package br.com.maferfs.libraryapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookRequestDTO {
    @NotBlank(message = "Titulo é mandatório")
    @Size(max = 100, message = "Título não pode exceder a 100 caracteres")
    private String titulo;

    @NotBlank(message = "Autor é mandatório")
    @Size(max = 50, message =  "Autor não pode exceder a 50 caracteres")
    private String autor;

    private String isbn;

    public BookRequestDTO(String titulo, String autor, String isbn){
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
