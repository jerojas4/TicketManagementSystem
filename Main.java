import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        TicketManager manager = new TicketManager();

        boolean running = true;

        while (running) {

            System.out.println("===== IT HELP DESK =====");
            System.out.println("1. Create Ticket");
            System.out.println("2. View All Tickets");
            System.out.println("3. Search Ticket by ID");
            System.out.println("4. Update Ticket Status");
            System.out.println("5. Update Ticket Priority");
            System.out.println("6. Delete Ticket");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scnr.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }
            switch (choice) {
                // CREATE TICKET
                case 1: {
                    System.out.println("===== CREATE TICKET =====");

                    String subject;

                    while (true) {
                        System.out.print("Enter subject: ");
                        subject = scnr.nextLine().trim();

                        if (!subject.isEmpty()) {
                            break;
                        }

                        System.out.println("Subject cannot be empty!");
                    }
                    String description;
                    while (true) {
                        System.out.print("Enter description: ");
                        description = scnr.nextLine().trim();

                        if (!description.isEmpty()) {
                            break;
                        }

                        System.out.println("Description cannot be empty!");
                    }

                    String priority;

                    while (true) {
                        System.out.print("Enter priority (LOW/MEDIUM/HIGH): ");
                        priority = scnr.nextLine().trim().toUpperCase();

                        if (priority.equals("LOW") ||
                            priority.equals("MEDIUM") ||
                            priority.equals("HIGH")) {
                            break;
                        }

                        System.out.println("Invalid priority!");
                    }

                    Ticket ticket = new Ticket(
                        subject,
                        description,
                        priority,
                        "OPEN"
                    );

                    if (manager.addTicket(ticket)) {
                        System.out.println("\nTicket created successfully!");
                        System.out.println("Ticket ID: " + ticket.getTicketID());
                    } else {
                        System.out.println("Failed to create ticket.");
                    }

                    break;
                }

                // VIEW ALL TICKETS
                case 2: {
                    System.out.println("\n===== ALL TICKETS =====");

                    if (manager.getAllTickets().isEmpty()) {
                        System.out.println("No tickets found.");
                        break;
                    }

                    for (Ticket ticket : manager.getAllTickets()) {
                        System.out.println("----------------------------");
                        System.out.println("Ticket ID: " + ticket.getTicketID());
                        System.out.println("Subject: " + ticket.getSubject());
                        System.out.println("Description: " + ticket.getDescription());
                        System.out.println("Priority: " + ticket.getPriority());
                        System.out.println("Status: " + ticket.getStatus());
                        System.out.println("Created: " + ticket.getCreatedAt());
                    }

                    break;
                }

                // SEARCH TICKET
                case 3: {
                    System.out.println("\n===== SEARCH TICKET =====");

                    System.out.print("Enter Ticket ID: ");

                    int ticketID;

                    try {
                        ticketID = Integer.parseInt(scnr.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Ticket ID!");
                        break;
                    }

                    Ticket ticket = manager.getTicketByID(ticketID);

                    if (ticket == null) {
                        System.out.println("Ticket not found.");
                    } else {
                        System.out.println("----------------------------");
                        System.out.println("Ticket ID: " + ticket.getTicketID());
                        System.out.println("Subject: " + ticket.getSubject());
                        System.out.println("Description: " + ticket.getDescription());
                        System.out.println("Priority: " + ticket.getPriority());
                        System.out.println("Status: " + ticket.getStatus());
                        System.out.println("Created: " + ticket.getCreatedAt());
                    }

                    break;
                }

                // UPDATE TICKET STATUS
                case 4: {
                    System.out.println("\n===== UPDATE STATUS =====");

                    System.out.print("Enter Ticket ID: ");

                    int ticketID;

                    try {
                        ticketID = Integer.parseInt(scnr.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Ticket ID!");
                        break;
                    }

                    if (manager.getTicketByID(ticketID) == null) {
                        System.out.println("Ticket not found.");
                        break;
                    }

                    String status;

                    while (true) {
                        System.out.print("Enter status (OPEN/IN_PROGRESS/CLOSED): ");
                        status = scnr.nextLine().trim().toUpperCase();

                        if (status.equals("OPEN") ||
                            status.equals("IN_PROGRESS") ||
                            status.equals("CLOSED")) {
                            break;
                        }

                        System.out.println("Invalid status!");
                    }

                    if (manager.updateTicketStatus(ticketID, status)) {
                        System.out.println("Ticket status updated successfully!");
                    } else {
                        System.out.println("Failed to update ticket.");
                    }

                    break;
                }

                // UPDATE TICKET PRIORITY
                case 5: {
                    System.out.println("\n===== UPDATE PRIORITY =====");

                    System.out.print("Enter Ticket ID: ");

                    int ticketID;

                    try {
                        ticketID = Integer.parseInt(scnr.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Ticket ID!");
                        break;
                    }

                    if (manager.getTicketByID(ticketID) == null) {
                        System.out.println("Ticket not found.");
                        break;
                    }

                    String priority;

                    while (true) {
                        System.out.print("Enter priority (LOW/MEDIUM/HIGH): ");
                        priority = scnr.nextLine().trim().toUpperCase();

                        if (priority.equals("LOW") ||
                            priority.equals("MEDIUM") ||
                            priority.equals("HIGH")) {
                            break;
                        }

                        System.out.println("Invalid priority!");
                    }

                    if (manager.updateTicketPriority(ticketID, priority)) {
                        System.out.println("Ticket priority updated successfully!");
                    } else {
                        System.out.println("Failed to update priority.");
                    }

                    break;
                }

                // DELETE TICKET
                case 6: {
                    System.out.println("\n===== DELETE TICKET =====");

                    System.out.print("Enter Ticket ID: ");

                    int ticketID;

                    try {
                        ticketID = Integer.parseInt(scnr.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Ticket ID!");
                        break;
                    }

                    Ticket ticket = manager.getTicketByID(ticketID);

                    if (ticket == null) {
                        System.out.println("Ticket not found.");
                        break;
                    }

                    System.out.println("Ticket: " + ticket.getSubject());
                    System.out.print("Are you sure you want to delete? (YES/NO): ");

                    String confirmation = scnr.nextLine().trim();

                    if (confirmation.equalsIgnoreCase("YES")) {
                        if (manager.deleteTicket(ticketID)) {
                            System.out.println("Ticket deleted successfully!");
                        } else {
                            System.out.println("Failed to delete ticket.");
                        }
                    } else {
                        System.out.println("Deletion cancelled.");
                    }

                    break;
                }

                // EXIT
                case 7: {
                    System.out.println("Exiting Help Desk System...");
                    running = false;
                    break;
                }

                default:
                    System.out.println("Invalid choice! Please select 1-7.");
            }
        }

        scnr.close();
    }
}