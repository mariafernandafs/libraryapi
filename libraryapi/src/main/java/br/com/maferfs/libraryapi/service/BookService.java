package br.com.maferfs.libraryapi.service;

import br.com.maferfs.libraryapi.dto.BookRequestDTO;
import br.com.maferfs.libraryapi.dto.BookResponseDTO;
import br.com.maferfs.libraryapi.exceptions.ResourceNotFoundException;
import br.com.maferfs.libraryapi.model.Book;
import br.com.maferfs.libraryapi.respository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookResponseDTO createBook(BookRequestDTO bookRequestDto){
        Book bookCriado = bookRepository.save(new Book(bookRequestDto));
        return new BookResponseDTO(bookCriado);
    }

    public List<BookResponseDTO> listarBooks(){
        List<Book> listaBooks =  bookRepository.findAll();

        List<BookResponseDTO> listaResponseDTO = new ArrayList<>();
        for(Book book: listaBooks) {
            BookResponseDTO bookResponseDTO = new BookResponseDTO(book);
            listaResponseDTO.add(bookResponseDTO);
        }

        /*
        // Converte a lista de Book em uma lista de BookResponseDTO
        return listaBook.stream()
                .map(book -> new BookResponseDTO(book))// Converte cada Book em BookResponseDTO
                .collect(Collectors.toList()); // Coleta os resultados em uma lista

         */
        return listaResponseDTO;
    }

    public BookResponseDTO listarBookPorId(Long id){
        Optional<Book> bookId = bookRepository.findById(id);

        if(!bookId.isEmpty()){
            return new BookResponseDTO(bookId.get());
        }
        else{
            throw new ResourceNotFoundException("Livro não encontrado com o ID: " + id);
        }
    }
    public BookResponseDTO listarBookPorTitulo(String titulo){
        Optional<Book> bookTitulo= bookRepository.findByTitulo(titulo);

        if(bookTitulo.isPresent()){
            System.out.println("livro encontrado");
            return new BookResponseDTO(bookTitulo.get());
        }
        else{
            throw new ResourceNotFoundException("Livro não encontrado com o título: " + titulo);
        }
    }

    public BookResponseDTO atualizarBook(Book book, Long id){
        Optional<Book> bookAtualizar = bookRepository.findById(id);

        if(bookAtualizar.isPresent()){
            Book bookExistente = bookAtualizar.get();

            bookExistente.setTitulo(book.getTitulo());
            bookExistente.setAutor(book.getAutor());
            bookExistente.setIsbn(book.getIsbn());

            bookRepository.save(bookExistente);
            return new BookResponseDTO(bookExistente);
        }
        else{
            throw new ResourceNotFoundException("Livro não encontrado para ser atualizado");
        }
    }
    public void deletarBook (Long id){
        Optional<Book> bookDeletar = bookRepository.findById(id);

        if(bookDeletar.isPresent()){
            Book bookExistente = bookDeletar.get();
            bookRepository.delete(bookExistente);
        }
        else{
            throw new ResourceNotFoundException("Livro não encontrado para ser deletado");
        }

    }


}
