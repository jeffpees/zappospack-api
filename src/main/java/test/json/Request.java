package test.json;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jeffrep
 * Date: 7/23/14
 * Time: 6:57 AM
 * To change this template use File | Settings | File Templates.
 */

public class Request implements Serializable {
    /* private static final long serialVersionUID = 1513207428686438208L;
    private int left;
    private int right;
    public int getLeft() {return left;}
    public void setLeft(int left) {this.left = left;}
    public int getRight() {return right;}
    public void setRight(int right) {this.right = right;}  */

    private int shoes;
    private int clothes;
    private int bags;
    private int housewares;
    private int beauty;
    private int accessories;
    private boolean random;
    private int randomInt;

    public int getShoes() {return shoes;}
    public void setShoes(int shoes) {this.shoes = shoes;}
    public int getClothes() {return clothes;}
    public void setClothes(int clothes) {this.clothes = clothes;}
    public int getBags() {return bags;}
    public void setBags(int bags) {this.bags = bags;}
    public int getHousewares() {return housewares;}
    public void setHousewares(int housewares) {this.housewares = housewares;}
    public int getBeauty() {return beauty;}
    public void setBeauty(int beauty) {this.beauty = beauty;}
    public int getAccessories() {return accessories;}
    public void setAccessories(int accessories) {this.accessories = accessories;}
    public boolean getRandom() {return random;}
    public void setRandom(boolean random) {this.random = random;}
    public int getRandomInt() {return randomInt;}
    public void setRandomInt(int randomInt) {this.randomInt = randomInt;}
}
