package sneke.nav.Sneke.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import sneke.nav.Sneke.R;
import sneke.nav.Sneke.fragments.Replacer;
import sneke.nav.Sneke.fragments.web;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private ImageButton searchButton, menu;
    private EditText searchBar;
    private Replacer grid;
    private static LinearLayout searchViewGroup;
    private String baseUrl = "https://developer.android.com/guide/topics/search/search-dialog#jav";
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
        grid = new Replacer();
        searchViewGroup=findViewById(R.id.cluster);
        searchBar = findViewById(R.id.searchBar);
        searchButton = findViewById(R.id.searchButton);
        Log.i(TAG, "onCreate: " + R.id.webView);
        menu = findViewById(R.id.menu);
        viewList.add(searchBar);
        viewList.add(searchButton);
        viewList.add(menu);

        getSupportFragmentManager().beginTransaction().add(R.id.frame, grid).commit();
        set();
        /**
         * A native method that is implemented by the 'native-lib' native library,
         * which is packaged with this application.
         */

    }

    /*Helper methods*/


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
//        Log.i(TAG, String.valueOf(main.getView().getId() == R.id.grid));
        if (searchText.length() > 0) {

            if (findViewById(R.id.webView) == null) {
                web w =
                        web.newInstance("https://www.google.co.in/search?q=" + searchText);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, w).commit();
//                Log.i(TAG, String.valueOf(w.getView()==findViewById(R.id.webView)));

//                webView.loadUrl(searchText);
            } else
                web.search("https://www.google.co.in/search?q=" + searchText);


        }

    }


    public static View getClusterView(){
        return searchViewGroup;
    }


}
