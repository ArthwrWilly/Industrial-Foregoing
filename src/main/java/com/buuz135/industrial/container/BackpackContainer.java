/*
 * This file is part of Industrial Foregoing.
 *
 * Copyright 2021, Buuz135
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.buuz135.industrial.container;

import com.buuz135.industrial.item.infinity.item.ItemInfinityBackpack;
import com.buuz135.industrial.worlddata.BackpackDataManager;
import com.hrznstudio.titanium.container.BasicAddonContainer;
import com.hrznstudio.titanium.container.impl.DisableableSlot;
import com.hrznstudio.titanium.network.locator.LocatorInstance;
import com.hrznstudio.titanium.network.locator.instance.InventoryStackLocatorInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class BackpackContainer extends BasicAddonContainer {

    private final String id;
    private LocatorInstance instance;

    public BackpackContainer(Object provider, LocatorInstance locatorInstance, ContainerLevelAccess worldPosCallable, Inventory playerInventory, int containerId, String id) {
        super(provider, locatorInstance, worldPosCallable, playerInventory, containerId);
        this.id = id;
        this.instance = locatorInstance;
        if (this.instance instanceof InventoryStackLocatorInstance stackLocatorInstance){
            for (Slot slot : this.slots) {
                if (slot.getSlotIndex() == stackLocatorInstance.getInventorySlot() && slot instanceof DisableableSlot){
                    ItemStack dd = slot.getItem();
                    ((DisableableSlot)slot).setIsDisabled(() -> true);
                }
            }

        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot != null && slot.hasItem()) {
            BackpackDataManager.BackpackItemHandler handler = BackpackDataManager.getData(player.level).getBackpack(id);
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            for (int pos = 0; pos < handler.getSlots(); pos++) {
                if (!handler.getStackInSlot(pos).isEmpty() && handler.isItemValid(pos, itemstack1)){
                    ItemStack result = handler.insertItem(pos, itemstack1, false);
                    slot.set(result);
                    if (player instanceof ServerPlayer) ItemInfinityBackpack.sync(player.level, id , (ServerPlayer) player);
                    broadcastChanges();
                    break;
                    //return itemstack;
                }
            }
        }
        return super.quickMoveStack(player, index);
    }


}
