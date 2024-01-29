

package myid.chiqors.wasteland;

import cpw.mods.fml.common.IWorldGenerator;
import myid.chiqors.wasteland.config.ModConfig;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class PostModWorldGenerator implements IWorldGenerator {
  public static int surfaceBlockID = Block.getIdFromBlock(ModConfig.getSurfaceBlock());
  
  public static int grassID = Block.getIdFromBlock((Block)Blocks.grass);
  
  public static int tallGrassID = Block.getIdFromBlock((Block)Blocks.tallgrass);
  
  public static int deadBushID = Block.getIdFromBlock((Block)Blocks.deadbush);
  
  public static int radius = ModConfig.forceDisableGrassRadius;
  
  private static boolean checkingNearChunks = false;
  
  private static int checkingChunkX;
  
  private static int checkingChunkZ;
  
  public IWorldGenerator toIWorldGenerator() {
    IWorldGenerator generator = this;
    return generator;
  }
  
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    if (world.provider.dimensionId == 0 && world.getWorldChunkManager() instanceof myid.chiqors.wasteland.world.WorldChunkManagerWasteland && ModConfig.forceDisableGrass)
      if (!checkingNearChunks) {
        if (checkReplaceBlocks(world.getChunkFromChunkCoords(chunkX, chunkZ), world, chunkX, chunkZ)) {
          checkingNearChunks = true;
          for (int x = 0 - radius; x <= radius; x++) {
            for (int z = 0 - radius; z <= radius; z++) {
              if (x != 0 || z != 0) {
                Chunk current = world.getChunkFromChunkCoords(chunkX + x, chunkZ + z);
                checkReplaceBlocks(current, world, chunkX + x, chunkZ + z);
              } 
            } 
          } 
          checkingNearChunks = false;
        } 
      } else {
        checkReplaceBlocks(world.getChunkFromChunkCoords(chunkX, chunkZ), world, chunkX, chunkZ);
      }  
  }
  
  private static boolean checkReplaceBlocks(Chunk chunk, World world, int chunkX, int chunkZ) {
    boolean blocksExist = false;
    ExtendedBlockStorage[] extStore = chunk.getBlockStorageArray();
    for (int i = 3; extStore != null && i < extStore.length; i++) {
      ExtendedBlockStorage extB = extStore[i];
      if (extB != null) {
        byte[] blocks = extB.getBlockLSBArray();
        for (int j = 0; j < blocks.length; j++) {
          if (blocks[j] == grassID) {
            blocks[j] = (byte)surfaceBlockID;
            blocksExist = true;
          } else if (blocks[j] == tallGrassID) {
            blocks[j] = (byte)deadBushID;
          } 
        } 
      } 
    } 
    return blocksExist;
  }
}
