package bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "shoppingCartItem")
@XmlType(propOrder = {"book", "price", "quantity"})
public class ShoppingCartItemBean {

    public ShoppingCartItemBean() {}

    private BookBean book;
    private int quantity;
    private int price;

    public ShoppingCartItemBean(BookBean book, int quantity) {
        this.setBook(book);
        this.setQuantity(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.setPrice(this.getBook().getPrice() * quantity);
    }

    public int getPrice() {
        return price;
    }

    private void setPrice(int price) {
        this.price = price;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

}
