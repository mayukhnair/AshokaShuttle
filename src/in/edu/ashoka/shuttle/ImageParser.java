/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.edu.ashoka.shuttle;

import java.awt.Image;
import javax.swing.ImageIcon;
import in.edu.ashoka.shuttle.ShuttleApp;

/**
 *
 * @author Mayukh Nair
 */
public class ImageParser {
    public ImageIcon getScaledImage(String imagepath, int labelwidth, int labelheight){
        ShuttleApp shut=new ShuttleApp();
        ImageIcon grabbedimg=new ImageIcon(imagepath);
        Image nonscaledimg=grabbedimg.getImage();
        Image scaledimg=nonscaledimg.getScaledInstance(labelwidth, labelheight, Image.SCALE_SMOOTH);
        return(new ImageIcon(scaledimg));
    }
}
