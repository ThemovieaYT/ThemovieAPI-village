package net.themoviea.themovieapi_village.entity.data;

import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.PacketByteBuf;
import net.themoviea.themovieapi_village.ThemovieAPIVillage;
import net.themoviea.themovieapi_village.village.EntityProfession;
import net.themoviea.themovieapi_village.village.VillageEntityData;

public class TrackedDataHandlerRegister {
	public static final TrackedDataHandler<VillageEntityData> VILLAGE_ENTITY_DATA = new TrackedDataHandler<VillageEntityData>() {
		public void write(PacketByteBuf data, VillageEntityData object) {
			data.writeVarInt(ThemovieAPIVillage.ENTITY_PROFESSION.getRawId(object.getProfession()));
		}
		
		public VillageEntityData read(PacketByteBuf packetByteBuf) {
			return new VillageEntityData((EntityProfession)ThemovieAPIVillage.ENTITY_PROFESSION.get(packetByteBuf.readVarInt()), packetByteBuf.readVarInt());
		}
		
		public VillageEntityData copy(VillageEntityData villageEntityData) {
			return villageEntityData;
		}
	};
	
	static {
		TrackedDataHandlerRegistry.register(VILLAGE_ENTITY_DATA);
	}
}
