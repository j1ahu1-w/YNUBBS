package ynu.com.ynubbs;

import ynu.com.ynubbs.Web.WebService;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.SyncFailedException;


public class Register  extends AppCompatActivity {

    Button toLogBtn, RegBtn;
    EditText u_id,u_name, password,passwordc;
    private String info;
    private TextView infotv;
    private ProgressDialog dialog;
    private static Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 获取控件
        toLogBtn = (Button) findViewById(R.id.btn_to_login);
        RegBtn = (Button) findViewById(R.id.btn_register);
        infotv = (TextView) findViewById(R.id.info);

        // 设置按钮监听器
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u_id=(EditText) findViewById(R.id.edt_id);
                u_name = (EditText) findViewById(R.id.edt_name);
                password = (EditText) findViewById(R.id.edt_pw);
                passwordc = (EditText) findViewById(R.id.edt_pwc);
                String id,u,p,pc;
                id=u_id.getText().toString();
                u=u_name.getText().toString();
                p=password.getText().toString();
                pc=passwordc.getText().toString();
                if ((id!=null)&& ( u!=null) && (p!=null)&& (p.equals(pc))) {
                    System.out.println("connecting!---------------step1");
                    new Thread(new MyThread()).start();
                    System.out.println("connecting!---------------step2");
                }else{
                    info="请确认您的信息正确";
                    u_id.setText("");
                    u_name.setText("");
                    password.setText("");
                    passwordc.setText("");
                }

            }
        });
        toLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logIntent = new Intent(Register.this, Login.class);
                startActivity(logIntent);
            }
        });

    }

    // 子线程接收数据，主线程修改数据
    public class MyThread implements Runnable {
        @Override
        public void run() {
            info = WebService.RegisterExecuteHttpGet("RegisterServlet", Integer.valueOf(u_id.getText().toString()), u_name.getText().toString(),password.getText().toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    infotv.setText(info);
                }
            });
        }
    }
}
