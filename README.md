# Seizure
A lightweight android library to save any view as image in desired location in device.

Seizure helps the developer to quit scratching their heads for storing a view as image in local storage.

Steps:
\n
1.Get runtime permission for storage for marshmallow or higher \n
2.Create an object of Seizure_Acitivity and pass the required parameters. \n
3.and yell 'Done'!\n

Required parameters:\n

1.Context,\n
2.View - view which is to be saved as image(ImageView,frameLayout,Button,Texview etc..)\n
3.Filename - name of image\n
4.Path - path where to save file\n
5.Format of image:\n
           Following formats of images available:\n
           1.FILEFORMAT.JPEG_FORMAT
           2.FILEFORMAT.PNG_FORMAT
           3.FILEFORMAT.WEBP_FORMAT\n
6.Quality of image (must be between 0-100)
           \n
#Deployment

repositories {\n
    maven 
    {\n
           url 'https://dl.bintray.com/qureshiayaz29/Seizure'
    \n}
}

#Dependency

dependencies {
    compile 'seizure.ayaz.inside.seizureproject:seizure:1.0'
}
