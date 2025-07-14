package ra.session_04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.session_04.entity.Book;
import ra.session_04.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Hiển thị danh sách sách với phân trang và tìm kiếm
    @GetMapping
    public String listBooks(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(required = false) String keyword) {
        model.addAttribute("bookPage", bookService.getBooks(keyword, page));
        model.addAttribute("keyword", keyword);
        return "book-list";
    }

    // Hiển thị form thêm mới
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("title", "Add New Book");
        return "book-form";
    }

    // Xử lý thêm mới hoặc cập nhật
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    // Hiển thị form cập nhật
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        bookService.getById(id).ifPresent(book -> model.addAttribute("book", book));
        model.addAttribute("title", "Edit Book");
        return "book-form";
    }

    // Xóa sách
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}