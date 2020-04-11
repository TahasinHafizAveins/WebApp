package com.stratasoft.stratasoft;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webview;
    private int backKeyPressCounter = 0;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = findViewById(R.id.web_view);
       /* wv_superlog.setWebViewClient(new WebViewClient());
        wv_superlog.loadUrl("http://superlog.ashepashe.com/");*/


        try{
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url)
                {
                    try{
                        view.loadUrl(url); //this is controversial - see comments and other answers
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    return true;
                }
            });

            webview.clearCache(true);
            webview.clearHistory();
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webview.getSettings().setDomStorageEnabled(true);
            //webview.loadUrl("http://superlog.ashepashe.com");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume(){
        super.onResume();
        webview.reload();
    }

    @Override
    public void onBackPressed() {
        /*if(webview.copyBackForwardList().getCurrentIndex()>0){
            webview.goBack();
        }else{
            super.onBackPressed();
        }*/

        backKeyPressCounter++;
        if (backKeyPressCounter == 1) {
            Toast.makeText(getApplicationContext(), R.string.press_again_to_close_the_app, Toast.LENGTH_LONG).show();
        } else if (backKeyPressCounter >= 2) {
            moveTaskToBack(true);
            System.exit(1);
        }
    }
}
