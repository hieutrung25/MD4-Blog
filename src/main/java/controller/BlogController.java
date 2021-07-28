package controller;

import model.Blog;
import model.BlogForm;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.BlogService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private Environment environment;
    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public String showBlog(Model model){
        List<Blog> list = blogService.findAll();
        model.addAttribute("list",list);
        return "list";
    }
    @GetMapping("/create")
    public String showFormCreate(Model model){
        model.addAttribute("blog",new BlogForm());
        return "/create";
    }
    @PostMapping("/create")
    public String createBlog(@ModelAttribute("BlogForm") BlogForm blogForm, Model model){
        Blog blog = new Blog(blogForm.getName(),blogForm.getContent());
        MultipartFile file = blogForm.getImg();
        String img = file.getOriginalFilename();
        blog.setImg(img);
        String fileUpload = environment.getProperty("file_upload").toString();
        try{
            FileCopyUtils.copy(file.getBytes(),new File(fileUpload+img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blogService.save(blog);
        model.addAttribute("blog", new BlogForm());
        return "redirect:/blog";
    }
    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable int id, Model model){
        model.addAttribute("blog", blogService.findById(id));
        return "edit";
    }
    @PostMapping("/edit/{id}")
    public String editBlog(@ModelAttribute BlogForm blogForm, @PathVariable int id){
        Blog blog = blogService.findById(id);
        blog.setName(blogForm.getName());
        blog.setContent(blogForm.getContent());
        if(blogForm.getImg().getSize() > 0) {
            MultipartFile file = blogForm.getImg();
            String img = file.getOriginalFilename();
            blog.setImg(img);
            String fileUpload = environment.getProperty("file_upload").toString();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + img));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        blogService.save(blog);
        return "redirect:/blog";
    }
    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable int id){
        blogService.remove(id);
        return "redirect:/blog";
    }
}
//