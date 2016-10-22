package com.slash.androidxmlprinter;

import com.slash.androidxmlprinter.xmlprinter.AXMLPrinter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String manifestPath = Environment.getExternalStorageDirectory()+"/AndroidManifest.xml";
        AXMLPrinter.decodeManifest(new String[]{manifestPath});
    }
}
