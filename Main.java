import java.util.Scanner;
import User.User;
import java.util.ArrayList;
import Community.Community;

public class Main extends iFace {
    public static void main(String[] args) {
        User[] users = new User[1000];
        User current_user = null;
        ArrayList<Community> communities = new ArrayList<Community>();

        Scanner sc = new Scanner(System.in); // Create a Scanner object

        System.out.println("Welcome to iFace. Choose an option from the list below: ");

        while (true) {
            if (current_user != null) {
                System.out.println("\n0 - Logout");
            } else {
                System.out.println("\n0 - Login");
            }
            System.out.println("1 - Register user");
            System.out.println("2 - Delete user");
            System.out.println("3 - List users");
            System.out.println("4 - Edit profile attributes");
            System.out.println("5 - Add Friend");
            System.out.println("6 - Friend invites");
            System.out.println("7 - See my information");
            System.out.println("8 - Send message");
            System.out.println("9 - See your messages");
            System.out.println("10 - Create community");
            System.out.println("11 - Add member to community");
            System.out.println("12 - List members from a community");
            System.out.println("13 - Open the news feed");
            System.out.println("14 - Add message to the feed");
            System.out.println("15 - Exit");

            String input = sc.next();

            if (input.equals("0")) {
                if (current_user == null) {
                    System.out.println("Selected: Login");
                    current_user = new User();
                    System.out.println("Enter your username: ");
                    String username = sc.next();
                    System.out.println("Enter your password: ");
                    String password = sc.next();
                    current_user = handleLogin(username, password, users);

                    if (current_user != null) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Login failed!");
                    }
                } else {
                    current_user = null;
                    System.out.println("You have logged out succesfully.");
                }
            } else if (input.equals("1")) {
                System.out.println("Selected: Register user");
                System.out.println("Email: ");
                String email = sc.next();
                System.out.println("Password: ");
                String password = sc.next();
                System.out.println("Username: ");
                String username = sc.next();
                createUser(users, email, password, username);
            } else if (input.equals("2")) {
                System.out.println("Selected: Delete user");
                System.out.println("Enter your email: ");
                String email = sc.next();
                System.out.println("Enter your password: ");
                String password = sc.next();
                if (email.equals(current_user.getEmail()) && password.equals(current_user.getPassword())) {
                    current_user = null;
                }
                deleteUser(users, email, password);
            } else if (input.equals("3")) {
                System.out.println("Selected: List users");
                listUsers(users);
            } else if (input.equals("4")) {
                System.out.println("Selected: Edit profile attributes");
                System.out.println("Enter your email: ");
                String email = sc.next();
                System.out.println("Enter your password: ");
                String password = sc.next();
                current_user.editAttributes(users, email, password, sc);
            } else if (input.equals("5")) {
                System.out.println("Selected: Add friend");
                if (current_user == null) {
                    System.out.println("You must login before adding friends!");
                } else {
                    System.out.println("Add friend: ");
                    String invite_friend = sc.next();
                    current_user.addFriend(users, current_user, invite_friend);
                }
            } else if (input.equals("6")) {
                System.out.println("Selected: See invites");
                if (current_user == null) {
                    System.out.println("You must login before seeing your invites!");
                } else {
                    current_user.seeInvites(sc, users);
                }
            } else if (input.equals("7")) {
                System.out.println("Selected: See my information");
                if (current_user == null) {
                    System.out.println("You must login before seeing your information!");
                } else {
                    System.out.println("\nUser ID: " + current_user.getId());
                    System.out.println("User EMAIL: " + current_user.getEmail());
                    System.out.println("User PASSWORD: " + current_user.getPassword());
                    System.out.println("User USERNAME: " + current_user.getUsername());
                    System.out.println("Friends: ");

                    int null_users = 0;

                    for (int i = 0; i < 1000; i++) {
                        if (current_user.friends[i] != null) {
                            if (current_user.friends[i].relationship.equals("Friends")) {
                                System.out.println(current_user.friends[i].username);
                            }
                        } else {
                            null_users++;
                        }

                        if (null_users == 1000) {
                            System.out.println("You have no friends.");
                        }

                    }
                }
            } else if (input.equals("8")) {
                System.out.println("Selected: Send message");
                System.out.println("Send a message to user: ");
                String username = sc.next();
                for (int i = 0; i < 1000; i++) {
                    if (users[i] != null) {
                        if (username.equals(users[i].getUsername())) {
                            System.out.println("Type the message you wish to send below: ");
                            String message = sc.next();
                            try {
                                users[i].addMessage(current_user.getUsername() + ": " + message);
                            } catch (Exception e) {
                                System.out.println("Error: your message was not able to be sent. Code: " + e);
                            }
                        }
                    }
                }
            } else if (input.equals("9")) {
                System.out.println("Selected: See your messages");
                if (current_user == null) {
                    System.out.println("You must login before seeing your messages!");
                } else {
                    for (int i = 0; i < current_user.messages.size(); i++) {
                        System.out.println(current_user.messages.get(i));
                    }
                }
            } else if (input.equals("10")) {
                System.out.println("Selected: Create community");
                Community community = new Community();
                System.out.println("Name of the community: ");
                String name = sc.next();
                System.out.println("Topic of the community:");
                String topic = sc.next();
                System.out.println("Capacity: ");
                int capacity = sc.nextInt();
                community.setName(name);
                community.setTopic(topic);
                community.setCapacity(capacity);
                communities.add(community);
                System.out.println("Community created!");
            } else if (input.equals("11")) {
                System.out.println("Add member to community");
                System.out.println("Username of the user who is going to be added");
                String username = sc.next();

                System.out.println("List of available communities");

                for (int i = 0; i < communities.size(); i++) {
                    System.out.println(communities.get(i).getName());
                }

                System.out.println("Will be added to the community called: >");
                String community = sc.next();

                for (int k = 0; k < communities.size(); k++) {
                    if (community.equals(communities.get(k).getName())) {
                        communities.get(k).addMember(username);
                    }
                }

                System.out.println("User was added!");
            } else if (input.equals("12")) {
                System.out.println("Selected: List members from a community");
                System.out.println("Select a community from the list: ");
                for (int i = 0; i < communities.size(); i++) {
                    System.out.println(communities.get(i).getName());
                }

                System.out.println("\n");

                String selection = sc.next();

                System.out.println("\n");

                for (int k = 0; k < communities.size(); k++) {
                    if (communities.get(k) != null) {
                        if (selection.equals(communities.get(k).getName())) {
                            for (int i = 0; i < communities.get(k).members.size(); i++) {
                                System.out.println(communities.get(k).members.get(i));
                            }
                        }
                    }
                }
            } else if (input.equals("13")) {
                System.out.println("Selected: Open the news feed");

                listNews(current_user);
            } else if (input.equals("14")) {
                System.out.println("Selected: Add message to the feed");
                System.out.println("Type a message to send to the feed: ");
                String message = sc.next();
                message += sc.nextLine();
                System.out.println("Will this message only be visible to friends? 1 for YES | 0 for NO ");
                String x = sc.next();
                boolean privacy;
                if (x.equals("1")) {
                    privacy = true;
                    addNews(current_user.getId(), current_user, message, privacy);
                } else if (x.equals("0")) {
                    privacy = false;
                    addNews(current_user.getId(), current_user, message, privacy);
                } else {
                    System.out.println("Invalid option! Please try again.");
                }

            } else if (input.equals("15")) {
                System.out.println("Thank you for trying out iFace!");
                break;
            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }

        sc.close();
    }
}
