package com.jjhjart.ancientcraft.init;

import com.jjhjart.ancientcraft.NewVoid;
import com.jjhjart.ancientcraft.networking.DimensionTpPKT;
import com.jjhjart.ancientcraft.networking.OpenTmGuiPKT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.maven.artifact.versioning.ArtifactVersion;

public class ModPacketHandler {
    private static final String PROTOCOL_VERSION = getProtocolVersion();
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(NewVoid.MOD_ID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void init() {
        int disc = 0;

        CHANNEL.registerMessage(disc++, DimensionTpPKT.class, DimensionTpPKT::encode, DimensionTpPKT::decode, DimensionTpPKT::handle);
        CHANNEL.registerMessage(disc++, OpenTmGuiPKT.class, OpenTmGuiPKT::encode, OpenTmGuiPKT::decode, OpenTmGuiPKT::handle);
    }

    private static String getProtocolVersion() {
        ArtifactVersion version = ModList.get().getModContainerById(NewVoid.MOD_ID).get().getModInfo().getVersion();
        return version.getMajorVersion()+"."+version.getMinorVersion()+"."+version.getIncrementalVersion()+"-"+version.getQualifier();
    }
}
