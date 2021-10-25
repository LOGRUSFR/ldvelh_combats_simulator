package fr.dunan.jx.commun;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.jar.Attributes;
import java.util.jar.Manifest;

class VersionTest {

    Version version;

    //TODO change to integration test w/ jar

    @BeforeEach
    @Test
    void build() {
        //Manifest pas charge lors d une exec loclae sans jar...
        Manifest mf = new Manifest();
        Attributes attrs = mf.getMainAttributes();
        attrs.put(Attributes.Name.IMPLEMENTATION_TITLE, "myProgram");
        attrs.put(Attributes.Name.IMPLEMENTATION_VERSION, "myVersion");
        version = new Version(mf);
        Assertions.assertNotNull(version);
    }

    @Test
    void getProgramName() {
        Assertions.assertNotNull(version.getProgramName());
        Assertions.assertNotEquals("", version.getProgramName());
    }

    @Test
    void getProgramVersion() {
        Assertions.assertNotNull(version.getProgramVersion());
        Assertions.assertNotEquals("", version.getProgramVersion());
    }
}