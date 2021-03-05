package net.themoviea.themovieapi_village.village;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.themoviea.themovieapi_village.ThemovieAPIVillage;

public class VillageEntityData {
	private static final int[] LEVEL_BASE_EXPERIENCE = new int[]{0, 10, 70, 150, 250};
	public static final Codec<VillageEntityData> CODEC = RecordCodecBuilder.create((instance) -> {
	      return instance.group(ThemovieAPIVillage.VILLAGE_ENTITY_TYPE.fieldOf("village_entity_type").orElseGet(() -> {
	         return VillageEntityType.RED;
	      }).forGetter((villagerData) -> {
	         return villagerData.type;
	      }), ThemovieAPIVillage.ENTITY_PROFESSION.fieldOf("entity_profession").orElseGet(() -> {
	    	  return EntityProfession.NONE;
	      }).forGetter((villagerData) -> {
	    	  return villagerData.profession;
	      }), Codec.INT.fieldOf("level").orElse(1).forGetter((villagerData) -> {
	         return villagerData.level;
	      })).apply(instance, VillageEntityData::new);
	   });
	
	private final VillageEntityType type;
	private final EntityProfession profession;
	private final int level;
	
	public VillageEntityData(VillageEntityType type, EntityProfession profession, int i) {
		this.type = type;
		this.profession = profession;
		this.level = Math.max(1, i);
	}
	
	public VillageEntityType getVillageEntityType() {
		return this.type;
	}
	
	public EntityProfession getProfession() {
		return this.profession;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public VillageEntityData withVillageEntityType(VillageEntityType type) {
		return new VillageEntityData(type, this.profession, this.level);
	}
	
	public VillageEntityData withProfession(EntityProfession profession) {
		return new VillageEntityData(this.type, profession, this.level);
	}
	
	public VillageEntityData withLevel(int level) {
		return new VillageEntityData(this.type, this.profession, level);
	}
	
	@Environment(EnvType.CLIENT)
	public static int getLowerLevelExperience(int level) {
		return canLevelUp(level) ? LEVEL_BASE_EXPERIENCE[level - 1] : 0;
	}

	public static int getUpperLevelExperience(int level) {
	    return canLevelUp(level) ? LEVEL_BASE_EXPERIENCE[level] : 0;
	 }

	 public static boolean canLevelUp(int level) {
	    return level >= 1 && level < 5;
	 }
}
