import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.json.simple.JSONObject;

import java.io.IOException;

public class ObjectCopyTest {


  public static final ObjectMapper MAPPER_NO_FAIL_ON_UNKNOWN = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);


  private static String getClassNameForType(String type) throws ClassNotFoundException {
    return Class.forName(String.format("%sClass", type)).getSimpleName();
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("type", getClassNameForType("Child2"));
    jsonObject.put("p", "pp");
    jsonObject.put("p1", "pp1");
    jsonObject.put("p2", "pp1");
    //    BaseClass nested = ChildClass2.builder().p2("np2").build();
    //    nested.setP("np");

    JSONObject nested = new JSONObject();
    nested.put("type", getClassNameForType("Child1"));
    nested.put("p", "np");
    nested.put("p1", "np1");
    jsonObject.put("nested", nested);

    /*BaseClass baseClass = new ChildClass1();
    BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(baseClass);
    PropertyValues propertyValues = new MutablePropertyValues(jsonObject);
    beanWrapper.setPropertyValues(propertyValues, true);*/

    BaseClass baseClass =
        MAPPER_NO_FAIL_ON_UNKNOWN.readValue(jsonObject.toJSONString(), BaseClass.class);

    System.out.println(baseClass);
  }

}


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = Child1Class.class),
                  @JsonSubTypes.Type(value = Child2Class.class)})
@Getter
@Setter
@ToString
abstract class BaseClass {

  private String p;

  private BaseClass nested;

}


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
class Child1Class extends BaseClass {

  private String p1;

}


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
class Child2Class extends BaseClass {

  private String p2;

}