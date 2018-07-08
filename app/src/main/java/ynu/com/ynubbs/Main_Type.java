package ynu.com.ynubbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

import ynu.com.ynubbs.Entity.Post;
import ynu.com.ynubbs.Entity.User;
import ynu.com.ynubbs.Web.WebService;

public class Main_Type extends AppCompatActivity {
    private Button mtBtn_1,mtBtn_2,mtBtn_3,mtBtn_shida,mtBtn_userinfo;
    private ArrayList<Post> posts=new ArrayList<Post>();
    private ArrayList<User> user=new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_type);
        Intent typeIntent = getIntent();
        final String userid = typeIntent.getStringExtra("userid");
        mtBtn_1=(Button) findViewById(R.id.btn_type_1);
        mtBtn_2=(Button) findViewById(R.id.btn_type_2);
        mtBtn_3=(Button) findViewById(R.id.btn_type_3);
        mtBtn_shida=(Button) findViewById(R.id.mt_btn_shida);
        mtBtn_userinfo=(Button) findViewById(R.id.mt_btn_userinfo);

        mtBtn_1.setOnClickListener(new View.OnClickListener() {
            final String type="1";
            @Override
            public void onClick(View view) {
                MyThread1 mt =new MyThread1();
                mt.setType(type);
                Thread thread=new Thread(mt);
//                System.out.print("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
                thread.start();
            }
        });
        mtBtn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String type="2";
                MyThread2 mt =new MyThread2();
                mt.setType(type);
                Thread thread=new Thread(mt);
                thread.start();
            }
        });
        mtBtn_3.setOnClickListener(new View.OnClickListener() {
            final String type="3";
            @Override
            public void onClick(View view) {
                MyThread3 mt =new MyThread3();
                mt.setType(type);
                Thread thread=new Thread(mt);
                thread.start();
            }
        });

        mtBtn_shida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new MyThread4()).start();
            }
        });
        mtBtn_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new MyThread5()).start();
            }
        });

    }

    public class MyThread1 implements Runnable {
        private String type;
        public void setType(String type){
            this.type=type;
        }
        @Override
        public void run() {
            Intent logIntent = getIntent();
            final String userid = logIntent.getStringExtra("userid");
            Intent selfIntent = new Intent(Main_Type.this, Post_Type.class);
            posts=WebService.SelectByTypeExecuteHttpGet("SelectByTypeServlet",type);
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

    public class MyThread2 implements Runnable {
        private String type;
        public void setType(String type){
            this.type=type;
        }
        @Override
        public void run() {
            Intent logIntent = getIntent();
            final String userid = logIntent.getStringExtra("userid");
            Intent selfIntent = new Intent(Main_Type.this, Post_Type.class);
            posts=WebService.SelectByTypeExecuteHttpGet("SelectByTypeServlet",type);
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

    public class MyThread3 implements Runnable {
        private String type;
        public void setType(String type){
            this.type=type;
        }
        @Override
        public void run() {
            Intent logIntent = getIntent();
            final String userid = logIntent.getStringExtra("userid");
            Intent selfIntent = new Intent(Main_Type.this, Post_Type.class);
            posts=WebService.SelectByTypeExecuteHttpGet("SelectByTypeServlet",type);
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

    public class MyThread4 implements Runnable {
        @Override
        public void run() {
            Intent logIntent = getIntent();
            final String userid = logIntent.getStringExtra("userid");
            Intent selfIntent = new Intent(Main_Type.this, Main.class);
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
            startActivity(selfIntent);
        }

    }

    public class MyThread5 implements Runnable {
        @Override
        public void run() {
            Intent logIntent = getIntent();
            final String userid = logIntent.getStringExtra("userid");
            Intent selfIntent = new Intent(Main_Type.this, Main_Userinfo.class);
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
            selfIntent.putExtra("userid",userid);
            selfIntent.putExtra("username",username);
            selfIntent.putExtra("userpassword",userpassword);
            startActivity(selfIntent);
        }

    }

}
