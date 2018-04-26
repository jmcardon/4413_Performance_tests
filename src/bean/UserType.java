package bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum UserType {
    @XmlEnumValue("Visitor") VISITOR,
    @XmlEnumValue("Customer") CUSTOMER,
    @XmlEnumValue("Partner") PARTNER,
    @XmlEnumValue("Administrator") ADMIN;

    public static UserType getUserType(String type) throws Exception {
        if (type == null) return null;
        switch (type.toLowerCase()) {
            case "visitor":
                return UserType.VISITOR;
            case "customer":
                return UserType.CUSTOMER;
            case "partner":
                return UserType.PARTNER;
            case "admin":
                return UserType.ADMIN;
            default:
                throw new Exception("Invalid user type");
        }
    }
}
