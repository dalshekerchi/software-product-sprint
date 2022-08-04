package data;

/** An item on the message list. */
public final class Message {

  private final long id;
  private final String message;

  public Message(long id, String message) {
    this.id = id;
    this.message = message;
  }
}