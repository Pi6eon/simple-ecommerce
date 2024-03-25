package com.fallinnadim.jobapp.review;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
}
