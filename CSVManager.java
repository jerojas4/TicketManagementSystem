import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CSVManager {

    private static final String FILE_NAME = "tickets.csv";

    // Save all tickets to CSV
    public static void saveTickets(ArrayList<Ticket> tickets) {

        try (PrintWriter writer = new PrintWriter(
                Files.newBufferedWriter(
                    Path.of(FILE_NAME), StandardCharsets.UTF_8))) {

            writer.println("ticketID,subject,description,priority,status,createdAt");

            for (Ticket ticket : tickets) {

                writer.println(
                    ticket.getTicketID() + "," +
                    escape(ticket.getSubject()) + "," +
                    escape(ticket.getDescription()) + "," +
                    escape(ticket.getPriority()) + "," +
                    escape(ticket.getStatus()) + "," +
                    ticket.getCreatedAt()
                );
            }

            if (writer.checkError()) {
                throw new IOException("Failed to write CSV data.");
            }

            System.out.println("Tickets saved successfully!");

        } catch (IOException e) {
            throw new IllegalStateException(
                "Error saving tickets: " + e.getMessage(), e
            );
        }
    }

    // Load tickets from CSV
    public static ArrayList<Ticket> loadTickets() {

        ArrayList<Ticket> tickets = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return tickets;
        }

        try (PushbackReader reader = new PushbackReader(
                Files.newBufferedReader(
                    Path.of(FILE_NAME), StandardCharsets.UTF_8), 1)) {

            ArrayList<String> fields = new ArrayList<>();
            StringBuilder value = new StringBuilder();

            boolean inQuotes = false;
            boolean firstRow = true;
            int character;

            while ((character = reader.read()) != -1) {

                char c = (char) character;

                if (c == '"') {
                    if (inQuotes) {
                        int next = reader.read();

                        if (next == '"') {
                            value.append('"');
                        } else {
                            inQuotes = false;

                            if (next != -1) {
                                reader.unread(next);
                            }
                        }
                    } else {
                        inQuotes = true;
                    }

                } else if (c == ',' && !inQuotes) {
                    fields.add(value.toString());
                    value.setLength(0);

                } else if ((c == '\n' || c == '\r') && !inQuotes) {

                    if (c == '\r') {
                        int next = reader.read();

                        if (next != '\n' && next != -1) {
                            reader.unread(next);
                        }
                    }

                    fields.add(value.toString());
                    value.setLength(0);

                    if (firstRow) {
                        firstRow = false;
                    } else {
                        addLoadedTicket(tickets, fields);
                    }

                    fields.clear();

                } else {
                    value.append(c);
                }
            }

            if (inQuotes) {
                throw new IOException("Unclosed quotation mark in CSV.");
            }

            if (value.length() > 0 || !fields.isEmpty()) {
                fields.add(value.toString());

                if (!firstRow) {
                    addLoadedTicket(tickets, fields);
                }
            }

            tickets.sort((a, b) ->
                Integer.compare(a.getTicketID(), b.getTicketID()));

            System.out.println("Tickets loaded successfully!");

        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalStateException(
                "Error loading tickets: " + e.getMessage(), e
            );
        }

        return tickets;
    }

    // Convert CSV fields into Ticket objects
    private static void addLoadedTicket(
            ArrayList<Ticket> tickets, ArrayList<String> fields) {

        if (fields.size() != 6) {
            throw new IllegalArgumentException("Invalid CSV ticket row.");
        }

        Ticket ticket = new Ticket(
            fields.get(1),
            fields.get(2),
            fields.get(3),
            fields.get(4)
        );

        tickets.add(ticket);
    }

    // Handle special characters in CSV
    private static String escape(String value) {

        if (value.contains(",") ||
            value.contains("\"") ||
            value.contains("\n") ||
            value.contains("\r")) {

            return "\"" + value.replace("\"", "\"\"") + "\"";
        }

        return value;
    }
}