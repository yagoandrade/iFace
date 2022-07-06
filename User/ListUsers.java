package User;

// Singleton Method
public class ListUsers {
    public static void listUsers(User[] user) {
        int null_users = 0;

        for (int i = 0; i < 1000; i++) {
            if (user[i] != null) {
                System.out.println("\nUser ID: " + i);
                System.out.println("User EMAIL: " + user[i].getEmail());
                System.out.println("User USERNAME: " + user[i].getUsername());
            } else {
                null_users++;
            }
        }

        if (null_users == 1000) {
            System.out.println("There are no registered users.");
        }
    }
}
