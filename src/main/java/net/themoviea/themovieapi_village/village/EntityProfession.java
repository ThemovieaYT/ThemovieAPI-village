package net.themoviea.themovieapi_village.village;

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.themoviea.themovieapi_base.exceptions.InputNotAnObjectException;
import net.themoviea.themovieapi_base.registering.CustomEasyRegister;
import net.themoviea.themovieapi_village.ThemovieAPIVillage;

public class EntityProfession implements CustomEasyRegister<EntityProfession> {
	public static final EntityProfession NONE;
	private static EntityProfession entityProfession;
	private final ArrayList<Object> mcEntityProfession = new ArrayList<>();
	private final String id;
	private final PointOfInterestType workStation;
	private final ImmutableSet<Block> secondaryJobSites;
	@Nullable
	private final SoundEvent workSound;
	
	public EntityProfession(String id, PointOfInterestType workStation, SoundEvent workSound) {
		this(id, workStation, ImmutableSet.of(), workSound);
	}
	
	public EntityProfession(String id, PointOfInterestType workStation, ImmutableSet<Block> secondaryJobSites, SoundEvent workSound) {
		this.id = id;
		this.workStation = workStation;
		this.secondaryJobSites = secondaryJobSites;
		this.workSound = workSound;
	}
	
	public PointOfInterestType getWorkStation() {
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

	@Override
	public EntityProfession getObject() {
		return this;
	}
	
	public static EntityProfession getEntityProfession() {
		return entityProfession;
	}

	@Override
	public ArrayList<Object> getArrayList() {
		return this.mcEntityProfession;
	}
	
	static EntityProfession register(String id, PointOfInterestType workStation, @Nullable SoundEvent workSound) {
	      return register(id, workStation, ImmutableSet.of(), workSound);
	   }
	
	static EntityProfession register(String id, PointOfInterestType workStation, ImmutableSet<Block> secondaryJobSites, @Nullable SoundEvent workSound) {
		return(EntityProfession)Registry.register(ThemovieAPIVillage.ENTITY_PROFESSION, new Identifier(id), new EntityProfession(id, workStation, secondaryJobSites, workSound));
	}
	
	@Override
	public void registerCustom(String modid, Registry<EntityProfession> registry) throws InputNotAnObjectException {
		CustomEasyRegister.super.registerCustom(modid, registry);
	}
	
	@Override
	public boolean createCustomRegisterList(Object... a) {
		return CustomEasyRegister.super.createCustomRegisterList(a);
	}
	
	public static void registerEntityProfessions(String modid, Object... a) {
		getEntityProfession().createCustomRegisterList(a);
		try {
			getEntityProfession().registerCustom(modid, ThemovieAPIVillage.ENTITY_PROFESSION);
		} catch (InputNotAnObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static {
		NONE = register("themovieapivillage:none", PointOfInterestType.UNEMPLOYED, (SoundEvent)null);
	}
}
