package ynu.com.ynubbs.Entity;

public class Reply {
    private int r_id;
    private int r_to_p_id;
    private int r_to_class;
    private int r_to_id;
    private String r_content;
    private String r_time;
    private int r_user;

    public Reply() {

    }

    public Reply(int r_id, int r_to_p_id, int r_to_class, int r_to_id, String r_content, String r_time, int r_user) {
        this.r_id=r_id;
        this.r_to_p_id=r_to_p_id;
        this.r_to_class=r_to_class;
        this.r_to_id=r_to_id;
        this.r_content=r_content;
        this.r_time=r_time;
        this.r_user=r_user;
    }

    public void SetId(int i) {
        r_id=i;
    }
    public int GetId() {
        return r_id;
    }

    public void SetRToPId(int i) {
        r_to_p_id=i;
    }
    public int GetRToPId() {
        return r_to_p_id;
    }

    public void SetClass(int r) {
        r_to_class=r;
    }
    public int GetClass() {
        return r_to_class;
    }

    public void SetRToId(int i) {
        r_to_id=i;
    }
    public int GetRToId() {
        return r_to_id;
    }


    public void SetContent(String content) {
        r_content=content;
    }
    public String GetContent() {
        return r_content;
    }

    public void SetTime(String time) {
        r_time=time;
    }
    public String GetTime() {
        return r_time;
    }

    public void SetUserId(int i) {
        r_user=i;
    }
    public int GetUserId() {
        return r_user;
    }

}
