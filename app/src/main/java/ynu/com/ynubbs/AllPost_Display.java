package ynu.com.ynubbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import ynu.com.ynubbs.Entity.Post;
import ynu.com.ynubbs.Entity.Reply;
import ynu.com.ynubbs.Web.WebService;

public class AllPost_Display extends AppCompatActivity {
    private TextView Note_title,Note_content;
    private Button mBtn_reply,mBtn_delete,mBtn_re;
    private LinearLayout lly_reply;
    private ArrayList<Post> posts=new ArrayList<Post>();
    private String postid,postuser,info,pid,rclass,ruser;
    private ArrayList<Reply> replys = new ArrayList<Reply>();
    private ArrayList<Reply> reply1 = new ArrayList<Reply>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_post_display);
        Intent disIntent = getIntent();
        final String userid = disIntent.getStringExtra("userid");
        final String[] postdatacontent = disIntent.getStringArrayExtra("postdatacontent");
        final String[] postdatatheme = disIntent.getStringArrayExtra("postdatatheme");
        final String[] postdataid1 = disIntent.getStringArrayExtra("postdataid");
        final String[] postdatauser = disIntent.getStringArrayExtra("postdatauser");
        final String[] replydatacontent = disIntent.getStringArrayExtra("replydatacontent");
        final String[] replydataid = disIntent.getStringArrayExtra("replydataid");
        final String[] replydatauser = disIntent.getStringArrayExtra("replydatauser");
        postid=postdataid1[0];
        postuser=postdatauser[0];
        ruser=replydatauser[0];
        Note_title=(TextView) findViewById(R.id.tv_note_title);
        Note_content=(TextView) findViewById(R.id.tv_note_content);
        lly_reply=(LinearLayout) findViewById(R.id.lly_reply);
        Note_title.setText(postdatatheme[0]);
        Note_content.setText(postdatacontent[0]);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        for (String c :replydatacontent){
//            System.out.println(c);
//        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
        ShowReply(replydataid,replydatacontent,userid);
        mBtn_reply=(Button)findViewById(R.id.reply);
        mBtn_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new MyThread1()).start();
            }
        });
        mBtn_delete=(Button)findViewById(R.id.post_delete);
        mBtn_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("9999999999999999999999999999999999999999999999999999999999999999999999");
                System.out.println(postuser);
                System.out.println(userid);
                if (postuser.equals(userid)){
                    new Thread(new MyThread2()).start();
                }
            }
        });
        mBtn_re=(Button)findViewById(R.id.post_re);
        mBtn_re.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new Thread(new MyThread5()).start();
            }
        });
    }

    private void ShowReply(final String[] m,String[] n,final String userid) {
        for ( int i = 0; i < n.length; i++) {
            final String rid=m[i];
            int sign=Integer.valueOf(rid);
            if (sign!=0){
                TextView tv = new TextView(this);
                tv.setText(n[i]);
                tv.setMaxLines(1);
                lly_reply.addView(tv);
                Button b1 =new Button(this);
                b1.setText("回复");
                Button b2 =new Button(this);
                b2.setText("删除");
                lly_reply.addView(b1);
                lly_reply.addView(b2);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MyThread3 mt =new MyThread3();
                        mt.setReplyId(rid);
                        Thread thread=new Thread(mt);
                        thread.start();
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MyThread4 mt =new MyThread4();
                        mt.setReplyId(rid);
                        Thread thread=new Thread(mt);
                        thread.start();
                    }
                });
            }
        }
    }

    public class MyThread1 implements Runnable {
        @Override
        public void run() {
            Intent disIntent = getIntent();
            final String userid = disIntent.getStringExtra("userid");
            final String[] postdataid = disIntent.getStringArrayExtra("postdataid");
            Intent intent = new Intent(AllPost_Display.this, AllReply_Submission.class);
            pid=postdataid[0];
            rclass="0";
            intent.putExtra("userid",userid);
            intent.putExtra("pid",pid);
            intent.putExtra("rclass",rclass);
            intent.putExtra("rtoid",pid);
            startActivity(intent);
        }

    }
    public class MyThread2 implements Runnable {
        @Override
        public void run() {
            Intent disIntent = getIntent();
            final String userid = disIntent.getStringExtra("userid");
            final String[] postdataid = disIntent.getStringArrayExtra("postdataid");
            postid=postdataid[0];
            WebService.DeletePostExecuteHttpGet("DeletePostServlet",postid);
            Intent intent = new Intent(AllPost_Display.this, Main.class);
            posts=WebService.SelectAllExecuteHttpGet("SelectAllServlet");
            ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
            int j=0;
            for (Post ps : posts) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("p_id", ps.GetId());
                item.put("p_class", ps.GetClass());
                item.put("p_theme", ps.GetTheme());
                item.put("p_content", ps.GetContent());
                item.put("p_time", ps.GetTime());
                item.put("p_user", ps.GetUserId());
                data.add(item);
                j++;
            }
            String[] postdataids=new String[j];
            String[] postdatathemes=new String[j];
            int i=0;
            for (HashMap<String,Object> m: data){
                System.out.println("-------------------------------------------");
                String x =(m.get("p_id")).toString();
                String k =(m.get("p_theme")).toString();
                postdataids[i]=x;
                postdatathemes[i]=k;
                i++;
            }
            System.out.println(data);
            intent.putExtra("userid",userid);
            intent.putExtra("postdataid",postdataids);
            intent.putExtra("postdatatheme",postdatathemes);
            startActivity(intent);
        }

    }

    public class MyThread3 implements Runnable {
        private String replyid;
        public void setReplyId(String replyid){
            this.replyid=replyid;
        }
        @Override
        public void run() {
            Intent disIntent = getIntent();
            final String userid = disIntent.getStringExtra("userid");
            final String[] postdataid = disIntent.getStringArrayExtra("postdataid");
            Intent intent = new Intent(AllPost_Display.this, AllReply_Submission.class);
            pid=postdataid[0];
            rclass="1";
            System.out.println("22222222222222222222222222222222222222222222222");
            System.out.println(replyid);
            System.out.println("22222222222222222222222222222222222222222222222");
            intent.putExtra("userid",userid);
            intent.putExtra("pid",pid);
            intent.putExtra("rclass",rclass);
            intent.putExtra("rtoid",replyid);
            startActivity(intent);
        }
    }
    public class MyThread4 implements Runnable {
        private String replyid;
        public void setReplyId(String replyid){
            this.replyid=replyid;
        }
        @Override
        public void run() {
            Intent disIntent = getIntent();
            final String userid = disIntent.getStringExtra("userid");
            final String[] postdataid1 = disIntent.getStringArrayExtra("postdataid");
            final String type=disIntent.getStringExtra("type");
            pid=postdataid1[0];
            reply1=WebService.SelectReply1ExecuteHttpGet("SelectReply1Servlet",replyid);
            ArrayList<HashMap<String, Object>> r1data = new ArrayList<HashMap<String, Object>>();
            int jr1=0;
            for(Reply rs1:reply1){
                HashMap<String, Object> r1item = new HashMap<String, Object>();
                r1item.put("r_id",rs1.GetId());
                r1item.put("r_to_p_id",rs1.GetRToPId());
                r1item.put("r_to_class",rs1.GetClass());
                r1item.put("r_to_id",rs1.GetRToId());
                r1item.put("r_content",rs1.GetContent());
                r1item.put("r_time",rs1.GetTime());
                r1item.put("r_user",rs1.GetUserId());
                r1data.add(r1item);
                jr1++;
            }
            String[] replyuser1= new String[jr1];
            int ir1=0;
            for(HashMap<String,Object>mr:r1data){
                System.out.println("-------------------------------------------");
                String zr1=(mr.get("r_user")).toString();
                replyuser1[ir1] = zr1;
                ir1++;
            }
            ruser=replyuser1[0];
            if (ruser==userid){
                WebService.DeleteReplyExecuteHttpGet("DeleteReplyServlet",replyid);
            }
            Intent intent = new Intent(AllPost_Display.this, AllPost_Display.class);
            posts = WebService.SelectSingleExecuteHttpGet("SelectSingleServlet",pid);
            replys=WebService.SelectReplyExecuteHttpGet("SelectReplyServlet",pid);
            ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
            ArrayList<HashMap<String, Object>> rdata = new ArrayList<HashMap<String, Object>>();
            int j = 0,jr=0;
            for (Post ps : posts) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("p_id", ps.GetId());
                item.put("p_class", ps.GetClass());
                item.put("p_theme", ps.GetTheme());
                item.put("p_content", ps.GetContent());
                item.put("p_time", ps.GetTime());
                item.put("p_user", ps.GetUserId());
                data.add(item);
                j++;
            }
            for(Reply rs:replys){
                HashMap<String, Object> ritem = new HashMap<String, Object>();
                ritem.put("r_id",rs.GetId());
                ritem.put("r_to_p_id",rs.GetRToPId());
                ritem.put("r_to_class",rs.GetClass());
                ritem.put("r_to_id",rs.GetRToId());
                ritem.put("r_content",rs.GetContent());
                ritem.put("r_time",rs.GetTime());
                ritem.put("r_user",rs.GetUserId());
                rdata.add(ritem);
                jr++;
            }
            String[] postdataid=new String [j];
            String[] postdatacontent = new String[j];
            String[] postdatatheme = new String[j];
            String[] postdatauser= new String[j];
            String[] replydataid=new String [jr];
            String[] replydatacontent = new String[jr];
            String[] replydatauser= new String[jr];
            int i = 0,ir=0;
            for (HashMap<String, Object> m : data) {
                System.out.println("-------------------------------------------");
                String x = (m.get("p_content")).toString();
                String k = (m.get("p_theme")).toString();
                String y=(m.get("p_id")).toString();
                String z=(m.get("p_user")).toString();
                postdatacontent[i] = x;
                postdatatheme[i] = k;
                postdataid[i]=y;
                postdatauser[i]=z;
                i++;
            }
            for(HashMap<String,Object>mr:rdata){
                System.out.println("-------------------------------------------");
                String xr = (mr.get("r_content")).toString();
                String yr=(mr.get("r_id")).toString();
                String zr=(mr.get("r_user")).toString();
                replydatacontent[ir] = xr;
                replydataid[ir]=yr;
                replydatauser[ir]=zr;
                ir++;
            }
            System.out.println(data);
            System.out.println(postdatacontent[0]);
            System.out.println(postdatatheme[0]);
            System.out.println(type);
            for (String c :replydatacontent){
                System.out.println(c);
            }
            intent.putExtra("userid", userid);
            intent.putExtra("postdatacontent", postdatacontent);
            intent.putExtra("postdatatheme", postdatatheme);
            intent.putExtra("postdataid", postdataid);
            intent.putExtra("postdatauser", postdatauser);
            intent.putExtra("replydatauser", replydatauser);
            intent.putExtra("replydataid", replydataid);
            intent.putExtra("replydatacontent", replydatacontent);
            intent.putExtra("type", type);
            startActivity(intent);
        }
    }
    public class MyThread5 implements Runnable {
        @Override
        public void run() {
            Intent logIntent = getIntent();
            final String userid = logIntent.getStringExtra("userid");
            final String type = logIntent.getStringExtra("type");
            Intent selfIntent = new Intent(AllPost_Display.this, Main.class);
            posts=WebService.SelectAllExecuteHttpGet("SelectAllServlet");
            ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
            int j=0;
            for (Post ps : posts) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("p_id", ps.GetId());
                item.put("p_class", ps.GetClass());
                item.put("p_theme", ps.GetTheme());
                item.put("p_content", ps.GetContent());
                item.put("p_time", ps.GetTime());
                item.put("p_user", ps.GetUserId());
                data.add(item);
                j++;
            }
            System.out.println(data);
            String[] postdataid=new String[j];
            String[] postdatatheme=new String[j];
            int i=0;
            for (HashMap<String,Object> m: data){
                System.out.println("-------------------------------------------");
                String x =(m.get("p_id")).toString();
                String k =(m.get("p_theme")).toString();
                postdataid[i]=x;
                postdatatheme[i]=k;
                i++;
            }
            System.out.println(data);
            selfIntent.putExtra("userid",userid);
            selfIntent.putExtra("postdataid",postdataid);
            selfIntent.putExtra("postdatatheme",postdatatheme);
            selfIntent.putExtra("type",type);
            startActivity(selfIntent);
        }
    }
}
