package fr.dunan.jx.commun;

import lombok.Getter;

import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Getter
public final class Version {

    private static final Version versionSingleton = null;

    private final String programName;

    private final String programVersion;

    private Version() {
        Package mainPackage = Version.class.getPackage();
        programName = mainPackage.getImplementationTitle();
        programVersion = mainPackage.getImplementationVersion();
    }

    /**
     * For unit test...
     *
     */
    public Version(Manifest mf) {
        Attributes attr = mf.getMainAttributes();
        programName = attr.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
        programVersion = attr.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
    }

    public static Version build() {
        if (versionSingleton == null)
            return new Version();
        return versionSingleton;
    }

}
