package test.json;

public class Item implements Comparable<Item> {

    public int orderNumber;
    public String type;
    public int size;
    public int boxNumber;
    public String status;

    public Item() {
        super();
    }

    public Item(int orderNumber, String type, int size, int boxNumber, String status) {

        this.orderNumber = orderNumber;
        this.type = type;
        this.size = size;
        this.boxNumber = boxNumber;
        this.status = status;

    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Item i) {
        return type.compareTo(i.getType());
    }

    @Override
    public boolean equals(Object item) {
        Item i = (Item) item;
        return getType().equals(i.getType());

    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    public String toString() {

        return "\norder number: " + orderNumber + " type: " + type + " size: " + size + " in box number: " + boxNumber + " status: " + status;

    }

}
