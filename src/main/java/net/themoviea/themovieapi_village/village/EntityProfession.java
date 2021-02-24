package net.themoviea.themovieapi_village.village;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.poi.PointOfInterestType;

public class EntityProfession {
	private final String id;
	private final PointOfInterestType workStation;
	private final ImmutableSet<Block> secondaryJobSites;
	@Nullable
	private final SoundEvent workSound;
	
	private EntityProfession(String id, PointOfInterestType workStation, ImmutableSet<Block> secondaryJobSites, SoundEvent workSound) {
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
}
