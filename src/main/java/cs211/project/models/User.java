package cs211.project.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class User extends Person {
    private UUID id;
    private String avatar;
    private String username;
    private String password;
    private String role;
    private String lastLogin;
    private String createdAt;

    public User(HashMap<String, String> userMap) {
        super(userMap.get("firstName"), userMap.get("lastName"));
        this.id = UUID.fromString(userMap.get("id"));
        this.avatar = userMap.get("avatar");
        this.username = userMap.get("username");
        this.password = userMap.get("password");
        this.role = userMap.get("role");
        this.lastLogin = userMap.get("lastLogin");
        this.createdAt = userMap.get("createdAt");
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String  lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public HashMap<String, String> toHashMap() {
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("id", this.id.toString());
        userMap.put("avatar", this.avatar);
        userMap.put("firstName", this.getFirstName());
        userMap.put("lastName", this.getLastName());
        userMap.put("username", this.username);
        userMap.put("password", this.password);
        userMap.put("role", this.role);
        userMap.put("lastLogin", this.lastLogin);
        userMap.put("createdAt",this.createdAt);
        return userMap;
    }
}
