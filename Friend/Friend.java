package Friend;

import User.User;

public class Friend extends User {
    public int user_id;
    public String username;
    public String relationship;

    @Override
    public int getId() {
        return user_id;
    }

    @Override
    public void setId(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}