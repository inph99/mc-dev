package net.minecraft.server;

import java.util.List;

final class DispenseBehaviorArmor extends DispenseBehaviorItem {

    DispenseBehaviorArmor() {}

    protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.b(isourceblock.h());
        int i = isourceblock.getBlockX() + enumfacing.getAdjacentX();
        int j = isourceblock.getBlockY() + enumfacing.getAdjacentY();
        int k = isourceblock.getBlockZ() + enumfacing.getAdjacentZ();
        AxisAlignedBB axisalignedbb = AxisAlignedBB.a((double) i, (double) j, (double) k, (double) (i + 1), (double) (j + 1), (double) (k + 1));
        List list = isourceblock.k().a(EntityLiving.class, axisalignedbb, (IEntitySelector) (new EntitySelectorEquipable(itemstack)));

        if (list.size() > 0) {
            EntityLiving entityliving = (EntityLiving) list.get(0);
            int l = entityliving instanceof EntityHuman ? 1 : 0;
            int i1 = EntityInsentient.b(itemstack);
            ItemStack itemstack1 = itemstack.cloneItemStack();

            itemstack1.count = 1;
            entityliving.setEquipment(i1 - l, itemstack1);
            if (entityliving instanceof EntityInsentient) {
                ((EntityInsentient) entityliving).a(i1, 2.0F);
            }

            --itemstack.count;
            return itemstack;
        } else {
            return super.b(isourceblock, itemstack);
        }
    }
}
