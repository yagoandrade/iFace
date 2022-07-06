package User;

import Friend.Friend;
import User.Attributes.Attribute;

import java.util.InputMismatchException;
import java.util.Scanner;

// Builder pattern
public class User extends Attribute implements UserObj {
    private final int id;
    private final String email;
    private final String password;
    private final String username;
    private boolean private_feed;
    public Friend[] friends = new Friend[1000];

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
        this.username = builder.username;
        this.private_feed = builder.private_feed;
    }

    /*
     * public void setId(int id) {
     * this.id = id;
     * }
     */

    @Override
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    /*
     * public void setEmail(String email) {
     * this.email = email;
     * }
     */

    public String getPassword() {
        return password;
    }

    /*
     * public void setPassword(String password) {
     * this.password = password;
     * }
     */

    @Override
    public String getUsername() {
        return username;
    }

    /*
     * public void setUsername(String username) {
     * this.username = username;
     * }
     */

    public static class UserBuilder {
        private int id;
        private final String email;
        private final String password;
        private String username;
        private boolean private_feed;

        public UserBuilder(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder private_feed(boolean private_feed) {
            this.private_feed = private_feed;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        // Return the finally consrcuted User object
        public User build() {
            User user = new User(this);
            validateUserObject(user);
            return user;
        }

        private void validateUserObject(User user) {
            try {
                if (user.email.isEmpty())
                    throw new Error("Email was not set correctly");
                if (user.password.isEmpty())
                    throw new Error("Password was not set correctly");
                // Do some basic validations to check
                // if user object does not break any assumption of system
            } catch (Exception error) {
                System.out.println("Error: " + error);
            }
        }
    }

    public boolean getPrivateFeed() {
        return private_feed;
    }

    public void setPrivateFeed(boolean value) {
        this.private_feed = value;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public void addFriend(User[] users, User currentUser, String username) {
        // Get added friend information
        for (int j = 0; j < 1000; j++) {
            if (users[j] != null) {
                if (username.equals(users[j].getUsername())) {
                    for (int i = 0; i < 1000; i++) {

                        if (currentUser.friends[i] == null) {
                            for (int k = 0; k < 1000; k++) {
                                if (users[j].friends[k] == null) {
                                    users[j].friends[k] = new Friend();
                                    users[j].friends[k].setId(currentUser.getId());
                                    users[j].friends[k].setUsername(currentUser.getUsername());
                                    users[j].friends[k].setRelationship("Pending");
                                    break;
                                }
                            }

                            friends[i] = new Friend();
                            friends[i].setId(users[j].getId());
                            friends[i].setUsername(users[j].getUsername());
                            friends[i].setRelationship("Outgoing");
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    public void editAttributes(User[] user, String email, String password, Scanner sc) {
        try {
            for (int i = 0; i < 1000; i++) {
                if (user[i] != null) {
                    if ((email.equals(user[i].getEmail())) && (password.equals(user[i].getPassword()))) {
                        while (true) {
                            if (user[i].getName() == null) {
                                System.out.println("1 - Register a new first name");
                                System.out
                                        .println("You currently don't have a first name associated with your account.");
                            } else {
                                System.out.println("\n1 - Update your first name");
                                System.out.println("Current first name: " + user[i].getName());

                            }
                            if (user[i].getSurname() == null) {
                                System.out.println("2 - Register a new last name");
                                System.out
                                        .println("You currently don't have a last name associated with your account.");
                            } else {
                                System.out.println("2 - Update your last name");
                                System.out.println("Current last name: " + user[i].getSurname());
                            }
                            if (user[i].getCity() == null) {
                                System.out.println("3 - Register a new city");
                                System.out.println("You currently don't have a city associated with your account.");
                            } else {
                                System.out.println("3 - Update your city");
                                System.out.println("Current city: " + user[i].getCity());
                            }
                            if (user[i].getAge() == -1) {
                                System.out.println("4 - Register a new age");
                                System.out.println("You currently don't have an age associated with your account.");
                            } else {
                                System.out.println("4 - Update your age");
                                System.out.println("Current age: " + user[i].getAge());
                            }
                            System.out.println("5 - Close attribute settings");

                            int input = sc.nextInt();
                            sc.nextLine();
                            if (input == 1) {
                                System.out.println("Desired first name: ");
                                String input_text = sc.next();
                                user[i].setName(input_text);
                            } else if (input == 2) {
                                System.out.println("Desired last name: ");
                                String input_text = sc.next();
                                user[i].setSurname(input_text);
                            } else if (input == 3) {
                                System.out.println("Desired city: ");
                                String input_text = sc.next();
                                user[i].setCity(input_text);
                            } else if (input == 4) {
                                System.out.println("Your age: ");
                                String input_text = sc.next();
                                try {
                                    int number = Integer.parseInt(input_text);
                                    user[i].setAge(number);
                                } catch (Exception e) {
                                    System.out.println("You must input a number for your age!");
                                }
                            } else if (input == 5) {
                                System.out.println("Selected: Close attribute settings");
                                break;
                            }
                        }
                        return;
                    }
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println("There was an error when accessing the memory, please try again.");
        } catch (Exception e) {
            System.out.println("There was an error when editing the user, please try again.");
        }
    }

    public void seeInvites(Scanner sc, User[] users) {
        int pending_invites = 0;
        int outgoing_invites = 0;

        for (int i = 0; i < 1000; i++) {
            if (friends[i] != null) {
                if (friends[i].relationship == "Pending") {
                    System.out.println(friends[i].user_id + " - Invite pending: " + friends[i].username);
                    pending_invites++;
                }
            }
        }

        for (int i = 0; i < 1000; i++) {
            if (friends[i] != null) {
                if (friends[i].relationship == "Outgoing") {
                    System.out.println(friends[i].user_id + " - Invite outgoing: " + friends[i].username);
                    outgoing_invites++;
                }
            }
        }

        if (pending_invites == 0) {
            if (outgoing_invites == 0) {
                System.out.println("No pending or outgoing invites");
            }
            return;
        } else {
            System.out.println("You wish to respond to any of these invites?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");

            try {
                int input = sc.nextInt();

                if (input == 1) {
                    System.out.println("Please enter the id of the user invite you wish to respond to: ");
                    int invite_id = sc.nextInt();
                    sc.nextLine();
                    for (int j = 0; j < 1000; j++) {
                        if (friends[j] != null) {
                            if (friends[j].getId() == invite_id) {
                                System.out.println("Press 1 to accept");
                                System.out.println("Press 2 to deny");
                                int response = sc.nextInt();
                                sc.nextLine();
                                if (response == 1) {
                                    friends[j].setRelationship("Friends");
                                    for (int n = 0; n < 1000; n++) {
                                        if (users[n] != null) {
                                            if (users[n].getUsername().equals(friends[j].getUsername())) {
                                                for (int m = 0; m < 1000; m++) {
                                                    if (users[n].friends[m] != null) {
                                                        if (users[n].friends[m].getUsername()
                                                                .equals(this.getUsername())) {
                                                            System.out.println(users[n].friends[m].getRelationship());
                                                            if (users[n].friends[m].getRelationship() == "Outgoing") {
                                                                users[n].friends[m].setRelationship("Friends");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    System.out.println("Accepted");
                                    break;
                                } else if (response == 2) {
                                    for (int n = 0; n < 1000; n++) {
                                        if (users[n] != null) {
                                            if (users[n].getUsername().equals(friends[j].getUsername())) {
                                                for (int m = 0; m < 1000; m++) {
                                                    if (users[n].friends[m] != null) {
                                                        if (users[n].friends[m].getUsername()
                                                                .equals(this.getUsername())) {
                                                            System.out.println(users[n].friends[m].getRelationship());
                                                            if (users[n].friends[m].getRelationship() == "Outgoing") {
                                                                users[n].friends[m] = null;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    friends[j] = null;
                                    System.out.println("Denied");
                                    break;
                                }
                            }
                        }
                    }
                } else if (input == 2) {
                    System.out.println("Returning...");
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Your input must be an integer.");
            }
            return;
        }
    }
}