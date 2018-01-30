# Seizure
A lightweight android library to save any view as image in desired location in device.

Seizure helps the developer to quit scratching their heads for storing a view as image in local storage.

Steps:

1.Get runtime permission for storage for marshmallow or higher 
2.Create an object of Seizure_Acitivity and pass the required parameters.
3.and yell 'Done'!

Required parameters:

1.Context,
2.View - view which is to be saved as image(ImageView,frameLayout,Button,Texview etc..)
3.Filename - name of image
4.Path - path where to save file
5.Format of image:
           Following formats of images available:
           1.FILEFORMAT.JPEG_FORMAT
           2.FILEFORMAT.PNG_FORMAT
           3.FILEFORMAT.WEBP_FORMAT
6.Quality of image (must be between 0-100)
           
#Deployment

repositories {
    maven 
    {
           url 'https://dl.bintray.com/qureshiayaz29/Seizure'
    }
}

#Dependency

dependencies {
    compile 'seizure.ayaz.inside.seizureproject:seizure:1.0'
}
