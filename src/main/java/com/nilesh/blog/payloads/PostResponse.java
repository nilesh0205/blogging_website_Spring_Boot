package com.nilesh.blog.payloads;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	 private List<PostDto> content;
	 private int pageNumber;
	 private int pageSize;
	 private long totalElements;
	 private int totalPages;
	 private boolean lastPage;
}
