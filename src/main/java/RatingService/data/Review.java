package RatingService.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "reviews")
public class Review {

    private String id;
    private Customer customer;
    private Product product;
    private int rating;
    private Map <String,String> reviewContent;

    public Review(Customer customer, Product product, int rating, Map<String, String> reviewContent) {
        this.customer = customer;
        this.product = product;
        this.rating = rating;
        this.reviewContent = reviewContent;
    }

    public Review() {
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Map<String, String> getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(Map<String, String> reviewContent) {
        this.reviewContent = reviewContent;
    }
}
