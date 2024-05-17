package serialization;

import java.io.Serializable;

public class ProductDetails implements Serializable {

    private static final long serialVersionUID = 3659932210257138726L;

    private	String product;
    private	String feature;

    // this field will not be serialized
    transient private int featureCount;

    @Override
    public String toString() {
        return "ProductDetails{" +
                "product='" + product + '\'' +
                ", feature='" + feature + '\'' +
                ", featureCount=" + featureCount +
                '}';
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getFeatureCount() {
        return featureCount;
    }

    public void setFeatureCount(int count) {
        this.featureCount = count;
    }
}