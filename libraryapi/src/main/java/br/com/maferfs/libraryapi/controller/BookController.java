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
    public BookResponseDTO createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.createBook(bookRequestDTO);
    }

    @GetMapping
    public List<BookResponseDTO> listarBooks() {
        return bookService.listarBooks();
    }

    @GetMapping("/{id}")
    public BookResponseDTO listarBookById(@PathVariable Long id) {
        return bookService.listarBookPorId(id);
    }

    @GetMapping("/titulo")
    public BookResponseDTO listarBookByTitulo(@RequestParam (value = "titulo", required = true)String titulo) {
        return bookService.listarBookPorTitulo(titulo);
    }

    @PutMapping("/{id}") //retomar erro personalizado para nao envio do RequestBody
    public BookResponseDTO atualizarBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        return bookService.atualizarBook(book, id);
    }

    @DeleteMapping("/{id}")
    public void deletarBook(@Valid @PathVariable Long id) {
        bookService.deletarBook(id);
    }



    //GET /books?page={page}&size={size}&sort={sort}: Implementar paginação e ordenação.

}
