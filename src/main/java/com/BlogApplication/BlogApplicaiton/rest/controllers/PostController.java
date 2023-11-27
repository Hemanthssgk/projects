package com.BlogApplication.BlogApplicaiton.rest.controllers;

import com.BlogApplication.BlogApplicaiton.rest.exceptions.model.ApiResponse;
import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.Post;
import com.BlogApplication.BlogApplicaiton.rest.services.interfaces.FileService;
import com.BlogApplication.BlogApplicaiton.rest.services.interfaces.PostService;
import com.BlogApplication.BlogApplicaiton.rest.services.model.PostDTO;
import com.BlogApplication.BlogApplicaiton.rest.services.model.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    FileService fileService;
    @Value("${image.location}")
    String pathToCopyFiles;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDTO> createPost(@RequestBody Post post, @PathVariable Integer userId, @PathVariable Integer categoryId) throws Exception {
        return ResponseEntity.ok(postService.createPost(post, userId, categoryId));
    }

    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(postService.getAllPostByCategory(categoryId));
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.getAllPostByUser(userId));
    }

    @GetMapping("/post")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(required = false, defaultValue = "0") int pageNumber, @RequestParam(required = false, defaultValue = "10") int pageSize, @RequestParam(required = false, defaultValue = "asc") String sortOrder, @RequestParam(required = false, defaultValue = "postId") String sortBy) {
        return ResponseEntity.ok(postService.getAllPosts(pageNumber, pageSize, sortOrder.toLowerCase(), sortBy));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.getById(postId));
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody Post post, @PathVariable Integer postId) throws Exception {
        return ResponseEntity.ok(postService.updatePost(post, postId));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) throws Exception {
        postService.deletePost(postId);
        return ResponseEntity.ok(new ApiResponse("DeletedPost", new Date(), true));

    }

    @GetMapping("/post/search")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(postService.searchPosts(search));
    }

    @PostMapping("/post/upload")
    public ResponseEntity<String> uploadFileToFileSystem(@RequestBody MultipartFile multipartFile) throws IOException {
        fileService.uploadImage(pathToCopyFiles, multipartFile);
        return ResponseEntity.ok("File is Uploaded Sucessfully");
    }

    @GetMapping("/post/download")
    public ResponseEntity<byte[]> downloadFile( @RequestParam String fileName) throws IOException {
        byte[] fileContent = fileService.downloadImage(fileName, pathToCopyFiles);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }
}
