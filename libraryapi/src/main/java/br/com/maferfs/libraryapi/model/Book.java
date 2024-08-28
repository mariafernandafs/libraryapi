package br.com.maferfs.libraryapi.model;

import br.com.maferfs.libraryapi.dto.BookRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)

public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @ToString.Include
    private String titulo;
    @Column
    @ToString.Include
    private String autor;
    @Column
    private String isbn;

    public Book(BookRequestDTO bookRequestDTO){
        this.titulo = bookRequestDTO.getTitulo();
        this.autor = bookRequestDTO.getAutor();
        this.isbn = bookRequestDTO.getIsbn();
    }

}
