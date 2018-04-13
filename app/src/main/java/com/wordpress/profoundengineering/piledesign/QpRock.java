package com.wordpress.profoundengineering.piledesign;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HP on 11/6/2017.
 */

public class QpRock extends Fragment {
    private Spinner spPileType;
    private EditText etCrossSection,etUCS,etPhi,etFS;
    private Button btnCompute;
    private TextView tvResult;
    private double d,ucs,phi,fs;
    private double nphi, ap, Qall;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.qp_coyleandcastellos_method, container, false);
        spPileType = (Spinner) myView.findViewById(R.id.spPileType);
        etCrossSection = (EditText) myView.findViewById(R.id.etCrossSection);
        etUCS = (EditText) myView.findViewById(R.id.etUCS);
        etPhi = (EditText) myView.findViewById(R.id.etPhi);
        etFS = (EditText) myView.findViewById(R.id.etFS);
        btnCompute = (Button) myView.findViewById(R.id.btnCompute);
        tvResult = (TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Qp for Rock");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try {
                    String stCrossSection = etCrossSection.getText().toString();
                    String stUCS = etUCS.getText().toString();
                    String stPhi = etPhi.getText().toString();
                    String stFS = etFS.getText().toString();
                    d = Double.parseDouble(stCrossSection);
                    ucs = Double.parseDouble(stUCS);
                    phi = Double.parseDouble(stPhi);
                    fs = Double.parseDouble(stFS);

                    //Calculation of Ap
                    String pileType = spPileType.getSelectedItem().toString();
                    switch (pileType) {
                        case "Circular":
                            ap = (Math.PI) * d * d / 4;
                            break;
                        case "Square":
                            ap = d * d;
                            break;
                        default:
                            Toast.makeText(getActivity(), "Pile type not properly selected!", Toast.LENGTH_SHORT).show();
                    }

                    //Calculation of Qp
                    nphi=Math.tan((Math.PI/180)*phi)*Math.tan((Math.PI/180)*phi);
                    Qall = ap*(ucs/5)*(nphi+1)/fs;
                    Qall = Math.floor(Qall * 100.0) / 100.0;

                    //Code to display the result
                    tvResult.setText("The allowable point resistance of the pile is = " + Qall + " kN");
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Incomplete Field or Input Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myView;
    }
}
