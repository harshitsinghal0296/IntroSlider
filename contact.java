package info.androidhive.introslider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class contact extends AppCompatActivity {

    Button btn;
    EditText edt;
    private ProgressDialog pd;
    public static final String url="https://api.textlocal.in/send/?username=kapil98arora@gmail.com&hash=b2793d6769330e8269b4544d0800df07f188f631526bc434a853fb6955ae3b9c&sender=IMSTRY&numbers=919982764402&message=Dear customer Thank You To Write Us We Will Get Back To You Soon Till Then Stay Happy Stay Healthy And Stay Relax";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        edt=(EditText)findViewById(R.id.phone_number);
        btn=(Button)findViewById(R.id.button_start_verification);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt.getText().toString().trim().equals("")) {
                    edt.setError("Email is Required");
                } else
                {
                    String n=edt.getText().toString();
               //     pd = ProgressDialog.show(getParent(), "", "Verifying User", true, false);
                    final JSONObject emptyJsonObject = new JSONObject();
                    Toast.makeText(getApplicationContext(), "User verified", Toast.LENGTH_SHORT).show();
                    Log.d("hello","error");
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url,emptyJsonObject, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        VolleyLog.v("Response:%n %s", response.toString(4));
                                        Log.d("Response:%n %s", response.toString(4));
                                        int OtpresponseFlag = response.getInt("status");
                 //                       pd.dismiss();
                                        if(OtpresponseFlag == 1)
                                        {
                                            Toast.makeText(getApplicationContext(), "User verified", Toast.LENGTH_SHORT).show();
                                          //  getActivity().startActivity(new Intent(getActivity(),MainActivity.class));
                                           // getActivity().finish();
                                            //overridePendingTransition( R.anim.lefttoright, R.anim.stable );
                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(), "OTP Verification Failed ! Try Again", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(pd.isShowing())
                            {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "OTP Verification Failed ! Try Again", Toast.LENGTH_SHORT).show();
                            }
                            VolleyLog.e("Error: ", error.getMessage());
                        }
                    });
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(req);

                }
            }
        });
    }
}
