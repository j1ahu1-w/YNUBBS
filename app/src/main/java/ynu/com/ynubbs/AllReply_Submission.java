package ynu.com.ynubbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ynu.com.ynubbs.Entity.Post;
import ynu.com.ynubbs.Entity.Reply;
import ynu.com.ynubbs.Web.WebService;

public class AllReply_Submission extends AppCompatActivity {
    private String r_to_p_id,r_to_class,r_to_id,r_content,r_time,r_user;
    private EditText rcET;
    private Button Submission;
    private ArrayList<Post> posts = new ArrayList<Post>();
    private ArrayList<Reply> replys = new ArrayList<Reply>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reply_submission);
        Intent disIntent = getIntent();
        final String userid = disIntent.getStringExtra("userid");
        final String pid = disIntent.getStringExtra("pid");
        final String rclass = disIntent.getStringExtra("rclass");
        rcET=(EditText)findViewById(R.id.edt_reply_to_note) ;
        Submission=(Button) findViewById(R.id.btn_reply_to_note) ;
        Submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new MyThread1()).start();
            }
        });
    }
    public class MyThread1 implements Runnable {
        @Override
        public void run() {
            Intent disIntent = getIntent();
            final String userid = disIntent.getStringExtra("userid");
            final String pid = disIntent.getStringExtra("pid");
            final String rtoid = disIntent.getStringExtra("rtoid");
            final String rclass = disIntent.getStringExtra("rclass");
            r_to_p_id=pid;
            r_to_class=rclass;
            r_to_id=rtoid;
            r_content=rcET.getText().toString();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
            Date date = new Date(System.currentTimeMillis());
            r_time=simpleDateFormat.format(date);
            r_user=userid;
            WebService.RInsertExecuteHttpGet("InsertReplyServlet",r_to_p_id,r_to_class,r_to_id,r_content,r_time,r_user);
            Intent intent = new Intent(AllReply_Submission.this, AllPost_Display.class);
            posts = WebService.SelectSingleExecuteHttpGet("SelectSingleServlet",pid);
            replys = WebService.SelectReplyExecuteHttpGet("SelectReplyServlet",pid);
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
            System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            startActivity(intent);
        }

    }
}
