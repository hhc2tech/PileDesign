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

public class QsAlphaMethod extends Fragment {
    private Spinner spPileType, spLayers;
    private EditText etCrossSection, etL1,etL2,etL3,etCu1,etCu2,etCu3,etAlpha1,etAlpha2,etAlpha3;
    private Button btnCompute;
    private TextView tvResult;
    private double d,l1,l2,l3,cu1,cu2,cu3;
    private double p,alpha1,alpha2,alpha3,cupa1,cupa2,cupa3,f1,f2,f3,Qs;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.qs_alpha_method,container,false);
        spPileType=(Spinner) myView.findViewById(R.id.spPileType);
        spLayers=(Spinner) myView.findViewById(R.id.spNoOfSoilLayers);
        etCrossSection=(EditText) myView.findViewById(R.id.etCrossSection);
        etL1=(EditText) myView.findViewById(R.id.etL1);
        etL2=(EditText) myView.findViewById(R.id.etL2);
        etL3=(EditText) myView.findViewById(R.id.etL3);
        etCu1=(EditText) myView.findViewById(R.id.etCu1);
        etCu2=(EditText) myView.findViewById(R.id.etCu2);
        etCu3=(EditText) myView.findViewById(R.id.etCu3);
        etAlpha1=(EditText) myView.findViewById(R.id.etAlpha1);
        etAlpha2=(EditText) myView.findViewById(R.id.etAlpha2);
        etAlpha3=(EditText) myView.findViewById(R.id.etAlpha3);
        btnCompute=(Button) myView.findViewById(R.id.btnCompute);
        tvResult=(TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Alpha Method for Qs");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try{
                    String stCrossSection=etCrossSection.getText().toString();
                    String stL1=etL1.getText().toString();
                    String stL2=etL2.getText().toString();
                    String stL3=etL3.getText().toString();
                    String stCu1=etCu1.getText().toString();
                    String stCu2=etCu2.getText().toString();
                    String stCu3=etCu3.getText().toString();
                    String stAlpha1 = etAlpha1.getText().toString();
                    String stAlpha2 = etAlpha2.getText().toString();
                    String stAlpha3 = etAlpha3.getText().toString();
                    d=Double.parseDouble(stCrossSection);

                    //Calculation of p
                    String pileType = spPileType.getSelectedItem().toString();
                    switch (pileType){
                        case "Circular":
                            p=(Math.PI)*d;
                            break;
                        case "Square":
                            p=4*d;
                            break;
                        default:
                            Toast.makeText(getActivity(), "Pile type not properly selected!", Toast.LENGTH_SHORT).show();
                    }

                    String layers = spLayers.getSelectedItem().toString();
                    switch (layers) {
                        case "One":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            cu1=Double.parseDouble(stCu1);
                            alpha1=Double.parseDouble(stAlpha1);
                            //Calculation of alpha
                            if (alpha1==0) {
                                cupa1=cu1/100;
                                alpha1 = 0.0260395901709103*cupa1*cupa1*cupa1*cupa1 - 0.2232913451157*cupa1*cupa1*cupa1 +
                                        0.749751276250752*cupa1*cupa1 - 1.19831136814912*cupa1 + 1.11874566279433;
                                if(cupa1<0.1) {
                                    alpha1 = 1;
                                }
                                else if (cupa1>2.8)
                                {
                                    Toast.makeText(getActivity(), "The given value of Cu is out of range. You can anyway "+
                                            "proceed if you want.", Toast.LENGTH_SHORT).show();
                                    alpha1 = 0.34;
                                }
                            }
                            //Calculation of Qs
                            f1=alpha1*cu1;
                            Qs=p*f1*l1;
                            Qs=Math.floor(Qs*100.0)/100.0;
                            break;
                        case "Two":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            l2=Double.parseDouble(stL2);
                            cu1=Double.parseDouble(stCu1);
                            cu2=Double.parseDouble(stCu2);
                            alpha1=Double.parseDouble(stAlpha1);
                            alpha2=Double.parseDouble(stAlpha2);
                            //Calculation of alpha
                            if (alpha1==0) {
                                cupa1=cu1/100;
                                alpha1 = 0.0260395901709103*cupa1*cupa1*cupa1*cupa1 - 0.2232913451157*cupa1*cupa1*cupa1 +
                                        0.749751276250752*cupa1*cupa1 - 1.19831136814912*cupa1 + 1.11874566279433;
                                if(cupa1<0.1) {
                                    alpha1 = 1;
                                }
                                else if (cupa1>2.8)
                                {
                                    Toast.makeText(getActivity(), "The given value of Cu1 is out of range. You can anyway "+
                                            "proceed if you want.", Toast.LENGTH_SHORT).show();
                                    alpha1 = 0.34;
                                }
                            }
                            if (alpha2==0) {
                                cupa2=cu2/100;
                                alpha2 = 0.0260395901709103*cupa2*cupa2*cupa2*cupa2 - 0.2232913451157*cupa2*cupa2*cupa2 +
                                        0.749751276250752*cupa2*cupa2 - 1.19831136814912*cupa2 + 1.11874566279433;
                                if(cupa2<0.1) {
                                    alpha2 = 1;
                                }
                                else if (cupa2>2.8)
                                {
                                    Toast.makeText(getActivity(), "The given value of Cu2 is out of range. You can anyway "+
                                            "proceed if you want.", Toast.LENGTH_SHORT).show();
                                    alpha2 = 0.34;
                                }
                            }
                            //Calculation of Qs
                            f1=alpha1*cu1;
                            f2=alpha2*cu2;
                            Qs=p*f1*l1+p*f2*l2;
                            Qs=Math.floor(Qs*100.0)/100.0;
                            break;
                        case "Three":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            l2=Double.parseDouble(stL2);
                            l3=Double.parseDouble(stL3);
                            cu1=Double.parseDouble(stCu1);
                            cu2=Double.parseDouble(stCu2);
                            cu3=Double.parseDouble(stCu3);
                            alpha1=Double.parseDouble(stAlpha1);
                            alpha2=Double.parseDouble(stAlpha2);
                            alpha3=Double.parseDouble(stAlpha3);
                            //Calculation of alpha
                            if (alpha1==0) {
                                cupa1=cu1/100;
                                alpha1 = 0.0260395901709103*cupa1*cupa1*cupa1*cupa1 - 0.2232913451157*cupa1*cupa1*cupa1 +
                                        0.749751276250752*cupa1*cupa1 - 1.19831136814912*cupa1 + 1.11874566279433;
                                if(cupa1<0.1) {
                                    alpha1 = 1;
                                }
                                else if (cupa1>2.8)
                                {
                                    Toast.makeText(getActivity(), "The given value of Cu1 is out of range. You can anyway "+
                                            "proceed if you want.", Toast.LENGTH_SHORT).show();
                                    alpha1 = 0.34;
                                }
                            }
                            if (alpha2==0) {
                                cupa2=cu2/100;
                                alpha2 = 0.0260395901709103*cupa2*cupa2*cupa2*cupa2 - 0.2232913451157*cupa2*cupa2*cupa2 +
                                        0.749751276250752*cupa2*cupa2 - 1.19831136814912*cupa2 + 1.11874566279433;
                                if(cupa2<0.1) {
                                    alpha2 = 1;
                                }
                                else if (cupa2>2.8)
                                {
                                    Toast.makeText(getActivity(), "The given value of Cu2 is out of range. You can anyway "+
                                            "proceed if you want.", Toast.LENGTH_SHORT).show();
                                    alpha2 = 0.34;
                                }
                            }
                            if (alpha3==0) {
                                cupa3=cu3/100;
                                alpha3 = 0.0260395901709103*cupa3*cupa3*cupa3*cupa3 - 0.2232913451157*cupa3*cupa3*cupa3 +
                                        0.749751276250752*cupa3*cupa3 - 1.19831136814912*cupa3 + 1.11874566279433;
                                if(cupa3<0.1) {
                                    alpha3 = 1;
                                }
                                else if (cupa3>2.8)
                                {
                                    Toast.makeText(getActivity(), "The given value of Cu2 is out of range. You can anyway "+
                                            "proceed if you want.", Toast.LENGTH_SHORT).show();
                                    alpha3 = 0.34;
                                }
                            }
                            //Calculation of Qs
                            f1=alpha1*cu1;
                            f2=alpha2*cu2;
                            f3=alpha3*cu3;
                            Qs=p*f1*l1+p*f2*l2+p*f3*l3;
                            Qs=Math.floor(Qs*100.0)/100.0;
                            break;
                        default:
                            Toast.makeText(getActivity(), "Number of soil layers not properly selected!", Toast.LENGTH_SHORT).show();
                    }

                    //Code to display the result
                    tvResult.setText("The ultimate friction resistance of the pile is = "+Qs+" kN");
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Incomplete Field or Input Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myView;
    }
}
