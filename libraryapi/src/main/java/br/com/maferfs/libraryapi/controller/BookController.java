package br.com.maferfs.libraryapi.controller;

import br.com.maferfs.libraryapi.dto.BookRequestDTO;
import br.com.maferfs.libraryapi.dto.BookResponseDTO;
import br.com.maferfs.libraryapi.exceptions.ResourceNotFoundException;
import br.com.maferfs.libraryapi.model.Book;
import br.com.maferfs.libraryapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
       BookResponseDTO bookCriado = bookService.createBook(bookRequestDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(bookCriado);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> listarBooks() {
        List<BookResponseDTO> bookList = bookService.listarBooks();
        return ResponseEntity.status(HttpStatus.OK).body(bookList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> listarBookById(@PathVariable Long id) {
        BookResponseDTO bookEncontrado = bookService.listarBookPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookEncontrado);
    }

    @GetMapping("/titulo")
    public ResponseEntity<BookResponseDTO> listarBookByTitulo(@RequestParam (value = "titulo", required = true)String titulo) {
        BookResponseDTO bookEncontrado = bookService.listarBookPorTitulo(titulo);
        return ResponseEntity.status(HttpStatus.OK).body(bookEncontrado);
    }

    @PutMapping("/{id}") //retomar erro personalizado para nao envio do RequestBody
    public ResponseEntity<BookResponseDTO> atualizarBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        BookResponseDTO bookAtualizado = bookService.atualizarBook(book, id);
        return ResponseEntity.status(HttpStatus.OK).body(bookAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarBook(@Valid @PathVariable Long id) {
        bookService.deletarBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    //GET /books?page={page}&size={size}&sort={sort}: Implementar paginação e ordenação.

}
