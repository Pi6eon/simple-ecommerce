package com.fallinnadim.jobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private  ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean inserted = reviewService.addReview(companyId, review);
        if (!inserted) {
            return new ResponseEntity<>("Company do not exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully add review", HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review reviewById = reviewService.getReviewById(companyId, reviewId);
        if (reviewById == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviewById, HttpStatus.OK);
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean isReviewed = reviewService.updateReview(companyId, reviewId, review);
        if (!isReviewed) {
            return new ResponseEntity<>("Review Not FOund", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Review update successfully", HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if (!isDeleted) {
            return new ResponseEntity<>("Review Not FOund", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Review update successfully", HttpStatus.OK);
    }

}
