import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

public class CyclicEqualsTest {

  public static void main(String[] args) throws JsonProcessingException {
   /* Child child1 = Child.builder().id(1L).build();
    Child child2 = Child.builder().id(2L).build();
    Parent parent1 = Parent.builder().id(1L).child(Arrays.asList(child1, child2)).build();
    child1.setParent(parent1);
    child2.setParent(parent1);

    Child child3 = Child.builder().id(1L).build();
    Child child4 = Child.builder().id(2L).build();
    Parent parent2 = Parent.builder().id(1L).child(Arrays.asList(child3, child4)).build();
    child3.setParent(parent2);
    child4.setParent(parent2);

    System.out.println(new ObjectMapper().writeValueAsString(parent1));
    //    System.out.println(parent1.equals(parent2));*/
  }


}


@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@EqualsAndHashCode
class Parent {

  private Long id;

  @JsonIgnoreProperties(value = {"parent"})
  private List<Child> child;

}


@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@EqualsAndHashCode
class Child {

  private Long id;

  private Parent parent;

}
