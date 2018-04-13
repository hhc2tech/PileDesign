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
 * Created by HP on 10/20/2017.
 */

public class QpVesicsMethod extends Fragment {
    private Spinner spPileType, spSoilType;
    private EditText etLength,etCrossSection,etGama,etPhi,etCu,etNqs;
    private Button btnCompute;
    private TextView tvResult;
    private double length,d,gama,phi,cu,nqs;
    private double ap,Qpl,Qp;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.qp_vesics_method,container,false);
        spPileType=(Spinner) myView.findViewById(R.id.spPileType);
        spSoilType=(Spinner) myView.findViewById(R.id.spSoilType);
        etLength=(EditText) myView.findViewById(R.id.etLength);
        etCrossSection=(EditText) myView.findViewById(R.id.etCrossSection);
        etGama=(EditText) myView.findViewById(R.id.etGama);
        etPhi=(EditText) myView.findViewById(R.id.etPhi);
        etCu=(EditText) myView.findViewById(R.id.etCu);
        etNqs=(EditText) myView.findViewById(R.id.etNqs);
        btnCompute=(Button) myView.findViewById(R.id.btnCompute);
        tvResult=(TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Vesic's Method for Qp");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try{
                    String stLength=etLength.getText().toString();
                    String stCrossSection=etCrossSection.getText().toString();
                    String stGama=etGama.getText().toString();
                    String stPhi=etPhi.getText().toString();
                    String stCu=etCu.getText().toString();
                    String stNqs=etNqs.getText().toString();
                    length=Double.parseDouble(stLength);
                    d=Double.parseDouble(stCrossSection);
                    gama=Double.parseDouble(stGama);
                    phi=Double.parseDouble(stPhi);
                    cu=Double.parseDouble(stCu);
                    nqs=Double.parseDouble(stNqs);

                    //Calculation of Ap
                    String pileType = spPileType.getSelectedItem().toString();
                    switch (pileType){
                        case "Circular":
                            ap=(Math.PI)*d*d/4;
                            break;
                        case "Square":
                            ap=d*d;
                            break;
                        default:
                            Toast.makeText(getActivity(), "Pile type not properly selected!", Toast.LENGTH_SHORT).show();
                    }

                    //Calculation of Qp
                    String soilType = spSoilType.getSelectedItem().toString();
                    switch (soilType){
                        case "Sand":
                            //Calculation of limiting pile capacity
                            Qp=ap*nqs*gama*length*(1+2*(1-Math.sin((Math.PI/180)*phi)))/3;
                            Qp=Math.floor(Qp*100.0)/100.0;
                            if (cu!=0){
                                etCu.setText("0");
                                Toast.makeText(getActivity(), "Cu is zero for the selected soil type!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Clay":
                            Qp=ap*nqs*cu;
                            Qp=Math.floor(Qp*100.0)/100.0;
                            if (phi!=0){
                                etPhi.setText("0");
                                Toast.makeText(getActivity(), "Phi is zero for the selected soil type!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            Toast.makeText(getActivity(), "Soil type not properly selected!", Toast.LENGTH_SHORT).show();
                    }

                    //Code to display the result
                    tvResult.setText("The ultimate point resistance of the pile is = "+Qp+" kN");
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Incomplete Field or Input Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myView;
    }
}
