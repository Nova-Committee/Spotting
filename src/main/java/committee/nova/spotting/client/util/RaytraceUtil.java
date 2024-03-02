package committee.nova.spotting.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.entity.PartEntity;

import java.util.Optional;

/**
 * Stole from ToroHealth(https://github.com/ToroCraft/ToroHealth)
 */
public class RaytraceUtil {
    public static Optional<Entity> getEntityInCrosshair(float partialTicks, double reachDistance) {
        final Minecraft client = Minecraft.getInstance();
        if (client.player == null) return Optional.empty();
        final Entity viewer = client.getCameraEntity();
        if (viewer == null) return Optional.empty();
        final Vec3 position = viewer.getEyePosition(partialTicks);
        final Vec3 look = viewer.getViewVector(1.0F);
        final Vec3 max = position.add(look.x * reachDistance, look.y * reachDistance, look.z * reachDistance);
        final AABB searchBox = viewer.getBoundingBox().expandTowards(look.scale(reachDistance)).inflate(1.0D, 1.0D, 1.0D);
        final EntityHitResult result = ProjectileUtil.getEntityHitResult(viewer, position, max,
                searchBox, entity -> !entity.isSpectator() && entity.isPickable(), reachDistance * reachDistance);
        if (result == null) return Optional.empty();
        final Entity e = result.getEntity();
        final Entity target = (e instanceof PartEntity) ? ((PartEntity<?>) e).getParent() : result.getEntity();
        HitResult blockHit =
                clip(setupRayTraceContext(client.player, reachDistance, ClipContext.Fluid.NONE));
        if (!blockHit.getType().equals(HitResult.Type.MISS)) {
            final double blockDistance = blockHit.getLocation().distanceTo(position);
            if (blockDistance > target.distanceTo(client.player)) return Optional.of(target);
        } else return Optional.of(target);
        return Optional.empty();
    }

    private static ClipContext setupRayTraceContext(Player player, double distance, ClipContext.Fluid fluidHandling) {
        final float pitch = player.getXRot();
        final float yaw = player.getYRot();
        final Vec3 fromPos = player.getEyePosition(1.0F);
        final float f3 = Mth.cos(-yaw * 0.017453292F - 3.1415927F);
        final float f4 = Mth.sin(-yaw * 0.017453292F - 3.1415927F);
        final float f5 = -Mth.cos(-pitch * 0.017453292F);
        final float xComponent = f4 * f5;
        final float yComponent = Mth.sin(-pitch * 0.017453292F);
        final float zComponent = f3 * f5;
        final Vec3 toPos = fromPos.add((double) xComponent * distance, (double) yComponent * distance,
                (double) zComponent * distance);
        return new ClipContext(fromPos, toPos, ClipContext.Block.OUTLINE, fluidHandling, player);
    }

    private static BlockHitResult clip(ClipContext context) {
        return BlockGetter.traverseBlocks(context.getFrom(), context.getTo(), context, (c, pos) -> {
            BlockState block = getWorld().getBlockState(pos);
            if (!block.canOcclude()) {
                return null;
            }
            VoxelShape voxelshape = c.getBlockShape(block, getWorld(), pos);
            return getWorld().clipWithInteractionOverride(c.getFrom(), c.getTo(), pos, voxelshape, block);
        }, (c) -> {
            final Vec3 to = c.getTo();
            Vec3 vec3 = c.getFrom().subtract(to);
            return BlockHitResult.miss(to, Direction.getNearest(vec3.x, vec3.y, vec3.z),
                    new BlockPos(Mth.floor(to.x), Mth.floor(to.y), Mth.floor(to.z)));
        });
    }

    private static Level getWorld() {
        return Minecraft.getInstance().level;
    }
}
