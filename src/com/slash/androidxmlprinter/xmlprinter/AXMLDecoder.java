package com.slash.androidxmlprinter.xmlprinter;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.slash.androidxmlprinter.axml.AXmlResourceParser;
import com.slash.androidxmlprinter.zip.Apk2Zip;
import com.slash.androidxmlprinter.zip.ZipReader;

public class AXMLDecoder {
	    private static final float[] RADIX_MULTS = new float[]{0.00390625F, 3.051758E-5F, 1.192093E-7F, 4.656613E-10F};
	    private static final String[] DIMENSION_UNITS = new String[]{"px", "dip", "sp", "pt", "in", "mm", "", ""};
	    private static final String[] FRACTION_UNITS = new String[]{"%", "%p", "", "", "", "", "", ""};
		private static final Object PERMISSION = "uses-permission";
		private static PrintStream ps;

	    public AXMLDecoder() {
	    }

	    public static List<String> getPermissions(String apkPath){
	    	String outXMLpath = apkPath.substring(0,apkPath.lastIndexOf("/")+1)+"AndroidManifest.xml";
	    	String outZipPath = apkPath.substring(0,apkPath.lastIndexOf("."))+".zip";
	    	boolean converted = Apk2Zip.renameFile(apkPath,outZipPath);
			if(!converted){
				return null;
			}
			boolean read = ZipReader.readManifestFromZip(outZipPath,outXMLpath);
			if(!read){
				return null;
			} 
			List<String> permissions = new ArrayList<>();
			
			decodeManifest(permissions,new String[]{outXMLpath});
			
			Apk2Zip.renameFile(outZipPath, apkPath);
			
			File outXMLFile = new File(outXMLpath);
			if(outXMLFile.exists()){
				outXMLFile.delete();
			}
			
			return permissions;
	    }
	    
	    public static void decodeManifest(List<String> permissions ,String[] arguments){
//	    	
//	    	if(arguments.length >= 2){
//	    		file = new File(arguments[1]);
//	    	}
	    	
	        if(arguments.length < 1) {
//	            log("Usage: AXMLPrinter <binary xml file>", new Object[0]);
	        } else {
	            try {
	                AXmlResourceParser e = new AXmlResourceParser();
	                e.open(new FileInputStream(arguments[0]));
	                StringBuilder indent = new StringBuilder(10);
	                String indentStep = "\t";

	                while(true) {
	                    while(true) {
	                        int type = e.next();
	                        if(type == 1) {
	                            return;
	                        }
	                        switch(type) {
	                        case 0:
	                        case 1:
	                        default:
	                            break;
	                        case 2:
	                        	String name = e.getName();
	                        	for(int i=0 ;i != e.getAttributeCount(); ++i){
	                        		if(name.equals(PERMISSION)){
	                        			String attrValue = getAttributeValue(e,i);
	                        			String permission = AttrValueConverter.convert(attrValue);
	                        			if(!permissions.contains(permission)){
	                        				permissions.add(permission);
	                        			}
	                        		}
	                        	}
	                            break;
	                        }
	                    }
	                }
	            } catch (Exception var8) {
	                var8.printStackTrace();
	            }
	        }
	    }

	    private static String getNamespacePrefix(String prefix) {
	        return prefix != null && prefix.length() != 0?prefix + ":":"";
	    }

	    private static String getAttributeValue(AXmlResourceParser parser, int index) {
	        int type = parser.getAttributeValueType(index);
	        int data = parser.getAttributeValueData(index);
	        return type == 3?parser.getAttributeValue(index):(type == 2?String.format("?%s%08X", new Object[]{getPackage(data), Integer.valueOf(data)}):(type == 1?String.format("@%s%08X", new Object[]{getPackage(data), Integer.valueOf(data)}):(type == 4?String.valueOf(Float.intBitsToFloat(data)):(type == 17?String.format("0x%08X", new Object[]{Integer.valueOf(data)}):(type == 18?(data != 0?"true":"false"):(type == 5?Float.toString(complexToFloat(data)) + DIMENSION_UNITS[data & 15]:(type == 6?Float.toString(complexToFloat(data)) + FRACTION_UNITS[data & 15]:(type >= 28 && type <= 31?String.format("#%08X", new Object[]{Integer.valueOf(data)}):(type >= 16 && type <= 31?String.valueOf(data):String.format("<0x%X, type 0x%02X>", new Object[]{Integer.valueOf(data), Integer.valueOf(type)}))))))))));
	    }

	    private static String getPackage(int id) {
	        return id >>> 24 == 1?"android:":"";
	    }

	    public static float complexToFloat(int complex) {
	        return (float)(complex & -256) * RADIX_MULTS[complex >> 4 & 3];
	    }
	}

