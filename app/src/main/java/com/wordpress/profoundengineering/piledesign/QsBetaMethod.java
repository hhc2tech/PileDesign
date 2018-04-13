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

public class QsBetaMethod extends Fragment {
    private Spinner spPileType, spLayers;
    private EditText etCrossSection,etL1,etL2,etL3,etPhir1,etPhir2,etPhir3,etGama1,etGama2,etGama3,etOCR1,etOCR2,etOCR3;
    private Button btnCompute;
    private TextView tvResult;
    private double d,l1,l2,l3,phir1,phir2,phir3,gama1,gama2,gama3,ocr1,ocr2,ocr3;
    private double p,beta1,beta2,beta3,cupa1,cupa2,cupa3,f1,f2,f3,Qs;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.qs_beta_method,container,false);
        spPileType=(Spinner) myView.findViewById(R.id.spPileType);
        spLayers=(Spinner) myView.findViewById(R.id.spNoOfSoilLayers);
        etCrossSection=(EditText) myView.findViewById(R.id.etCrossSection);
        etL1=(EditText) myView.findViewById(R.id.etL1);
        etL2=(EditText) myView.findViewById(R.id.etL2);
        etL3=(EditText) myView.findViewById(R.id.etL3);
        etPhir1=(EditText) myView.findViewById(R.id.etPhir1);
        etPhir2=(EditText) myView.findViewById(R.id.etPhir2);
        etPhir3=(EditText) myView.findViewById(R.id.etPhir3);
        etGama1=(EditText) myView.findViewById(R.id.etGama1);
        etGama2=(EditText) myView.findViewById(R.id.etGama2);
        etGama3=(EditText) myView.findViewById(R.id.etGama3);
        etOCR1=(EditText) myView.findViewById(R.id.etOCR1);
        etOCR2=(EditText) myView.findViewById(R.id.etOCR2);
        etOCR3=(EditText) myView.findViewById(R.id.etOCR3);
        btnCompute=(Button) myView.findViewById(R.id.btnCompute);
        tvResult=(TextView) myView.findViewById(R.id.tvResult);
        getActivity().setTitle("Beta Method for Qs");

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try{
                    String stCrossSection=etCrossSection.getText().toString();
                    String stL1=etL1.getText().toString();
                    String stL2=etL2.getText().toString();
                    String stL3=etL3.getText().toString();
                    String stPhir1=etPhir1.getText().toString();
                    String stPhir2=etPhir2.getText().toString();
                    String stPhir3=etPhir3.getText().toString();
                    String stGama1=etGama1.getText().toString();
                    String stGama2=etGama2.getText().toString();
                    String stGama3=etGama3.getText().toString();
                    String stOCR1=etOCR1.getText().toString();
                    String stOCR2=etOCR2.getText().toString();
                    String stOCR3=etOCR3.getText().toString();
                    d=Double.parseDouble(stCrossSection);

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
                            phir1=Double.parseDouble(stPhir1);
                            gama1=Double.parseDouble(stGama1);
                            ocr1=Double.parseDouble(stOCR1);

                            //Calculation of beta
                            beta1=Math.sqrt(ocr1)*(1-Math.sin((Math.PI/180)*phir1))*Math.tan((Math.PI/180)*phir1);

                            //Calculation of Qs
                            f1=beta1*l1*gama1/2;
                            Qs=p*f1*l1;
                            Qs=Math.floor(Qs*100.0)/100.0;
                            break;
                        case "Two":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            l2=Double.parseDouble(stL2);
                            phir1=Double.parseDouble(stPhir1);
                            phir2=Double.parseDouble(stPhir2);
                            gama1=Double.parseDouble(stGama1);
                            gama2=Double.parseDouble(stGama2);
                            ocr1=Double.parseDouble(stOCR1);
                            ocr2=Double.parseDouble(stOCR2);

                            //Calculation of beta
                            beta1=Math.sqrt(ocr1)*(1-Math.sin((Math.PI/180)*phir1))*Math.tan((Math.PI/180)*phir1);
                            beta2=Math.sqrt(ocr2)*(1-Math.sin((Math.PI/180)*phir2))*Math.tan((Math.PI/180)*phir2);

                            //Calculation of Qs
                            f1=beta1*l1*gama1/2;
                            f2=beta2*0.5*(2*gama1*l1+gama2*l2);
                            Qs=p*f1*l1+p*f2*l2;
                            Qs=Math.floor(Qs*100.0)/100.0;
                            break;
                        case "Three":
                            //Getting inputs
                            l1=Double.parseDouble(stL1);
                            l2=Double.parseDouble(stL2);
                            l3=Double.parseDouble(stL3);
                            phir1=Double.parseDouble(stPhir1);
                            phir2=Double.parseDouble(stPhir2);
                            phir3=Double.parseDouble(stPhir3);
                            gama1=Double.parseDouble(stGama1);
                            gama2=Double.parseDouble(stGama2);
                            gama3=Double.parseDouble(stGama3);
                            ocr1=Double.parseDouble(stOCR1);
                            ocr2=Double.parseDouble(stOCR2);
                            ocr3=Double.parseDouble(stOCR3);

                            //Calculation of beta
                            beta1=Math.sqrt(ocr1)*(1-Math.sin((Math.PI/180)*phir1))*Math.tan((Math.PI/180)*phir1);
                            beta2=Math.sqrt(ocr2)*(1-Math.sin((Math.PI/180)*phir2))*Math.tan((Math.PI/180)*phir2);
                            beta3=Math.sqrt(ocr3)*(1-Math.sin((Math.PI/180)*phir3))*Math.tan((Math.PI/180)*phir3);

                            //Calculation of Qs
                            f1=beta1*l1*gama1/2;
                            f2=beta2*0.5*(2*gama1*l1+gama2*l2);
                            f3=beta3*0.5*(2*(gama1*l1+gama2*l2)+gama3*l3);
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
