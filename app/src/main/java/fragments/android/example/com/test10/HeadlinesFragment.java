package fragments.android.example.com.test10;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeadlinesFragment extends Fragment implements View.OnClickListener {

    OnMenuSelectionListener mListener;

    public HeadlinesFragment() {
        // Required empty public constructor
    }

    public static HeadlinesFragment newInstance(int index) {
        HeadlinesFragment f = new HeadlinesFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnMenuSelectionListener) {
            mListener = (OnMenuSelectionListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet HeadlinesFragment.OnMenuSelectionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Check","Headlines onCreateView");
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);

        TextView menu1 = (TextView) view.findViewById(R.id.menu1);
        TextView menu2 = (TextView) view.findViewById(R.id.menu2);
        TextView menu3 = (TextView) view.findViewById(R.id.menu3);
        TextView menu4 = (TextView) view.findViewById(R.id.menu4);
        TextView menu5 = (TextView) view.findViewById(R.id.menu5);

        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);
        menu3.setOnClickListener(this);
        menu4.setOnClickListener(this);
        menu5.setOnClickListener(this);

        return  view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu1 : mListener.OnMenuSelected(1); break;
            case R.id.menu2 : mListener.OnMenuSelected(2); break;
            case R.id.menu3 : mListener.OnMenuSelected(3); break;
            case R.id.menu4 : mListener.OnMenuSelected(4); break;
            case R.id.menu5 : mListener.OnMenuSelected(5); break;
            default:
        }
    }

    public interface OnMenuSelectionListener {
        void OnMenuSelected(int menuId);
    }
}
