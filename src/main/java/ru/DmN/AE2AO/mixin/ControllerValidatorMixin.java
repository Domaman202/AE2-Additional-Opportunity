package ru.DmN.AE2AO.mixin;

import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridNode;
import appeng.api.util.WorldCoord;
import appeng.me.pathfinding.ControllerValidator;
import appeng.tile.networking.ControllerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import ru.DmN.AE2AO.Main;

@Mixin(value={ControllerValidator.class})
public class ControllerValidatorMixin {
    @Shadow
    private boolean isValid = true;
    @Shadow
    private int found = 0;
    @Shadow
    private int minX;
    @Shadow
    private int minY;
    @Shadow
    private int minZ;
    @Shadow
    private int maxX;
    @Shadow
    private int maxY;
    @Shadow
    private int maxZ;

    @Shadow
    public int getFound() {
        return this.found;
    }

    @Shadow
    private void setFound(int found) {
        this.found = found;
    }

    @Shadow
    public boolean isValid() {
        return this.isValid;
    }

    @Shadow
    private void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * @author
     */
    @Overwrite(remap=false)
    public boolean visitNode(IGridNode n) throws Throwable {
        IGridHost host = n.getMachine();
        if (this.isValid && host instanceof ControllerBlockEntity) {
            WorldCoord pos = ((ControllerBlockEntity) host).getLocation();

            this.minX = Math.min(pos.x, this.minX);
            this.maxX = Math.max(pos.x, this.maxX);
            this.minY = Math.min(pos.y, this.minY);
            this.maxY = Math.max(pos.y, this.maxY);
            this.minZ = Math.min(pos.z, this.minZ);
            this.maxZ = Math.max(pos.z, this.maxZ);

            if (this.maxX - this.minX < Main.controllerX && this.maxY - this.minY < Main.controllerY && this.maxZ - this.minZ < Main.controllerZ) {
                this.setFound(this.getFound() + 1);
                return true;
            }
        } else {
            return false;
        }
        this.setValid(false);
        return this.isValid();
    }
}