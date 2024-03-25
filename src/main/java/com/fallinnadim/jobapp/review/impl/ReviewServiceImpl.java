package com.fallinnadim.jobapp.review.impl;

import com.fallinnadim.jobapp.review.Review;
import com.fallinnadim.jobapp.review.ReviewRepository;
import com.fallinnadim.jobapp.review.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }
}
