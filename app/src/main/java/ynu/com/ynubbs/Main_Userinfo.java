package ynu.com.ynubbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import ynu.com.ynubbs.Entity.Post;
import ynu.com.ynubbs.Web.WebService;

public class Main_Userinfo extends AppCompatActivity {
    private Button mtBtn_shida,mtBtn_type;
    private ArrayList<Post> posts = new ArrayList<Post>();
    private TextView tvId,tvName,tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_userinfo);
        Intent intent = getIntent();
        final String userid = intent.getStringExtra("userid");
        final String[] username = intent.getStringArrayExtra("username");
        final String[] userpassword = intent.getStringArrayExtra("userpassword");
        tvId=(TextView)findViewById(R.id.etv1);
        tvName=(TextView)findViewById(R.id.etv2);
        tvPassword=(TextView)findViewById(R.id.etv3);
        tvId.setText(userid);
        tvName.setText(username[0]);
        tvPassword.setText(userpassword[0]);
        mtBtn_shida=(Button)findViewById(R.id.btn_shida);
        mtBtn_type=(Button)findViewById(R.id.btn_type);
        mtBtn_shida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new MyThread1()).start();
            }
        });
        mtBtn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent typeIntent = new Intent(Main_Userinfo.this, Main_Type.class);
                typeIntent.putExtra("userid", userid);
                startActivity(typeIntent);
            }
        });
    }

    public class MyThread1 implements Runnable {
        @Override
        public void run() {
            Intent logIntent = getIntent();
            final String userid = logIntent.getStringExtra("userid");
            Intent selfIntent = new Intent(Main_Userinfo.this, Main.class);
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
}
