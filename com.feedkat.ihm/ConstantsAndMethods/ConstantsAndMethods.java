package ConstantsAndMethods;

import java.awt.Color;
import java.awt.Font;

import Model.CatBasics;

public class ConstantsAndMethods {
	/**
	 * @author G.Nivert
	 */
	
	public static final String		_Window_Title 						= "FeedKat - Bluetooth";
	public static final String		_Tool_Version						= "1.1";
	
	public static final Color		_Color_Orange						= new Color(255,153,0);
    public static final Color		_Color_Blue							= new Color(103,163,240);
    
    public static final Font		_Font_Arial_Rounded_MT_Bold_50		= new Font("Arial Rounded MT Bold", Font.BOLD, 50);
    public static final Font		_Font_Arial_Rounded_MT_Bold_20		= new Font("Arial Rounded MT Bold", Font.BOLD, 20);
    
    public static final String		_IMGS_Folder						= "./resources/Images/";
    public static final String		_Logo_FeedKat						= _IMGS_Folder + "Logo_FeedKat.png";
    public static final String		_Bluetooth_Gif						= _IMGS_Folder + "Bluetooth.gif";
    public static final String		_IconApp_FeedKat					= _IMGS_Folder + "IconApp_FeedKat.png";
    
    public static CatBasics 		cat;
    public static final String		_Cat_Age							= "18 mois";
    public static final double		_Cat_Weight							= 2.6;
    public static final String		_Cat_Name							= "Grizby";
    public static final int			_Battery_Value						= 50;
    public static final int			_Collar_ID							= 3;
    public static int				_YCat								= 10;
    public static int				_XCat								= 50;
    public static final int			_Activity							= 25;
}
