package br.com.maferfs.libraryapi.dto;

import br.com.maferfs.libraryapi.model.Book;

public class BookResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;

    public BookResponseDTO(Book book){
        this.id = book.getId();
        this.titulo = book.getTitulo();
        this.autor = book.getAutor();
        this.isbn = book.getIsbn();
    }
    public Long getId() {
        return id;
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
