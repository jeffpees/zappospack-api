package test.json;

public class Item implements Comparable<Item> {

    public int orderNumber;
    public int itemNumber;
    public String type;
    public int size;
    public int boxNumber;
    public String status;

    public Item() {
        super();
    }

    public Item(int orderNumber, int itemNumber, String type, int size, int boxNumber, String status) {

        this.orderNumber = orderNumber;
        this.itemNumber = itemNumber;
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

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber() {
        this.itemNumber = itemNumber;
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

    @Override
    public String toString() {

        return "Item [order number= " + orderNumber + ", item number= " + itemNumber +  ", type= " + type + ", size= " + size + ", boxnumber= " + boxNumber + ", status:= " + status + "]";
    }

}
