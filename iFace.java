import java.util.ArrayList;

import Exceptions.UserNotFoundException;
import Feed.News;
import User.User;

abstract class iFace {
    static ArrayList<News> Feed = new ArrayList<News>();

    public static void addNews(int id, User client, String content, boolean privacy) {
        News item = new News();
        item.id = id;
        item.user = client.getUsername();
        item.content = content;
        item.privacy = privacy;
        Feed.add(item);
    }

    public static boolean isFriends(String user, User client) {
        // Show friends of current user
        if (client.getUsername().equals(user)) {
            return true;
        }

        for (int i = 0; i < client.friends.length; i++) {
            if (client.friends[i] != null) {
                System.out.println(client.friends[i].getUsername());
                if (client.friends[i].getUsername().equals(user)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void listNews(User client) {
        int messages_found = 0;

        for (int i = 0; i < Feed.size(); i++) {

            if (((Feed.get(i).privacy == true) && (isFriends(Feed.get(i).user, client)))
                    || (Feed.get(i).privacy == false)) {
                messages_found++;
                System.out.println();
                System.out.println('"' + Feed.get(i).content + '"');
                System.out.println("Posted by: " + Feed.get(i).user);
                System.out.println();
            }
        }

        if (messages_found < 1) {
            System.out.println("No messages were found.");
        }
    }

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
                user[i] = new User.UserBuilder(email, password).id(i).username(username).build();
                System.out.println("New user registered with the informations: ");
                System.out.println("ID: " + user[i].getId());
                System.out.println("EMAIL: " + user[i].getEmail());
                System.out.println("USERNAME: " + user[i].getUsername());
                return 0;
            }
        }

        System.out.println("The system was not able to complete your request.");
        return -1;
    }

    public static void deleteUser(User[] user, String email, String password) {
        try {
            for (int i = 0; i < 1000; i++) {
                if (user[i] != null) {
                    if ((email.equals(user[i].getEmail())) && (password.equals(user[i].getPassword()))) {
                        user[i] = null;
                        return;
                    }
                }
            }

            throw new UserNotFoundException("User was not found.");
        } catch (UserNotFoundException e) {
            System.out.println(e);
        }

    }
}
