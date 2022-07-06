import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Community.Community;
import Exceptions.EmptyInputException;
import Exceptions.InvalidEmailException;
import Exceptions.MaximumSizeException;
import User.User;
import Util.EmailValidator;

import User.ListUsers;

public class Main extends iFace {
    public static void main(String[] args) {
        User[] users = new User[1000];
        User client = null;
        ArrayList<Community> communities = new ArrayList<Community>();

        Scanner sc = new Scanner(System.in); // Create a Scanner object
        Boolean active = true;
        int input = -1;

        System.out.println("Welcome to iFace. Choose an option from the list below: ");

        while (active) {
            if (client != null) {
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

            try {
                input = sc.nextInt();
                sc.nextLine();

                switch (input) {
                    case 0:
                        if (client == null) {
                            System.out.println("Selected: Login");
                            System.out.println("Enter your username: ");
                            String username = sc.next();
                            System.out.println("Enter your password: ");
                            String password = sc.next();
                            client = handleLogin(username, password, users);

                            if (client != null) {
                                System.out.println("Login successful!");
                            } else {
                                System.out.println("Login failed!");
                            }
                        } else {
                            client = null;
                            System.out.println("You have logged out succesfully.");
                        }
                        break;
                    case 1:
                        System.out.println("Selected: Register user");

                        try {
                            System.out.println("Email: ");
                            String email = sc.next();

                            if (email.length() > 45) {
                                throw new MaximumSizeException(
                                        "The email must have less than " + 45 + " characters in length");
                            }

                            if (email.equals("")) {
                                throw new EmptyInputException("You must type something to the email.");
                            }

                            if (EmailValidator.isValid(email) == false) {
                                throw new InvalidEmailException("The email is invalid.");
                            }

                            System.out.println("Password: ");
                            String password = sc.next();
                            if (password.length() > 45) {
                                throw new MaximumSizeException(
                                        "The password must have less than " + 45 + " characters in length");
                            }

                            if (password.equals("")) {
                                throw new EmptyInputException("You must type something to the password.");
                            }
                            System.out.println("Username: ");
                            String username = sc.next();
                            if (username.length() > 45) {
                                throw new MaximumSizeException(
                                        "The username must have less than " + 45 + " characters in length");
                            }

                            if (username.equals("")) {
                                throw new EmptyInputException("You must type something to the username.");
                            }
                            createUser(users, email, password, username);
                        } catch (EmptyInputException e) {
                            System.out.println(e);
                        } catch (MaximumSizeException e) {
                            System.out.println(e);
                        } catch (InvalidEmailException e) {
                            System.out.println(e);
                        }
                        break;
                    case 2:
                        System.out.println("Selected: Delete user");

                        try {
                            System.out.println("Email: ");
                            String email = sc.next();

                            if (email.length() > 45) {
                                throw new MaximumSizeException(
                                        "Emails usually have less than 45 characters in length.");
                            }

                            if (email.equals("")) {
                                throw new EmptyInputException("You must type something in the email field.");
                            }

                            System.out.println("Password: ");
                            String password = sc.next();
                            if (password.length() > 45) {
                                throw new MaximumSizeException(
                                        "The password must have less than " + 45 + " characters in length");
                            }

                            if (password.equals("")) {
                                throw new EmptyInputException("You must type something to the password.");
                            }

                            if (client != null) {
                                if (email.equals(client.getEmail())
                                        && password.equals(client.getPassword())) {
                                    client = null;
                                }
                            }

                            deleteUser(users, email, password);

                        } catch (EmptyInputException e) {
                            System.out.println(e);
                        } catch (MaximumSizeException e) {
                            System.out.println(e);
                        }
                        break;
                    case 3:
                        System.out.println("Selected: List users");
                        ListUsers.listUsers(users);
                        break;
                    case 4:
                        System.out.println("Selected: Edit profile attributes");

                        try {
                            System.out.println("Email: ");
                            String email = sc.next();

                            if (email.length() > 45) {
                                throw new MaximumSizeException(
                                        "Emails usually have less than 45 characters in length.");
                            }

                            if (email.equals("")) {
                                throw new EmptyInputException("You must type something in the email field.");
                            }

                            System.out.println("Password: ");
                            String password = sc.next();
                            if (password.length() > 45) {
                                throw new MaximumSizeException(
                                        "The password must have less than " + 45 + " characters in length");
                            }

                            if (password.equals("")) {
                                throw new EmptyInputException("You must type something to the password.");
                            }

                            client.editAttributes(users, email, password, sc);
                        } catch (EmptyInputException e) {
                            System.out.println(e);
                        } catch (MaximumSizeException e) {
                            System.out.println(e);
                        }
                        break;
                    case 5:
                        System.out.println("Selected: Add friend");
                        if (client == null) {
                            System.out.println("You must login before adding friends!");
                        } else {
                            System.out.println("Add friend: ");
                            String invite_friend = sc.next();
                            client.addFriend(users, client, invite_friend);
                        }
                        break;
                    case 6:
                        System.out.println("Selected: See invites");
                        if (client == null) {
                            System.out.println("You must login before seeing your invites!");
                        } else {
                            client.seeInvites(sc, users);
                        }
                    case 7:
                        System.out.println("Selected: See my information");
                        if (client == null) {
                            System.out.println("You must login before seeing your information!");
                        } else {
                            System.out.println("\nUser ID: " + client.getId());
                            System.out.println("User EMAIL: " + client.getEmail());
                            System.out.println("User USERNAME: " + client.getUsername());
                            System.out.println("Friends: ");

                            int null_users = 0;

                            for (int i = 0; i < 1000; i++) {
                                if (client.friends[i] != null) {
                                    if (client.friends[i].relationship.equals("Friends")) {
                                        System.out.println(client.friends[i].username);
                                    }
                                } else {
                                    null_users++;
                                }

                                if (null_users == 1000) {
                                    System.out.println("You have no friends.");
                                }

                            }
                        }
                        break;
                    case 8:
                        System.out.println("Selected: Send message");
                        System.out.println("Send a message to user: ");
                        try {
                            String username = sc.next();
                            for (int i = 0; i < 1000; i++) {
                                if (users[i] != null) {
                                    if (username.equals(users[i].getUsername())) {
                                        System.out.println("Type the message you wish to send below: ");
                                        String message = sc.next();
                                        try {
                                            users[i].addMessage(client.getUsername() + ": " + message);
                                        } catch (Exception e) {
                                            System.out
                                                    .println("Error: your message was not able to be sent. Code: " + e);
                                        }
                                    }
                                }
                            }
                        }

                        catch (RuntimeException e) {
                            System.out.println("An error has ocourred during runtime: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("An issue has arisen with your program. Error: " + e.getMessage());
                        }
                        break;
                    case 9:
                        System.out.println("Selected: See your messages");
                        if (client == null) {
                            System.out.println("You must login before seeing your messages!");
                        } else {
                            for (int i = 0; i < client.messages.size(); i++) {
                                System.out.println(client.messages.get(i));
                            }
                        }
                        break;
                    case 10:
                        System.out.println("Selected: Create community");
                        Community community = new Community();
                        System.out.println("Name of the community: ");
                        String name = sc.next();
                        System.out.println("Topic of the community:");
                        String topic = sc.next();
                        community.setName(name);
                        community.setTopic(topic);
                        try {
                            System.out.println("Capacity: ");
                            String s = sc.next();
                            int capacity = Integer.parseInt(s);
                            community.setCapacity(capacity);
                        } catch (NumberFormatException e) {
                            System.out.println("The capacity must be defined as a number!");
                        }
                        communities.add(community);
                        System.out.println("Community created!");
                        break;
                    case 11:
                        System.out.println("Add member to community");
                        System.out.println("Username of the user who is going to be added");
                        String username = sc.next();

                        System.out.println("List of available communities");

                        for (int i = 0; i < communities.size(); i++) {
                            System.out.println(communities.get(i).getName());
                        }

                        System.out.println("Will be added to the community called: >");
                        String inputCommunity = sc.next();

                        for (int k = 0; k < communities.size(); k++) {
                            if (inputCommunity.equals(communities.get(k).getName())) {
                                communities.get(k).addMember(username);
                            }
                        }

                        System.out.println("User was added!");
                        break;
                    case 12:
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
                        break;
                    case 13:
                        System.out.println("Selected: Open the news feed");
                        listNews(client);
                        break;
                    case 14:
                        System.out.println("Selected: Add message to the feed");
                        if (client == null) {
                            System.out.println("You must log in before accessing this feature");
                        } else {
                            try {
                                System.out.println("Type a message to send to the feed: ");
                                String message = sc.next();
                                message += sc.nextLine();

                                if (message.equals("")) {
                                    throw new EmptyInputException("You must type something to the message.");
                                }

                                System.out
                                        .println("Will this message only be visible to friends? 1 for YES | 0 for NO ");
                                String s = sc.next();
                                int option = Integer.parseInt(s);
                                boolean privacy;
                                if (option == 1) {
                                    privacy = true;
                                    addNews(client.getId(), client, message, privacy);
                                } else if (option == 0) {
                                    privacy = false;
                                    addNews(client.getId(), client, message, privacy);
                                } else {
                                    System.out.println("Please choose one of the options listed above.");
                                }

                            } catch (EmptyInputException e) {
                                System.out.println(e);
                            } catch (NumberFormatException e) {
                                System.out.println("You must input a number.");
                            }
                        }
                        break;
                    case 15:
                        System.out.println("Thank you for trying out iFace!");
                        active = false;
                        break;
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("You must input a number. Try again.");
            } catch (NullPointerException e) {
                System.out.println("You must login before using this feature!");
            } catch (Exception e) {
                System.out.println("Unexpected error! Please try again later.");
            }
        }

        sc.close();
    }
}