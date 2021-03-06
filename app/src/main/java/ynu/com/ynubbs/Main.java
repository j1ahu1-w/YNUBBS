package ynu.com.ynubbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import java.util.HashMap;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

import ynu.com.ynubbs.Entity.Post;
import ynu.com.ynubbs.Entity.Reply;
import ynu.com.ynubbs.Entity.User;

import ynu.com.ynubbs.Web.WebService;

public class Main extends AppCompatActivity {

    private LinearLayout sv_lly;
    private Button mBtn_type, mBtn_shida, mBtn_userinfo;
    private ArrayList<Post> posts = new ArrayList<Post>();
    private ArrayList<Post> post = new ArrayList<Post>();
    private ArrayList<User> user = new ArrayList<User>();
    private ArrayList<Reply> replys = new ArrayList<Reply>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent logIntent = getIntent();
        final String userid = logIntent.getStringExtra("userid");
        final String[] postdataid = logIntent.getStringArrayExtra("postdataid");
        final String[] postdatatheme = logIntent.getStringArrayExtra("postdatatheme");
        sv_lly = (LinearLayout) findViewById(R.id.sv_lly);
        ShowAll(postdataid,postdatatheme,userid);
        mBtn_shida = (Button) findViewById(R.id.btn_shida);
        mBtn_shida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new MyThread1()).start();
            }
        });
        mBtn_type = (Button) findViewById(R.id.btn_type);
        mBtn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent typeIntent = new Intent(Main.this, Main_Type.class);
                typeIntent.putExtra("userid", userid);
                startActivity(typeIntent);
            }
        });
        ///跳入用户个人信息管理界面
        mBtn_userinfo = (Button) findViewById(R.id.btn_userinfo);
        mBtn_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new MyThread3()).start();
            }
        });
    }

    public class MyThread1 implements Runnable {
            @Override
            public void run() {
                Intent logIntent = getIntent();
                final String userid = logIntent.getStringExtra("userid");
                Intent selfIntent = new Intent(Main.this, Main.class);
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
                String[] postdataid=new String[j];
                String[] postdatatheme=new String[j];
                int i=0;
                for (HashMap<String,Object> m: data){
//                    System.out.println("-------------------------------------------");
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
                startActivity(selfIntent);
            }

    }

    private void ShowAll(final String[] m,String[] n,final String userid) {
        for ( int i = 0; i < n.length; i++) {
            TextView tv = new TextView(this);
            tv.setText(n[i]);
            final String pid=m[i];
            int sign=Integer.valueOf(pid);
            if (sign!=0){
                tv.setMaxLines(1);
                sv_lly.addView(tv);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                    new Thread(new MyThread2()).start();
                        MyThread2 mt =new MyThread2();
                        mt.setPostid(pid);
                        Thread thread=new Thread(mt);
                        thread.start();
                    }
                });
            }

        }
    }
    public class MyThread2 implements Runnable {
        private String postid;
        public void setPostid(String postid){
            this.postid=postid;
        }
        @Override
        public void run() {
            Intent logIntent = getIntent();
            final String userid = logIntent.getStringExtra("userid");
            Intent disIntent = new Intent(Main.this, AllPost_Display.class);
            posts = WebService.SelectSingleExecuteHttpGet("SelectSingleServlet", postid);
            replys=WebService.SelectReplyExecuteHttpGet("SelectReplyServlet",postid);
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
//            System.out.println(data);
//            System.out.println(postdatacontent[0]);
//            System.out.println(postdatatheme[0]);
//            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//            for (String t: replydatacontent){
//                System.out.println(t);
//            }
//            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
            disIntent.putExtra("userid", userid);
            disIntent.putExtra("postdatacontent", postdatacontent);
            disIntent.putExtra("postdatatheme", postdatatheme);
            disIntent.putExtra("postdataid", postdataid);
            disIntent.putExtra("postdatauser", postdatauser);
            disIntent.putExtra("replydatauser", replydatauser);
            disIntent.putExtra("replydataid", replydataid);
            disIntent.putExtra("replydatacontent", replydatacontent);
            startActivity(disIntent);
        }
    }

    public class MyThread3 implements Runnable {
        @Override
        public void run() {
            Intent logIntent = getIntent();
            final String userid = logIntent.getStringExtra("userid");
            Intent uIntent = new Intent(Main.this, Main_Userinfo.class);
            user=WebService.SelectUserExecuteHttpGet("UserServlet",userid);
            ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
            int j=0;
            for (User u : user) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("u_id", u.GetId());
                item.put("u_name", u.GetName());
                item.put("u_password", u.GetPassword());
                data.add(item);
                j++;
            }
            String[] username=new String[j];
            String[] userpassword=new String[j];
            int i=0;
            for (HashMap<String,Object> m: data){
                System.out.println("-------------------------------------------");
                String x =(m.get("u_name")).toString();
                String k =(m.get("u_password")).toString();
                username[i]=x;
                userpassword[i]=k;
                i++;
            }
            System.out.println(data);
            uIntent.putExtra("userid",userid);
            uIntent.putExtra("username",username);
            uIntent.putExtra("userpassword",userpassword);
            startActivity(uIntent);
        }

    }
}