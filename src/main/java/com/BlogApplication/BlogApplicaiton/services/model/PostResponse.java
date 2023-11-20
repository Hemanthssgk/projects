package com.BlogApplication.BlogApplicaiton.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private List<PostDTO> content = new ArrayList<>();

    private Integer pageSize;
    private Integer pageNumber;
    private Integer totalElements;
    private Integer totalPages;
    private boolean isLastPage;
}
