

package myid.chiqors.wasteland.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;

public class EntityDayZombie extends EntityMob {
  protected static final IAttribute field_110186_bp = (IAttribute)(new RangedAttribute("zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
  
  private static final UUID babySpeedBoostUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
  
  private static final AttributeModifier babySpeedBoostModifier = new AttributeModifier(babySpeedBoostUUID, "Baby speed boost", 0.5D, 1);
  
  private final EntityAIBreakDoor field_146075_bs = new EntityAIBreakDoor((EntityLiving)this);
  
  private int conversionTime;
  
  private boolean field_146076_bu = false;
  
  private float field_146074_bv = -1.0F;
  
  private float field_146073_bw;
  
  private static final String __OBFID = "CL_00001702";
  
  public EntityDayZombie(World p_i1745_1_) {
    super(p_i1745_1_);
    getNavigator().setBreakDoors(true);
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0D, false));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityVillager.class, 1.0D, true));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0D));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIMoveThroughVillage((EntityCreature)this, 1.0D, false));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
    this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityVillager.class, 0, false));
    setSize(0.6F, 1.8F);
  }
  
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000417232514D);
    getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
    getAttributeMap().registerAttribute(field_110186_bp).setBaseValue(this.rand.nextDouble() * ForgeModContainer.zombieSummonBaseChance);
  }
  
  protected void entityInit() {
    super.entityInit();
    getDataWatcher().addObject(12, Byte.valueOf((byte)0));
    getDataWatcher().addObject(13, Byte.valueOf((byte)0));
    getDataWatcher().addObject(14, Byte.valueOf((byte)0));
  }
  
  public int getTotalArmorValue() {
    int i = super.getTotalArmorValue() + 2;
    if (i > 20)
      i = 20; 
    return i;
  }
  
  protected boolean isAIEnabled() {
    return true;
  }
  
  public boolean func_146072_bX() {
    return this.field_146076_bu;
  }
  
  public void func_146070_a(boolean p_146070_1_) {
    if (this.field_146076_bu != p_146070_1_) {
      this.field_146076_bu = p_146070_1_;
      if (p_146070_1_) {
        this.tasks.addTask(1, (EntityAIBase)this.field_146075_bs);
      } else {
        this.tasks.removeTask((EntityAIBase)this.field_146075_bs);
      } 
    } 
  }
  
  public boolean isChild() {
    return (getDataWatcher().getWatchableObjectByte(12) == 1);
  }
  
  protected int getExperiencePoints(EntityPlayer p_70693_1_) {
    if (isChild())
      this.experienceValue = (int)(this.experienceValue * 2.5F); 
    return super.getExperiencePoints(p_70693_1_);
  }
  
  public void setChild(boolean p_82227_1_) {
    p_82227_1_ = false;
    getDataWatcher().updateObject(12, Byte.valueOf((byte)(p_82227_1_ ? 1 : 0)));
    if (this.worldObj != null && !this.worldObj.isClient) {
      IAttributeInstance iattributeinstance = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      iattributeinstance.removeModifier(babySpeedBoostModifier);
      if (p_82227_1_)
        iattributeinstance.applyModifier(babySpeedBoostModifier); 
    } 
    func_146071_k(p_82227_1_);
  }
  
  public boolean isVillager() {
    return (getDataWatcher().getWatchableObjectByte(13) == 1);
  }
  
  public void setVillager(boolean p_82229_1_) {
    getDataWatcher().updateObject(13, Byte.valueOf((byte)(p_82229_1_ ? 1 : 0)));
  }
  
  public void onLivingUpdate() {
    if (this.worldObj.isDaytime() && !this.worldObj.isClient && !isChild()) {
      float f = getBrightness(1.0F);
      if (f > 0.5F) {
        this.entityAge -= 2;
        if (this.entityAge < 0)
          this.entityAge = 0; 
      } else {
        this.entityAge += 2;
      } 
      if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
        boolean flag = true;
        ItemStack itemstack = getEquipmentInSlot(4);
        if (itemstack != null) {
          if (itemstack.isItemStackDamageable()) {
            itemstack.setItemDamage(itemstack.getItemDamageForDisplay() + this.rand.nextInt(2));
            if (itemstack.getItemDamageForDisplay() >= itemstack.getMaxDamage()) {
              renderBrokenItemStack(itemstack);
              setCurrentItemOrArmor(4, (ItemStack)null);
            } 
          } 
          flag = false;
        } 
        if (flag);
      } 
    } 
    if (isRiding() && getAttackTarget() != null && this.ridingEntity instanceof EntityChicken)
      ((EntityLiving)this.ridingEntity).getNavigator().setPath(getNavigator().getPath(), 1.5D); 
    super.onLivingUpdate();
  }
  
  public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
    if (!super.attackEntityFrom(p_70097_1_, p_70097_2_))
      return false; 
    EntityLivingBase entitylivingbase = getAttackTarget();
    if (entitylivingbase == null && getEntityToAttack() instanceof EntityLivingBase)
      entitylivingbase = (EntityLivingBase)getEntityToAttack(); 
    if (entitylivingbase == null && p_70097_1_.getEntity() instanceof EntityLivingBase)
      entitylivingbase = (EntityLivingBase)p_70097_1_.getEntity(); 
    return true;
  }
  
  public void onUpdate() {
    if (!this.worldObj.isClient && isConverting()) {
      int i = getConversionTimeBoost();
      this.conversionTime -= i;
      if (this.conversionTime <= 0)
        convertToVillager(); 
    } 
    super.onUpdate();
  }
  
  public boolean attackEntityAsMob(Entity p_70652_1_) {
    boolean flag = super.attackEntityAsMob(p_70652_1_);
    if (flag) {
      int i = this.worldObj.difficultySetting.getDifficultyId();
      if (getHeldItem() == null && isBurning() && this.rand.nextFloat() < i * 0.3F)
        p_70652_1_.setFire(2 * i); 
    } 
    return flag;
  }
  
  protected String getLivingSound() {
    return "mob.zombie.say";
  }
  
  protected String getHurtSound() {
    return "mob.zombie.hurt";
  }
  
  protected String getDeathSound() {
    return "mob.zombie.death";
  }
  
  protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
    playSound("mob.zombie.step", 0.15F, 1.0F);
  }
  
  protected Item func_146068_u() {
    return Items.rotten_flesh;
  }
  
  public EnumCreatureAttribute getCreatureAttribute() {
    return EnumCreatureAttribute.UNDEAD;
  }
  
  protected void dropRareDrop(int p_70600_1_) {
    switch (this.rand.nextInt(3)) {
      case 0:
        func_145779_a(Items.iron_ingot, 1);
        break;
      case 1:
        func_145779_a(Items.carrot, 1);
        break;
      case 2:
        func_145779_a(Items.potato, 1);
        break;
    } 
  }
  
  protected void addRandomArmor() {
    super.addRandomArmor();
    if (this.rand.nextFloat() < ((this.worldObj.difficultySetting == EnumDifficulty.HARD) ? 0.05F : 0.01F)) {
      int i = this.rand.nextInt(3);
      if (i == 0) {
        setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
      } else {
        setCurrentItemOrArmor(0, new ItemStack(Items.iron_shovel));
      } 
    } 
  }
  
  public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
    super.writeEntityToNBT(p_70014_1_);
    if (isChild())
      p_70014_1_.setBoolean("IsBaby", true); 
    if (isVillager())
      p_70014_1_.setBoolean("IsVillager", true); 
    p_70014_1_.setInteger("ConversionTime", isConverting() ? this.conversionTime : -1);
    p_70014_1_.setBoolean("CanBreakDoors", func_146072_bX());
  }
  
  public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
    super.readEntityFromNBT(p_70037_1_);
    if (p_70037_1_.getBoolean("IsBaby"))
      setChild(true); 
    if (p_70037_1_.getBoolean("IsVillager"))
      setVillager(true); 
    if (p_70037_1_.func_150297_b("ConversionTime", 99) && p_70037_1_.getInteger("ConversionTime") > -1)
      startConversion(p_70037_1_.getInteger("ConversionTime")); 
    func_146070_a(p_70037_1_.getBoolean("CanBreakDoors"));
  }
  
  public void onKillEntity(EntityLivingBase p_70074_1_) {
    super.onKillEntity(p_70074_1_);
    if ((this.worldObj.difficultySetting == EnumDifficulty.NORMAL || this.worldObj.difficultySetting == EnumDifficulty.HARD) && p_70074_1_ instanceof EntityVillager) {
      if (this.worldObj.difficultySetting != EnumDifficulty.HARD && this.rand.nextBoolean())
        return; 
      EntityDayZombie entityzombie = new EntityDayZombie(this.worldObj);
      entityzombie.copyLocationAndAnglesFrom((Entity)p_70074_1_);
      this.worldObj.removeEntity((Entity)p_70074_1_);
      entityzombie.onSpawnWithEgg((IEntityLivingData)null);
      entityzombie.setVillager(true);
      if (p_70074_1_.isChild())
        entityzombie.setChild(true); 
      this.worldObj.spawnEntityInWorld((Entity)entityzombie);
      this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1016, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
    } 
  }
  
  public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_) {
    Object p_110161_1_1 = super.onSpawnWithEgg(p_110161_1_);
    float f = this.worldObj.func_147462_b(this.posX, this.posY, this.posZ);
    setCanPickUpLoot((this.rand.nextFloat() < 0.55F * f));
    if (p_110161_1_1 == null)
      p_110161_1_1 = new GroupData((this.worldObj.rand.nextFloat() < ForgeModContainer.zombieBabyChance), (this.worldObj.rand.nextFloat() < 0.05F), null); 
    if (p_110161_1_1 instanceof GroupData) {
      GroupData groupdata = (GroupData)p_110161_1_1;
      if (groupdata.field_142046_b)
        setVillager(true); 
      if (groupdata.field_142048_a) {
        setChild(true);
        if (this.worldObj.rand.nextFloat() < 0.05D) {
          List<EntityChicken> list = this.worldObj.selectEntitiesWithinAABB(EntityChicken.class, this.boundingBox.expand(5.0D, 3.0D, 5.0D), IEntitySelector.field_152785_b);
          if (!list.isEmpty()) {
            EntityChicken entitychicken = list.get(0);
            entitychicken.func_152117_i(true);
            mountEntity((Entity)entitychicken);
          } 
        } else if (this.worldObj.rand.nextFloat() < 0.05D) {
          EntityChicken entitychicken1 = new EntityChicken(this.worldObj);
          entitychicken1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
          entitychicken1.onSpawnWithEgg((IEntityLivingData)null);
          entitychicken1.func_152117_i(true);
          this.worldObj.spawnEntityInWorld((Entity)entitychicken1);
          mountEntity((Entity)entitychicken1);
        } 
      } 
    } 
    func_146070_a((this.rand.nextFloat() < f * 0.1F));
    addRandomArmor();
    enchantEquipment();
    if (getEquipmentInSlot(4) == null) {
      Calendar calendar = this.worldObj.getCurrentDate();
      if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
        setCurrentItemOrArmor(4, new ItemStack((this.rand.nextFloat() < 0.1F) ? Blocks.lit_pumpkin : Blocks.pumpkin));
        this.equipmentDropChances[4] = 0.0F;
      } 
    } 
    getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
    double d0 = this.rand.nextDouble() * 1.5D * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ);
    if (d0 > 1.0D)
      getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", d0, 2)); 
    if (this.rand.nextFloat() < f * 0.05F) {
      getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 0.25D + 0.5D, 0));
      getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
      func_146070_a(true);
    } 
    return (IEntityLivingData)p_110161_1_1;
  }
  
  public boolean interact(EntityPlayer p_70085_1_) {
    ItemStack itemstack = p_70085_1_.getCurrentEquippedItem();
    if (itemstack != null && itemstack.getItem() == Items.golden_apple && itemstack.getItemDamage() == 0 && isVillager() && isPotionActive(Potion.weakness)) {
      if (!p_70085_1_.capabilities.isCreativeMode)
        itemstack.stackSize--; 
      if (itemstack.stackSize <= 0)
        p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack)null); 
      if (!this.worldObj.isClient)
        startConversion(this.rand.nextInt(2401) + 3600); 
      return true;
    } 
    return false;
  }
  
  protected void startConversion(int p_82228_1_) {
    this.conversionTime = p_82228_1_;
    getDataWatcher().updateObject(14, Byte.valueOf((byte)1));
    removePotionEffect(Potion.weakness.id);
    addPotionEffect(new PotionEffect(Potion.damageBoost.id, p_82228_1_, Math.min(this.worldObj.difficultySetting.getDifficultyId() - 1, 0)));
    this.worldObj.setEntityState((Entity)this, (byte)16);
  }
  
  @SideOnly(Side.CLIENT)
  public void handleHealthUpdate(byte p_70103_1_) {
    if (p_70103_1_ == 16) {
      this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "mob.zombie.remedy", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
    } else {
      super.handleHealthUpdate(p_70103_1_);
    } 
  }
  
  protected boolean canDespawn() {
    return !isConverting();
  }
  
  public boolean isConverting() {
    return (getDataWatcher().getWatchableObjectByte(14) == 1);
  }
  
  protected void convertToVillager() {
    EntityVillager entityvillager = new EntityVillager(this.worldObj);
    entityvillager.copyLocationAndAnglesFrom((Entity)this);
    entityvillager.onSpawnWithEgg((IEntityLivingData)null);
    entityvillager.setLookingForHome();
    if (isChild())
      entityvillager.setGrowingAge(-24000); 
    this.worldObj.removeEntity((Entity)this);
    this.worldObj.spawnEntityInWorld((Entity)entityvillager);
    entityvillager.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1017, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
  }
  
  protected int getConversionTimeBoost() {
    int i = 1;
    if (this.rand.nextFloat() < 0.01F) {
      int j = 0;
      for (int k = (int)this.posX - 4; k < (int)this.posX + 4 && j < 14; k++) {
        for (int l = (int)this.posY - 4; l < (int)this.posY + 4 && j < 14; l++) {
          for (int i1 = (int)this.posZ - 4; i1 < (int)this.posZ + 4 && j < 14; i1++) {
            Block block = this.worldObj.getBlock(k, l, i1);
            if (block == Blocks.iron_bars || block == Blocks.bed) {
              if (this.rand.nextFloat() < 0.3F)
                i++; 
              j++;
            } 
          } 
        } 
      } 
    } 
    return i;
  }
  
  public void func_146071_k(boolean p_146071_1_) {
    func_146069_a(p_146071_1_ ? 0.5F : 1.0F);
  }
  
  protected final void setSize(float p_70105_1_, float p_70105_2_) {
    boolean flag = (this.field_146074_bv > 0.0F && this.field_146073_bw > 0.0F);
    this.field_146074_bv = p_70105_1_;
    this.field_146073_bw = p_70105_2_;
    if (!flag)
      func_146069_a(1.0F); 
  }
  
  protected final void func_146069_a(float p_146069_1_) {
    super.setSize(this.field_146074_bv * p_146069_1_, this.field_146073_bw * p_146069_1_);
  }
  
  class GroupData implements IEntityLivingData {
    public boolean field_142048_a;
    
    public boolean field_142046_b;
    
    private static final String __OBFID = "CL_00001704";
    
    private GroupData(boolean p_i2348_2_, boolean p_i2348_3_) {
      this.field_142048_a = false;
      this.field_142046_b = false;
      this.field_142048_a = p_i2348_2_;
      this.field_142046_b = p_i2348_3_;
    }
    
    GroupData(boolean p_i2349_2_, boolean p_i2349_3_, Object p_i2349_4_) {
      this(p_i2349_2_, p_i2349_3_);
    }
  }
  
  public float getBlockPathWeight(int p_70783_1_, int p_70783_2_, int p_70783_3_) {
    return 1.0F - this.worldObj.getLightBrightness(p_70783_1_, p_70783_2_, p_70783_3_);
  }
  
  protected boolean isValidLightLevel() {
    if (((int)(this.worldObj.getWorldTime() / 12000L) & 0x1) == 1)
      return false; 
    int i = MathHelper.floor_double(this.posX);
    int j = MathHelper.floor_double(this.boundingBox.minY);
    int k = MathHelper.floor_double(this.posZ);
    if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > this.rand.nextInt(32) || !this.worldObj.canBlockSeeTheSky(i, j, k))
      return false; 
    int i1 = this.worldObj.skylightSubtracted;
    this.worldObj.skylightSubtracted = 10;
    int l = this.worldObj.getBlockLightValue(i, j, k);
    this.worldObj.skylightSubtracted = i1;
    return (l <= this.rand.nextInt(8));
  }
}
