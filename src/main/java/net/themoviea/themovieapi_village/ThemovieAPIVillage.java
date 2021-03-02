package net.themoviea.themovieapi_village;

import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Lifecycle;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;
import net.themoviea.themovieapi_village.village.EntityProfession;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ThemovieAPIVillage implements ModInitializer {
	public static final String MOD_ID = "themovieapivillage";
	public static final RegistryKey<Registry<EntityProfession>> ENTITY_PROFESSION_KEY;
	public static final Registry<EntityProfession> ENTITY_PROFESSION;
	
	private static final Supplier<Set<PointOfInterestType>> VILLAGE_ENTITY_WORKSTATIONS;
	public static final Predicate<PointOfInterestType> IS_USED_BY_ENTITY_PROFESSION;
	
	static {
		ENTITY_PROFESSION_KEY = RegistryKey.ofRegistry(new Identifier(MOD_ID, "entity_profession"));
		MutableRegistry<EntityProfession> temp = new DefaultedRegistry<>(MOD_ID + ":unemployed", ENTITY_PROFESSION_KEY, Lifecycle.experimental());
		ENTITY_PROFESSION = temp;
		VILLAGE_ENTITY_WORKSTATIONS = Suppliers.memoize(() -> {
			return (Set)ENTITY_PROFESSION.stream().map(EntityProfession::getWorkStation).collect(Collectors.toSet());
		});
		IS_USED_BY_ENTITY_PROFESSION = (pointOfInterestType) -> {
			return ((Set)VILLAGE_ENTITY_WORKSTATIONS.get()).contains(pointOfInterestType);
		};
	}
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
	}
}
