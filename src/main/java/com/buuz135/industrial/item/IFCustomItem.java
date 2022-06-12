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

package com.buuz135.industrial.item;

import com.hrznstudio.titanium.api.IRecipeProvider;
import com.hrznstudio.titanium.item.BasicItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.TranslatableComponent;

import javax.annotation.Nullable;

import net.minecraft.world.item.Item.Properties;

public abstract class IFCustomItem extends BasicItem implements IRecipeProvider {

    public IFCustomItem(String name, CreativeModeTab group, Properties builder) {
        super(name, builder.tab(group));
    }

    public IFCustomItem(String name, CreativeModeTab group) {
        this(name, group, new Properties());
    }

    @Nullable
    @Override
    public String getCreatorModId(ItemStack itemStack) {
        return new TranslatableComponent("itemGroup." + this.category.getRecipeFolderName()).getString();
    }
}
