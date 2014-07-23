package test.json;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Controller
@RequestMapping("/test")
public class ApiController {

    public List<Item> listOfItems = new ArrayList<Item>();
    private int orderNumber = 1, boxID = 1, setNums = 50;

    @RequestMapping(method = RequestMethod.POST, value = "order")
    @ResponseBody
    public Result order(@RequestBody final Request request) {
        final Result result = new Result();
        /*  result.setAddition((request.getLeft() + request.getRight()) * 2 );
        result.setSubtraction(request.getLeft() - request.getRight());
        result.setMultiplication(request.getLeft() * request.getRight());
        return result; */

        int acc = request.getAccessories();
        int bags = request.getBags();
        int beaut = request.getBeauty();
        int cloth = request.getClothes();
        int house = request.getHousewares();
        int shoes = request.getShoes();
        int total = (acc + bags + beaut + cloth + house + shoes);

        createOrder(acc, bags, beaut, cloth, house, shoes);



        result.setAccessories(acc);
        result.setBags(bags);
        result.setBeauty(beaut);
        result.setClothes(cloth);
        result.setHousewares(house);
        result.setShoes(shoes);
        result.setTotal(total);

        return result;


    }


    public void createOrder (int accessories, int bags, int beauty, int clothes, int housewares, int shoes) {


    try {

            for (int x = 0; x < accessories; x++) {
                Item item = new Item();

                item.orderNumber = 1;
                item.type = "accessories";
                item.size = 1;
                item.status = "unboxed";

                listOfItems.add(item);

                x++;

            }

            for (int x = 0; x < bags; x++) {
                Item item = new Item();

                item.orderNumber = 1;
                item.type = "bags";
                item.size = 3;
                item.status = "unboxed";

                listOfItems.add(item);

                x++;

            }

            for (int x = 0; x < beauty; x++) {
                Item item = new Item();

                item.orderNumber = 1;
                item.type = "beauty";
                item.size = 1;
                item.status = "unboxed";

                listOfItems.add(item);

                x++;

            }

            for (int x = 0; x < clothes; x++) {
                Item item = new Item();

                item.orderNumber = 1;
                item.type = "clothes";
                item.size = 2;
                item.status = "unboxed";

                listOfItems.add(item);

                x++;

            }

            for (int x = 0; x < housewares; x++) {
                Item item = new Item();

                item.orderNumber = 1;
                item.type = "housewares";
                item.size = 5;
                item.status = "unboxed";

                listOfItems.add(item);

                x++;

            }

            for (int x = 0; x < shoes; x++) {
                Item item = new Item();

                item.orderNumber = 1;
                item.type = "shoes";
                item.size = 3;
                item.status = "unboxed";

                listOfItems.add(item);


                x++;

            }

        boxOrder(listOfItems);

        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File("/Users/jeffrep/Desktop/listOfItems.json"), listOfItems);


    } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

   /* public int getSize(String type) { // returns the size value of the item

        switch (type) {

            case "shoes": return 3;

            case "clothes": return 2;

            case "bags": return 3;

            case "housewares": return 5;

            case "beauty": return 2;

            case "accessories": return 1;

            default: return 0;
        }

    }   */

    class BoxCompare implements Comparator<Item> {
        public int compare(Item one, Item two) {

            if (one.getBoxNumber() < two.getBoxNumber())
                return -1;
            else return 1;

        }

    }

    public List<Item> boxOrder (List<Item> listOfItems) {

        int boxSize = 9;

        while (!isComplete(listOfItems)) {  //loops until every item is boxed

            for (int x = 0; x < listOfItems.size(); x++) {

                boolean full = isFull(boxSize, listOfItems.get(x).size);

                if (full) { //if full, increments the boxID, resets boxSize variable, assigns ID to ordered item, subtracts boxSize, and advises that it has been boxed

                    boxID++;
                    boxSize = 9;
                    listOfItems.get(x).boxNumber = boxID;
                    boxSize -= listOfItems.get(x).size;
                    listOfItems.get(x).status = "full";
                    continue;
                }
                else if (!full) { //subtracts from boxSize, assigns ID to ordered item, and updates the status
                    boxSize -= listOfItems.get(x).size;
                    listOfItems.get(x).boxNumber = boxID;
                    listOfItems.get(x).status = "not full";
                    continue;
                }

            }

        }

        return listOfItems;
    }

    public boolean isFull (int boxSize, int typeSize) {

        //System.out.println("boxSize = " + boxSize + " typeSize = " + typeSize + " box ID is: " + boxID);
        //System.out.println("true/false: " + (boxSize < typeSize || boxSize == 0));

        if (boxSize < typeSize || boxSize == 0) { //if the box doesn't have any space left or doesn't have enough space, returns true

            return true;
        }

        return false;
    }

    public boolean isComplete (List<Item> listOfItems) {

        for (int x = 0; x < listOfItems.size(); x++ ) {

            if (listOfItems.get(x).status.equals("unboxed")) {

                return false;

            }

        }

        return true;

    }

    public String getRandomType() { //randomly assigns a type of product that we sell

        Random r = new Random();
        int typeNumber;

        typeNumber = 1 + r.nextInt(6);

        switch(typeNumber)
        {
            case 1: return "shoes";

            case 2: return "clothes";

            case 3: return "bags";

            case 4: return "housewares";

            case 5: return "beauty";

            case 6: return "accessories";

            default:

                System.out.println(typeNumber);
                return "error";

        }
    }


    public List<Item> getAllItems() {
        return listOfItems;
    }



}

