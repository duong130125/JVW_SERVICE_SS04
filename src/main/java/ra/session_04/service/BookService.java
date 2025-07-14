package ra.session_04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.session_04.entity.Book;
import ra.session_04.repository.BookRepository;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<Book> getBooks(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
        if (keyword != null && !keyword.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(keyword, pageable);
        }
        return bookRepository.findAll(pageable);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}