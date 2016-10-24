# androidAXMLprinter
A demo of decoding AndroidManifest.xml from .apk file  based on AXMLPrinter2.jar and xmlPullParser.
###Thanks to the author of AXMLPrinter2.jar!
###For now this project is only able to decode the permission info from the AndroidManifest.xml because I only need this.Later maybe I will think about to add some other functions.
### The main class has been rename to AXMLDecoder.java in the package main.It's simple to use this with just one step:
####Call the static function : AXMLDecoder.getPermissions(String).The param is the absolute path of your apk file,and the return type is List in geric of String.
####In consideration of the decoding process maybe time-consuming,it's better to call this method in a thread ,and add an complete callback to handle the result of decoding.
###Hope you have fun!
