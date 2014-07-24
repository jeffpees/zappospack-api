package test.json;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/test")
public class ApiController {

    @RequestMapping(method = RequestMethod.POST, value = "order")
    @ResponseBody
    public List<Item> order(@RequestBody final Request request) {

        List<Item> listOfItems = new ArrayList<Item>();

        int acc = request.getAccessories();
        int bags = request.getBags();
        int beaut = request.getBeauty();
        int cloth = request.getClothes();
        int house = request.getHousewares();
        int shoes = request.getShoes();

        listOfItems = createOrder(acc, bags, beaut, cloth, house, shoes);

        return listOfItems;

    }


    public List<Item> createOrder (int accessories, int bags, int beauty, int clothes, int housewares, int shoes) {

        List<Item> listOfItems = new ArrayList<Item>();
        ArrayList<Integer> types = new ArrayList();
        ArrayList<Integer> noOfItems = new ArrayList();

        int noOfTypes = 0;

        int itemNo = 1;

        if (accessories > 0) {
            types.add(1);
            noOfItems.add(accessories);
            noOfTypes = noOfTypes + 1;
        }
        if (bags > 0) {
            types.add(2);
            noOfItems.add(bags);
            noOfTypes = noOfTypes + 1;
        }
        if (beauty > 0) {
            types.add(3);
            noOfItems.add(beauty);
            noOfTypes = noOfTypes + 1;
        }
        if (clothes > 0) {
            types.add(4);
            noOfItems.add(clothes);
            noOfTypes = noOfTypes + 1;
        }
        if (housewares > 0) {
            types.add(5);
            noOfItems.add(housewares);
            noOfTypes = noOfTypes + 1;
        }
        if (shoes > 0) {
            types.add(6);
            noOfItems.add(shoes);
            noOfTypes = noOfTypes + 1;
        }


        for (int x = 0; x < noOfTypes; x++) {

            for (int y = 0; y < noOfItems.get(y); y++) {

            Item item = new Item();

            item.orderNumber = 1;
            item.itemNumber = itemNo;
            item.type = getType(types.get(x));
            item.size = getSize(item.type);
            item.status = "unboxed";

            listOfItems.add(item);

            itemNo++;

            }

        }

        boxOrder(listOfItems);

        BoxCompare boxCompare = new BoxCompare();
        Collections.sort(listOfItems, boxCompare);

        javaToJsonConverter(listOfItems);

        return listOfItems;

    }

    public void javaToJsonConverter (List<Item> listOfItems) {

        try {

            final ObjectMapper mapper = new ObjectMapper();

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("/Users/jeffrep/Desktop/listOfItems.json"), listOfItems);


        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    public int getSize(String type) { // returns the size value of the item

        switch (type) {

            case "shoes": return 3;

            case "clothes": return 2;

            case "bags": return 3;

            case "housewares": return 5;

            case "beauty": return 2;

            case "accessories": return 1;

            default: return 0;
        }

    }


    class BoxCompare implements Comparator<Item> {
        public int compare(Item one, Item two) {

            if (one.getBoxNumber() < two.getBoxNumber())
                return -1;
            else return 1;

        }

    }

    public List<Item> boxOrder (List<Item> listOfItems) {

        int boxSize = 9, boxID = 1;

        while (!isComplete(listOfItems)) {  //loops until every item is boxed

            for (int x = 0; x < listOfItems.size(); x++) {

                boolean full = isFull(boxSize, listOfItems.get(x).size);

                if (full) { //if full, increments the boxID, resets boxSize variable, assigns ID to ordered item, subtracts boxSize, and advises that it has been boxed

                    boxID++;
                    boxSize = 9;
                    listOfItems.get(x).boxNumber = boxID;
                    boxSize -= listOfItems.get(x).size;
                    listOfItems.get(x).status = "new box";
                    continue;
                }
                else if (!full) { //subtracts from boxSize, assigns ID to ordered item, and updates the status
                    boxSize -= listOfItems.get(x).size;
                    listOfItems.get(x).boxNumber = boxID;
                    listOfItems.get(x).status = "packing same box";
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

    public String getType(int typeNumber) { //assigns a type of product that we sell based on ID

        switch(typeNumber)
        {
            case 1: return "accessories";

            case 2: return "bags";

            case 3: return "beauty";

            case 4: return "clothes";

            case 5: return "housewares";

            case 6: return "shoes";

            default:

                System.out.println(typeNumber);
                return "error";

        }
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

}

