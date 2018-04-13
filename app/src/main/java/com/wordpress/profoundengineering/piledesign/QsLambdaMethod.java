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

public class QsLambdaMethod extends Fragment {
    private Spinner spPileType, spLayers;
    private EditText etCrossSection,etLambda,etL1,etL2,etL3,etCu1,etCu2,etCu3,etGama1,etGama2,etGama3;
    private Button btnCompute;
    private TextView tvResult;
    private double d,lambda,l,l1,l2,l3,cu,cu1,cu2,cu3,gama1,gama2,gama3;
    private double p,a1,a2,a3,sigma,fav,Qs;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.qs_lambda_method,container,false);
        spPileType=(Spinner) myView.findViewById(R.id.spPileType);
        spLayers=(Spinner) myView.findViewById(R.id.spNoOfSoilLayers);
        etCrossSection=(EditText) myView.findViewById(R.id.etCrossSection);
        etLambda=(EditText) myView.findViewById(R.id.etLambda);
        etL1=(EditText) myView.findViewById(R.id.etL1);
        etL2=(EditText) myView.findViewById(R.id.etL2);
        etL3=(EditText) myView.findViewById(R.id.etL3);
        etCu1=(EditText) myView.findViewById(R.id.etCu1);
        etCu2=(EditText) myView.findViewById(R.id.etCu2);
        etCu3=(EditText) myView.findViewById(R.id.etCu3);
        etGama1=(EditText) myView.findViewById(R.id.etGama1);
        etGama2=(EditText) myView.findViewById(R.id.etGama2);
        etGama3=(EditText) myView.findViewById(R.id.etGama3);
        btnCompute=(Button) myView.findViewById(R.id.btnCompute);
        tvResult=(TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Lambda Method for Qs");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try{
                    String stCrossSection=etCrossSection.getText().toString();
                    String stLambda=etLambda.getText().toString();
                    String stL1=etL1.getText().toString();
                    String stL2=etL2.getText().toString();
                    String stL3=etL3.getText().toString();
                    String stCu1=etCu1.getText().toString();
                    String stCu2=etCu2.getText().toString();
                    String stCu3=etCu3.getText().toString();
                    String stGama1=etGama1.getText().toString();
                    String stGama2=etGama2.getText().toString();
                    String stGama3=etGama3.getText().toString();
                    d=Double.parseDouble(stCrossSection);
                    lambda=Double.parseDouble(stLambda);

                    //Calculation of As
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
                            gama1=Double.parseDouble(stGama1);
                            //Calculation of lambda
                            if (lambda==0) {
                                l=l1;
                                lambda = 0.00000000002264448293*l*l*l*l*l*l - 0.00000000714785865996*l*l*l*l*l +
                                        0.00000090171999144340*l*l*l*l - 0.00005838181010708880*l*l*l+ 0.00208262028681006000*l*l -
                                        0.04095091938670950000*l + 0.49843663876857400000;
                                if(l<0|l>90) {
                                    Toast.makeText(getActivity(), "The given value of Cu is out of range. You can anyway "+
                                            "proceed if you want.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            //Calculation of Qs
                            sigma=0.5*gama1*l;
                            fav=lambda*(sigma+2*cu1);
                            Qs=p*fav*l;
                            Qs=Math.floor(Qs*100.0)/100.0;
                            break;
                        case "Two":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            l2=Double.parseDouble(stL2);
                            cu1=Double.parseDouble(stCu1);
                            cu2=Double.parseDouble(stCu2);
                            gama1=Double.parseDouble(stGama1);
                            gama2=Double.parseDouble(stGama2);
                            //Calculation of lambda
                            l=l1+l2;
                            lambda = 0.00000000002264448293*l*l*l*l*l*l - 0.00000000714785865996*l*l*l*l*l +
                                    0.00000090171999144340*l*l*l*l - 0.00005838181010708880*l*l*l+ 0.00208262028681006000*l*l -
                                    0.04095091938670950000*l + 0.49843663876857400000;
                            if(l<0|l>90) {
                                Toast.makeText(getActivity(), "The given value of Cu is out of range. You can anyway "+
                                        "proceed if you want.", Toast.LENGTH_SHORT).show();
                            }
                            //Calculation of Qs
                            cu=(cu1*l1+cu2*l2)/l;
                            a1=0.5*gama1*l1*l1;
                            a2=0.5*(2*gama1*l1+gama2*l2)*l2;
                            sigma=(a1+a2)/l;
                            fav=lambda*(sigma+2*cu);
                            Qs=p*fav*l;
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
                            gama1=Double.parseDouble(stGama1);
                            gama2=Double.parseDouble(stGama2);
                            gama3=Double.parseDouble(stGama3);
                            //Calculation of lambda
                            l=l1+l2+l3;
                            lambda = 0.00000000002264448293*l*l*l*l*l*l - 0.00000000714785865996*l*l*l*l*l +
                                    0.00000090171999144340*l*l*l*l - 0.00005838181010708880*l*l*l+ 0.00208262028681006000*l*l -
                                    0.04095091938670950000*l + 0.49843663876857400000;
                            if(l<0|l>90) {
                                Toast.makeText(getActivity(), "The given value of Cu is out of range. You can anyway "+
                                        "proceed if you want.", Toast.LENGTH_SHORT).show();
                            }
                            //Calculation of Qs
                            cu=(cu1*l1+cu2*l2+cu3*l3)/l;
                            a1=0.5*gama1*l1*l1;
                            a2=0.5*(2*gama1*l1+gama2*l2)*l2;
                            a3=0.5*(2*(gama1*l1+gama2*l2)+gama3*l3)*l3;
                            sigma=(a1+a2+a3)/l;
                            fav=lambda*(sigma+2*cu);
                            Qs=p*fav*l;
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
