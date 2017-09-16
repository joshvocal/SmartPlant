package me.joshvocal.smartplant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mMoistureListView;
    private MoistureAdapter mMoistureAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDeviceDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDeviceDatabaseReference = mFirebaseDatabase.getReference().child("moistures");

        mMoistureListView = findViewById(R.id.moistureListView);

        List<Integer> moistureList = new ArrayList<>();
        mMoistureAdapter = new MoistureAdapter(this, R.layout.item_device, moistureList);
        mMoistureListView.setAdapter(mMoistureAdapter);

        if (mChildEventListener == null) {

            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    long moisture = (long) dataSnapshot.getValue();
//                    Toast.makeText(MainActivity.this, Long.toString(moisture), Toast.LENGTH_SHORT).show();
                    mMoistureAdapter.add((int) moisture);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
        }

        mDeviceDatabaseReference.addChildEventListener(mChildEventListener);

    }
}
