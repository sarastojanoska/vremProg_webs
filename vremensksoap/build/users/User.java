package users;

import java.util.List;
import java.io.Serializable;
import prognoza.Prognoza;

public class User implements Serializable {
    private String username;
    private String password;
    private List<Integer> prognozi;
    
    public User() { }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public void setSearches(List<Integer> prognozi) {
        this.prognozi = prognozi;
    }
    public List<Integer> getSearches() {
        return this.prognozi;
    }
}
