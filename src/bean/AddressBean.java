package bean;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "address")
@XmlType(propOrder = {"id", "street", "province", "country", "zip", "phone"})
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressBean {

    @XmlAttribute
    private int id;
    @XmlAttribute
    private String street;
    @XmlAttribute
    private String province;
    @XmlAttribute
    private String country;
    @XmlAttribute
    private String zip;
    @XmlAttribute
    private String phone;

    public AddressBean(int id, String street, String province, String country, String zip) {
        this(id, street, province, country, zip, null);
    }

    public AddressBean(int id, String street, String province, String country, String zip, String phone) {
        super();
        this.setId(id);
        this.setStreet(street);
        this.setProvince(province);
        this.setCountry(country);
        this.setZip(zip);
        this.setPhone(phone);
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
    }


    public String getProvince() {
        return province;
    }


    public void setProvince(String province) {
        this.province = province;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getZip() {
        return zip;
    }


    public void setZip(String zip) {
        this.zip = zip;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public JsonObjectBuilder toJsonObjectBuilder() {
        return Json.createObjectBuilder()
                .add("id", this.getId())
                .add("street", this.getStreet())
                .add("province", this.getProvince())
                .add("country", this.getCountry())
                .add("zip", this.getZip())
                .add("phone", this.getPhone());
    }

    @Override
    public String toString() {
        return this.toJsonObjectBuilder().build().toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + id;
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((province == null) ? 0 : province.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((zip == null) ? 0 : zip.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddressBean other = (AddressBean) obj;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (id != other.id)
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (province == null) {
            if (other.province != null)
                return false;
        } else if (!province.equals(other.province))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        if (zip == null) {
            if (other.zip != null)
                return false;
        } else if (!zip.equals(other.zip))
            return false;
        return true;
    }

    public static void main(String[] args) {
        AddressBean address = new AddressBean(1, "1st Ave", "Ontario", "Canada", "M1M 3G5", "647-128-1832");
        String json = address.toJsonObjectBuilder().build().toString();
        System.out.println(json);
    }

}
