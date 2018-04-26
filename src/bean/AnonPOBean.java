package bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "anonPurchaseOrder")
public class AnonPOBean {

    @XmlElement
    private String bookId;

    @XmlElementWrapper
    private List<AnonPOItem> itemList;

    @XmlRootElement(name = "purchaseOrder")
    public static class AnonPOItem {

        @XmlElement
        private int price;
        @XmlElement
        private int quantity;
        @XmlElement
        private int rating;
        @XmlElement
        private String review;
        @XmlElement
        private String province;
        @XmlElement
        private String zip;
        @XmlElement
        private PoBean.Status status;

        public AnonPOItem() { }

        public AnonPOItem(int price, int quantity, int rating, String review, String province, String zip, PoBean.Status status) {
            this.price = price;
            this.quantity = quantity;
            this.rating = rating;
            this.review = review;
            this.province = province;
            this.zip = zip;
            this.status = status;
        }
    }

    public AnonPOBean() { }

    public AnonPOBean(String bookId, List<AnonPOItem> itemList) {
        this.bookId = bookId;
        this.itemList = itemList;
    }
}
