package cca;

public class Announcement {
    private String Title;
    private String Service;
    private String Description;
    private String Location;
    private String Payment;

    public Announcement(String title, String service, String description, String location, String payment) {
        Title = title;
        Service = service;
        Description = description;
        Location = location;
        Payment = payment;
    }

    public String getTitle() {
        return Title;
    }

    public String getService() {
        return Service;
    }

    public String getDescription() {
        return Description;
    }

    public String getLocation() {
        return Location;
    }

    public String getPayment() {
        return Payment;
    }

    public String toString() {
        return Title + "\nService: " + Service + "\nDescription: " + Description + "\nLocation: " + Location + "\nPayment method: " + Payment;
    }
}
