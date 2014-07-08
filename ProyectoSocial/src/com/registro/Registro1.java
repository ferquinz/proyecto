package com.registro;

import com.entrada.R;

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

public class Registro1 extends Activity implements OnClickListener {

	private Button siguiente;
	private EditText telefono;
	private EditText usuario;
	private EditText nick;
	private EditText email;
	private EditText pass;
	private EditText confirmpass;
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
 		setContentView(R.layout.registro1); 
 		registerListeners();
	}
	
	private void registerListeners() {
		
		siguiente = (Button) findViewById(R.id.r1siguiente); 
		siguiente.setOnClickListener(this);
	 		
		telefono = (EditText) findViewById(R.id.r1telefono);
		usuario = (EditText) findViewById(R.id.r1usuario);
		nick = (EditText) findViewById(R.id.r1nick);
		email = (EditText) findViewById(R.id.r1email);
		pass = (EditText) findViewById(R.id.r1pass);
		confirmpass = (EditText) findViewById(R.id.r1repeatpass);
	
	}
	
	@Override
	public void onClick(View view) {
		int id = ((Button) view).getId();
		
	   	 if(id == this.siguiente.getId()){
	   		if (Comprobar()){
	   			ProgressDialog pd = ProgressDialog.show(Registro1.this,"Actualizando","Cargando",true,true);
   				startActivity(new Intent(this, Registro2.class));
	   			pd.dismiss();
	   		} 
	   	 }	
	}
	
	/* Comprobamos que los campos no esten vacios primero */
	private boolean Comprobar(){
		
		if (!telefono.getText().toString().trim().equals("")){
			if (!usuario.getText().toString().trim().equals("")){
				if (!nick.getText().toString().trim().equals("")){
					if (!email.getText().toString().trim().equals("")){
						if(!pass.getText().toString().trim().equals("")){
							if(pass.getText().toString().trim().equals(confirmpass.getText().toString())){
								return true;
							}
							else{
								new AlertDialog.Builder(this).setTitle("Error").setMessage("Contraseñas distintas")
								.setNeutralButton("Reintentar", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {}
								}).show();
							}
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
						new AlertDialog.Builder(this).setTitle("Error").setMessage("Email Incorrecto")
						.setNeutralButton("Reintentar", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {}
						}).show();
					}
				}
				else{
					new AlertDialog.Builder(this).setTitle("Error").setMessage("Nick Incorrecto")
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
		}
		else{
			new AlertDialog.Builder(this).setTitle("Error").setMessage("Telefono Incorrecto")
			.setNeutralButton("Reintentar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {}
			}).show();	
		}
		
		return false;	
	}

}
