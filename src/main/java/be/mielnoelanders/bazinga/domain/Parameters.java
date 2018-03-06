package be.mielnoelanders.bazinga.domain;

import java.io.Serializable;

public class Parameters extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -1926628474232469673L;

    // FIELDS
    // valid type to be found in ParameterEnum
    private String type;
    private int percentage;

    // CONSTUCTORS
    public Parameters() {
    }

    public Parameters(String type, int percentage) {
        this.type = type;
        this.percentage = percentage;
    }

    //GETTERS & SETTERS
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    //Overrides

    @Override
    public String toString() {
        return "Parameters{" +
                "type='" + type + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
