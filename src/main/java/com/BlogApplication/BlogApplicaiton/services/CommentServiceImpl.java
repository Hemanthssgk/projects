package com.BlogApplication.BlogApplicaiton.services;

import com.BlogApplication.BlogApplicaiton.exceptions.NoResourceFoundException;
import com.BlogApplication.BlogApplicaiton.repositories.CommentRepo;
import com.BlogApplication.BlogApplicaiton.repositories.PostRepository;
import com.BlogApplication.BlogApplicaiton.repositories.UserRepository;
import com.BlogApplication.BlogApplicaiton.repositories.entity.Comment;
import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import com.BlogApplication.BlogApplicaiton.services.interfaces.CommentService;
import com.BlogApplication.BlogApplicaiton.services.model.CommentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDTO saveComment(Comment comment, Integer userId, Integer postId) {
        if (comment != null && postId != null && userRepository.existsById(userId) && postRepository.existsById(postId)) {
            if (postRepository.findById(postId).isPresent()) {
                comment.setPost(postRepository.findById(postId).get());
            } else {
                throw new NoResourceFoundException("No Post Found");
            }

            if (userId == null) {
                comment.setUser(null);
            } else {
                comment.setUser(userRepository.findById(userId).orElseThrow(() -> new NoResourceFoundException("No User found")));
            }

            Comment persistedComment = commentRepo.save(comment);
            return this.modelMapper.map(persistedComment, CommentDTO.class);
        }
        throw new NoResourceFoundException("Either user or post doesn't exists or invalid user or post");
    }

    @Override
    public CommentDTO updateComment(Comment comment, Integer commentId, Integer userId, Integer postId) {
        if (comment != null && postId != null && userRepository.existsById(userId) && postRepository.existsById(postId)) {
            if (postRepository.findById(postId).isPresent()) {
                comment.setPost(postRepository.findById(postId).get());
            } else {
                throw new NoResourceFoundException("No Post Found");
            }

            if (userId == null) {
                comment.setUser(null);
            } else {
                comment.setUser(userRepository.findById(userId).orElseThrow(() -> new NoResourceFoundException("No User found")));
            }
            comment.setCommentId(commentId);
            Comment persistedComment = commentRepo.save(comment);
            return this.modelMapper.map(persistedComment, CommentDTO.class);
        }
        throw new NoResourceFoundException("Either user or post doesn't exists or invalid user or post");
    }

    @Override
    public void deleteComment(Comment comment) {
        if (comment != null&&comment.getCommentId()!=null) {
            commentRepo.deleteById(comment.getCommentId());
        }
    }

    @Override
    public List<CommentDTO> getCommentsByUserId(Integer userId) {
        if (userId!=null)
        {
            List<Comment> comments = commentRepo.findAllByUser_UserId(userId);
            return comments.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
        }
        return null;
    }
}
