package fragments.android.example.com.test10;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    private int cMenuId;

    public ArticleFragment() {
        // Required empty public constructor
    }

    public static ArticleFragment newInstance(int menuId) {
        ArticleFragment f = new ArticleFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(MainActivity.KEY_CURR_MENU, menuId);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments()!=null) {
            cMenuId = getArguments().getInt(MainActivity.KEY_CURR_MENU);
        }

        Log.i("Check", "Article Current Menu= " + cMenuId);

        setRetainInstance(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        if (cMenuId > 0) {
            setText(view);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }



    private void setText(View view) {
        TextView textView1 = (TextView) view.findViewById(R.id.TextView1);

        String str = null;
        switch (cMenuId) {
            case 1 : str = "You select menu "+cMenuId; break;
            case 2 : str = "You select menu "+cMenuId; break;
            case 3 : str = "You select menu "+cMenuId; break;
            case 4 : str = "You select menu "+cMenuId; break;
            case 5 : str = "You select menu "+cMenuId; break;
            default:
        }

        textView1.setText(str);
    }
}
