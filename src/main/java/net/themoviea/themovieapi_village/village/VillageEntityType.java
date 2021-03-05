package net.themoviea.themovieapi_village.village;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.themoviea.themovieapi_village.ThemovieAPIVillage;

public class VillageEntityType {
	public static final VillageEntityType NONE = create("none");
	public static final VillageEntityType RED = create("red");
	public static final VillageEntityType ORANGE = create("orange");
	public static final VillageEntityType YELLOW = create("yellow");
	
	private final String string;
	
	private VillageEntityType(String string) {
		this.string = string;
	}
	
	public String toString() {
		return this.string;
	}
	
	public static VillageEntityType create(String id) {
		return (VillageEntityType)Registry.register(ThemovieAPIVillage.VILLAGE_ENTITY_TYPE, (Identifier)(new Identifier("themovieapivillage", id)), new VillageEntityType(id));
	}
}
