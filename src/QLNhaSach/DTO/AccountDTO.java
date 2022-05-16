package QLNhaSach.DTO;

public class AccountDTO {
    String UserName;
    String Password;
    int Level;
    int ID;
    
    public AccountDTO()
    {}

    public AccountDTO(int ID, String UserName, String Password, int Level) {
        this.ID = ID;
        this.UserName = UserName;
        this.Password = Password;
        this.Level = Level;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    } 
}
