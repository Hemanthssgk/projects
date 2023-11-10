package com.BlogApplication.BlogApplicaiton.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Integer categoryId;
    private String title;
    private String description;

//    private List<Post> posts;
//
//    List<Role> roles;
}
