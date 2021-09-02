package net.themoviea.themovieapi_village.village;

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.themoviea.themovieapi_base.exceptions.InputNotAnObjectException;
import net.themoviea.themovieapi_base.registering.CustomEasyRegister;
import net.themoviea.themovieapi_village.ThemovieAPIVillage;
import net.minecraft.block.BlockState;

public class EntityProfession {
	public static final EntityProfession NONE;
	private static EntityProfession entityProfession;
	private final ArrayList<Object> mcEntityProfession = new ArrayList<>();
	private final String id;
	private final BlockState workStation;
	private final ImmutableSet<Block> secondaryJobSites;
	@Nullable
	private final SoundEvent workSound;
	
	public EntityProfession(String id, BlockState workStation, SoundEvent workSound) {
		this(id, workStation, ImmutableSet.of(), workSound);
	}
	
	public EntityProfession(String id, BlockState workStation, ImmutableSet<Block> secondaryJobSites, SoundEvent workSound) {
		this.id = id;
		this.workStation = workStation;
		this.secondaryJobSites = secondaryJobSites;
		this.workSound = workSound;
	}
	
	public BlockState getWorkStation() {
		return this.workStation;
	}
	
	public String getId() {
		return this.id;
	}
	
	public SoundEvent getWorkSound() {
		return this.workSound;
	}
	
	public ImmutableSet<Block> getSecondaryJobSites() {
	      return this.secondaryJobSites;
	}
	
	public EntityProfession getObject() {
		return this;
	}
	
	public static EntityProfession getEntityProfession() {
		return entityProfession;
	}

	public ArrayList<Object> getArrayList() {
		return this.mcEntityProfession;
	}
	
	public static EntityProfession register(String id, BlockState workStation, @Nullable SoundEvent workSound) {
	      return register(id, workStation, ImmutableSet.of(), workSound);
	   }
	
	public static EntityProfession register(String id, BlockState workStation, ImmutableSet<Block> secondaryJobSites, @Nullable SoundEvent workSound) {
		return(EntityProfession)Registry.register(ThemovieAPIVillage.ENTITY_PROFESSION, new Identifier(id), new EntityProfession(id, workStation, secondaryJobSites, workSound));
	}
	
	static {
		NONE = register("themovieapivillage:none", ThemovieAPIVillage.ENTITY_UNEMPLOYED, (SoundEvent)null);
	}
}
