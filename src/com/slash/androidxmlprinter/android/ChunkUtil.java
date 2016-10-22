package com.slash.androidxmlprinter.android;

import java.io.IOException;

public class ChunkUtil {
	ChunkUtil() {
    }

    public static final void readCheckType(IntReader reader, int expectedType) throws IOException {
        int type = reader.readInt();
        if(type != expectedType) {
            throw new IOException("Expected chunk of type 0x" + Integer.toHexString(expectedType) + ", read 0x" + Integer.toHexString(type) + ".");
        }
    }
}
