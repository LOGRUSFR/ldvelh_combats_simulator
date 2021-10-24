package fr.dunan.jx.commun;

import com.jcabi.manifests.Manifests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VersionTest {

    Version version;

    @BeforeEach
    @Test
    void build() {
        //Manifest pas charge lors d une exec manuelle
        Manifests.DEFAULT.put("Implementation-Title", "ldvelh_combats_simulator");
        Manifests.DEFAULT.put("Implementation-Version", "1.1.1");
        version = Version.build();
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