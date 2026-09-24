import java.io.*;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<JobApplication> applications = new ArrayList<>();

    static final String FILE_NAME = "applications.dat";

    enum Status {
        APPLIED,
        SCREENING,
        INTERVIEW,
        OFFER,
        REJECTED
    }

    static class JobApplication implements Serializable {
        private static final long serialVersionUID = 1L;

        int id;
        String company;
        String role;
        String location;
        Status status;
        String interviewDate;
        String notes;

        JobApplication(int id, String company, String role,
                       String location, Status status,
                       String interviewDate, String notes) {

            this.id = id;
            this.company = company;
            this.role = role;
            this.location = location;
            this.status = status;
            this.interviewDate = interviewDate;
            this.notes = notes;
        }
    }

    public static void main(String[] args) {

        loadApplications();

        while (true) {
            showDashboard();
            showMenu();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addApplication();
                    break;

                case "2":
                    viewApplications();
                    break;

                case "3":
                    searchApplications();
                    break;

                case "4":
                    updateStatus();
                    break;

                case "5":
                    deleteApplication();
                    break;

                case "6":
                    showStatistics();
                    break;

                case "7":
                    saveApplications();
                    System.out.println("\nThank you for using CareerTrack!");
                    return;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    static void showDashboard() {

        System.out.println("\n==============================================");
        System.out.println("              CAREERTRACK");
        System.out.println("          JOB APPLICATION TRACKER");
        System.out.println("==============================================");

        System.out.println("Total Applications : " + applications.size());

        int interviews = 0;
        int offers = 0;

        for (JobApplication app : applications) {

            if (app.status == Status.INTERVIEW) {
                interviews++;
            }

            if (app.status == Status.OFFER) {
                offers++;
            }
        }

        System.out.println("Interviews         : " + interviews);
        System.out.println("Offers             : " + offers);
        System.out.println("==============================================");
    }

    static void showMenu() {

        System.out.println("\n1. Add Application");
        System.out.println("2. View Applications");
        System.out.println("3. Search Applications");
        System.out.println("4. Update Status");
        System.out.println("5. Delete Application");
        System.out.println("6. View Statistics");
        System.out.println("7. Exit");

        System.out.print("\nEnter your choice: ");
    }

    static void addApplication() {

        System.out.println("\n----------- ADD APPLICATION -----------");

        System.out.print("Company: ");
        String company = scanner.nextLine();

        System.out.print("Job Role: ");
        String role = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.println("\nStatus:");
        System.out.println("1. Applied");
        System.out.println("2. Screening");
        System.out.println("3. Interview");
        System.out.println("4. Offer");
        System.out.println("5. Rejected");

        System.out.print("Choose status: ");
        int statusChoice = Integer.parseInt(scanner.nextLine());

        Status status = getStatus(statusChoice);

        System.out.print("Interview Date (DD-MM-YYYY or N/A): ");
        String interviewDate = scanner.nextLine();

        System.out.print("Notes: ");
        String notes = scanner.nextLine();

        int id = getNextId();

        JobApplication application =
                new JobApplication(
                        id,
                        company,
                        role,
                        location,
                        status,
                        interviewDate,
                        notes
                );

        applications.add(application);

        saveApplications();

        System.out.println("\nApplication added successfully!");
        System.out.println("Application ID: " + id);
    }

    static Status getStatus(int choice) {

        switch (choice) {

            case 1:
                return Status.APPLIED;

            case 2:
                return Status.SCREENING;

            case 3:
                return Status.INTERVIEW;

            case 4:
                return Status.OFFER;

            case 5:
                return Status.REJECTED;

            default:
                return Status.APPLIED;
        }
    }

    static void viewApplications() {

        System.out.println("\n----------- YOUR APPLICATIONS -----------");

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        for (JobApplication app : applications) {
            displayApplication(app);
        }
    }

    static void displayApplication(JobApplication app) {

        System.out.println("------------------------------------------");

        System.out.println("ID              : " + app.id);
        System.out.println("Company         : " + app.company);
        System.out.println("Role            : " + app.role);
        System.out.println("Location        : " + app.location);
        System.out.println("Status          : " + app.status);
        System.out.println("Interview Date  : " + app.interviewDate);
        System.out.println("Notes           : " + app.notes);

        System.out.println("------------------------------------------");
    }

    static void searchApplications() {

        System.out.println("\n----------- SEARCH APPLICATIONS -----------");

        System.out.print("Enter company or role: ");

        String search = scanner.nextLine().toLowerCase();

        boolean found = false;

        for (JobApplication app : applications) {

            if (app.company.toLowerCase().contains(search)
                    || app.role.toLowerCase().contains(search)) {

                displayApplication(app);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching applications found.");
        }
    }

    static void updateStatus() {

        System.out.println("\n----------- UPDATE STATUS -----------");

        System.out.print("Enter Application ID: ");

        int id = Integer.parseInt(scanner.nextLine());

        JobApplication application = findById(id);

        if (application == null) {
            System.out.println("Application not found.");
            return;
        }

        System.out.println("\nCurrent Status: " + application.status);

        System.out.println("\n1. Applied");
        System.out.println("2. Screening");
        System.out.println("3. Interview");
        System.out.println("4. Offer");
        System.out.println("5. Rejected");

        System.out.print("New status: ");

        int choice = Integer.parseInt(scanner.nextLine());

        application.status = getStatus(choice);

        saveApplications();

        System.out.println("Status updated successfully!");
    }

    static void deleteApplication() {

        System.out.println("\n----------- DELETE APPLICATION -----------");

        System.out.print("Enter Application ID: ");

        int id = Integer.parseInt(scanner.nextLine());

        JobApplication application = findById(id);

        if (application == null) {
            System.out.println("Application not found.");
            return;
        }

        applications.remove(application);

        saveApplications();

        System.out.println("Application deleted successfully.");
    }

    static JobApplication findById(int id) {

        for (JobApplication app : applications) {

            if (app.id == id) {
                return app;
            }
        }

        return null;
    }

    static int getNextId() {

        int maxId = 0;

        for (JobApplication app : applications) {

            if (app.id > maxId) {
                maxId = app.id;
            }
        }

        return maxId + 1;
    }

    static void showStatistics() {

        System.out.println("\n----------- APPLICATION STATISTICS -----------");

        int applied = 0;
        int screening = 0;
        int interviews = 0;
        int offers = 0;
        int rejected = 0;

        for (JobApplication app : applications) {

            switch (app.status) {

                case APPLIED:
                    applied++;
                    break;

                case SCREENING:
                    screening++;
                    break;

                case INTERVIEW:
                    interviews++;
                    break;

                case OFFER:
                    offers++;
                    break;

                case REJECTED:
                    rejected++;
                    break;
            }
        }

        System.out.println("Applied       : " + applied);
        System.out.println("Screening     : " + screening);
        System.out.println("Interviews    : " + interviews);
        System.out.println("Offers        : " + offers);
        System.out.println("Rejected      : " + rejected);

        System.out.println("-----------------------------------------------");

        if (applications.size() > 0) {

            double interviewRate =
                    (interviews * 100.0) / applications.size();

            System.out.printf(
                    "Interview Rate: %.2f%%\n",
                    interviewRate
            );
        }
    }

    static void saveApplications() {

        try {

            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME)
                    );

            output.writeObject(applications);

            output.close();

        } catch (IOException e) {

            System.out.println("Could not save applications.");
        }
    }

    @SuppressWarnings("unchecked")
    static void loadApplications() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {

            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME)
                    );

            applications =
                    (ArrayList<JobApplication>) input.readObject();

            input.close();

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Could not load saved applications.");
        }
    }
}