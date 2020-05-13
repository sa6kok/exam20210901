package com.localbandb.localbandb.web.api.controlers;

import com.localbandb.localbandb.services.services.ReviewService;
import com.localbandb.localbandb.web.api.models.ReviewCreateModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import static com.localbandb.localbandb.web.api.constants.Constants.MY_URL;

@CrossOrigin(origins = MY_URL, maxAge = 3600)
@RestController
@RequestMapping("/review")
public class ReviewController extends BaseController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create/{id}")
    @Secured("ROLE_GUEST")
    public boolean saveConfirm(@PathVariable String id, @RequestBody ReviewCreateModel model) {
        return reviewService.save(model, id);
    }
}
