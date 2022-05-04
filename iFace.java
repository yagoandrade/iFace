import User.User;

public class iFace {
    public static User handleLogin(String username, String password, User[] users) {
        for (int i = 0; i < 1000; i++) {
            if (users[i] != null) {
                if ((username.equals(users[i].getUsername())) && (password.equals(users[i].getPassword()))) {
                    return users[i];
                }
            }
        }

        return null;
    }

    public static int createUser(User[] user, String email, String password, String username) {
        for (int i = 0; i < 1000; i++) {
            if (user[i] == null) {
                user[i] = new User();
                user[i].setId(i);
                user[i].setEmail(email);
                user[i].setPassword(password);
                user[i].setUsername(username);
                System.out.println("New user registered with the informations: ");
                System.out.println("ID: " + user[i].getId());
                System.out.println("EMAIL: " + user[i].getEmail());
                System.out.println("PASSWORD: " + user[i].getPassword());
                System.out.println("USERNAME: " + user[i].getUsername());
                return 0;
            }
        }

        System.out.println("The system was not able to complete your request.");
        return -1;
    }

    public static void deleteUser(User[] user, String email, String password) {
        for (int i = 0; i < 1000; i++) {
            if (user[i] != null) {
                if ((email.equals(user[i].getEmail())) && (password.equals(user[i].getPassword()))) {
                    user[i] = null;
                    break;
                }
            }
        }
    }

    public static void listUsers(User[] user) {
        int null_users = 0;

        for (int i = 0; i < 1000; i++) {
            if (user[i] != null) {
                System.out.println("\nUser ID: " + i);
                System.out.println("User EMAIL: " + user[i].getEmail());
                System.out.println("User PASSWORD: " + user[i].getPassword());
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
