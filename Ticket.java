import java.time.LocalDateTime;

public class Ticket {

    private final int ticketID;
    private String subject;
    private String description;
    private String priority;
    private String status;
    private final LocalDateTime createdAt;

    private static int nextID = 1001;

    // Constructor for creating new tickets
    public Ticket(String subject, String description,
                  String priority, String status) {

        this.ticketID = nextID++;
        this.subject = subject;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    // Constructor for loading existing tickets from CSV
    public Ticket(int ticketID, String subject, String description,
                  String priority, String status, LocalDateTime createdAt) {

        this.ticketID = ticketID;
        this.subject = subject;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;

        // Ensure future tickets receive unique IDs
        if (ticketID >= nextID) {
            nextID = ticketID + 1;
        }
    }

    public int getTicketID() {
        return ticketID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}