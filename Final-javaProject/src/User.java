import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;

public class User implements Serializable{
    private String username;
    private String password;
    private Boolean isAdmin;

    public User(String username, String password, Boolean isAdmin){
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public Boolean getIsAdmin(){
        return this.isAdmin;
    }
    public void setUsername(String name){
        this.username = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setIsAdmin(Boolean isAdmin){
        this.isAdmin = isAdmin;
    }
}