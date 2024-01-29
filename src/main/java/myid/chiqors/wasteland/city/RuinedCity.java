

package myid.chiqors.wasteland.city;

import myid.chiqors.wasteland.config.ModConfig;
import myid.chiqors.wasteland.items.LootStack;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class RuinedCity {
  CityBlockLayout layout;
  
  public RuinedCity(World world, MultiVector center, List<MultiVector> chunks, Random random) {
    this.layout = new CityBlockLayout(chunks, center, random, world);
  }
  
  public void generate(World world, Random random, List<SchematicBuilding> buildingSchematics, LootStack[] loot) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokespecial generateCityRoads : (Lnet/minecraft/world/World;Ljava/util/Random;)V
    //   6: aload_2
    //   7: bipush #16
    //   9: invokevirtual nextInt : (I)I
    //   12: istore #5
    //   14: aload_0
    //   15: getfield layout : Ldk/mrspring/wasteland/city/CityBlockLayout;
    //   18: getfield block : Ljava/util/List;
    //   21: invokeinterface size : ()I
    //   26: istore #6
    //   28: iconst_0
    //   29: istore #7
    //   31: iload #7
    //   33: iload #6
    //   35: if_icmpge -> 628
    //   38: aload_0
    //   39: getfield layout : Ldk/mrspring/wasteland/city/CityBlockLayout;
    //   42: getfield block : Ljava/util/List;
    //   45: iload #7
    //   47: invokeinterface get : (I)Ljava/lang/Object;
    //   52: checkcast dk/mrspring/wasteland/city/CityBlock
    //   55: astore #8
    //   57: aload #8
    //   59: getfield doGenerate : Z
    //   62: ifeq -> 80
    //   65: aload #8
    //   67: aload_1
    //   68: aload_2
    //   69: aload_3
    //   70: aload #4
    //   72: iload #5
    //   74: invokevirtual generate : (Lnet/minecraft/world/World;Ljava/util/Random;Ljava/util/List;[Ldk/mrspring/wasteland/items/LootStack;I)V
    //   77: goto -> 598
    //   80: aload #8
    //   82: getfield area : Ldk/mrspring/wasteland/utils/Rectangle;
    //   85: getfield length : I
    //   88: bipush #32
    //   90: if_icmpne -> 121
    //   93: aload #8
    //   95: getfield area : Ldk/mrspring/wasteland/utils/Rectangle;
    //   98: getfield width : I
    //   101: bipush #32
    //   103: if_icmpne -> 110
    //   106: iconst_1
    //   107: goto -> 111
    //   110: iconst_0
    //   111: dup
    //   112: istore #9
    //   114: ifeq -> 121
    //   117: iconst_1
    //   118: goto -> 122
    //   121: iconst_0
    //   122: istore #9
    //   124: iload #9
    //   126: ifeq -> 145
    //   129: aload_2
    //   130: iconst_4
    //   131: invokevirtual nextInt : (I)I
    //   134: ifle -> 141
    //   137: iconst_1
    //   138: goto -> 158
    //   141: iconst_0
    //   142: goto -> 158
    //   145: aload_2
    //   146: iconst_4
    //   147: invokevirtual nextInt : (I)I
    //   150: ifne -> 157
    //   153: iconst_1
    //   154: goto -> 158
    //   157: iconst_0
    //   158: istore #10
    //   160: iload #9
    //   162: ifeq -> 175
    //   165: aload_2
    //   166: iconst_2
    //   167: invokevirtual nextInt : (I)I
    //   170: iconst_1
    //   171: iadd
    //   172: goto -> 176
    //   175: iconst_1
    //   176: istore #11
    //   178: bipush #10
    //   180: istore #12
    //   182: iconst_0
    //   183: istore #13
    //   185: iload #13
    //   187: bipush #50
    //   189: if_icmpge -> 413
    //   192: iload #10
    //   194: ifeq -> 413
    //   197: aload_2
    //   198: iconst_2
    //   199: invokevirtual nextInt : (I)I
    //   202: ifne -> 219
    //   205: aload #8
    //   207: getfield area : Ldk/mrspring/wasteland/utils/Rectangle;
    //   210: getfield width : I
    //   213: bipush #8
    //   215: isub
    //   216: goto -> 221
    //   219: bipush #8
    //   221: istore #14
    //   223: aload_2
    //   224: iconst_2
    //   225: invokevirtual nextInt : (I)I
    //   228: ifne -> 245
    //   231: aload #8
    //   233: getfield area : Ldk/mrspring/wasteland/utils/Rectangle;
    //   236: getfield length : I
    //   239: bipush #8
    //   241: isub
    //   242: goto -> 247
    //   245: bipush #8
    //   247: istore #15
    //   249: aload_2
    //   250: iload #12
    //   252: iconst_1
    //   253: iadd
    //   254: invokevirtual nextInt : (I)I
    //   257: iload #12
    //   259: iconst_2
    //   260: idiv
    //   261: isub
    //   262: iload #14
    //   264: iadd
    //   265: aload #8
    //   267: getfield area : Ldk/mrspring/wasteland/utils/Rectangle;
    //   270: getfield position : Ldk/mrspring/wasteland/utils/Vector;
    //   273: getfield X : I
    //   276: iadd
    //   277: istore #14
    //   279: aload_2
    //   280: iload #12
    //   282: iconst_1
    //   283: iadd
    //   284: invokevirtual nextInt : (I)I
    //   287: iload #12
    //   289: iconst_2
    //   290: idiv
    //   291: isub
    //   292: iload #15
    //   294: iadd
    //   295: aload #8
    //   297: getfield area : Ldk/mrspring/wasteland/utils/Rectangle;
    //   300: getfield position : Ldk/mrspring/wasteland/utils/Vector;
    //   303: getfield Z : I
    //   306: iadd
    //   307: istore #15
    //   309: aload_1
    //   310: iload #14
    //   312: iload #15
    //   314: invokestatic getWorldHeight : (Lnet/minecraft/world/World;II)I
    //   317: istore #16
    //   319: new dk/mrspring/wasteland/world/gen/WorldGenWastelandLake
    //   322: dup
    //   323: aload_2
    //   324: iconst_5
    //   325: invokevirtual nextInt : (I)I
    //   328: ifne -> 337
    //   331: getstatic net/minecraft/init/Blocks.water : Lnet/minecraft/block/Block;
    //   334: goto -> 340
    //   337: invokestatic getlakeLiquid : ()Lnet/minecraft/block/Block;
    //   340: invokespecial <init> : (Lnet/minecraft/block/Block;)V
    //   343: astore #17
    //   345: new dk/mrspring/wasteland/world/gen/WorldGenWastelandClay
    //   348: dup
    //   349: iconst_3
    //   350: invokespecial <init> : (I)V
    //   353: astore #18
    //   355: aload #17
    //   357: aload_1
    //   358: aload_2
    //   359: iload #14
    //   361: iload #16
    //   363: iload #15
    //   365: invokevirtual generate : (Lnet/minecraft/world/World;Ljava/util/Random;III)Z
    //   368: ifne -> 375
    //   371: iconst_1
    //   372: goto -> 376
    //   375: iconst_0
    //   376: istore #10
    //   378: iload #10
    //   380: ifne -> 407
    //   383: aload_2
    //   384: bipush #6
    //   386: invokevirtual nextInt : (I)I
    //   389: iconst_5
    //   390: if_icmpge -> 407
    //   393: aload #18
    //   395: aload_1
    //   396: aload_2
    //   397: iload #14
    //   399: iload #16
    //   401: iload #15
    //   403: invokevirtual generate : (Lnet/minecraft/world/World;Ljava/util/Random;III)Z
    //   406: pop
    //   407: iinc #13, 1
    //   410: goto -> 185
    //   413: iconst_0
    //   414: istore #13
    //   416: iconst_0
    //   417: istore #14
    //   419: iload #14
    //   421: bipush #50
    //   423: if_icmpge -> 598
    //   426: iload #13
    //   428: iload #11
    //   430: if_icmpge -> 598
    //   433: aload_2
    //   434: iconst_2
    //   435: invokevirtual nextInt : (I)I
    //   438: ifne -> 455
    //   441: aload #8
    //   443: getfield area : Ldk/mrspring/wasteland/utils/Rectangle;
    //   446: getfield width : I
    //   449: bipush #8
    //   451: isub
    //   452: goto -> 457
    //   455: bipush #8
    //   457: istore #15
    //   459: aload_2
    //   460: iconst_2
    //   461: invokevirtual nextInt : (I)I
    //   464: ifne -> 481
    //   467: aload #8
    //   469: getfield area : Ldk/mrspring/wasteland/utils/Rectangle;
    //   472: getfield length : I
    //   475: bipush #8
    //   477: isub
    //   478: goto -> 483
    //   481: bipush #8
    //   483: istore #16
    //   485: aload_2
    //   486: iload #12
    //   488: iconst_1
    //   489: iadd
    //   490: invokevirtual nextInt : (I)I
    //   493: iload #12
    //   495: iconst_2
    //   496: idiv
    //   497: isub
    //   498: iload #15
    //   500: iadd
    //   501: aload #8
    //   503: getfield area : Ldk/mrspring/wasteland/utils/Rectangle;
    //   506: getfield position : Ldk/mrspring/wasteland/utils/Vector;
    //   509: getfield X : I
    //   512: iadd
    //   513: istore #15
    //   515: aload_2
    //   516: iload #12
    //   518: iconst_1
    //   519: iadd
    //   520: invokevirtual nextInt : (I)I
    //   523: iload #12
    //   525: iconst_2
    //   526: idiv
    //   527: isub
    //   528: iload #16
    //   530: iadd
    //   531: aload #8
    //   533: getfield area : Ldk/mrspring/wasteland/utils/Rectangle;
    //   536: getfield position : Ldk/mrspring/wasteland/utils/Vector;
    //   539: getfield Z : I
    //   542: iadd
    //   543: istore #16
    //   545: aload_1
    //   546: iload #15
    //   548: iload #16
    //   550: invokestatic getWorldHeight : (Lnet/minecraft/world/World;II)I
    //   553: istore #17
    //   555: new dk/mrspring/wasteland/world/gen/WorldGenWastelandBigTree
    //   558: dup
    //   559: iconst_1
    //   560: invokespecial <init> : (Z)V
    //   563: astore #18
    //   565: aload #18
    //   567: aload_1
    //   568: aload_2
    //   569: iload #15
    //   571: iload #17
    //   573: iload #16
    //   575: invokevirtual generate : (Lnet/minecraft/world/World;Ljava/util/Random;III)Z
    //   578: ifeq -> 588
    //   581: iload #13
    //   583: iconst_1
    //   584: iadd
    //   585: goto -> 590
    //   588: iload #13
    //   590: istore #13
    //   592: iinc #14, 1
    //   595: goto -> 419
    //   598: iload #7
    //   600: iload #6
    //   602: if_icmpeq -> 622
    //   605: getstatic dk/mrspring/wasteland/Wasteland.NETWORK : Lcpw/mods/fml/common/network/simpleimpl/SimpleNetworkWrapper;
    //   608: iload #7
    //   610: iconst_1
    //   611: iadd
    //   612: iload #6
    //   614: iconst_1
    //   615: iadd
    //   616: invokestatic createProgressMessage : (II)Ldk/mrspring/wasteland/utils/Message;
    //   619: invokevirtual sendToAll : (Lcpw/mods/fml/common/network/simpleimpl/IMessage;)V
    //   622: iinc #7, 1
    //   625: goto -> 31
    //   628: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #32	-> 0
    //   #33	-> 6
    //   #34	-> 14
    //   #35	-> 28
    //   #37	-> 38
    //   #39	-> 57
    //   #41	-> 65
    //   #45	-> 80
    //   #46	-> 124
    //   #47	-> 160
    //   #48	-> 178
    //   #50	-> 182
    //   #52	-> 197
    //   #53	-> 223
    //   #54	-> 249
    //   #55	-> 279
    //   #56	-> 309
    //   #58	-> 319
    //   #59	-> 345
    //   #61	-> 355
    //   #62	-> 378
    //   #64	-> 393
    //   #50	-> 407
    //   #68	-> 413
    //   #69	-> 416
    //   #71	-> 433
    //   #72	-> 459
    //   #73	-> 485
    //   #74	-> 515
    //   #75	-> 545
    //   #77	-> 555
    //   #78	-> 565
    //   #69	-> 592
    //   #82	-> 598
    //   #84	-> 605
    //   #35	-> 622
    //   #87	-> 628
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   114	7	9	large	Z
    //   223	184	14	x	I
    //   249	158	15	z	I
    //   319	88	16	y	I
    //   345	62	17	lake	Ldk/mrspring/wasteland/world/gen/WorldGenWastelandLake;
    //   355	52	18	clay	Ldk/mrspring/wasteland/world/gen/WorldGenWastelandClay;
    //   185	228	13	l	I
    //   459	133	15	x	I
    //   485	107	16	z	I
    //   555	37	17	y	I
    //   565	27	18	tree	Ldk/mrspring/wasteland/world/gen/WorldGenWastelandBigTree;
    //   419	179	14	t	I
    //   124	474	9	large	Z
    //   160	438	10	genLake	Z
    //   178	420	11	trees	I
    //   182	416	12	w	I
    //   416	182	13	totalTrees	I
    //   57	565	8	generatingBlock	Ldk/mrspring/wasteland/city/CityBlock;
    //   31	597	7	i	I
    //   0	629	0	this	Ldk/mrspring/wasteland/city/RuinedCity;
    //   0	629	1	world	Lnet/minecraft/world/World;
    //   0	629	2	random	Ljava/util/Random;
    //   0	629	3	buildingSchematics	Ljava/util/List;
    //   0	629	4	loot	[Ldk/mrspring/wasteland/items/LootStack;
    //   14	615	5	cityColour	I
    //   28	601	6	citySize	I
    // Local variable type table:
    //   start	length	slot	name	signature
    //   0	629	3	buildingSchematics	Ljava/util/List<Ldk/mrspring/wasteland/city/SchematicBuilding;>;
  }
  
  private void generateCityRoads(World world, Random r) {
    int roadWidth = 2;
    int hOffset = 0;
    for (int b = 0; b < this.layout.block.size(); b++) {
      CityBlock block = this.layout.block.get(b);
      int w = block.area.width / 16;
      int l = block.area.length / 16;
      int x = block.area.position.X;
      int z = block.area.position.Z;
      int i;
      for (i = 0; i < w; i++) {
        if (block.connectedFaces[i])
          for (int j = 0; j < roadWidth; j++) {
            int y1 = block.cornerHeight[i];
            int y2 = block.cornerHeight[i + 1];
            generateRoad(world, x + i * 16 - roadWidth, z + block.area.length - 1 - j, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, true, r);
          }  
        if (block.connectedFaces[2 * w + l - i - 1])
          for (int j = 0; j < roadWidth; j++) {
            int y1 = block.cornerHeight[2 * w + l - i];
            int y2 = block.cornerHeight[2 * w + l - i - 1];
            generateRoad(world, x + i * 16 - roadWidth, z + j, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, true, r);
          }  
      } 
      for (i = 0; i < l; i++) {
        if (block.connectedFaces[w + l - i - 1])
          for (int j = 0; j < roadWidth; j++) {
            int y1 = block.cornerHeight[w + l - i];
            int y2 = block.cornerHeight[w + l - i - 1];
            generateRoad(world, x + block.area.width - 1 - j, z + i * 16 - roadWidth, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, false, r);
          }  
        if (block.connectedFaces[2 * w + l + i])
          for (int j = 0; j < roadWidth; j++) {
            int y2, y1 = block.cornerHeight[2 * w + l + i];
            if (2 * w + l + i + 1 >= block.cornerHeight.length) {
              y2 = block.cornerHeight[0];
            } else {
              y2 = block.cornerHeight[2 * w + l + i + 1];
            } 
            generateRoad(world, x + j, z + i * 16 - roadWidth, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, false, r);
          }  
      } 
    } 
  }
  
  private void generateRoad(World world, int x, int z, int y1, int y2, int length, boolean dir, Random r) {
    int odds = 10;
    Block roadBlock = Blocks.stained_hardened_clay;
    Block surfaceBlock = ModConfig.getSurfaceBlock();
    if (dir) {
      for (int i = 0; i < length; i++) {
        boolean f = (r.nextInt(odds) == 0);
        if (i < length / 2) {
          world.setBlock(x + i, y1, z, f ? surfaceBlock : roadBlock, f ? 0 : 15, 2);
          clearAbove(x + i, y1 + 1, z, 5, world);
          fillBelow(x + i, y1 - 1, z, 3, world);
        } else {
          world.setBlock(x + i, y2, z, f ? surfaceBlock : roadBlock, f ? 0 : 15, 2);
          clearAbove(x + i, y2 + 1, z, 5, world);
          fillBelow(x + i, y2 - 1, z, 3, world);
        } 
      } 
    } else {
      for (int i = 0; i < length; i++) {
        boolean f = (r.nextInt(odds) == 0);
        if (i < length / 2) {
          world.setBlock(x, y1, z + i, f ? surfaceBlock : roadBlock, f ? 0 : 15, 2);
          clearAbove(x, y1 + 1, z + i, 5, world);
          fillBelow(x, y1 - 1, z + i, 3, world);
        } else {
          world.setBlock(x, y2, z + i, f ? surfaceBlock : roadBlock, f ? 0 : 15, 2);
          clearAbove(x, y2 + 1, z + i, 5, world);
          fillBelow(x, y2 - 1, z + i, 3, world);
        } 
      } 
    } 
  }
  
  public static void clearAbove(int x, int y, int z, int d, World world) {
    for (int i = 0; i < d; i++) {
      if (!world.getBlock(x, y + i, z).equals(Blocks.air))
        world.setBlock(x, y + i, z, Blocks.air, 0, 2); 
    } 
  }
  
  public static void fillBelow(int x, int y, int z, int d, World world) {
    for (int i = 0; i < d; i++) {
      Block b = world.getBlock(x, y - i, z);
      if (b.equals(Blocks.air) || b.equals(Blocks.deadbush))
        world.setBlock(x, y - i, z, Blocks.stone, 0, 2); 
    } 
  }
}
