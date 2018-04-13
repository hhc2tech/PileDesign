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

public class QpCoyleandCastellosMethod extends Fragment {
    private Spinner spPileType;
    private EditText etLength, etCrossSection, etGama, etNqs;
    private Button btnCompute;
    private TextView tvResult;
    private double length, d, gama;
    private double nqs, ap, Qp;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.qp_coyleandcastellos_method, container, false);
        spPileType = (Spinner) myView.findViewById(R.id.spPileType);
        etLength = (EditText) myView.findViewById(R.id.etLength);
        etCrossSection = (EditText) myView.findViewById(R.id.etCrossSection);
        etGama = (EditText) myView.findViewById(R.id.etGama);
        etNqs = (EditText) myView.findViewById(R.id.etNqs);
        btnCompute = (Button) myView.findViewById(R.id.btnCompute);
        tvResult = (TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Coyle and Castello's Method for Qp");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try {
                    String stLength = etLength.getText().toString();
                    String stCrossSection = etCrossSection.getText().toString();
                    String stGama = etGama.getText().toString();
                    String stNqs = etNqs.getText().toString();
                    length = Double.parseDouble(stLength);
                    d = Double.parseDouble(stCrossSection);
                    gama = Double.parseDouble(stGama);
                    nqs = Double.parseDouble(stNqs);

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
                    Qp = ap * (gama * length * nqs);
                    Qp = Math.floor(Qp * 100.0) / 100.0;

                    //Code to display the result
                    tvResult.setText("The ultimate point resistance of the pile is = " + Qp + " kN");
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Incomplete Field or Input Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myView;
    }
}
