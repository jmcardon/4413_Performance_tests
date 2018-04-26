package bean;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class BookReviewBean extends JsonBean {

    private int rating;
    private String review;

    public BookReviewBean(int rating, String review) {
        this.setRating(rating);
        this.setReview(review);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public JsonObjectBuilder toJsonObjectBuilder() {
        return Json.createObjectBuilder()
                .add("rating", this.getRating())
                .add("review", this.getReview());
    }
}
