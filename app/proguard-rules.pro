#1.基本指令区
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-ignorewarnings
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable

#2.默认保留区
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}

#3.webview
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}

#-------------greendao-----------
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties { *; }

# If you DO use SQLCipher:
-keep class org.greenrobot.greendao.database.SqlCipherEncryptedHelper { *; }

# If you do NOT use SQLCipher:
-dontwarn net.sqlcipher.database.**
# If you do NOT use RxJava:
-dontwarn rx.**
-keep class com.flying.famous.quotes.db.gen.**{*;}
-keep class com.flying.famous.quotes.db.entity.**{*;}
-keep class com.flying.famous.quotes.db.**{*;}
-keep class com.flying.famous.quotes.manager.**{*;}

-keep class aavax.xml.**{*;}
-keep class com.bea.**{*;}
-keep class com.**{*;}
-keep class org.**{*;}

#-------------greendao-----------

#-------------Glide-----------
##Glide
-dontwarn com.bumptech.glide.**
-keep class com.bumptech.glide.**{*;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}
# for DexGuard only
#-------------Glide-----------

# Add *one* of the following rules to your Proguard configuration file.
# Alternatively, you can annotate classes and class members with @android.support.annotation.Keep

# keep everything in this package from being removed or renamed
-keep class org.** { *; }

# keep everything in this package from being renamed only
-keepnames class org.** { *; }
# Add *one* of the following rules to your Proguard configuration file.
# Alternatively, you can annotate classes and class members with @android.support.annotation.Keep

# keep everything in this package from being removed or renamed
-keep class schemasMicrosoftComVml.** { *; }

# keep everything in this package from being renamed only
-keepnames class schemasMicrosoftComVml.** { *; }
# Alternatively, you can annotate classes and class members with @android.support.annotation.Keep

# keep everything in this package from being removed or renamed
-keep class schemasMicrosoftComOfficeExcel.** { *; }

# keep everything in this package from being renamed only
-keepnames class schemasMicrosoftComOfficeExcel.** { *; }
-keep class schemasMicrosoftComOfficeOffice.** { *; }

# keep everything in this package from being renamed only
-keepnames class schemasMicrosoftComOfficeOffice.** { *; }
-keep class pub.** { *; }

# keep everything in this package from being renamed only
-keepnames class pub.** { *; }
-keep class java.** { *; }

# keep everything in this package from being renamed only
-keepnames class java.** { *; }
-keep class android.** { *; }

# keep everything in this package from being renamed only
-keepnames class android.** { *; }
-keep class repackage.** { *; }

# keep everything in this package from being renamed only
-keepnames class repackage.** { *; }
-keep class androidx.** { *; }

# keep everything in this package from being renamed only
-keepnames class androidx.** { *; }

-keep class schemaorg_apache_xmlbeans.** { *; }

# keep everything in this package from being renamed only
-keepnames class schemaorg_apache_xmlbeans.** { *; }

-keep class schemasMicrosoftComOfficeWord.** { *; }

# keep everything in this package from being renamed only
-keepnames class schemasMicrosoftComOfficeWord.** { *; }

-keep class schemasMicrosoftComOfficePowerpoint.** { *; }

# keep everything in this package from being renamed only
-keepnames class schemasMicrosoftComOfficePowerpoint.** { *; }

-keep class aavax.** { *; }

# keep everything in this package from being renamed only
-keepnames class aavax.** { *; }
-keep class net.** { *; }

# keep everything in this package from being renamed only
-keepnames class net.** { *; }

-keep class rx.** { *; }

# keep everything in this package from being renamed only
-keepnames class rx.** { *; }
# keep everything in this package from being removed or renamed
-keep class aavax.** { *; }

# keep everything in this package from being renamed only
-keepnames class aavax.** { *; }

# keep everything in this package from being removed or renamed
-keep class kotlin.** { *; }

# keep everything in this package from being renamed only
-keepnames class kotlin.** { *; }

-keep class javax.** { *; }

# keep everything in this package from being renamed only
-keepnames class javax.** { *; }

-keep class com.bumptech.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.bumptech.** { *; }

-keep class com.google.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.google.** { *; }

-keep class com.sun.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.sun.** { *; }

-keep class com.bea.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.bea.** { *; }

-keep class com.microsoft.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.microsoft.** { *; }

-keep class com.wutka.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.wutka.** { *; }

-keep class com.zhy.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.zhy.** { *; }

-keep class com.heima.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.heima.** { *; }


-keep class com.xiang.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.xiang.** { *; }



