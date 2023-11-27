package com.BlogApplication.BlogApplicaiton.rest.repositories.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    //    using join column here becuase, foriegn key will be stored in the comment table
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;
    //    using join column here becuase, foriegn key will be stored in the comment table
    @ManyToOne
    @JoinColumn(name = "userid")
    User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    @NotEmpty
    private String comment;
}
