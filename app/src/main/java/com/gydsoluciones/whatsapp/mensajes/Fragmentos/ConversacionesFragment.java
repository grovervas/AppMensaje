package com.gydsoluciones.whatsapp.mensajes.Fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gydsoluciones.whatsapp.mensajes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversacionesFragment extends Fragment {


    public ConversacionesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversaciones, container, false);
    }

}
