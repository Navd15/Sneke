package sneke.nav.Sneke;

import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.WrapperListAdapter;

import java.util.ArrayList;

import sneke.nav.Sneke.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "";
    private ImageButton searchButton, menu;
    private EditText searchBar;
    private Replacer main;
    private String baseUrl = "https://developer.android.com/guide/topics/search/search-dialog#jav";
    private WebView webView;
    private View.OnClickListener listener;
    private ArrayList<View> viewList;

    // Used to load the 'native-lib' library on application startup.

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewList = new ArrayList<>();
        listener = this;
        main =new Replacer();
        searchBar = findViewById(R.id.searchBar);
        searchButton = findViewById(R.id.searchButton);

        menu = findViewById(R.id.menu);
        viewList.add(searchBar);
        viewList.add(searchButton);
        viewList.add(menu);
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.webView,main);
        set();
        /**
         * A native method that is implemented by the 'native-lib' native library,
         * which is packaged with this application.
         */

    }

    /*Helper methods*/

    /*Start fragment according to imageView clicked*/

    public void ImageClickListener(View v) {
        switch ((String) v.getTag()) {
            case "bookmarks":
                Log.i(TAG, "ImageClickListener: bookmarks");
                break;
            case "downloads":
                break;
            case "history":
                break;
            case "settings":

                break;

            default:
                /* Dont know*/
        }

    }

    /*Sets click listener*/
    private void set() {
        for (View i : viewList) {
            i.setOnClickListener(listener);
        }
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick: " + "called");
        switch ((String) v.getTag()) {
            case "search_button":
                startWebView();
                break;
        }
    }

    private void startWebView() {
        String searchText = searchBar.getText().toString();
        if (searchText.length() > 0) {
            if(findViewById(R.id.webView)==null){
                Log.i(TAG, "startWebView: Yas");
                getSupportFragmentManager().beginTransaction().replace(R.id.webView,main).commit();
//                webView = findViewById(R.id.webView);
            }
//            webView.loadUrl(baseUrl + searchText);
//            Log.i(TAG, "startWebView: " + webView.getUrl());
        }

    }


}
