package com.cpets.sp.asynctaskexample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button submit;
    EditText mobilenumber,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobilenumber=(EditText)findViewById(R.id.mobilenumber);
        password=(EditText)findViewById(R.id.password);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobilenumber.getText().toString().isEmpty() && password.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Enter Perfectly", Toast.LENGTH_SHORT).show();
                }else {
                    LoginAsyncTask runner = new LoginAsyncTask();
                    runner.execute();
                }
            }
        });
    }

    private class LoginAsyncTask extends AsyncTask<String, String, String> {

        private String mobNo, passWord;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait", "loading...");
            mobNo = mobilenumber.getText().toString();
            passWord = password.getText().toString();
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject obj = null;
            try {
                obj = new JSONObject();
                obj.accumulate("mobileNo", mobNo);
                obj.accumulate("password", passWord);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String content = AsyncTaskHelper.makeServiceCall(" http://www.cpetsol.com/pp/userRest/profileHome", "POST", obj);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            try {
                JSONArray mainArray = new JSONArray(result);
                JSONObject obj1 = mainArray.getJSONObject(0);
                if (obj1.getString("Status").equalsIgnoreCase("Success")) {
                    Toast.makeText(LoginActivity.this, "Login Succesfully Done.", Toast.LENGTH_LONG).show();
                } else if (obj1.getString("Status").equalsIgnoreCase("Error")) {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//onPostExecute
    }//LoginAsyncTask class
}
