package ynu.com.ynubbs.Entity;

public class Post {
    private int p_id;
    private int p_class;
    private String p_theme;
    private String p_content;
    private String p_time;
    private int p_user;

    public Post() {

    }

    public Post(int p_id, int p_class, String p_theme, String p_content, String p_time, int p_user) {
        this.p_id=p_id;
        this.p_class=p_class;
        this.p_theme=p_theme;
        this.p_content=p_content;
        this.p_time=p_time;
        this.p_user=p_user;
    }

    public void SetId(int i) {
        p_id=i;
    }
    public int GetId() {
        return p_id;
    }

    public void SetClass(int c) {
        p_class=c;
    }

    public int GetClass() {
        return p_class;
    }

    public void SetTheme(String theme) {
        p_theme=theme;
    }
    public String GetTheme() {
        return p_theme;
    }

    public void SetContent(String content) {
        p_content=content;
    }
    public String GetContent() {
        return p_content;
    }

    public void SetTime(String time) {
        p_time=time;
    }
    public String GetTime() {
        return p_time;
    }

    public void SetUserId(int i) {
        p_user=i;
    }
    public int GetUserId() {
        return p_user;
    }

}
