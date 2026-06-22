package starmaker.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;

public class TextureSizeHelper {

    public static float[] getTextureSizeAsFloat(ResourceLocation location) {
        Minecraft mc = Minecraft.getMinecraft();

        mc.getTextureManager().bindTexture(location);
        ITextureObject textureObject = mc.getTextureManager().getTexture(location);

        int glTextureId = textureObject.getGlTextureId();
        org.lwjgl.opengl.GL11.glBindTexture(org.lwjgl.opengl.GL11.GL_TEXTURE_2D, glTextureId);

        int width = org.lwjgl.opengl.GL11.glGetTexLevelParameteri(org.lwjgl.opengl.GL11.GL_TEXTURE_2D, 0, org.lwjgl.opengl.GL11.GL_TEXTURE_WIDTH);
        int height = org.lwjgl.opengl.GL11.glGetTexLevelParameteri(org.lwjgl.opengl.GL11.GL_TEXTURE_2D, 0, org.lwjgl.opengl.GL11.GL_TEXTURE_HEIGHT);

        return new float[] { (float) width, (float) height };

    }
}
