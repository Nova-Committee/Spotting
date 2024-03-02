package committee.nova.spotting.mixin;

import committee.nova.spotting.common.capabilities.SpottingCapability;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntity extends CapabilityProvider<Entity> {
    protected MixinEntity(Class<Entity> baseClass) {
        super(baseClass);
    }

    @Inject(method = "isCurrentlyGlowing", at = @At("HEAD"), cancellable = true)
    private void inject$isGlowing(CallbackInfoReturnable<Boolean> cir) {
        this.getCapability(SpottingCapability.SPOTTABLE).ifPresent(s -> {
            if (s.isHighlighted()) cir.setReturnValue(true);
        });
    }
}
