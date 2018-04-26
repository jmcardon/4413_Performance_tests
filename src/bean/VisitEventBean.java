package bean;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VisitEventBean extends JsonBean {

    @XmlType
    @XmlEnum(String.class)
    public static enum VisitEventType {
        @XmlEnumValue("VIEW") VIEW,
        @XmlEnumValue("CART") CART,
        @XmlEnumValue("PURCHASE") PURCHASE;

        public static VisitEventType getVisitEventType(String visitEventType) {
            if (visitEventType == null) return null;
            switch (visitEventType.toLowerCase()) {
                case "view":
                    return VisitEventType.VIEW;
                case "cart":
                    return VisitEventType.CART;
                case "purchase":
                    return VisitEventType.PURCHASE;
                default:
                    return null;
            }
        }

    }

    String day;
    BookBean book;
    VisitEventType eventType;

    public VisitEventBean(String day, BookBean book, VisitEventType eventType) {
        super();
        this.setDay(day);
        this.setBook(book);
        this.setEventType(eventType);
    }

    public String getDay() {
        return day;
    }
    
    public String getFormattedDate() {
    		String date = this.getDay();
    		try {
				date = new SimpleDateFormat("MMM d, yyyy").format(new SimpleDateFormat("MMddyyyy").parse(this.getDay()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		return date;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

    public VisitEventType getEventType() {
        return eventType;
    }

    public void setEventType(VisitEventType eventType) {
        this.eventType = eventType;
    }

    public JsonObjectBuilder toJsonObjectBuilder() {
        return Json.createObjectBuilder()
                .add("day", this.getDay())
                .add("book", this.getBook().toJsonObjectBuilder())
                .add("eventType", this.getEventType().toString());
    }
}