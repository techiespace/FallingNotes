package com.techiespace.projects.fallingnotes;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        // 6. Finally, replace the AndroidLauncher activity content with the Libgdx Fragment.
        GameFragment fragment = new GameFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.libgdx_content, fragment);
        trans.commit();

//		initialize(new FallingNotesGame(), config);
    }

    @Override
    public void exit() {

    }

    public static class GameFragment extends AndroidFragmentApplication {
        // 5. Add the initializeForView() code in the Fragment's onCreateView method.
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return initializeForView(new FallingNotesGame("CScale.mid"));
        }
	}
}
																																																																																																																																																																																																																																																																																																																																																	