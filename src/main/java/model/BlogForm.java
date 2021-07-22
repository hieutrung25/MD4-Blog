package model;

import org.springframework.web.multipart.MultipartFile;

public class BlogForm {
    private int id;
    private String name;
    private MultipartFile img;
    private String content;

    public BlogForm() {
    }

    public BlogForm(String name, MultipartFile img, String content) {
        this.name = name;
        this.img = img;
        this.content = content;
    }

    public BlogForm(int id, String name, MultipartFile img, String content) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
