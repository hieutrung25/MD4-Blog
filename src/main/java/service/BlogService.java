package service;

import model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();
    Blog findById(int id);
    void save(Blog model);
    void remove(int id);
}
