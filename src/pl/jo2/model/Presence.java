package pl.jo2.model;

public class Presence {

  private PresenceType myType;
  private String myStatus;

  public Presence(PresenceType type, String status) {
    this.myType = type;
    this.myStatus = status;
  }

  public PresenceType getType() {
    return myType;
  }

  public void setType(PresenceType type) {
    this.myType = type;
  }

  public String getStatus() {
    return myStatus;
  }

  public void setStatus(String status) {
    this.myStatus = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Presence presence = (Presence) o;

    if (myStatus != null ? !myStatus.equals(presence.myStatus) : presence.myStatus != null) return false;
    if (myType != presence.myType) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = myType != null ? myType.hashCode() : 0;
    result = 31 * result + (myStatus != null ? myStatus.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Presence{type=" + myType +
        ", status='" + myStatus + "'}";
  }
}
