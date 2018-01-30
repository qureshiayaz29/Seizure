package seizure.ayaz.inside;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by AYAZ on 29-Jan-18.
 */

public class Seizure_activity
{
    View view_img=null;
    Context ctx=null;
    String filename;
    String path;
    String fileformat;
    int quality;
    public Seizure_activity(Context context, View view, String filename, String path, String fileformat, int quality)
    {
        ctx=context;
        view_img=view;
        this.filename=filename;
        this.path=path;
        this.fileformat=fileformat;
        this.quality=quality;
    }
    public boolean save_IMAGE()
    {
        try {
            if (view_img == null || path == null) {
                throw new Exception();
            }
            view_img.setDrawingCacheEnabled(true);
            view_img.buildDrawingCache();

            Bitmap cache = view_img.getDrawingCache();

            return storeImage(cache, filename, path, fileformat, quality);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private boolean storeImage(Bitmap image, String filename, String path, String fileformat, int quality) throws Exception
    {
            File pictureFile = getOutputMediaFile(path,filename,fileformat);

        if (pictureFile == null)
        {
            Log.d("IMAGE CLASS:","Error creating media file, check storage permissions: ");// e.getMessage());
            return false;
        }
        try {
            Bitmap.CompressFormat c = null;
            if (fileformat.equals(FILEFORMAT.PNG_FORMAT)) {
                c = Bitmap.CompressFormat.PNG;
            } else if (fileformat.equals(FILEFORMAT.JPEG_FORMAT))
            {
                c = Bitmap.CompressFormat.JPEG;
            } else if (fileformat.equals(FILEFORMAT.WBEP_FORMAT))
            {
                c = Bitmap.CompressFormat.WEBP;
            }



            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(c, quality, fos);
            fos.close();

            if(fileformat.equals(FILEFORMAT.PNG_FORMAT))
            {
                MediaScannerConnection.scanFile(ctx, new String[] { pictureFile.getPath() }, new String[] { "image/png" }, null);
            }
            else if(fileformat.equals(FILEFORMAT.JPEG_FORMAT))
            {
                MediaScannerConnection.scanFile(ctx, new String[] { pictureFile.getPath() }, new String[] { "image/jpeg" }, null);
            }
            else if(fileformat.equals(FILEFORMAT.WBEP_FORMAT))
            {
                MediaScannerConnection.scanFile(ctx, new String[] { pictureFile.getPath() }, new String[] { "image/webp" }, null);
            }
            view_img.destroyDrawingCache();
            return true;
        } catch (FileNotFoundException e) {
            Log.d("IMAGE", "File not found: " + e.getMessage());
            return false;

        } catch (IOException e) {
            Log.d("IMAGE", "Error accessing file: " + e.getMessage());
            return false;

        }
    }
    private File getOutputMediaFile(String path,String filename,String fileformat) throws Exception
    {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(path);

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }

        // Create a media file name
        File mediaFile;
        String mImageName=filename +fileformat;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }
}
