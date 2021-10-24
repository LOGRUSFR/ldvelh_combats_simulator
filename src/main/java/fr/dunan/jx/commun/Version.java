package fr.dunan.jx.commun;

import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public final class Version {

    private static Version versionSingleton;

    private static final String MANIFEST_PATH = "META-INF/MANIFEST.MF";

    @Getter
    private String programName;

    @Getter
    private String programVersion;

    private Version() {
        ClassLoader cl = Version.class.getClassLoader();
        URL url = cl.getResource(MANIFEST_PATH);
        Manifest manifest = null;
        try {
            manifest = new Manifest(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Attributes attr = manifest.getMainAttributes();
        programName = manifest.getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_TITLE);
        programVersion = manifest.getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_VERSION);
    }

    public static Version build() {
        if (versionSingleton == null)
            return new Version();
        return versionSingleton;
    }

}
