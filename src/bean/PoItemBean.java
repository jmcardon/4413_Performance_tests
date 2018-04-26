package bean;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "purchaseOrderItem")
@XmlType(propOrder = {"price", "quantity", "rating", "review", "book"})
@XmlAccessorType(XmlAccessType.FIELD)
public class PoItemBean {

    public PoItemBean(){}

    private PoItemIdBean pk = new PoItemIdBean();
    @XmlAttribute
    private int price;
    @XmlAttribute
    private int quantity;
    @XmlAttribute
    private int rating;
    @XmlAttribute
    private String review;

    public PoItemBean(int price, int quantity, PoBean po, BookBean book) {
        this(price, quantity, po, book, 0, null);
    }

    public PoItemBean(int price, int quantity, PoBean po, BookBean book, int rating, String review) {
        super();
        this.setPrice(price);
        this.setQuantity(quantity);
        this.getPk().setPo(po);
        this.getPk().setBook(book);
        this.setRating(rating);
        this.setReview(review);
    }

    public PoItemIdBean getPk() {
        return pk;
    }

    public void setPk(PoItemIdBean pk) {
        this.pk = pk;
    }

    public PoBean getPo() {
        return this.getPk().getPo();
    }

    public void setPo(PoBean po) {
        this.getPk().setPo(po);
    }

    @XmlElement(name = "book")
    public BookBean getBook() {
        return this.getPk().getBook();
    }

    public void setBook(BookBean book) {
        this.getPk().setBook(book);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
