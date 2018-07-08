package ynu.com.ynubbs;

import ynu.com.ynubbs.Web.WebService;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import ynu.com.ynubbs.Entity.Post;


public class Login  extends AppCompatActivity {

    private Button LogBtn, toRegBtn;
    private EditText u_id, password;
    private String info;
    private TextView infotv;
    private ProgressDialog dialog;
    private static Handler handler = new Handler();
    private String userid,p;
    private ArrayList<Post> posts=new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 获取控件
        LogBtn = (Button) findViewById(R.id.btn_login);
        toRegBtn = (Button) findViewById(R.id.btn_to_register);
        infotv = (TextView) findViewById(R.id.info);

        // 设置按钮监听器
        LogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u_id = (EditText) findViewById(R.id.edt_id);
                password = (EditText) findViewById(R.id.edt_pw);
                //String uid,p;
                userid=u_id.getText().toString();
                p=password.getText().toString();
                if ((userid != null) && (p!= null)) {
                    new Thread(new MyThread()).start();
                } else{
                    u_id.setText("");
                    password.setText("");
                }
            }
        });
        toRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(Login.this, Register.class);
                startActivity(regIntent);
            }
        });

    }

    // 子线程接收数据，主线程修改数据
    public class MyThread implements Runnable {
        @Override
        public void run() {
                info = WebService.LoginExecuteHttpGet("LoginServlet",u_id.getText().toString(), password.getText().toString());
//                System.out.print(info);
                if (info==null || info=="服务器连接超时..."||info.indexOf("账号或密码不正确")>0){
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            infotv.setText(info);
//                        }
//                    });
//                    u_id.setText("");
//                    password.setText("");
                    Intent logIntent = new Intent(Login.this, Login.class);
                    startActivity(logIntent);
                }else {
                    Intent logIntent = new Intent(Login.this, Main.class);
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
                    logIntent.putExtra("userid",userid);
                    logIntent.putExtra("postdataid",postdataid);
                    logIntent.putExtra("postdatatheme",postdatatheme);
                    startActivity(logIntent);
                }
            }
        }
}
