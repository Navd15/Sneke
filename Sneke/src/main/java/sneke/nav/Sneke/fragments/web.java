package sneke.nav.Sneke.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import sneke.nav.Sneke.R;
import sneke.nav.Sneke.activities.MainActivity;

public class web extends Fragment {
    public static WebView webView;
    private static final String TAG = "web";
    private String path;

    public static web newInstance(String path) {
        Bundle args = new Bundle();
        args.putString("url", path);
        web fragment = new web();
        fragment.setArguments(args);
        return fragment;
    }

    public web() {
//       path= getArguments().getString("url");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.webview, container, false);
        webView = rootView.findViewById(R.id.webView);
        setWebView(webView);
        webView.loadUrl(getArguments().getString("url"));
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public static void search(String path) {

        if (webView != null) {
            webView.loadUrl(path);
        }

    }

    private void setWebView(final WebView wv) {
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        wv.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
                        if (wv.canGoBack()) {
                            wv.goBack();
                        }
                }

                return true;
            }
        });
        wv.getSettings().setJavaScriptEnabled(true);
//        final View  cluster=MainActivity.getClusterView();
//
//wv.setOnTouchListener(new View.OnTouchListener() {
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//
//        return true;
//    }
//});
    }


}
