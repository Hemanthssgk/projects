package com.BlogApplication.BlogApplicaiton.services.interfaces;

import com.BlogApplication.BlogApplicaiton.repositories.entity.Post;
import com.BlogApplication.BlogApplicaiton.services.model.PostDTO;
import com.BlogApplication.BlogApplicaiton.services.model.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    PostDTO createPost(Post post,Integer userId, Integer categoryId) throws Exception;
    PostDTO updatePost(Post post,Integer id);
    void deletePost(Integer id);
    PostDTO getById(Integer id);
    PostResponse getAllPosts(int pageNumber, int pageSize, String sortOrder, String sortBy);

    List<PostDTO> getAllPostByCategory(Integer categoryId);
    List<PostDTO> getAllPostByUser(Integer userId);

    List<PostDTO> searchPosts(String keyword);

}
