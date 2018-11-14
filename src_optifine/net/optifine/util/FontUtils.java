package net.optifine.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class FontUtils {
   public static Properties readFontProperties(nf locationFontTexture) {
      String fontFileName = locationFontTexture.a();
      Properties props = new Properties();
      String suffix = ".png";
      if (!fontFileName.endsWith(suffix)) {
         return props;
      } else {
         String fileName = fontFileName.substring(0, fontFileName.length() - suffix.length()) + ".properties";

         try {
            nf locProp = new nf(locationFontTexture.b(), fileName);
            InputStream in = .Config.getResourceStream(.Config.getResourceManager(), locProp);
            if (in == null) {
               return props;
            }

            .Config.log("Loading " + fileName);
            props.load(in);
         } catch (FileNotFoundException var7) {
            ;
         } catch (IOException var8) {
            var8.printStackTrace();
         }

         return props;
      }
   }

   public static void readCustomCharWidths(Properties props, float[] charWidth) {
      Set keySet = props.keySet();
      Iterator iter = keySet.iterator();

      while(iter.hasNext()) {
         String key = (String)iter.next();
         String prefix = "width.";
         if (key.startsWith(prefix)) {
            String numStr = key.substring(prefix.length());
            int num = .Config.parseInt(numStr, -1);
            if (num >= 0 && num < charWidth.length) {
               String value = props.getProperty(key);
               float width = .Config.parseFloat(value, -1.0F);
               if (width >= 0.0F) {
                  charWidth[num] = width;
               }
            }
         }
      }

   }

   public static float readFloat(Properties props, String key, float defOffset) {
      String str = props.getProperty(key);
      if (str == null) {
         return defOffset;
      } else {
         float offset = .Config.parseFloat(str, Float.MIN_VALUE);
         if (offset == Float.MIN_VALUE) {
            .Config.warn("Invalid value for " + key + ": " + str);
            return defOffset;
         } else {
            return offset;
         }
      }
   }

   public static boolean readBoolean(Properties props, String key, boolean defVal) {
      String str = props.getProperty(key);
      if (str == null) {
         return defVal;
      } else {
         String strLow = str.toLowerCase().trim();
         if (!strLow.equals("true") && !strLow.equals("on")) {
            if (!strLow.equals("false") && !strLow.equals("off")) {
               .Config.warn("Invalid value for " + key + ": " + str);
               return defVal;
            } else {
               return false;
            }
         } else {
            return true;
         }
      }
   }

   public static nf getHdFontLocation(nf fontLoc) {
      if (!.Config.isCustomFonts()) {
         return fontLoc;
      } else if (fontLoc == null) {
         return fontLoc;
      } else if (!.Config.isMinecraftThread()) {
         return fontLoc;
      } else {
         String fontName = fontLoc.a();
         String texturesStr = "textures/";
         String mcpatcherStr = "mcpatcher/";
         if (!fontName.startsWith(texturesStr)) {
            return fontLoc;
         } else {
            fontName = fontName.substring(texturesStr.length());
            fontName = mcpatcherStr + fontName;
            nf fontLocHD = new nf(fontLoc.b(), fontName);
            return .Config.hasResource(.Config.getResourceManager(), fontLocHD) ? fontLocHD : fontLoc;
         }
      }
   }
}
