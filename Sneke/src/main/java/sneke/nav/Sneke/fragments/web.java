package sneke.nav.Sneke.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import sneke.nav.Sneke.R;
import sneke.nav.Sneke.activities.MainActivity;

public class web extends Fragment implements GestureDetector.OnGestureListener {
    public static WebView webView;
    private static final String TAG = "web";
    private GestureDetectorCompat detectorCompat;
    private boolean translatedOnce = false;
    private View cluster = MainActivity.getClusterView();

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
        detectorCompat = new GestureDetectorCompat(getActivity(), this);

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
        wv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detectorCompat.onTouchEvent(event);
            }
        });
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


    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (velocityY < 0 && velocityY < -2000) {
            if (!translatedOnce) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        cluster.animate().translationY(120).setDuration(50).setInterpolator(new DecelerateInterpolator());
                        translatedOnce = true;
                    }
                });

            }

        } else if (velocityY > 0) {
            if (translatedOnce) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        cluster.animate().translationY(0).setDuration(50).setInterpolator(new DecelerateInterpolator());
                        translatedOnce = false;
                    }
                });

            }
        }

        return false;
    }
}
