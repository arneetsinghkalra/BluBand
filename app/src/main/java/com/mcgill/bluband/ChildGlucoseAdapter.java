package com.mcgill.bluband;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

public class ChildGlucoseAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<String> name;
    private List<String> childId;
    private List<Integer> glucose;
    //Map<Integer, Integer> BGLevel;

    public ChildGlucoseAdapter(Context c, DataSnapshot dataSnapshot) {
        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
            Child aChild = snapshot.getValue(Child.class);
            String aName = aChild.getName();
            name.add(aName);
            String aChildId = dataSnapshot.getValue(String.class);
            childId.add(aChildId);
            //TODO: add BGlevel with timestamp
            //-----temp for BG level
            int aGlucose = aChild.getGlucose();
            glucose.add(aGlucose);
            mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @Override
    public int getCount() {
        return childId.size();
    }

    @Override
    public Object getItem(int position) {
        return name.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.layout_home, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView idTextView = (TextView) v.findViewById(R.id.idTextView);
        TextView glucoseTextView = (TextView) v.findViewById(R.id.glucoseTextView);

        nameTextView.setText(name.get(position));
        idTextView.setText(childId.get(position));
        glucoseTextView.setText(glucose.get(position).toString() + "mmol/L");

        return v;
    }
}
