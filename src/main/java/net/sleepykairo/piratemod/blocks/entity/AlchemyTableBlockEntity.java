package net.sleepykairo.piratemod.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.item.ModItems;
import net.sleepykairo.piratemod.screen.AlchemyTableScreenHandler;
import org.jetbrains.annotations.Nullable;

public class AlchemyTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);

    private static final int FUEL_SLOT = 0;
    private static final int[] INPUT_SLOTS = new int[]{1, 2, 3, 4};
    private static final int OUTPUT_SLOT = 5;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 600;

    private int fuel = 0;
    private int fuelMax = 1200;

    private boolean fuelActive = false;

    public AlchemyTableBlockEntity( BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALCHEMY_TABLE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> AlchemyTableBlockEntity.this.progress;
                    case 1 -> AlchemyTableBlockEntity.this.maxProgress;
                    case 2 -> AlchemyTableBlockEntity.this.fuel;
                    case 3 -> AlchemyTableBlockEntity.this.fuelMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AlchemyTableBlockEntity.this.progress = value;
                    case 1 -> AlchemyTableBlockEntity.this.maxProgress = value;
                    case 2 -> AlchemyTableBlockEntity.this.fuel = value;
                    case 3 -> AlchemyTableBlockEntity.this.fuelMax = value;
                }
            }

            @Override
            public int size() {
                return 6;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Alchemy Table");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("alchemy_table.progress", progress);
        nbt.putInt("alchemy_table.fuel", fuel);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        nbt.getInt("alchemy_table.progress");
        nbt.getInt("alchemy_table.fuel");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AlchemyTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        updateFuel();

        if(fuelActive) {
            fuel--;
        }

        if(isOutputSlotEmptyOrReceivable()) {
            if(this.hasRecipe() && this.hasFuel()) {
                fuelActive = true;
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }

    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        for (int index : INPUT_SLOTS) {
            this.removeStack(INPUT_SLOTS[index - 1], 1);
        }
        ItemStack result = new ItemStack(ModItems.PLANT_INGOT);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
        //fuel -= fuelMax / 2;
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private boolean hasFuel() {
        return fuel > 0;
    }

    private boolean hasFuelInSlot() {
        return getStack(FUEL_SLOT).getItem() == ModItems.CATALYST;
    }

    private void updateFuel() {
        if(hasFuelInSlot() && !hasFuel()) {
            fuelActive = false;
            fuel = fuelMax;
            this.removeStack(FUEL_SLOT, 1);
        }
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack result = new ItemStack(ModItems.PLANT_INGOT);
        boolean hasInput = getStack(INPUT_SLOTS[1]).getItem() == ModItems.PLANT_MATTER;

        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }
}
