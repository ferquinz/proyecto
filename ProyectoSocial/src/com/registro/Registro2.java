package com.registro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.SecureRandom;

import com.entrada.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class Registro2 extends Activity implements OnClickListener {

	private static final int CAMERA_REQUEST = 1888; 

    static String str_Camera_Photo_ImagePath = "";
    private static File f;
    private static int Take_Photo = 2;
    private static String str_randomnumber = "";
    static String str_Camera_Photo_ImageName = "";
    public static String str_SaveFolderName;
    private static File wallpaperDirectory;
    Bitmap bitmap;
    int storeposition = 0;
    public static GridView gridview;
    public static ImageView imagen1;
    
	private Button siguiente;
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
 		setContentView(R.layout.registro2); 
 		registerListeners();
	}
	
	private void registerListeners() {
		
		siguiente = (Button) findViewById(R.id.r2siguiente); 
		siguiente.setOnClickListener(this);
		
		//this.imageView = (ImageView)this.findViewById(R.id.r2añadir);
		imagen1 = (ImageView) findViewById(R.id.r2adjuntar);
		imagen1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	str_SaveFolderName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/rajeshsample";
                str_randomnumber = String.valueOf(nextSessionId());
                wallpaperDirectory = new File(str_SaveFolderName);
                if (!wallpaperDirectory.exists())
                    wallpaperDirectory.mkdirs();
                str_Camera_Photo_ImageName = str_randomnumber + ".jpg";
                str_Camera_Photo_ImagePath = str_SaveFolderName + "/" + str_randomnumber + ".jpg";
                System.err.println(" str_Camera_Photo_ImagePath  " + str_Camera_Photo_ImagePath);
                f = new File(str_Camera_Photo_ImagePath);
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f)),Take_Photo);
                System.err.println("f  " + f);
            }
        });
	}
	

	@Override
	public void onClick(View view) {
		int id = ((Button) view).getId();
		
	   	 if(id == this.siguiente.getId()){
	   		//if (Comprobar()){
	   			/*ProgressDialog pd = ProgressDialog.show(RegistroActivity.this,"Actualizando","Cargando",true,true);
	   			if(newAccount())
	   				startActivity(new Intent(this, InicioActivity.class));
	   			pd.dismiss();*/
	   		//} 
	   	 }
	   	 
	   	
    }



	// used to create randon numbers
	public String nextSessionId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		if (requestCode == Take_Photo) {
		    String filePath = null;
		
		    filePath = str_Camera_Photo_ImagePath;
		    if (filePath != null) {
		        Bitmap faceView = ( new_decode(new File(
		                        filePath))); // ========================> good
		                                        // lines
		        imagen1.setImageBitmap(faceView);
		
		    } else {
		        bitmap = null;
		    }
		}
	} 

	public static Bitmap new_decode(File f) {
	
		// decode image size
	
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		o.inDither = false; // Disable Dithering mode
	
		o.inPurgeable = true; // Tell to gc that whether it needs free memory,
								// the Bitmap can be cleared
	
		o.inInputShareable = true; // Which kind of reference will be used to
	                            // recover the Bitmap data after being
	                            // clear, when it will be used in the future
		try {
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		// Find the correct scale value. It should be the power of 2.
		final int REQUIRED_SIZE = 300;
		int width_tmp = o.outWidth, height_tmp = o.outHeight;
		int scale = 1;
		while (true) {
		    if (width_tmp / 1.5 < REQUIRED_SIZE && height_tmp / 1.5 < REQUIRED_SIZE)
		        break;
		    width_tmp /= 1.5;
		    height_tmp /= 1.5;
		    scale *= 1.5;
		}
	
		// decode with inSampleSize
		BitmapFactory.Options o2 = new BitmapFactory.Options();
		// o2.inSampleSize=scale;
		o.inDither = false; // Disable Dithering mode
		
		o.inPurgeable = true; // Tell to gc that whether it needs free memory,
		                        // the Bitmap can be cleared
		
		o.inInputShareable = true; // Which kind of reference will be used to
		                            // recover the Bitmap data after being
		                            // clear, when it will be used in the future
		// return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		try {
		
		//  return BitmapFactory.decodeStream(new FileInputStream(f), null,
		//          null);
		    Bitmap bitmap= BitmapFactory.decodeStream(new FileInputStream(f), null, null);
		    System.out.println(" IW " + width_tmp);
		    System.out.println("IHH " + height_tmp);           
		       int iW = width_tmp;
		        int iH = height_tmp;
		
		       return Bitmap.createScaledBitmap(bitmap, iW, iH, true);
		
		} catch (OutOfMemoryError e) {
		    // TODO: handle exception
		    e.printStackTrace();
		    // clearCache();
		
		    // System.out.println("bitmap creating success");
		    System.gc();
		    return null;
		    // System.runFinalization();
		    // Runtime.getRuntime().gc();
		    // System.gc();
		    // decodeFile(f);
		} catch (FileNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    return null;
		}
	}
}

