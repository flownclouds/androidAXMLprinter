package com.slash.androidxmlprinter.xmlprinter;

import java.io.FileInputStream;

import android.util.Log;

import com.slash.androidxmlprinter.axml.AXmlResourceParser;

public class AXMLPrinter {
	    private static final float[] RADIX_MULTS = new float[]{0.00390625F, 3.051758E-5F, 1.192093E-7F, 4.656613E-10F};
	    private static final String[] DIMENSION_UNITS = new String[]{"px", "dip", "sp", "pt", "in", "mm", "", ""};
	    private static final String[] FRACTION_UNITS = new String[]{"%", "%p", "", "", "", "", "", ""};

	    public AXMLPrinter() {
	    }

	    public static void decodeManifest(String[] arguments){
	    	arguments = new String[]{"D://AndroidManifest.xml"};
	        if(arguments.length < 1) {
	            log("Usage: AXMLPrinter <binary xml file>", new Object[0]);
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
	                            log("<?xml version=\"1.0\" encoding=\"utf-8\"?>", new Object[0]);
	                        case 1:
	                        default:
	                            break;
	                        case 2:
	                            log("%s<%s%s", new Object[]{indent, getNamespacePrefix(e.getPrefix()), e.getName()});
	                            indent.append("\t");
	                            int namespaceCountBefore = e.getNamespaceCount(e.getDepth() - 1);
	                            int namespaceCount = e.getNamespaceCount(e.getDepth());

	                            int i;
	                            for(i = namespaceCountBefore; i != namespaceCount; ++i) {
	                                log("%sxmlns:%s=\"%s\"", new Object[]{indent, e.getNamespacePrefix(i), e.getNamespaceUri(i)});
	                            }

	                            for(i = 0; i != e.getAttributeCount(); ++i) {
	                                log("%s%s%s=\"%s\"", new Object[]{indent, getNamespacePrefix(e.getAttributePrefix(i)), e.getAttributeName(i), getAttributeValue(e, i)});
	                            }

	                            log("%s>", new Object[]{indent});
	                            break;
	                        case 3:
	                            indent.setLength(indent.length() - "\t".length());
	                            log("%s</%s%s>", new Object[]{indent, getNamespacePrefix(e.getPrefix()), e.getName()});
	                            break;
	                        case 4:
	                            log("%s%s", new Object[]{indent, e.getText()});
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

	    private static void log(String format, Object... arguments) {
	        System.out.printf(format, arguments);
	        System.out.println();
	        
	    }

	    public static float complexToFloat(int complex) {
	        return (float)(complex & -256) * RADIX_MULTS[complex >> 4 & 3];
	    }
	}

