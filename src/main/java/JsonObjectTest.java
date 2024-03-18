import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.simple.JSONObject;

public class JsonObjectTest {
  public static void main(String[] args) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("A", null);
    System.out.println(jsonObject.toJSONString());
  }
}
