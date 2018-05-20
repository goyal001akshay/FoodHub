package com.androidtutorialpoint.googlemapsretrofit;

/**
 * Created by kayo on 14/4/17.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ListScientistsFragment extends Fragment {
    private final String TAG = "ListScientistsFragment";
    private String[] scientistNames = {"Kathi Roll","Chicken-Do-Plaza","Fritata","Chicken Lasagna","Chicken Biryani","Tomato Soup"};
    private String[] birthYear = {"Price Rs. ","Price Rs. ","Price Rs. ","Price Rs. ","Price Rs. ","Price Rs. "};
    private String[] deathYear = {"80","250","450","300","220","150"};
    private int[] image = {R.drawable.ff,R.drawable.ff,R.drawable.ff,R.drawable.ff,R.drawable.ff,R.drawable.ff};
    private ArrayList<Scientist> mScientists;
    private RecyclerView mScientistRecyclerView;
    private ScientistAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mScientists = new ArrayList<>();
        for(int i =0;i<scientistNames.length;i++){
            Scientist s = new Scientist();
            s.setName(scientistNames[i]);
            s.setBirthYear(birthYear[i]);
            s.setDeathYear(deathYear[i]);
            s.setImageId(image[i]);
            mScientists.add(s);
        }
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listscientist, container, false);
        mScientistRecyclerView = (RecyclerView) view
                .findViewById(R.id.scientist_recycler_view);
        mScientistRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mScientistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    private void updateUI(){
        mAdapter = new ScientistAdapter(mScientists);
        mScientistRecyclerView.setAdapter(mAdapter);
    }
    private class ScientistHolder extends RecyclerView.ViewHolder{
        private Scientist mScientist;
        public ImageView mImageView;
        public TextView mNameTextView;
        public TextView mBirthDeathTextView;
        public ScientistHolder(View itemView){
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mNameTextView = (TextView) itemView.findViewById(R.id.textview_name);
            mBirthDeathTextView = (TextView) itemView.findViewById(R.id.textview_birth_death);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),
                            mScientist.getName() + " ordered and placed. Please collect the order in 15 min!", Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
        public void bindData(Scientist s){
            mScientist = s;
            mImageView.setImageResource(s.getImageId());
            mNameTextView.setText(s.getName());
            mBirthDeathTextView.setText(s.getBirthYear()+"-"+s.getDeathYear());
        }
    }
    private class ScientistAdapter extends RecyclerView.Adapter<ScientistHolder>{
        private ArrayList<Scientist> mScientists;
        public ScientistAdapter(ArrayList<Scientist> Scientists){
            mScientists = Scientists;
        }
        @Override
        public ScientistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.category_list_item_1,parent,false);
            return new ScientistHolder(view);
        }
        @Override
        public void onBindViewHolder(ScientistHolder holder, int position) {
            Scientist s = mScientists.get(position);
            holder.bindData(s);
        }
        @Override
        public int getItemCount() {
            return mScientists.size();
        }
    }
}
