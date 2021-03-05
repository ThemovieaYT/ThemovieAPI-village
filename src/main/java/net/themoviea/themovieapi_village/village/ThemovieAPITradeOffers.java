package net.themoviea.themovieapi_village.village;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class ThemovieAPITradeOffers {
	
	public static void addTradeOffersToProfessions(HashMap<Object, Object> professionMap, EntityProfession profession, ImmutableMap<Integer, ThemovieAPITradeOffers.Factory[]> map) {
		professionMap.put(profession, copyToFastUtilMap(map));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Int2ObjectMap<ThemovieAPITradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, ThemovieAPITradeOffers.Factory[]> map) {
	      return new Int2ObjectOpenHashMap(map);
	}
	
	public static class OneEmeraldFactory implements ThemovieAPITradeOffers.Factory {
		private final Item buy;
		private final int price;
		private final int maxUses;
		private final int experience;
		private final float multiplier;
		
		public OneEmeraldFactory(ItemConvertible item, int price, int maxUses, int experience) {
	         this.buy = item.asItem();
	         this.price = price;
	         this.maxUses = maxUses;
	         this.experience = experience;
	         this.multiplier = 0.05F;
	      }
		
		@Override
		public @Nullable TradeOffer create(Entity entity, Random random) {
			ItemStack itemStack = new ItemStack(this.buy, this.price);
			return new TradeOffer(itemStack, new ItemStack(Items.EMERALD), this.maxUses, this.experience, this.multiplier);
		}
	}
	public interface Factory {
		@Nullable
		TradeOffer create(Entity entity, Random random);
	}
}
