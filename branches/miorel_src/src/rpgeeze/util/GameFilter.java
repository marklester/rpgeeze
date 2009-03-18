package rpgeeze.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class GameFilter extends FileFilter {
       

    //Accept all directories and all xml
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }

        String extension = getExtension(file);
        if (extension != null) {
            if (extension.equals("xml")) {
                    return true;
            } else {
                return false;
            }
        }
        System.out.println(extension);
        return false;
    }
    public String getDescription() {
        return "xml";
    }
       
    public static String getExtension(File file) {
        String extension = null;
        String string = file.getName();
        int i = string.lastIndexOf('.');
        if (i > 0 &&  i < string.length() - 1)
            extension = string.substring(i+1).toLowerCase();
        return extension;
    }


}


