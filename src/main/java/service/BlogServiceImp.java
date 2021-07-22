package service;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import repository.BlogRepository;

import java.util.List;

public class BlogServiceImp implements BlogService{
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(int id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog model){
        blogRepository.save(model);
    }

    @Override
    public void remove(int id){
        blogRepository.remove(id);
    }
}
