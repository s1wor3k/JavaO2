package pl.jo2.model;

public class Presence {

  private PresenceType type;
  private String status;

  public Presence() {
  }

  public Presence(PresenceType type, String status) {
    this.type = type;
    this.status = status;
  }

  public PresenceType getType() {
    return type;
  }

  public void setType(PresenceType type) {
    this.type = type;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Presence{" +
      "type=" + type +
      ", icons.presence='" + status + '\'' +
      '}';
  }
}
