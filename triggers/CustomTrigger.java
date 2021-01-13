package com.jjhjart.ancientcraft.triggers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class CustomTrigger implements ICriterionTrigger<CustomTrigger.Instance> {

    private final ResourceLocation resourceloacation;
    public final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

    public CustomTrigger(ResourceLocation resourceLocation) {
        this.resourceloacation = resourceLocation;
    }

    @Override
    public ResourceLocation getId() {
        return this.resourceloacation;
    }

    @Override
    public void addListener(PlayerAdvancements playerAdvancements, Listener listener) {
        Listeners triggerListeners = listeners.get(playerAdvancements);

        if (triggerListeners == null) {
            triggerListeners = new Listeners(playerAdvancements);
            listeners.put(playerAdvancements, triggerListeners);
        }

        triggerListeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements playerAdvancements, Listener listener) {
        Listeners triggerListeners = listeners.get(playerAdvancements);

        if (triggerListeners != null) {
            triggerListeners.remove(listener);

            if (triggerListeners.isEmpty()) {
                listeners.remove(playerAdvancements);
            }
        }
    }

    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancements) {
        listeners.remove(playerAdvancements);
    }

    @Override
    public Instance deserializeInstance(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
        return new Instance(getId());
    }

    public void trigger(ServerPlayerEntity player) {
        Listeners triggerListeners = listeners.get(player.getAdvancements());

        if (triggerListeners != null) {
            triggerListeners.trigger(player);
        }
    }

    public static class Instance extends CriterionInstance {

        public Instance(ResourceLocation rl) {
            super(rl);
        }

        public boolean test() {
            return true;
        }
    }

    static class Listeners {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<?>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancements) {
            this.playerAdvancements = playerAdvancements;
        }

        public boolean isEmpty() {
            return listeners.isEmpty();
        }

        public void add(Listener<?> listener) {
            listeners.add(listener);
        }

        public void remove(Listener<?> listener) {
            listeners.remove(listener);
        }

        public void trigger(ServerPlayerEntity player) {
            ArrayList<Listener<?>> list = null;

            for (Listener<?> listener : listeners) {
                if (((Instance)listener.getCriterionInstance()).test()) {
                    if (list == null) {
                        list = Lists.newArrayList();
                    }
                    list.add(listener);
                }
            }

            if (list != null) {
                for (Listener<?> listener : list) {
                    listener.grantCriterion(playerAdvancements);
                }
            }
        }
    }
}
