import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MapperTest {

  public static void main(String[] args) throws JsonProcessingException {
    Pojo pojo = new Pojo("1", "2");
    ObjectMapper objectMapper = new ObjectMapper();
    System.out.println(objectMapper.writeValueAsString(pojo));

    ObjectNode objectNode = objectMapper.createObjectNode();
    ObjectNode kuchKuchNode = objectMapper.createObjectNode();
    //    TextNode textNode = objectNode.textNode("kuchKuchText");
    kuchKuchNode.put("aur kuch kuch", "textNode");
    objectNode.set("kuch kuch", kuchKuchNode);
    System.out.println(objectMapper.writeValueAsString(objectNode));
  }

}
