package com.BlogApplication.BlogApplicaiton.services.model;

import com.BlogApplication.BlogApplicaiton.repositories.entity.Category;
import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Integer postId;
    private String title;
    private String content;
    private String image;



    // separated user and userdto, category and category dro, with this we will get rid of the stackoverflow
    // where post calls user, user calls post and so on till stack overflow. in userDTO and categoryDTO we
    // have not used user and category.
    private UserDTO user;
    private CategoryDTO category;
}
