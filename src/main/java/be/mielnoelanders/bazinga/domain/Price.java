package be.mielnoelanders.bazinga.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Price extends AbstractEntity implements Serializable {

    private static final long serialVersionUID =1L;

    @NotNull
    private double normalPrice;
    @NotNull
    private int promotionPercentage;
    @NotNull
    private double actualPrice;

    public double getNormalPrice() {
        return normalPrice;
    }
    public void setNormalPrice(double normalPrice) {
        this.normalPrice = normalPrice;
    }
    public int getPromotionPercentage() {
        return promotionPercentage;
    }
    public void setPromotionPercentage(int promotionPercentage) {
        this.promotionPercentage = promotionPercentage;
    }
    public double getActualPrice() {
        return actualPrice;
    }
    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }


}
