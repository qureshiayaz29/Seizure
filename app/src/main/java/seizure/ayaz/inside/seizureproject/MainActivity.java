package seizure.ayaz.inside.seizureproject;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockApplication;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import seizure.ayaz.inside.FILEFORMAT;
import seizure.ayaz.inside.Seizure_activity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    FrameLayout frameLayout;
    Button button;
    private static int REQUEST=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout=findViewById(R.id.frameLayout);
        button=findViewById(R.id.button);

        checkPermission();
        setRandomGradient();
        showinfoDialog();

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRandomGradient();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(REQUEST==1)
                    proceed();
                else
                    Toast.makeText(MainActivity.this,"permission required",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showinfoDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        break;

                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Tap the box to change colour").setPositiveButton("Okay", dialogClickListener).show();
    }

    private void checkPermission() {

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Permission Granted" , Toast.LENGTH_SHORT).show();
                REQUEST=1;
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                REQUEST=0;
            }
        };

        TedPermission.with(MainActivity.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not save image.\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();

    }

    private void proceed() {
        File sd = Environment.getExternalStorageDirectory();
        String path = sd.getAbsolutePath() + "/pqrst/";

        /**
         *
         *
         * Create object of class Seizure_activity and use it to call save_IMAGE()
         * The save_IMAGE return true if image is successfully saved and false for failed operation
         *
         *
         */

        //create object
        Seizure_activity seizure_activity=new Seizure_activity(MainActivity.this,frameLayout,"ayaz123",path, FILEFORMAT.WBEP_FORMAT,100);

        //save Image
        Boolean b=seizure_activity.save_IMAGE();
        Toast.makeText(MainActivity.this,b.toString(),Toast.LENGTH_SHORT).show();

    }

    private void setRandomGradient() {
        int[] colors = new int[2];
        colors[0] = getRandomColor();
        colors[1] = getRandomColor();

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors);

        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        //gd.setGradientRadius(300f);
        gd.setCornerRadius(0f);
        frameLayout.setBackground(gd);

    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//        return Color.argb(166,255,255,255);
    }
}
