package com.fallinnadim.jobapp.review.impl;

import com.fallinnadim.jobapp.company.Company;
import com.fallinnadim.jobapp.company.CompanyService;
import com.fallinnadim.jobapp.review.Review;
import com.fallinnadim.jobapp.review.ReviewRepository;
import com.fallinnadim.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return false;
        }
        review.setCompany(company);
        reviewRepository.save(review);
        return true;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId()
                .equals(reviewId))
                .findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        if (companyService.getCompanyById(companyId) == null) {
            return false;
        }
        review.setCompany(companyService.getCompanyById(companyId));
        review.setId(reviewId);
        reviewRepository.save(review);
        return true;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review); // removing review from review list, associated with company before deleting it
            review.setCompany(null); // set company to null ???
            companyService.updateCompany(company, companyId); // update
            reviewRepository.deleteById(reviewId); // delete review by id
            return true;
        }
        return false;
    }
}
