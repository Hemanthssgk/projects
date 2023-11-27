package com.BlogApplication.BlogApplicaiton.rest.services;

import com.BlogApplication.BlogApplicaiton.rest.exceptions.NoResourceFoundException;
import com.BlogApplication.BlogApplicaiton.rest.exceptions.NoResourcePassedException;
import com.BlogApplication.BlogApplicaiton.rest.repositories.CategoryRepo;
import com.BlogApplication.BlogApplicaiton.rest.repositories.PostRepository;
import com.BlogApplication.BlogApplicaiton.rest.repositories.UserRepository;
import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.Category;
import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.Post;
import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.User;
import com.BlogApplication.BlogApplicaiton.rest.services.interfaces.PostService;
import com.BlogApplication.BlogApplicaiton.rest.services.model.PostDTO;
import com.BlogApplication.BlogApplicaiton.rest.services.model.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    //TODO:  here we have a issue if a exception occurs after the transaction then the values are not rolled back. fix it. can be fixed by using @Transactional
    public PostDTO createPost(Post post, Integer userId, Integer categoryId) throws Exception {
        if (post!=null&&userId!=null&&categoryId!=null)
        {
            post.setCategory(categoryRepo.findById(categoryId).orElseThrow(() -> new NoResourceFoundException("Category","categoryId",categoryId)));
            post.setUser(userRepository.findById(userId).orElseThrow(() -> new NoResourceFoundException("User","UserId",userId)));
            return this.modelMapper.map(postRepository.save(post), PostDTO.class);
        }
        throw new NoResourcePassedException("No Resource Passed"+post+userId+categoryId);
    }

    @Override
    public PostDTO updatePost(Post post,Integer id) {
        if (id!=null&&post!=null)
        {
            // getting the existing data from db and updating the details from the received object so to update the fields in the database.
            Post post1 = postRepository.findById(id).orElseThrow(()-> new NoResourceFoundException("Post","Id",id));
            post.setPostId(id);
            post.setUser(post1.getUser());
            post.setCategory(post1.getCategory());
            post.setTitle(post1.getTitle());
            post.setImage(post.getImage());
            post.setUser(post1.getUser());
            post.setPostId(post1.getPostId());
            return this.modelMapper.map(postRepository.save(post), PostDTO.class);
        }
        throw new NoResourcePassedException("either id or post is null");

    }

    @Override
    public void deletePost(Integer id) {
        if (id!=null&&postRepository.existsById(id))
        {
            postRepository.deleteById(id);
        }
        throw new NoResourcePassedException("Null or post doesnt exists: id:"+id);
    }

    @Override
    public PostDTO getById(Integer id) {
        if (id!=null&&postRepository.existsById(id))
        {
            return this.modelMapper.map(postRepository.findById(id),PostDTO.class);
        }
        throw new NoResourcePassedException("Null or post doesn't exists: id:"+id);
    }

    @Override
    public PostResponse getAllPosts(int pageNumber,int pageSize,String sortOrder, String sortBy) {
        // this will paginate and sort the data.
        Sort sort = sortOrder.equals("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PostResponse postResponse = new PostResponse();
        Page<Post> posts= postRepository.findAll(PageRequest.of(pageNumber, pageSize,sort));
        postResponse.setContent(posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList()));
        postResponse.setTotalElements(posts.getSize());
        postResponse.setLastPage(posts.isLast());
        postResponse.setPageSize(pageSize);
        postResponse.setPageNumber(pageNumber);
        postResponse.setTotalPages(posts.getTotalPages());
        return postResponse;
//        return postRepository.findAll().stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }


    @Override
    public List<PostDTO> getAllPostByCategory(Integer categoryId) {

        if (categoryId!=null) {
            Optional<Category> category = categoryRepo.findById(categoryId);
            return category.map(value -> postRepository.findAllByCategory(value).stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList())).orElseGet(() -> List.of(new PostDTO()));
        }
        throw new NoResourcePassedException("Id value is null id:"+categoryId);
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer userId) {
        if (userId!=null) {
            Optional<User> user = userRepository.findById(userId);
            return user.map(value -> postRepository.findAllByUser(value).stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList())).orElseGet(() -> List.of(new PostDTO()));
        }
        throw new NoResourcePassedException("Id value is null id:"+userId);
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        return postRepository.findByTitleContaining(keyword).stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }
}
