package net.heipiao.beefix.mixin;

import net.minecraft.entity.passive.BeeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeeEntity.class)
public class BeeEntityMixin {
    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/BeeEntity;setNearTarget(Z)V"), cancellable = true)
    public void isHiveValid(CallbackInfo ci){
        BeeEntity thisBee = (BeeEntity)(Object)this;
        if(thisBee.hasHive())
             if(!thisBee.world.isChunkLoaded(thisBee.getHivePos().getX()>>4, thisBee.getHivePos().getZ()>>4))
                ci.cancel();
    }
}
