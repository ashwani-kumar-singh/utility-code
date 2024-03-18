package email;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class EmailClient {

  private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private static final String TOKENS_DIRECTORY_PATH = "tokens";

  /**
   * Global instance of the scopes required by this quickstart.
   * If modifying these scopes, delete your previously saved tokens/ folder.
   */
  private static final List<String> SCOPES =
      Arrays.asList(GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_SEND, GmailScopes.GMAIL_READONLY);
  private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
  public static final String EMAIL_CONTENT_PATH = "/email_text_5.html";
  public static final String MESSAGE_ID =
      "<CALanXOX3Qu=xU+uEfWScK5BCx2FcJ9t=3kTynGLAiJymYZ5tHg@mail.gmail.com>";

  /**
   * Creates an authorized Credential object.
   *
   * @param HTTP_TRANSPORT The network HTTP Transport.
   * @return An authorized Credential object.
   * @throws IOException If the credentials.json file cannot be found.
   */
  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {
    // Load client secrets.
    InputStream in = EmailClient.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow =
        new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline").build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
  }

  /**
   * Create a MimeMessage using the parameters provided.
   *
   * @param to       email address of the receiver
   * @param from     email address of the sender, the mailbox account
   * @param subject  subject of the email
   * @param bodyText body text of the email
   * @return the MimeMessage to be used to send email
   */
  public static MimeMessage createEmail(String to, String from, String subject, String bodyText)
      throws MessagingException {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    MimeMessage email = new MimeMessage(session);

    email.setFrom(new InternetAddress(from));
    email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
    email.setSubject(subject);
    email.setContent(bodyText, "text/html");
    email.setHeader("In-Reply-To", MESSAGE_ID);
    email.setHeader("References", MESSAGE_ID);
    return email;
  }

  /**
   * Create a message from an email.
   *
   * @param emailContent Email to be set to raw of message
   * @return a message containing a base64url encoded email
   * @throws IOException
   * @throws MessagingException
   */
  public static Message createMessageWithEmail(MimeMessage emailContent, String threadId)
      throws MessagingException, IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    emailContent.writeTo(buffer);
    byte[] bytes = buffer.toByteArray();
    String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
    Message message = new Message();
    message.setRaw(encodedEmail);
    message.setThreadId(threadId);
    return message;
  }

  /**
   * Create a MimeMessage using the parameters provided.
   *
   * @param to       Email address of the receiver.
   * @param from     Email address of the sender, the mailbox account.
   * @param subject  Subject of the email.
   * @param bodyText Body text of the email.
   * @param file     Path to the file to be attached.
   * @return MimeMessage to be used to send email.
   * @throws MessagingException
   */
  public static MimeMessage createEmailWithAttachment(String to, String from, String subject,
      String bodyText, File file) throws MessagingException, IOException {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    MimeMessage email = new MimeMessage(session);

    email.setFrom(new InternetAddress(from));
    email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
    email.setSubject(subject);

    MimeBodyPart mimeBodyPart = new MimeBodyPart();
    mimeBodyPart.setContent(bodyText, "text/plain");

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(mimeBodyPart);

    mimeBodyPart = new MimeBodyPart();
    DataSource source = new FileDataSource(file);

    mimeBodyPart.setDataHandler(new DataHandler(source));
    mimeBodyPart.setFileName(file.getName());

    multipart.addBodyPart(mimeBodyPart);
    email.setContent(multipart);

    return email;
  }

  /**
   * Send an email from the user's mailbox to its recipient.
   *
   * @param service      Authorized Gmail API instance.
   * @param userId       User's email address. The special value "me"
   *                     can be used to indicate the authenticated user.
   * @param emailContent Email to be sent.
   * @return The sent message
   * @throws MessagingException
   * @throws IOException
   */
  public static Message sendMessage(Gmail service, String userId, String threadId,
      MimeMessage emailContent) throws MessagingException, IOException {
    Message message = createMessageWithEmail(emailContent, threadId);
    message = service.users().messages().send(userId, message).execute();

    System.out.println("Message id: " + message.getId());
    System.out.println(message.toPrettyString());
    return message;
  }

  public static void main(String... args)
      throws IOException, GeneralSecurityException, MessagingException {
    // Build a new authorized API client service.
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME).build();

    // Print the labels in the user's account.
    String user = "me";
    ListLabelsResponse listResponse = service.users().labels().list(user).execute();
    List<Label> labels = listResponse.getLabels();
    if (labels.isEmpty()) {
      System.out.println("No labels found.");
    } else {
      System.out.println("Labels:");
      for (Label label : labels) {
        System.out.printf("- %s\n", label.getName());
      }
    }

    Message initialMessage = getMessage(service, MESSAGE_ID);
    System.out.println(initialMessage);

    Message message = sendMessage(service, "me", initialMessage.getThreadId(),
        createEmail("kunal.sahu@coviam.com", "ksahu750@gmail.com", "Re: Test", getEmailContent()));
    System.out.println(message);

  }

  private static String getEmailContent() throws FileNotFoundException {
    //    return "response";
    InputStream in = EmailClient.class.getResourceAsStream(EMAIL_CONTENT_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + EMAIL_CONTENT_PATH);
    }
    return readFromInputStream(in);
  }

  private static String readFromInputStream(InputStream inputStream) {
    return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
        .collect(Collectors.joining("\n"));
  }

  private static Message getMessage(Gmail service, String messageId) throws IOException {
    Message message = null;
    ListMessagesResponse listMessagesResponse =
        service.users().messages().list("me").setQ("rfc822msgid:" + messageId).execute();
    if (listMessagesResponse.getResultSizeEstimate() > 0) {
      message =
          service.users().messages().get("me", listMessagesResponse.getMessages().get(0).getId())
              .execute();
    }
    return message;
  }

}
