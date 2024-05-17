package serialization.inheritance;

import org.apache.commons.lang.StringUtils;
import org.mortbay.util.StringUtil;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ChildClass extends ParentClass implements Serializable, ObjectInputValidation {

    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Summary[ productId=" + getProductId() + ", product=" + getProduct()
                + ", brand=" + getBrand() + " ]";
    }


    //If the readObject() method is present in our class, ObjectInputStream‘s readObject() method
    // will use our class’s readObject() method for reading the object from the stream.
    // Custom change during Deserialization

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        // notice the order of read and write should be same
        this.brand = brand.substring(4);
        setProductId(ois.readInt());
        setProduct((String) ois.readObject());
    }

    //Custom Change During Serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        this.brand = "xyz-" + brand;
        oos.defaultWriteObject();

        oos.writeInt(getProductId());
        oos.writeObject(getProduct());
    }

    @Override
    public void validateObject() throws InvalidObjectException {
        // validate the object here
        if (StringUtils.isEmpty(brand))
            throw new InvalidObjectException("brand is not set or empty.");
        if (getProductId() <= 0)
            throw new InvalidObjectException("productId is not set or zero.");
    }
}
