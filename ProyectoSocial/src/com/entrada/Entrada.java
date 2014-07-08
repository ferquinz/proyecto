package com.entrada;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


@SuppressLint("NewApi")
public class Entrada extends Activity implements OnClickListener{

	
	private Button entrar;
	
	 public void onCreate(Bundle savedInstanceState) { 
		 super.onCreate(savedInstanceState); 
		 setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		 setContentView(R.layout.entrada); 
		 	         
		 registerListeners();

	 } 
	 
	 
	 private void registerListeners() {
	 
		 entrar = (Button) findViewById(R.id.entrar); 
		 entrar.setOnClickListener(this);
	
	}

	public void onClick(View view) {
		int id = ((Button) view).getId();
		
	   	 if(id == this.entrar.getId()){
	   			ProgressDialog pd = ProgressDialog.show(Entrada.this,"Actualizando","Cargando",true,true);
	   			startActivity(new Intent(this, Login.class));
	   			pd.dismiss();
	   	 }		
	}
	
}
