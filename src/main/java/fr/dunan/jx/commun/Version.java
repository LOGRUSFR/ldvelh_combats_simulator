package fr.dunan.jx.commun;

import lombok.Getter;

import java.util.jar.Attributes;
import java.util.jar.Manifest;

public final class Version {

    private static Version versionSingleton;

    @Getter
    private String programName;

    @Getter
    private String programVersion;

    private Version() {
        Package mainPackage = Version.class.getPackage();
        programName = mainPackage.getImplementationTitle();
        programVersion = mainPackage.getImplementationVersion();
    }

    /**
     * For unit test...
     *
     * @param mf
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
