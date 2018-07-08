package ynu.com.ynubbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import ynu.com.ynubbs.Entity.Reply;
import ynu.com.ynubbs.Entity.Post;
import ynu.com.ynubbs.Web.WebService;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Post_Publish extends AppCompatActivity {
    private Button plBtn, reBtn;
    private EditText themeET, contentET;
    private String p_class,p_theme,p_content,p_time,p_user;
    private String info;
    private ArrayList<Post> posts=new ArrayList<Post>();
    private ArrayList<Reply> replys = new ArrayList<Reply>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_publish);
        Intent logIntent = getIntent();
        final String userid = logIntent.getStringExtra("userid");
        final String type = logIntent.getStringExtra("type");
        plBtn=(Button) findViewById(R.id.btn_pl_note);
        reBtn=(Button) findViewById(R.id.btn_re);
        plBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeET = (EditText) findViewById(R.id.edt_note_title);
                contentET = (EditText) findViewById(R.id.edt_note_content);
                //String uid,p;
                p_theme=themeET.getText().toString();
                p_content=contentET.getText().toString();
                if ((p_theme != null) && (p_content!= null)) {
                    new Thread(new MyThread1()).start();
                } else{
                    themeET.setText("");
                    contentET.setText("");
                }
            }
        });
        reBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new MyThread2()).start();
            }
        });
    }

    public class MyThread1 implements Runnable {
        @Override
        public void run() {
            Intent logIntent = getIntent();
            final String userid = logIntent.getStringExtra("userid");
            final String type = logIntent.getStringExtra("type");
            p_class=type;
            p_user=userid;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
            Date date = new Date(System.currentTimeMillis());
            p_time=simpleDateFormat.format(date);
            info = WebService.PInsertExecuteHttpGet("InsertPostServlet",p_class,themeET.getText().toString(), contentET.getText().toString(),p_time,p_user);
        }
    }

    public class MyThread2 implements Runnable {
        @Override
        public void run() {
            Intent disIntent = getIntent();
            final String userid = disIntent.getStringExtra("userid");
            final String type = disIntent.getStringExtra("type");
            Intent intent = new Intent(Post_Publish.this, Post_Type.class);
            posts=WebService.SelectByTypeExecuteHttpGet("SelectByTypeServlet",type);
            ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
            int j = 0;
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
            String[] postdataid=new String [j];
            String[] postdatacontent = new String[j];
            String[] postdatatheme = new String[j];
            String[] postdatauser= new String[j];
            int i = 0;
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
            System.out.println(data);
            System.out.println(postdatacontent[0]);
            System.out.println(postdatatheme[0]);
            System.out.println(type);

            intent.putExtra("userid", userid);
            intent.putExtra("postdatacontent", postdatacontent);
            intent.putExtra("postdatatheme", postdatatheme);
            intent.putExtra("postdataid", postdataid);
            intent.putExtra("postdatauser", postdatauser);

            intent.putExtra("type", type);
            startActivity(intent);
        }
    }
}
