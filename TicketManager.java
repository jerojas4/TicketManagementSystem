import java.util.ArrayList;

public class TicketManager {

    private final ArrayList<Ticket> tickets;

    public TicketManager() {
        this.tickets = CSVManager.loadTickets();
    }

    public boolean addTicket(Ticket ticket) {
        if (ticket == null || getTicketByID(ticket.getTicketID()) != null) {
            return false;
        }

        tickets.add(ticket);
        tickets.sort((a, b) ->
            Integer.compare(a.getTicketID(), b.getTicketID()));

        CSVManager.saveTickets(tickets);
        return true;
    }

    public ArrayList<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public Ticket getTicketByID(int ticketID) {
        int low = 0;
        int high = tickets.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midID = tickets.get(mid).getTicketID();

            if (midID == ticketID) {
                return tickets.get(mid);
            } else if (midID < ticketID) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null;
    }

    public boolean updateTicketStatus(int ticketID, String status) {
        Ticket ticket = getTicketByID(ticketID);

        if (ticket == null) {
            return false;
        }

        ticket.setStatus(status);
        CSVManager.saveTickets(tickets);

        return true;
    }

    public boolean updateTicketPriority(int ticketID, String priority) {
        Ticket ticket = getTicketByID(ticketID);

        if (ticket == null) {
            return false;
        }

        ticket.setPriority(priority);
        CSVManager.saveTickets(tickets);

        return true;
    }

    public boolean deleteTicket(int ticketID) {
        Ticket ticket = getTicketByID(ticketID);

        if (ticket == null) {
            return false;
        }

        tickets.remove(ticket);
        CSVManager.saveTickets(tickets);

        return true;
    }
}