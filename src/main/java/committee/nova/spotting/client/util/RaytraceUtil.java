package committee.nova.spotting.client.util;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.entity.PartEntity;

import java.util.Optional;

/**
 * Stole from ToroHealth(https://github.com/ToroCraft/ToroHealth)
 */
public class RaytraceUtil {
    public static Optional<Entity> getEntityInCrosshair(float partialTicks, double reachDistance) {
        final Minecraft client = Minecraft.getInstance();
        if (client.player == null) return Optional.empty();
        final Entity viewer = client.getRenderViewEntity();
        if (viewer == null) return Optional.empty();
        final Vector3d position = viewer.getEyePosition(partialTicks);
        final Vector3d look = viewer.getLook(1.0F);
        final Vector3d max = position.add(look.x * reachDistance, look.y * reachDistance, look.z * reachDistance);
        final AxisAlignedBB searchBox = viewer.getBoundingBox().expand(look.scale(reachDistance)).expand(1.0D, 1.0D, 1.0D);
        final EntityRayTraceResult result = ProjectileHelper.rayTraceEntities(viewer, position, max,
                searchBox, entity -> !entity.isSpectator() && entity.canBeCollidedWith(), reachDistance * reachDistance);
        if (result == null) return Optional.empty();
        final Entity e = result.getEntity();
        final Entity target = (e instanceof PartEntity) ? ((PartEntity<?>) e).getParent() : result.getEntity();
        final BlockRayTraceResult blockHit = rayTraceBlocks(
                setupRayTraceContext(client.player, reachDistance, RayTraceContext.FluidMode.NONE));
        if (!blockHit.getType().equals(RayTraceResult.Type.MISS)) {
            final double blockDistance = blockHit.getHitVec().distanceTo(position);
            if (blockDistance > target.getDistance(client.player)) {
                return Optional.of(target);
            }
        } else return Optional.of(target);
        return Optional.empty();
    }

    private static RayTraceContext setupRayTraceContext(PlayerEntity player, double distance, RayTraceContext.FluidMode fluidHandling) {
        final float pitch = player.rotationPitch;
        final float yaw = player.rotationYaw;
        final Vector3d fromPos = player.getEyePosition(1.0F);
        final float float_3 = MathHelper.cos(-yaw * 0.017453292F - 3.1415927F);
        final float float_4 = MathHelper.sin(-yaw * 0.017453292F - 3.1415927F);
        final float float_5 = -MathHelper.cos(-pitch * 0.017453292F);
        final float xComponent = float_4 * float_5;
        final float yComponent = MathHelper.sin(-pitch * 0.017453292F);
        final float zComponent = float_3 * float_5;
        final Vector3d toPos = fromPos.add((double) xComponent * distance, (double) yComponent * distance,
                (double) zComponent * distance);
        return new RayTraceContext(fromPos, toPos, RayTraceContext.BlockMode.OUTLINE, fluidHandling,
                player);
    }

    public static BlockRayTraceResult rayTraceBlocks(RayTraceContext context) {
        return IBlockReader.doRayTrace(context, (c, pos) -> {
            if (getWorld() == null) return null;
            final BlockState block = getWorld().getBlockState(pos);
            if (!block.isSolid()) {
                return null;
            }
            final VoxelShape blockShape = c.getBlockShape(block, getWorld(), pos);
            return getWorld().rayTraceBlocks(c.getStartVec(), c.getEndVec(), pos, blockShape, block);
        }, (c) -> {
            final Vector3d v = c.getStartVec().subtract(c.getEndVec());
            return BlockRayTraceResult.createMiss(c.getEndVec(), Direction.getFacingFromVector(v.x, v.y, v.z), new BlockPos(c.getEndVec()));
        });
    }

    private static World getWorld() {
        return Minecraft.getInstance().world;
    }
}
