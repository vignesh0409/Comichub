package ajiet.ise.dept.comichub;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Comics extends AppCompatActivity {
    PDFView pdfView;
    String link="",productlist="",product="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);

        pdfView =findViewById(R.id.pdfv);
        if (isConnected())
        {
            Toast.makeText(getApplicationContext(),"Internet Connected",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"No Internet Connected",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder=new AlertDialog.Builder(Comics.this);
            builder.setTitle("Not Internet Connection Alert")
                    .setMessage("Go to Settings?")
                    .setCancelable(false)
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"Go to Homepage",Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog dialog=builder
                    .create();
            dialog.show();
        }

        link=getIntent().getStringExtra("title");
        product=getIntent().getStringExtra("link");
        productlist=getIntent().getStringExtra("productlist");


        Bundle bundle=getIntent().getExtras();
        if (bundle!=null)
        {
            link=getIntent().getStringExtra("link");

        }
        new  Comics.RetrivePDFStream().execute(link);
    }

    class RetrivePDFStream extends AsyncTask<String,Void, InputStream>
    {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            try {
                URL urlx=new URL(strings[0]);
                HttpURLConnection urlConnection= (HttpURLConnection) urlx.openConnection();
                if(urlConnection.getResponseCode()==200)
                {
                    inputStream=new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException ex)
            {
                return null;
            }
            return inputStream;
            } protected void onPreExecute()
    {
        progressDialog = new ProgressDialog(Comics.this);
        progressDialog.setTitle("Getting The Content:)");
        progressDialog.setMessage("Loading.....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


    }
        protected void onPostExecute(InputStream inputStream)
        {

            pdfView.fromStream(inputStream).load();
            progressDialog.dismiss();
        }
        }
    ProgressDialog progressDialog;



public boolean isConnected(){
        boolean connected=Boolean.parseBoolean("false");
        try {
            ConnectivityManager cm=  (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ninfo=cm.getActiveNetworkInfo();
            connected=ninfo!=null&&ninfo.isAvailable()&&ninfo.isConnected();
            return connected;
        }
        catch (Exception e)
        {
            Log.e("Connectivity Exception",e.getMessage());
        }
        return connected;
}
public boolean onOptionsItemSelected(MenuItem item)
{
    if (item.getItemId()== android.R.id.home)
    {
        onBackPressed();
        return true;
    }
    return false;
}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Comics.this,MainActivity.class);
        startActivity(intent);
    }

}