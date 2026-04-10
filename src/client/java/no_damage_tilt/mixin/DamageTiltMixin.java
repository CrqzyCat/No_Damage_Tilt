package no_damage_tilt.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to {@link GameRenderer} to disable the damage tilt effect.
 * This class targets the {@code tiltViewWhenHurt} method to prevent the camera from tilting when the player takes damage.
 */
@Mixin(GameRenderer.class)
public class DamageTiltMixin {

    /**
     * Injects at the head of the {@code tiltViewWhenHurt} method to cancel its execution.
     * This effectively disables the visual damage tilt effect that normally occurs when the player is hurt.
     *
     * @param matrices The {@link MatrixStack} used for rendering transformations.
     * @param tickDelta The tick delta for smooth rendering.
     * @param ci The {@link CallbackInfo} to control the original method's execution.
     */
    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void stopDamageTilt(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        // Cancels the original method's execution, preventing the damage tilt.
        ci.cancel();
    }
}