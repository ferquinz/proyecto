package com.entrada;

import com.registro.Registro1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements OnClickListener{

	private Button login;
	private Button register;
	private EditText user;
	private EditText pass;
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.login); 
		registerListeners();
	}

	private void registerListeners() {
			
		login = (Button) findViewById(R.id.login); 
		login.setOnClickListener(this);

		register = (Button) findViewById(R.id.register); 
		register.setOnClickListener(this);
	 		
		user = (EditText) findViewById(R.id.userText);
		pass = (EditText) findViewById(R.id.passText);
	}
	
	@Override
	public void onClick(View view) {
		int id = ((Button) view).getId();
		
		if(id == this.login.getId()){
			if (Comprobar()){
				
			}
	   	 }
	   	 
	   	 if(id == this.register.getId()){
	   		ProgressDialog pd = ProgressDialog.show(Login.this,"Actualizando","Cargando",true,true);
	   		startActivity(new Intent(this, Registro1.class));
	   		pd.dismiss();
	   	 }	
	}
	
	/* Funcion para comprobar los campos Login y Pass */
	private boolean Comprobar(){
		if (!user.getText().toString().trim().equals("")){
			if(!pass.getText().toString().trim().equals("")){
				return true;
			}
			else{
				new AlertDialog.Builder(this).setTitle("Error").setMessage("Contraseña Incorrecto")
				.setNeutralButton("Reintentar", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {}
				}).show();
			}
		}
		else{
			new AlertDialog.Builder(this).setTitle("Error").setMessage("Login Incorrecto")
			.setNeutralButton("Reintentar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {}
			}).show();
		}	
		return false;	
	}
		
}
