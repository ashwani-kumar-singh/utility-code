import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.SetUtils;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiffChecker {

  public static void main(String[] args) throws IOException {
    Object[] s1 =
        {"ar", "bg", "ca", "da", "de", "en", "el", "eu", "cz", "es", "fi", "ga", "fr", "fa", "gl",
            "hi", "hy", "hu", "it", "nl", "lv", "no", "pt", "ja", "id", "ro", "ru", "sv", "th",
            "tr"};
    Object[] s2 =
        {"ar", "bn", "bg", "my", "zh", "ca", "hr", "cs", "da", "nl", "en", "et", "fil", "fi", "fr",
            "de", "el", "gu", "he", "hi", "hu", "id", "it", "jv", "ja", "kk", "kn", "ko", "ms",
            "ml", "mr", "mn", "ne", "no", "fa", "pl", "pt", "pa", "ps", "ro", "ru", "sk", "so",
            "es", "sv", "ta", "te", "th", "tr", "uk", "ur", "vi"};

    Set<Object> set1 = Stream.of(s1).collect(Collectors.toSet());

    Set<Object> set2 = Stream.of(s2).collect(Collectors.toSet());

    SetUtils.SetView<Object> inSet1ButNotInSet2 = SetUtils.difference(set1, set2);
    System.out.println(inSet1ButNotInSet2);

    SetUtils.SetView<Object> inSet2ButNotInSet1 = SetUtils.difference(set2, set1);
    System.out.println(inSet2ButNotInSet1);

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(
        "[{\n" + "\t\"language\": \"Arabic\",\n" + "\t\"languageCode\": \"ar\"\n" + "}, {\n"
            + "\t\"language\": \"Bengali\",\n" + "\t\"languageCode\": \"bn\"\n" + "}, {\n"
            + "\t\"language\": \"Bulgarian\",\n" + "\t\"languageCode\": \"bg\"\n" + "}, {\n"
            + "\t\"language\": \"Burmese\",\n" + "\t\"languageCode\": \"my\"\n" + "}, {\n"
            + "\t\"language\": \"Chinese\",\n" + "\t\"languageCode\": \"zh\"\n" + "}, {\n"
            + "\t\"language\": \"Catalan\",\n" + "\t\"languageCode\": \"ca\"\n" + "}, {\n"
            + "\t\"language\": \"Croatian\",\n" + "\t\"languageCode\": \"hr\"\n" + "}, {\n"
            + "\t\"language\": \"Czech\",\n" + "\t\"languageCode\": \"cs\"\n" + "}, {\n"
            + "\t\"language\": \"Danish\",\n" + "\t\"languageCode\": \"da\"\n" + "}, {\n"
            + "\t\"language\": \"Dutch\",\n" + "\t\"languageCode\": \"nl\"\n" + "}, {\n"
            + "\t\"language\": \"English\",\n" + "\t\"languageCode\": \"en\"\n" + "}, {\n"
            + "\t\"language\": \"Estonian\",\n" + "\t\"languageCode\": \"et\"\n" + "}, {\n"
            + "\t\"language\": \"Filipino\",\n" + "\t\"languageCode\": \"fil\"\n" + "}, {\n"
            + "\t\"language\": \"Finnish\",\n" + "\t\"languageCode\": \"fi\"\n" + "}, {\n"
            + "\t\"language\": \"French\",\n" + "\t\"languageCode\": \"fr\"\n" + "}, {\n"
            + "\t\"language\": \"German\",\n" + "\t\"languageCode\": \"de\"\n" + "}, {\n"
            + "\t\"language\": \"Greek\",\n" + "\t\"languageCode\": \"el\"\n" + "}, {\n"
            + "\t\"language\": \"Gujarati\",\n" + "\t\"languageCode\": \"gu\"\n" + "}, {\n"
            + "\t\"language\": \"Hebrew\",\n" + "\t\"languageCode\": \"he\"\n" + "}, {\n"
            + "\t\"language\": \"Hindi\",\n" + "\t\"languageCode\": \"hi\"\n" + "}, {\n"
            + "\t\"language\": \"Hungarian\",\n" + "\t\"languageCode\": \"hu\"\n" + "}, {\n"
            + "\t\"language\": \"Indonesian\",\n" + "\t\"languageCode\": \"id\"\n" + "}, {\n"
            + "\t\"language\": \"Italian\",\n" + "\t\"languageCode\": \"it\"\n" + "}, {\n"
            + "\t\"language\": \"Javanese\",\n" + "\t\"languageCode\": \"jv\"\n" + "}, {\n"
            + "\t\"language\": \"Japanese\",\n" + "\t\"languageCode\": \"ja\"\n" + "}, {\n"
            + "\t\"language\": \"Kazakh\",\n" + "\t\"languageCode\": \"kk\"\n" + "}, {\n"
            + "\t\"language\": \"Kannada\",\n" + "\t\"languageCode\": \"kn\"\n" + "}, {\n"
            + "\t\"language\": \"Korean\",\n" + "\t\"languageCode\": \"ko\"\n" + "}, {\n"
            + "\t\"language\": \"Malay\",\n" + "\t\"languageCode\": \"ms\"\n" + "}, {\n"
            + "\t\"language\": \"Malayalam\",\n" + "\t\"languageCode\": \"ml\"\n" + "}, {\n"
            + "\t\"language\": \"Marathi\",\n" + "\t\"languageCode\": \"mr\"\n" + "}, {\n"
            + "\t\"language\": \"Mongolian\",\n" + "\t\"languageCode\": \"mn\"\n" + "}, {\n"
            + "\t\"language\": \"Nepali\",\n" + "\t\"languageCode\": \"ne\"\n" + "}, {\n"
            + "\t\"language\": \"Norwegian\",\n" + "\t\"languageCode\": \"no\"\n" + "}, {\n"
            + "\t\"language\": \"Persian\",\n" + "\t\"languageCode\": \"fa\"\n" + "}, {\n"
            + "\t\"language\": \"Polish\",\n" + "\t\"languageCode\": \"pl\"\n" + "}, {\n"
            + "\t\"language\": \"Portuguese\",\n" + "\t\"languageCode\": \"pt\"\n" + "}, {\n"
            + "\t\"language\": \"Punjabi\",\n" + "\t\"languageCode\": \"pa\"\n" + "}, {\n"
            + "\t\"language\": \"Pushto\",\n" + "\t\"languageCode\": \"ps\"\n" + "}, {\n"
            + "\t\"language\": \"Romanian\",\n" + "\t\"languageCode\": \"ro\"\n" + "}, {\n"
            + "\t\"language\": \"Russian\",\n" + "\t\"languageCode\": \"ru\"\n" + "}, {\n"
            + "\t\"language\": \"Slovak\",\n" + "\t\"languageCode\": \"sk\"\n" + "}, {\n"
            + "\t\"language\": \"Somali\",\n" + "\t\"languageCode\": \"so\"\n" + "}, {\n"
            + "\t\"language\": \"Spanish\",\n" + "\t\"languageCode\": \"es\"\n" + "}, {\n"
            + "\t\"language\": \"Swedish\",\n" + "\t\"languageCode\": \"sv\"\n" + "}, {\n"
            + "\t\"language\": \"Tamil\",\n" + "\t\"languageCode\": \"ta\"\n" + "}, {\n"
            + "\t\"language\": \"Telugu\",\n" + "\t\"languageCode\": \"te\"\n" + "}, {\n"
            + "\t\"language\": \"Thai\",\n" + "\t\"languageCode\": \"th\"\n" + "}, {\n"
            + "\t\"language\": \"Turkish\",\n" + "\t\"languageCode\": \"tr\"\n" + "}, {\n"
            + "\t\"language\": \"Ukrainian\",\n" + "\t\"languageCode\": \"uk\"\n" + "}, {\n"
            + "\t\"language\": \"Urdu\",\n" + "\t\"languageCode\": \"ur\"\n" + "}, {\n"
            + "\t\"language\": \"Vietnamese\",\n" + "\t\"languageCode\": \"vi\"\n" + "}]");

    System.out.println("Ye sab add karne hai (Not in Base Synonyms but there in UI)");
    jsonNode.elements().forEachRemaining(el -> {
      if (inSet2ButNotInSet1.contains(el.get("languageCode").asText())) {
        System.out
            .println(el.get("language").asText() + "(" + el.get("languageCode").asText() + ")");
      }
    });

  }
}
