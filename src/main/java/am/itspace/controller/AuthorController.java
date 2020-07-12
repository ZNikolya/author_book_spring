package am.itspace.controller;


import am.itspace.model.Author;
import am.itspace.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {



    private final AuthorRepository authorRepo;


    @GetMapping("/authorPage")
    public String authorPage(ModelMap modelMap) {
        List<Author> allAuthor = authorRepo.findAll();
        modelMap.addAttribute("authors", allAuthor);
        return "authorPage/author";
    }

    @GetMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam("id") int id) {
        authorRepo.deleteById(id);
        return "redirect:/authorPage";
    }

    @GetMapping("authorPage/editAuthor")
    public String authorByIde(@RequestParam("id") int id, ModelMap map) {
        Author author = authorRepo.getOne(id);
        map.addAttribute("author", author);
        return "authorPage/editAuthor";
    }

    @PostMapping("/editAuthor")
    public String changeAuthor(@ModelAttribute Author author) {
        authorRepo.save(author);
        return "redirect:/";
    }



}
