package com.engappsados.engappsadosapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebas on 8/15/2017.
 */

public class Apps_Tab extends Fragment implements View.OnClickListener {

    private ListView lvApps;
    private AdapterAppItem adapter;
    private List<AppModelo> aplicaciones;
    public DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.apps_tab, container, false);
        lvApps = (ListView) rootView.findViewById(R.id.listview_apps);
        aplicaciones = new ArrayList<>();
        final Context elContexto = rootView.getContext();

        mDatabaseRef.child("aplicaciones").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fillApps(dataSnapshot);
                adapter = new AdapterAppItem(aplicaciones, rootView.getContext());
                lvApps.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            //puede ser que tenga que hacer un onDataChange por todos
            public void fillApps(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String nombre = child.child("nombre").getValue().toString();
                    String descripcion = child.child("descripcion").getValue().toString();
                    String link = child.child("link").getValue().toString();
                    String img = child.child("imagen").getValue().toString();
                    AppModelo nuevaApp = new AppModelo(nombre, descripcion, img, link);
                    nuevaApp.setTitle(nombre);
                    if (!aplicaciones.contains(nuevaApp)) {
                        aplicaciones.add(nuevaApp);
                    }


                }
            }
        });

        return rootView;
    }


    @Override
    public void onClick(View v) {

    }
}

