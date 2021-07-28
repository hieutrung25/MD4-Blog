package repository;

import model.Blog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BlogRepositoryImp implements BlogRepository{
    @PersistenceContext
    private EntityManager ent;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = ent.createQuery("select b from Blog as b",Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(int id) {
        Blog blog = ent.find(Blog.class,id);
        return blog;
    }

    @Override
    public void save(Blog model) {
        if(model.getId() >0){
            ent.merge(model);
        }else {
            ent.persist(model);
        }
    }

    @Override
    public void remove(int id) {
        Blog blog = findById(id);
        if(blog != null){
            ent.remove(blog);
        }
    }
}
//