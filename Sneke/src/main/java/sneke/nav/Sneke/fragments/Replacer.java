package sneke.nav.Sneke.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sneke.nav.Sneke.R;

public class Replacer extends Fragment {
    private static final String TAG = "Replacer";
    public Replacer() {


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.grid,container,false);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }


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


}

