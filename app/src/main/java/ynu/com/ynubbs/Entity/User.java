package ynu.com.ynubbs.Entity;

public class User {
    private int u_id;
    private String u_name;
    private String u_password;

    public User(){

    }

    public User( int u_id,String u_name,String u_password){
        this.u_id=u_id;
        this.u_name=u_name;
        this.u_password=u_password;
    }

    public void SetId(int i) {
        u_id=i;
    }

    public int GetId() {
        return u_id;
    }

    public void SetName(String name) {
        u_name=name;
    }
    public String GetName() {
        return u_name;
    }

    public void SetPassword(String p) {
        u_password=p;
    }
    public String GetPassword() {
        return u_password;
    }

}
