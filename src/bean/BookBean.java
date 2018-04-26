package bean;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "book")
@XmlType(propOrder = {"bid", "title", "price", "category", "description", "rating"})
public class BookBean extends JsonBean {

    @XmlType
    @XmlEnum(String.class)
    public static enum Category {
        @XmlEnumValue("Science") SCIENCE,
        @XmlEnumValue("Fiction") FICTION,
        @XmlEnumValue("Engineering") ENGINEERING;

        public static Category getCategory(String category) {
            if (category == null) return null;
            switch (category.toLowerCase()) {
                case "science":
                    return Category.SCIENCE;
                case "fiction":
                    return Category.FICTION;
                case "engineering":
                    return Category.ENGINEERING;
                default:
                    return null;
            }
        }
    }
    private String bid;
    private String title;
    private int price;
    private float rating;
    private Category category;
    private String description;
    private Set<PoItemBean> poItems = new HashSet<PoItemBean>(0);

    public BookBean() {
        this("", "", 0, null, 0, "");
    }

    public BookBean(String bid, String title, int price, Category category, float rating, String description) {
        super();
        this.bid = bid;
        this.title = title;
        this.price = price;
        this.category = category;
        this.rating = rating;
        this.description = description;
    }

    public String getBid() {
        return bid;
    }


    public void setBid(String bid) {
        this.bid = bid;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlTransient
    public Set<PoItemBean> getPoItems() {
        return poItems;
    }

    public void setPoItems(Set<PoItemBean> poItems) {
        this.poItems = poItems;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public JsonObjectBuilder toJsonObjectBuilder() {
        return Json.createObjectBuilder()
                .add("bid", this.getBid())
                .add("title", this.getTitle())
                .add("price", this.getPrice())
                .add("rating", this.getRating())
                .add("category", this.getCategory().toString())
                .add("description", this.getDescription());
    }

    @Override
    public int hashCode() {
        return bid.hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookBean other = (BookBean) obj;
        return this.getBid().equals(other.getBid());
    }

    @Override
    public String toString() {
        return String.format("Book: bid=%s, title=%s, price=%d, description=%s, category=%s, rating=%.1f", this.getBid(), this.getTitle(), this.getPrice(), this.getDescription(), this.getCategory().toString(), this.getRating());
    }
}
