package fragments.android.example.com.test10;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements HeadlinesFragment.OnMenuSelectionListener {

    private static final String TAG_HEADLINES_FRAGMENT  = "HeadlinesFragment";
    private static final String TAG_ARTICLE_FRAGMENT    = "ArticleFragment";
    private static final String KEY_PRE_NUM_PANES       = "PreNumPanes";
    public  static final String KEY_CURR_MENU           = "CurrMenu";
    private boolean mTwoPane;
    private int cMenuId;
    HeadlinesFragment headlinesFragment;
    ArticleFragment articleFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.i("Check","BackStack Count= "+fragmentManager.getBackStackEntryCount());
        if (fragmentManager.getBackStackEntryCount()>0) {
            fragmentManager.popBackStackImmediate();
        }


        Log.i("Check","BackStack Count= "+fragmentManager.getBackStackEntryCount());

        int numPanes = 0;

        if (savedInstanceState!=null) {
            cMenuId = savedInstanceState.getInt(KEY_CURR_MENU);
            numPanes = savedInstanceState.getInt(KEY_PRE_NUM_PANES);

        }

        headlinesFragment = (HeadlinesFragment) fragmentManager.findFragmentByTag(TAG_HEADLINES_FRAGMENT);
        articleFragment = (ArticleFragment) fragmentManager.findFragmentByTag(TAG_ARTICLE_FRAGMENT);

        mTwoPane = findViewById(R.id.article_fragment_container_twopane)!=null;

        if (mTwoPane) {
            // Now is Landscape mode
            if (numPanes!=2) {
                // Before is Portrait mode
                if (headlinesFragment!=null) {
                    fragmentManager.beginTransaction()
                            .remove(headlinesFragment)
                            .commit();
                    fragmentManager.executePendingTransactions();
                } else {
                    headlinesFragment = new HeadlinesFragment();
                }

                if (articleFragment!=null) {
                    fragmentManager.beginTransaction()
                            .remove(articleFragment)
                            .commit();
                    fragmentManager.executePendingTransactions();
                } else {
                    articleFragment = new ArticleFragment();
                }

                fragmentManager.beginTransaction()
                        .add(R.id.headlines_fragment_container, headlinesFragment, TAG_HEADLINES_FRAGMENT)
                        .commit();
                fragmentManager.executePendingTransactions();

                fragmentManager.beginTransaction()
                        .add(R.id.article_fragment_container_twopane, articleFragment.newInstance(cMenuId), TAG_ARTICLE_FRAGMENT)
                        .commit();
                fragmentManager.executePendingTransactions();

            } else {
                //
            }

        } else {
            // Now is Portrait mode

            if (numPanes!=1) {
                // Before is Landscape mode
                if (headlinesFragment!=null) {
                    fragmentManager.beginTransaction()
                            .remove(headlinesFragment)
                            .commit();
                    fragmentManager.executePendingTransactions();
                } else {
                    headlinesFragment = new HeadlinesFragment();
                }

                fragmentManager.beginTransaction()
                        .add(R.id.main_fragment_container, headlinesFragment, TAG_HEADLINES_FRAGMENT)
                        .commit();
                fragmentManager.executePendingTransactions();

                if (articleFragment!=null) {
                    fragmentManager.beginTransaction()
                            .remove(articleFragment)
                            .commit();
                    fragmentManager.executePendingTransactions();
                } else {
                    articleFragment = new ArticleFragment();
                }


            } else {
                //
            }


        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_PRE_NUM_PANES, mTwoPane? 2 : 1);
        outState.putInt(KEY_CURR_MENU, cMenuId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mTwoPane) {
            //Toast.makeText(getApplicationContext(), "Back Button pressed.", Toast.LENGTH_SHORT).show();

        } else {


        }
    }

    @Override
    public void OnMenuSelected(int menuId) {
        cMenuId = menuId;
        DisplayArticleFragment();

    }

    void DisplayArticleFragment() {
        Log.i("Check","DisplayArticleFragment="+cMenuId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (mTwoPane) {
            fragmentManager.beginTransaction()
                    .replace(R.id.article_fragment_container_twopane, articleFragment.newInstance(cMenuId), TAG_ARTICLE_FRAGMENT)
                    .commit();
            fragmentManager.executePendingTransactions();


        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, articleFragment.newInstance(cMenuId), TAG_ARTICLE_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
            fragmentManager.executePendingTransactions();

        }
    }
}
