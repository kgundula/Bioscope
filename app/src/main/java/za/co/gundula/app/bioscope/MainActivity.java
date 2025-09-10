package za.co.gundula.app.bioscope;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import za.co.gundula.app.bioscope.model.BioScope;

/**
 * The main activity of the application.
 *
 * This activity displays the main screen, which can be a single-pane or two-pane layout,
 * depending on the device's screen size.
 */
public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener, BioScopeDetailsFragment.OnFragmentInteractionListener {

    private boolean mTwoPane;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.view_bioscope_details_container) != null) {

            mTwoPane = true;
            if (savedInstanceState == null) {

                BioScopeDetailsFragment fragment = new BioScopeDetailsFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.view_bioscope_details_container, new BioScopeDetailsFragment())
                        .commit();
            }

        } else {
            mTwoPane = false;
        }
        //MainFragment mainFragment = ((MainFragment)getSupportFragmentManager().findFragmentById(R.id.movie_fragment));


    }

    /**
     * Returns true if the device is a tablet, false otherwise.
     *
     * @return true if the device is a tablet, false otherwise.
     */
    public boolean isTablet() {
        return mTwoPane;
    }

    /**
     * Replaces the current fragment with a new BioScopeDetailsFragment.
     *
     * @param bioScope The BioScope object to display in the fragment.
     */
    public void replaceFragment(BioScope bioScope) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(BioScopeDetailsActivity.bioscope_object, bioScope);
        arguments.putBoolean(BioScopeDetailsActivity.two_pane, mTwoPane);

        BioScopeDetailsFragment fragment = new BioScopeDetailsFragment();
        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_bioscope_details_container, fragment)
                .commit();

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Initialize the contents of the Activity's standard options menu.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed; if you return false it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     * @param uri The Uri to describe the interaction.
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * Sets the subtitle of the action bar.
     *
     * @param subTitle The subtitle to set.
     */
    public void setSubTitle(String subTitle) {
        if (subTitle != null)
            getSupportActionBar().setSubtitle(subTitle);
    }
}
