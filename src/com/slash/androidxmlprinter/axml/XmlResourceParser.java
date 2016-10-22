package com.slash.androidxmlprinter.axml;

import org.xmlpull.v1.XmlPullParser;

import com.slash.androidxmlprinter.android.AttributeSet;

public interface XmlResourceParser extends XmlPullParser, AttributeSet{
	void close();
}
